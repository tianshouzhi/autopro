/*     */ package com.tianshouzhi.autopro.ui.wizard.page.extra.tab_functions_ui;
/*     */ 
/*     */ import com.tianshouzhi.autopro.domain.db.TableColumn;
/*     */ import com.tianshouzhi.autopro.domain.functions.LoginFunction;
/*     */ import com.tianshouzhi.autopro.util.DBTablesUtil;
/*     */ import com.tianshouzhi.autopro.util.StringUtil;
/*     */ import org.eclipse.jface.dialogs.MessageDialog;
/*     */ import org.eclipse.swt.events.SelectionAdapter;
/*     */ import org.eclipse.swt.events.SelectionEvent;
/*     */ import org.eclipse.swt.widgets.Button;
/*     */ import org.eclipse.swt.widgets.Combo;
/*     */ import org.eclipse.swt.widgets.Composite;
/*     */ import org.eclipse.swt.widgets.Label;
/*     */ import org.eclipse.swt.widgets.Shell;
/*     */ 
/*     */ public class LoginFunctionUI
/*     */ {
/*     */   public Combo combo_tableName;
/*     */   public Combo combo_login_column;
/*     */   public Combo combo_password_column;
/*     */   public Shell shell;
/*     */ 
/*     */   public LoginFunctionUI(Composite parent)
/*     */   {
/*  28 */     this.shell = parent.getShell();
/*  29 */     createContents(parent);
/*     */   }
/*     */ 
/*     */   private void createContents(Composite container) {
/*  33 */     Label label_select_table = new Label(container, 0);
/*  34 */     label_select_table.setBounds(10, 13, 61, 17);
/*  35 */     label_select_table.setText("选择用户表");
/*  36 */     this.combo_tableName = new Combo(container, 8);
/*  37 */     this.combo_tableName.setBounds(80, 10, 120, 17);
/*  38 */     this.combo_tableName.setItems(DBTablesUtil.getSelectedTableNames());
/*  39 */     this.combo_tableName.addSelectionListener(new SelectTableListener());
/*  40 */     Label label_select_login_column = new Label(container, 8);
/*  41 */     label_select_login_column.setBounds(10, 53, 61, 17);
/*  42 */     label_select_login_column.setText("登陆账号字段");
/*  43 */     this.combo_login_column = new Combo(container, 8);
/*  44 */     this.combo_login_column.setBounds(79, 50, 120, 25);
/*     */ 
/*  47 */     Label label_1 = new Label(container, 0);
/*  48 */     label_1.setBounds(259, 53, 61, 17);
/*  49 */     label_1.setText("密码字段");
/*  50 */     this.combo_password_column = new Combo(container, 8);
/*  51 */     this.combo_password_column.setBounds(330, 50, 120, 25);
/*     */ 
/*  53 */     Button button = new Button(container, 32);
/*  54 */     button.setBounds(22, 90, 98, 17);
/*  55 */     button.setText("登陆/注销)");
/*  56 */     button.setSelection(true);
/*  57 */     button.setEnabled(false);
/*     */ 
/*  59 */     Button button_1 = new Button(container, 32);
/*  60 */     button_1.setBounds(200, 90, 170, 17);
/*  61 */     button_1.setText("自动登陆功能(使用Cookie)");
/*  62 */     button_1.setSelection(true);
/*  63 */     button_1.setEnabled(false);
/*     */ 
/*  65 */     Button button_2 = new Button(container, 32);
/*  66 */     button_2.setBounds(200, 120, 128, 17);
/*  67 */     button_2.setText("登陆验证码");
/*  68 */     button_2.setSelection(true);
/*  69 */     button_2.setEnabled(false);
/*     */ 
/*  71 */     Button button_3 = new Button(container, 32);
/*  72 */     button_3.setBounds(22, 120, 150, 17);
/*  73 */     button_3.setText("密码加密方式(默认MD5)");
/*  74 */     button_3.setSelection(true);
/*  75 */     button_3.setEnabled(false);
/*     */ 
/*  77 */     Button btn_add = new Button(container, 0);
/*  78 */     btn_add.setBounds(222, 155, 61, 23);
/*  79 */     btn_add.setText("添加");
/*  80 */     btn_add.addSelectionListener(new AddLoginFunctionListener());
/*     */ 
/*  82 */     Button btn_reset = new Button(container, 0);
/*  83 */     btn_reset.setBounds(300, 155, 61, 23);
/*  84 */     btn_reset.setText("重置");
/*  85 */     btn_reset.addSelectionListener(new ResetLoginFunctionListener());
/*     */   }
/*     */ 
/*     */   class AddLoginFunctionListener extends SelectionAdapter
/*     */   {
/*     */     AddLoginFunctionListener()
/*     */     {
/*     */     }
/*     */ 
/*     */     public void widgetSelected(SelectionEvent e)
/*     */     {
/* 111 */       String tableName = LoginFunctionUI.this.combo_tableName.getText();
/* 112 */       String loginColumnName = LoginFunctionUI.this.combo_login_column.getText();
/* 113 */       String passwordColumnName = LoginFunctionUI.this.combo_password_column.getText();
/* 114 */       if ((StringUtil.isEmpty(tableName)) || 
/* 115 */         (StringUtil.isEmpty(loginColumnName)) || 
/* 116 */         (StringUtil.isEmpty(passwordColumnName))) {
/* 117 */         MessageDialog.openInformation(LoginFunctionUI.this.shell, "警告", "请选择用户表,登陆名和密码字段");
/*     */       } else {
/* 119 */         TableColumn loginAccountColumn = DBTablesUtil.getTableColumn(tableName, loginColumnName);
/* 120 */         TableColumn loginPasswordColumn = DBTablesUtil.getTableColumn(tableName, passwordColumnName);
/* 121 */         LoginFunction loginFunction = new LoginFunction(loginAccountColumn, loginPasswordColumn);
/* 122 */         if (!DBTablesUtil.hasLoginFunction(tableName)) {
/* 123 */           DBTablesUtil.addTableFunction(tableName, loginFunction);
/*     */         } else {
/* 125 */           DBTablesUtil.removeLoginFunction(tableName);
/* 126 */           DBTablesUtil.addTableFunction(tableName, loginFunction);
/*     */         }
/* 128 */         LoginFunctionUI.this.combo_tableName.setText(tableName + "(已选)");
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   class ResetLoginFunctionListener extends SelectionAdapter
/*     */   {
/*     */     ResetLoginFunctionListener()
/*     */     {
/*     */     }
/*     */ 
/*     */     public void widgetSelected(SelectionEvent e)
/*     */     {
/* 141 */       String tableName = LoginFunctionUI.this.combo_tableName.getText();
/* 142 */       LoginFunctionUI.this.combo_tableName.setText("");
/* 143 */       LoginFunctionUI.this.combo_password_column.setText("");
/* 144 */       LoginFunctionUI.this.combo_login_column.setText("");
/* 145 */       boolean has = DBTablesUtil.hasLoginFunction(tableName);
/* 146 */       if (has)
/* 147 */         DBTablesUtil.removeLoginFunction(tableName);
/*     */     }
/*     */   }
/*     */ 
/*     */   class SelectTableListener extends SelectionAdapter
/*     */   {
/*     */     SelectTableListener()
/*     */     {
/*     */     }
/*     */ 
/*     */     public void widgetSelected(SelectionEvent e)
/*     */     {
/*  95 */       String tableName = LoginFunctionUI.this.combo_tableName.getText();
/*  96 */       if (StringUtil.isEmpty(tableName)) {
/*  97 */         return;
/*     */       }
/*  99 */       String[] columnNames = DBTablesUtil.getSelectedDbTableColumnNames(tableName);
/* 100 */       LoginFunctionUI.this.combo_login_column.setItems(columnNames);
/* 101 */       LoginFunctionUI.this.combo_password_column.setItems(columnNames);
/*     */     }
/*     */   }
/*     */ }

/* Location:           D:\MyEclipse2015CI\dropins\com.tianshouzhi.autopro_1.0.0.201506242235.jar
 * Qualified Name:     com.tianshouzhi.autopro.ui.wizard.page.extra.tab_functions_ui.LoginFunctionUI
 * JD-Core Version:    0.6.2
 */