/*    */ package com.tianshouzhi.autopro.domain.enums;
/*    */ 
/*    */ public enum OrmFrameworkEnum
/*    */ {
/*  5 */   MYBATIS("3.2.4", 
/*  6 */     new String[] { "3.2.4", "3.1.1", "3.0.6" }, 
/*  7 */     "Dao_Mybatis_sqlMapConfig.xml.ftl", 
/*  8 */     "Dao_Mybatis_sqlMapper.xml.ftl", 
/*  9 */     "Dao_Mybatis_BaseDaoImpl.java.ftl", 
/* 10 */     "Mybatis"), 
/*    */ 
/*  6 */   HIBERNATE(
/* 12 */     "3.6.10.Final", 
/* 13 */     new String[] { "4.3.6.Final", "4.2.18.Final", "4.1.12.Final", "4.0.1.Final", "3.6.10.Final", "3.5.6-Final", "3.3.2.GA" }, 
/* 14 */     "Dao_Hibernate_Hibernate.config.xml.ftl", 
/* 15 */     "Dao_Hibernate_EntityName.hbm.xml.ftl", 
/* 16 */     "Dao_Hibernate_BaseDaoImpl.java.ftl", 
/* 17 */     "Hibernate"), 
/*    */ 
/* 13 */   IBATIS(
/* 19 */     "2.3.5", 
/* 20 */     new String[] { "2.3.5" }, 
/* 21 */     "Dao_Ibatis_sqlMapConfig.xml.ftl", 
/* 22 */     "Dao_Ibatis_sqlMapper.xml.ftl", 
/* 23 */     "Dao_Ibatis_BaseDaoImpl.java.ftl", 
/* 24 */     "Ibatis");
/*    */ 
/*    */   private String defaultVersion;
/*    */   private String[] supportVersions;
/*    */   private String coreConfigTempName;
/*    */   private String mappingConfigTempName;
/*    */   private String baseDaoImplTempName;
/*    */   private String displayName;
/*    */ 
/* 28 */   private OrmFrameworkEnum(String defaultVersion, String[] supportVersions, String coreConfigTempName, String mappingConfigTempName, String baseDaoImplTempName, String displayName) { this.defaultVersion = defaultVersion;
/* 29 */     this.supportVersions = supportVersions;
/* 30 */     this.coreConfigTempName = coreConfigTempName;
/* 31 */     this.mappingConfigTempName = mappingConfigTempName;
/* 32 */     this.baseDaoImplTempName = baseDaoImplTempName;
/* 33 */     this.displayName = displayName;
/*    */   }
/*    */ 
/*    */   public String getDefaultVersion()
/*    */   {
/* 44 */     return this.defaultVersion;
/*    */   }
/*    */   public String[] getSupportVersions() {
/* 47 */     return this.supportVersions;
/*    */   }
/*    */   public String getCoreConfigTempName() {
/* 50 */     return this.coreConfigTempName;
/*    */   }
/*    */   public String getMappingConfigTempName() {
/* 53 */     return this.mappingConfigTempName;
/*    */   }
/*    */   public String getBaseDaoImplTempName() {
/* 56 */     return this.baseDaoImplTempName;
/*    */   }
/*    */   public String getDisplayName() {
/* 59 */     return this.displayName;
/*    */   }
/*    */ 
/*    */   public static OrmFrameworkEnum getDefault() {
/* 63 */     return MYBATIS;
/*    */   }
/*    */   public static String[] getOrmDisplayNames() {
/* 66 */     OrmFrameworkEnum[] values = values();
/* 67 */     String[] ormNames = new String[values.length];
/* 68 */     int i = 0;
/* 69 */     OrmFrameworkEnum[] arrayOfOrmFrameworkEnum1 = values; int j = values.length; for (int i = 0; i < j; i++) { OrmFrameworkEnum dataBaseEnum = arrayOfOrmFrameworkEnum1[i];
/* 70 */       ormNames[i] = dataBaseEnum.getDisplayName();
/* 71 */       i++;
/*    */     }
/* 73 */     return ormNames;
/*    */   }
/*    */ 
/*    */   public static OrmFrameworkEnum getDaoFrameworkEnumByName(String name)
/*    */   {
/* 80 */     for (OrmFrameworkEnum daoFrameworkEnum : values()) {
/* 81 */       if (daoFrameworkEnum.name().equalsIgnoreCase(name)) {
/* 82 */         return daoFrameworkEnum;
/*    */       }
/*    */     }
/* 85 */     return null;
/*    */   }
/*    */ }

/* Location:           D:\MyEclipse2015CI\dropins\com.tianshouzhi.autopro_1.0.0.201506242235.jar
 * Qualified Name:     com.tianshouzhi.autopro.domain.enums.OrmFrameworkEnum
 * JD-Core Version:    0.6.2
 */