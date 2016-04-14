/*     */ package com.tianshouzhi.autopro.service.code.function.report;
/*     */ 
/*     */ import com.tianshouzhi.autopro.domain.db.DBTable;
/*     */ import com.tianshouzhi.autopro.util.CustomFKMethodGetRelateTable;
/*     */ import com.tianshouzhi.autopro.util.DBTablesUtil;
/*     */ import com.tianshouzhi.autopro.util.FreemarkerUtil;
/*     */ import com.tianshouzhi.autopro.util.StringUtil;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class ReportFunctionGenerator
/*     */ {
/*     */   public static void genrateReportFunction()
/*     */   {
/*  18 */     boolean hasReportFunction = DBTablesUtil.hasReportFunction();
/*  19 */     if (hasReportFunction)
/*     */     {
/*  23 */       generateReportCommon();
/*     */     }
/*  25 */     else return;
/*     */ 
/*  28 */     List dbTables = DBTablesUtil.getSelectedDBTables();
/*     */ 
/*  32 */     for (DBTable dbTable : dbTables) {
/*  33 */       List reportFunctions = DBTablesUtil.getReportFunctions(dbTable.getTableName());
/*  34 */       if ((reportFunctions != null) && (reportFunctions.size() > 0)) {
/*  35 */         String entityName = StringUtil.initCap(StringUtil.getCamelName(dbTable.getTableName()));
/*  36 */         Map data = new HashMap();
/*  37 */         data.put("dbTable", dbTable);
/*     */ 
/*  40 */         data.put("getRelateTable", new CustomFKMethodGetRelateTable());
/*  41 */         String templateName = "EntityNameReportMapper.java.ftl";
/*  42 */         String fileName = entityName + "ReportMapper.xml";
/*  43 */         FreemarkerUtil.generateMavenConfigFile(templateName, data, "mapper/report", fileName);
/*     */ 
/*  46 */         templateName = "EntityNameReportDao.java.ftl";
/*  47 */         fileName = "I" + entityName + "ReportDao.java";
/*  48 */         FreemarkerUtil.generateMavenJavaFile(templateName, data, "dao.report", fileName);
/*     */ 
/*  51 */         templateName = "EntityNameReportDaoImpl.java.ftl";
/*  52 */         fileName = entityName + "ReportDaoImpl.java";
/*  53 */         FreemarkerUtil.generateMavenJavaFile(templateName, data, "dao.report.impl", fileName);
/*     */ 
/*  56 */         templateName = "EntityNameReportService.java.ftl";
/*  57 */         fileName = "I" + entityName + "ReportService.java";
/*  58 */         FreemarkerUtil.generateMavenJavaFile(templateName, data, "service.report", fileName);
/*     */ 
/*  61 */         templateName = "EntityNameReportServiceImpl.java.ftl";
/*  62 */         fileName = entityName + "ReportServiceImpl.java";
/*  63 */         FreemarkerUtil.generateMavenJavaFile(templateName, data, "service.report.impl", fileName);
/*     */ 
/*  66 */         templateName = "EntityNameReportController.java.ftl";
/*  67 */         fileName = entityName + "ReportController.java";
/*  68 */         FreemarkerUtil.generateMavenJavaFile(templateName, data, "web.controller.report", fileName);
/*     */ 
/*  71 */         templateName = "report.jsp.ftl";
/*  72 */         fileName = "report.jsp";
/*  73 */         FreemarkerUtil.generateMavenWebFile(templateName, data, "WEB-INF/jsp/" + dbTable.getEntityName(), fileName);
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   private static void generateReportCommon()
/*     */   {
/*  80 */     String templateName = "BaseReportVo.java.ftl";
/*  81 */     FreemarkerUtil.generateMavenJavaFile(templateName, null, "domain.report", "BaseReportVo.java");
/*     */ 
/*  83 */     templateName = "TimeReportVo.java.ftl";
/*  84 */     FreemarkerUtil.generateMavenJavaFile(templateName, null, "domain.report", "TimeReportVo.java");
/*     */ 
/*  86 */     templateName = "IBaseReportDao.java.ftl";
/*  87 */     FreemarkerUtil.generateMavenJavaFile(templateName, null, "base.report", "IBaseReportDao.java");
/*     */ 
/*  89 */     templateName = "BaseReportDaoImpl.java.ftl";
/*  90 */     FreemarkerUtil.generateMavenJavaFile(templateName, null, "base.report", "BaseReportDaoMybatisImpl.java");
/*     */ 
/*  92 */     templateName = "IBaseReportService.java.ftl";
/*  93 */     FreemarkerUtil.generateMavenJavaFile(templateName, null, "base.report", "IBaseReportService.java");
/*     */ 
/*  95 */     templateName = "BaseReportServiceImpl.java.ftl";
/*  96 */     FreemarkerUtil.generateMavenJavaFile(templateName, null, "base.report", "BaseReportServiceImpl.java");
/*     */ 
/*  98 */     templateName = "Util_ChartUtil.java.ftl";
/*  99 */     FreemarkerUtil.generateMavenJavaFile(templateName, null, "util", "ChartUtil.java");
/*     */ 
/* 101 */     templateName = "ReportSearchVo.java.ftl";
/* 102 */     FreemarkerUtil.generateMavenJavaFile(templateName, null, "domain.report", "ReportSearchVo.java");
/*     */   }
/*     */ }

/* Location:           D:\MyEclipse2015CI\dropins\com.tianshouzhi.autopro_1.0.0.201506242235.jar
 * Qualified Name:     com.tianshouzhi.autopro.service.code.function.report.ReportFunctionGenerator
 * JD-Core Version:    0.6.2
 */