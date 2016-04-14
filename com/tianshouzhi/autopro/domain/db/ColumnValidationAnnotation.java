/*    */ package com.tianshouzhi.autopro.domain.db;
/*    */ 
/*    */ import java.util.List;
/*    */ 
/*    */ public enum ColumnValidationAnnotation
/*    */ {
/*  8 */   NotNull("@NotNull", null, "验证注解的元素值不是null"), 
/*  9 */   Min("@Min", "验证注解的元素值大于等于@Min指定的value值"), 
/* 10 */   Max("@Max", "验证注解的元素值小于等于@Min指定的value值");
/*    */ 
/*    */   private String AnnotationName;
/*    */   private List<String> conditions;
/*    */   private String annotationDescription;
/*    */ 
/* 16 */   public String getAnnotationName() { return this.AnnotationName; }
/*    */ 
/*    */   public void setAnnotationName(String annotationName) {
/* 19 */     this.AnnotationName = annotationName;
/*    */   }
/*    */   public List<String> getConditions() {
/* 22 */     return this.conditions;
/*    */   }
/*    */   public void setConditions(List<String> conditions) {
/* 25 */     this.conditions = conditions;
/*    */   }
/*    */   private ColumnValidationAnnotation(String annotationName, List<String> conditions) {
/* 28 */     this.AnnotationName = annotationName;
/* 29 */     this.conditions = conditions;
/*    */   }
/*    */   public String getAnnotationDescription() {
/* 32 */     return this.annotationDescription;
/*    */   }
/*    */ 
/*    */   private ColumnValidationAnnotation(String annotationName, String annotationDescription)
/*    */   {
/* 37 */     this.AnnotationName = annotationName;
/* 38 */     this.annotationDescription = annotationDescription;
/*    */   }
/*    */   private ColumnValidationAnnotation(String annotationName) {
/* 41 */     this.AnnotationName = annotationName;
/*    */   }
/*    */ 
/*    */   private ColumnValidationAnnotation(String annotationName, List<String> conditions, String annotationDescription) {
/* 45 */     this.AnnotationName = annotationName;
/* 46 */     this.conditions = conditions;
/* 47 */     this.annotationDescription = annotationDescription;
/*    */   }
/*    */ }

/* Location:           D:\MyEclipse2015CI\dropins\com.tianshouzhi.autopro_1.0.0.201506242235.jar
 * Qualified Name:     com.tianshouzhi.autopro.domain.db.ColumnValidationAnnotation
 * JD-Core Version:    0.6.2
 */