/*    */ package com.tianshouzhi.autopro.domain.enums;
/*    */ 
/*    */ public enum DataBaseEnum
/*    */ {
/*  4 */   MYSQL("com.mysql.jdbc.Driver", 
/*  5 */     "jdbc:mysql://localhost:3306/blog?useUnicode=true&characterEncoding=UTF-8", 
/*  6 */     "root", 
/*  7 */     "root"), 
/*  8 */   ORACLE("oracle.jdbc.OracleDriver", 
/*  9 */     "jdbc:oracle:thin:@localhost:1521:orcl", 
/* 10 */     "scott", 
/* 11 */     "tiger");
/*    */ 
/*    */   private String driverClassPath;
/*    */   private String sampleUrl;
/*    */   private String defaultUser;
/*    */   private String defaultPass;
/*    */ 
/* 15 */   private DataBaseEnum(String driverClassPath, String sampleUrl, String defaultUser, String defaultPass) { this.driverClassPath = driverClassPath;
/* 16 */     this.sampleUrl = sampleUrl;
/* 17 */     this.defaultUser = defaultUser;
/* 18 */     this.defaultPass = defaultPass;
/*    */   }
/*    */ 
/*    */   public String getDriverClassPath()
/*    */   {
/* 31 */     return this.driverClassPath;
/*    */   }
/*    */   public String getSampleUrl() {
/* 34 */     return this.sampleUrl;
/*    */   }
/*    */ 
/*    */   public String getDefaultUser()
/*    */   {
/* 41 */     return this.defaultUser;
/*    */   }
/*    */ 
/*    */   public String getDefaultPass()
/*    */   {
/* 48 */     return this.defaultPass;
/*    */   }
/*    */ 
/*    */   public static DataBaseEnum getDefault()
/*    */   {
/* 54 */     return MYSQL;
/*    */   }
/*    */ 
/*    */   public static String[] getSuppDbNames()
/*    */   {
/* 61 */     DataBaseEnum[] values = values();
/* 62 */     String[] dbNames = new String[values.length];
/* 63 */     int i = 0;
/* 64 */     DataBaseEnum[] arrayOfDataBaseEnum1 = values; int j = values.length; for (int i = 0; i < j; i++) { DataBaseEnum dataBaseEnum = arrayOfDataBaseEnum1[i];
/* 65 */       dbNames[i] = dataBaseEnum.name();
/* 66 */       i++;
/*    */     }
/* 68 */     return dbNames;
/*    */   }
/*    */ 
/*    */   public static DataBaseEnum getDataBaseByName(String name)
/*    */   {
/* 75 */     for (DataBaseEnum dataBaseEnum : values()) {
/* 76 */       if (dataBaseEnum.name().equalsIgnoreCase(name)) {
/* 77 */         return dataBaseEnum;
/*    */       }
/*    */     }
/* 80 */     return null;
/*    */   }
/*    */ }

/* Location:           D:\MyEclipse2015CI\dropins\com.tianshouzhi.autopro_1.0.0.201506242235.jar
 * Qualified Name:     com.tianshouzhi.autopro.domain.enums.DataBaseEnum
 * JD-Core Version:    0.6.2
 */