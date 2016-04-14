/*     */ package com.tianshouzhi.autopro.util;
/*     */ 
/*     */ import com.tianshouzhi.autopro.domain.db.DBTable;
/*     */ import com.tianshouzhi.autopro.domain.db.TableColumn;
/*     */ import com.tianshouzhi.autopro.domain.db.TableRelation;
/*     */ import com.tianshouzhi.autopro.domain.functions.BaseTableFunction;
/*     */ import com.tianshouzhi.autopro.domain.functions.ExcelFunction;
/*     */ import com.tianshouzhi.autopro.domain.functions.LoginFunction;
/*     */ import com.tianshouzhi.autopro.domain.functions.LongTextFunction;
/*     */ import com.tianshouzhi.autopro.domain.functions.ReportFunction;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import org.apache.log4j.Logger;
/*     */ 
/*     */ public class DBTablesUtil
/*     */ {
/*  21 */   private static final Logger logger = Logger.getLogger(DBTablesUtil.class);
/*     */ 
/*     */   public static boolean isMiddleTable(DBTable dbTable)
/*     */   {
/*  29 */     int foreinkeyListSize = dbTable.getForeinkeyList().size();
/*  30 */     int pkColumnListSize = dbTable.getPKColumnList().size();
/*  31 */     int tableColumnsSize = dbTable.getTableColumns().size();
/*  32 */     int totalColumnsSize = foreinkeyListSize + pkColumnListSize + tableColumnsSize;
/*  33 */     if ((foreinkeyListSize == totalColumnsSize) && (foreinkeyListSize == 2)) {
/*  34 */       return true;
/*     */     }
/*  36 */     return false;
/*     */   }
/*     */ 
/*     */   public static List<DBTable> getSelectedDBTables()
/*     */   {
/*  46 */     List dbTables = (List)MessageHub.get("SELECTED_TABLES");
/*  47 */     List excludeNoneGenerateCodeTables = new ArrayList();
/*  48 */     for (DBTable dbTable : dbTables) {
/*  49 */       if (dbTable.isGenerateCode()) {
/*  50 */         excludeNoneGenerateCodeTables.add(dbTable);
/*     */       }
/*     */     }
/*  53 */     return excludeNoneGenerateCodeTables;
/*     */   }
/*     */ 
/*     */   public static String[] getSelectedTableNames()
/*     */   {
/*  61 */     List selectedDBTables = getSelectedDBTables();
/*  62 */     String[] names = new String[selectedDBTables.size()];
/*  63 */     int i = 0;
/*  64 */     for (DBTable dbTable : selectedDBTables) {
/*  65 */       names[i] = dbTable.getTableName();
/*  66 */       i++;
/*     */     }
/*  68 */     return names;
/*     */   }
/*     */ 
/*     */   public static DBTable getSelectedDBTableByTableName(String tableName)
/*     */   {
/*  77 */     List selectedDBTables = getSelectedDBTables();
/*  78 */     for (DBTable dbTable : selectedDBTables) {
/*  79 */       if (tableName.equals(dbTable.getTableName())) {
/*  80 */         return dbTable;
/*     */       }
/*     */     }
/*  83 */     return null;
/*     */   }
/*     */ 
/*     */   public static String[] getSelectedDbTableColumnNames(String tableName)
/*     */   {
/*  92 */     List selectedDBTables = getSelectedDBTables();
/*  93 */     for (DBTable dbTable : selectedDBTables) {
/*  94 */       if (dbTable.getTableName().equals(tableName)) {
/*  95 */         List foreinkeyList = dbTable.getForeinkeyList();
/*  96 */         List tableColumns = dbTable.getTableColumns();
/*  97 */         if (foreinkeyList != null) {
/*  98 */           String[] columnNames = new String[foreinkeyList.size() + tableColumns.size()];
/*  99 */           int i = 0;
/* 100 */           for (TableColumn tableColumn : tableColumns) {
/* 101 */             columnNames[i] = tableColumn.getColumnName();
/* 102 */             i++;
/*     */           }
/*     */ 
/* 105 */           for (TableColumn tableColumn : foreinkeyList) {
/* 106 */             columnNames[i] = tableColumn.getColumnName();
/* 107 */             i++;
/*     */           }
/* 109 */           return columnNames;
/*     */         }
/*     */       }
/*     */     }
/* 113 */     return null;
/*     */   }
/*     */ 
/*     */   public static String[] getSelectedDbTableColumnNamesFirstBlank(String tableName)
/*     */   {
/* 122 */     String[] tableColumnNames = getSelectedDbTableColumnNames(tableName);
/* 123 */     if ((tableColumnNames != null) && (tableColumnNames.length > 0)) {
/* 124 */       String[] columnNamesFirstBlank = new String[tableColumnNames.length + 1];
/* 125 */       columnNamesFirstBlank[0] = "";
/* 126 */       System.arraycopy(tableColumnNames, 0, columnNamesFirstBlank, 1, tableColumnNames.length);
/* 127 */       return columnNamesFirstBlank;
/*     */     }
/* 129 */     return null;
/*     */   }
/*     */ 
/*     */   public static TableColumn getTableColumn(String tableName, String columnName)
/*     */   {
/* 139 */     DBTable dbTable = getSelectedDBTableByTableName(tableName);
/* 140 */     List foreinkeyList = dbTable.getForeinkeyList();
/* 141 */     for (TableColumn tableColumn : foreinkeyList) {
/* 142 */       if (tableColumn.getColumnName().equals(columnName)) {
/* 143 */         return tableColumn;
/*     */       }
/*     */     }
/* 146 */     List tableColumns = dbTable.getTableColumns();
/* 147 */     for (TableColumn tableColumn : tableColumns) {
/* 148 */       if (tableColumn.getColumnName().equals(columnName)) {
/* 149 */         return tableColumn;
/*     */       }
/*     */     }
/* 152 */     return null;
/*     */   }
/*     */ 
/*     */   public static void addTableFunction(String tableName, BaseTableFunction tableFunction)
/*     */   {
/* 161 */     DBTable dbTable = getSelectedDBTableByTableName(tableName);
/* 162 */     if (dbTable == null) {
/* 163 */       logger.info("没有该表:" + tableName);
/* 164 */       return;
/*     */     }
/* 166 */     dbTable.getTableFunctions().add(tableFunction);
/* 167 */     replaceMessageHubDbtable(dbTable);
/*     */   }
/*     */ 
/*     */   public static void replaceMessageHubDbtable(DBTable newTable)
/*     */   {
/* 175 */     if (newTable == null) {
/* 176 */       return;
/*     */     }
/* 178 */     List selectedDBTables = getSelectedDBTables();
/* 179 */     int i = 0;
/* 180 */     for (DBTable oldTable : selectedDBTables) {
/* 181 */       if (oldTable.getTableName().equals(newTable.getTableName())) {
/* 182 */         selectedDBTables.set(i, oldTable);
/* 183 */         MessageHub.put("SELECTED_TABLES", selectedDBTables);
/* 184 */         return;
/*     */       }
/* 186 */       i++;
/*     */     }
/*     */   }
/*     */ 
/*     */   public static List<ReportFunction> getReportFunctions(String tableName)
/*     */   {
/* 195 */     DBTable dbTable = getSelectedDBTableByTableName(tableName);
/* 196 */     List tableFunctions = dbTable.getTableFunctions();
/* 197 */     if ((tableFunctions != null) && (tableFunctions.size() > 0)) {
/* 198 */       List reportFunctions = new ArrayList();
/* 199 */       for (BaseTableFunction baseTableFunction : tableFunctions) {
/* 200 */         if ((baseTableFunction instanceof ReportFunction)) {
/* 201 */           ReportFunction reportFunction = (ReportFunction)baseTableFunction;
/* 202 */           reportFunctions.add(reportFunction);
/*     */         }
/*     */       }
/* 205 */       return reportFunctions;
/*     */     }
/* 207 */     return null;
/*     */   }
/*     */ 
/*     */   public static String[] getDateColumnNames(String tableName)
/*     */   {
/* 214 */     DBTable dbTable = getSelectedDBTableByTableName(tableName);
/* 215 */     List tableColumns = dbTable.getTableColumns();
/* 216 */     List dateColumnNames = new ArrayList();
/* 217 */     for (TableColumn tableColumn : tableColumns) {
/* 218 */       if (tableColumn.getJavaType().equals("java.util.Date")) {
/* 219 */         dateColumnNames.add(tableColumn.getColumnName());
/*     */       }
/*     */     }
/* 222 */     String[] dateColumnNamesArr = new String[dateColumnNames.size()];
/* 223 */     return (String[])dateColumnNames.toArray(dateColumnNamesArr);
/*     */   }
/*     */ 
/*     */   public static void putDBTables(List<DBTable> dbTables)
/*     */   {
/* 228 */     MessageHub.put("SELECTED_TABLES", dbTables);
/*     */   }
/*     */ 
/*     */   public static void removeFunction(String tableName, String UUID_KEY)
/*     */   {
/* 233 */     DBTable dbTable = getSelectedDBTableByTableName(tableName);
/* 234 */     List tableFunctions = dbTable.getTableFunctions();
/* 235 */     ReportFunction reportFunction = new ReportFunction();
/* 236 */     reportFunction.setUUID_KEY(UUID_KEY);
/* 237 */     tableFunctions.remove(reportFunction);
/* 238 */     dbTable.setTableFunctions(tableFunctions);
/* 239 */     replaceTable(dbTable);
/*     */   }
/*     */ 
/*     */   private static void replaceTable(DBTable repalcentMent) {
/* 243 */     List selectedDBTables = getSelectedDBTables();
/* 244 */     for (DBTable dbTable : selectedDBTables) {
/* 245 */       int i = 0;
/* 246 */       if (repalcentMent.getTableName().equals(dbTable.getTableName())) {
/* 247 */         selectedDBTables.set(i, repalcentMent);
/* 248 */         i++;
/*     */       }
/*     */     }
/* 251 */     putDBTables(selectedDBTables);
/*     */   }
/*     */   public static DBTable getRelateTable(String tableName, String foregnKeyName) {
/* 254 */     DBTable dbTable = getSelectedDBTableByTableName(tableName);
/* 255 */     List tableRelations = dbTable.getTableRelations();
/* 256 */     if ((tableRelations != null) && (tableRelations.size() > 0)) {
/* 257 */       for (TableRelation tableRelation : tableRelations) {
/* 258 */         if ((tableRelation.getFKColumn_Name().equals(foregnKeyName)) && (tableRelation.getFKTable_Name().equals(tableName))) {
/* 259 */           String pkTable_Name = tableRelation.getPKTable_Name();
/* 260 */           return getSelectedDBTableByTableName(pkTable_Name);
/*     */         }
/*     */       }
/*     */     }
/*     */ 
/* 265 */     return null;
/*     */   }
/*     */   public static boolean hasReportFunction() {
/* 268 */     List dbTables = getSelectedDBTables();
/* 269 */     for (DBTable dbTable : dbTables) {
/* 270 */       List reportFunctions = getReportFunctions(dbTable.getTableName());
/* 271 */       if ((reportFunctions != null) && (reportFunctions.size() > 0)) {
/* 272 */         return true;
/*     */       }
/*     */     }
/* 275 */     return false;
/*     */   }
/*     */ 
/*     */   public static LoginFunction getLoginFunction() {
/* 279 */     List allFunctions = getAllFunctions();
/* 280 */     if ((allFunctions != null) && (allFunctions.size() > 0)) {
/* 281 */       for (BaseTableFunction baseTableFunction : allFunctions) {
/* 282 */         if ((baseTableFunction != null) && ((baseTableFunction instanceof LoginFunction))) {
/* 283 */           return (LoginFunction)baseTableFunction;
/*     */         }
/*     */       }
/*     */     }
/*     */ 
/* 288 */     return null;
/*     */   }
/*     */ 
/*     */   public static List<BaseTableFunction> getAllFunctions()
/*     */   {
/* 293 */     List functions = new ArrayList();
/* 294 */     List dbTables = getSelectedDBTables();
/* 295 */     for (DBTable dbTable : dbTables) {
/* 296 */       functions.addAll(dbTable.getTableFunctions());
/*     */     }
/* 298 */     return functions;
/*     */   }
/*     */ 
/*     */   public static boolean hasLoginFunction(String tableName) {
/* 302 */     DBTable dbTable = getSelectedDBTableByTableName(tableName);
/* 303 */     List tableFunctions = dbTable.getTableFunctions();
/* 304 */     if ((tableFunctions != null) && (tableFunctions.size() > 0)) {
/* 305 */       for (BaseTableFunction baseTableFunction : tableFunctions) {
/* 306 */         if ((baseTableFunction instanceof LoginFunction)) {
/* 307 */           return true;
/*     */         }
/*     */       }
/*     */     }
/* 311 */     return false;
/*     */   }
/*     */   public static void removeLoginFunction(String tableName) {
/* 314 */     DBTable dbTable = getSelectedDBTableByTableName(tableName);
/* 315 */     List tableFunctions = dbTable.getTableFunctions();
/* 316 */     if ((tableFunctions != null) && (tableFunctions.size() > 0)) {
/* 317 */       int i = 0;
/* 318 */       for (BaseTableFunction baseTableFunction : tableFunctions) {
/* 319 */         if ((baseTableFunction instanceof LoginFunction)) {
/* 320 */           dbTable.getTableFunctions().remove(i);
/* 321 */           replaceMessageHubDbtable(dbTable);
/* 322 */           return;
/*     */         }
/* 324 */         i++;
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   public static boolean hasExcelFunction() {
/* 330 */     List allFunctions = getAllFunctions();
/* 331 */     for (BaseTableFunction baseTableFunction : allFunctions) {
/* 332 */       if ((baseTableFunction instanceof ExcelFunction)) {
/* 333 */         return true;
/*     */       }
/*     */     }
/* 336 */     return false;
/*     */   }
/*     */   public static List<TableColumn> getLongTextColumns(String tableName) {
/* 339 */     DBTable dbTable = getSelectedDBTableByTableName(tableName);
/* 340 */     List tableColumns = dbTable.getTableColumns();
/* 341 */     List longTextColumn = new ArrayList();
/* 342 */     for (TableColumn tableColumn : tableColumns) {
/* 343 */       String jdbcType = tableColumn.getJdbcType();
/* 344 */       if ("LONGVARCHAR".equals(jdbcType)) {
/* 345 */         longTextColumn.add(tableColumn);
/*     */       }
/*     */     }
/* 348 */     return longTextColumn;
/*     */   }
/*     */ 
/*     */   public static void removeLongTextFunction(String tableName, String columnName)
/*     */   {
/* 353 */     DBTable dbTable = getSelectedDBTableByTableName(tableName);
/* 354 */     List tableFunctions = dbTable.getTableFunctions();
/* 355 */     if ((tableFunctions != null) && (tableFunctions.size() > 0)) {
/* 356 */       int i = 0;
/* 357 */       for (BaseTableFunction baseTableFunction : tableFunctions) {
/* 358 */         if ((baseTableFunction instanceof LongTextFunction)) {
/* 359 */           LongTextFunction longTextFunction = (LongTextFunction)baseTableFunction;
/* 360 */           if (longTextFunction.getLongTextColumnName().equals(columnName)) {
/* 361 */             dbTable.getTableFunctions().remove(i);
/* 362 */             replaceMessageHubDbtable(dbTable);
/* 363 */             return;
/*     */           }
/*     */         }
/* 366 */         i++;
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   public static boolean useHtmlEditor(String tableName, String longtextColumnName)
/*     */   {
/* 378 */     DBTable dbTable = getSelectedDBTableByTableName(tableName);
/* 379 */     List tableFunctions = dbTable.getTableFunctions();
/* 380 */     if ((tableFunctions != null) && (tableFunctions.size() > 0)) {
/* 381 */       for (BaseTableFunction baseTableFunction : tableFunctions) {
/* 382 */         if ((baseTableFunction instanceof LongTextFunction)) {
/* 383 */           LongTextFunction longTextFunction = (LongTextFunction)baseTableFunction;
/* 384 */           if (longTextFunction.getLongTextColumnName().equals(longtextColumnName)) {
/* 385 */             return true;
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/* 390 */     return false;
/*     */   }
/*     */   public static Object useHtmlEditor(String tableName) {
/* 393 */     DBTable dbTable = getSelectedDBTableByTableName(tableName);
/* 394 */     List tableFunctions = dbTable.getTableFunctions();
/* 395 */     if ((tableFunctions != null) && (tableFunctions.size() > 0)) {
/* 396 */       for (BaseTableFunction baseTableFunction : tableFunctions) {
/* 397 */         if ((baseTableFunction instanceof LongTextFunction)) {
/* 398 */           return Boolean.valueOf(true);
/*     */         }
/*     */       }
/*     */     }
/* 402 */     return Boolean.valueOf(false);
/*     */   }
/*     */ }

/* Location:           D:\MyEclipse2015CI\dropins\com.tianshouzhi.autopro_1.0.0.201506242235.jar
 * Qualified Name:     com.tianshouzhi.autopro.util.DBTablesUtil
 * JD-Core Version:    0.6.2
 */