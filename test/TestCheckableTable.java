/*     */ package test;
/*     */ 
/*     */ import java.util.Hashtable;
/*     */ import org.eclipse.jface.window.ApplicationWindow;
/*     */ import org.eclipse.swt.custom.TableEditor;
/*     */ import org.eclipse.swt.graphics.Point;
/*     */ import org.eclipse.swt.widgets.Button;
/*     */ import org.eclipse.swt.widgets.Combo;
/*     */ import org.eclipse.swt.widgets.Composite;
/*     */ import org.eclipse.swt.widgets.Control;
/*     */ import org.eclipse.swt.widgets.Display;
/*     */ import org.eclipse.swt.widgets.Group;
/*     */ import org.eclipse.swt.widgets.Shell;
/*     */ import org.eclipse.swt.widgets.Table;
/*     */ import org.eclipse.swt.widgets.TableColumn;
/*     */ import org.eclipse.swt.widgets.TableItem;
/*     */ import org.eclipse.swt.widgets.Text;
/*     */ 
/*     */ public class TestCheckableTable extends ApplicationWindow
/*     */ {
/*     */   private Table table;
/*  24 */   private String[][] contents = { { "", "张三", "男", "未婚" }, 
/*  25 */     { "", "李四", "女", "已婚" }, { "", "王五", "女", "未婚" } };
/*     */ 
/*  27 */   private Hashtable<TableItem, TableItemControls> tableControls = new Hashtable();
/*     */   private static final int NAME_INDEX = 1;
/*     */   private static final int SEX_INDEX = 2;
/*     */   private static final int MARRY_INDEX = 3;
/* 204 */   private static final String[] SEXS = { "男", "女" };
/*     */ 
/*     */   public TestCheckableTable()
/*     */   {
/*  33 */     super(null);
/*     */   }
/*     */ 
/*     */   protected Control createContents(Composite parent)
/*     */   {
/*  43 */     Composite container = new Composite(parent, 0);
/*     */ 
/*  45 */     Group group = new Group(container, 0);
/*  46 */     group.setText("表");
/*  47 */     group.setBounds(10, 10, 481, 238);
/*     */ 
/*  49 */     this.table = new Table(group, 67616);
/*     */ 
/*  51 */     this.table.setBounds(10, 20, 461, 208);
/*  52 */     this.table.setHeaderVisible(true);
/*  53 */     this.table.setLinesVisible(true);
/*     */ 
/*  55 */     TableColumn tableColumn = new TableColumn(this.table, 0);
/*  56 */     tableColumn.setWidth(20);
/*     */ 
/*  59 */     TableColumn tableColumn = new TableColumn(this.table, 0);
/*  60 */     tableColumn.setWidth(104);
/*  61 */     tableColumn.setText("名");
/*     */ 
/*  64 */     TableColumn tableColumn = new TableColumn(this.table, 0);
/*  65 */     tableColumn.setWidth(110);
/*  66 */     tableColumn.setText("性别");
/*     */ 
/*  69 */     TableColumn tableColumn = new TableColumn(this.table, 16777216);
/*  70 */     tableColumn.setWidth(100);
/*  71 */     tableColumn.setText("婚否");
/*     */     String[][] arrayOfString;
/*  76 */     TableColumn localTableColumn1 = (arrayOfString = this.contents).length; for (tableColumn = 0; tableColumn < localTableColumn1; tableColumn++) { String[] content = arrayOfString[tableColumn];
/*  77 */       TableItem item = new TableItem(this.table, 16777216);
/*  78 */       item.setText(content);
/*     */     }
/*  80 */     createCells(this.table);
/*     */ 
/*  82 */     return container;
/*     */   }
/*     */ 
/*     */   private void createCells(Table table) {
/*  86 */     for (int i = 0; i < table.getItemCount(); i++)
/*  87 */       createOneItemCells(table.getItem(i));
/*     */   }
/*     */ 
/*     */   private void createOneItemCells(TableItem item)
/*     */   {
/*  92 */     TableEditor nameEditor = new TableEditor(this.table);
/*     */ 
/*  94 */     Text name = new Text(this.table, 16777216);
/*  95 */     name.setText(item.getText(1));
/*  96 */     nameEditor.grabHorizontal = true;
/*  97 */     nameEditor.setEditor(name, item, 1);
/*     */ 
/*  99 */     TableEditor sexTableEditor = new TableEditor(this.table);
/* 100 */     int sexIndex = findElement(SEXS, item.getText(2));
/* 101 */     Combo sexCombo = new Combo(this.table, 12);
/* 102 */     for (String sex : SEXS) {
/* 103 */       sexCombo.add(sex);
/*     */     }
/*     */ 
/* 106 */     sexCombo.select(sexIndex);
/* 107 */     sexTableEditor.grabHorizontal = true;
/* 108 */     sexTableEditor.setEditor(sexCombo, item, 2);
/*     */ 
/* 110 */     TableEditor marryTableEditor = new TableEditor(this.table);
/* 111 */     Button marryButton = new Button(this.table, 16777248);
/* 112 */     if (item.getText(3).equals("已婚"))
/* 113 */       marryButton.setSelection(true);
/*     */     else {
/* 115 */       marryButton.setSelection(false);
/*     */     }
/*     */ 
/* 118 */     marryTableEditor.grabHorizontal = true;
/* 119 */     marryTableEditor.setEditor(marryButton, item, 3);
/*     */ 
/* 121 */     TableItemControls tableItemControls = new TableItemControls(name, 
/* 122 */       sexCombo, marryButton, nameEditor, sexTableEditor, 
/* 123 */       marryTableEditor);
/*     */ 
/* 125 */     this.tableControls.put(item, tableItemControls);
/*     */   }
/*     */ 
/*     */   private int findElement(String[] elements, String target)
/*     */   {
/* 130 */     for (int i = 0; i < elements.length; i++) {
/* 131 */       if (elements[i].equals(target)) {
/* 132 */         return i;
/*     */       }
/*     */     }
/* 135 */     return -1;
/*     */   }
/*     */ 
/*     */   public static void main(String[] args)
/*     */   {
/*     */     try
/*     */     {
/* 145 */       TestCheckableTable window = new TestCheckableTable();
/* 146 */       window.setBlockOnOpen(true);
/* 147 */       window.open();
/* 148 */       Display.getCurrent().dispose();
/*     */     } catch (Exception e) {
/* 150 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   protected void configureShell(Shell newShell)
/*     */   {
/* 187 */     super.configureShell(newShell);
/* 188 */     newShell.setText("表操作相关");
/*     */   }
/*     */ 
/*     */   protected Point getInitialSize()
/*     */   {
/* 196 */     return new Point(604, 379);
/*     */   }
/*     */ 
/*     */   class TableItemControls
/*     */   {
/*     */     Text nameText;
/*     */     Combo sexCombo;
/*     */     Button marryButton;
/*     */     TableEditor nameEditor;
/*     */     TableEditor sexEditor;
/*     */     TableEditor marryEditor;
/*     */ 
/*     */     public TableItemControls(Text nameText, Combo sexCombo, Button marryButton, TableEditor nameEditor, TableEditor sexEditor, TableEditor marryEditor)
/*     */     {
/* 165 */       this.nameText = nameText;
/* 166 */       this.sexCombo = sexCombo;
/* 167 */       this.marryButton = marryButton;
/* 168 */       this.nameEditor = nameEditor;
/* 169 */       this.sexEditor = sexEditor;
/* 170 */       this.marryEditor = marryEditor;
/*     */     }
/*     */ 
/*     */     public void dispose() {
/* 174 */       this.nameText.dispose();
/* 175 */       this.sexCombo.dispose();
/* 176 */       this.marryButton.dispose();
/*     */     }
/*     */   }
/*     */ }

/* Location:           D:\MyEclipse2015CI\dropins\com.tianshouzhi.autopro_1.0.0.201506242235.jar
 * Qualified Name:     test.TestCheckableTable
 * JD-Core Version:    0.6.2
 */