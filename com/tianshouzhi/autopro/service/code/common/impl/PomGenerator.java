/*    */ package com.tianshouzhi.autopro.service.code.common.impl;
/*    */ 
/*    */ import com.tianshouzhi.autopro.util.Constant.TemplateNames;
/*    */ import com.tianshouzhi.autopro.util.DBTablesUtil;
/*    */ import com.tianshouzhi.autopro.util.FreemarkerUtil;
/*    */ import com.tianshouzhi.autopro.util.MavenDependcyUtil;
/*    */ import com.tianshouzhi.autopro.util.MessageHub;
/*    */ import com.tianshouzhi.autopro.util.XmlUtil;
/*    */ import java.util.HashMap;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ 
/*    */ public class PomGenerator
/*    */ {
/*    */   public static void generatePOM()
/*    */   {
/* 26 */     String projectName = (String)MessageHub.get("PROJECT_NAME");
/*    */ 
/* 28 */     List mavenDependencies = (List)MessageHub.get("List_MavenDependency");
/* 29 */     if (DBTablesUtil.hasExcelFunction()) {
/* 30 */       List apachePoiDependencies = MavenDependcyUtil.getApachePoiDependencies("poi", null);
/* 31 */       mavenDependencies.addAll(apachePoiDependencies);
/*    */     }
/*    */ 
/* 34 */     if (DBTablesUtil.hasReportFunction())
/*    */     {
/* 36 */       List highChartsDenpendcy = MavenDependcyUtil.getHighChartsDenpendcy();
/* 37 */       mavenDependencies.addAll(highChartsDenpendcy);
/*    */     }
/*    */ 
/* 41 */     String savePath = (String)MessageHub.get("PROJECT_FS_PATH") + "/pom.xml";
/* 42 */     String templateName = Constant.TemplateNames.Maven_pom;
/*    */ 
/* 44 */     Map data = new HashMap();
/* 45 */     String[] split = projectName.split(" ");
/* 46 */     data.put("groupId", XmlUtil.getPomGroupId());
/* 47 */     data.put("artifactId", XmlUtil.getPomArtifactId());
/* 48 */     data.put("projectName", split[0]);
/* 49 */     data.put("mavenDependencies", mavenDependencies);
/*    */ 
/* 51 */     FreemarkerUtil.generateFile(templateName, data, savePath);
/*    */   }
/*    */ }

/* Location:           D:\MyEclipse2015CI\dropins\com.tianshouzhi.autopro_1.0.0.201506242235.jar
 * Qualified Name:     com.tianshouzhi.autopro.service.code.common.impl.PomGenerator
 * JD-Core Version:    0.6.2
 */