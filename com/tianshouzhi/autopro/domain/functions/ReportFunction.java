/*     */ package com.tianshouzhi.autopro.domain.functions;
/*     */ 
/*     */ import com.tianshouzhi.autopro.domain.db.TableColumn;
/*     */ import com.tianshouzhi.autopro.domain.enums.ChartType;
/*     */ import java.util.UUID;
/*     */ 
/*     */ public class ReportFunction extends BaseTableFunction
/*     */ {
/*     */   private TableColumn xaxis;
/*     */   private TableColumn yaxis;
/*     */   private TableColumn categorizedColumn;
/*     */   private ChartType chartType;
/*  34 */   private Boolean isAccumulate = Boolean.valueOf(false);
/*     */ 
/*  38 */   private Boolean isCountRecord = Boolean.valueOf(false);
/*     */   private TableColumn countValueColumn;
/*     */   private String UUID_KEY;
/*     */ 
/*     */   public ReportFunction()
/*     */   {
/*  11 */     super("Report_Function");
/*  12 */     this.UUID_KEY = UUID.randomUUID().toString();
/*     */   }
/*     */ 
/*     */   public TableColumn getXaxis()
/*     */   {
/*  48 */     return this.xaxis;
/*     */   }
/*     */ 
/*     */   public void setXaxis(TableColumn xaxis) {
/*  52 */     this.xaxis = xaxis;
/*     */   }
/*     */ 
/*     */   public TableColumn getYaxis() {
/*  56 */     return this.yaxis;
/*     */   }
/*     */ 
/*     */   public void setYaxis(TableColumn yaxis) {
/*  60 */     this.yaxis = yaxis;
/*     */   }
/*     */ 
/*     */   public TableColumn getCategorizedColumn() {
/*  64 */     return this.categorizedColumn;
/*     */   }
/*     */ 
/*     */   public void setCategorizedColumn(TableColumn categorizedColumn) {
/*  68 */     this.categorizedColumn = categorizedColumn;
/*     */   }
/*     */ 
/*     */   public ChartType getChartType() {
/*  72 */     return this.chartType;
/*     */   }
/*     */ 
/*     */   public void setChartType(ChartType chartType) {
/*  76 */     this.chartType = chartType;
/*     */   }
/*     */ 
/*     */   public Boolean getIsAccumulate() {
/*  80 */     return this.isAccumulate;
/*     */   }
/*     */ 
/*     */   public void setIsAccumulate(Boolean isAccumulate) {
/*  84 */     this.isAccumulate = isAccumulate;
/*     */   }
/*     */ 
/*     */   public TableColumn getCountValueColumn() {
/*  88 */     return this.countValueColumn;
/*     */   }
/*     */ 
/*     */   public void setCountValueColumn(TableColumn countValueColumn) {
/*  92 */     this.countValueColumn = countValueColumn;
/*     */   }
/*     */ 
/*     */   public Boolean getIsCountRecord() {
/*  96 */     return this.isCountRecord;
/*     */   }
/*     */ 
/*     */   public void setIsCountRecord(Boolean isCountRecord) {
/* 100 */     this.isCountRecord = isCountRecord;
/*     */   }
/*     */ 
/*     */   public String getUUID_KEY()
/*     */   {
/* 105 */     return this.UUID_KEY;
/*     */   }
/*     */ 
/*     */   public void setUUID_KEY(String uUID_KEY) {
/* 109 */     this.UUID_KEY = uUID_KEY;
/*     */   }
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 115 */     int result = super.hashCode();
/* 116 */     result = 31 * result + (
/* 117 */       this.UUID_KEY == null ? 0 : this.UUID_KEY.hashCode());
/* 118 */     return result;
/*     */   }
/*     */ 
/*     */   public boolean equals(Object obj)
/*     */   {
/* 123 */     if (this == obj)
/* 124 */       return true;
/* 125 */     if (!super.equals(obj))
/* 126 */       return false;
/* 127 */     if (getClass() != obj.getClass())
/* 128 */       return false;
/* 129 */     ReportFunction other = (ReportFunction)obj;
/* 130 */     if (this.UUID_KEY == null) {
/* 131 */       if (other.UUID_KEY != null)
/* 132 */         return false;
/* 133 */     } else if (!this.UUID_KEY.equals(other.UUID_KEY))
/* 134 */       return false;
/* 135 */     return true;
/*     */   }
/*     */ }

/* Location:           D:\MyEclipse2015CI\dropins\com.tianshouzhi.autopro_1.0.0.201506242235.jar
 * Qualified Name:     com.tianshouzhi.autopro.domain.functions.ReportFunction
 * JD-Core Version:    0.6.2
 */