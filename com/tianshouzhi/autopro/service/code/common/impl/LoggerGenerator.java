/*    */ package com.tianshouzhi.autopro.service.code.common.impl;
/*    */ 
/*    */ import com.tianshouzhi.autopro.util.Constant.TemplateNames;
/*    */ import com.tianshouzhi.autopro.util.FreemarkerUtil;
/*    */ 
/*    */ public class LoggerGenerator
/*    */ {
/*    */   public static void generateLog4j()
/*    */   {
/*  9 */     String templateName = Constant.TemplateNames.Log_log4j;
/* 10 */     String fileName = "log4j.properties";
/* 11 */     FreemarkerUtil.generateMavenConfigFile(templateName, null, null, fileName);
/*    */   }
/*    */ }

/* Location:           D:\MyEclipse2015CI\dropins\com.tianshouzhi.autopro_1.0.0.201506242235.jar
 * Qualified Name:     com.tianshouzhi.autopro.service.code.common.impl.LoggerGenerator
 * JD-Core Version:    0.6.2
 */