/*     */ package com.tianshouzhi.autopro.service.code.business;
/*     */ 
/*     */ import com.tianshouzhi.autopro.domain.db.DBTable;
/*     */ import com.tianshouzhi.autopro.service.code.business.impl.SpringCodeGenerator;
/*     */ import com.tianshouzhi.autopro.util.Constant.TemplateNames;
/*     */ import com.tianshouzhi.autopro.util.FreemarkerUtil;
/*     */ import com.tianshouzhi.autopro.util.MessageHub;
/*     */ import com.tianshouzhi.autopro.util.StringUtil;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class ServiceGenerator
/*     */ {
/*     */   private void generateIBaseService()
/*     */   {
/*  20 */     Map data = new HashMap();
/*  21 */     String templateName = Constant.TemplateNames.Base_IBaseService;
/*  22 */     String fileName = "IBaseService.java";
/*  23 */     String entityPakcage = "base";
/*  24 */     FreemarkerUtil.generateMavenJavaFile(templateName, data, entityPakcage, fileName);
/*     */   }
/*     */ 
/*     */   private void generateBaseServiceImpl() {
/*  28 */     Map data = new HashMap();
/*     */ 
/*  30 */     String templateName = Constant.TemplateNames.Service_BaseServiceImpl;
/*  31 */     String fileName = "BaseServiceImpl.java";
/*  32 */     String entityPakcage = "base";
/*  33 */     FreemarkerUtil.generateMavenJavaFile(templateName, data, entityPakcage, fileName);
/*     */   }
/*     */ 
/*     */   private void generateEntityServiceInteface()
/*     */   {
/*  38 */     Map data = new HashMap();
/*     */ 
/*  40 */     List dbTables = (List)MessageHub.get("SELECTED_TABLES");
/*  41 */     String templateName = Constant.TemplateNames.Service_IEntityNameService;
/*  42 */     for (DBTable dbTable : dbTables)
/*  43 */       if (dbTable.isGenerateCode()) {
/*  44 */         data.put("dbTable", dbTable);
/*  45 */         String entityPakcage = "service";
/*  46 */         String fileName = "/I" + StringUtil.initCap(dbTable.getEntityName()) + 
/*  47 */           "Service.java";
/*  48 */         FreemarkerUtil.generateMavenJavaFile(templateName, data, entityPakcage, fileName);
/*     */       }
/*     */   }
/*     */ 
/*     */   private void generateEntityServiceImpl()
/*     */   {
/*  55 */     List dbTables = (List)MessageHub.get("SELECTED_TABLES");
/*  56 */     Map data = new HashMap();
/*     */ 
/*  58 */     String templateName = Constant.TemplateNames.Service_EntityNameServiceImpl;
/*  59 */     for (DBTable dbTable : dbTables)
/*  60 */       if (dbTable.isGenerateCode()) {
/*  61 */         data.put("dbTable", dbTable);
/*  62 */         String fileName = StringUtil.initCap(dbTable.getEntityName()) + 
/*  63 */           "ServiceImpl.java";
/*  64 */         String entityPakcage = "service.impl";
/*  65 */         FreemarkerUtil.generateMavenJavaFile(templateName, data, entityPakcage, fileName);
/*     */       }
/*     */   }
/*     */ 
/*     */   private void generateTest()
/*     */   {
/*     */   }
/*     */ 
/*     */   public void generate()
/*     */   {
/*  97 */     generateIBaseService();
/*  98 */     generateBaseServiceImpl();
/*  99 */     generateEntityServiceInteface();
/* 100 */     generateEntityServiceImpl();
/* 101 */     generateTest();
/* 102 */     new SpringCodeGenerator().generateApplicationContextXml();
/*     */   }
/*     */ }

/* Location:           D:\MyEclipse2015CI\dropins\com.tianshouzhi.autopro_1.0.0.201506242235.jar
 * Qualified Name:     com.tianshouzhi.autopro.service.code.business.ServiceGenerator
 * JD-Core Version:    0.6.2
 */