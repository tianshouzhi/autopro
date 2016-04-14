/*     */ package com.tianshouzhi.autopro.ui.wizard.page.extra;
/*     */ 
/*     */ import com.tianshouzhi.autopro.ui.wizard.page.extra.tab_functions_ui.ExcelFunctionUI;
/*     */ import com.tianshouzhi.autopro.ui.wizard.page.extra.tab_functions_ui.LoginFunctionUI;
/*     */ import com.tianshouzhi.autopro.ui.wizard.page.extra.tab_functions_ui.LongTextFunctionUI;
/*     */ import com.tianshouzhi.autopro.ui.wizard.page.extra.tab_functions_ui.ReportFunctionUI;
/*     */ import com.tianshouzhi.autopro.ui.wizard.page.extra.tab_functions_ui.TemplateEngineUI;
/*     */ import org.apache.log4j.Logger;
/*     */ import org.eclipse.jface.wizard.WizardPage;
/*     */ import org.eclipse.swt.custom.CTabFolder;
/*     */ import org.eclipse.swt.custom.CTabItem;
/*     */ import org.eclipse.swt.custom.ScrolledComposite;
/*     */ import org.eclipse.swt.widgets.Composite;
/*     */ import org.eclipse.wb.swt.SWTResourceManager;
/*     */ 
/*     */ public class TabFunctionUI extends WizardPage
/*     */ {
/*  22 */   private static final Logger logger = Logger.getLogger(TabFunctionUI.class);
/*     */   private CTabFolder tabFolder;
/*     */   private Composite login_item_area;
/*     */   private LoginFunctionUI loginFunctionUI;
/*     */   private String loginFunctionName;
/*     */   private String privilegeFunctionName;
/*     */   private Composite privilege_item_area;
/*     */   private String excelExportFunction;
/*     */   private ExcelFunctionUI excelFunctionUI;
/*     */   private Composite excelExport_item_area;
/*     */   private LongTextFunctionUI longTextFunctionUI;
/*     */   private Composite longText_item_area;
/*     */   private String workfolwFunction;
/*     */   private Composite workfolwFunction_item_area;
/*     */   private ReportFunctionUI reportFunctionUI;
/*     */   private ScrolledComposite reportScrolledComposite;
/*     */   private Composite report_item_area;
/*     */   private String templateEngineFunctionName;
/*     */   private Composite templateEngine_item_area;
/*     */   private TemplateEngineUI templateEngineUI;
/*     */   private Composite email_item_area;
/*     */   private String emailFunctionName;
/*     */ 
/*     */   public TabFunctionUI(String pageName)
/*     */   {
/*  54 */     super(pageName);
/*  55 */     setTitle("功能配置");
/*  56 */     setDescription("选择功能和对应的表");
/*     */   }
/*     */ 
/*     */   public void createControl(Composite parent)
/*     */   {
/*  61 */     Composite container = new Composite(parent, 0);
/*  62 */     createContents(container);
/*  63 */     setControl(container);
/*     */   }
/*     */ 
/*     */   public void init()
/*     */   {
/*  72 */     this.excelFunctionUI = new ExcelFunctionUI(this.excelExport_item_area);
/*     */ 
/*  74 */     this.longTextFunctionUI = new LongTextFunctionUI(this.longText_item_area);
/*     */ 
/*  76 */     this.loginFunctionUI = new LoginFunctionUI(this.login_item_area);
/*     */ 
/*  78 */     this.templateEngineUI = new TemplateEngineUI(this.templateEngine_item_area);
/*     */ 
/*  80 */     this.reportFunctionUI = new ReportFunctionUI(this.report_item_area);
/*  81 */     this.reportScrolledComposite.setMinSize(this.report_item_area.computeSize(-1, -1));
/*     */   }
/*     */ 
/*     */   private void createContents(Composite container)
/*     */   {
/*  87 */     this.tabFolder = new CTabFolder(container, 2048);
/*  88 */     this.tabFolder.setSelectionBackground(SWTResourceManager.getColor(35));
/*  89 */     this.tabFolder.setBounds(10, 10, 554, 454);
/*     */ 
/*  91 */     CTabItem loginTabItem = new CTabItem(this.tabFolder, 0);
/*  92 */     this.loginFunctionName = "登陆";
/*  93 */     loginTabItem.setText(this.loginFunctionName);
/*     */ 
/*  95 */     this.login_item_area = new Composite(this.tabFolder, 0);
/*  96 */     loginTabItem.setControl(this.login_item_area);
/*     */ 
/*  99 */     CTabItem tabItem_3 = new CTabItem(this.tabFolder, 0);
/* 100 */     this.privilegeFunctionName = "权限";
/* 101 */     tabItem_3.setText(this.privilegeFunctionName);
/*     */ 
/* 103 */     this.privilege_item_area = new Composite(this.tabFolder, 0);
/* 104 */     tabItem_3.setControl(this.privilege_item_area);
/*     */ 
/* 106 */     CTabItem tabItem = new CTabItem(this.tabFolder, 0);
/* 107 */     tabItem.setText("报表");
/*     */ 
/* 109 */     this.reportScrolledComposite = new ScrolledComposite(this.tabFolder, 2560);
/* 110 */     tabItem.setControl(this.reportScrolledComposite);
/* 111 */     this.reportScrolledComposite.setExpandHorizontal(true);
/* 112 */     this.reportScrolledComposite.setExpandVertical(true);
/*     */ 
/* 114 */     this.report_item_area = new Composite(this.reportScrolledComposite, 0);
/* 115 */     this.reportScrolledComposite.setContent(this.report_item_area);
/*     */ 
/* 117 */     CTabItem tbtmExcel = new CTabItem(this.tabFolder, 0);
/* 118 */     this.excelExportFunction = "Excel";
/* 119 */     tbtmExcel.setText(this.excelExportFunction);
/*     */ 
/* 121 */     this.excelExport_item_area = new Composite(this.tabFolder, 0);
/* 122 */     tbtmExcel.setControl(this.excelExport_item_area);
/*     */ 
/* 124 */     this.longText_item_area = new Composite(this.tabFolder, 0);
/* 125 */     CTabItem longtext_tab_item = new CTabItem(this.tabFolder, 0);
/* 126 */     longtext_tab_item.setControl(this.longText_item_area);
/* 127 */     longtext_tab_item.setText("htmlEditor");
/*     */ 
/* 129 */     CTabItem tabItem_2 = new CTabItem(this.tabFolder, 0);
/* 130 */     this.workfolwFunction = "工作流";
/* 131 */     tabItem_2.setText(this.workfolwFunction);
/* 132 */     this.workfolwFunction_item_area = new Composite(this.tabFolder, 512);
/* 133 */     tabItem_2.setControl(this.workfolwFunction_item_area);
/*     */ 
/* 135 */     CTabItem tabItem_5 = new CTabItem(this.tabFolder, 0);
/* 136 */     this.emailFunctionName = "邮件";
/* 137 */     tabItem_5.setText(this.emailFunctionName);
/*     */ 
/* 139 */     this.email_item_area = new Composite(this.tabFolder, 0);
/* 140 */     tabItem_5.setControl(this.email_item_area);
/*     */ 
/* 142 */     CTabItem tbtmFreemarker = new CTabItem(this.tabFolder, 0);
/* 143 */     this.templateEngineFunctionName = "模板引擎";
/* 144 */     tbtmFreemarker.setText(this.templateEngineFunctionName);
/*     */ 
/* 146 */     this.templateEngine_item_area = new Composite(this.tabFolder, 0);
/* 147 */     tbtmFreemarker.setControl(this.templateEngine_item_area);
/*     */ 
/* 149 */     CTabItem tabItem_4 = new CTabItem(this.tabFolder, 0);
/* 150 */     tabItem_4.setText("定时器");
/*     */ 
/* 152 */     this.tabFolder.setSelection(loginTabItem);
/*     */ 
/* 154 */     Composite composite = new Composite(this.tabFolder, 0);
/* 155 */     tabItem_4.setControl(composite);
/*     */   }
/*     */ }

/* Location:           D:\MyEclipse2015CI\dropins\com.tianshouzhi.autopro_1.0.0.201506242235.jar
 * Qualified Name:     com.tianshouzhi.autopro.ui.wizard.page.extra.TabFunctionUI
 * JD-Core Version:    0.6.2
 */