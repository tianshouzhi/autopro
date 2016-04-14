/*    */ package com.tianshouzhi.autopro.util;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.HashMap;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ import javax.xml.parsers.DocumentBuilder;
/*    */ import javax.xml.parsers.DocumentBuilderFactory;
/*    */ import org.apache.log4j.Logger;
/*    */ import org.w3c.dom.Document;
/*    */ import org.w3c.dom.Node;
/*    */ import org.w3c.dom.NodeList;
/*    */ 
/*    */ public class XmlUtil
/*    */ {
/* 20 */   private static final Logger logger = Logger.getLogger(XmlUtil.class);
/*    */ 
/*    */   public static Document read(String path)
/*    */   {
/* 28 */     DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
/*    */     try
/*    */     {
/* 31 */       DocumentBuilder builder = factory.newDocumentBuilder();
/* 32 */       document = builder.parse(path);
/*    */     }
/*    */     catch (Exception e)
/*    */     {
/*    */       Document document;
/* 34 */       logger.error("解析XML文件出错");
/* 35 */       throw new RuntimeException(e);
/*    */     }
/*    */     Document document;
/* 37 */     return document;
/*    */   }
/*    */ 
/*    */   public static Map<String, List<String>> getElementTextContent(String path, String[] tagNames)
/*    */   {
/* 46 */     Map resultMap = null;
/* 47 */     if ((!StringUtil.isEmpty(path)) && (tagNames != null) && (tagNames.length > 0)) {
/* 48 */       Document document = read(path);
/* 49 */       resultMap = new HashMap();
/* 50 */       for (String tagName : tagNames) {
/* 51 */         NodeList nodeList = document.getElementsByTagName(tagName);
/* 52 */         if (nodeList.getLength() >= 1) {
/* 53 */           List textContents = new ArrayList();
/* 54 */           for (int i = 0; i < nodeList.getLength(); i++) {
/* 55 */             Node item = nodeList.item(i);
/* 56 */             String textContent = item.getTextContent();
/* 57 */             textContents.add(textContent);
/*    */           }
/* 59 */           resultMap.put(tagName, textContents);
/*    */         }
/*    */       }
/*    */     }
/* 63 */     return resultMap;
/*    */   }
/*    */ 
/*    */   public static String getBasePackage()
/*    */   {
/*    */     try {
/* 69 */       String path = (String)MessageHub.get("PROJECT_FS_PATH") + "/pom.xml"; String groupId = "groupId"; String artifactId = "artifactId"; Map resultMap = getElementTextContent(path, new String[] { groupId, artifactId }); groupId = (String)((List)resultMap.get(groupId)).get(0); artifactId = (String)((List)resultMap.get(artifactId)).get(0); return groupId + "." + artifactId; } catch (Exception e) { throw new RuntimeException("读取pom.xml文件失败", e); }
/*    */ 
/*    */   }
/*    */ 
/*    */   public static String getPomGroupId()
/*    */   {
/* 84 */     String path = (String)MessageHub.get("PROJECT_FS_PATH") + "/pom.xml"; String groupId = "groupId"; String artifactId = "artifactId"; Map resultMap = getElementTextContent(path, new String[] { groupId, artifactId }); groupId = (String)((List)resultMap.get(groupId)).get(0); return groupId;
/*    */   }
/*    */ 
/*    */   public static String getPomArtifactId()
/*    */   {
/* 95 */     String path = (String)MessageHub.get("PROJECT_FS_PATH") + "/pom.xml"; String groupId = "groupId"; String artifactId = "artifactId"; Map resultMap = getElementTextContent(path, new String[] { groupId, artifactId }); artifactId = (String)((List)resultMap.get(artifactId)).get(0); return artifactId;
/*    */   }
/*    */ }

/* Location:           D:\MyEclipse2015CI\dropins\com.tianshouzhi.autopro_1.0.0.201506242235.jar
 * Qualified Name:     com.tianshouzhi.autopro.util.XmlUtil
 * JD-Core Version:    0.6.2
 */