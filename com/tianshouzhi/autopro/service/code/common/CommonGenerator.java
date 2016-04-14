/*    */ package com.tianshouzhi.autopro.service.code.common;
/*    */ 
/*    */ import com.tianshouzhi.autopro.service.code.common.impl.EntityAndSearchVoGenerator;
/*    */ import com.tianshouzhi.autopro.service.code.common.impl.LoggerGenerator;
/*    */ import com.tianshouzhi.autopro.service.code.common.impl.PomGenerator;
/*    */ import com.tianshouzhi.autopro.service.code.common.impl.UtilGenerator;
/*    */ 
/*    */ public class CommonGenerator
/*    */ {
/*    */   public void generate()
/*    */   {
/* 11 */     UtilGenerator.generateUtil();
/* 12 */     PomGenerator.generatePOM();
/* 13 */     EntityAndSearchVoGenerator.generateEntityAndSearchVo();
/* 14 */     LoggerGenerator.generateLog4j();
/*    */   }
/*    */ }

/* Location:           D:\MyEclipse2015CI\dropins\com.tianshouzhi.autopro_1.0.0.201506242235.jar
 * Qualified Name:     com.tianshouzhi.autopro.service.code.common.CommonGenerator
 * JD-Core Version:    0.6.2
 */