/*    */ package com.tianshouzhi.autopro.service.code.jsp;
/*    */ 
/*    */ import com.tianshouzhi.autopro.domain.db.DBTable;
/*    */ import com.tianshouzhi.autopro.domain.functions.ExcelFunction;
/*    */ import com.tianshouzhi.autopro.util.Constant.TemplateNames;
/*    */ import com.tianshouzhi.autopro.util.CustomFKMethodUseHtmlEditor;
/*    */ import com.tianshouzhi.autopro.util.FileCopyUtil;
/*    */ import com.tianshouzhi.autopro.util.FreemarkerUtil;
/*    */ import com.tianshouzhi.autopro.util.MessageHub;
/*    */ import java.io.File;
/*    */ import java.net.URL;
/*    */ import java.util.HashMap;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ import org.apache.log4j.Logger;
/*    */ import org.eclipse.core.runtime.FileLocator;
/*    */ import org.eclipse.core.runtime.Platform;
/*    */ import org.osgi.framework.Bundle;
/*    */ 
/*    */ public class JspGenerator
/*    */ {
/* 26 */   private static final Logger logger = Logger.getLogger(JspGenerator.class);
/*    */ 
/*    */   public void generate()
/*    */     throws Exception
/*    */   {
/* 36 */     copyResource();
/*    */ 
/* 38 */     List dbTables = (List)MessageHub.get("SELECTED_TABLES");
/* 39 */     String projectName = (String)MessageHub.get("PROJECT_NAME");
/*    */ 
/* 41 */     Map data = new HashMap();
/* 42 */     data.put("dbTables", dbTables);
/* 43 */     data.put("projectName", projectName);
/*    */ 
/* 45 */     String templateName = Constant.TemplateNames.UI_sidebar;
/* 46 */     String subFSPath = "/WEB-INF/jsp/public";
/* 47 */     String fileName = "sidebar.jsp";
/* 48 */     FreemarkerUtil.generateMavenWebFile(templateName, data, subFSPath, fileName);
/*    */ 
/* 50 */     templateName = Constant.TemplateNames.UI_header;
/* 51 */     subFSPath = "/WEB-INF/jsp/public";
/* 52 */     fileName = "header.jsp";
/* 53 */     FreemarkerUtil.generateMavenWebFile(templateName, data, subFSPath, fileName);
/*    */ 
/* 55 */     templateName = Constant.TemplateNames.UI_index;
/* 56 */     fileName = "index.jsp";
/* 57 */     FreemarkerUtil.generateMavenWebFile(templateName, data, subFSPath, fileName);
/*    */ 
/* 59 */     for (DBTable dbTable : dbTables)
/* 60 */       if (dbTable.isGenerateCode())
/*    */       {
/* 62 */         data.put("dbTable", dbTable);
/* 63 */         subFSPath = "/WEB-INF/jsp/" + dbTable.getEntityName() + "/";
/* 64 */         if (dbTable.getGenerateListJSP().booleanValue()) {
/* 65 */           templateName = Constant.TemplateNames.UI_listUI;
/* 66 */           if (dbTable.getTableFunctions().contains(new ExcelFunction()))
/* 67 */             data.put("exportExcel", Boolean.valueOf(true));
/*    */           else {
/* 69 */             data.put("exportExcel", Boolean.valueOf(false));
/*    */           }
/* 71 */           fileName = "listUI.jsp";
/* 72 */           FreemarkerUtil.generateMavenWebFile(templateName, data, subFSPath, fileName);
/*    */         }
/* 74 */         data.put("useHtmlEditor", new CustomFKMethodUseHtmlEditor());
/* 75 */         if (dbTable.getGenerateListJSP().booleanValue())
/*    */         {
/* 77 */           templateName = Constant.TemplateNames.UI_saveUI;
/* 78 */           fileName = "saveUI.jsp";
/* 79 */           FreemarkerUtil.generateMavenWebFile(templateName, data, subFSPath, fileName);
/*    */         }
/*    */       }
/*    */   }
/*    */ 
/*    */   private static void copyResource()
/*    */   {
/*    */     try
/*    */     {
/* 93 */       URL url = Platform.getBundle("com.tianshouzhi.autopro").getResource(
/* 94 */         "resource");
/* 95 */       String path = FileLocator.toFileURL(url).getPath();
/*    */ 
/* 101 */       File resourceDir = new File(path);
/* 102 */       String jsDirString = (String)MessageHub.get("PROJECT_FS_PATH") + "/src/main/webapp/js";
/* 103 */       File jsDir = new File(jsDirString);
/*    */ 
/* 105 */       new FileCopyUtil().copy(resourceDir, jsDir);
/*    */     } catch (Exception e) {
/* 107 */       logger.error("拷贝资源出现错误", e);
/*    */     }
/*    */   }
/*    */ }

/* Location:           D:\MyEclipse2015CI\dropins\com.tianshouzhi.autopro_1.0.0.201506242235.jar
 * Qualified Name:     com.tianshouzhi.autopro.service.code.jsp.JspGenerator
 * JD-Core Version:    0.6.2
 */