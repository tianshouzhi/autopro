/*     */ package com.tianshouzhi.autopro.domain.db;
/*     */ 
/*     */ import com.tianshouzhi.autopro.util.StringUtil;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ 
/*     */ public class TableColumn
/*     */ {
/*     */   private String columnName;
/*     */   private String propertyName;
/*     */   private String javaType;
/*     */   private String javaSimpleName;
/*     */   private String jdbcType;
/*  37 */   private String columnComment = "";
/*     */   private Integer maxLength;
/*  41 */   private Boolean notNull = Boolean.valueOf(false);
/*     */ 
/*  44 */   private List<String> columnAnnotations = new ArrayList();
/*     */ 
/*  47 */   private Boolean isAutoIncrement = Boolean.valueOf(false);
/*     */ 
/*  49 */   private Boolean isForeignKey = Boolean.valueOf(false);
/*     */ 
/*     */   public String getColumnName() {
/*  52 */     return this.columnName;
/*     */   }
/*     */ 
/*     */   public TableColumn(String columnName, String javaType, String jdbcType, String columnComment, Integer maxLength, Boolean notNull, boolean isAutoIncrement)
/*     */   {
/*  58 */     this.columnName = columnName;
/*  59 */     this.javaType = javaType;
/*  60 */     this.jdbcType = jdbcType;
/*  61 */     this.columnComment = columnComment;
/*  62 */     this.maxLength = maxLength;
/*  63 */     this.notNull = notNull;
/*  64 */     int index = javaType.lastIndexOf(".");
/*     */ 
/*  66 */     if (index > 0)
/*  67 */       this.javaSimpleName = javaType.substring(index + 1);
/*     */     else {
/*  69 */       this.javaSimpleName = javaType;
/*     */     }
/*     */ 
/*  72 */     this.propertyName = StringUtil.getCamelName(columnName);
/*  73 */     this.isAutoIncrement = Boolean.valueOf(isAutoIncrement);
/*     */ 
/*  76 */     if (notNull.booleanValue()) {
/*  77 */       this.columnAnnotations.add("@NotBlank");
/*     */     }
/*  79 */     if ("String".equals(this.javaSimpleName))
/*  80 */       this.columnAnnotations.add("@Length(max=" + maxLength + ")");
/*     */   }
/*     */ 
/*     */   public Boolean getNotNull() {
/*  84 */     return this.notNull;
/*     */   }
/*     */ 
/*     */   public void setNotNull(Boolean notNull) {
/*  88 */     this.notNull = notNull;
/*     */   }
/*     */ 
/*     */   public void setColumnName(String columnName) {
/*  92 */     this.columnName = columnName;
/*     */   }
/*     */ 
/*     */   public String getColumnComment() {
/*  96 */     return this.columnComment;
/*     */   }
/*     */ 
/*     */   public void setColumnComment(String columnComment) {
/* 100 */     this.columnComment = columnComment;
/*     */   }
/*     */ 
/*     */   public Integer getMaxLength() {
/* 104 */     return this.maxLength;
/*     */   }
/*     */ 
/*     */   public void setMaxLength(Integer maxLength) {
/* 108 */     this.maxLength = maxLength;
/*     */   }
/*     */ 
/*     */   public TableColumn()
/*     */   {
/*     */   }
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 117 */     int result = 1;
/* 118 */     result = 31 * result + (
/* 119 */       this.columnName == null ? 0 : this.columnName.hashCode());
/* 120 */     return result;
/*     */   }
/*     */ 
/*     */   public boolean equals(Object obj)
/*     */   {
/* 125 */     if (this == obj)
/* 126 */       return true;
/* 127 */     if (obj == null)
/* 128 */       return false;
/* 129 */     if (getClass() != obj.getClass())
/* 130 */       return false;
/* 131 */     TableColumn other = (TableColumn)obj;
/* 132 */     if (this.columnName == null) {
/* 133 */       if (other.columnName != null)
/* 134 */         return false;
/* 135 */     } else if (!this.columnName.equals(other.columnName))
/* 136 */       return false;
/* 137 */     return true;
/*     */   }
/*     */ 
/*     */   public String getJavaType() {
/* 141 */     return this.javaType;
/*     */   }
/*     */ 
/*     */   public void setJavaType(String javaType) {
/* 145 */     this.javaType = javaType;
/*     */   }
/*     */ 
/*     */   public String getJdbcType() {
/* 149 */     return this.jdbcType;
/*     */   }
/*     */ 
/*     */   public void setJdbcType(String jdbcType) {
/* 153 */     this.jdbcType = jdbcType;
/*     */   }
/*     */ 
/*     */   public String getJavaSimpleName() {
/* 157 */     return this.javaSimpleName;
/*     */   }
/*     */ 
/*     */   public void setJavaSimpleName(String javaSimpleName) {
/* 161 */     this.javaSimpleName = javaSimpleName;
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/* 166 */     return "TableColumn [columnName=" + this.columnName + ", javaType=" + 
/* 167 */       this.javaType + ", javaSimpleName=" + this.javaSimpleName + 
/* 168 */       ", jdbcType=" + this.jdbcType + ", columnComment=" + this.columnComment + 
/* 169 */       ", maxLength=" + this.maxLength + ", notNull=" + this.notNull + "]";
/*     */   }
/*     */ 
/*     */   public String getPropertyName()
/*     */   {
/* 174 */     return this.propertyName;
/*     */   }
/*     */ 
/*     */   public void setPropertyName(String propertyName) {
/* 178 */     this.propertyName = propertyName;
/*     */   }
/*     */ 
/*     */   public Boolean getIsAutoIncrement() {
/* 182 */     return this.isAutoIncrement;
/*     */   }
/*     */ 
/*     */   public void setAutoIncrement(boolean isAutoIncrement) {
/* 186 */     this.isAutoIncrement = Boolean.valueOf(isAutoIncrement);
/*     */   }
/*     */ 
/*     */   public List<String> getColumnAnnotations() {
/* 190 */     return this.columnAnnotations;
/*     */   }
/*     */ 
/*     */   public void setColumnAnnotations(List<String> columnAnnotations) {
/* 194 */     this.columnAnnotations = columnAnnotations;
/*     */   }
/*     */ 
/*     */   public void setIsAutoIncrement(Boolean isAutoIncrement) {
/* 198 */     this.isAutoIncrement = isAutoIncrement;
/*     */   }
/*     */ 
/*     */   public Boolean getIsForeignKey() {
/* 202 */     return this.isForeignKey;
/*     */   }
/*     */ 
/*     */   public void setIsForeignKey(Boolean isForeignKey) {
/* 206 */     this.isForeignKey = isForeignKey;
/*     */   }
/*     */ }

/* Location:           D:\MyEclipse2015CI\dropins\com.tianshouzhi.autopro_1.0.0.201506242235.jar
 * Qualified Name:     com.tianshouzhi.autopro.domain.db.TableColumn
 * JD-Core Version:    0.6.2
 */