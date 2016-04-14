/*    */ package com.tianshouzhi.autopro.service.code.function.login;
/*    */ 
/*    */ import com.tianshouzhi.autopro.domain.db.DBTable;
/*    */ import com.tianshouzhi.autopro.domain.db.TableColumn;
/*    */ import com.tianshouzhi.autopro.domain.functions.BaseTableFunction;
/*    */ import com.tianshouzhi.autopro.domain.functions.LoginFunction;
/*    */ import com.tianshouzhi.autopro.util.Constant.TemplateNames;
/*    */ import com.tianshouzhi.autopro.util.FreemarkerUtil;
/*    */ import java.util.HashMap;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ 
/*    */ public class LoginFunctionGenerator
/*    */ {
/*    */   public static void generateLoginFunction(DBTable dbTable, String projectName)
/*    */   {
/* 16 */     List tableFunctions = dbTable.getTableFunctions();
/* 17 */     String username = null;
/* 18 */     String password = null;
/* 19 */     for (BaseTableFunction tableFunction : tableFunctions) {
/* 20 */       if ("Login_Function".equals(tableFunction.getFunctionName())) {
/* 21 */         LoginFunction loginFunction = (LoginFunction)tableFunction;
/* 22 */         username = loginFunction.getLoginAccountColumn().getPropertyName();
/* 23 */         password = loginFunction.getLoginPasswordColumn().getPropertyName();
/*    */       }
/*    */     }
/* 26 */     Map data = new HashMap();
/* 27 */     data.put("dbTable", dbTable);
/* 28 */     data.put("projectName", projectName);
/* 29 */     data.put("username", username);
/* 30 */     data.put("password", password);
/*    */ 
/* 33 */     String templateName = Constant.TemplateNames.Function_LoginController;
/* 34 */     String entityPakcage = "web.controller";
/* 35 */     String fileName = "LoginController.java";
/* 36 */     FreemarkerUtil.generateMavenJavaFile(templateName, data, entityPakcage, fileName);
/*    */ 
/* 39 */     templateName = "Function_LoginUI.jsp.ftl";
/* 40 */     String subFSPath = "/WEB-INF/jsp/login";
/* 41 */     fileName = "loginUI.jsp";
/* 42 */     FreemarkerUtil.generateMavenWebFile(templateName, data, subFSPath, fileName);
/*    */ 
/* 45 */     entityPakcage = "util";
/* 46 */     templateName = "Util_SecurityCodeGenarator.java.ftl";
/* 47 */     fileName = "SecurityCodeGenarator.java";
/* 48 */     FreemarkerUtil.generateMavenJavaFile(templateName, data, entityPakcage, fileName);
/*    */ 
/* 50 */     templateName = "Util_BufferedImageGenarator.java.ftl";
/* 51 */     fileName = "BufferedImageGenarator.java";
/* 52 */     FreemarkerUtil.generateMavenJavaFile(templateName, data, entityPakcage, fileName);
/*    */ 
/* 55 */     entityPakcage = "web.inteceptor";
/* 56 */     templateName = "Function_CheckPrivilegeInteceptor.java.ftl";
/* 57 */     fileName = "CheckPrivilegeInteceptor.java";
/* 58 */     FreemarkerUtil.generateMavenJavaFile(templateName, data, entityPakcage, fileName);
/*    */   }
/*    */ }

/* Location:           D:\MyEclipse2015CI\dropins\com.tianshouzhi.autopro_1.0.0.201506242235.jar
 * Qualified Name:     com.tianshouzhi.autopro.service.code.function.login.LoginFunctionGenerator
 * JD-Core Version:    0.6.2
 */