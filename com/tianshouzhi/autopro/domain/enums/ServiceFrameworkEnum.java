/*    */ package com.tianshouzhi.autopro.domain.enums;
/*    */ 
/*    */ public enum ServiceFrameworkEnum
/*    */ {
/*  4 */   SPRING("Spring", 
/*  5 */     "3.2.0.RELEASE", 
/*  6 */     new String[] { "4.1.0.RELEASE", "4.0.0.RELEASE", "3.2.0.RELEASE", "3.1.3.RELEASE", "3.0.3.RELEASE", "2.5.6" }, 
/*  7 */     "Service_applicationContextTemplate.xml.ftl", 
/*  8 */     "Service_BaseServiceImpl.java.ftl");
/*    */ 
/*    */   private String displayName;
/*    */   private String defaultVersion;
/*    */   private String[] supportedVersions;
/*    */   private String coreCondigTempName;
/*    */   private String baseServiceImplTempName;
/*    */ 
/* 20 */   private ServiceFrameworkEnum(String displayName, String defaultVersion, String[] supportedVersions, String coreCondigTempName, String baseServiceImplTempName) { this.displayName = displayName;
/* 21 */     this.defaultVersion = defaultVersion;
/* 22 */     this.supportedVersions = supportedVersions;
/* 23 */     this.coreCondigTempName = coreCondigTempName;
/* 24 */     this.baseServiceImplTempName = baseServiceImplTempName; }
/*    */ 
/*    */   public String getDisplayName()
/*    */   {
/* 28 */     return this.displayName;
/*    */   }
/*    */ 
/*    */   public String getDefaultVersion() {
/* 32 */     return this.defaultVersion;
/*    */   }
/*    */ 
/*    */   public String[] getSupportedVersions() {
/* 36 */     return this.supportedVersions;
/*    */   }
/*    */ 
/*    */   public String getCoreCondigTempName() {
/* 40 */     return this.coreCondigTempName;
/*    */   }
/*    */ 
/*    */   public String getBaseServiceImplTempName() {
/* 44 */     return this.baseServiceImplTempName;
/*    */   }
/*    */ 
/*    */   public static String[] getServiceFramworkNames() {
/* 48 */     return new String[] { SPRING.getDisplayName() };
/*    */   }
/*    */ 
/*    */   public static ServiceFrameworkEnum getDefault() {
/* 52 */     return SPRING;
/*    */   }
/*    */ 
/*    */   public static ServiceFrameworkEnum getServiceFrameworkEnumByName(String name)
/*    */   {
/* 59 */     for (ServiceFrameworkEnum serviceFrameworkEnum : values()) {
/* 60 */       if (serviceFrameworkEnum.name().equalsIgnoreCase(name)) {
/* 61 */         return serviceFrameworkEnum;
/*    */       }
/*    */     }
/* 64 */     return null;
/*    */   }
/*    */ }

/* Location:           D:\MyEclipse2015CI\dropins\com.tianshouzhi.autopro_1.0.0.201506242235.jar
 * Qualified Name:     com.tianshouzhi.autopro.domain.enums.ServiceFrameworkEnum
 * JD-Core Version:    0.6.2
 */