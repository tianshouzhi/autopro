/*     */ package com.tianshouzhi.autopro.service.code.dao;
/*     */ 
/*     */ import com.tianshouzhi.autopro.domain.db.DBConnectionConfig;
/*     */ import com.tianshouzhi.autopro.domain.db.DBTable;
/*     */ import com.tianshouzhi.autopro.domain.enums.OrmFrameworkEnum;
/*     */ import com.tianshouzhi.autopro.domain.maven.MavenDependency;
/*     */ import com.tianshouzhi.autopro.service.code.dao.impl.IbatisCodeGenerator;
/*     */ import com.tianshouzhi.autopro.service.code.dao.impl.MybatisCodeGenerator;
/*     */ import com.tianshouzhi.autopro.util.Constant.TemplateNames;
/*     */ import com.tianshouzhi.autopro.util.FreemarkerUtil;
/*     */ import com.tianshouzhi.autopro.util.MessageHub;
/*     */ import com.tianshouzhi.autopro.util.StringUtil;
/*     */ import freemarker.template.TemplateException;
/*     */ import java.io.IOException;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class DaoGenerator
/*     */ {
/*     */   public void generate()
/*     */     throws Exception
/*     */   {
/*  29 */     generateJdbcPropertiesFile();
/*  30 */     generateIBaseDao();
/*  31 */     generateIEntityDao();
/*  32 */     generateIEntityDaoImpl();
/*     */ 
/*  35 */     String ormType = "";
/*  36 */     List mavenDependencies = (List)MessageHub.get("List_MavenDependency");
/*  37 */     for (MavenDependency mavenDependency : mavenDependencies) {
/*  38 */       if (mavenDependency.getArtifactId().equals("mybatis")) {
/*  39 */         ormType = OrmFrameworkEnum.MYBATIS.name();
/*  40 */         break;
/*     */       }
/*  42 */       if (mavenDependency.getGroupId().equals("hibernate")) {
/*  43 */         ormType = OrmFrameworkEnum.HIBERNATE.name();
/*  44 */         break;
/*     */       }
/*  46 */       if (mavenDependency.getArtifactId().equals("ibatis")) {
/*  47 */         ormType = OrmFrameworkEnum.IBATIS.name();
/*  48 */         break;
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/*  53 */     if (ormType.equalsIgnoreCase(OrmFrameworkEnum.MYBATIS.name())) {
/*  54 */       new MybatisCodeGenerator().genertate();
/*  55 */       return;
/*     */     }
/*     */ 
/*  58 */     if (ormType.equalsIgnoreCase(OrmFrameworkEnum.HIBERNATE.name()))
/*     */     {
/*  60 */       return;
/*     */     }
/*  62 */     if (ormType.equalsIgnoreCase(OrmFrameworkEnum.IBATIS.name())) {
/*  63 */       new IbatisCodeGenerator().genertate();
/*  64 */       return;
/*     */     }
/*     */   }
/*     */ 
/*     */   private void generateIEntityDao()
/*     */     throws IOException, Exception
/*     */   {
/*  75 */     Map data = new HashMap();
/*     */ 
/*  78 */     List dbTables = (List)MessageHub.get("SELECTED_TABLES");
/*     */ 
/*  80 */     String templateName = Constant.TemplateNames.Dao_IEntityNameDao;
/*  81 */     for (DBTable dbTable : dbTables)
/*  82 */       if (dbTable.isGenerateCode()) {
/*  83 */         data.put("dbTable", dbTable);
/*  84 */         String entityPakcage = "dao";
/*  85 */         String fileName = "/I" + StringUtil.initCap(dbTable.getEntityName()) + "Dao.java";
/*  86 */         FreemarkerUtil.generateMavenJavaFile(templateName, data, entityPakcage, fileName);
/*     */       }
/*     */   }
/*     */ 
/*     */   private void generateIEntityDaoImpl()
/*     */     throws IOException, Exception
/*     */   {
/*  97 */     Map data = new HashMap();
/*     */ 
/* 100 */     List dbTables = (List)MessageHub.get("SELECTED_TABLES");
/* 101 */     String templateName = Constant.TemplateNames.Dao_EntityNameDaoImpl;
/* 102 */     for (DBTable dbTable : dbTables)
/* 103 */       if (dbTable.isGenerateCode()) {
/* 104 */         data.put("dbTable", dbTable);
/* 105 */         String entityPakcage = "dao.impl";
/* 106 */         String fileName = StringUtil.initCap(dbTable.getEntityName()) + "DaoImpl.java";
/* 107 */         FreemarkerUtil.generateMavenJavaFile(templateName, data, entityPakcage, fileName);
/*     */       }
/*     */   }
/*     */ 
/*     */   private void generateJdbcPropertiesFile()
/*     */     throws Exception
/*     */   {
/* 117 */     Map data = new HashMap();
/*     */ 
/* 119 */     String templateName = Constant.TemplateNames.Dao_jdbc_properties;
/* 120 */     DBConnectionConfig dbConfig = (DBConnectionConfig)MessageHub.get("DB_CONNECTION_CONFIG");
/*     */ 
/* 122 */     data.put("dbConfig", dbConfig);
/* 123 */     String fileName = "jdbc.properties";
/* 124 */     FreemarkerUtil.generateMavenConfigFile(templateName, data, null, fileName);
/*     */   }
/*     */ 
/*     */   private void generateIBaseDao()
/*     */     throws IOException, TemplateException
/*     */   {
/* 130 */     Map data = new HashMap();
/*     */ 
/* 132 */     String templateName = Constant.TemplateNames.Base_IBaseDao;
/* 133 */     String entityPakcage = "base";
/* 134 */     String fileName = "IBaseDao.java";
/* 135 */     FreemarkerUtil.generateMavenJavaFile(templateName, data, entityPakcage, fileName);
/*     */   }
/*     */ }

/* Location:           D:\MyEclipse2015CI\dropins\com.tianshouzhi.autopro_1.0.0.201506242235.jar
 * Qualified Name:     com.tianshouzhi.autopro.service.code.dao.DaoGenerator
 * JD-Core Version:    0.6.2
 */