/*    */ package com.tianshouzhi.autopro.service.code.mvc;
/*    */ 
/*    */ import com.tianshouzhi.autopro.domain.enums.MvcFrameworkEnum;
/*    */ import com.tianshouzhi.autopro.domain.maven.MavenDependency;
/*    */ import com.tianshouzhi.autopro.service.code.mvc.impl.SpringMvcCodeGenerator;
/*    */ import com.tianshouzhi.autopro.util.Constant.TemplateNames;
/*    */ import com.tianshouzhi.autopro.util.FreemarkerUtil;
/*    */ import com.tianshouzhi.autopro.util.MessageHub;
/*    */ import java.util.HashMap;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ 
/*    */ public class WebGenerator
/*    */ {
/*    */   public void generate()
/*    */     throws Exception
/*    */   {
/* 17 */     generateWebXML();
/* 18 */     String mvcFrameworkName = "";
/* 19 */     List mavenDependencies = (List)MessageHub.get("List_MavenDependency");
/* 20 */     for (MavenDependency mavenDependency : mavenDependencies) {
/* 21 */       if (mavenDependency.getArtifactId().contains("spring-webmvc")) {
/* 22 */         mvcFrameworkName = MvcFrameworkEnum.SPRINGMVC.name();
/* 23 */         break;
/*    */       }
/* 25 */       if (mavenDependency.getGroupId().contains("struts")) {
/* 26 */         mvcFrameworkName = MvcFrameworkEnum.STRUTS2.name();
/* 27 */         break;
/*    */       }
/*    */     }
/*    */ 
/* 31 */     if (mvcFrameworkName.equalsIgnoreCase(MvcFrameworkEnum.SPRINGMVC.name())) {
/* 32 */       new SpringMvcCodeGenerator().generate();
/* 33 */       return;
/*    */     }
/* 35 */     if (mvcFrameworkName.equalsIgnoreCase(MvcFrameworkEnum.STRUTS2.name()));
/*    */   }
/*    */ 
/*    */   private void generateWebXML()
/*    */     throws Exception
/*    */   {
/* 43 */     Map data = new HashMap();
/*    */ 
/* 46 */     String templateName = Constant.TemplateNames.Web_web_xml;
/* 47 */     String fileName = "web.xml";
/* 48 */     String subFSPath = "WEB-INF";
/* 49 */     FreemarkerUtil.generateMavenWebFile(templateName, data, subFSPath, fileName);
/*    */   }
/*    */ }

/* Location:           D:\MyEclipse2015CI\dropins\com.tianshouzhi.autopro_1.0.0.201506242235.jar
 * Qualified Name:     com.tianshouzhi.autopro.service.code.mvc.WebGenerator
 * JD-Core Version:    0.6.2
 */