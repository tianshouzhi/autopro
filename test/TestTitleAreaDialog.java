/*    */ package test;
/*    */ 
/*    */ import org.eclipse.jface.dialogs.IDialogConstants;
/*    */ import org.eclipse.jface.dialogs.TitleAreaDialog;
/*    */ import org.eclipse.swt.graphics.Point;
/*    */ import org.eclipse.swt.layout.GridData;
/*    */ import org.eclipse.swt.widgets.Composite;
/*    */ import org.eclipse.swt.widgets.Control;
/*    */ import org.eclipse.swt.widgets.Shell;
/*    */ 
/*    */ public class TestTitleAreaDialog extends TitleAreaDialog
/*    */ {
/*    */   public TestTitleAreaDialog(Shell parentShell)
/*    */   {
/* 19 */     super(parentShell);
/*    */   }
/*    */ 
/*    */   protected Control createDialogArea(Composite parent)
/*    */   {
/* 28 */     Composite area = (Composite)super.createDialogArea(parent);
/* 29 */     Composite container = new Composite(area, 0);
/* 30 */     container.setLayoutData(new GridData(1808));
/*    */ 
/* 32 */     return area;
/*    */   }
/*    */ 
/*    */   protected void createButtonsForButtonBar(Composite parent)
/*    */   {
/* 41 */     createButton(parent, 0, IDialogConstants.OK_LABEL, 
/* 42 */       true);
/* 43 */     createButton(parent, 1, 
/* 44 */       IDialogConstants.CANCEL_LABEL, false);
/*    */   }
/*    */ 
/*    */   protected Point getInitialSize()
/*    */   {
/* 52 */     return new Point(450, 300);
/*    */   }
/*    */ }

/* Location:           D:\MyEclipse2015CI\dropins\com.tianshouzhi.autopro_1.0.0.201506242235.jar
 * Qualified Name:     test.TestTitleAreaDialog
 * JD-Core Version:    0.6.2
 */