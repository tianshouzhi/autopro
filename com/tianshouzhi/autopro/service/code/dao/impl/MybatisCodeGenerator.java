/*     */ package com.tianshouzhi.autopro.service.code.dao.impl;
/*     */ 
/*     */ import com.tianshouzhi.autopro.domain.db.DBTable;
/*     */ import com.tianshouzhi.autopro.domain.db.TableColumn;
/*     */ import com.tianshouzhi.autopro.domain.enums.OrmFrameworkEnum;
/*     */ import com.tianshouzhi.autopro.domain.maven.MavenDependency;
/*     */ import com.tianshouzhi.autopro.util.CustomFKMethodGetRelateTable;
/*     */ import com.tianshouzhi.autopro.util.FreemarkerUtil;
/*     */ import com.tianshouzhi.autopro.util.MessageHub;
/*     */ import com.tianshouzhi.autopro.util.StringUtil;
/*     */ import freemarker.template.TemplateException;
/*     */ import java.io.IOException;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class MybatisCodeGenerator
/*     */ {
/*     */   public void genertate()
/*     */     throws Exception
/*     */   {
/*  25 */     String baseDaoImplTemplateName = null;
/*  26 */     String sqlMapConfigTemplateName = null;
/*  27 */     String sqlMapperTemplateName = null;
/*     */ 
/*  30 */     generateSpringMybatisConfig();
/*     */ 
/*  33 */     String version = "";
/*  34 */     List mavenDependencies = (List)MessageHub.get("List_MavenDependency");
/*  35 */     for (MavenDependency mavenDependency : mavenDependencies) {
/*  36 */       if (mavenDependency.getGroupId().contains("mybatis")) {
/*  37 */         version = mavenDependency.getVersion();
/*  38 */         break;
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/*  43 */     if (version.startsWith("3")) {
/*  44 */       baseDaoImplTemplateName = OrmFrameworkEnum.MYBATIS.getBaseDaoImplTempName();
/*  45 */       sqlMapConfigTemplateName = OrmFrameworkEnum.MYBATIS.getCoreConfigTempName();
/*  46 */       sqlMapperTemplateName = OrmFrameworkEnum.MYBATIS.getMappingConfigTempName();
/*     */     }
/*     */ 
/*  50 */     generateSqlMapConfig(sqlMapConfigTemplateName);
/*  51 */     generateSqlMappers(sqlMapperTemplateName);
/*  52 */     generateBaseDaoImpl(baseDaoImplTemplateName);
/*     */   }
/*     */ 
/*     */   private void generateSpringMybatisConfig()
/*     */   {
/*  59 */     String templateName = "spring_mybatis.xml.ftl";
/*  60 */     String fileName = "spring-mybatis.xml";
/*  61 */     FreemarkerUtil.generateMavenConfigFile(templateName, null, null, fileName);
/*     */   }
/*     */ 
/*     */   private void generateBaseDaoImpl(String templateName)
/*     */     throws IOException, Exception
/*     */   {
/*  70 */     Map data = new HashMap();
/*  71 */     String entityPakcage = "base";
/*  72 */     String fileName = "BaseDaoMybatisImpl.java";
/*  73 */     FreemarkerUtil.generateMavenJavaFile(templateName, data, entityPakcage, fileName);
/*     */   }
/*     */ 
/*     */   private void generateSqlMappers(String templateName)
/*     */     throws Exception
/*     */   {
/*  83 */     Map data = new HashMap();
/*     */ 
/*  85 */     List dbTables = (List)MessageHub.get("SELECTED_TABLES");
/*     */ 
/*  87 */     for (DBTable dbTable : dbTables) {
/*  88 */       List tableColumns = dbTable.getTableColumns();
/*     */ 
/*  90 */       StringBuffer sqlSelectArgsString = new StringBuffer();
/*  91 */       StringBuffer insertValuesString = new StringBuffer();
/*  92 */       StringBuffer updateValuesString = new StringBuffer();
/*  93 */       int i = 0;
/*  94 */       for (TableColumn tableColumn : tableColumns) {
/*  95 */         String columnName = tableColumn.getColumnName();
/*  96 */         String propertyName = tableColumn.getPropertyName();
/*  97 */         if (i == tableColumns.size() - 1) {
/*  98 */           sqlSelectArgsString.append(columnName);
/*  99 */           insertValuesString.append("#{" + propertyName + "}");
/* 100 */           updateValuesString.append(columnName + "=#{" + propertyName + "}");
/*     */         } else {
/* 102 */           sqlSelectArgsString.append(columnName + ",");
/* 103 */           insertValuesString.append("#{" + propertyName + "},");
/* 104 */           updateValuesString.append(columnName + "=#{" + propertyName + "},");
/*     */         }
/* 106 */         i++;
/*     */       }
/* 108 */       if (dbTable.isGenerateCode()) {
/* 109 */         data.put("dbTable", dbTable);
/* 110 */         data.put("sqlSelectArgsString", sqlSelectArgsString);
/* 111 */         data.put("insertValuesString", insertValuesString);
/* 112 */         data.put("updateValuesString", updateValuesString);
/* 113 */         data.put("getRelateTable", new CustomFKMethodGetRelateTable());
/* 114 */         String fileName = StringUtil.initCap(dbTable.getEntityName()) + 
/* 115 */           "Mapper.xml";
/* 116 */         String subFSPath = "mapper";
/* 117 */         FreemarkerUtil.generateMavenConfigFile(templateName, data, subFSPath, fileName);
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   private void generateSqlMapConfig(String templateName)
/*     */     throws IOException, TemplateException
/*     */   {
/* 132 */     Map data = new HashMap();
/*     */ 
/* 134 */     String fileName = "sqlMapConfig.xml";
/*     */ 
/* 137 */     List dbTables = (List)MessageHub.get("SELECTED_TABLES");
/* 138 */     data.put("dbTables", dbTables);
/*     */ 
/* 141 */     FreemarkerUtil.generateMavenConfigFile(templateName, data, null, fileName);
/*     */   }
/*     */ }

/* Location:           D:\MyEclipse2015CI\dropins\com.tianshouzhi.autopro_1.0.0.201506242235.jar
 * Qualified Name:     com.tianshouzhi.autopro.service.code.dao.impl.MybatisCodeGenerator
 * JD-Core Version:    0.6.2
 */