/*    */ package com.tianshouzhi.autopro.service.code.function;
/*    */ 
/*    */ import com.tianshouzhi.autopro.domain.db.DBTable;
/*    */ import com.tianshouzhi.autopro.domain.functions.BaseTableFunction;
/*    */ import com.tianshouzhi.autopro.service.code.function.login.LoginFunctionGenerator;
/*    */ import com.tianshouzhi.autopro.service.code.function.report.ReportFunctionGenerator;
/*    */ import com.tianshouzhi.autopro.util.MessageHub;
/*    */ import java.util.Iterator;
/*    */ import java.util.List;
/*    */ import org.apache.log4j.Logger;
/*    */ 
/*    */ public class FunctionGenerator
/*    */ {
/* 24 */   private static final Logger logger = Logger.getLogger(FunctionGenerator.class);
/*    */ 
/*    */   public void generate()
/*    */   {
/* 28 */     List dbTables = (List)MessageHub.get("SELECTED_TABLES");
/*    */     Iterator localIterator2;
/* 31 */     for (Iterator localIterator1 = dbTables.iterator(); localIterator1.hasNext(); 
/* 33 */       localIterator2.hasNext())
/*    */     {
/* 31 */       DBTable dbTable = (DBTable)localIterator1.next();
/* 32 */       List tableFunctions = dbTable.getTableFunctions();
/* 33 */       localIterator2 = tableFunctions.iterator(); continue; BaseTableFunction tableFunction = (BaseTableFunction)localIterator2.next();
/* 34 */       logger.info("数据库表:" + dbTable.getTableName() + "--附加功能" + tableFunction.getFunctionName());
/*    */     }
/*    */ 
/* 38 */     for (localIterator1 = dbTables.iterator(); localIterator1.hasNext(); 
/* 40 */       localIterator2.hasNext())
/*    */     {
/* 38 */       DBTable dbTable = (DBTable)localIterator1.next();
/* 39 */       List tableFunctions = dbTable.getTableFunctions();
/* 40 */       localIterator2 = tableFunctions.iterator(); continue; BaseTableFunction tableFunction = (BaseTableFunction)localIterator2.next();
/* 41 */       if ("Login_Function".equals(tableFunction.getFunctionName())) {
/* 42 */         String projectName = (String)MessageHub.get("PROJECT_NAME");
/* 43 */         LoginFunctionGenerator.generateLoginFunction(dbTable, projectName);
/*    */       }
/*    */ 
/*    */     }
/*    */ 
/* 48 */     ReportFunctionGenerator.genrateReportFunction();
/*    */   }
/*    */ }

/* Location:           D:\MyEclipse2015CI\dropins\com.tianshouzhi.autopro_1.0.0.201506242235.jar
 * Qualified Name:     com.tianshouzhi.autopro.service.code.function.FunctionGenerator
 * JD-Core Version:    0.6.2
 */