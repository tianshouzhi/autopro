/*    */ package com.tianshouzhi.autopro.domain.functions;
/*    */ 
/*    */ import com.tianshouzhi.autopro.domain.db.DBTable;
/*    */ import com.tianshouzhi.autopro.domain.db.TableColumn;
/*    */ 
/*    */ public class LoginFunction extends BaseTableFunction
/*    */ {
/*    */   private TableColumn loginAccountColumn;
/*    */   private TableColumn loginPasswordColumn;
/*    */   private DBTable dbTable;
/*    */ 
/*    */   public LoginFunction(TableColumn loginAccountColumn, TableColumn loginPasswordColumn)
/*    */   {
/* 24 */     super("Login_Function");
/* 25 */     this.loginAccountColumn = loginAccountColumn;
/* 26 */     this.loginPasswordColumn = loginPasswordColumn;
/*    */   }
/*    */ 
/*    */   public TableColumn getLoginAccountColumn() {
/* 30 */     return this.loginAccountColumn;
/*    */   }
/*    */ 
/*    */   public void setLoginAccountColumn(TableColumn loginAccountColumn) {
/* 34 */     this.loginAccountColumn = loginAccountColumn;
/*    */   }
/*    */ 
/*    */   public TableColumn getLoginPasswordColumn() {
/* 38 */     return this.loginPasswordColumn;
/*    */   }
/*    */ 
/*    */   public void setLoginPasswordColumn(TableColumn loginPasswordColumn) {
/* 42 */     this.loginPasswordColumn = loginPasswordColumn;
/*    */   }
/*    */ 
/*    */   public DBTable getDbTable() {
/* 46 */     return this.dbTable;
/*    */   }
/*    */ 
/*    */   public void setDbTable(DBTable dbTable) {
/* 50 */     this.dbTable = dbTable;
/*    */   }
/*    */ }

/* Location:           D:\MyEclipse2015CI\dropins\com.tianshouzhi.autopro_1.0.0.201506242235.jar
 * Qualified Name:     com.tianshouzhi.autopro.domain.functions.LoginFunction
 * JD-Core Version:    0.6.2
 */