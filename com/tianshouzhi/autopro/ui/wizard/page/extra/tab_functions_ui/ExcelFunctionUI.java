/*    */ package com.tianshouzhi.autopro.ui.wizard.page.extra.tab_functions_ui;
/*    */ 
/*    */ import com.tianshouzhi.autopro.domain.db.DBTable;
/*    */ import com.tianshouzhi.autopro.domain.functions.ExcelFunction;
/*    */ import com.tianshouzhi.autopro.util.DBTablesUtil;
/*    */ import java.util.List;
/*    */ import org.eclipse.swt.events.SelectionAdapter;
/*    */ import org.eclipse.swt.events.SelectionEvent;
/*    */ import org.eclipse.swt.widgets.Button;
/*    */ import org.eclipse.swt.widgets.Composite;
/*    */ import org.eclipse.swt.widgets.Control;
/*    */ 
/*    */ public class ExcelFunctionUI
/*    */ {
/*    */   public ExcelFunctionUI(Composite parent)
/*    */   {
/* 22 */     createContents(parent);
/*    */   }
/*    */ 
/*    */   private Control createContents(Composite container)
/*    */   {
/* 28 */     int y = 20;
/* 29 */     List excludeNoneGenerateCodeTables = DBTablesUtil.getSelectedDBTables();
/* 30 */     for (DBTable dbTable : excludeNoneGenerateCodeTables) {
/* 31 */       Button button = new Button(container, 32);
/* 32 */       button.setBounds(10, y, 150, 16);
/* 33 */       button.setText(dbTable.getTableName());
/* 34 */       button.addSelectionListener(new ExcelFunctionCheckListener(button));
/* 35 */       y += 23;
/*    */     }
/*    */ 
/* 38 */     return container;
/*    */   }
/*    */ 
/*    */   class ExcelFunctionCheckListener extends SelectionAdapter
/*    */   {
/*    */     private Button button;
/*    */ 
/*    */     public ExcelFunctionCheckListener(Button button)
/*    */     {
/* 48 */       this.button = button;
/*    */     }
/*    */ 
/*    */     public void widgetSelected(SelectionEvent e)
/*    */     {
/* 53 */       String tableName = this.button.getText();
/* 54 */       boolean enabled = this.button.isEnabled();
/* 55 */       for (DBTable table : DBTablesUtil.getSelectedDBTables())
/* 56 */         if (tableName.equalsIgnoreCase(table.getTableName())) {
/* 57 */           if (enabled)
/* 58 */             table.getTableFunctions().add(new ExcelFunction());
/*    */           else {
/* 60 */             table.getTableFunctions().remove(new ExcelFunction());
/*    */           }
/* 62 */           DBTablesUtil.replaceMessageHubDbtable(table);
/* 63 */           break;
/*    */         }
/*    */     }
/*    */   }
/*    */ }

/* Location:           D:\MyEclipse2015CI\dropins\com.tianshouzhi.autopro_1.0.0.201506242235.jar
 * Qualified Name:     com.tianshouzhi.autopro.ui.wizard.page.extra.tab_functions_ui.ExcelFunctionUI
 * JD-Core Version:    0.6.2
 */