/*     */ package test;
/*     */ 
/*     */ import org.eclipse.jface.action.MenuManager;
/*     */ import org.eclipse.jface.action.StatusLineManager;
/*     */ import org.eclipse.jface.action.ToolBarManager;
/*     */ import org.eclipse.jface.window.ApplicationWindow;
/*     */ import org.eclipse.swt.custom.ScrolledComposite;
/*     */ import org.eclipse.swt.graphics.Point;
/*     */ import org.eclipse.swt.layout.GridData;
/*     */ import org.eclipse.swt.layout.GridLayout;
/*     */ import org.eclipse.swt.widgets.Button;
/*     */ import org.eclipse.swt.widgets.Combo;
/*     */ import org.eclipse.swt.widgets.Composite;
/*     */ import org.eclipse.swt.widgets.Control;
/*     */ import org.eclipse.swt.widgets.Display;
/*     */ import org.eclipse.swt.widgets.Label;
/*     */ import org.eclipse.swt.widgets.Shell;
/*     */ import org.eclipse.swt.widgets.Table;
/*     */ import org.eclipse.swt.widgets.TableColumn;
/*     */ import org.eclipse.wb.swt.SWTResourceManager;
/*     */ 
/*     */ public class ReportLayout extends ApplicationWindow
/*     */ {
/*     */   private Composite composite;
/*     */   private Table table;
/*     */ 
/*     */   public ReportLayout()
/*     */   {
/*  31 */     super(null);
/*  32 */     createActions();
/*  33 */     addToolBar(8388672);
/*  34 */     addMenuBar();
/*  35 */     addStatusLine();
/*     */   }
/*     */ 
/*     */   protected Control createContents(Composite parent)
/*     */   {
/*  44 */     Composite container = new Composite(parent, 0);
/*     */ 
/*  46 */     ScrolledComposite scrolledComposite = new ScrolledComposite(container, 2816);
/*  47 */     scrolledComposite.setBounds(0, 0, 434, 453);
/*  48 */     scrolledComposite.setExpandHorizontal(true);
/*  49 */     scrolledComposite.setExpandVertical(true);
/*     */ 
/*  51 */     this.composite = new Composite(scrolledComposite, 0);
/*     */ 
/*  53 */     Composite composite_1 = new Composite(this.composite, 2048);
/*  54 */     composite_1.setBounds(10, 283, 398, 143);
/*  55 */     composite_1.setLayout(new GridLayout(1, false));
/*     */ 
/*  57 */     Label label = new Label(composite_1, 0);
/*  58 */     label.setText(" 已添加：");
/*  59 */     label.setForeground(SWTResourceManager.getColor(6));
/*     */ 
/*  62 */     this.table = new Table(composite_1, 67584);
/*  63 */     this.table.setLinesVisible(true);
/*  64 */     this.table.setHeaderVisible(true);
/*  65 */     this.table.setFont(SWTResourceManager.getFont("微软雅黑", 8, 0));
/*  66 */     this.table.setLayoutData(new GridData(4, 4, true, true, 1, 1));
/*     */ 
/*  68 */     TableColumn tableColumn = new TableColumn(this.table, 0);
/*  69 */     tableColumn.setWidth(60);
/*  70 */     tableColumn.setText("报表类型");
/*     */ 
/*  73 */     TableColumn tableColumn = new TableColumn(this.table, 0);
/*  74 */     tableColumn.setWidth(64);
/*  75 */     tableColumn.setText("统计记录");
/*     */ 
/*  78 */     TableColumn tableColumn = new TableColumn(this.table, 0);
/*  79 */     tableColumn.setWidth(59);
/*  80 */     tableColumn.setText("统计列值");
/*     */ 
/*  83 */     TableColumn tableColumn = new TableColumn(this.table, 0);
/*  84 */     tableColumn.setWidth(69);
/*  85 */     tableColumn.setText("横轴");
/*     */ 
/*  88 */     TableColumn tableColumn = new TableColumn(this.table, 0);
/*  89 */     tableColumn.setWidth(67);
/*  90 */     tableColumn.setText("纵轴");
/*     */ 
/*  93 */     TableColumn tableColumn = new TableColumn(this.table, 0);
/*  94 */     tableColumn.setWidth(59);
/*  95 */     tableColumn.setText("分类字段");
/*     */ 
/*  98 */     TableColumn tableColumn = new TableColumn(this.table, 0);
/*  99 */     tableColumn.setWidth(34);
/* 100 */     tableColumn.setText("删除");
/*     */ 
/* 105 */     Composite composite_1 = new Composite(this.composite, 2048);
/* 106 */     composite_1.setLayout(null);
/* 107 */     composite_1.setBounds(10, 68, 398, 203);
/*     */ 
/* 109 */     Label label = new Label(composite_1, 0);
/* 110 */     label.setText("报表类型：");
/* 111 */     label.setBounds(14, 14, 60, 17);
/*     */ 
/* 114 */     Combo combo = new Combo(composite_1, 8);
/* 115 */     combo.setBounds(79, 10, 158, 25);
/*     */ 
/* 118 */     Composite composite_2 = new Composite(composite_1, 2048);
/* 119 */     composite_2.setBounds(249, 14, 135, 122);
/*     */ 
/* 122 */     Label label = new Label(composite_1, 131072);
/* 123 */     label.setText("统计类型：");
/* 124 */     label.setBounds(14, 41, 60, 17);
/*     */ 
/* 127 */     Button button = new Button(composite_1, 16);
/* 128 */     button.setText("统计记录");
/* 129 */     button.setBounds(79, 41, 69, 17);
/*     */ 
/* 132 */     Label label = new Label(composite_1, 0);
/* 133 */     label.setText("是否累加：");
/* 134 */     label.setBounds(14, 64, 60, 17);
/*     */ 
/* 137 */     Button button = new Button(composite_1, 16);
/* 138 */     button.setText("是");
/* 139 */     button.setBounds(79, 64, 33, 17);
/*     */ 
/* 142 */     Button button = new Button(composite_1, 16);
/* 143 */     button.setText("统计列值");
/* 144 */     button.setBounds(168, 41, 69, 17);
/*     */ 
/* 147 */     Button button = new Button(composite_1, 16);
/* 148 */     button.setText("否");
/* 149 */     button.setBounds(168, 64, 33, 17);
/*     */ 
/* 152 */     Label label = new Label(composite_1, 131072);
/* 153 */     label.setText("横坐标列：");
/* 154 */     label.setBounds(10, 91, 64, 17);
/*     */ 
/* 157 */     Combo combo = new Combo(composite_1, 8);
/* 158 */     combo.setBounds(79, 87, 158, 25);
/*     */ 
/* 161 */     Label label = new Label(composite_1, 0);
/* 162 */     label.setText("分类字段：");
/* 163 */     label.setBounds(14, 121, 60, 17);
/*     */ 
/* 166 */     Combo combo = new Combo(composite_1, 8);
/* 167 */     combo.setBounds(79, 117, 158, 25);
/*     */ 
/* 170 */     Button button = new Button(composite_1, 0);
/* 171 */     button.setText("添加");
/* 172 */     button.setBounds(304, 148, 80, 27);
/*     */ 
/* 175 */     Label label = new Label(composite_1, 0);
/* 176 */     label.setText("纵坐标列：");
/* 177 */     label.setBounds(14, 155, 61, 17);
/*     */ 
/* 180 */     Combo combo = new Combo(composite_1, 8);
/* 181 */     combo.setBounds(79, 147, 158, 25);
/*     */ 
/* 185 */     Label label = new Label(this.composite, 0);
/* 186 */     label.setText("数据库表：");
/* 187 */     label.setBounds(15, 13, 61, 17);
/*     */ 
/* 190 */     Label label = new Label(this.composite, 131072);
/* 191 */     label.setText("添加报表：");
/* 192 */     label.setBounds(10, 41, 66, 17);
/*     */ 
/* 195 */     Combo combo = new Combo(this.composite, 8);
/* 196 */     combo.setBounds(90, 10, 156, 25);
/*     */ 
/* 199 */     scrolledComposite.setContent(this.composite);
/* 200 */     scrolledComposite.setMinSize(this.composite.computeSize(-1, -1));
/*     */ 
/* 203 */     return container;
/*     */   }
/*     */ 
/*     */   private void createActions()
/*     */   {
/*     */   }
/*     */ 
/*     */   protected MenuManager createMenuManager()
/*     */   {
/* 219 */     MenuManager menuManager = new MenuManager("menu");
/* 220 */     return menuManager;
/*     */   }
/*     */ 
/*     */   protected ToolBarManager createToolBarManager(int style)
/*     */   {
/* 229 */     ToolBarManager toolBarManager = new ToolBarManager(style);
/* 230 */     return toolBarManager;
/*     */   }
/*     */ 
/*     */   protected StatusLineManager createStatusLineManager()
/*     */   {
/* 239 */     StatusLineManager statusLineManager = new StatusLineManager();
/* 240 */     return statusLineManager;
/*     */   }
/*     */ 
/*     */   public static void main(String[] args)
/*     */   {
/*     */     try
/*     */     {
/* 249 */       ReportLayout window = new ReportLayout();
/* 250 */       window.setBlockOnOpen(true);
/* 251 */       window.open();
/* 252 */       Display.getCurrent().dispose();
/*     */     } catch (Exception e) {
/* 254 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   protected void configureShell(Shell newShell)
/*     */   {
/* 264 */     super.configureShell(newShell);
/* 265 */     newShell.setText("New Application");
/*     */   }
/*     */ 
/*     */   protected Point getInitialSize()
/*     */   {
/* 273 */     return new Point(450, 624);
/*     */   }
/*     */ }

/* Location:           D:\MyEclipse2015CI\dropins\com.tianshouzhi.autopro_1.0.0.201506242235.jar
 * Qualified Name:     test.ReportLayout
 * JD-Core Version:    0.6.2
 */