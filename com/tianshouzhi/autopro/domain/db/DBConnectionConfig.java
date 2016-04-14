/*    */ package com.tianshouzhi.autopro.domain.db;
/*    */ 
/*    */ import javax.xml.bind.annotation.XmlType;
/*    */ 
/*    */ @XmlType
/*    */ public class DBConnectionConfig
/*    */ {
/*    */   private String url;
/*    */   private String username;
/*    */   private String password;
/*    */   private String db_type;
/*    */ 
/*    */   public DBConnectionConfig(String url, String db_type, String username, String password)
/*    */   {
/* 16 */     this.url = url;
/* 17 */     this.username = username;
/* 18 */     this.password = password;
/* 19 */     this.db_type = db_type;
/*    */   }
/*    */ 
/*    */   public DBConnectionConfig() {
/*    */   }
/*    */ 
/*    */   public String getUrl() {
/* 26 */     return this.url;
/*    */   }
/*    */   public void setUrl(String url) {
/* 29 */     this.url = url;
/*    */   }
/*    */   public String getUsername() {
/* 32 */     return this.username;
/*    */   }
/*    */   public void setUsername(String username) {
/* 35 */     this.username = username;
/*    */   }
/*    */   public String getPassword() {
/* 38 */     return this.password;
/*    */   }
/*    */   public void setPassword(String password) {
/* 41 */     this.password = password;
/*    */   }
/*    */   public String getDb_type() {
/* 44 */     return this.db_type;
/*    */   }
/*    */   public void setDb_type(String db_type) {
/* 47 */     this.db_type = db_type;
/*    */   }
/*    */ 
/*    */   public String toString() {
/* 51 */     return "Database=" + this.db_type + "\r\nurl=" + this.url + "\r\nusername=" + this.username + 
/* 52 */       "\r\npassword=" + this.password;
/*    */   }
/*    */ }

/* Location:           D:\MyEclipse2015CI\dropins\com.tianshouzhi.autopro_1.0.0.201506242235.jar
 * Qualified Name:     com.tianshouzhi.autopro.domain.db.DBConnectionConfig
 * JD-Core Version:    0.6.2
 */