/*    */ package com.tianshouzhi.autopro.util;
/*    */ 
/*    */ import com.tianshouzhi.autopro.domain.maven.PomBean;
/*    */ import java.util.HashMap;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ import org.eclipse.core.resources.IProject;
/*    */ import org.eclipse.core.runtime.IPath;
/*    */ import org.eclipse.jdt.core.IJavaProject;
/*    */ 
/*    */ public class MessageHub
/*    */ {
/* 17 */   private static Map<String, Object> map = new HashMap();
/*    */ 
/* 19 */   public static void put(String key, Object value) { map.put(key, value); }
/*    */ 
/*    */   public static Object get(String key)
/*    */   {
/* 23 */     return map.get(key);
/*    */   }
/*    */ 
/*    */   public static void init(IJavaProject javaProject)
/*    */   {
/* 47 */     String path = javaProject.getProject().getLocation().toOSString() + "/pom.xml";
/* 48 */     String n_groupId = "groupId";
/* 49 */     String n_artifactId = "artifactId";
/* 50 */     String v_groupId = null;
/* 51 */     String v_artifactId = null;
/* 52 */     Map resultMap = XmlUtil.getElementTextContent(path, new String[] { n_groupId, n_artifactId });
/* 53 */     if ((resultMap != null) && (resultMap.size() > 0)) {
/* 54 */       v_groupId = (String)((List)resultMap.get(n_groupId)).get(0);
/* 55 */       v_artifactId = (String)((List)resultMap.get(n_artifactId)).get(0);
/*    */     }
/* 57 */     PomBean pomBean = new PomBean();
/* 58 */     pomBean.setGroupId(v_groupId);
/* 59 */     pomBean.setArtifactId(v_artifactId);
/*    */ 
/* 61 */     map.put("O_POM_BEAN", pomBean);
/* 62 */     map.put("BASE_PACKAGE", v_groupId + "." + v_artifactId);
/* 63 */     map.put("PROJECT_NAME", javaProject.getProject().getName());
/* 64 */     map.put("PROJECT_FS_PATH", javaProject.getProject().getLocation().toOSString());
/*    */   }
/*    */ 
/*    */   public static void release() {
/* 68 */     map.clear();
/*    */   }
/*    */ 
/*    */   public static class Keys
/*    */   {
/*    */     public static final String O_ORIGIN_TABLES = "ORIGIN_TABLES";
/*    */     public static final String List_DBTable_SELECTED = "SELECTED_TABLES";
/*    */     public static final String String_BASE_PACKAGE = "BASE_PACKAGE";
/*    */     public static final String Object_DBConnectionConfig = "DB_CONNECTION_CONFIG";
/*    */     public static final String String_PROJECT_NAME = "PROJECT_NAME";
/*    */     public static final String String_PROJECT_FS_PATH = "PROJECT_FS_PATH";
/*    */     public static final String List_TableFunction_SELECTED = "L_SELECTED_FUNCTIONS";
/*    */     public static final String Object_PomBean = "O_POM_BEAN";
/*    */     public static final String List_MavenDependency = "List_MavenDependency";
/*    */   }
/*    */ }

/* Location:           D:\MyEclipse2015CI\dropins\com.tianshouzhi.autopro_1.0.0.201506242235.jar
 * Qualified Name:     com.tianshouzhi.autopro.util.MessageHub
 * JD-Core Version:    0.6.2
 */