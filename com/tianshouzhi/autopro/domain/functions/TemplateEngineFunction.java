/*    */ package com.tianshouzhi.autopro.domain.functions;
/*    */ 
/*    */ import com.tianshouzhi.autopro.domain.enums.TemplateEngineEnum;
/*    */ 
/*    */ public class TemplateEngineFunction extends BaseTableFunction
/*    */ {
/*    */   private String templateDir;
/*    */   private TemplateEngineEnum templateEngineEnum;
/*    */ 
/*    */   public TemplateEngineFunction()
/*    */   {
/* 13 */     super("Freemarker_Function");
/*    */   }
/*    */ 
/*    */   public TemplateEngineEnum getTemplateEngineEnum()
/*    */   {
/* 19 */     return this.templateEngineEnum;
/*    */   }
/*    */ 
/*    */   public void setTemplateEngineEnum(TemplateEngineEnum templateEngineEnum) {
/* 23 */     this.templateEngineEnum = templateEngineEnum;
/*    */   }
/*    */ 
/*    */   public String getTemplateDir()
/*    */   {
/* 29 */     return this.templateDir;
/*    */   }
/*    */ 
/*    */   public void setTemplateDir(String templateDir)
/*    */   {
/* 35 */     this.templateDir = templateDir;
/*    */   }
/*    */ }

/* Location:           D:\MyEclipse2015CI\dropins\com.tianshouzhi.autopro_1.0.0.201506242235.jar
 * Qualified Name:     com.tianshouzhi.autopro.domain.functions.TemplateEngineFunction
 * JD-Core Version:    0.6.2
 */