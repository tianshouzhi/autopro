/*    */ package com.tianshouzhi.autopro.domain.db;
/*    */ 
/*    */ public class TableRelation
/*    */ {
/*    */   private String FKColumn_Name;
/*    */   private String PKColumn_Name;
/*    */   private String FKTable_Name;
/*    */   private String PKTable_Name;
/*    */ 
/*    */   public String getFKColumn_Name()
/*    */   {
/* 24 */     return this.FKColumn_Name;
/*    */   }
/*    */ 
/*    */   public TableRelation()
/*    */   {
/*    */   }
/*    */ 
/*    */   public TableRelation(String fKColumn_Name, String pKTable_Name, String fKTable_Name, String pKColumn_Name)
/*    */   {
/* 34 */     this.FKColumn_Name = fKColumn_Name;
/* 35 */     this.PKTable_Name = pKTable_Name;
/* 36 */     this.FKTable_Name = fKTable_Name;
/* 37 */     this.PKColumn_Name = pKColumn_Name;
/*    */   }
/*    */ 
/*    */   public void setFKColumn_Name(String fKColumn_Name) {
/* 41 */     this.FKColumn_Name = fKColumn_Name;
/*    */   }
/*    */ 
/*    */   public String getPKTable_Name() {
/* 45 */     return this.PKTable_Name;
/*    */   }
/*    */ 
/*    */   public void setPKTable_Name(String pKTable_Name) {
/* 49 */     this.PKTable_Name = pKTable_Name;
/*    */   }
/*    */ 
/*    */   public String getFKTable_Name() {
/* 53 */     return this.FKTable_Name;
/*    */   }
/*    */ 
/*    */   public void setFKTable_Name(String fKTable_Name) {
/* 57 */     this.FKTable_Name = fKTable_Name;
/*    */   }
/*    */ 
/*    */   public String getPKColumn_Name() {
/* 61 */     return this.PKColumn_Name;
/*    */   }
/*    */ 
/*    */   public void setPKColumn_Name(String pKColumn_Name) {
/* 65 */     this.PKColumn_Name = pKColumn_Name;
/*    */   }
/*    */ 
/*    */   public String toString()
/*    */   {
/* 70 */     return "TableRelation [FKColumn_Name=" + this.FKColumn_Name + 
/* 71 */       ", PKTable_Name=" + this.PKTable_Name + ", FKTable_Name=" + 
/* 72 */       this.FKTable_Name + ", PKColumn_Name=" + this.PKColumn_Name + "]";
/*    */   }
/*    */ }

/* Location:           D:\MyEclipse2015CI\dropins\com.tianshouzhi.autopro_1.0.0.201506242235.jar
 * Qualified Name:     com.tianshouzhi.autopro.domain.db.TableRelation
 * JD-Core Version:    0.6.2
 */