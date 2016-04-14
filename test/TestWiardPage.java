/*    */ package test;
/*    */ 
/*    */ import org.eclipse.jface.wizard.WizardPage;
/*    */ import org.eclipse.swt.widgets.Composite;
/*    */ 
/*    */ public class TestWiardPage extends WizardPage
/*    */ {
/*    */   public TestWiardPage()
/*    */   {
/* 13 */     super("wizardPage");
/* 14 */     setTitle("Wizard Page title");
/* 15 */     setDescription("Wizard Page description");
/*    */   }
/*    */ 
/*    */   public void createControl(Composite parent)
/*    */   {
/* 23 */     Composite container = new Composite(parent, 0);
/*    */ 
/* 25 */     setControl(container);
/*    */   }
/*    */ }

/* Location:           D:\MyEclipse2015CI\dropins\com.tianshouzhi.autopro_1.0.0.201506242235.jar
 * Qualified Name:     test.TestWiardPage
 * JD-Core Version:    0.6.2
 */