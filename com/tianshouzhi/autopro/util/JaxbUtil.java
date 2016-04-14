/*    */ package com.tianshouzhi.autopro.util;
/*    */ 
/*    */ import java.io.File;
/*    */ import java.io.FileOutputStream;
/*    */ import javax.xml.bind.JAXBContext;
/*    */ import javax.xml.bind.Marshaller;
/*    */ import javax.xml.bind.Unmarshaller;
/*    */ 
/*    */ public class JaxbUtil
/*    */ {
/*    */   public static void marshal(Object rootElement, String fileSaveDir, String fileName)
/*    */     throws Exception
/*    */   {
/* 20 */     JAXBContext ctx = JAXBContext.newInstance(new Class[] { rootElement.getClass() });
/* 21 */     Marshaller m = ctx.createMarshaller();
/* 22 */     m.setProperty("jaxb.formatted.output", Boolean.valueOf(true));
/*    */ 
/* 24 */     m.marshal(rootElement, System.out);
/* 25 */     FileOutputStream out = new FileOutputStream(fileSaveDir + "/" + fileName);
/* 26 */     m.marshal(rootElement, out);
/* 27 */     out.close();
/*    */   }
/*    */ 
/*    */   public static <T> T unmarshal(T rootElement, String filePath)
/*    */     throws Exception
/*    */   {
/* 40 */     JAXBContext ctx = JAXBContext.newInstance(new Class[] { rootElement.getClass() });
/* 41 */     Unmarshaller u = ctx.createUnmarshaller();
/* 42 */     Object t = u.unmarshal(new File(filePath));
/* 43 */     return t;
/*    */   }
/*    */ }

/* Location:           D:\MyEclipse2015CI\dropins\com.tianshouzhi.autopro_1.0.0.201506242235.jar
 * Qualified Name:     com.tianshouzhi.autopro.util.JaxbUtil
 * JD-Core Version:    0.6.2
 */