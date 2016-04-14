/*    */ package com.tianshouzhi.autopro.domain.enums;
/*    */ 
/*    */ public enum ChartType
/*    */ {
/*  4 */   COLUMN("column", "柱状图(不分类)"), 
/*  5 */   LINE("line", "折线图(不分类)"), 
/*  6 */   MULTI_COLUMN("multi_column", "多列柱状图(分类统计)"), 
/*  7 */   STACK_COLUMN("stack_column", "叠加柱状图(分类统计)"), 
/*  8 */   MULTI_LINE("mutil_line", "多条折线图(分类统计)"), 
/*  9 */   PIE("pie", "饼状图");
/*    */ 
/*    */   private String reportType;
/*    */   private String displayName;
/*    */   private String imgurl;
/*    */ 
/*    */   private ChartType(String reportType, String displayName)
/*    */   {
/* 25 */     this.reportType = reportType;
/* 26 */     this.displayName = displayName;
/* 27 */     this.imgurl = ("icons/reports/" + name().toLowerCase() + ".png");
/*    */   }
/*    */ 
/*    */   public String getReportType() {
/* 31 */     return this.reportType;
/*    */   }
/*    */ 
/*    */   public void setReportType(String reportType) {
/* 35 */     this.reportType = reportType;
/*    */   }
/*    */ 
/*    */   public String getDisplayName() {
/* 39 */     return this.displayName;
/*    */   }
/*    */ 
/*    */   public void setDisplayName(String displayName) {
/* 43 */     this.displayName = displayName;
/*    */   }
/*    */ 
/*    */   public String getImgurl() {
/* 47 */     return this.imgurl;
/*    */   }
/*    */ 
/*    */   public void setImgurl(String imgurl) {
/* 51 */     this.imgurl = imgurl;
/*    */   }
/*    */ 
/*    */   public static String[] getChartDisplayNames() {
/* 55 */     ChartType[] values = values();
/* 56 */     String[] names = new String[values.length];
/* 57 */     int i = 0;
/* 58 */     ChartType[] arrayOfChartType1 = values; int j = values.length; for (int i = 0; i < j; i++) { ChartType chartType = arrayOfChartType1[i];
/* 59 */       names[i] = chartType.getDisplayName();
/* 60 */       i++;
/*    */     }
/* 62 */     return names;
/*    */   }
/*    */ 
/*    */   public static ChartType getChartTypeByDisplayName(String chartDisplayName) {
/* 66 */     ChartType[] values = values();
/* 67 */     for (ChartType chartType : values) {
/* 68 */       if (chartType.displayName.equals(chartDisplayName)) {
/* 69 */         return chartType;
/*    */       }
/*    */     }
/* 72 */     return null;
/*    */   }
/*    */ }

/* Location:           D:\MyEclipse2015CI\dropins\com.tianshouzhi.autopro_1.0.0.201506242235.jar
 * Qualified Name:     com.tianshouzhi.autopro.domain.enums.ChartType
 * JD-Core Version:    0.6.2
 */