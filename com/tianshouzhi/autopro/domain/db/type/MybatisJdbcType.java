/*    */ package com.tianshouzhi.autopro.domain.db.type;
/*    */ 
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ 
/*    */ public enum MybatisJdbcType
/*    */ {
/* 23 */   ARRAY(
/* 27 */     2003), 
/* 28 */   BIT(-7), 
/* 29 */   TINYINT(-6), 
/* 30 */   SMALLINT(5), 
/* 31 */   INTEGER(4), 
/* 32 */   BIGINT(-5), 
/* 33 */   FLOAT(6), 
/* 34 */   REAL(7), 
/* 35 */   DOUBLE(8), 
/* 36 */   NUMERIC(2), 
/* 37 */   DECIMAL(3), 
/* 38 */   CHAR(1), 
/* 39 */   VARCHAR(12), 
/* 40 */   LONGVARCHAR(-1), 
/* 41 */   DATE(91), 
/* 42 */   TIME(92), 
/* 43 */   TIMESTAMP(93), 
/* 44 */   BINARY(-2), 
/* 45 */   VARBINARY(-3), 
/* 46 */   LONGVARBINARY(-4), 
/* 47 */   NULL(0), 
/* 48 */   OTHER(1111), 
/* 49 */   BLOB(2004), 
/* 50 */   CLOB(2005), 
/* 51 */   BOOLEAN(16), 
/* 52 */   CURSOR(-10), 
/* 53 */   UNDEFINED(-2147482648), 
/* 54 */   NVARCHAR(-9), 
/* 55 */   NCHAR(-15), 
/* 56 */   NCLOB(2011), 
/* 57 */   STRUCT(2002);
/*    */ 
/*    */   public final int TYPE_CODE;
/*    */   private static Map<Integer, MybatisJdbcType> codeLookup;
/*    */ 
/* 60 */   static { codeLookup = new HashMap();
/*    */ 
/* 63 */     for (MybatisJdbcType type : values())
/* 64 */       codeLookup.put(Integer.valueOf(type.TYPE_CODE), type);
/*    */   }
/*    */ 
/*    */   private MybatisJdbcType(int code)
/*    */   {
/* 69 */     this.TYPE_CODE = code;
/*    */   }
/*    */ 
/*    */   public static MybatisJdbcType forCode(int code) {
/* 73 */     return (MybatisJdbcType)codeLookup.get(Integer.valueOf(code));
/*    */   }
/*    */ }

/* Location:           D:\MyEclipse2015CI\dropins\com.tianshouzhi.autopro_1.0.0.201506242235.jar
 * Qualified Name:     com.tianshouzhi.autopro.domain.db.type.MybatisJdbcType
 * JD-Core Version:    0.6.2
 */