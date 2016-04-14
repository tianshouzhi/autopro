/*     */ package test;
/*     */ 
/*     */ import org.eclipse.jface.window.ApplicationWindow;
/*     */ import org.eclipse.swt.custom.CTabFolder;
/*     */ import org.eclipse.swt.custom.CTabItem;
/*     */ import org.eclipse.swt.custom.ScrolledComposite;
/*     */ import org.eclipse.swt.events.SelectionAdapter;
/*     */ import org.eclipse.swt.events.SelectionEvent;
/*     */ import org.eclipse.swt.graphics.Point;
/*     */ import org.eclipse.swt.layout.GridData;
/*     */ import org.eclipse.swt.layout.GridLayout;
/*     */ import org.eclipse.swt.widgets.Button;
/*     */ import org.eclipse.swt.widgets.Combo;
/*     */ import org.eclipse.swt.widgets.Composite;
/*     */ import org.eclipse.swt.widgets.Control;
/*     */ import org.eclipse.swt.widgets.Display;
/*     */ import org.eclipse.swt.widgets.Label;
/*     */ import org.eclipse.swt.widgets.List;
/*     */ import org.eclipse.swt.widgets.Shell;
/*     */ import org.eclipse.swt.widgets.Table;
/*     */ import org.eclipse.swt.widgets.TableColumn;
/*     */ import org.eclipse.wb.swt.SWTResourceManager;
/*     */ 
/*     */ public class TestTabLayout extends ApplicationWindow
/*     */ {
/*     */   private Button btn_addFunction;
/*     */   private Button btn_editFunction;
/*     */   private Button btn_delFunction;
/*     */   private Table table;
/*     */ 
/*     */   public TestTabLayout()
/*     */   {
/*  35 */     super(null);
/*  36 */     addToolBar(8388672);
/*  37 */     addMenuBar();
/*  38 */     addStatusLine();
/*     */   }
/*     */ 
/*     */   protected Control createContents(Composite parent)
/*     */   {
/*  47 */     Composite container = new Composite(parent, 0);
/*     */ 
/*  49 */     Composite composite_1 = new Composite(container, 2048);
/*  50 */     composite_1.setBounds(10, 10, 101, 27);
/*     */ 
/*  52 */     Label label = new Label(composite_1, 0);
/*  53 */     label.setBounds(22, 0, 53, 17);
/*  54 */     label.setText("已选功能");
/*     */ 
/*  56 */     CTabFolder tabFolder = new CTabFolder(container, 2048);
/*  57 */     tabFolder.setSelectionBackground(SWTResourceManager.getColor(35));
/*  58 */     tabFolder.setBounds(117, 10, 392, 354);
/*     */ 
/*  60 */     CTabItem tabItem_4 = new CTabItem(tabFolder, 0);
/*  61 */     tabItem_4.setText("New Item");
/*     */ 
/*  63 */     Composite composite = new Composite(tabFolder, 0);
/*  64 */     tabItem_4.setControl(composite);
/*     */ 
/*  66 */     ScrolledComposite scrolledComposite = new ScrolledComposite(composite, 2816);
/*  67 */     scrolledComposite.setBounds(0, 0, 386, 325);
/*  68 */     scrolledComposite.setExpandHorizontal(true);
/*  69 */     scrolledComposite.setExpandVertical(true);
/*     */ 
/*  71 */     Composite composite_2 = new Composite(scrolledComposite, 0);
/*     */ 
/*  73 */     Composite composite_3 = new Composite(composite_2, 2048);
/*  74 */     composite_3.setBounds(10, 283, 347, 143);
/*  75 */     composite_3.setLayout(new GridLayout(1, false));
/*     */ 
/*  77 */     Label label_1 = new Label(composite_3, 0);
/*  78 */     label_1.setText(" 已添加：");
/*  79 */     label_1.setForeground(SWTResourceManager.getColor(6));
/*     */ 
/*  81 */     this.table = new Table(composite_3, 67584);
/*  82 */     this.table.setLinesVisible(true);
/*  83 */     this.table.setHeaderVisible(true);
/*  84 */     this.table.setFont(SWTResourceManager.getFont("微软雅黑", 8, 0));
/*  85 */     this.table.setLayoutData(new GridData(4, 4, true, true, 1, 1));
/*     */ 
/*  87 */     TableColumn tableColumn = new TableColumn(this.table, 0);
/*  88 */     tableColumn.setWidth(60);
/*  89 */     tableColumn.setText("报表类型");
/*     */ 
/*  91 */     TableColumn tableColumn_1 = new TableColumn(this.table, 0);
/*  92 */     tableColumn_1.setWidth(64);
/*  93 */     tableColumn_1.setText("统计记录");
/*     */ 
/*  95 */     TableColumn tableColumn_2 = new TableColumn(this.table, 0);
/*  96 */     tableColumn_2.setWidth(59);
/*  97 */     tableColumn_2.setText("统计列值");
/*     */ 
/*  99 */     TableColumn tableColumn_3 = new TableColumn(this.table, 0);
/* 100 */     tableColumn_3.setWidth(69);
/* 101 */     tableColumn_3.setText("横轴");
/*     */ 
/* 103 */     TableColumn tableColumn_4 = new TableColumn(this.table, 0);
/* 104 */     tableColumn_4.setWidth(67);
/* 105 */     tableColumn_4.setText("纵轴");
/*     */ 
/* 107 */     TableColumn tableColumn_5 = new TableColumn(this.table, 0);
/* 108 */     tableColumn_5.setWidth(59);
/* 109 */     tableColumn_5.setText("分类字段");
/*     */ 
/* 111 */     TableColumn tableColumn_6 = new TableColumn(this.table, 0);
/* 112 */     tableColumn_6.setWidth(34);
/* 113 */     tableColumn_6.setText("删除");
/*     */ 
/* 115 */     Composite composite_4 = new Composite(composite_2, 2048);
/* 116 */     composite_4.setLayout(null);
/* 117 */     composite_4.setBounds(10, 68, 347, 203);
/*     */ 
/* 119 */     Label label_2 = new Label(composite_4, 0);
/* 120 */     label_2.setText("报表类型：");
/* 121 */     label_2.setBounds(14, 14, 60, 17);
/*     */ 
/* 123 */     Combo combo = new Combo(composite_4, 8);
/* 124 */     combo.setBounds(79, 10, 142, 23);
/*     */ 
/* 126 */     Composite composite_5 = new Composite(composite_4, 2048);
/* 127 */     composite_5.setBounds(227, 14, 107, 99);
/*     */ 
/* 129 */     Label label_3 = new Label(composite_4, 131072);
/* 130 */     label_3.setText("统计类型：");
/* 131 */     label_3.setBounds(14, 41, 60, 17);
/*     */ 
/* 133 */     Button button = new Button(composite_4, 16);
/* 134 */     button.setText("记录");
/* 135 */     button.setBounds(79, 41, 69, 17);
/*     */ 
/* 137 */     Label label_4 = new Label(composite_4, 0);
/* 138 */     label_4.setText("是否累加：");
/* 139 */     label_4.setBounds(14, 64, 60, 17);
/*     */ 
/* 141 */     Button button_1 = new Button(composite_4, 16);
/* 142 */     button_1.setText("是");
/* 143 */     button_1.setBounds(79, 64, 33, 17);
/*     */ 
/* 145 */     Button button_2 = new Button(composite_4, 16);
/* 146 */     button_2.setText("列值");
/* 147 */     button_2.setBounds(168, 41, 52, 17);
/*     */ 
/* 149 */     Button button_3 = new Button(composite_4, 16);
/* 150 */     button_3.setText("否");
/* 151 */     button_3.setBounds(168, 64, 33, 17);
/*     */ 
/* 153 */     Label label_5 = new Label(composite_4, 131072);
/* 154 */     label_5.setText("横坐标列：");
/* 155 */     label_5.setBounds(10, 91, 64, 17);
/*     */ 
/* 157 */     Combo combo_1 = new Combo(composite_4, 8);
/* 158 */     combo_1.setBounds(79, 87, 142, 23);
/*     */ 
/* 160 */     Label label_6 = new Label(composite_4, 0);
/* 161 */     label_6.setText("分类字段：");
/* 162 */     label_6.setBounds(14, 121, 60, 17);
/*     */ 
/* 164 */     Combo combo_2 = new Combo(composite_4, 8);
/* 165 */     combo_2.setBounds(79, 117, 142, 23);
/*     */ 
/* 167 */     Button button_4 = new Button(composite_4, 0);
/* 168 */     button_4.setText("添加");
/* 169 */     button_4.setBounds(254, 119, 80, 27);
/*     */ 
/* 171 */     Label label_7 = new Label(composite_4, 0);
/* 172 */     label_7.setText("纵坐标列：");
/* 173 */     label_7.setBounds(14, 155, 61, 17);
/*     */ 
/* 175 */     Combo combo_3 = new Combo(composite_4, 8);
/* 176 */     combo_3.setBounds(79, 147, 142, 23);
/*     */ 
/* 178 */     Label label_8 = new Label(composite_2, 0);
/* 179 */     label_8.setText("数据库表：");
/* 180 */     label_8.setBounds(15, 13, 61, 17);
/*     */ 
/* 182 */     Label label_9 = new Label(composite_2, 131072);
/* 183 */     label_9.setText("添加报表：");
/* 184 */     label_9.setBounds(10, 41, 66, 17);
/*     */ 
/* 186 */     Combo combo_4 = new Combo(composite_2, 8);
/* 187 */     combo_4.setBounds(90, 10, 156, 23);
/* 188 */     scrolledComposite.setContent(composite_2);
/* 189 */     scrolledComposite.setMinSize(composite_2.computeSize(-1, -1));
/*     */ 
/* 191 */     this.btn_addFunction = new Button(container, 0);
/* 192 */     this.btn_addFunction.addSelectionListener(new SelectionAdapter()
/*     */     {
/*     */       public void widgetSelected(SelectionEvent e)
/*     */       {
/*     */       }
/*     */     });
/* 197 */     this.btn_addFunction.setBounds(67, 385, 80, 27);
/* 198 */     this.btn_addFunction.setText("添加");
/*     */ 
/* 200 */     this.btn_editFunction = new Button(container, 0);
/* 201 */     this.btn_editFunction.addSelectionListener(new SelectionAdapter()
/*     */     {
/*     */       public void widgetSelected(SelectionEvent e)
/*     */       {
/*     */       }
/*     */     });
/* 206 */     this.btn_editFunction.setBounds(216, 385, 80, 27);
/* 207 */     this.btn_editFunction.setText("修改");
/*     */ 
/* 209 */     this.btn_delFunction = new Button(container, 0);
/* 210 */     this.btn_delFunction.addSelectionListener(new SelectionAdapter()
/*     */     {
/*     */       public void widgetSelected(SelectionEvent e)
/*     */       {
/*     */       }
/*     */     });
/* 215 */     this.btn_delFunction.setBounds(365, 385, 80, 27);
/* 216 */     this.btn_delFunction.setText("删除");
/*     */ 
/* 218 */     List list = new List(container, 2048);
/* 219 */     list.setBounds(10, 35, 101, 329);
/*     */ 
/* 221 */     return container;
/*     */   }
/*     */ 
/*     */   public static void main(String[] args)
/*     */   {
/*     */     try
/*     */     {
/* 232 */       TestTabLayout window = new TestTabLayout();
/* 233 */       window.setBlockOnOpen(true);
/* 234 */       window.open();
/* 235 */       Display.getCurrent().dispose();
/*     */     } catch (Exception e) {
/* 237 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   protected void configureShell(Shell newShell)
/*     */   {
/* 247 */     super.configureShell(newShell);
/* 248 */     newShell.setText("New Application");
/*     */   }
/*     */ 
/*     */   protected Point getInitialSize()
/*     */   {
/* 256 */     return new Point(535, 489);
/*     */   }
/*     */ }

/* Location:           D:\MyEclipse2015CI\dropins\com.tianshouzhi.autopro_1.0.0.201506242235.jar
 * Qualified Name:     test.TestTabLayout
 * JD-Core Version:    0.6.2
 */