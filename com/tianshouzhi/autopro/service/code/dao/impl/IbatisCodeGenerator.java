/*    */ package com.tianshouzhi.autopro.service.code.dao.impl;
/*    */ 
/*    */ import com.tianshouzhi.autopro.domain.db.DBTable;
/*    */ import com.tianshouzhi.autopro.domain.db.TableColumn;
/*    */ import com.tianshouzhi.autopro.domain.enums.OrmFrameworkEnum;
/*    */ import com.tianshouzhi.autopro.util.FreemarkerUtil;
/*    */ import com.tianshouzhi.autopro.util.MessageHub;
/*    */ import com.tianshouzhi.autopro.util.StringUtil;
/*    */ import java.util.HashMap;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ 
/*    */ public class IbatisCodeGenerator
/*    */ {
/*    */   public void genertate()
/*    */   {
/* 19 */     String baseDaoImplTemplateName = null;
/* 20 */     String sqlMapConfigTemplateName = null;
/* 21 */     String sqlMapperTemplateName = null;
/*    */ 
/* 24 */     generateSpringIbatisConfig();
/* 25 */     baseDaoImplTemplateName = OrmFrameworkEnum.IBATIS.getBaseDaoImplTempName();
/* 26 */     sqlMapConfigTemplateName = OrmFrameworkEnum.IBATIS.getCoreConfigTempName();
/* 27 */     sqlMapperTemplateName = OrmFrameworkEnum.IBATIS.getMappingConfigTempName();
/*    */ 
/* 29 */     generateSqlMapConfig(sqlMapConfigTemplateName);
/* 30 */     generateSqlMappers(sqlMapperTemplateName);
/* 31 */     generateBaseDaoImpl(baseDaoImplTemplateName);
/*    */   }
/*    */ 
/*    */   private void generateSpringIbatisConfig() {
/* 35 */     String templateName = "spring_ibatis.xml.ftl";
/* 36 */     String fileName = "spring-ibatis.xml";
/* 37 */     FreemarkerUtil.generateMavenConfigFile(templateName, null, null, fileName);
/*    */   }
/*    */ 
/*    */   private void generateSqlMapConfig(String sqlMapConfigTemplateName)
/*    */   {
/* 42 */     Map data = new HashMap();
/*    */ 
/* 44 */     String fileName = "sqlMapConfig.xml";
/*    */ 
/* 47 */     List dbTables = (List)MessageHub.get("SELECTED_TABLES");
/* 48 */     data.put("dbTables", dbTables);
/*    */ 
/* 51 */     FreemarkerUtil.generateMavenConfigFile(sqlMapConfigTemplateName, data, null, fileName);
/*    */   }
/*    */ 
/*    */   private void generateSqlMappers(String sqlMapperTemplateName)
/*    */   {
/* 56 */     Map data = new HashMap();
/*    */ 
/* 58 */     List dbTables = (List)MessageHub.get("SELECTED_TABLES");
/*    */ 
/* 60 */     for (DBTable dbTable : dbTables) {
/* 61 */       List tableColumns = dbTable.getTableColumns();
/*    */ 
/* 63 */       StringBuffer sqlSelectArgsString = new StringBuffer();
/* 64 */       StringBuffer insertValuesString = new StringBuffer();
/* 65 */       StringBuffer updateValuesString = new StringBuffer();
/* 66 */       int i = 0;
/* 67 */       for (TableColumn tableColumn : tableColumns) {
/* 68 */         String columnName = tableColumn.getColumnName();
/* 69 */         String propertyName = tableColumn.getPropertyName();
/* 70 */         if (i == tableColumns.size() - 1) {
/* 71 */           sqlSelectArgsString.append(columnName);
/* 72 */           insertValuesString.append("#{" + propertyName + "}");
/* 73 */           updateValuesString.append(columnName + "=#{" + propertyName + "}");
/*    */         } else {
/* 75 */           sqlSelectArgsString.append(columnName + ",");
/* 76 */           insertValuesString.append("#{" + propertyName + "},");
/* 77 */           updateValuesString.append(columnName + "=#{" + propertyName + "},");
/*    */         }
/* 79 */         i++;
/*    */       }
/*    */ 
/* 82 */       if (dbTable.isGenerateCode()) {
/* 83 */         data.put("dbTable", dbTable);
/* 84 */         data.put("sqlSelectArgsString", sqlSelectArgsString);
/* 85 */         data.put("insertValuesString", insertValuesString);
/* 86 */         data.put("updateValuesString", updateValuesString);
/* 87 */         String fileName = StringUtil.initCap(dbTable.getEntityName()) + 
/* 88 */           "Mapper.xml";
/* 89 */         String subFSPath = "mapper";
/* 90 */         FreemarkerUtil.generateMavenConfigFile(sqlMapperTemplateName, data, subFSPath, fileName);
/*    */       }
/*    */     }
/*    */   }
/*    */ 
/*    */   private void generateBaseDaoImpl(String baseDaoImplTemplateName) {
/* 96 */     Map data = new HashMap();
/* 97 */     String entityPakcage = "base";
/* 98 */     String fileName = "BaseDaoIbatisImpl.java";
/* 99 */     FreemarkerUtil.generateMavenJavaFile(baseDaoImplTemplateName, data, entityPakcage, fileName);
/*    */   }
/*    */ }

/* Location:           D:\MyEclipse2015CI\dropins\com.tianshouzhi.autopro_1.0.0.201506242235.jar
 * Qualified Name:     com.tianshouzhi.autopro.service.code.dao.impl.IbatisCodeGenerator
 * JD-Core Version:    0.6.2
 */