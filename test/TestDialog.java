/*     */ package test;
/*     */ 
/*     */ import org.eclipse.jface.dialogs.Dialog;
/*     */ import org.eclipse.jface.dialogs.IDialogConstants;
/*     */ import org.eclipse.swt.graphics.Point;
/*     */ import org.eclipse.swt.layout.GridData;
/*     */ import org.eclipse.swt.layout.GridLayout;
/*     */ import org.eclipse.swt.widgets.Button;
/*     */ import org.eclipse.swt.widgets.Combo;
/*     */ import org.eclipse.swt.widgets.Composite;
/*     */ import org.eclipse.swt.widgets.Control;
/*     */ import org.eclipse.swt.widgets.Label;
/*     */ import org.eclipse.swt.widgets.Shell;
/*     */ 
/*     */ public class TestDialog extends Dialog
/*     */ {
/*     */   public TestDialog(Shell parentShell)
/*     */   {
/*  23 */     super(parentShell);
/*     */   }
/*     */ 
/*     */   protected Control createDialogArea(Composite parent)
/*     */   {
/*  32 */     Composite container = (Composite)super.createDialogArea(parent);
/*  33 */     GridLayout gridLayout = (GridLayout)container.getLayout();
/*  34 */     gridLayout.numColumns = 4;
/*     */ 
/*  36 */     Label label_3 = new Label(container, 0);
/*  37 */     label_3.setText("当前表");
/*  38 */     new Label(container, 0);
/*  39 */     new Label(container, 0);
/*  40 */     new Label(container, 0);
/*     */ 
/*  42 */     Label lblNewLabel = new Label(container, 0);
/*  43 */     lblNewLabel.setLayoutData(new GridData(131072, 16777216, false, false, 1, 1));
/*  44 */     lblNewLabel.setText("选择报表类型：");
/*     */ 
/*  46 */     Combo combo = new Combo(container, 8);
/*  47 */     combo.setLayoutData(new GridData(4, 16777216, true, false, 1, 1));
/*  48 */     new Label(container, 0);
/*  49 */     new Label(container, 0);
/*     */ 
/*  51 */     Label label_1 = new Label(container, 0);
/*  52 */     label_1.setLayoutData(new GridData(131072, 16777216, false, false, 1, 1));
/*  53 */     label_1.setText("统计类型：");
/*     */ 
/*  55 */     Button button = new Button(container, 16);
/*  56 */     button.setText("记录数");
/*     */ 
/*  58 */     Button btnRadioButton = new Button(container, 16);
/*  59 */     btnRadioButton.setText("列值");
/*  60 */     new Label(container, 0);
/*     */ 
/*  62 */     Label label = new Label(container, 0);
/*  63 */     label.setLayoutData(new GridData(131072, 16777216, false, false, 1, 1));
/*  64 */     label.setText("横轴列：");
/*     */ 
/*  66 */     Combo combo_1 = new Combo(container, 8);
/*  67 */     combo_1.setLayoutData(new GridData(4, 16777216, true, false, 1, 1));
/*     */ 
/*  69 */     Label lblNewLabel_1 = new Label(container, 0);
/*  70 */     lblNewLabel_1.setLayoutData(new GridData(131072, 16777216, false, false, 1, 1));
/*  71 */     lblNewLabel_1.setText("分类字段：");
/*     */ 
/*  73 */     Combo combo_2 = new Combo(container, 8);
/*  74 */     combo_2.setLayoutData(new GridData(4, 16777216, true, false, 1, 1));
/*     */ 
/*  76 */     Label label_2 = new Label(container, 0);
/*  77 */     label_2.setLayoutData(new GridData(131072, 16777216, false, false, 1, 1));
/*  78 */     label_2.setText("统计值列：");
/*     */ 
/*  80 */     Combo combo_3 = new Combo(container, 8);
/*  81 */     combo_3.setLayoutData(new GridData(4, 16777216, true, false, 1, 1));
/*  82 */     new Label(container, 0);
/*  83 */     new Label(container, 0);
/*     */ 
/*  85 */     return container;
/*     */   }
/*     */ 
/*     */   protected void createButtonsForButtonBar(Composite parent)
/*     */   {
/*  94 */     createButton(parent, 0, IDialogConstants.OK_LABEL, 
/*  95 */       true);
/*  96 */     createButton(parent, 1, 
/*  97 */       IDialogConstants.CANCEL_LABEL, false);
/*     */   }
/*     */ 
/*     */   protected Point getInitialSize()
/*     */   {
/* 105 */     return new Point(450, 378);
/*     */   }
/*     */ }

/* Location:           D:\MyEclipse2015CI\dropins\com.tianshouzhi.autopro_1.0.0.201506242235.jar
 * Qualified Name:     test.TestDialog
 * JD-Core Version:    0.6.2
 */