/*    */ package test;
/*    */ 
/*    */ import org.eclipse.swt.custom.CTabFolder;
/*    */ import org.eclipse.swt.custom.CTabItem;
/*    */ import org.eclipse.swt.events.SelectionAdapter;
/*    */ import org.eclipse.swt.events.SelectionEvent;
/*    */ import org.eclipse.swt.layout.FillLayout;
/*    */ import org.eclipse.swt.widgets.Button;
/*    */ import org.eclipse.swt.widgets.Composite;
/*    */ import org.eclipse.swt.widgets.Display;
/*    */ import org.eclipse.swt.widgets.Shell;
/*    */ 
/*    */ public class TabFolderExample
/*    */ {
/*    */   public static void main(String[] args)
/*    */   {
/* 17 */     Display display = new Display();
/* 18 */     Shell shell = new Shell(display);
/* 19 */     shell.setText("TabFolder Example");
/* 20 */     shell.setBounds(100, 100, 175, 125);
/* 21 */     shell.setLayout(new FillLayout());
/*    */ 
/* 23 */     CTabFolder tabFolder = new CTabFolder(shell, 2048);
/* 24 */     tabFolder.setSelectionBackground(Display.getCurrent().getSystemColor(35));
/*    */ 
/* 26 */     CTabItem tabItem_1 = new CTabItem(tabFolder, 0);
/* 27 */     tabItem_1.setText("New Item");
/*    */ 
/* 29 */     Composite composite_1 = new Composite(tabFolder, 0);
/* 30 */     tabItem_1.setControl(composite_1);
/*    */ 
/* 32 */     CTabItem tabItem_2 = new CTabItem(tabFolder, 0);
/* 33 */     tabItem_2.setText("New Item");
/*    */ 
/* 35 */     Composite composite_2 = new Composite(tabFolder, 0);
/* 36 */     tabItem_2.setControl(composite_2);
/*    */ 
/* 38 */     CTabItem tabItem_3 = new CTabItem(tabFolder, 0);
/* 39 */     tabItem_3.setText("New Item");
/*    */ 
/* 41 */     Composite composite_3 = new Composite(tabFolder, 0);
/* 42 */     tabItem_3.setControl(composite_3);
/*    */ 
/* 44 */     CTabItem tabItem_4 = new CTabItem(tabFolder, 0);
/* 45 */     tabItem_4.setText("New Item");
/*    */ 
/* 47 */     Composite composite_4 = new Composite(tabFolder, 0);
/* 48 */     tabItem_4.setControl(composite_4);
/*    */ 
/* 50 */     Composite composite_5 = new Composite(tabFolder, 0);
/* 51 */     tabItem_4.setControl(composite_5);
/*    */ 
/* 53 */     Button button_1 = new Button(tabFolder, 32);
/* 54 */     tabItem_4.setControl(button_1);
/* 55 */     button_1.setText("Check Button");
/*    */ 
/* 57 */     CTabItem tabItem_5 = new CTabItem(tabFolder, 0);
/* 58 */     tabItem_5.setText("New Item");
/*    */ 
/* 60 */     Button btnCheckButton = new Button(tabFolder, 32);
/* 61 */     tabItem_5.setControl(btnCheckButton);
/* 62 */     btnCheckButton.setText("Check Button");
/* 63 */     for (int i = 1; i < 4; i++) {
/* 64 */       CTabItem tabItem = new CTabItem(tabFolder, 0);
/* 65 */       tabItem.setText("Tab " + i);
/* 66 */       Composite composite = new Composite(tabFolder, 0);
/* 67 */       tabItem.setControl(composite);
/* 68 */       Button button = new Button(composite, 8);
/* 69 */       button.setBounds(25, 25, 100, 25);
/* 70 */       button.setText("Click Me Now");
/* 71 */       button.addSelectionListener(new SelectionAdapter() {
/*    */         public void widgetSelected(SelectionEvent event) {
/* 73 */           ((Button)event.widget).setText("I Was Clicked");
/*    */         }
/*    */       });
/*    */     }
/* 77 */     shell.open();
/* 78 */     while (!shell.isDisposed()) {
/* 79 */       if (!display.readAndDispatch())
/* 80 */         display.sleep();
/*    */     }
/* 82 */     display.dispose();
/*    */   }
/*    */ }

/* Location:           D:\MyEclipse2015CI\dropins\com.tianshouzhi.autopro_1.0.0.201506242235.jar
 * Qualified Name:     test.TabFolderExample
 * JD-Core Version:    0.6.2
 */