/*     */ package com.tianshouzhi.autopro.ui.wizard.page.extra.tab_functions_ui;
/*     */ 
/*     */ import com.tianshouzhi.autopro.domain.db.DBTable;
/*     */ import com.tianshouzhi.autopro.domain.enums.ChartType;
/*     */ import com.tianshouzhi.autopro.domain.functions.ReportFunction;
/*     */ import com.tianshouzhi.autopro.util.DBTablesUtil;
/*     */ import com.tianshouzhi.autopro.util.PluginUtil;
/*     */ import com.tianshouzhi.autopro.util.StringUtil;
/*     */ import java.io.File;
/*     */ import java.util.List;
/*     */ import org.eclipse.jface.dialogs.MessageDialog;
/*     */ import org.eclipse.swt.custom.TableEditor;
/*     */ import org.eclipse.swt.events.SelectionAdapter;
/*     */ import org.eclipse.swt.events.SelectionEvent;
/*     */ import org.eclipse.swt.graphics.Image;
/*     */ import org.eclipse.swt.widgets.Button;
/*     */ import org.eclipse.swt.widgets.Combo;
/*     */ import org.eclipse.swt.widgets.Composite;
/*     */ import org.eclipse.swt.widgets.Control;
/*     */ import org.eclipse.swt.widgets.Label;
/*     */ import org.eclipse.swt.widgets.Shell;
/*     */ import org.eclipse.swt.widgets.Table;
/*     */ import org.eclipse.swt.widgets.TableItem;
/*     */ import org.eclipse.swt.widgets.Text;
/*     */ import org.eclipse.wb.swt.SWTResourceManager;
/*     */ 
/*     */ public class ReportFunctionUI
/*     */ {
/*     */   private Table table;
/*     */   private Combo combo_select_table;
/*     */   private Combo combo_report_type;
/*     */   private static final int REPORT_TYPE_INDEX = 0;
/*     */   private static final int REPORT_COUNT_RECORD = 1;
/*     */   private static final int REPORT_COUNT_COLUMN_VALUE = 2;
/*     */   private static final int REPORT_IS_ACCUMULATE = 3;
/*     */   private static final int REPORT_XAIXS_COLUMN = 4;
/*     */   private static final int REPORT_YAIXS_COLUMN = 5;
/*     */   private static final int REPORT_CATEGORY_COLUMN = 6;
/*     */   private static final int REPORT_DELETE_COLUMN = 7;
/*     */   private Composite composite_report_img_area;
/*     */   private Combo combo_xaixs_column;
/*     */   private Combo combo_yaixs_column;
/*     */   private Combo combo_category_column;
/*     */   private Button radio_count_record;
/*     */   private Button radio_count_column_value;
/*     */   private Button btn_add_report;
/*     */   private Button check_box_accumulate;
/*     */   private Combo combo_count_value_column;
/*     */   private Shell shell;
/*     */   private Text text_relate_table;
/*     */   private Combo combo_category_displayName;
/*     */ 
/*     */   public ReportFunctionUI(Composite parent)
/*     */   {
/*  68 */     this.shell = parent.getShell();
/*  69 */     createContents(parent);
/*     */   }
/*     */ 
/*     */   protected Control createContents(Composite container)
/*     */   {
/*  74 */     Label lblNewLabel = new Label(container, 0);
/*  75 */     lblNewLabel.setBounds(10, 20, 45, 17);
/*  76 */     lblNewLabel.setText("选择表");
/*     */ 
/*  79 */     this.combo_select_table = new Combo(container, 8);
/*  80 */     this.combo_select_table.setBounds(61, 17, 161, 25);
/*  81 */     this.combo_select_table.setItems(DBTablesUtil.getSelectedTableNames());
/*  82 */     this.combo_select_table.addSelectionListener(new DBTableSelectListener(null));
/*     */ 
/*  86 */     Label lblNewLabel = new Label(container, 0);
/*  87 */     lblNewLabel.setBounds(10, 65, 60, 17);
/*  88 */     lblNewLabel.setText("配置报表：");
/*     */ 
/*  90 */     boolean isEnable = !StringUtil.isEmpty(this.combo_select_table.getText());
/*     */ 
/*  92 */     Composite composite_1 = new Composite(container, 2048);
/*  93 */     composite_1.setLayout(null);
/*  94 */     composite_1.setBounds(10, 85, 498, 208);
/*     */ 
/*  96 */     Label label = new Label(composite_1, 0);
/*  97 */     label.setText("报表类型：");
/*  98 */     label.setBounds(14, 14, 60, 17);
/*     */ 
/* 101 */     this.combo_report_type = new Combo(composite_1, 8);
/* 102 */     this.combo_report_type.setBounds(79, 10, 158, 25);
/* 103 */     this.combo_report_type.setItems(ChartType.getChartDisplayNames());
/* 104 */     this.combo_report_type.addSelectionListener(new ChartTypeSelectListener(null));
/* 105 */     this.combo_report_type.setEnabled(isEnable);
/*     */ 
/* 108 */     this.composite_report_img_area = new Composite(composite_1, 2048);
/* 109 */     this.composite_report_img_area.setBounds(300, 10, 139, 125);
/*     */ 
/* 112 */     Label label = new Label(composite_1, 131072);
/* 113 */     label.setText("统计类型：");
/* 114 */     label.setBounds(14, 41, 60, 17);
/*     */ 
/* 117 */     this.radio_count_record = new Button(composite_1, 16);
/* 118 */     this.radio_count_record.setText("统计记录数");
/* 119 */     this.radio_count_record.setBounds(79, 41, 80, 17);
/* 120 */     this.radio_count_record.addSelectionListener(new CountRecordCountListener(null));
/* 121 */     this.radio_count_record.setEnabled(isEnable);
/*     */ 
/* 124 */     this.radio_count_column_value = new Button(composite_1, 16);
/* 125 */     this.radio_count_column_value.setText("统计列值");
/* 126 */     this.radio_count_column_value.setBounds(168, 41, 69, 17);
/* 127 */     this.radio_count_column_value.addSelectionListener(new CountColumnValueListener(null));
/* 128 */     this.radio_count_column_value.setEnabled(isEnable);
/*     */ 
/* 131 */     Label label = new Label(composite_1, 131072);
/* 132 */     label.setText("横坐标列：");
/* 133 */     label.setBounds(10, 91, 64, 17);
/*     */ 
/* 136 */     this.combo_xaixs_column = new Combo(composite_1, 8);
/* 137 */     this.combo_xaixs_column.setBounds(79, 87, 158, 25);
/* 138 */     this.combo_xaixs_column.setEnabled(isEnable);
/*     */ 
/* 141 */     Label label = new Label(composite_1, 0);
/* 142 */     label.setText("纵坐标列：");
/* 143 */     label.setBounds(14, 121, 60, 17);
/*     */ 
/* 146 */     this.combo_yaixs_column = new Combo(composite_1, 8);
/* 147 */     this.combo_yaixs_column.setBounds(79, 117, 158, 25);
/* 148 */     this.combo_yaixs_column.setEnabled(isEnable);
/*     */ 
/* 151 */     this.btn_add_report = new Button(composite_1, 0);
/* 152 */     this.btn_add_report.setText("添加");
/* 153 */     this.btn_add_report.setBounds(359, 141, 80, 27);
/* 154 */     this.btn_add_report.addSelectionListener(new AddReportToDBTableListener(null));
/*     */ 
/* 157 */     Label label = new Label(composite_1, 0);
/* 158 */     label.setText("分类字段：");
/* 159 */     label.setBounds(14, 181, 61, 17);
/*     */ 
/* 162 */     this.combo_category_column = new Combo(composite_1, 8);
/* 163 */     this.combo_category_column.setBounds(81, 178, 80, 25);
/* 164 */     this.combo_category_column.addSelectionListener(new CategoryColumnListener());
/* 165 */     this.combo_category_column.setEnabled(isEnable);
/*     */ 
/* 168 */     this.check_box_accumulate = new Button(composite_1, 32);
/* 169 */     this.check_box_accumulate.setBounds(79, 64, 98, 17);
/* 170 */     this.check_box_accumulate.setText("按时间段累加");
/* 171 */     this.check_box_accumulate.addSelectionListener(new AccumulateButtonListener(null));
/* 172 */     this.check_box_accumulate.setEnabled(isEnable);
/*     */ 
/* 174 */     Label label = new Label(composite_1, 0);
/* 175 */     label.setBounds(14, 153, 61, 17);
/* 176 */     label.setText("统计值列：");
/*     */ 
/* 178 */     this.combo_count_value_column = new Combo(composite_1, 8);
/* 179 */     this.combo_count_value_column.setBounds(79, 148, 158, 25);
/* 180 */     this.combo_count_value_column.setEnabled(isEnable);
/*     */ 
/* 182 */     Label label_1 = new Label(composite_1, 0);
/* 183 */     label_1.setBounds(168, 181, 48, 17);
/* 184 */     label_1.setText("关联表：");
/*     */ 
/* 186 */     this.text_relate_table = new Text(composite_1, 2056);
/* 187 */     this.text_relate_table.setBounds(218, 178, 73, 23);
/* 188 */     this.text_relate_table.setEditable(false);
/*     */ 
/* 190 */     Label label_2 = new Label(composite_1, 0);
/* 191 */     label_2.setBounds(304, 181, 48, 17);
/* 192 */     label_2.setText("分类名：");
/*     */ 
/* 194 */     this.combo_category_displayName = new Combo(composite_1, 8);
/* 195 */     this.combo_category_displayName.setBounds(358, 178, 80, 25);
/* 196 */     this.combo_category_displayName.setEnabled(false);
/*     */ 
/* 201 */     Label lblNewLabel = new Label(container, 0);
/* 202 */     lblNewLabel.setBounds(10, 295, 45, 17);
/* 203 */     lblNewLabel.setText("已添加：");
/*     */ 
/* 206 */     this.table = new Table(container, 67584);
/* 207 */     this.table.setBounds(10, 315, 498, 203);
/* 208 */     this.table.setHeaderVisible(true);
/* 209 */     this.table.setLinesVisible(true);
/*     */ 
/* 211 */     org.eclipse.swt.widgets.TableColumn tableColumn = new org.eclipse.swt.widgets.TableColumn(this.table, 0);
/* 212 */     tableColumn.setWidth(60);
/* 213 */     tableColumn.setText("报表类型");
/*     */ 
/* 216 */     org.eclipse.swt.widgets.TableColumn tableColumn = new org.eclipse.swt.widgets.TableColumn(this.table, 0);
/* 217 */     tableColumn.setWidth(64);
/* 218 */     tableColumn.setText("统计记录");
/*     */ 
/* 221 */     org.eclipse.swt.widgets.TableColumn tableColumn = new org.eclipse.swt.widgets.TableColumn(this.table, 0);
/* 222 */     tableColumn.setWidth(100);
/* 223 */     tableColumn.setText("统计列值");
/*     */ 
/* 226 */     org.eclipse.swt.widgets.TableColumn tableColumn = new org.eclipse.swt.widgets.TableColumn(this.table, 0);
/* 227 */     tableColumn.setWidth(40);
/* 228 */     tableColumn.setText("累加");
/*     */ 
/* 231 */     org.eclipse.swt.widgets.TableColumn tableColumn = new org.eclipse.swt.widgets.TableColumn(this.table, 0);
/* 232 */     tableColumn.setWidth(100);
/* 233 */     tableColumn.setText("横轴列");
/*     */ 
/* 236 */     org.eclipse.swt.widgets.TableColumn tableColumn = new org.eclipse.swt.widgets.TableColumn(this.table, 0);
/* 237 */     tableColumn.setWidth(100);
/* 238 */     tableColumn.setText("纵轴列");
/*     */ 
/* 241 */     org.eclipse.swt.widgets.TableColumn tableColumn = new org.eclipse.swt.widgets.TableColumn(this.table, 0);
/* 242 */     tableColumn.setWidth(100);
/* 243 */     tableColumn.setText("分类字段");
/*     */ 
/* 246 */     org.eclipse.swt.widgets.TableColumn tableColumn = new org.eclipse.swt.widgets.TableColumn(this.table, 0);
/* 247 */     tableColumn.setWidth(50);
/* 248 */     tableColumn.setText("删除");
/*     */ 
/* 252 */     this.combo_select_table.getText();
/*     */ 
/* 263 */     return container;
/*     */   }
/*     */ 
/*     */   private void addTableItem(ReportFunction reportFunction)
/*     */   {
/* 381 */     TableItem tableItem = new TableItem(this.table, 0);
/* 382 */     tableItem.setData(reportFunction.getUUID_KEY());
/* 383 */     tableItem.setText(0, reportFunction.getChartType().getDisplayName());
/* 384 */     Boolean isCountRecord = reportFunction.getIsCountRecord();
/* 385 */     tableItem.setText(1, isCountRecord.booleanValue() ? "是" : "");
/* 386 */     com.tianshouzhi.autopro.domain.db.TableColumn valueColumn = reportFunction.getCountValueColumn();
/* 387 */     tableItem.setText(2, valueColumn != null ? valueColumn.getColumnName() : "");
/* 388 */     Boolean isAccumulate = reportFunction.getIsAccumulate();
/* 389 */     tableItem.setText(3, isAccumulate.booleanValue() ? "是" : "");
/* 390 */     com.tianshouzhi.autopro.domain.db.TableColumn axisColumn = reportFunction.getXaxis();
/* 391 */     tableItem.setText(4, axisColumn != null ? axisColumn.getColumnName() : "");
/* 392 */     com.tianshouzhi.autopro.domain.db.TableColumn yxisColumn = reportFunction.getYaxis();
/* 393 */     tableItem.setText(5, yxisColumn != null ? yxisColumn.getColumnName() : "");
/* 394 */     com.tianshouzhi.autopro.domain.db.TableColumn categoryColumn = reportFunction.getCategorizedColumn();
/* 395 */     tableItem.setText(6, categoryColumn != null ? categoryColumn.getColumnName() : "");
/* 396 */     tableItem.setText(7, "删除");
/* 397 */     TableEditor deleteEditor = new TableEditor(this.table);
/* 398 */     deleteEditor.grabHorizontal = true;
/* 399 */     Button button = new Button(this.table, 0);
/* 400 */     int tableItemIndex = this.table.getItemCount() - 1;
/* 401 */     if ((this.table.getItemCount() == 0) && 
/* 402 */       (tableItemIndex < 0)) {
/* 403 */       tableItemIndex = 0;
/*     */     }
/*     */ 
/* 406 */     button.addSelectionListener(new DeleteTableReportFunctionListener(tableItemIndex));
/* 407 */     button.setText("删除");
/* 408 */     deleteEditor.setEditor(button, tableItem, 7);
/*     */   }
/*     */ 
/*     */   private class AccumulateButtonListener extends SelectionAdapter
/*     */   {
/*     */     private AccumulateButtonListener()
/*     */     {
/*     */     }
/*     */ 
/*     */     public void widgetSelected(SelectionEvent e)
/*     */     {
/* 485 */       String tableName = ReportFunctionUI.this.combo_select_table.getText();
/* 486 */       if (StringUtil.isEmpty(tableName)) {
/* 487 */         return;
/*     */       }
/* 489 */       if (ReportFunctionUI.this.check_box_accumulate.getSelection()) {
/* 490 */         ReportFunctionUI.this.combo_xaixs_column.setEnabled(true);
/* 491 */         String[] dateColumnNames = DBTablesUtil.getDateColumnNames(tableName);
/* 492 */         if ((dateColumnNames != null) && (dateColumnNames.length > 0)) {
/* 493 */           ReportFunctionUI.this.combo_xaixs_column.setItems(dateColumnNames);
/* 494 */           ReportFunctionUI.this.combo_xaixs_column.setText(dateColumnNames[0]);
/*     */         }
/*     */       } else {
/* 497 */         ReportFunctionUI.this.combo_xaixs_column.setItems(DBTablesUtil.getSelectedDbTableColumnNames(tableName));
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   private class AddReportToDBTableListener extends SelectionAdapter
/*     */   {
/*     */     private AddReportToDBTableListener()
/*     */     {
/*     */     }
/*     */ 
/*     */     public void widgetSelected(SelectionEvent e)
/*     */     {
/* 330 */       String tableName = ReportFunctionUI.this.combo_select_table.getText();
/* 331 */       String chartType = ReportFunctionUI.this.combo_report_type.getText();
/* 332 */       if ((StringUtil.isEmpty(tableName)) || (StringUtil.isEmpty(chartType))) {
/* 333 */         return;
/*     */       }
/*     */ 
/* 338 */       String xaixsColumnName = null;
/* 339 */       String yaixsColumnName = null;
/*     */ 
/* 341 */       if ((chartType.equals(
/* 341 */         Boolean.valueOf((ChartType.LINE.getDisplayName().equals(chartType)) || 
/* 341 */         (chartType.equals(ChartType.COLUMN.getDisplayName()))))) || 
/* 342 */         (chartType.equals(ChartType.MULTI_LINE.getDisplayName())) || 
/* 343 */         (chartType.equals(ChartType.STACK_COLUMN.getDisplayName())) || 
/* 344 */         (chartType.equals(ChartType.MULTI_COLUMN.getDisplayName()))) {
/* 345 */         xaixsColumnName = ReportFunctionUI.this.combo_xaixs_column.getText();
/* 346 */         yaixsColumnName = ReportFunctionUI.this.combo_yaixs_column.getText();
/*     */       }
/*     */ 
/* 350 */       String category_column_name = ReportFunctionUI.this.combo_category_column.getText();
/*     */ 
/* 353 */       boolean isCountColumnValue = ReportFunctionUI.this.radio_count_column_value.getSelection();
/* 354 */       boolean isCountRecord = ReportFunctionUI.this.radio_count_record.getSelection();
/* 355 */       boolean isAccumulate = ReportFunctionUI.this.check_box_accumulate.getSelection();
/* 356 */       String countValueColumnName = ReportFunctionUI.this.combo_count_value_column.getText();
/*     */ 
/* 358 */       ReportFunction reportFunction = new ReportFunction();
/* 359 */       reportFunction.setChartType(ChartType.getChartTypeByDisplayName(chartType));
/* 360 */       com.tianshouzhi.autopro.domain.db.TableColumn categoryColumn = DBTablesUtil.getTableColumn(tableName, category_column_name);
/* 361 */       reportFunction.setCategorizedColumn(categoryColumn);
/* 362 */       reportFunction.setIsAccumulate(Boolean.valueOf(isAccumulate));
/* 363 */       reportFunction.setXaxis(DBTablesUtil.getTableColumn(tableName, xaixsColumnName));
/* 364 */       reportFunction.setYaxis(DBTablesUtil.getTableColumn(tableName, yaixsColumnName));
/* 365 */       reportFunction.setIsCountRecord(Boolean.valueOf(isCountRecord));
/* 366 */       if (isCountColumnValue) {
/* 367 */         reportFunction.setCountValueColumn(DBTablesUtil.getTableColumn(tableName, countValueColumnName));
/*     */       }
/* 369 */       DBTablesUtil.addTableFunction(tableName, reportFunction);
/*     */ 
/* 372 */       ReportFunctionUI.this.addTableItem(reportFunction);
/*     */     }
/*     */   }
/*     */ 
/*     */   class CategoryColumnListener extends SelectionAdapter
/*     */   {
/*     */     CategoryColumnListener()
/*     */     {
/*     */     }
/*     */ 
/*     */     public void widgetSelected(SelectionEvent e)
/*     */     {
/* 572 */       String categoryColumnName = ReportFunctionUI.this.combo_category_column.getText();
/* 573 */       String tableName = ReportFunctionUI.this.combo_select_table.getText();
/* 574 */       com.tianshouzhi.autopro.domain.db.TableColumn tableColumn = DBTablesUtil.getTableColumn(tableName, categoryColumnName);
/* 575 */       Boolean isForeignKey = tableColumn.getIsForeignKey();
/* 576 */       if (isForeignKey.booleanValue()) {
/* 577 */         ReportFunctionUI.this.combo_category_displayName.setEnabled(true);
/* 578 */         DBTable relateTable = DBTablesUtil.getRelateTable(tableName, categoryColumnName);
/* 579 */         ReportFunctionUI.this.text_relate_table.setText(relateTable.getTableName());
/* 580 */         ReportFunctionUI.this.combo_category_displayName.setItems(relateTable.getColumnNames());
/*     */       } else {
/* 582 */         ReportFunctionUI.this.combo_category_displayName.setEnabled(false);
/* 583 */         ReportFunctionUI.this.combo_category_displayName.setText("");
/* 584 */         ReportFunctionUI.this.text_relate_table.setText("");
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   private class ChartTypeSelectListener extends SelectionAdapter
/*     */   {
/*     */     private ChartTypeSelectListener()
/*     */     {
/*     */     }
/*     */ 
/*     */     public void widgetSelected(SelectionEvent e)
/*     */     {
/* 511 */       String chartDisplayName = ReportFunctionUI.this.combo_report_type.getText();
/* 512 */       if ((chartDisplayName == null) || (StringUtil.isEmpty(chartDisplayName))) {
/* 513 */         return;
/*     */       }
/*     */ 
/* 517 */       ChartType chart = ChartType.getChartTypeByDisplayName(chartDisplayName);
/* 518 */       String imgurl = chart.getImgurl();
/* 519 */       Image image = SWTResourceManager.getImage(PluginUtil.getResourceFile(imgurl).getAbsolutePath());
/* 520 */       ReportFunctionUI.this.composite_report_img_area.setBackgroundImage(image);
/*     */ 
/* 524 */       ReportFunctionUI.this.radio_count_column_value.setEnabled(true);
/* 525 */       ReportFunctionUI.this.radio_count_record.setEnabled(true);
/* 526 */       String tableName = ReportFunctionUI.this.combo_select_table.getText();
/* 527 */       String[] dateColumnNames = DBTablesUtil.getDateColumnNames(tableName);
/*     */ 
/* 529 */       if ((dateColumnNames != null) && (dateColumnNames.length > 0))
/* 530 */         ReportFunctionUI.this.check_box_accumulate.setEnabled(true);
/*     */       else {
/* 532 */         ReportFunctionUI.this.check_box_accumulate.setEnabled(false);
/*     */       }
/* 534 */       if ((chartDisplayName.equals(ChartType.LINE.getDisplayName())) || 
/* 535 */         (chartDisplayName.equals(ChartType.COLUMN.getDisplayName()))) {
/* 536 */         ReportFunctionUI.this.combo_category_column.setEnabled(false);
/* 537 */         ReportFunctionUI.this.combo_category_column.setText("");
/* 538 */         ReportFunctionUI.this.combo_xaixs_column.setEnabled(true);
/* 539 */         ReportFunctionUI.this.combo_yaixs_column.setEnabled(true);
/*     */       }
/*     */ 
/* 544 */       if ((chartDisplayName.equals(ChartType.MULTI_COLUMN.getDisplayName())) || 
/* 545 */         (chartDisplayName.equals(ChartType.STACK_COLUMN.getDisplayName())) || 
/* 546 */         (chartDisplayName.equals(ChartType.MULTI_LINE.getDisplayName()))) {
/* 547 */         ReportFunctionUI.this.combo_xaixs_column.setEnabled(true);
/* 548 */         ReportFunctionUI.this.combo_yaixs_column.setEnabled(true);
/* 549 */         ReportFunctionUI.this.combo_category_column.setEnabled(true);
/*     */       }
/*     */ 
/* 552 */       if (chartDisplayName.equals(ChartType.PIE.getDisplayName())) {
/* 553 */         ReportFunctionUI.this.combo_xaixs_column.setEnabled(false);
/* 554 */         ReportFunctionUI.this.combo_yaixs_column.setEnabled(false);
/* 555 */         ReportFunctionUI.this.combo_category_column.setEnabled(true);
/* 556 */         ReportFunctionUI.this.radio_count_record.setSelection(true);
/*     */       }
/*     */ 
/* 560 */       if (ReportFunctionUI.this.radio_count_record.getSelection())
/* 561 */         ReportFunctionUI.this.combo_yaixs_column.setEnabled(false);
/*     */     }
/*     */   }
/*     */ 
/*     */   private class CountColumnValueListener extends SelectionAdapter
/*     */   {
/*     */     private CountColumnValueListener()
/*     */     {
/*     */     }
/*     */ 
/*     */     public void widgetSelected(SelectionEvent e)
/*     */     {
/* 471 */       ReportFunctionUI.this.combo_count_value_column.setEnabled(true);
/* 472 */       ReportFunctionUI.this.combo_yaixs_column.setEnabled(false);
/*     */     }
/*     */   }
/*     */ 
/*     */   private class CountRecordCountListener extends SelectionAdapter
/*     */   {
/*     */     private CountRecordCountListener()
/*     */     {
/*     */     }
/*     */ 
/*     */     public void widgetSelected(SelectionEvent e)
/*     */     {
/* 452 */       ReportFunctionUI.this.combo_count_value_column.setEnabled(false);
/* 453 */       ReportFunctionUI.this.combo_xaixs_column.setEnabled(true);
/* 454 */       ReportFunctionUI.this.combo_yaixs_column.setEnabled(false);
/* 455 */       if (ChartType.PIE.getDisplayName().equals(ReportFunctionUI.this.combo_report_type.getText()))
/* 456 */         ReportFunctionUI.this.combo_xaixs_column.setEnabled(false);
/*     */     }
/*     */   }
/*     */ 
/*     */   private class DBTableSelectListener extends SelectionAdapter
/*     */   {
/*     */     private DBTableSelectListener()
/*     */     {
/*     */     }
/*     */ 
/*     */     public void widgetSelected(SelectionEvent e)
/*     */     {
/* 275 */       String tableName = ReportFunctionUI.this.combo_select_table.getText();
/* 276 */       if (StringUtil.isEmpty(tableName)) {
/* 277 */         return;
/*     */       }
/*     */ 
/* 281 */       ReportFunctionUI.this.combo_report_type.setEnabled(true);
/* 282 */       ReportFunctionUI.this.combo_category_column.setEnabled(false);
/* 283 */       ReportFunctionUI.this.combo_count_value_column.setEnabled(false);
/* 284 */       ReportFunctionUI.this.combo_xaixs_column.setEnabled(false);
/* 285 */       ReportFunctionUI.this.combo_yaixs_column.setEnabled(false);
/*     */ 
/* 287 */       String[] columnNames = DBTablesUtil.getSelectedDbTableColumnNamesFirstBlank(tableName);
/* 288 */       ReportFunctionUI.this.combo_category_column.setItems(columnNames);
/* 289 */       ReportFunctionUI.this.combo_xaixs_column.setItems(columnNames);
/* 290 */       ReportFunctionUI.this.combo_yaixs_column.setItems(columnNames);
/* 291 */       ReportFunctionUI.this.combo_count_value_column.setItems(columnNames);
/*     */ 
/* 294 */       ReportFunctionUI.this.table.removeAll();
/* 295 */       String dbTableName = ReportFunctionUI.this.combo_select_table.getText();
/* 296 */       List reportFunctions = DBTablesUtil.getReportFunctions(dbTableName);
/* 297 */       if ((reportFunctions != null) && (reportFunctions.size() > 0)) {
/* 298 */         for (ReportFunction reportFunction : reportFunctions) {
/* 299 */           ReportFunctionUI.this.addTableItem(reportFunction);
/*     */         }
/*     */ 
/*     */       }
/*     */ 
/* 304 */       ReportFunctionUI.this.combo_xaixs_column.setEnabled(false);
/* 305 */       ReportFunctionUI.this.combo_yaixs_column.setEnabled(false);
/* 306 */       ReportFunctionUI.this.combo_count_value_column.setEnabled(false);
/*     */ 
/* 308 */       String chartDisplayName = ReportFunctionUI.this.combo_report_type.getText();
/* 309 */       if ((chartDisplayName != null) && (
/* 310 */         (chartDisplayName.equals(ChartType.STACK_COLUMN.getDisplayName())) || 
/* 311 */         (chartDisplayName.equals(ChartType.MULTI_COLUMN.getDisplayName())) || 
/* 312 */         (chartDisplayName.equals(ChartType.MULTI_LINE.getDisplayName())) || 
/* 313 */         (chartDisplayName.equals(ChartType.PIE.getDisplayName()))))
/* 314 */         ReportFunctionUI.this.combo_category_column.setEnabled(true);
/*     */     }
/*     */   }
/*     */ 
/*     */   private class DeleteTableReportFunctionListener extends SelectionAdapter
/*     */   {
/*     */     private int tableItemIndex;
/*     */ 
/*     */     public DeleteTableReportFunctionListener(int tableItemIndex)
/*     */     {
/* 419 */       this.tableItemIndex = tableItemIndex;
/*     */     }
/*     */ 
/*     */     public void widgetSelected(SelectionEvent e)
/*     */     {
/* 424 */       String selectedTable = ReportFunctionUI.this.combo_select_table.getText();
/* 425 */       if (StringUtil.isEmpty(selectedTable)) {
/* 426 */         return;
/*     */       }
/*     */ 
/* 429 */       boolean deleteConfirm = MessageDialog.openConfirm(ReportFunctionUI.this.shell, "删除", "你确定要删除吗");
/* 430 */       if (!deleteConfirm) {
/* 431 */         return;
/*     */       }
/*     */ 
/* 434 */       TableItem tableItem = ReportFunctionUI.this.table.getItem(this.tableItemIndex);
/* 435 */       String UUID_KEY = (String)tableItem.getData();
/* 436 */       DBTablesUtil.removeFunction(ReportFunctionUI.this.combo_select_table.getText(), UUID_KEY);
/*     */ 
/* 438 */       ReportFunctionUI.this.table.remove(this.tableItemIndex);
/*     */ 
/* 440 */       Button delbutton = (Button)e.getSource();
/* 441 */       delbutton.dispose();
/*     */     }
/*     */   }
/*     */ }

/* Location:           D:\MyEclipse2015CI\dropins\com.tianshouzhi.autopro_1.0.0.201506242235.jar
 * Qualified Name:     com.tianshouzhi.autopro.ui.wizard.page.extra.tab_functions_ui.ReportFunctionUI
 * JD-Core Version:    0.6.2
 */