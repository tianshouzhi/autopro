/*   */ package test;
/*   */ 
/*   */ import java.io.PrintStream;
/*   */ 
/*   */ public class TestReplaceAll
/*   */ {
/*   */   public static void main(String[] args)
/*   */   {
/* 5 */     String regex = "tianshouzhi.wangxiaoxiao";
/* 6 */     String replaceAll = regex.replaceAll("\\.", "__");
/* 7 */     System.out.println(replaceAll);
/*   */   }
/*   */ }

/* Location:           D:\MyEclipse2015CI\dropins\com.tianshouzhi.autopro_1.0.0.201506242235.jar
 * Qualified Name:     test.TestReplaceAll
 * JD-Core Version:    0.6.2
 */