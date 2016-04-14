/*     */ package test;
/*     */ 
/*     */ import org.eclipse.jface.action.MenuManager;
/*     */ import org.eclipse.jface.action.StatusLineManager;
/*     */ import org.eclipse.jface.action.ToolBarManager;
/*     */ import org.eclipse.jface.window.ApplicationWindow;
/*     */ import org.eclipse.swt.graphics.Point;
/*     */ import org.eclipse.swt.widgets.Composite;
/*     */ import org.eclipse.swt.widgets.Control;
/*     */ import org.eclipse.swt.widgets.Display;
/*     */ import org.eclipse.swt.widgets.Shell;
/*     */ 
/*     */ public class TestReportUI extends ApplicationWindow
/*     */ {
/*     */   public TestReportUI()
/*     */   {
/*  20 */     super(null);
/*  21 */     createActions();
/*  22 */     addToolBar(8388672);
/*  23 */     addMenuBar();
/*  24 */     addStatusLine();
/*     */   }
/*     */ 
/*     */   protected Control createContents(Composite parent)
/*     */   {
/*  33 */     Composite container = new Composite(parent, 0);
/*     */ 
/*  35 */     return container;
/*     */   }
/*     */ 
/*     */   private void createActions()
/*     */   {
/*     */   }
/*     */ 
/*     */   protected MenuManager createMenuManager()
/*     */   {
/*  51 */     MenuManager menuManager = new MenuManager("menu");
/*  52 */     return menuManager;
/*     */   }
/*     */ 
/*     */   protected ToolBarManager createToolBarManager(int style)
/*     */   {
/*  61 */     ToolBarManager toolBarManager = new ToolBarManager(style);
/*  62 */     return toolBarManager;
/*     */   }
/*     */ 
/*     */   protected StatusLineManager createStatusLineManager()
/*     */   {
/*  71 */     StatusLineManager statusLineManager = new StatusLineManager();
/*  72 */     return statusLineManager;
/*     */   }
/*     */ 
/*     */   public static void main(String[] args)
/*     */   {
/*     */     try
/*     */     {
/*  81 */       TestReportUI window = new TestReportUI();
/*  82 */       window.setBlockOnOpen(true);
/*  83 */       window.open();
/*  84 */       Display.getCurrent().dispose();
/*     */     } catch (Exception e) {
/*  86 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   protected void configureShell(Shell newShell)
/*     */   {
/*  96 */     super.configureShell(newShell);
/*  97 */     newShell.setText("New Application");
/*     */   }
/*     */ 
/*     */   protected Point getInitialSize()
/*     */   {
/* 105 */     return new Point(450, 500);
/*     */   }
/*     */ }

/* Location:           D:\MyEclipse2015CI\dropins\com.tianshouzhi.autopro_1.0.0.201506242235.jar
 * Qualified Name:     test.TestReportUI
 * JD-Core Version:    0.6.2
 */