/*    */ package com.tianshouzhi.autopro.util;
/*    */ 
/*    */ import com.tianshouzhi.autopro.domain.db.DBConnectionConfig;
/*    */ import com.tianshouzhi.autopro.domain.enums.DataBaseEnum;
/*    */ import java.sql.Connection;
/*    */ import java.sql.DriverManager;
/*    */ import java.sql.ResultSet;
/*    */ import java.sql.Statement;
/*    */ 
/*    */ public class JdbcUtil
/*    */ {
/*    */   public static Connection getConnection(String url, String username, String password)
/*    */     throws Exception
/*    */   {
/* 16 */     return DriverManager.getConnection(url, username, password);
/*    */   }
/*    */   public static Connection getConnection(DBConnectionConfig dbConfig) {
/* 19 */     Connection connection = null;
/*    */     try {
/* 21 */       DataBaseEnum dataBase = DataBaseEnum.getDataBaseByName(dbConfig.getDb_type());
/* 22 */       Class.forName(dataBase.getDriverClassPath());
/* 23 */       connection = getConnection(dbConfig.getUrl(), dbConfig.getUsername(), dbConfig.getPassword());
/*    */     } catch (Exception localException) {
/* 25 */       connection = null;
/*    */     }
/* 27 */     return connection;
/*    */   }
/*    */ 
/*    */   public static void release(Connection connection, Statement st, ResultSet rs)
/*    */   {
/* 32 */     if (rs != null) {
/*    */       try {
/* 34 */         rs.close();
/*    */       } catch (Exception e) {
/* 36 */         e.printStackTrace();
/*    */       }
/* 38 */       rs = null;
/*    */     }
/*    */ 
/* 41 */     if (st != null) {
/*    */       try {
/* 43 */         st.close();
/*    */       } catch (Exception e) {
/* 45 */         e.printStackTrace();
/*    */       }
/*    */ 
/*    */     }
/*    */ 
/* 50 */     if (connection != null)
/*    */       try {
/* 52 */         connection.close();
/*    */       } catch (Exception e) {
/* 54 */         e.printStackTrace();
/*    */       }
/*    */   }
/*    */ 
/*    */   public static boolean checkDBConnectionConfig(DBConnectionConfig dbConnectionConfig)
/*    */   {
/* 65 */     Connection connection = getConnection(dbConnectionConfig);
/* 66 */     return connection != null;
/*    */   }
/*    */ }

/* Location:           D:\MyEclipse2015CI\dropins\com.tianshouzhi.autopro_1.0.0.201506242235.jar
 * Qualified Name:     com.tianshouzhi.autopro.util.JdbcUtil
 * JD-Core Version:    0.6.2
 */