/*    */ package com.tianshouzhi.autopro.ui.wizard.page.main.provider;
/*    */ 
/*    */ import com.tianshouzhi.autopro.domain.db.DBTable;
/*    */ import com.tianshouzhi.autopro.util.PluginUtil;
/*    */ import com.tianshouzhi.autopro.util.StringUtil;
/*    */ import java.io.File;
/*    */ import org.eclipse.jface.viewers.ColumnLabelProvider;
/*    */ import org.eclipse.jface.viewers.ITableLabelProvider;
/*    */ import org.eclipse.swt.graphics.Image;
/*    */ import org.eclipse.wb.swt.SWTResourceManager;
/*    */ 
/*    */ public class SelectTableDialogUIProvider extends ColumnLabelProvider
/*    */   implements ITableLabelProvider
/*    */ {
/* 15 */   private static final Image CHECKED = SWTResourceManager.getImage(PluginUtil.getResourceFile("icons/checked.jpg").getAbsolutePath());
/* 16 */   private static final Image UNCHECKED = SWTResourceManager.getImage(PluginUtil.getResourceFile("icons/unchecked.jpg").getAbsolutePath());
/*    */ 
/* 18 */   public Image getColumnImage(Object element, int columnIndex) { DBTable dbTable = (DBTable)element;
/* 19 */     if (columnIndex == 2) {
/* 20 */       Boolean generateListJSP = dbTable.getGenerateListJSP();
/* 21 */       if (generateListJSP.booleanValue()) {
/* 22 */         return CHECKED;
/*    */       }
/* 24 */       return UNCHECKED;
/*    */     }
/*    */ 
/* 27 */     if (columnIndex == 3) {
/* 28 */       Boolean generateListJSP = dbTable.getGenerateSaveUIJsp();
/* 29 */       if (generateListJSP.booleanValue()) {
/* 30 */         return CHECKED;
/*    */       }
/* 32 */       return UNCHECKED;
/*    */     }
/*    */ 
/* 35 */     return null; }
/*    */ 
/*    */   public String getColumnText(Object element, int columnIndex)
/*    */   {
/* 39 */     DBTable dbTable = (DBTable)element;
/* 40 */     if (columnIndex == 0) {
/* 41 */       return dbTable.getTableName();
/*    */     }
/* 43 */     if (columnIndex == 1) {
/* 44 */       return StringUtil.getCamelName(dbTable.getEntityName());
/*    */     }
/*    */ 
/* 47 */     return null;
/*    */   }
/*    */ }

/* Location:           D:\MyEclipse2015CI\dropins\com.tianshouzhi.autopro_1.0.0.201506242235.jar
 * Qualified Name:     com.tianshouzhi.autopro.ui.wizard.page.main.provider.SelectTableDialogUIProvider
 * JD-Core Version:    0.6.2
 */