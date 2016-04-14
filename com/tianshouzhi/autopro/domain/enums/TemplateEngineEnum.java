/*    */ package com.tianshouzhi.autopro.domain.enums;
/*    */ 
/*    */ public enum TemplateEngineEnum
/*    */ {
/*  4 */   FREEMARKER("Freemarker", 
/*  5 */     "src/main/resources/fk_templates", "2.3.9"), 
/*    */ 
/*  7 */   VELOCITY("Velocity", "src/main/resources/v_templates", "");
/*    */ 
/*    */   private String displayName;
/*    */   private String defaultTemplateDir;
/*    */   private String defaultVersion;
/*    */ 
/* 15 */   public String getDisplayName() { return this.displayName; }
/*    */ 
/*    */   public void setDisplayName(String displayName) {
/* 18 */     this.displayName = displayName;
/*    */   }
/*    */ 
/*    */   private TemplateEngineEnum(String displayName, String defaultTemplateDir, String defaultVersion)
/*    */   {
/* 23 */     this.displayName = displayName;
/* 24 */     this.defaultTemplateDir = defaultTemplateDir;
/* 25 */     this.defaultVersion = defaultVersion;
/*    */   }
/*    */   public String getDefaultTemplateDir() {
/* 28 */     return this.defaultTemplateDir;
/*    */   }
/*    */   public void setDefaultTemplateDir(String defaultTemplateDir) {
/* 31 */     this.defaultTemplateDir = defaultTemplateDir;
/*    */   }
/*    */   public String getDefaultVersion() {
/* 34 */     return this.defaultVersion;
/*    */   }
/*    */   public void setDefaultVersion(String defaultVersion) {
/* 37 */     this.defaultVersion = defaultVersion;
/*    */   }
/*    */   public static TemplateEngineEnum getDefault() {
/* 40 */     return FREEMARKER;
/*    */   }
/*    */ 
/*    */   public static TemplateEngineEnum getTemplateEngineEnumByName(String name)
/*    */   {
/* 48 */     for (TemplateEngineEnum templateEngineEnum : values()) {
/* 49 */       if (templateEngineEnum.name().equalsIgnoreCase(name)) {
/* 50 */         return templateEngineEnum;
/*    */       }
/*    */     }
/* 53 */     return null;
/*    */   }
/*    */ 
/*    */   public static String[] getTemplateEngineDisplayNames()
/*    */   {
/* 61 */     TemplateEngineEnum[] values = values();
/* 62 */     String[] templateEngineNames = new String[values.length];
/* 63 */     int i = 0;
/* 64 */     TemplateEngineEnum[] arrayOfTemplateEngineEnum1 = values; int j = values.length; for (int i = 0; i < j; i++) { TemplateEngineEnum templateEngineEnum = arrayOfTemplateEngineEnum1[i];
/* 65 */       templateEngineNames[i] = templateEngineEnum.getDisplayName();
/* 66 */       i++;
/*    */     }
/* 68 */     return templateEngineNames;
/*    */   }
/*    */ }

/* Location:           D:\MyEclipse2015CI\dropins\com.tianshouzhi.autopro_1.0.0.201506242235.jar
 * Qualified Name:     com.tianshouzhi.autopro.domain.enums.TemplateEngineEnum
 * JD-Core Version:    0.6.2
 */