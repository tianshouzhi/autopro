/*     */ package com.tianshouzhi.autopro.util;
/*     */ 
/*     */ import freemarker.cache.FileTemplateLoader;
/*     */ import freemarker.cache.MultiTemplateLoader;
/*     */ import freemarker.cache.TemplateLoader;
/*     */ import freemarker.template.Configuration;
/*     */ import freemarker.template.Template;
/*     */ import freemarker.template.TemplateException;
/*     */ import java.io.File;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.OutputStream;
/*     */ import java.io.StringWriter;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Date;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Locale;
/*     */ import java.util.Map;
/*     */ import org.apache.log4j.Logger;
/*     */ 
/*     */ public class FreemarkerUtil
/*     */ {
/*  35 */   private static final Logger logger = Logger.getLogger(FreemarkerUtil.class);
/*     */   public static Configuration configuration;
/*  38 */   private static String roottemplatesDir = "templates";
/*  39 */   public static File rootdir = null;
/*  40 */   public static List<TemplateLoader> templateLoaders = new ArrayList();
/*     */ 
/*     */   static {
/*     */     try {
/*  44 */       configuration = new Configuration();
/*  45 */       configuration.setEncoding(Locale.CHINA, "UTF-8");
/*     */ 
/*  48 */       configuration.setNumberFormat("#.##");
/*  49 */       configuration.setLocale(Locale.CHINA);
/*  50 */       configuration.setDateTimeFormat("yyyy-MM-dd HH:mm:ss");
/*     */ 
/*  52 */       rootdir = PluginUtil.getResourceFile(roottemplatesDir);
/*     */ 
/*  56 */       configuration.setTemplateLoader(new MultiTemplateLoader(getLoaders()));
/*     */     } catch (Exception e) {
/*  58 */       logger.error("FreeMarker初始化错误！--->" + rootdir.getAbsolutePath(), e);
/*     */     }
/*     */   }
/*     */ 
/*     */   public static String template2BufferdHtml(String templateName, Map<String, Object> params)
/*     */     throws TemplateException, IOException
/*     */   {
/*  77 */     StringWriter result = null;
/*  78 */     Template template = configuration.getTemplate(templateName);
/*  79 */     result = new StringWriter();
/*  80 */     template.process(params, result);
/*     */ 
/*  82 */     String content = result.toString();
/*  83 */     return content;
/*     */   }
/*     */ 
/*     */   private static TemplateLoader[] getLoaders() throws IOException {
/*  87 */     initTemplateLoaders(rootdir);
/*  88 */     TemplateLoader[] loaders = new TemplateLoader[templateLoaders.size()];
/*  89 */     int i = 0;
/*  90 */     for (TemplateLoader templateLoader : templateLoaders) {
/*  91 */       loaders[i] = templateLoader;
/*  92 */       i++;
/*     */     }
/*  94 */     return loaders;
/*     */   }
/*     */ 
/*     */   private static void initTemplateLoaders(File rootdir) throws IOException {
/*  98 */     if (rootdir.isDirectory()) {
/*  99 */       templateLoaders.add(new FileTemplateLoader(rootdir));
/*     */     }
/* 101 */     File[] listFiles = rootdir.listFiles();
/* 102 */     if ((listFiles != null) && (listFiles.length > 0))
/* 103 */       for (File file : listFiles)
/* 104 */         if (file.isDirectory()) {
/* 105 */           templateLoaders.add(new FileTemplateLoader(file));
/* 106 */           initTemplateLoaders(file);
/*     */         }
/*     */   }
/*     */ 
/*     */   public static String createTemplate(String storepath, String templateName, String content)
/*     */     throws IOException
/*     */   {
/* 125 */     File file = new File(storepath + "/" + templateName);
/* 126 */     OutputStream outputStream = new FileOutputStream(file);
/* 127 */     outputStream.write(content.getBytes());
/* 128 */     outputStream.flush();
/* 129 */     outputStream.close();
/* 130 */     return file.getAbsolutePath();
/*     */   }
/*     */ 
/*     */   public static String createTemplate(String content, String savePath)
/*     */     throws IOException
/*     */   {
/* 144 */     File file = new File(savePath);
/* 145 */     OutputStream outputStream = new FileOutputStream(file);
/* 146 */     outputStream.write(content.getBytes());
/* 147 */     outputStream.flush();
/* 148 */     outputStream.close();
/* 149 */     return file.getAbsolutePath();
/*     */   }
/*     */ 
/*     */   public static String getTemplateContent(String templateName)
/*     */     throws IOException
/*     */   {
/* 161 */     Template template = configuration.getTemplate(templateName);
/* 162 */     return template.toString();
/*     */   }
/*     */ 
/*     */   public static void generateFile(String templateName, Map<String, Object> data, String savePath)
/*     */   {
/*     */     try
/*     */     {
/* 183 */       File dir = new File(savePath.substring(0, savePath.lastIndexOf("/")));
/* 184 */       if (!dir.exists()) {
/* 185 */         dir.mkdirs();
/*     */       }
/* 187 */       File statichtml = new File(savePath);
/* 188 */       String content = template2BufferdHtml(templateName, data);
/* 189 */       OutputStream outputStream = new FileOutputStream(statichtml);
/* 190 */       outputStream.write(content.getBytes("UTF-8"));
/* 191 */       outputStream.close();
/*     */     } catch (Exception e) {
/* 193 */       logger.error("根据模板" + templateName + "生成文件失败", e);
/* 194 */       throw new RuntimeException(e);
/*     */     }
/*     */   }
/*     */ 
/*     */   public static void generateMavenJavaFile(String templateName, Map<String, Object> data, String entityPakcage, String fileName)
/*     */   {
/* 207 */     String basePath = (String)MessageHub.get("PROJECT_FS_PATH");
/* 208 */     String basePackage = (String)MessageHub.get("BASE_PACKAGE");
/*     */ 
/* 210 */     String subPath = "/src/main/java/" + new StringBuilder(String.valueOf(basePackage)).append(".").append(entityPakcage).append(".").toString().replaceAll("\\.", "/");
/* 211 */     String savePath = basePath + subPath + fileName;
/* 212 */     String author = System.getProperty("user.name", "");
/* 213 */     Date createDate = new Date();
/* 214 */     if (data == null) {
/* 215 */       data = new HashMap();
/*     */     }
/* 217 */     data.put("author", author);
/* 218 */     data.put("createDate", createDate);
/* 219 */     data.put("package", basePackage + "." + entityPakcage);
/* 220 */     data.put("basePackage", basePackage);
/* 221 */     generateFile(templateName, data, savePath);
/*     */   }
/*     */ 
/*     */   public static void generateMavenConfigFile(String templateName, Map<String, Object> data, String subFSPath, String fileName)
/*     */   {
/* 231 */     String basePath = (String)MessageHub.get("PROJECT_FS_PATH");
/* 232 */     String subPath = "/src/main/resources";
/* 233 */     subPath = correctPath(subFSPath, subPath);
/* 234 */     String savePath = basePath + subPath + fileName;
/* 235 */     String basePackage = (String)MessageHub.get("BASE_PACKAGE");
/* 236 */     if (data != null) {
/* 237 */       data.put("basePackage", basePackage);
/*     */     }
/*     */ 
/* 240 */     generateFile(templateName, data, savePath);
/*     */   }
/*     */ 
/*     */   public static void generateMavenWebFile(String templateName, Map<String, Object> data, String subFSPath, String fileName)
/*     */   {
/* 247 */     String basePath = (String)MessageHub.get("PROJECT_FS_PATH");
/* 248 */     String subPath = "/src/main/webapp";
/* 249 */     subPath = correctPath(subFSPath, subPath);
/*     */ 
/* 251 */     String savePath = basePath + subPath + fileName;
/* 252 */     generateFile(templateName, data, savePath);
/*     */   }
/*     */ 
/*     */   private static String correctPath(String subFSPath, String subPath) {
/* 256 */     if (StringUtil.isEmpty(subFSPath)) {
/* 257 */       subFSPath = "/";
/*     */     }
/* 259 */     if (!subFSPath.startsWith("/")) {
/* 260 */       subFSPath = "/" + subFSPath;
/*     */     }
/* 262 */     if (!subFSPath.endsWith("/")) {
/* 263 */       subFSPath = subFSPath + "/";
/*     */     }
/* 265 */     subPath = subPath + subFSPath;
/* 266 */     return subPath;
/*     */   }
/*     */ }

/* Location:           D:\MyEclipse2015CI\dropins\com.tianshouzhi.autopro_1.0.0.201506242235.jar
 * Qualified Name:     com.tianshouzhi.autopro.util.FreemarkerUtil
 * JD-Core Version:    0.6.2
 */