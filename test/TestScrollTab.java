/*     */ package test;
/*     */ 
/*     */ import org.eclipse.jface.action.MenuManager;
/*     */ import org.eclipse.jface.action.StatusLineManager;
/*     */ import org.eclipse.jface.action.ToolBarManager;
/*     */ import org.eclipse.jface.window.ApplicationWindow;
/*     */ import org.eclipse.swt.custom.ScrolledComposite;
/*     */ import org.eclipse.swt.graphics.Point;
/*     */ import org.eclipse.swt.widgets.Composite;
/*     */ import org.eclipse.swt.widgets.Control;
/*     */ import org.eclipse.swt.widgets.Display;
/*     */ import org.eclipse.swt.widgets.Label;
/*     */ import org.eclipse.swt.widgets.Shell;
/*     */ 
/*     */ public class TestScrollTab extends ApplicationWindow
/*     */ {
/*     */   private Composite composite;
/*     */ 
/*     */   public TestScrollTab()
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
/*  38 */     ScrolledComposite scrolledComposite = new ScrolledComposite(container, 2816);
/*  39 */     scrolledComposite.setBounds(40, 27, 241, 107);
/*  40 */     scrolledComposite.setExpandHorizontal(true);
/*  41 */     scrolledComposite.setExpandVertical(true);
/*     */ 
/*  43 */     this.composite = new Composite(scrolledComposite, 0);
/*     */ 
/*  45 */     Label lblNewLabel = new Label(this.composite, 2048);
/*  46 */     lblNewLabel.setBounds(21, 10, 61, 139);
/*  47 */     lblNewLabel.setText("New Label");
/*     */ 
/*  49 */     scrolledComposite.setContent(this.composite);
/*  50 */     scrolledComposite.setMinSize(this.composite.computeSize(-1, -1));
/*     */ 
/*  53 */     return container;
/*     */   }
/*     */ 
/*     */   private void createActions()
/*     */   {
/*     */   }
/*     */ 
/*     */   protected MenuManager createMenuManager()
/*     */   {
/*  69 */     MenuManager menuManager = new MenuManager("menu");
/*  70 */     return menuManager;
/*     */   }
/*     */ 
/*     */   protected ToolBarManager createToolBarManager(int style)
/*     */   {
/*  79 */     ToolBarManager toolBarManager = new ToolBarManager(style);
/*  80 */     return toolBarManager;
/*     */   }
/*     */ 
/*     */   protected StatusLineManager createStatusLineManager()
/*     */   {
/*  89 */     StatusLineManager statusLineManager = new StatusLineManager();
/*  90 */     return statusLineManager;
/*     */   }
/*     */ 
/*     */   public static void main(String[] args)
/*     */   {
/*     */     try
/*     */     {
/*  99 */       TestScrollTab window = new TestScrollTab();
/* 100 */       window.setBlockOnOpen(true);
/* 101 */       window.open();
/* 102 */       Display.getCurrent().dispose();
/*     */     } catch (Exception e) {
/* 104 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   protected void configureShell(Shell newShell)
/*     */   {
/* 114 */     super.configureShell(newShell);
/* 115 */     newShell.setText("New Application");
/*     */   }
/*     */ 
/*     */   protected Point getInitialSize()
/*     */   {
/* 123 */     return new Point(450, 300);
/*     */   }
/*     */ }

/* Location:           D:\MyEclipse2015CI\dropins\com.tianshouzhi.autopro_1.0.0.201506242235.jar
 * Qualified Name:     test.TestScrollTab
 * JD-Core Version:    0.6.2
 */