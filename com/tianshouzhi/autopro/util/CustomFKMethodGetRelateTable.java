/*    */ package com.tianshouzhi.autopro.util;
/*    */ 
/*    */ import freemarker.template.TemplateMethodModel;
/*    */ import freemarker.template.TemplateModelException;
/*    */ import java.util.List;
/*    */ 
/*    */ public class CustomFKMethodGetRelateTable
/*    */   implements TemplateMethodModel
/*    */ {
/*    */   public Object exec(List args)
/*    */     throws TemplateModelException
/*    */   {
/* 13 */     if ((args == null) || (args.size() != 2)) {
/* 14 */       throw new TemplateModelException("Wrong arguments");
/*    */     }
/* 16 */     String tableName = (String)args.get(0);
/* 17 */     String foregnKeyName = (String)args.get(1);
/* 18 */     return DBTablesUtil.getRelateTable(tableName, foregnKeyName);
/*    */   }
/*    */ }

/* Location:           D:\MyEclipse2015CI\dropins\com.tianshouzhi.autopro_1.0.0.201506242235.jar
 * Qualified Name:     com.tianshouzhi.autopro.util.CustomFKMethodGetRelateTable
 * JD-Core Version:    0.6.2
 */