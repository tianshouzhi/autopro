/*    */ package com.tianshouzhi.autopro.util;
/*    */ 
/*    */ public class StringUtil
/*    */ {
/*    */   public static boolean isEmpty(String str)
/*    */   {
/*  5 */     if ((str != null) && (str.trim().length() > 0)) {
/*  6 */       return false;
/*    */     }
/*  8 */     return true;
/*    */   }
/*    */ 
/*    */   public static String getDBName(String dburl) {
/* 12 */     int beginIndex = dburl.lastIndexOf("/") + 1;
/* 13 */     int endIndex = dburl.indexOf("?");
/* 14 */     if (endIndex < 1) {
/* 15 */       endIndex = dburl.length();
/*    */     }
/* 17 */     String dbName = dburl.substring(beginIndex, endIndex);
/* 18 */     return dbName;
/*    */   }
/*    */ 
/*    */   public static String initCap(String str) {
/* 22 */     char c = str.charAt(0);
/* 23 */     String regex = new String(new char[] { c });
/* 24 */     String replacement = regex.toUpperCase();
/*    */ 
/* 26 */     return str.replaceFirst(regex, replacement);
/*    */   }
/*    */ 
/*    */   public static String getCamelName(String str) {
/* 30 */     return getCamelName(str, "_");
/*    */   }
/*    */ 
/*    */   public static String getCamelName(String str, String regex)
/*    */   {
/* 39 */     if (regex == null) {
/* 40 */       return str;
/*    */     }
/* 42 */     if (isEmpty(str)) {
/* 43 */       return str;
/*    */     }
/* 45 */     if (str.indexOf(regex) < 0) {
/* 46 */       if ((str.toUpperCase().equals(str)) || (initCap(str).equals(str))) {
/* 47 */         str = str.toLowerCase();
/*    */       }
/* 49 */       return str;
/*    */     }
/* 51 */     if (str.indexOf(regex) == 1) {
/* 52 */       str = str.substring(2, str.length());
/*    */     }
/*    */ 
/* 55 */     String[] splits = str.split(regex);
/* 56 */     if (splits.length >= 3) {
/* 57 */       splits = new String[] { splits[(splits.length - 2)], splits[(splits.length - 1)] };
/*    */     }
/* 59 */     StringBuffer sb = new StringBuffer();
/* 60 */     for (int i = 0; i < splits.length; i++) {
/* 61 */       if (i == 0)
/* 62 */         splits[i] = splits[i].toLowerCase();
/*    */       else {
/* 64 */         splits[i] = initCap(splits[i].toLowerCase());
/*    */       }
/* 66 */       sb.append(splits[i]);
/*    */     }
/*    */ 
/* 69 */     return sb.toString();
/*    */   }
/*    */ }

/* Location:           D:\MyEclipse2015CI\dropins\com.tianshouzhi.autopro_1.0.0.201506242235.jar
 * Qualified Name:     com.tianshouzhi.autopro.util.StringUtil
 * JD-Core Version:    0.6.2
 */