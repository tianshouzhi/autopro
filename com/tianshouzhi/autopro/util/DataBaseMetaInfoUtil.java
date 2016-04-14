/*     */ package com.tianshouzhi.autopro.util;
/*     */ 
/*     */ import com.tianshouzhi.autopro.domain.db.DBConnectionConfig;
/*     */ import com.tianshouzhi.autopro.domain.db.DBTable;
/*     */ import com.tianshouzhi.autopro.domain.db.TableColumn;
/*     */ import com.tianshouzhi.autopro.domain.db.TableRelation;
/*     */ import com.tianshouzhi.autopro.domain.db.type.DateTypeConvertor;
/*     */ import com.tianshouzhi.autopro.domain.db.type.MybatisJdbcType;
/*     */ import com.tianshouzhi.autopro.domain.enums.DataBaseEnum;
/*     */ import com.tianshouzhi.autopro.ui.msg.Messages;
/*     */ import java.io.PrintStream;
/*     */ import java.sql.Connection;
/*     */ import java.sql.DatabaseMetaData;
/*     */ import java.sql.PreparedStatement;
/*     */ import java.sql.ResultSet;
/*     */ import java.sql.ResultSetMetaData;
/*     */ import java.sql.Statement;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import org.apache.log4j.Logger;
/*     */ 
/*     */ public class DataBaseMetaInfoUtil
/*     */ {
/*  33 */   private static final Logger logger = Logger.getLogger(DataBaseMetaInfoUtil.class);
/*     */ 
/*     */   public static List<DBTable> getDBTables(DBConnectionConfig dbConfig)
/*     */   {
/*  40 */     List dbTables = null;
/*  41 */     Connection connection = null;
/*     */ 
/*  44 */     List tableColumnsCommentList = new ArrayList();
/*     */     try
/*     */     {
/*  47 */       connection = JdbcUtil.getConnection(dbConfig);
/*  48 */       tableColumnsCommentList = getTableAndColumnCommentMapping(dbConfig);
/*  49 */       String catalogName = connection.getCatalog();
/*  50 */       DatabaseMetaData dbMetaData = connection.getMetaData();
/*  51 */       ResultSet tableResultSet = dbMetaData.getTables(connection.getCatalog(), 
/*  52 */         dbMetaData.getUserName(), "%", new String[] { "TABLE" });
/*  53 */       dbTables = new ArrayList();
/*  54 */       while (tableResultSet.next())
/*     */       {
/*  56 */         String tableName = tableResultSet.getString("TABLE_NAME");
/*  57 */         dbMetaData.getDatabaseProductName();
/*  58 */         String tableComment = getMysqlTableComment(tableColumnsCommentList, tableName);
/*  59 */         String sql = "select * from " + tableName;
/*  60 */         ResultSet rsSet = connection.createStatement()
/*  61 */           .executeQuery(sql);
/*     */ 
/*  63 */         ResultSetMetaData columnsMetaData = rsSet.getMetaData();
/*     */ 
/*  65 */         List tableRelationList = getTableRelations(
/*  66 */           dbMetaData, catalogName, tableName);
/*     */ 
/*  68 */         List primaryKeyNameList = getPrimaryKeys(dbMetaData, 
/*  69 */           catalogName, tableName);
/*  70 */         List primarykeyList = new ArrayList();
/*     */ 
/*  73 */         List tableColumns = new ArrayList();
/*     */ 
/*  76 */         List foreinkeyList = new ArrayList();
/*     */ 
/*  78 */         rsSet.close();
/*     */ 
/*  80 */         for (int i = 1; i <= columnsMetaData.getColumnCount(); i++) {
/*  81 */           String columnName = columnsMetaData.getColumnName(i);
/*  82 */           int jdbcTypeCode = columnsMetaData.getColumnType(i);
/*  83 */           MybatisJdbcType mybatisJdbcType = MybatisJdbcType.forCode(jdbcTypeCode);
/*  84 */           String jdbcType = mybatisJdbcType.name();
/*  85 */           String javaType = columnsMetaData.getColumnClassName(i);
/*     */ 
/*  88 */           if (DateTypeConvertor.dateTrans.contains(jdbcType)) {
/*  89 */             javaType = "java.util.Date";
/*     */           }
/*     */ 
/*  93 */           if ("[B".equalsIgnoreCase(javaType)) {
/*  94 */             javaType = "byte[]";
/*     */           }
/*     */ 
/*  97 */           boolean nullable = columnsMetaData.isNullable(i) == 1;
/*     */ 
/*  99 */           String columnComment = "";
/*     */ 
/* 101 */           columnComment = setTableColumnComment(tableColumnsCommentList, 
/* 102 */             tableName, columnName, columnComment);
/*     */ 
/* 104 */           Integer maxLength = null;
/*     */ 
/* 106 */           if (("java.lang.Integer".equals(javaType)) || 
/* 107 */             ("java.lang.String".equals(javaType)) || 
/* 108 */             ("java.lang.Long".equals(javaType))) {
/* 109 */             maxLength = Integer.valueOf(columnsMetaData.getColumnDisplaySize(i));
/*     */           }
/*     */ 
/* 113 */           if ("java.math.BigDecimal".equals(javaType)) {
/* 114 */             int precision = columnsMetaData.getPrecision(i);
/* 115 */             int scale = columnsMetaData.getScale(i);
/* 116 */             if ((precision != 0) && (scale == 0))
/* 117 */               javaType = "java.lang.Long";
/*     */             else {
/* 119 */               javaType = "java.lang.Double";
/*     */             }
/*     */ 
/*     */           }
/*     */ 
/* 124 */           boolean isAutoIncrement = columnsMetaData.isAutoIncrement(i);
/* 125 */           TableColumn tableColumn = new TableColumn(columnName, 
/* 126 */             javaType, jdbcType, columnComment, maxLength, 
/* 127 */             Boolean.valueOf(nullable), isAutoIncrement);
/*     */ 
/* 130 */           if (primaryKeyNameList.contains(columnName)) {
/* 131 */             primarykeyList.add(tableColumn);
/*     */           }
/*     */           else
/*     */           {
/* 135 */             boolean currentColumnIsNotFK = true;
/* 136 */             for (TableRelation tableRelation : tableRelationList) {
/* 137 */               if (tableRelation.getFKColumn_Name().equals(columnName)) {
/* 138 */                 tableColumn.setIsForeignKey(Boolean.valueOf(true));
/* 139 */                 currentColumnIsNotFK = false;
/* 140 */                 break;
/*     */               }
/*     */             }
/* 143 */             if (currentColumnIsNotFK)
/* 144 */               tableColumns.add(tableColumn);
/*     */             else {
/* 146 */               foreinkeyList.add(tableColumn);
/*     */             }
/*     */           }
/*     */         }
/* 150 */         DBTable dbTable = new DBTable(tableName, tableColumns, primarykeyList, tableRelationList, foreinkeyList, tableComment);
/*     */ 
/* 152 */         if (columnsMetaData.getColumnCount() == tableRelationList.size()) {
/* 153 */           dbTable.setGenerateCode(false);
/* 154 */           dbTable.setGenerateListJSP(Boolean.valueOf(false));
/* 155 */           dbTable.setGenerateSaveUIJsp(Boolean.valueOf(false));
/*     */         }
/* 157 */         dbTables.add(dbTable);
/* 158 */         columnsMetaData = null;
/*     */       }
/* 160 */       tableResultSet.close();
/*     */     }
/*     */     catch (Exception e) {
/* 163 */       dbTables = null;
/* 164 */       e.printStackTrace();
/* 165 */       logger.error(Messages.DBUtil_GET_DB_TABLE_ERROR, e);
/* 166 */       throw new RuntimeException(e);
/*     */     } finally {
/* 168 */       JdbcUtil.release(connection, null, null);
/*     */     }
/*     */ 
/* 171 */     convertTableRelationToEntityRelation(dbTables);
/* 172 */     return dbTables;
/*     */   }
/*     */ 
/*     */   private static String getMysqlTableComment(List<DBTable> tableAndColumnsCommentList, String tableName)
/*     */   {
/* 183 */     String tableComment = tableName;
/* 184 */     for (DBTable dbTable : tableAndColumnsCommentList) {
/* 185 */       if (dbTable.getTableName().equals(tableName)) {
/* 186 */         tableComment = dbTable.getTableComment();
/*     */       }
/*     */     }
/* 189 */     return tableComment;
/*     */   }
/*     */ 
/*     */   private static void convertTableRelationToEntityRelation(List<DBTable> dbTables)
/*     */   {
/* 199 */     Map nameTableMap = new HashMap();
/*     */ 
/* 201 */     for (DBTable dbTable : dbTables)
/* 202 */       nameTableMap.put(dbTable.getTableName(), dbTable);
/*     */     Iterator localIterator2;
/* 205 */     label300: for (??? = dbTables.iterator(); ???.hasNext(); 
/* 209 */       localIterator2.hasNext())
/*     */     {
/* 205 */       DBTable fkTable = (DBTable)???.next();
/* 206 */       if ((fkTable.getTableRelations() == null) && (fkTable.getTableRelations().size() == 0)) {
/*     */         break label300;
/*     */       }
/* 209 */       localIterator2 = fkTable.getTableRelations().iterator(); continue; TableRelation tableRelation = (TableRelation)localIterator2.next();
/*     */ 
/* 212 */       if ((fkTable.getTableRelations() != null) && (fkTable.getTableRelations().size() > 0))
/*     */       {
/* 214 */         String pkTable_Name = tableRelation.getPKTable_Name();
/* 215 */         DBTable pkTable = (DBTable)nameTableMap.get(pkTable_Name);
/*     */ 
/* 217 */         if ((pkTable.getTableRelations() != null) && (pkTable.getTableRelations().size() > 0))
/*     */         {
/* 219 */           boolean isContains = false;
/* 220 */           for (TableRelation invertRelation : pkTable.getTableRelations())
/*     */           {
/* 222 */             if ((invertRelation != null) && (invertRelation.getPKTable_Name().equals(pkTable))) {
/* 223 */               isContains = true;
/* 224 */               break;
/*     */             }
/*     */           }
/* 227 */           if (isContains) {
/* 228 */             fkTable.getManyToManyList().add(pkTable);
/* 229 */             pkTable.getManyToManyList().add(fkTable);
/*     */           } else {
/* 231 */             setOneToManyRelation(fkTable, pkTable, nameTableMap);
/*     */           }
/*     */         }
/*     */         else
/*     */         {
/* 236 */           setOneToManyRelation(fkTable, pkTable, nameTableMap);
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   private static void setOneToManyRelation(DBTable fkTable, DBTable pkTable, Map<String, DBTable> nameTableMap)
/*     */   {
/* 245 */     if (DBTablesUtil.isMiddleTable(fkTable)) {
/* 246 */       System.out.println("中间表:" + fkTable.getTableName());
/* 247 */       List tableRelations = fkTable.getTableRelations();
/* 248 */       for (TableRelation tableRelation : tableRelations) {
/* 249 */         String pkTable_Name = tableRelation.getPKTable_Name();
/* 250 */         String fkTable_Name = tableRelation.getFKTable_Name();
/* 251 */         DBTable pktable1 = (DBTable)nameTableMap.get(pkTable_Name);
/* 252 */         DBTable fktable1 = (DBTable)nameTableMap.get(fkTable_Name);
/* 253 */         List manyToManyList = pkTable.getManyToManyList();
/* 254 */         if ((!manyToManyList.contains(pktable1)) && 
/* 255 */           (!DBTablesUtil.isMiddleTable(pktable1)) && (!pkTable.getTableName().equals(pktable1.getTableName()))) {
/* 256 */           pkTable.getManyToManyList().add(pktable1);
/*     */         }
/*     */ 
/* 259 */         if ((!manyToManyList.contains(fktable1)) && (!fkTable.getTableName().equals(fktable1.getTableName())) && 
/* 260 */           (!DBTablesUtil.isMiddleTable(fktable1))) {
/* 261 */           pkTable.getManyToManyList().add(fktable1);
/*     */         }
/*     */       }
/*     */ 
/* 265 */       return;
/*     */     }
/*     */ 
/* 268 */     if (fkTable.getTableName().equals(pkTable.getTableName())) {
/* 269 */       fkTable.setIsSelfRelation(Boolean.valueOf(true));
/*     */     } else {
/* 271 */       fkTable.getManyToOneList().add(pkTable);
/* 272 */       pkTable.getOneToManyList().add(fkTable);
/*     */     }
/*     */   }
/*     */ 
/*     */   private static String setTableColumnComment(List<DBTable> tableAndColumnComment, String tableName, String columnName, String columnComment)
/*     */   {
/* 286 */     for (DBTable dbTable : tableAndColumnComment) {
/* 287 */       boolean isbreak = false;
/* 288 */       for (TableColumn tableColumn : dbTable.getTableColumns()) {
/* 289 */         if ((dbTable.getTableName().equalsIgnoreCase(tableName)) && (tableColumn.getColumnName().equalsIgnoreCase(columnName))) {
/* 290 */           if (!StringUtil.isEmpty(tableColumn.getColumnComment())) {
/* 291 */             columnComment = tableColumn.getColumnComment();
/*     */           }
/*     */           else {
/* 294 */             columnComment = columnName;
/*     */           }
/* 296 */           isbreak = true;
/* 297 */           break;
/*     */         }
/*     */       }
/*     */ 
/* 301 */       if (isbreak) {
/*     */         break;
/*     */       }
/*     */     }
/* 305 */     return columnComment;
/*     */   }
/*     */ 
/*     */   private static List<String> getPrimaryKeys(DatabaseMetaData dbMetaData, String catalogName, String tableName)
/*     */     throws Exception
/*     */   {
/* 317 */     List primarykeyList = new ArrayList();
/* 318 */     ResultSet primaryKeySet = dbMetaData.getPrimaryKeys(catalogName, null, 
/* 319 */       tableName);
/* 320 */     while (primaryKeySet.next()) {
/* 321 */       primarykeyList.add(primaryKeySet.getString("COLUMN_NAME"));
/*     */     }
/* 323 */     return primarykeyList;
/*     */   }
/*     */ 
/*     */   private static List<TableRelation> getTableRelations(DatabaseMetaData dbMetaData, String catalogName, String tableName)
/*     */     throws Exception
/*     */   {
/* 337 */     List tableRelationList = new ArrayList();
/* 338 */     ResultSet foreinKeySet = dbMetaData.getImportedKeys(catalogName, null, 
/* 339 */       tableName);
/* 340 */     while (foreinKeySet.next()) {
/* 341 */       String fKColumn_Name = foreinKeySet.getString("FKCOLUMN_NAME");
/* 342 */       String pKTable_Name = foreinKeySet.getString("PKTABLE_NAME");
/* 343 */       String fKTable_Name = foreinKeySet.getString("FKTABLE_NAME");
/* 344 */       String pKColumn_Name = foreinKeySet.getString("PKCOLUMN_NAME");
/* 345 */       tableRelationList.add(new TableRelation(fKColumn_Name, 
/* 346 */         pKTable_Name, fKTable_Name, pKColumn_Name));
/*     */     }
/* 348 */     return tableRelationList;
/*     */   }
/*     */ 
/*     */   private static List<DBTable> getTableAndColumnCommentMapping(DBConnectionConfig dbConfig)
/*     */     throws Exception
/*     */   {
/* 356 */     Connection connection = JdbcUtil.getConnection(dbConfig);
/* 357 */     String sql = null;
/* 358 */     String tableLabel = "TABLE_NAME";
/* 359 */     String tableCommentLabel = null;
/* 360 */     String columnCommentLabel = null;
/* 361 */     String databaseProductName = connection.getMetaData().getDatabaseProductName();
/* 362 */     if (databaseProductName.equalsIgnoreCase(DataBaseEnum.MYSQL.name())) {
/* 363 */       connection.setCatalog("information_schema");
/* 364 */       sql = "select * from TABLES WHERE TABLE_SCHEMA = '" + StringUtil.getDBName(dbConfig.getUrl()) + "'";
/* 365 */       tableCommentLabel = "TABLE_COMMENT";
/* 366 */       columnCommentLabel = "COLUMN_COMMENT";
/*     */     }
/* 368 */     if (databaseProductName.equalsIgnoreCase(DataBaseEnum.ORACLE.name())) {
/* 369 */       sql = "select * from user_tab_comments";
/* 370 */       tableCommentLabel = "COMMENTS";
/* 371 */       columnCommentLabel = "COMMENTS";
/*     */     }
/*     */ 
/* 374 */     PreparedStatement st = connection.prepareStatement(sql);
/*     */ 
/* 377 */     ResultSet resultSet = st.executeQuery(sql);
/* 378 */     List dbTables = new ArrayList();
/* 379 */     while (resultSet.next()) {
/* 380 */       String tableName = resultSet.getString(tableLabel);
/* 381 */       tableComment = resultSet.getString(tableCommentLabel);
/* 382 */       if (tableComment == null) {
/* 383 */         tableComment = tableName;
/*     */       }
/* 385 */       DBTable dbTable = new DBTable();
/* 386 */       dbTable.setTableName(tableName);
/* 387 */       dbTable.setTableComment(tableComment);
/* 388 */       dbTables.add(dbTable);
/*     */     }
/*     */     ResultSet colResultSet;
/* 390 */     for (String tableComment = dbTables.iterator(); tableComment.hasNext(); 
/* 398 */       colResultSet.next())
/*     */     {
/* 390 */       DBTable dbTable = (DBTable)tableComment.next();
/* 391 */       if (databaseProductName.equalsIgnoreCase(DataBaseEnum.MYSQL.name())) {
/* 392 */         sql = "select * from COLUMNS where TABLE_SCHEMA='" + StringUtil.getDBName(dbConfig.getUrl()) + "' AND TABLE_NAME='" + dbTable.getTableName() + "'";
/*     */       }
/* 394 */       if (databaseProductName.equalsIgnoreCase(DataBaseEnum.ORACLE.name())) {
/* 395 */         sql = "select * from all_col_comments where TABLE_NAME='" + dbTable.getTableName() + "'";
/*     */       }
/* 397 */       colResultSet = st.executeQuery(sql);
/* 398 */       continue;
/* 399 */       String columnName = colResultSet.getString("COLUMN_NAME");
/* 400 */       String columnComment = colResultSet.getString(columnCommentLabel);
/* 401 */       TableColumn tableColumn = new TableColumn();
/* 402 */       tableColumn.setColumnName(columnName);
/* 403 */       tableColumn.setColumnComment(columnComment);
/* 404 */       dbTable.getTableColumns().add(tableColumn);
/*     */     }
/*     */ 
/* 407 */     JdbcUtil.release(null, st, resultSet);
/* 408 */     return dbTables;
/*     */   }
/*     */ }

/* Location:           D:\MyEclipse2015CI\dropins\com.tianshouzhi.autopro_1.0.0.201506242235.jar
 * Qualified Name:     com.tianshouzhi.autopro.util.DataBaseMetaInfoUtil
 * JD-Core Version:    0.6.2
 */