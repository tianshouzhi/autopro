/*    */ package com.tianshouzhi.autopro.service.code.common.impl;
/*    */ 
/*    */ import com.tianshouzhi.autopro.domain.db.DBTable;
/*    */ import com.tianshouzhi.autopro.domain.functions.BaseTableFunction;
/*    */ import com.tianshouzhi.autopro.util.Constant.TemplateNames;
/*    */ import com.tianshouzhi.autopro.util.FreemarkerUtil;
/*    */ import com.tianshouzhi.autopro.util.MessageHub;
/*    */ import java.util.HashMap;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ 
/*    */ public class UtilGenerator
/*    */ {
/*    */   public static void generateUtil()
/*    */   {
/* 26 */     Map data = new HashMap();
/*    */ 
/* 28 */     String entityPackage = "util";
/*    */ 
/* 30 */     String fileName = "PageBean.java";
/* 31 */     String templateName = Constant.TemplateNames.Util_PageBean;
/* 32 */     FreemarkerUtil.generateMavenJavaFile(templateName, data, entityPackage, fileName);
/*    */ 
/* 35 */     templateName = Constant.TemplateNames.Util_JsonUtil;
/* 36 */     fileName = "JsonUtil.java";
/* 37 */     FreemarkerUtil.generateMavenJavaFile(templateName, data, entityPackage, fileName);
/*    */ 
/* 40 */     templateName = Constant.TemplateNames.Util_StringUtil;
/* 41 */     fileName = "StringUtil.java";
/* 42 */     FreemarkerUtil.generateMavenJavaFile(templateName, data, entityPackage, fileName);
/* 43 */     List dbTables = (List)MessageHub.get("SELECTED_TABLES");
/* 44 */     for (DBTable dbTable : dbTables) {
/* 45 */       List tableFunctions = dbTable.getTableFunctions();
/* 46 */       for (BaseTableFunction baseTableFunction : tableFunctions) {
/* 47 */         String functionName = baseTableFunction.getFunctionName();
/* 48 */         if ("Excel_Function".equals(functionName))
/*    */         {
/* 50 */           templateName = Constant.TemplateNames.Util_ApachePOIExcelUtil;
/* 51 */           fileName = "ApachePOIExcelUtil.java";
/* 52 */           FreemarkerUtil.generateMavenJavaFile(templateName, data, entityPackage, fileName);
/* 53 */           break;
/*    */         }
/*    */ 
/*    */       }
/*    */ 
/*    */     }
/*    */ 
/* 60 */     templateName = "Util_ResponseEnum.java.ftl";
/* 61 */     fileName = "ResponseEnum.java";
/* 62 */     FreemarkerUtil.generateMavenJavaFile(templateName, data, entityPackage, fileName);
/*    */ 
/* 65 */     templateName = "Util_ResponseWrapper.java.ftl";
/* 66 */     fileName = "ResponseWrapper.java";
/* 67 */     FreemarkerUtil.generateMavenJavaFile(templateName, data, entityPackage, fileName);
/*    */ 
/* 71 */     templateName = "Util_ValidateResult.java.ftl";
/* 72 */     fileName = "ValidateResult.java";
/* 73 */     FreemarkerUtil.generateMavenJavaFile(templateName, data, entityPackage, fileName);
/*    */ 
/* 76 */     templateName = "Util_DateUtil.java.ftl";
/* 77 */     fileName = "DateUtil.java";
/* 78 */     FreemarkerUtil.generateMavenJavaFile(templateName, data, entityPackage, fileName);
/*    */   }
/*    */ }

/* Location:           D:\MyEclipse2015CI\dropins\com.tianshouzhi.autopro_1.0.0.201506242235.jar
 * Qualified Name:     com.tianshouzhi.autopro.service.code.common.impl.UtilGenerator
 * JD-Core Version:    0.6.2
 */