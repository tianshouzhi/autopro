/*    */ package com.tianshouzhi.autopro.util;
/*    */ 
/*    */ import java.io.File;
/*    */ import java.io.FileInputStream;
/*    */ import java.io.FileOutputStream;
/*    */ import java.io.IOException;
/*    */ import java.io.InputStream;
/*    */ import java.io.OutputStream;
/*    */ import java.nio.channels.FileChannel;
/*    */ 
/*    */ public class FileCopyUtil
/*    */ {
/*    */   public File dirFrom;
/*    */   public File dirTo;
/*    */ 
/*    */   public void listFileInDir(File file)
/*    */     throws Exception
/*    */   {
/* 18 */     File[] files = file.listFiles();
/* 19 */     for (File f : files) {
/* 20 */       String tempfrom = f.getAbsolutePath();
/* 21 */       String tempto = tempfrom.replace(this.dirFrom.getAbsolutePath(), 
/* 22 */         this.dirTo.getAbsolutePath());
/* 23 */       if (f.isDirectory()) {
/* 24 */         File tempFile = new File(tempto);
/* 25 */         tempFile.mkdirs();
/* 26 */         listFileInDir(f);
/*    */       }
/*    */       else {
/* 29 */         int endindex = tempto.lastIndexOf("\\");
/* 30 */         String mkdirPath = tempto.substring(0, endindex);
/* 31 */         File tempFile = new File(mkdirPath);
/* 32 */         tempFile.mkdirs();
/* 33 */         copy(tempfrom, tempto);
/*    */       }
/*    */     }
/*    */   }
/*    */ 
/*    */   public void copy(String from, String to)
/*    */     throws Exception
/*    */   {
/* 42 */     InputStream in = new FileInputStream(from);
/* 43 */     OutputStream out = new FileOutputStream(to);
/*    */ 
/* 45 */     byte[] buff = new byte[1024];
/* 46 */     int len = 0;
/* 47 */     while ((len = in.read(buff)) != -1) {
/* 48 */       out.write(buff, 0, len);
/*    */     }
/* 50 */     in.close();
/* 51 */     out.close();
/*    */   }
/*    */ 
/*    */   public void copy(File dirFrom, File dirTo)
/*    */   {
/* 61 */     this.dirFrom = dirFrom;
/* 62 */     this.dirTo = dirTo;
/*    */     try {
/* 64 */       listFileInDir(dirFrom);
/*    */     } catch (Exception e) {
/* 66 */       throw new RuntimeException("文件拷贝出错", e);
/*    */     }
/*    */   }
/*    */ 
/*    */   public void fileChannelCopy(File sourceFile, File targetFile)
/*    */   {
/* 81 */     FileInputStream fi = null;
/*    */ 
/* 83 */     FileOutputStream fo = null;
/*    */ 
/* 85 */     FileChannel in = null;
/*    */ 
/* 87 */     FileChannel out = null;
/*    */     try
/*    */     {
/* 91 */       fi = new FileInputStream(sourceFile);
/*    */ 
/* 93 */       fo = new FileOutputStream(targetFile);
/*    */ 
/* 95 */       in = fi.getChannel();
/*    */ 
/* 97 */       out = fo.getChannel();
/*    */ 
/* 99 */       in.transferTo(0L, in.size(), out);
/*    */     }
/*    */     catch (IOException e)
/*    */     {
/* 103 */       throw new RuntimeException(e);
/*    */     }
/*    */     finally
/*    */     {
/*    */       try
/*    */       {
/* 109 */         fi.close();
/*    */ 
/* 111 */         in.close();
/*    */ 
/* 113 */         fo.close();
/*    */ 
/* 115 */         out.close();
/*    */       }
/*    */       catch (IOException e)
/*    */       {
/* 119 */         throw new RuntimeException(e);
/*    */       }
/*    */     }
/*    */   }
/*    */ }

/* Location:           D:\MyEclipse2015CI\dropins\com.tianshouzhi.autopro_1.0.0.201506242235.jar
 * Qualified Name:     com.tianshouzhi.autopro.util.FileCopyUtil
 * JD-Core Version:    0.6.2
 */