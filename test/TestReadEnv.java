/*   */ package test;
/*   */ 
/*   */ import java.io.PrintStream;
/*   */ 
/*   */ public class TestReadEnv
/*   */ {
/*   */   public static void main(String[] args)
/*   */   {
/* 5 */     String classpath = System.getenv("CLASSPATH");
/* 6 */     String java_home = System.getenv("JAVA_HOME");
/* 7 */     String maven_home = System.getenv("MAVEN_HOME");
/* 8 */     String user_dir = System.getProperty("user.home");
/* 9 */     System.out.println(classpath + "\n" + java_home + "\n" + maven_home + "\n" + user_dir);
/*   */   }
/*   */ }

/* Location:           D:\MyEclipse2015CI\dropins\com.tianshouzhi.autopro_1.0.0.201506242235.jar
 * Qualified Name:     test.TestReadEnv
 * JD-Core Version:    0.6.2
 */