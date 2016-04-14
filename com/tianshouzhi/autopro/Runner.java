/*    */ package com.tianshouzhi.autopro;
/*    */ 
/*    */ import com.tianshouzhi.autopro.ui.wizard.ConfigWizard;
/*    */ import com.tianshouzhi.autopro.util.MessageHub;
/*    */ import com.tianshouzhi.autopro.util.PluginUtil;
/*    */ import org.apache.log4j.Logger;
/*    */ import org.eclipse.core.commands.AbstractHandler;
/*    */ import org.eclipse.core.commands.ExecutionEvent;
/*    */ import org.eclipse.core.commands.ExecutionException;
/*    */ import org.eclipse.core.resources.IProject;
/*    */ import org.eclipse.core.runtime.IPath;
/*    */ import org.eclipse.jdt.core.IJavaProject;
/*    */ import org.eclipse.jface.wizard.WizardDialog;
/*    */ import org.eclipse.ui.IWorkbenchWindow;
/*    */ import org.eclipse.ui.handlers.HandlerUtil;
/*    */ 
/*    */ public class Runner extends AbstractHandler
/*    */ {
/* 19 */   private static final Logger logger = Logger.getLogger(Runner.class);
/*    */ 
/*    */   public Object execute(ExecutionEvent event)
/*    */     throws ExecutionException
/*    */   {
/*    */     try
/*    */     {
/* 26 */       IWorkbenchWindow workbenchWindow = HandlerUtil.getActiveWorkbenchWindow(event);
/* 27 */       ConfigWizard wizard = new ConfigWizard(workbenchWindow.getShell(), PluginUtil.getCurrentProject(event));
/* 28 */       WizardDialog dialog = new WizardDialog(workbenchWindow.getShell(), wizard);
/* 29 */       MessageHub.put("PROJECT_NAME", PluginUtil.getCurrentProject(event).getProject().getName());
/* 30 */       MessageHub.put("PROJECT_FS_PATH", PluginUtil.getCurrentProject(event).getProject().getLocation().toOSString());
/* 31 */       dialog.open();
/*    */     }
/*    */     catch (Exception e)
/*    */     {
/* 38 */       logger.error("error", e);
/*    */     }
/* 40 */     return null;
/*    */   }
/*    */ }

/* Location:           D:\MyEclipse2015CI\dropins\com.tianshouzhi.autopro_1.0.0.201506242235.jar
 * Qualified Name:     com.tianshouzhi.autopro.Runner
 * JD-Core Version:    0.6.2
 */