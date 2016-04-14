/*    */ package test;
/*    */ 
/*    */ import com.tianshouzhi.autopro.util.FileCopyUtil;
/*    */ import java.io.File;
/*    */ 
/*    */ public class TestFileCopy
/*    */ {
/*    */   public static void main(String[] args)
/*    */     throws Exception
/*    */   {
/*  9 */     File dirFrom = new File("D:\\tmp");
/* 10 */     File dirTo = new File("D:\\temp");
/* 11 */     new FileCopyUtil().copy(dirFrom, dirTo);
/*    */   }
/*    */ }

/* Location:           D:\MyEclipse2015CI\dropins\com.tianshouzhi.autopro_1.0.0.201506242235.jar
 * Qualified Name:     test.TestFileCopy
 * JD-Core Version:    0.6.2
 */