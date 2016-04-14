/*     */ package com.tianshouzhi.autopro.ui.wizard.page.main;
/*     */ 
/*     */ import com.tianshouzhi.autopro.domain.db.DBConnectionConfig;
/*     */ import com.tianshouzhi.autopro.domain.enums.DataBaseEnum;
/*     */ import com.tianshouzhi.autopro.domain.enums.MvcFrameworkEnum;
/*     */ import com.tianshouzhi.autopro.domain.enums.OrmFrameworkEnum;
/*     */ import com.tianshouzhi.autopro.domain.enums.ServiceFrameworkEnum;
/*     */ import com.tianshouzhi.autopro.ui.msg.Messages;
/*     */ import com.tianshouzhi.autopro.ui.wizard.page.extra.TabFunctionUI;
/*     */ import com.tianshouzhi.autopro.ui.wizard.page.main.dialog.SelectTableDialogUI;
/*     */ import com.tianshouzhi.autopro.util.DBTablesUtil;
/*     */ import com.tianshouzhi.autopro.util.DataBaseMetaInfoUtil;
/*     */ import com.tianshouzhi.autopro.util.JdbcUtil;
/*     */ import com.tianshouzhi.autopro.util.MavenDependcyUtil;
/*     */ import com.tianshouzhi.autopro.util.MessageHub;
/*     */ import com.tianshouzhi.autopro.util.StringUtil;
/*     */ import com.tianshouzhi.autopro.util.SupportedConstant;
/*     */ import com.tianshouzhi.autopro.util.XmlUtil;
/*     */ import java.sql.Connection;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import org.eclipse.jface.dialogs.MessageDialog;
/*     */ import org.eclipse.jface.wizard.WizardPage;
/*     */ import org.eclipse.swt.events.ModifyEvent;
/*     */ import org.eclipse.swt.events.ModifyListener;
/*     */ import org.eclipse.swt.events.SelectionAdapter;
/*     */ import org.eclipse.swt.events.SelectionEvent;
/*     */ import org.eclipse.swt.widgets.Button;
/*     */ import org.eclipse.swt.widgets.Combo;
/*     */ import org.eclipse.swt.widgets.Composite;
/*     */ import org.eclipse.swt.widgets.Control;
/*     */ import org.eclipse.swt.widgets.Group;
/*     */ import org.eclipse.swt.widgets.Label;
/*     */ import org.eclipse.swt.widgets.Shell;
/*     */ import org.eclipse.swt.widgets.Text;
/*     */ import org.eclipse.wb.swt.SWTResourceManager;
/*     */ 
/*     */ public class MainPage extends WizardPage
/*     */ {
/*     */   private SelectTableDialogUI selectTablesDialog;
/*     */   private TabFunctionUI tabFunctionUI;
/*     */   private Shell shell;
/*     */   private Text txt_db_url;
/*     */   private Text txt_db_username;
/*     */   private Text txt_db_password;
/*     */   private Combo combo_db_type;
/*  75 */   boolean generate_privilege = false;
/*  76 */   boolean generate_page = false;
/*     */   private Text text_basepackage;
/*     */   private Combo combo_orm_version;
/*     */   private Combo combo_spring_version;
/*     */   private Combo combo_mvc_version;
/*     */   private Combo combo_orm_type;
/*     */   private Combo combo_mvc_type;
/*     */   private Group grp_service;
/*     */ 
/*     */   public MainPage(TabFunctionUI tabFunctionUI)
/*     */   {
/*  51 */     super("mainPage");
/*  52 */     setTitle("主配置");
/*  53 */     this.tabFunctionUI = tabFunctionUI;
/*  54 */     setDescription("选择数据库、Dao层、Service层、Web层框架和版本,如需更多配置点击下一页");
/*     */   }
/*     */ 
/*     */   public void createControl(Composite parent)
/*     */   {
/*  62 */     Composite container = new Composite(parent, 0);
/*  63 */     createContents(container);
/*  64 */     setControl(container);
/*     */   }
/*     */ 
/*     */   protected Control createContents(Composite container)
/*     */   {
/*  83 */     this.shell = container.getShell();
/*     */ 
/*  85 */     Group group = new Group(container, 0);
/*  86 */     group.setForeground(
/*  87 */       SWTResourceManager.getColor(21));
/*  88 */     group.setText(Messages.ConfigUI_LANG_DB_CONFIG);
/*  89 */     group.setBounds(10, 48, 568, 82);
/*     */ 
/*  91 */     Label label = new Label(group, 0);
/*  92 */     label.setBounds(4, 21, 69, 15);
/*  93 */     label.setText(Messages.ConfigUI_LANG_DB_TYPE);
/*     */ 
/*  95 */     this.combo_db_type = new Combo(group, 8);
/*  96 */     this.combo_db_type.setBounds(79, 18, 112, 25);
/*  97 */     this.combo_db_type.setItems(DataBaseEnum.getSuppDbNames());
/*  98 */     this.combo_db_type.setText(DataBaseEnum.getDefault().name());
/*  99 */     this.combo_db_type.addSelectionListener(new DBTypeSelectListener(null));
/*     */ 
/* 101 */     Label lblUrl = new Label(group, 0);
/* 102 */     lblUrl.setBounds(41, 50, 21, 15);
/* 103 */     lblUrl.setText(Messages.ConfigUI_LANG_DB_URL);
/*     */ 
/* 105 */     Label label_1 = new Label(group, 131072);
/* 106 */     label_1.setBounds(208, 21, 42, 15);
/* 107 */     label_1.setText(Messages.ConfigUI_LANG_DB_USERNAME);
/*     */ 
/* 109 */     this.txt_db_username = new Text(group, 2048);
/* 110 */     this.txt_db_username.setText(Messages.ConfigUI_txtRoot_text);
/* 111 */     this.txt_db_username.setBounds(256, 18, 84, 21);
/* 112 */     this.txt_db_username.addModifyListener(new AutoCheckDBConfigListener(null));
/*     */ 
/* 114 */     Label label_2 = new Label(group, 0);
/* 115 */     label_2.setBounds(346, 21, 27, 15);
/* 116 */     label_2.setText(Messages.ConfigUI_LANG_DB_PASSWORD);
/*     */ 
/* 118 */     this.txt_db_password = new Text(group, 2048);
/* 119 */     this.txt_db_password.setBounds(379, 18, 73, 21);
/* 120 */     this.txt_db_password.addModifyListener(new AutoCheckDBConfigListener(null));
/*     */ 
/* 122 */     Button button_db_test = new Button(group, 0);
/* 123 */     button_db_test.addSelectionListener(new CheckDBConfigListener(null));
/* 124 */     button_db_test.setBounds(514, 45, 44, 25);
/* 125 */     button_db_test.setText(Messages.ConfigUI_LANG_DB_TEST);
/*     */ 
/* 127 */     this.txt_db_url = new Text(group, 2048);
/* 128 */     this.txt_db_url.setBounds(77, 47, 431, 21);
/* 129 */     this.txt_db_url.setText(Messages.ConfigUI_txtJdbcmysqllocalhostbloguseunicodetruecharacterencodingutf_text);
/* 130 */     this.txt_db_url.addModifyListener(new AutoCheckDBConfigListener(null));
/*     */ 
/* 132 */     Group grpDao = new Group(container, 0);
/* 133 */     grpDao.setText(Messages.ConfigUI_LANG_ORM_CONFIG);
/* 134 */     grpDao.setBounds(10, 136, 568, 58);
/*     */ 
/* 136 */     Label label_3 = new Label(grpDao, 131072);
/* 137 */     label_3.setBounds(10, 25, 55, 15);
/* 138 */     label_3.setText(Messages.ConfigUI_LANG_ORM_TYPE);
/*     */ 
/* 140 */     this.combo_orm_type = new Combo(grpDao, 8);
/* 141 */     this.combo_orm_type.addSelectionListener(new OrmSelectListener(null));
/* 142 */     this.combo_orm_type.setBounds(78, 22, 113, 25);
/* 143 */     this.combo_orm_type.setItems(OrmFrameworkEnum.getOrmDisplayNames());
/* 144 */     this.combo_orm_type.setText(OrmFrameworkEnum.getDefault().getDisplayName());
/* 145 */     Label label_4 = new Label(grpDao, 0);
/* 146 */     label_4.setBounds(217, 25, 24, 15);
/* 147 */     label_4.setText(Messages.ConfigUI_LANG_ORM_VERSION);
/*     */ 
/* 149 */     Button button_db_select_tables = new Button(grpDao, 0);
/* 150 */     button_db_select_tables.addSelectionListener(new OpenSelectTableDialogUIListener(container));
/* 151 */     button_db_select_tables.setBounds(381, 21, 75, 25);
/* 152 */     button_db_select_tables
/* 153 */       .setText(Messages.ConfigUI_LANG_SELECT_TABLES_BTN);
/*     */ 
/* 155 */     this.combo_orm_version = new Combo(grpDao, 0);
/* 156 */     this.combo_orm_version.setBounds(258, 22, 88, 25);
/* 157 */     this.combo_orm_version.setItems(OrmFrameworkEnum.getDefault().getSupportVersions());
/* 158 */     this.combo_orm_version.setText(OrmFrameworkEnum.getDefault().getDefaultVersion());
/*     */ 
/* 160 */     this.grp_service = new Group(container, 0);
/* 161 */     this.grp_service.setText(Messages.ConfigUI_LANG_SERVICE_CONFIG);
/* 162 */     this.grp_service.setBounds(10, 200, 568, 68);
/*     */ 
/* 164 */     Label lblSpring = new Label(this.grp_service, 0);
/* 165 */     lblSpring.setBounds(10, 31, 65, 22);
/* 166 */     lblSpring.setText(Messages.ConfigUI_LANG_SPRING_VERSION);
/*     */ 
/* 168 */     Label label_5 = new Label(this.grp_service, 0);
/* 169 */     label_5.setBounds(219, 31, 55, 15);
/* 170 */     label_5.setText(Messages.ConfigUI_LANG_INTEGRATION_TYPE);
/*     */ 
/* 172 */     Button radio_integration_xml = new Button(this.grp_service, 16);
/* 173 */     radio_integration_xml.setBounds(280, 31, 49, 16);
/* 174 */     radio_integration_xml.setText(SupportedConstant.SPRING_CONFIG_INTEGRATION_TYPE_XML);
/*     */ 
/* 176 */     Button radio_integration_annotation = new Button(this.grp_service, 16);
/* 177 */     radio_integration_annotation.setBounds(335, 31, 90, 16);
/* 178 */     radio_integration_annotation.setText(SupportedConstant.SPRING_CONFIG_INTEGRATION_TYPE_ANNOTATION);
/* 179 */     radio_integration_annotation.setSelection(true);
/*     */ 
/* 181 */     this.combo_spring_version = new Combo(this.grp_service, 0);
/* 182 */     this.combo_spring_version.setBounds(81, 28, 108, 25);
/* 183 */     this.combo_spring_version.setItems(ServiceFrameworkEnum.SPRING.getSupportedVersions());
/* 184 */     this.combo_spring_version.setText(ServiceFrameworkEnum.SPRING.getDefaultVersion());
/*     */ 
/* 186 */     Group grpWeb = new Group(container, 0);
/* 187 */     grpWeb.setText(Messages.ConfigUI_LANG_WEB_LAYER);
/* 188 */     grpWeb.setBounds(10, 274, 568, 58);
/*     */ 
/* 190 */     Label lblNewLabel = new Label(grpWeb, 131072);
/* 191 */     lblNewLabel.setBounds(10, 25, 55, 15);
/* 192 */     lblNewLabel.setText(Messages.ConfigUI_LANG_MVC_TYPE);
/*     */ 
/* 194 */     this.combo_mvc_type = new Combo(grpWeb, 8);
/* 195 */     this.combo_mvc_type.addSelectionListener(new MvcSelectListener(null));
/* 196 */     this.combo_mvc_type.setBounds(78, 22, 111, 25);
/* 197 */     this.combo_mvc_type.setItems(MvcFrameworkEnum.getMvcFrameworkDisplayNames());
/* 198 */     this.combo_mvc_type.setText(MvcFrameworkEnum.getDefault().getDisplayName());
/*     */ 
/* 200 */     Label label_7 = new Label(grpWeb, 131072);
/* 201 */     label_7.setBounds(214, 25, 31, 15);
/* 202 */     label_7.setText(Messages.ConfigUI_LANG_MVC_VERSION);
/*     */ 
/* 204 */     this.combo_mvc_version = new Combo(grpWeb, 0);
/* 205 */     this.combo_mvc_version.setBounds(261, 22, 111, 25);
/* 206 */     this.combo_mvc_version.setItems(ServiceFrameworkEnum.getDefault().getSupportedVersions());
/* 207 */     this.combo_mvc_version.setText(ServiceFrameworkEnum.getDefault().getDefaultVersion());
/*     */ 
/* 209 */     Label label_8 = new Label(container, 0);
/* 210 */     label_8.setBounds(20, 13, 55, 15);
/* 211 */     label_8.setText(Messages.ConfigUI_label_8_text);
/*     */ 
/* 213 */     this.text_basepackage = new Text(container, 2048);
/* 214 */     String basePackage = XmlUtil.getBasePackage();
/* 215 */     if (StringUtil.isEmpty(basePackage)) {
/* 216 */       basePackage = "com.autopro.example";
/*     */     }
/* 218 */     this.text_basepackage.setText(basePackage);
/* 219 */     this.text_basepackage.setBounds(87, 10, 136, 21);
/*     */ 
/* 221 */     return container;
/*     */   }
/*     */ 
/*     */   private DBConnectionConfig getDBConfig()
/*     */   {
/* 228 */     String db_type = this.combo_db_type.getText();
/* 229 */     String username = this.txt_db_username.getText();
/* 230 */     String password = this.txt_db_password.getText();
/* 231 */     String url = this.txt_db_url.getText();
/* 232 */     return new DBConnectionConfig(url, db_type, username, password);
/*     */   }
/*     */ 
/*     */   public boolean checkDBConfig(boolean showRightInfo)
/*     */   {
/* 294 */     String db_type = this.combo_db_type.getText();
/* 295 */     String username = this.txt_db_username.getText();
/* 296 */     String password = this.txt_db_password.getText();
/* 297 */     String url = this.txt_db_url.getText();
/* 298 */     boolean isEmpty = (StringUtil.isEmpty(db_type)) || (StringUtil.isEmpty(username)) || (StringUtil.isEmpty(password)) || (StringUtil.isEmpty(url));
/* 299 */     String message = "";
/* 300 */     if (isEmpty) {
/* 301 */       message = Messages.ConfigUI_DBCONFIG_NULL_TEXT;
/* 302 */       MessageDialog.openError(this.shell, 
/* 303 */         Messages.ConfigUI_TEST_DB_TITLE, message);
/* 304 */       return false;
/*     */     }
/* 306 */     DBConnectionConfig dbConfig = new DBConnectionConfig(url, db_type, username, password);
/*     */ 
/* 308 */     Connection connection = JdbcUtil.getConnection(dbConfig);
/* 309 */     if (connection == null) {
/* 310 */       message = Messages.ConfigUI_TEST_DB_ERROR;
/*     */     } else {
/* 312 */       message = Messages.ConfigUI_TEST_DB_OK;
/* 313 */       MessageHub.put("DB_CONNECTION_CONFIG", dbConfig);
/*     */     }
/*     */ 
/* 317 */     if (message.equals(Messages.ConfigUI_TEST_DB_ERROR)) {
/* 318 */       MessageDialog.openError(this.shell, 
/* 319 */         Messages.ConfigUI_TEST_DB_TITLE, dbConfig.toString() + Messages.ConfigUI_DBCONFIG_2 + message);
/* 320 */       return false;
/*     */     }
/* 322 */     if (showRightInfo)
/* 323 */       MessageDialog.openInformation(this.shell, 
/* 324 */         Messages.ConfigUI_TEST_DB_TITLE, dbConfig.toString() + Messages.ConfigUI_DBCONFIG_2 + message);
/* 325 */     return true;
/*     */   }
/*     */ 
/*     */   public void initMainPageMessageHubParams()
/*     */   {
/* 332 */     String springVersion = this.combo_spring_version.getText();
/* 333 */     String serviceFramworkName = "Spring";
/* 334 */     List serviceDependencies = 
/* 335 */       MavenDependcyUtil.getServiceDependencies(serviceFramworkName, springVersion);
/* 336 */     String ormName = this.combo_orm_type.getText();
/* 337 */     String ormVersion = this.combo_orm_version.getText();
/* 338 */     List ormDependencies = MavenDependcyUtil.getOrmDependencies(ormName, ormVersion);
/* 339 */     String mvcName = this.combo_mvc_type.getText();
/* 340 */     String mvcVersion = this.combo_mvc_version.getText();
/* 341 */     List mvcDependencies = 
/* 342 */       MavenDependcyUtil.getMvcDependencies(mvcName, mvcVersion);
/* 343 */     List mavenDependencies = (List)
/* 344 */       MessageHub.get("List_MavenDependency");
/* 345 */     if (mavenDependencies == null) {
/* 346 */       mavenDependencies = new ArrayList();
/*     */     }
/* 348 */     mavenDependencies.addAll(mvcDependencies);
/* 349 */     mavenDependencies.addAll(ormDependencies);
/* 350 */     mavenDependencies.addAll(serviceDependencies);
/*     */ 
/* 352 */     MessageHub.put("List_MavenDependency", mavenDependencies);
/*     */ 
/* 354 */     String basePackage = this.text_basepackage.getText();
/* 355 */     MessageHub.put("BASE_PACKAGE", basePackage);
/*     */   }
/*     */   private class AutoCheckDBConfigListener implements ModifyListener {
/*     */     private AutoCheckDBConfigListener() {
/*     */     }
/*     */ 
/*     */     public void modifyText(ModifyEvent e) {
/* 362 */       String username = MainPage.this.txt_db_username.getText();
/* 363 */       String password = MainPage.this.txt_db_password.getText();
/* 364 */       String db_url = MainPage.this.txt_db_url.getText();
/* 365 */       String dbType = MainPage.this.combo_db_type.getText();
/* 366 */       DBConnectionConfig dbConfig = new DBConnectionConfig(db_url, dbType, username, password);
/* 367 */       Connection connection = JdbcUtil.getConnection(dbConfig);
/* 368 */       if (connection != null) {
/* 369 */         List dbTables = DataBaseMetaInfoUtil.getDBTables(dbConfig);
/* 370 */         DBTablesUtil.putDBTables(dbTables);
/* 371 */         MainPage.this.setPageComplete(true);
/* 372 */         MessageHub.put("DB_CONNECTION_CONFIG", dbConfig);
/*     */ 
/* 374 */         MainPage.this.tabFunctionUI.init();
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   private class CheckDBConfigListener extends SelectionAdapter
/*     */   {
/*     */     private CheckDBConfigListener()
/*     */     {
/*     */     }
/*     */ 
/*     */     public void widgetSelected(SelectionEvent e)
/*     */     {
/* 256 */       MainPage.this.checkDBConfig(true);
/*     */     }
/*     */   }
/*     */ 
/*     */   private class DBTypeSelectListener extends SelectionAdapter
/*     */   {
/*     */     private DBTypeSelectListener()
/*     */     {
/*     */     }
/*     */ 
/*     */     public void widgetSelected(SelectionEvent e)
/*     */     {
/* 281 */       String dbTypeName = MainPage.this.combo_db_type.getText();
/* 282 */       DataBaseEnum db = DataBaseEnum.getDataBaseByName(dbTypeName);
/* 283 */       MainPage.this.txt_db_url.setText(db.getSampleUrl());
/* 284 */       MainPage.this.txt_db_username.setText(db.getDefaultUser());
/* 285 */       MainPage.this.txt_db_password.setText(db.getDefaultPass());
/*     */     }
/*     */   }
/*     */ 
/*     */   private class MvcSelectListener extends SelectionAdapter
/*     */   {
/*     */     private MvcSelectListener()
/*     */     {
/*     */     }
/*     */ 
/*     */     public void widgetSelected(SelectionEvent e)
/*     */     {
/* 270 */       String selectedMvc = MainPage.this.combo_mvc_type.getText();
/* 271 */       MvcFrameworkEnum mvcFramework = MvcFrameworkEnum.getMvcFrameworkEnumEnumByName(selectedMvc);
/* 272 */       MainPage.this.combo_mvc_version.setItems(mvcFramework.getSupportVersions());
/* 273 */       MainPage.this.combo_mvc_version.setText(mvcFramework.getDefaultVersion());
/*     */     }
/*     */   }
/*     */ 
/*     */   private class OpenSelectTableDialogUIListener extends SelectionAdapter
/*     */   {
/*     */     private Composite parent;
/*     */ 
/*     */     public OpenSelectTableDialogUIListener(Composite parent)
/*     */     {
/* 238 */       this.parent = parent;
/*     */     }
/*     */ 
/*     */     public void widgetSelected(SelectionEvent e) {
/* 242 */       boolean isSuccess = MainPage.this.checkDBConfig(false);
/* 243 */       if (!isSuccess) {
/* 244 */         return;
/*     */       }
/* 246 */       MainPage.this.selectTablesDialog = new SelectTableDialogUI(this.parent.getShell(), MainPage.this.getDBConfig(), MainPage.this.tabFunctionUI, MainPage.this);
/* 247 */       MainPage.this.selectTablesDialog.open();
/* 248 */       MainPage.this.selectTablesDialog.setBlockOnOpen(true);
/*     */     }
/*     */   }
/*     */ 
/*     */   private class OrmSelectListener extends SelectionAdapter
/*     */   {
/*     */     private OrmSelectListener()
/*     */     {
/*     */     }
/*     */ 
/*     */     public void widgetSelected(SelectionEvent e)
/*     */     {
/* 262 */       String selectedOrm = MainPage.this.combo_orm_type.getText();
/* 263 */       OrmFrameworkEnum ormFramework = OrmFrameworkEnum.getDaoFrameworkEnumByName(selectedOrm);
/* 264 */       MainPage.this.combo_orm_version.setItems(ormFramework.getSupportVersions());
/* 265 */       MainPage.this.combo_orm_version.setText(ormFramework.getDefaultVersion());
/*     */     }
/*     */   }
/*     */ }

/* Location:           D:\MyEclipse2015CI\dropins\com.tianshouzhi.autopro_1.0.0.201506242235.jar
 * Qualified Name:     com.tianshouzhi.autopro.ui.wizard.page.main.MainPage
 * JD-Core Version:    0.6.2
 */