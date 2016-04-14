/*    */ package test;
/*    */ 
/*    */ import org.eclipse.jface.viewers.IStructuredSelection;
/*    */ import org.eclipse.jface.wizard.Wizard;
/*    */ import org.eclipse.ui.INewWizard;
/*    */ import org.eclipse.ui.IWorkbench;
/*    */ 
/*    */ public class TestWizardUI extends Wizard
/*    */   implements INewWizard
/*    */ {
/*    */   public TestWizardUI()
/*    */   {
/* 11 */     setWindowTitle("New Wizard");
/*    */   }
/*    */ 
/*    */   public void addPages()
/*    */   {
/*    */   }
/*    */ 
/*    */   public boolean performFinish()
/*    */   {
/* 20 */     return false;
/*    */   }
/*    */ 
/*    */   public void init(IWorkbench workbench, IStructuredSelection selection)
/*    */   {
/*    */   }
/*    */ }

/* Location:           D:\MyEclipse2015CI\dropins\com.tianshouzhi.autopro_1.0.0.201506242235.jar
 * Qualified Name:     test.TestWizardUI
 * JD-Core Version:    0.6.2
 */