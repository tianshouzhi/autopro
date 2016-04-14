/*    */ package test;
/*    */ 
/*    */ import java.io.File;
/*    */ import java.io.PrintStream;
/*    */ import javax.xml.parsers.DocumentBuilder;
/*    */ import javax.xml.parsers.DocumentBuilderFactory;
/*    */ import org.w3c.dom.Document;
/*    */ import org.w3c.dom.Node;
/*    */ import org.w3c.dom.NodeList;
/*    */ 
/*    */ public class TestDotFile
/*    */ {
/*    */   public static void main(String[] args)
/*    */     throws Exception
/*    */   {
/* 17 */     String userHome = System.getProperty("user.home");
/* 18 */     String filePath = userHome + File.separator + ".m2" + File.separator + "settings.xml";
/*    */ 
/* 29 */     DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
/* 30 */     DocumentBuilder builder = builderFactory.newDocumentBuilder();
/* 31 */     Document document = builder.parse(filePath);
/* 32 */     System.out.println(document.getElementsByTagName("localRepository").item(0).getTextContent());
/*    */   }
/*    */ }

/* Location:           D:\MyEclipse2015CI\dropins\com.tianshouzhi.autopro_1.0.0.201506242235.jar
 * Qualified Name:     test.TestDotFile
 * JD-Core Version:    0.6.2
 */