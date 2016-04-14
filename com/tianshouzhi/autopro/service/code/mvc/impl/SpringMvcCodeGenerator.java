/*    */ package com.tianshouzhi.autopro.service.code.mvc.impl;
/*    */ 
/*    */ import com.tianshouzhi.autopro.domain.db.DBTable;
/*    */ import com.tianshouzhi.autopro.domain.enums.MvcFrameworkEnum;
/*    */ import com.tianshouzhi.autopro.domain.functions.ExcelFunction;
/*    */ import com.tianshouzhi.autopro.util.Constant.TemplateNames;
/*    */ import com.tianshouzhi.autopro.util.FreemarkerUtil;
/*    */ import com.tianshouzhi.autopro.util.MessageHub;
/*    */ import com.tianshouzhi.autopro.util.StringUtil;
/*    */ import java.util.HashMap;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ 
/*    */ public class SpringMvcCodeGenerator
/*    */ {
/*    */   public void generate()
/*    */     throws Exception
/*    */   {
/* 18 */     generateBaseController();
/* 19 */     generateEntityController();
/* 20 */     generateSpringMvcXml();
/* 21 */     generateMethodValidateInteceptor();
/*    */   }
/*    */ 
/*    */   public void generateEntityController() throws Exception
/*    */   {
/* 26 */     Map data = new HashMap();
/* 27 */     List dbTables = (List)MessageHub.get("SELECTED_TABLES");
/*    */ 
/* 29 */     String templateName = MvcFrameworkEnum.SPRINGMVC.getEntityNameControllerTempName();
/* 30 */     for (DBTable dbTable : dbTables)
/* 31 */       if (dbTable.isGenerateCode()) {
/* 32 */         data.put("dbTable", dbTable);
/* 33 */         if (dbTable.getTableFunctions().contains(new ExcelFunction()))
/* 34 */           data.put("exportExcel", Boolean.valueOf(true));
/*    */         else {
/* 36 */           data.put("exportExcel", Boolean.valueOf(false));
/*    */         }
/* 38 */         String entityPakcage = "web.controller";
/* 39 */         String fileName = StringUtil.initCap(dbTable.getEntityName()) + "Controller.java";
/* 40 */         FreemarkerUtil.generateMavenJavaFile(templateName, data, entityPakcage, fileName);
/*    */       }
/*    */   }
/*    */ 
/*    */   public void generateSpringMvcXml()
/*    */     throws Exception
/*    */   {
/* 47 */     Map data = new HashMap();
/* 48 */     String templateName = MvcFrameworkEnum.SPRINGMVC.getCoreConfigTempName();
/* 49 */     String fileName = "spring-mvc.xml";
/* 50 */     FreemarkerUtil.generateMavenConfigFile(templateName, data, null, fileName);
/*    */   }
/*    */ 
/*    */   private void generateBaseController() throws Exception
/*    */   {
/* 55 */     Map data = new HashMap();
/*    */ 
/* 57 */     String templateName = Constant.TemplateNames.Base_BaseController;
/*    */ 
/* 59 */     String entityPakcage = "base";
/* 60 */     String fileName = "BaseController.java";
/* 61 */     FreemarkerUtil.generateMavenJavaFile(templateName, data, entityPakcage, fileName);
/*    */   }
/*    */ 
/*    */   private static void generateMethodValidateInteceptor()
/*    */   {
/* 66 */     String entityPakcage = "web.inteceptor";
/* 67 */     String templateName = "MethodValidateInteceptor.java.ftl";
/* 68 */     String fileName = "MethodValidateInteceptor.java";
/* 69 */     FreemarkerUtil.generateMavenJavaFile(templateName, null, entityPakcage, fileName);
/*    */   }
/*    */ }

/* Location:           D:\MyEclipse2015CI\dropins\com.tianshouzhi.autopro_1.0.0.201506242235.jar
 * Qualified Name:     com.tianshouzhi.autopro.service.code.mvc.impl.SpringMvcCodeGenerator
 * JD-Core Version:    0.6.2
 */