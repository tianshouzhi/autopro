/*    */ package com.tianshouzhi.autopro.util;
/*    */ 
/*    */ import freemarker.template.TemplateMethodModel;
/*    */ import freemarker.template.TemplateModelException;
/*    */ import java.util.List;
/*    */ import org.apache.log4j.Logger;
/*    */ 
/*    */ public class CustomFKMethodUseHtmlEditor
/*    */   implements TemplateMethodModel
/*    */ {
/* 15 */   private static final Logger logger = Logger.getLogger(CustomFKMethodUseHtmlEditor.class);
/*    */ 
/*    */   public Object exec(List args)
/*    */     throws TemplateModelException
/*    */   {
/* 20 */     if ((args == null) || (args.size() == 2)) {
/* 21 */       String tableName = (String)args.get(0);
/* 22 */       String longtextColumnName = (String)args.get(1);
/* 23 */       boolean useHtmlEditor = DBTablesUtil.useHtmlEditor(tableName, longtextColumnName);
/* 24 */       logger.info("HtmlEditor使用日志:tableName:" + tableName + "--columnName:" + longtextColumnName + "--是否使用:" + useHtmlEditor);
/* 25 */       return Boolean.valueOf(useHtmlEditor);
/*    */     }
/* 27 */     if ((args == null) || (args.size() == 1)) {
/* 28 */       String tableName = (String)args.get(0);
/* 29 */       return DBTablesUtil.useHtmlEditor(tableName);
/*    */     }
/* 31 */     throw new TemplateModelException("Wrong arguments");
/*    */   }
/*    */ }

/* Location:           D:\MyEclipse2015CI\dropins\com.tianshouzhi.autopro_1.0.0.201506242235.jar
 * Qualified Name:     com.tianshouzhi.autopro.util.CustomFKMethodUseHtmlEditor
 * JD-Core Version:    0.6.2
 */