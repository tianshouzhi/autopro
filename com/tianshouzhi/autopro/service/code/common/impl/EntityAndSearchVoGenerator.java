/*    */ package com.tianshouzhi.autopro.service.code.common.impl;
/*    */ 
/*    */ import com.tianshouzhi.autopro.domain.db.DBTable;
/*    */ import com.tianshouzhi.autopro.domain.db.TableColumn;
/*    */ import com.tianshouzhi.autopro.util.Constant.TemplateNames;
/*    */ import com.tianshouzhi.autopro.util.FreemarkerUtil;
/*    */ import com.tianshouzhi.autopro.util.MessageHub;
/*    */ import com.tianshouzhi.autopro.util.StringUtil;
/*    */ import java.util.HashMap;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ 
/*    */ public class EntityAndSearchVoGenerator
/*    */ {
/*    */   public static void generateEntityAndSearchVo()
/*    */   {
/* 23 */     generateBaseSearchVo();
/* 24 */     List dbTables = (List)
/* 25 */       MessageHub.get("SELECTED_TABLES");
/*    */ 
/* 27 */     String entityTemplateName = Constant.TemplateNames.EntityName;
/* 28 */     String searchVoTemplateName = Constant.TemplateNames.EntityNameSeachVo;
/* 29 */     for (DBTable dbTable : dbTables)
/* 30 */       if (dbTable.isGenerateCode())
/*    */       {
/* 32 */         List tableColumns = dbTable.getTableColumns();
/*    */ 
/* 34 */         List pkColumnList = dbTable.getPKColumnList();
/* 35 */         StringBuffer pkArgs = new StringBuffer();
/* 36 */         for (TableColumn tableColumn : pkColumnList) {
/* 37 */           if (!tableColumn.getIsAutoIncrement().booleanValue()) {
/* 38 */             pkArgs.append(tableColumn.getJavaSimpleName() + " " + 
/* 39 */               tableColumn.getPropertyName() + ",");
/*    */           }
/*    */         }
/* 42 */         StringBuffer constructArgsString = new StringBuffer(pkArgs);
/* 43 */         int i = 0;
/* 44 */         for (TableColumn tableColumn : tableColumns) {
/* 45 */           if (i == tableColumns.size() - 1)
/* 46 */             constructArgsString.append(tableColumn
/* 47 */               .getJavaSimpleName() + 
/* 48 */               " " + 
/* 49 */               tableColumn.getPropertyName());
/*    */           else {
/* 51 */             constructArgsString.append(tableColumn
/* 52 */               .getJavaSimpleName() + 
/* 53 */               " " + 
/* 54 */               tableColumn.getPropertyName() + ",");
/*    */           }
/* 56 */           i++;
/*    */         }
/*    */ 
/* 60 */         Map data = new HashMap();
/* 61 */         data.put("dbTable", dbTable);
/* 62 */         data.put("constructArgsString", constructArgsString.toString());
/*    */ 
/* 64 */         String fileName = StringUtil.initCap(dbTable.getEntityName()) + 
/* 65 */           ".java";
/* 66 */         String entityPakcage = "domain";
/* 67 */         FreemarkerUtil.generateMavenJavaFile(entityTemplateName, data, entityPakcage, fileName);
/*    */ 
/* 69 */         fileName = StringUtil.initCap(dbTable.getEntityName()) + 
/* 70 */           "SearchVo.java";
/* 71 */         entityPakcage = "domain.searchvo";
/*    */ 
/* 73 */         FreemarkerUtil.generateMavenJavaFile(searchVoTemplateName, data, entityPakcage, fileName);
/*    */       }
/*    */   }
/*    */ 
/*    */   private static void generateBaseSearchVo()
/*    */   {
/* 79 */     Map data = new HashMap();
/*    */ 
/* 81 */     String templateName = Constant.TemplateNames.Base_BaseSearchVo;
/* 82 */     String fileName = "BaseSearchVo.java";
/* 83 */     String entityPackage = "base";
/* 84 */     FreemarkerUtil.generateMavenJavaFile(templateName, data, entityPackage, fileName);
/*    */   }
/*    */ }

/* Location:           D:\MyEclipse2015CI\dropins\com.tianshouzhi.autopro_1.0.0.201506242235.jar
 * Qualified Name:     com.tianshouzhi.autopro.service.code.common.impl.EntityAndSearchVoGenerator
 * JD-Core Version:    0.6.2
 */