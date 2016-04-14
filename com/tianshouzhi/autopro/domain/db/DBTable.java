/*     */ package com.tianshouzhi.autopro.domain.db;
/*     */ 
/*     */ import com.tianshouzhi.autopro.domain.functions.BaseTableFunction;
/*     */ import com.tianshouzhi.autopro.util.StringUtil;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ 
/*     */ public class DBTable
/*     */ {
/*     */   private String tableName;
/*  17 */   private String tableComment = "";
/*     */   private String entityName;
/*  21 */   private List<TableColumn> tableColumns = new ArrayList();
/*     */ 
/*  23 */   private boolean generateCode = true;
/*     */ 
/*  25 */   private List<TableColumn> PKColumnList = new ArrayList();
/*     */ 
/*  27 */   List<TableColumn> foreinkeyList = new ArrayList();
/*     */ 
/*  29 */   private List<DBTable> oneToManyList = new ArrayList();
/*     */ 
/*  31 */   private List<DBTable> manyToOneList = new ArrayList();
/*     */ 
/*  33 */   private List<DBTable> manyToManyList = new ArrayList();
/*     */ 
/*  35 */   private Boolean isSelfRelation = Boolean.valueOf(false);
/*     */ 
/*  37 */   private List<TableRelation> tableRelations = new ArrayList();
/*     */ 
/*  39 */   private List<BaseTableFunction> tableFunctions = new ArrayList();
/*     */ 
/*  43 */   private Boolean generateListJSP = Boolean.valueOf(true);
/*     */ 
/*  47 */   private Boolean generateSaveUIJsp = Boolean.valueOf(true);
/*     */ 
/*     */   public List<TableRelation> getTableRelations() {
/*  50 */     return this.tableRelations;
/*     */   }
/*     */ 
/*     */   public void setTableRelations(List<TableRelation> tableRelations) {
/*  54 */     this.tableRelations = tableRelations;
/*     */   }
/*     */ 
/*     */   public String getTableName() {
/*  58 */     return this.tableName;
/*     */   }
/*     */ 
/*     */   public void setTableName(String tableName) {
/*  62 */     this.tableName = tableName;
/*     */   }
/*     */ 
/*     */   public DBTable()
/*     */   {
/*     */   }
/*     */ 
/*     */   public List<TableColumn> getTableColumns() {
/*  70 */     return this.tableColumns;
/*     */   }
/*     */ 
/*     */   public void setTableColumns(List<TableColumn> tableColumns) {
/*  74 */     this.tableColumns = tableColumns;
/*     */   }
/*     */ 
/*     */   public String getTableComment()
/*     */   {
/*  80 */     return this.tableComment;
/*     */   }
/*     */ 
/*     */   public void setTableComment(String tableComment) {
/*  84 */     this.tableComment = tableComment;
/*     */   }
/*     */ 
/*     */   public boolean isGenerateCode()
/*     */   {
/*  89 */     return this.generateCode;
/*     */   }
/*     */ 
/*     */   public void setGenerateCode(boolean generateCode) {
/*  93 */     this.generateCode = generateCode;
/*     */   }
/*     */ 
/*     */   public List<TableColumn> getPKColumnList() {
/*  97 */     return this.PKColumnList;
/*     */   }
/*     */ 
/*     */   public void setPKColumnList(List<TableColumn> pKColumnList) {
/* 101 */     this.PKColumnList = pKColumnList;
/*     */   }
/*     */ 
/*     */   public String getEntityName() {
/* 105 */     return this.entityName;
/*     */   }
/*     */ 
/*     */   public void setEntityName(String entityName) {
/* 109 */     this.entityName = entityName;
/*     */   }
/*     */ 
/*     */   public DBTable(String tableName, List<TableColumn> tableColumns, List<TableColumn> pKColumnList, List<TableRelation> tableRelations, List<TableColumn> foreinkeyList, String tableComment)
/*     */   {
/* 115 */     this.tableName = tableName;
/* 116 */     this.tableColumns = tableColumns;
/* 117 */     this.PKColumnList = pKColumnList;
/* 118 */     this.tableRelations = tableRelations;
/* 119 */     this.entityName = StringUtil.getCamelName(tableName);
/* 120 */     this.tableComment = tableComment;
/* 121 */     this.foreinkeyList = foreinkeyList;
/*     */   }
/*     */ 
/*     */   public List<DBTable> getOneToManyList()
/*     */   {
/* 126 */     return this.oneToManyList;
/*     */   }
/*     */ 
/*     */   public void setOneToManyList(List<DBTable> oneToManyList) {
/* 130 */     this.oneToManyList = oneToManyList;
/*     */   }
/*     */ 
/*     */   public List<DBTable> getManyToOneList() {
/* 134 */     return this.manyToOneList;
/*     */   }
/*     */ 
/*     */   public void setManyToOneList(List<DBTable> manyToOneList) {
/* 138 */     this.manyToOneList = manyToOneList;
/*     */   }
/*     */ 
/*     */   public List<DBTable> getManyToManyList() {
/* 142 */     return this.manyToManyList;
/*     */   }
/*     */ 
/*     */   public void setManyToManyList(List<DBTable> manyToManyList) {
/* 146 */     this.manyToManyList = manyToManyList;
/*     */   }
/*     */ 
/*     */   public Boolean getIsSelfRelation() {
/* 150 */     return this.isSelfRelation;
/*     */   }
/*     */ 
/*     */   public void setIsSelfRelation(Boolean isSelfRelation) {
/* 154 */     this.isSelfRelation = isSelfRelation;
/*     */   }
/*     */ 
/*     */   public List<TableColumn> getForeinkeyList() {
/* 158 */     return this.foreinkeyList;
/*     */   }
/*     */ 
/*     */   public void setForeinkeyList(List<TableColumn> foreinkeyList) {
/* 162 */     this.foreinkeyList = foreinkeyList;
/*     */   }
/*     */ 
/*     */   public String[] getColumnNames() {
/* 166 */     String[] resultArr = new String[this.tableColumns.size()];
/* 167 */     int i = 0;
/* 168 */     for (TableColumn tableColumn : this.tableColumns) {
/* 169 */       resultArr[i] = tableColumn.getColumnName();
/* 170 */       i++;
/*     */     }
/* 172 */     return resultArr;
/*     */   }
/*     */ 
/*     */   public List<BaseTableFunction> getTableFunctions() {
/* 176 */     return this.tableFunctions;
/*     */   }
/*     */ 
/*     */   public void setTableFunctions(List<BaseTableFunction> tableFunctions) {
/* 180 */     this.tableFunctions = tableFunctions;
/*     */   }
/*     */ 
/*     */   public TableColumn getColumnByName(String colName) {
/* 184 */     List columns = getTableColumns();
/* 185 */     for (TableColumn tableColumn : columns) {
/* 186 */       if (tableColumn.getColumnName().equals(colName)) {
/* 187 */         return tableColumn;
/*     */       }
/*     */     }
/* 190 */     return null;
/*     */   }
/*     */ 
/*     */   public Boolean getGenerateListJSP() {
/* 194 */     return this.generateListJSP;
/*     */   }
/*     */ 
/*     */   public void setGenerateListJSP(Boolean generateListJSP) {
/* 198 */     this.generateListJSP = generateListJSP;
/*     */   }
/*     */ 
/*     */   public Boolean getGenerateSaveUIJsp() {
/* 202 */     return this.generateSaveUIJsp;
/*     */   }
/*     */ 
/*     */   public void setGenerateSaveUIJsp(Boolean generateSaveUIJsp) {
/* 206 */     this.generateSaveUIJsp = generateSaveUIJsp;
/*     */   }
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 212 */     int result = 1;
/* 213 */     result = 31 * result + (
/* 214 */       this.tableName == null ? 0 : this.tableName.hashCode());
/* 215 */     return result;
/*     */   }
/*     */ 
/*     */   public boolean equals(Object obj)
/*     */   {
/* 220 */     if (this == obj)
/* 221 */       return true;
/* 222 */     if (obj == null)
/* 223 */       return false;
/* 224 */     if (getClass() != obj.getClass())
/* 225 */       return false;
/* 226 */     DBTable other = (DBTable)obj;
/* 227 */     if (this.tableName == null) {
/* 228 */       if (other.tableName != null)
/* 229 */         return false;
/* 230 */     } else if (!this.tableName.equals(other.tableName))
/* 231 */       return false;
/* 232 */     return true;
/*     */   }
/*     */ }

/* Location:           D:\MyEclipse2015CI\dropins\com.tianshouzhi.autopro_1.0.0.201506242235.jar
 * Qualified Name:     com.tianshouzhi.autopro.domain.db.DBTable
 * JD-Core Version:    0.6.2
 */