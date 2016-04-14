/*    */ package test;
/*    */ 
/*    */ import java.io.PrintStream;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ 
/*    */ public class TestList
/*    */ {
/*    */   public static void main(String[] args)
/*    */   {
/*  8 */     List list = new ArrayList();
/*  9 */     String str = "hello";
/* 10 */     list.add(str);
/* 11 */     str = "world";
/* 12 */     list.set(0, "caca");
/* 13 */     System.out.println(list.size() + ":" + (String)list.get(0));
/*    */   }
/*    */ }

/* Location:           D:\MyEclipse2015CI\dropins\com.tianshouzhi.autopro_1.0.0.201506242235.jar
 * Qualified Name:     test.TestList
 * JD-Core Version:    0.6.2
 */