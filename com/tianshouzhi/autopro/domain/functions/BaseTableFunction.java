/*    */ package com.tianshouzhi.autopro.domain.functions;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ 
/*    */ public abstract class BaseTableFunction
/*    */ {
/*    */   protected String functionName;
/*    */   protected String importConfigName;
/* 23 */   private List<String> relatedTables = new ArrayList();
/*    */ 
/*    */   public BaseTableFunction(String functionName) {
/* 26 */     this.functionName = functionName;
/*    */   }
/*    */ 
/*    */   public String getFunctionName() {
/* 30 */     return this.functionName;
/*    */   }
/*    */ 
/*    */   public void setFunctionName(String functionName) {
/* 34 */     this.functionName = functionName;
/*    */   }
/*    */ 
/*    */   public String getImportConfigName() {
/* 38 */     return this.importConfigName;
/*    */   }
/*    */ 
/*    */   public void setImportConfigName(String importConfigName) {
/* 42 */     this.importConfigName = importConfigName;
/*    */   }
/*    */ 
/*    */   public int hashCode()
/*    */   {
/* 48 */     int result = 1;
/* 49 */     result = 31 * result + (
/* 50 */       this.functionName == null ? 0 : this.functionName.hashCode());
/* 51 */     return result;
/*    */   }
/*    */ 
/*    */   public boolean equals(Object obj)
/*    */   {
/* 56 */     if (this == obj)
/* 57 */       return true;
/* 58 */     if (obj == null)
/* 59 */       return false;
/* 60 */     if (getClass() != obj.getClass())
/* 61 */       return false;
/* 62 */     BaseTableFunction other = (BaseTableFunction)obj;
/* 63 */     if (this.functionName == null) {
/* 64 */       if (other.functionName != null)
/* 65 */         return false;
/* 66 */     } else if (!this.functionName.equals(other.functionName))
/* 67 */       return false;
/* 68 */     return true;
/*    */   }
/*    */ 
/*    */   public List<String> getRelatedTables() {
/* 72 */     return this.relatedTables;
/*    */   }
/*    */ 
/*    */   public void setRelatedTables(List<String> relatedTables) {
/* 76 */     this.relatedTables = relatedTables;
/*    */   }
/*    */ }

/* Location:           D:\MyEclipse2015CI\dropins\com.tianshouzhi.autopro_1.0.0.201506242235.jar
 * Qualified Name:     com.tianshouzhi.autopro.domain.functions.BaseTableFunction
 * JD-Core Version:    0.6.2
 */