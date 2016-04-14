/*     */ package com.tianshouzhi.autopro.util;
/*     */ 
/*     */ import com.tianshouzhi.autopro.domain.enums.MvcFrameworkEnum;
/*     */ import com.tianshouzhi.autopro.domain.enums.OrmFrameworkEnum;
/*     */ import com.tianshouzhi.autopro.domain.maven.MavenDependency;
/*     */ import java.io.File;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import org.apache.log4j.Logger;
/*     */ import org.w3c.dom.Document;
/*     */ import org.w3c.dom.Node;
/*     */ import org.w3c.dom.NodeList;
/*     */ 
/*     */ public class MavenDependcyUtil
/*     */ {
/*  22 */   private static final Logger logger = Logger.getLogger(MavenDependcyUtil.class);
/*     */ 
/*     */   public static List<MavenDependency> getOrmDependencies(String ormName, String ormVersion) {
/*  25 */     List mavenDependencies = new ArrayList();
/*     */ 
/*  27 */     mavenDependencies.add(new MavenDependency("com.mchange", "c3p0", "0.9.5"));
/*     */ 
/*  29 */     mavenDependencies.add(new MavenDependency("mysql", "mysql-connector-java", "5.1.26"));
/*  30 */     if (OrmFrameworkEnum.MYBATIS.name().equalsIgnoreCase(ormName))
/*     */     {
/*  32 */       mavenDependencies.add(new MavenDependency("org.mybatis", "mybatis", ormVersion));
/*     */ 
/*  34 */       mavenDependencies.add(new MavenDependency("org.mybatis", "mybatis-spring", "1.1.1"));
/*  35 */     } else if (OrmFrameworkEnum.IBATIS.name().equalsIgnoreCase(ormName)) {
/*  36 */       mavenDependencies.add(new MavenDependency("org.ibatis", "ibatis", ormVersion));
/*     */ 
/*  38 */       mavenDependencies.add(new MavenDependency("org.ibatis", "ibatis-spring", "1.1.1"));
/*     */     }
/*     */ 
/*  41 */     return mavenDependencies;
/*     */   }
/*     */ 
/*     */   public static List<MavenDependency> getServiceDependencies(String serviceName, String serviceVersion) {
/*  45 */     List mavenDependencies = new ArrayList();
/*     */ 
/*  47 */     mavenDependencies.add(new MavenDependency("org.springframework", "spring-context", serviceVersion));
/*  48 */     mavenDependencies.add(new MavenDependency("org.springframework", "spring-test", serviceVersion));
/*  49 */     mavenDependencies.add(new MavenDependency("junit", "junit", "4.9", "test"));
/*  50 */     mavenDependencies.add(new MavenDependency("commons-logging", "commons-logging-api", "1.1"));
/*  51 */     return mavenDependencies;
/*     */   }
/*     */ 
/*     */   public static List<MavenDependency> getMvcDependencies(String mvcName, String mvcVersion) {
/*  55 */     List mavenDependencies = new ArrayList();
/*     */ 
/*  57 */     mavenDependencies.add(new MavenDependency("javax.servlet", "servlet-api", "2.5", "provided"));
/*  58 */     mavenDependencies.add(new MavenDependency("javax.servlet.jsp", "jsp-api", "2.2", "provided"));
/*  59 */     mavenDependencies.add(new MavenDependency("javax.servlet.jsp.jstl", "jstl-api", "1.2", "provided"));
/*  60 */     mavenDependencies.add(new MavenDependency("com.google.code.gson", "gson", "2.3"));
/*  61 */     mavenDependencies.add(new MavenDependency("commons-beanutils", "commons-beanutils", "1.9.1"));
/*  62 */     if (MvcFrameworkEnum.SPRINGMVC.name().equalsIgnoreCase(mvcName)) {
/*  63 */       mavenDependencies.add(new MavenDependency("org.hibernate", "hibernate-validator", "4.3.1.Final"));
/*  64 */       mavenDependencies.add(new MavenDependency("org.jboss.logging", "jboss-logging-log4j", "2.2.0.CR1"));
/*  65 */       mavenDependencies.add(new MavenDependency("org.springframework", "spring-webmvc", mvcVersion));
/*     */     }
/*  67 */     MvcFrameworkEnum.STRUTS2.name().equalsIgnoreCase(mvcName);
/*     */ 
/*  69 */     return mavenDependencies;
/*     */   }
/*     */ 
/*     */   public static List<MavenDependency> getApachePoiDependencies(String framework, String version) {
/*  73 */     List mavenDependencies = new ArrayList();
/*     */ 
/*  75 */     mavenDependencies.add(new MavenDependency("org.apache.poi", "poi", "3.10-FINAL"));
/*  76 */     mavenDependencies.add(new MavenDependency("org.apache.poi", "ooxml-schemas", "1.0"));
/*  77 */     mavenDependencies.add(new MavenDependency("org.apache.poi", "poi-ooxml", "3.10-FINAL"));
/*  78 */     return mavenDependencies;
/*     */   }
/*     */ 
/*     */   public static List<MavenDependency> getHighChartsDenpendcy() {
/*  82 */     List mavenDependencies = new ArrayList();
/*  83 */     String groudId = "com.autopro";
/*  84 */     String artifactId = "highcharts";
/*  85 */     String version = "1.0";
/*  86 */     String jarFilePath = "lib/highcharts-1.0.jar";
/*  87 */     install2Repo(jarFilePath, groudId, artifactId, version);
/*  88 */     mavenDependencies.add(new MavenDependency(groudId, artifactId, version));
/*  89 */     return mavenDependencies;
/*     */   }
/*     */ 
/*     */   public static void install2Repo(String jarFilePath, String groupId, String artifactId, String version)
/*     */   {
/* 100 */     String repoPath = getRepoPath();
/* 101 */     logger.info("Maven仓库位置:" + repoPath);
/* 102 */     if (!StringUtil.isEmpty(repoPath)) {
/* 103 */       String artifactDirPath = repoPath + "/" + groupId.replace(".", "/") + "/" + artifactId + "/" + version;
/* 104 */       File artifactDir = new File(artifactDirPath);
/* 105 */       if (!artifactDir.exists()) {
/* 106 */         artifactDir.mkdirs();
/*     */       }
/* 108 */       File sourceFile = PluginUtil.getResourceFile(jarFilePath);
/* 109 */       String pathname = artifactDirPath + "/" + artifactId + "-" + version + ".jar";
/* 110 */       File targetFile = new File(pathname);
/* 111 */       logger.info("插件安装位置:" + pathname);
/* 112 */       if (!targetFile.exists()) {
/* 113 */         new FileCopyUtil().fileChannelCopy(sourceFile, targetFile);
/* 114 */         logger.info("插件安装成功！");
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   public static String getRepoPath()
/*     */   {
/* 125 */     String userhome = System.getProperty("user.home");
/* 126 */     String settingsFilePath = userhome + "/" + ".m2" + "/" + "settings.xml";
/* 127 */     File settingsxml = new File(settingsFilePath);
/* 128 */     boolean exists = settingsxml.exists();
/* 129 */     Document document = null;
/* 130 */     NodeList nodeList = null;
/* 131 */     if (exists) {
/* 132 */       document = XmlUtil.read(settingsFilePath);
/* 133 */       nodeList = document.getElementsByTagName("localRepository");
/*     */     } else {
/* 135 */       String maven_home = System.getenv("MAVEN_HOME");
/* 136 */       if (!StringUtil.isEmpty(maven_home)) {
/* 137 */         settingsFilePath = maven_home + "/" + "conf" + "/" + "settings.xml";
/* 138 */         document = XmlUtil.read(settingsFilePath);
/* 139 */         nodeList = document.getElementsByTagName("localRepository");
/*     */       }
/*     */     }
/* 142 */     String repoPath = "";
/* 143 */     if ((nodeList != null) && (nodeList.getLength() > 0)) {
/* 144 */       Node repoNode = nodeList.item(0);
/* 145 */       repoPath = repoNode.getTextContent();
/*     */     }
/*     */ 
/* 148 */     return repoPath;
/*     */   }
/*     */ }

/* Location:           D:\MyEclipse2015CI\dropins\com.tianshouzhi.autopro_1.0.0.201506242235.jar
 * Qualified Name:     com.tianshouzhi.autopro.util.MavenDependcyUtil
 * JD-Core Version:    0.6.2
 */