/*    */ package com.tianshouzhi.autopro.service.code.business.impl;
/*    */ 
/*    */ import com.tianshouzhi.autopro.domain.enums.OrmFrameworkEnum;
/*    */ import com.tianshouzhi.autopro.domain.enums.ServiceFrameworkEnum;
/*    */ import com.tianshouzhi.autopro.domain.functions.BaseTableFunction;
/*    */ import com.tianshouzhi.autopro.domain.maven.MavenDependency;
/*    */ import com.tianshouzhi.autopro.util.FreemarkerUtil;
/*    */ import com.tianshouzhi.autopro.util.MessageHub;
/*    */ import com.tianshouzhi.autopro.util.StringUtil;
/*    */ import java.util.ArrayList;
/*    */ import java.util.HashMap;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ import org.apache.log4j.Logger;
/*    */ 
/*    */ public class SpringCodeGenerator
/*    */ {
/* 23 */   private static final Logger logger = Logger.getLogger(SpringCodeGenerator.class);
/*    */ 
/*    */   public void generateApplicationContextXml()
/*    */   {
/* 28 */     String templateName = ServiceFrameworkEnum.SPRING.getCoreCondigTempName();
/* 29 */     Map data = new HashMap();
/* 30 */     List importConfigNames = new ArrayList();
/* 31 */     List tableFunctions = (List)MessageHub.get("L_SELECTED_FUNCTIONS");
/* 32 */     if (tableFunctions != null) {
/* 33 */       for (BaseTableFunction tableFunction : tableFunctions) {
/* 34 */         if (!StringUtil.isEmpty(tableFunction.getImportConfigName())) {
/* 35 */           importConfigNames.add(tableFunction.getImportConfigName());
/*    */         }
/*    */       }
/*    */     }
/* 39 */     String ormName = "";
/* 40 */     List mavenDependencies = (List)MessageHub.get("List_MavenDependency");
/* 41 */     for (MavenDependency mavenDependency : mavenDependencies) {
/* 42 */       if (mavenDependency.getArtifactId().equals("mybatis")) {
/* 43 */         ormName = OrmFrameworkEnum.MYBATIS.name();
/* 44 */         break;
/*    */       }
/* 46 */       if (mavenDependency.getArtifactId().equals("hibernate")) {
/* 47 */         ormName = OrmFrameworkEnum.HIBERNATE.name();
/* 48 */         break;
/*    */       }
/* 50 */       if (mavenDependency.getArtifactId().equals("ibatis")) {
/* 51 */         ormName = OrmFrameworkEnum.IBATIS.name();
/* 52 */         break;
/*    */       }
/*    */     }
/*    */ 
/* 56 */     importConfigNames.add("spring-" + ormName.toLowerCase() + ".xml");
/* 57 */     data.put("importConfigNames", importConfigNames);
/* 58 */     String fileName = "applicationContext.xml";
/* 59 */     FreemarkerUtil.generateMavenConfigFile(templateName, data, null, fileName);
/* 60 */     logger.info("生成applicationContext.xml");
/*    */   }
/*    */ }

/* Location:           D:\MyEclipse2015CI\dropins\com.tianshouzhi.autopro_1.0.0.201506242235.jar
 * Qualified Name:     com.tianshouzhi.autopro.service.code.business.impl.SpringCodeGenerator
 * JD-Core Version:    0.6.2
 */