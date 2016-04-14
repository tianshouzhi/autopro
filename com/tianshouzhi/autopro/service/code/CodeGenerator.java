/*    */ package com.tianshouzhi.autopro.service.code;
/*    */ 
/*    */ import com.tianshouzhi.autopro.domain.db.DBTable;
/*    */ import com.tianshouzhi.autopro.domain.functions.BaseTableFunction;
/*    */ import com.tianshouzhi.autopro.service.code.business.ServiceGenerator;
/*    */ import com.tianshouzhi.autopro.service.code.common.CommonGenerator;
/*    */ import com.tianshouzhi.autopro.service.code.dao.DaoGenerator;
/*    */ import com.tianshouzhi.autopro.service.code.function.FunctionGenerator;
/*    */ import com.tianshouzhi.autopro.service.code.jsp.JspGenerator;
/*    */ import com.tianshouzhi.autopro.service.code.mvc.WebGenerator;
/*    */ import com.tianshouzhi.autopro.service.user.UserSettingService;
/*    */ import com.tianshouzhi.autopro.util.MessageHub;
/*    */ import java.util.Iterator;
/*    */ import java.util.List;
/*    */ import org.apache.log4j.Logger;
/*    */ 
/*    */ public class CodeGenerator
/*    */ {
/* 27 */   private static final Logger logger = Logger.getLogger(CodeGenerator.class);
/*    */ 
/*    */   public static boolean generate()
/*    */   {
/* 38 */     boolean isSuccess = false;
/*    */     try
/*    */     {
/* 41 */       setTableFunctions();
/* 42 */       UserSettingService.saveGenerateConfig();
/* 43 */       new CommonGenerator().generate();
/* 44 */       new DaoGenerator().generate();
/* 45 */       new ServiceGenerator().generate();
/* 46 */       new WebGenerator().generate();
/* 47 */       new JspGenerator().generate();
/* 48 */       new FunctionGenerator().generate();
/* 49 */       isSuccess = true;
/*    */     } catch (Exception e) {
/* 51 */       isSuccess = false;
/* 52 */       logger.error("Generate Code Error", e);
/*    */     }
/* 54 */     return isSuccess;
/*    */   }
/*    */ 
/*    */   private static void setTableFunctions()
/*    */   {
/* 61 */     List selectDBtables = (List)
/* 62 */       MessageHub.get("SELECTED_TABLES");
/* 63 */     List tableFunctions = (List)
/* 64 */       MessageHub.get("L_SELECTED_FUNCTIONS");
/* 65 */     if (tableFunctions != null)
/*    */     {
/*    */       Iterator localIterator2;
/* 66 */       for (Iterator localIterator1 = tableFunctions.iterator(); localIterator1.hasNext(); 
/* 67 */         localIterator2.hasNext())
/*    */       {
/* 66 */         BaseTableFunction tableFunction = (BaseTableFunction)localIterator1.next();
/* 67 */         localIterator2 = selectDBtables.iterator(); continue; DBTable dbTable = (DBTable)localIterator2.next();
/* 68 */         if (tableFunction.getRelatedTables().contains(dbTable.getTableName()))
/* 69 */           dbTable.getTableFunctions().add(tableFunction);
/*    */       }
/*    */     }
/*    */   }
/*    */ }

/* Location:           D:\MyEclipse2015CI\dropins\com.tianshouzhi.autopro_1.0.0.201506242235.jar
 * Qualified Name:     com.tianshouzhi.autopro.service.code.CodeGenerator
 * JD-Core Version:    0.6.2
 */