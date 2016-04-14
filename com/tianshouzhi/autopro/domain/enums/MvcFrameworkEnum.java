/*    */ package com.tianshouzhi.autopro.domain.enums;
/*    */ 
/*    */ public enum MvcFrameworkEnum
/*    */ {
/*  4 */   SPRINGMVC("SpringMvc", 
/*  5 */     null, 
/*  6 */     null, 
/*  7 */     "Web_SpringMvc_SpringMvcConfig.xml.ftl", 
/*  8 */     "Web_SpringMvc_BaseControllerImpl.java.ftl", 
/*  9 */     "Web_SpringMvc_EntityNameController.java.ftl"), 
/* 10 */   STRUTS2("Struts2", 
/* 11 */     "2.3.16.3", 
/* 12 */     new String[] { "2.3.20", "2.3.16.3" }, 
/* 13 */     "Web_Struts2_struts.xml.ftl", 
/* 14 */     "Web_Struts2_BaseActionImpl.java.ftl", 
/* 15 */     "Web_Struts2_EntityNameAction.java.ftl");
/*    */ 
/*    */   private String displayName;
/*    */   private String defaultVersion;
/*    */   private String[] supportVersions;
/*    */   private String coreConfigTempName;
/*    */   private String baseControllerTempName;
/*    */   private String entityNameControllerTempName;
/*    */ 
/* 27 */   private MvcFrameworkEnum(String displayName, String defaultVersion, String[] supportVersions, String coreConfigTempName, String baseControllerTempName, String entityNameControllerName) { this.displayName = displayName;
/* 28 */     this.defaultVersion = defaultVersion;
/* 29 */     this.supportVersions = supportVersions;
/* 30 */     this.coreConfigTempName = coreConfigTempName;
/* 31 */     this.baseControllerTempName = baseControllerTempName;
/* 32 */     this.entityNameControllerTempName = entityNameControllerName; }
/*    */ 
/*    */   public String getDisplayName()
/*    */   {
/* 36 */     return this.displayName;
/*    */   }
/*    */   public String getDefaultVersion() {
/* 39 */     return this.defaultVersion;
/*    */   }
/*    */   public String[] getSupportVersions() {
/* 42 */     return this.supportVersions;
/*    */   }
/*    */   public String getCoreConfigTempName() {
/* 45 */     return this.coreConfigTempName;
/*    */   }
/*    */   public String getBaseControllerTempName() {
/* 48 */     return this.baseControllerTempName;
/*    */   }
/*    */ 
/*    */   public String getEntityNameControllerTempName() {
/* 52 */     return this.entityNameControllerTempName;
/*    */   }
/*    */ 
/*    */   public static MvcFrameworkEnum getMvcFrameworkEnumEnumByName(String name)
/*    */   {
/* 59 */     for (MvcFrameworkEnum mvcFrameworkEnum : values()) {
/* 60 */       if (mvcFrameworkEnum.name().equalsIgnoreCase(name)) {
/* 61 */         return mvcFrameworkEnum;
/*    */       }
/*    */     }
/* 64 */     return null;
/*    */   }
/*    */ 
/*    */   public static String[] getMvcFrameworkDisplayNames() {
/* 68 */     MvcFrameworkEnum[] values = values();
/* 69 */     String[] mvcNames = new String[values.length];
/* 70 */     int i = 0;
/* 71 */     MvcFrameworkEnum[] arrayOfMvcFrameworkEnum1 = values; int j = values.length; for (int i = 0; i < j; i++) { MvcFrameworkEnum mvcFrameworkEnum = arrayOfMvcFrameworkEnum1[i];
/* 72 */       mvcNames[i] = mvcFrameworkEnum.getDisplayName();
/* 73 */       i++;
/*    */     }
/* 75 */     return mvcNames;
/*    */   }
/*    */ 
/*    */   public static MvcFrameworkEnum getDefault() {
/* 79 */     return SPRINGMVC;
/*    */   }
/*    */ }

/* Location:           D:\MyEclipse2015CI\dropins\com.tianshouzhi.autopro_1.0.0.201506242235.jar
 * Qualified Name:     com.tianshouzhi.autopro.domain.enums.MvcFrameworkEnum
 * JD-Core Version:    0.6.2
 */