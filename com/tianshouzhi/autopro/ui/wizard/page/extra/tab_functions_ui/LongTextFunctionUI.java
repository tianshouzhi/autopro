/*    */ package com.tianshouzhi.autopro.ui.wizard.page.extra.tab_functions_ui;
/*    */ 
/*    */ import com.tianshouzhi.autopro.domain.db.DBTable;
/*    */ import com.tianshouzhi.autopro.domain.db.TableColumn;
/*    */ import com.tianshouzhi.autopro.domain.functions.LongTextFunction;
/*    */ import com.tianshouzhi.autopro.util.DBTablesUtil;
/*    */ import java.util.Iterator;
/*    */ import java.util.List;
/*    */ import org.apache.log4j.Logger;
/*    */ import org.eclipse.swt.events.SelectionAdapter;
/*    */ import org.eclipse.swt.events.SelectionEvent;
/*    */ import org.eclipse.swt.widgets.Button;
/*    */ import org.eclipse.swt.widgets.Composite;
/*    */ import org.eclipse.swt.widgets.Control;
/*    */ 
/*    */ public class LongTextFunctionUI
/*    */ {
/* 24 */   private static final Logger logger = Logger.getLogger(LongTextFunctionUI.class);
/*    */ 
/*    */   public LongTextFunctionUI(Composite parent) {
/* 27 */     createContents(parent);
/*    */   }
/*    */ 
/*    */   private Control createContents(Composite container) {
/* 31 */     int y = 20;
/* 32 */     List dbTables = DBTablesUtil.getSelectedDBTables();
/*    */     Iterator localIterator2;
/* 33 */     for (Iterator localIterator1 = dbTables.iterator(); localIterator1.hasNext(); 
/* 36 */       localIterator2.hasNext())
/*    */     {
/* 33 */       DBTable dbTable = (DBTable)localIterator1.next();
/* 34 */       String tableName = dbTable.getTableName();
/* 35 */       List longTextColumns = DBTablesUtil.getLongTextColumns(dbTable.getTableName());
/* 36 */       localIterator2 = longTextColumns.iterator(); continue; TableColumn tableColumn = (TableColumn)localIterator2.next();
/* 37 */       Button button = new Button(container, 32);
/* 38 */       button.setBounds(10, y, 300, 16);
/* 39 */       button.setText(tableName + "." + tableColumn.getColumnName());
/* 40 */       button.addSelectionListener(new LongTextSettingUICheckListener(button));
/* 41 */       y += 23;
/*    */     }
/*    */ 
/* 46 */     return container;
/*    */   }
/*    */ 
/*    */   class LongTextSettingUICheckListener extends SelectionAdapter
/*    */   {
/*    */     private Button button;
/*    */ 
/*    */     public LongTextSettingUICheckListener(Button button)
/*    */     {
/* 56 */       this.button = button;
/*    */     }
/*    */ 
/*    */     public void widgetSelected(SelectionEvent e)
/*    */     {
/* 62 */       boolean checked = this.button.getSelection();
/*    */ 
/* 64 */       String columnFullName = this.button.getText();
/* 65 */       String[] split = columnFullName.split("\\.");
/* 66 */       String tableName = split[0];
/* 67 */       String columnName = split[1];
/* 68 */       if (checked) {
/* 69 */         LongTextFunctionUI.logger.info("添加htmlEditor，表:" + tableName + "--列:" + columnName);
/* 70 */         DBTablesUtil.addTableFunction(tableName, new LongTextFunction(columnName, Boolean.valueOf(true)));
/*    */       } else {
/* 72 */         LongTextFunctionUI.logger.info("删除htmlEditor，表:" + tableName + "--列:" + columnName);
/* 73 */         DBTablesUtil.removeLongTextFunction(tableName, columnName);
/*    */       }
/*    */     }
/*    */   }
/*    */ }

/* Location:           D:\MyEclipse2015CI\dropins\com.tianshouzhi.autopro_1.0.0.201506242235.jar
 * Qualified Name:     com.tianshouzhi.autopro.ui.wizard.page.extra.tab_functions_ui.LongTextFunctionUI
 * JD-Core Version:    0.6.2
 */