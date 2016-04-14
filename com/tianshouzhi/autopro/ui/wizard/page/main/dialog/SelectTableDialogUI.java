/*     */ package com.tianshouzhi.autopro.ui.wizard.page.main.dialog;
/*     */ 
/*     */ import com.tianshouzhi.autopro.domain.db.DBConnectionConfig;
/*     */ import com.tianshouzhi.autopro.domain.db.DBTable;
/*     */ import com.tianshouzhi.autopro.ui.msg.Messages;
/*     */ import com.tianshouzhi.autopro.ui.wizard.page.extra.TabFunctionUI;
/*     */ import com.tianshouzhi.autopro.ui.wizard.page.main.MainPage;
/*     */ import com.tianshouzhi.autopro.util.DBTablesUtil;
/*     */ import com.tianshouzhi.autopro.util.DataBaseMetaInfoUtil;
/*     */ import com.tianshouzhi.autopro.util.MessageHub;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import org.apache.log4j.Logger;
/*     */ import org.eclipse.jface.dialogs.Dialog;
/*     */ import org.eclipse.jface.dialogs.IDialogConstants;
/*     */ import org.eclipse.jface.dialogs.MessageDialog;
/*     */ import org.eclipse.swt.custom.TableEditor;
/*     */ import org.eclipse.swt.events.SelectionAdapter;
/*     */ import org.eclipse.swt.events.SelectionEvent;
/*     */ import org.eclipse.swt.graphics.Point;
/*     */ import org.eclipse.swt.layout.FillLayout;
/*     */ import org.eclipse.swt.widgets.Button;
/*     */ import org.eclipse.swt.widgets.Composite;
/*     */ import org.eclipse.swt.widgets.Control;
/*     */ import org.eclipse.swt.widgets.Label;
/*     */ import org.eclipse.swt.widgets.Shell;
/*     */ import org.eclipse.swt.widgets.Table;
/*     */ import org.eclipse.swt.widgets.TableColumn;
/*     */ import org.eclipse.swt.widgets.TableItem;
/*     */ 
/*     */ public class SelectTableDialogUI extends Dialog
/*     */ {
/*  36 */   private static final Logger logger = Logger.getLogger(SelectTableDialogUI.class);
/*     */   private static final int CHECK_INDEX = 0;
/*     */   private static final int DBTABLE_NAME_INDEX = 1;
/*     */   private static final int ENTITY_NAME_INDEX = 2;
/*     */   private static final int LIST_UI_INDEX = 3;
/*     */   private static final int SAVE_UI_INDEX = 4;
/*     */   private static final int REMARK_INDEX = 5;
/*     */   Table table;
/*     */   private MainPage mainPage;
/*     */   private TabFunctionUI tabFunctionUI;
/*  55 */   List<String> selectedTableNames = new ArrayList();
/*  56 */   private List<DBTable> dbTables = new ArrayList();
/*     */ 
/*     */   public SelectTableDialogUI(Shell parentShell, DBConnectionConfig dbConfig, TabFunctionUI tabFunctionUI, MainPage mainPage)
/*     */   {
/*  64 */     super(parentShell);
/*  65 */     this.tabFunctionUI = tabFunctionUI;
/*  66 */     this.mainPage = mainPage;
/*     */ 
/*  68 */     List selectedTables = (List)MessageHub.get("SELECTED_TABLES");
/*  69 */     if ((selectedTables == null) || (selectedTables.isEmpty())) {
/*  70 */       for (DBTable dbTable : DataBaseMetaInfoUtil.getDBTables(dbConfig)) {
/*  71 */         if (DBTablesUtil.isMiddleTable(dbTable))
/*  72 */           this.dbTables.add(dbTable);
/*     */         else
/*  74 */           this.dbTables.add(0, dbTable);
/*     */       }
/*     */     }
/*     */     else
/*  78 */       this.dbTables = selectedTables;
/*     */   }
/*     */ 
/*     */   protected Control createDialogArea(Composite parent)
/*     */   {
/*  90 */     if (this.dbTables.size() == 0) {
/*  91 */       logger.info("数据库中并没有任何表！");
/*  92 */       String title = "提示";
/*  93 */       String message = "所选数据库中并没有任何表，请先创建表！";
/*  94 */       MessageDialog.openWarning(parent.getShell(), title, message);
/*  95 */       return null;
/*     */     }
/*     */ 
/*  98 */     Composite container = (Composite)super.createDialogArea(parent);
/*     */ 
/* 100 */     container.setLayout(new FillLayout(256));
/* 101 */     this.table = new Table(container, 2048);
/*     */ 
/* 103 */     this.table.setHeaderVisible(true);
/* 104 */     this.table.setLinesVisible(true);
/*     */ 
/* 106 */     String[] columnNames = { "", "表名", "实体名", "listUI", "saveUI", "备注" };
/*     */ 
/* 108 */     int[] columnWidths = { 25, 120, 120, 40, 40, 140 };
/*     */ 
/* 110 */     int[] columnAlignments = { 16777216, 16384, 16384, 16777216, 
/* 111 */       16777216, 16384 };
/*     */     TableColumn tableColumn;
/* 112 */     for (int i = 0; i < columnNames.length; i++)
/*     */     {
/* 114 */       tableColumn = new TableColumn(this.table, 
/* 115 */         columnAlignments[i]);
/* 116 */       tableColumn.setText(columnNames[i]);
/* 117 */       tableColumn.setWidth(columnWidths[i]);
/*     */     }
/*     */ 
/* 120 */     for (DBTable dbTable : this.dbTables) {
/* 121 */       TableItem item = new TableItem(this.table, 16777216);
/* 122 */       item.setText(0, "");
/* 123 */       item.setText(1, dbTable.getTableName());
/* 124 */       item.setText(3, "");
/* 125 */       item.setText(4, "");
/* 126 */       if (DBTablesUtil.isMiddleTable(dbTable)) {
/* 127 */         item.setText(5, "中间表,不生成代码");
/* 128 */         item.setText(2, "");
/*     */       } else {
/* 130 */         item.setChecked(dbTable.isGenerateCode());
/* 131 */         item.setText(2, dbTable.getEntityName());
/*     */       }
/*     */     }
/*     */ 
/* 135 */     createCells(this.table);
/*     */ 
/* 137 */     return container;
/*     */   }
/*     */ 
/*     */   private void createCells(Table table) {
/* 141 */     for (int i = 0; i < table.getItemCount(); i++)
/* 142 */       if (!DBTablesUtil.isMiddleTable((DBTable)this.dbTables.get(i)))
/* 143 */         createOneItemCells(table.getItem(i), (DBTable)this.dbTables.get(i));
/*     */   }
/*     */ 
/*     */   private void createOneItemCells(TableItem item, DBTable dbTable)
/*     */   {
/* 150 */     TableEditor checkAllEditor = new TableEditor(this.table);
/* 151 */     Button checkButton = new Button(this.table, 32);
/* 152 */     checkButton.setSelection(dbTable.isGenerateCode());
/* 153 */     checkAllEditor.grabHorizontal = true;
/* 154 */     checkAllEditor.setEditor(checkButton, item, 0);
/*     */ 
/* 156 */     Label dbTableName = new Label(this.table, 0);
/* 157 */     dbTableName.setText(item.getText(1));
/*     */ 
/* 160 */     Label dbTableEntityName = new Label(this.table, 0);
/* 161 */     dbTableEntityName.setText(item.getText(2));
/*     */ 
/* 163 */     TableEditor listUIEditor = new TableEditor(this.table);
/* 164 */     Button listUIButton = new Button(this.table, 32);
/* 165 */     listUIButton.setSelection(dbTable.getGenerateListJSP().booleanValue());
/* 166 */     listUIEditor.grabHorizontal = true;
/* 167 */     listUIEditor.setEditor(listUIButton, item, 3);
/*     */ 
/* 170 */     TableEditor saveUIEditor = new TableEditor(this.table);
/* 171 */     Button saveUIButton = new Button(this.table, 32);
/* 172 */     saveUIButton.setSelection(dbTable.getGenerateSaveUIJsp().booleanValue());
/* 173 */     saveUIEditor.grabHorizontal = true;
/* 174 */     saveUIEditor.setEditor(saveUIButton, item, 4);
/* 175 */     if (!checkButton.getSelection()) {
/* 176 */       saveUIButton.setEnabled(false);
/* 177 */       listUIButton.setEnabled(false);
/*     */     }
/*     */ 
/* 181 */     Label remarkContent = new Label(this.table, 0);
/* 182 */     remarkContent.setText(item.getText(5));
/*     */ 
/* 184 */     checkButton.addSelectionListener(new DBTableCheckListener(checkButton, listUIButton, saveUIButton, dbTable));
/* 185 */     listUIButton.addSelectionListener(new CheckSaveOrListListener(listUIButton, dbTable, "listUI"));
/* 186 */     saveUIButton.addSelectionListener(new CheckSaveOrListListener(saveUIButton, dbTable, "saveUI"));
/*     */   }
/*     */ 
/*     */   protected void createButtonsForButtonBar(Composite parent)
/*     */   {
/* 285 */     Button button = createButton(parent, 0, IDialogConstants.OK_LABEL, 
/* 286 */       true);
/*     */ 
/* 288 */     button.addSelectionListener(new SelectionAdapter()
/*     */     {
/*     */       public void widgetSelected(SelectionEvent e) {
/* 291 */         if ((SelectTableDialogUI.this.dbTables != null) && (SelectTableDialogUI.this.dbTables.size() > 0)) {
/* 292 */           MessageHub.put("SELECTED_TABLES", SelectTableDialogUI.this.dbTables);
/* 293 */           SelectTableDialogUI.this.mainPage.setPageComplete(true);
/* 294 */           SelectTableDialogUI.this.tabFunctionUI.init();
/*     */         }
/*     */       }
/*     */     });
/* 298 */     button.setText(Messages.SelectTableDialogUI_OK);
/* 299 */     Button button_1 = createButton(parent, 1, 
/* 300 */       IDialogConstants.CANCEL_LABEL, false);
/* 301 */     button_1.setText(Messages.SelectTableDialogUI_CANCEL);
/*     */   }
/*     */ 
/*     */   protected Point getInitialSize()
/*     */   {
/* 309 */     return new Point(505, 350);
/*     */   }
/*     */ 
/*     */   class CheckSaveOrListListener extends SelectionAdapter
/*     */   {
/*     */     private Button button;
/*     */     private DBTable dbTable;
/*     */     private String buttonType;
/*     */ 
/*     */     public CheckSaveOrListListener(Button button, DBTable dbTable, String buttonType)
/*     */     {
/* 196 */       this.button = button;
/* 197 */       this.dbTable = dbTable;
/* 198 */       this.buttonType = buttonType;
/*     */     }
/*     */ 
/*     */     public void widgetSelected(SelectionEvent e) {
/* 202 */       super.widgetSelected(e);
/* 203 */       if (this.buttonType.equals("saveUI")) {
/* 204 */         if (this.button.getSelection())
/* 205 */           this.dbTable.setGenerateSaveUIJsp(Boolean.valueOf(true));
/*     */         else {
/* 207 */           this.dbTable.setGenerateSaveUIJsp(Boolean.valueOf(false));
/*     */         }
/*     */       }
/* 210 */       else if (this.button.getSelection())
/* 211 */         this.dbTable.setGenerateListJSP(Boolean.valueOf(true));
/*     */       else {
/* 213 */         this.dbTable.setGenerateListJSP(Boolean.valueOf(false));
/*     */       }
/*     */ 
/* 217 */       for (int i = 0; i < SelectTableDialogUI.this.dbTables.size(); i++) {
/* 218 */         DBTable checkTable = (DBTable)SelectTableDialogUI.this.dbTables.get(i);
/* 219 */         if (checkTable.getTableName().equals(this.dbTable.getTableName())) {
/* 220 */           this.dbTable.setGenerateCode(checkTable.isGenerateCode());
/* 221 */           if (this.buttonType.equals("saveUI"))
/* 222 */             this.dbTable.setGenerateListJSP(checkTable.getGenerateListJSP());
/*     */           else {
/* 224 */             this.dbTable.setGenerateSaveUIJsp(checkTable.getGenerateSaveUIJsp());
/*     */           }
/* 226 */           SelectTableDialogUI.this.dbTables.set(i, this.dbTable);
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   class DBTableCheckListener extends SelectionAdapter
/*     */   {
/*     */     private Button checkButton;
/*     */     private Button listUIButton;
/*     */     private Button saveUIButton;
/*     */     private DBTable dbTable;
/*     */ 
/*     */     public DBTableCheckListener(Button checkButton, Button listUIButton, Button saveUIButton, DBTable dbTable)
/*     */     {
/* 245 */       this.checkButton = checkButton;
/* 246 */       this.listUIButton = listUIButton;
/* 247 */       this.saveUIButton = saveUIButton;
/* 248 */       this.dbTable = dbTable;
/*     */     }
/*     */ 
/*     */     public void widgetSelected(SelectionEvent e)
/*     */     {
/* 253 */       boolean selection = this.checkButton.getSelection();
/* 254 */       if (!selection) {
/* 255 */         this.listUIButton.setEnabled(false);
/* 256 */         this.saveUIButton.setEnabled(false);
/* 257 */         this.dbTable.setGenerateCode(false);
/*     */       } else {
/* 259 */         this.listUIButton.setSelection(true);
/* 260 */         this.saveUIButton.setSelection(true);
/* 261 */         this.dbTable.setGenerateCode(true);
/* 262 */         this.listUIButton.setEnabled(true);
/* 263 */         this.saveUIButton.setEnabled(true);
/*     */       }
/*     */ 
/* 267 */       for (int i = 0; i < SelectTableDialogUI.this.dbTables.size(); i++) {
/* 268 */         DBTable checkTable = (DBTable)SelectTableDialogUI.this.dbTables.get(i);
/* 269 */         if (checkTable.getTableName().equals(this.dbTable.getTableName())) {
/* 270 */           this.dbTable.setGenerateListJSP(checkTable.getGenerateListJSP());
/* 271 */           this.dbTable.setGenerateSaveUIJsp(checkTable.getGenerateSaveUIJsp());
/* 272 */           SelectTableDialogUI.this.dbTables.set(i, this.dbTable);
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */ }

/* Location:           D:\MyEclipse2015CI\dropins\com.tianshouzhi.autopro_1.0.0.201506242235.jar
 * Qualified Name:     com.tianshouzhi.autopro.ui.wizard.page.main.dialog.SelectTableDialogUI
 * JD-Core Version:    0.6.2
 */