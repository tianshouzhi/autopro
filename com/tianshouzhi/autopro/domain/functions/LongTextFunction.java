/*    */ package com.tianshouzhi.autopro.domain.functions;
/*    */ 
/*    */ public class LongTextFunction extends BaseTableFunction
/*    */ {
/*    */   private String longTextColumnName;
/*    */   private Boolean useFullText;
/*    */ 
/*    */   public LongTextFunction()
/*    */   {
/* 13 */     super("LongText_Function");
/*    */   }
/*    */ 
/*    */   public String getLongTextColumnName()
/*    */   {
/* 24 */     return this.longTextColumnName;
/*    */   }
/*    */   public void setLongTextColumnName(String longTextColumnName) {
/* 27 */     this.longTextColumnName = longTextColumnName;
/*    */   }
/*    */   public Boolean getUseFullText() {
/* 30 */     return this.useFullText;
/*    */   }
/*    */   public void setUseFullText(Boolean useFullText) {
/* 33 */     this.useFullText = useFullText;
/*    */   }
/*    */ 
/*    */   public int hashCode()
/*    */   {
/* 38 */     int result = super.hashCode();
/* 39 */     result = 31 * 
/* 40 */       result + (
/* 41 */       this.longTextColumnName == null ? 0 : this.longTextColumnName
/* 42 */       .hashCode());
/* 43 */     return result;
/*    */   }
/*    */ 
/*    */   public boolean equals(Object obj) {
/* 47 */     if (this == obj)
/* 48 */       return true;
/* 49 */     if (!super.equals(obj))
/* 50 */       return false;
/* 51 */     if (getClass() != obj.getClass())
/* 52 */       return false;
/* 53 */     LongTextFunction other = (LongTextFunction)obj;
/* 54 */     if (this.longTextColumnName == null) {
/* 55 */       if (other.longTextColumnName != null)
/* 56 */         return false;
/* 57 */     } else if (!this.longTextColumnName.equals(other.longTextColumnName))
/* 58 */       return false;
/* 59 */     return true;
/*    */   }
/*    */ 
/*    */   public LongTextFunction(String longTextColumnName, Boolean useFullText) {
/* 63 */     this();
/* 64 */     this.longTextColumnName = longTextColumnName;
/* 65 */     this.useFullText = useFullText;
/*    */   }
/*    */ }

/* Location:           D:\MyEclipse2015CI\dropins\com.tianshouzhi.autopro_1.0.0.201506242235.jar
 * Qualified Name:     com.tianshouzhi.autopro.domain.functions.LongTextFunction
 * JD-Core Version:    0.6.2
 */