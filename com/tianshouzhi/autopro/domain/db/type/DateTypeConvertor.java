/*    */ package com.tianshouzhi.autopro.domain.db.type;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ 
/*    */ public class DateTypeConvertor
/*    */ {
/*  7 */   public static List<String> dateTrans = new ArrayList();
/*    */ 
/*  9 */   static { dateTrans.add("DATE");
/* 10 */     dateTrans.add("DATETIME");
/* 11 */     dateTrans.add("TIMESTAMP");
/* 12 */     dateTrans.add("TIME");
/*    */   }
/*    */ }

/* Location:           D:\MyEclipse2015CI\dropins\com.tianshouzhi.autopro_1.0.0.201506242235.jar
 * Qualified Name:     com.tianshouzhi.autopro.domain.db.type.DateTypeConvertor
 * JD-Core Version:    0.6.2
 */