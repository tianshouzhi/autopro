/*     */ package com.tianshouzhi.autopro.ui.wizard;
/*     */ 
/*     */ import com.tianshouzhi.autopro.domain.db.DBConnectionConfig;
/*     */ import com.tianshouzhi.autopro.domain.db.DBTable;
/*     */ import com.tianshouzhi.autopro.service.code.CodeGenerator;
/*     */ import com.tianshouzhi.autopro.ui.msg.Messages;
/*     */ import com.tianshouzhi.autopro.ui.wizard.page.extra.TabFunctionUI;
/*     */ import com.tianshouzhi.autopro.ui.wizard.page.main.MainPage;
/*     */ import com.tianshouzhi.autopro.util.JdbcUtil;
/*     */ import com.tianshouzhi.autopro.util.MessageHub;
/*     */ import java.lang.reflect.InvocationTargetException;
/*     */ import java.util.List;
/*     */ import org.apache.log4j.Logger;
/*     */ import org.eclipse.core.resources.IProject;
/*     */ import org.eclipse.core.runtime.CoreException;
/*     */ import org.eclipse.core.runtime.IProgressMonitor;
/*     */ import org.eclipse.jdt.core.IJavaProject;
/*     */ import org.eclipse.jface.dialogs.MessageDialog;
/*     */ import org.eclipse.jface.dialogs.ProgressMonitorDialog;
/*     */ import org.eclipse.jface.operation.IRunnableWithProgress;
/*     */ import org.eclipse.jface.wizard.Wizard;
/*     */ import org.eclipse.swt.widgets.Shell;
/*     */ 
/*     */ public class ConfigWizard extends Wizard
/*     */ {
/*  27 */   private static Logger logger = Logger.getLogger(ConfigWizard.class);
/*     */   private MainPage mainPage;
/*     */   private TabFunctionUI tabFunctionUI;
/*     */   private Shell parentShell;
/*     */   private IJavaProject currentProject;
/*     */   boolean isSuccess;
/*     */ 
/*     */   public ConfigWizard(Shell shell, IJavaProject currentProject)
/*     */   {
/*  34 */     this.parentShell = shell;
/*  35 */     this.currentProject = currentProject;
/*  36 */     setWindowTitle("代码生成配置向导");
/*     */   }
/*     */ 
/*     */   public void addPages()
/*     */   {
/*  43 */     this.tabFunctionUI = new TabFunctionUI("附加功能页");
/*  44 */     this.mainPage = new MainPage(this.tabFunctionUI);
/*  45 */     List dbTables = (List)MessageHub.get("SELECTED_TABLES");
/*  46 */     if ((dbTables == null) || (dbTables.isEmpty()))
/*  47 */       this.mainPage.setPageComplete(false);
/*     */     else {
/*  49 */       this.mainPage.setPageComplete(true);
/*     */     }
/*  51 */     addPage(this.mainPage);
/*     */ 
/*  53 */     addPage(this.tabFunctionUI);
/*     */   }
/*     */ 
/*     */   public void dispose()
/*     */   {
/*  59 */     MessageHub.release();
/*  60 */     super.dispose();
/*     */   }
/*     */ 
/*     */   public boolean performFinish()
/*     */   {
/*     */     try
/*     */     {
/*  71 */       boolean checkResult = checkConfig();
/*  72 */       if (!checkResult) {
/*  73 */         return false;
/*     */       }
/*     */ 
/*  76 */       this.mainPage.initMainPageMessageHubParams();
/*     */ 
/*  78 */       generateCode();
/*     */ 
/*  80 */       if (this.isSuccess) {
/*  81 */         MessageDialog.openInformation(this.parentShell, 
/*  82 */           Messages.ConfigUI_OPERATE_RESULT, 
/*  83 */           Messages.ConfigUI_OPERATE_SUCCESS);
/*     */       }
/*     */       else {
/*  86 */         MessageDialog.openError(this.parentShell, 
/*  87 */           Messages.ConfigUI_OPERATE_RESULT, 
/*  88 */           Messages.ConfigUI_OPERATE_FAIL);
/*     */       }
/*  90 */       return true;
/*     */     } catch (Exception e) {
/*  92 */       MessageDialog.openError(this.parentShell, "代码生成失败", "对不起,代码生成失败！请重试");
/*  93 */       logger.error("", e);
/*     */     } finally {
/*  95 */       MessageHub.release();
/*     */     }
/*  97 */     return false;
/*     */   }
/*     */ 
/*     */   private boolean checkConfig()
/*     */   {
/* 102 */     boolean checkResult = false;
/*     */ 
/* 104 */     DBConnectionConfig dbConfig = (DBConnectionConfig)MessageHub.get("DB_CONNECTION_CONFIG");
/* 105 */     checkResult = checkDbConfig(dbConfig);
/* 106 */     if (!checkResult) {
/* 107 */       return checkResult;
/*     */     }
/*     */ 
/* 110 */     checkResult = checkHasSelectedTable();
/* 111 */     return checkResult;
/*     */   }
/*     */ 
/*     */   private boolean checkHasSelectedTable()
/*     */   {
/* 122 */     List dbTables = (List)MessageHub.get("SELECTED_TABLES");
/* 123 */     int selectCounts = 0;
/* 124 */     String error_no_select_table = Messages.ConfigUI_NO_SELECT_TABLE;
/* 125 */     if (dbTables == null) {
/* 126 */       MessageDialog.openError(this.parentShell, null, error_no_select_table);
/* 127 */       return false;
/*     */     }
/* 129 */     for (DBTable dbTable : dbTables) {
/* 130 */       if (dbTable.isGenerateCode()) {
/* 131 */         selectCounts++;
/*     */       }
/*     */     }
/* 134 */     if (selectCounts < 1) {
/* 135 */       MessageDialog.openError(this.parentShell, null, error_no_select_table);
/* 136 */       return false;
/*     */     }
/* 138 */     return true;
/*     */   }
/*     */ 
/*     */   public boolean canFinish()
/*     */   {
/* 144 */     List dbTables = (List)MessageHub.get("SELECTED_TABLES");
/* 145 */     if ((dbTables != null) && (dbTables.size() > 0)) {
/* 146 */       return true;
/*     */     }
/* 148 */     return false;
/*     */   }
/*     */ 
/*     */   private boolean checkDbConfig(DBConnectionConfig connectionConfig)
/*     */   {
/* 157 */     if (!JdbcUtil.checkDBConnectionConfig(connectionConfig)) {
/* 158 */       String message = Messages.ConfigUI_DBCONFIG_NULL_TEXT;
/* 159 */       MessageDialog.openError(this.parentShell, 
/* 160 */         Messages.ConfigUI_TEST_DB_TITLE, message);
/* 161 */       return false;
/*     */     }
/* 163 */     return true;
/*     */   }
/*     */ 
/*     */   private void generateCode()
/*     */   {
/* 172 */     ProgressMonitorDialog progress = new ProgressMonitorDialog(this.parentShell);
/*     */     try {
/* 174 */       progress.run(true, true, new IRunnableWithProgress()
/*     */       {
/*     */         public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException
/*     */         {
/* 178 */           int totalWork = 2;
/* 179 */           for (int i = 0; i < totalWork; i++) {
/* 180 */             if (monitor.isCanceled()) {
/*     */               break;
/*     */             }
/* 183 */             switch (i) {
/*     */             case 0:
/* 185 */               monitor.subTask(Messages.ConfigUI_Generating);
/* 186 */               ConfigWizard.this.isSuccess = CodeGenerator.generate();
/* 187 */               monitor.worked(1);
/* 188 */               break;
/*     */             case 1:
/* 191 */               monitor.subTask(Messages.ConfigUI_Refreshing);
/*     */               try {
/* 193 */                 ConfigWizard.this.currentProject.getProject().refreshLocal(2, 
/* 194 */                   null);
/*     */               } catch (CoreException e) {
/* 196 */                 ConfigWizard.logger.error(Messages.ConfigUI_Refreshing_Error, e);
/*     */               }
/* 198 */               monitor.worked(1);
/*     */             }
/*     */ 
/*     */           }
/*     */ 
/* 204 */           monitor.done();
/*     */         }
/*     */       });
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/* 210 */       logger.error("代码生成失败", e);
/*     */     }
/*     */   }
/*     */ }

/* Location:           D:\MyEclipse2015CI\dropins\com.tianshouzhi.autopro_1.0.0.201506242235.jar
 * Qualified Name:     com.tianshouzhi.autopro.ui.wizard.ConfigWizard
 * JD-Core Version:    0.6.2
 */