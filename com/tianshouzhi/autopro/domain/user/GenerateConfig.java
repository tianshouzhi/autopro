/*    */ package com.tianshouzhi.autopro.domain.user;
/*    */ 
/*    */ import com.tianshouzhi.autopro.domain.db.DBConnectionConfig;
/*    */ import com.tianshouzhi.autopro.domain.db.DBTable;
/*    */ import java.util.List;
/*    */ import javax.xml.bind.annotation.XmlRootElement;
/*    */ import javax.xml.bind.annotation.XmlTransient;
/*    */ 
/*    */ @XmlRootElement
/*    */ public class GenerateConfig
/*    */ {
/*    */   private List<DBTable> dbTables;
/*    */   private DBConnectionConfig dbConfig;
/*    */   private String basePackage;
/*    */   private String web_container_path;
/*    */ 
/*    */   @XmlTransient
/*    */   public List<DBTable> getDbTables()
/*    */   {
/* 24 */     return this.dbTables;
/*    */   }
/*    */ 
/*    */   public void setDbTables(List<DBTable> dbTables) {
/* 28 */     this.dbTables = dbTables;
/*    */   }
/*    */ 
/*    */   public DBConnectionConfig getDbConfig() {
/* 32 */     return this.dbConfig;
/*    */   }
/*    */ 
/*    */   public void setDbConfig(DBConnectionConfig dbConfig) {
/* 36 */     this.dbConfig = dbConfig;
/*    */   }
/*    */ 
/*    */   public GenerateConfig()
/*    */   {
/*    */   }
/*    */ 
/*    */   public String getBasePackage()
/*    */   {
/* 46 */     return this.basePackage;
/*    */   }
/*    */ 
/*    */   public void setBasePackage(String basePackage) {
/* 50 */     this.basePackage = basePackage;
/*    */   }
/*    */ 
/*    */   public String getWeb_container_path() {
/* 54 */     return this.web_container_path;
/*    */   }
/*    */ 
/*    */   public void setWeb_container_path(String web_container_path) {
/* 58 */     this.web_container_path = web_container_path;
/*    */   }
/*    */ 
/*    */   public GenerateConfig(List<DBTable> dbTables, DBConnectionConfig dbConfig, String basePackage, String web_container_path)
/*    */   {
/* 65 */     this.dbTables = dbTables;
/* 66 */     this.dbConfig = dbConfig;
/* 67 */     this.basePackage = basePackage;
/* 68 */     this.web_container_path = web_container_path;
/*    */   }
/*    */ }

/* Location:           D:\MyEclipse2015CI\dropins\com.tianshouzhi.autopro_1.0.0.201506242235.jar
 * Qualified Name:     com.tianshouzhi.autopro.domain.user.GenerateConfig
 * JD-Core Version:    0.6.2
 */