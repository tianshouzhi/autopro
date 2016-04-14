/*     */ package test;
/*     */ 
/*     */ import org.eclipse.jface.action.MenuManager;
/*     */ import org.eclipse.jface.action.StatusLineManager;
/*     */ import org.eclipse.jface.action.ToolBarManager;
/*     */ import org.eclipse.jface.window.ApplicationWindow;
/*     */ import org.eclipse.swt.custom.CTabFolder;
/*     */ import org.eclipse.swt.custom.CTabItem;
/*     */ import org.eclipse.swt.graphics.Point;
/*     */ import org.eclipse.swt.widgets.Combo;
/*     */ import org.eclipse.swt.widgets.Composite;
/*     */ import org.eclipse.swt.widgets.Control;
/*     */ import org.eclipse.swt.widgets.Display;
/*     */ import org.eclipse.swt.widgets.Shell;
/*     */ 
/*     */ public class Tab extends ApplicationWindow
/*     */ {
/*     */   public Tab()
/*     */   {
/*  23 */     super(null);
/*  24 */     createActions();
/*  25 */     addToolBar(8388672);
/*  26 */     addMenuBar();
/*  27 */     addStatusLine();
/*     */   }
/*     */ 
/*     */   protected Control createContents(Composite parent)
/*     */   {
/*  36 */     Composite container = new Composite(parent, 0);
/*     */ 
/*  38 */     CTabFolder tabFolder = new CTabFolder(container, 2048);
/*  39 */     tabFolder.setBounds(10, 10, 368, 239);
/*  40 */     tabFolder.setSelectionBackground(Display.getCurrent().getSystemColor(35));
/*     */ 
/*  42 */     CTabItem tabItem = new CTabItem(tabFolder, 0);
/*  43 */     tabItem.setText("New Item");
/*     */ 
/*  45 */     Composite composite = new Composite(tabFolder, 0);
/*  46 */     tabItem.setControl(composite);
/*     */ 
/*  48 */     Combo combo = new Combo(composite, 0);
/*  49 */     combo.setBounds(106, 67, 88, 23);
/*     */ 
/*  51 */     CTabItem tabItem_1 = new CTabItem(tabFolder, 0);
/*  52 */     tabItem_1.setText("New Item");
/*     */ 
/*  54 */     Composite composite_1 = new Composite(tabFolder, 0);
/*  55 */     tabItem_1.setControl(composite_1);
/*     */ 
/*  57 */     CTabItem tabItem_2 = new CTabItem(tabFolder, 0);
/*  58 */     tabItem_2.setText("New Item");
/*     */ 
/*  60 */     Composite composite_2 = new Composite(tabFolder, 0);
/*  61 */     tabItem_2.setControl(composite_2);
/*     */ 
/*  63 */     return container;
/*     */   }
/*     */ 
/*     */   private void createActions()
/*     */   {
/*     */   }
/*     */ 
/*     */   protected MenuManager createMenuManager()
/*     */   {
/*  79 */     MenuManager menuManager = new MenuManager("menu");
/*  80 */     return menuManager;
/*     */   }
/*     */ 
/*     */   protected ToolBarManager createToolBarManager(int style)
/*     */   {
/*  89 */     ToolBarManager toolBarManager = new ToolBarManager(style);
/*  90 */     return toolBarManager;
/*     */   }
/*     */ 
/*     */   protected StatusLineManager createStatusLineManager()
/*     */   {
/*  99 */     StatusLineManager statusLineManager = new StatusLineManager();
/* 100 */     return statusLineManager;
/*     */   }
/*     */ 
/*     */   public static void main(String[] args)
/*     */   {
/*     */     try
/*     */     {
/* 109 */       Tab window = new Tab();
/* 110 */       window.setBlockOnOpen(true);
/* 111 */       window.open();
/* 112 */       Display.getCurrent().dispose();
/*     */     } catch (Exception e) {
/* 114 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   protected void configureShell(Shell newShell)
/*     */   {
/* 124 */     super.configureShell(newShell);
/* 125 */     newShell.setText("New Application");
/*     */   }
/*     */ 
/*     */   protected Point getInitialSize()
/*     */   {
/* 133 */     return new Point(597, 420);
/*     */   }
/*     */ }

/* Location:           D:\MyEclipse2015CI\dropins\com.tianshouzhi.autopro_1.0.0.201506242235.jar
 * Qualified Name:     test.Tab
 * JD-Core Version:    0.6.2
 */