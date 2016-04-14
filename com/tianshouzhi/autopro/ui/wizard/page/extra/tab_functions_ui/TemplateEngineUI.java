/*    */ package com.tianshouzhi.autopro.ui.wizard.page.extra.tab_functions_ui;
/*    */ 
/*    */ import com.tianshouzhi.autopro.domain.enums.TemplateEngineEnum;
/*    */ import java.util.List;
/*    */ import org.eclipse.swt.events.SelectionAdapter;
/*    */ import org.eclipse.swt.events.SelectionEvent;
/*    */ import org.eclipse.swt.widgets.Combo;
/*    */ import org.eclipse.swt.widgets.Composite;
/*    */ import org.eclipse.swt.widgets.Label;
/*    */ import org.eclipse.swt.widgets.Text;
/*    */ import org.eclipse.wb.swt.SWTResourceManager;
/*    */ 
/*    */ public class TemplateEngineUI
/*    */ {
/*    */   private Text text_template_dir;
/*    */   private Combo combo_Engine_type;
/*    */ 
/*    */   public TemplateEngineUI(Composite parent)
/*    */   {
/* 30 */     createContents(parent);
/*    */   }
/*    */ 
/*    */   private void createContents(Composite container)
/*    */   {
/* 35 */     Label label_1 = new Label(container, 0);
/* 36 */     label_1.setBounds(21, 23, 78, 17);
/* 37 */     label_1.setText("选择模板引擎");
/*    */ 
/* 39 */     this.combo_Engine_type = new Combo(container, 0);
/* 40 */     this.combo_Engine_type.setBounds(105, 20, 134, 23);
/* 41 */     this.combo_Engine_type.setItems(TemplateEngineEnum.getTemplateEngineDisplayNames());
/* 42 */     this.combo_Engine_type.setText(TemplateEngineEnum.getDefault().getDisplayName());
/* 43 */     this.combo_Engine_type.addSelectionListener(new TemplateEngineChangeListener());
/*    */ 
/* 45 */     Label label_2 = new Label(container, 0);
/* 46 */     label_2.setBounds(21, 50, 78, 17);
/* 47 */     label_2.setText("模板文件目录");
/*    */ 
/* 49 */     this.text_template_dir = new Text(container, 2048);
/* 50 */     this.text_template_dir.setBounds(105, 20, 134, 23);
/* 51 */     this.text_template_dir.setText(TemplateEngineEnum.getDefault().getDefaultTemplateDir());
/*    */ 
/* 54 */     Label lblNewLabel = new Label(container, 2080);
/* 55 */     lblNewLabel.setBackground(SWTResourceManager.getColor(1));
/* 56 */     lblNewLabel.setBounds(21, 107, 399, 148);
/* 57 */     lblNewLabel.setText("1、模板文件路径：相对于项目根目录来说的,例如:src/main/resources/\nfk_templates，你应该将所有的Freemarker模板文件都放在该目录下\n\n2、默认设置：会将Freemaker与Spring进行整合，整合的配置文件名称为:\nspring-freemarker.xml，默认的字符编码为：UTF-8，默认的日期格式：\nyyyy-MM-dd HH:mm:ss，默认语言环境是：Locale_CN，你可以在生成\n的spring-freemarker.xml文件中进行修改");
/*    */ 
/* 64 */     Label label_3 = new Label(container, 0);
/* 65 */     label_3.setBounds(22, 84, 61, 17);
/* 66 */     label_3.setText("说明：");
/*    */   }
/*    */ 
/*    */   public String getText_template_dir() {
/* 70 */     return this.text_template_dir.getText();
/*    */   }
/*    */   public String getTemplateEngineName() {
/* 73 */     return this.combo_Engine_type.getText();
/*    */   }
/*    */ 
/*    */   public List<String> getRelateTableNames()
/*    */   {
/* 90 */     return null;
/*    */   }
/*    */ 
/*    */   class TemplateEngineChangeListener extends SelectionAdapter
/*    */   {
/*    */     TemplateEngineChangeListener()
/*    */     {
/*    */     }
/*    */ 
/*    */     public void widgetSelected(SelectionEvent e)
/*    */     {
/* 80 */       String engineDisplayName = TemplateEngineUI.this.combo_Engine_type.getText();
/* 81 */       TemplateEngineEnum templateEngine = TemplateEngineEnum.getTemplateEngineEnumByName(engineDisplayName);
/* 82 */       TemplateEngineUI.this.combo_Engine_type.setText(templateEngine.getDisplayName());
/* 83 */       TemplateEngineUI.this.text_template_dir.setText(templateEngine.getDefaultTemplateDir());
/*    */     }
/*    */   }
/*    */ }

/* Location:           D:\MyEclipse2015CI\dropins\com.tianshouzhi.autopro_1.0.0.201506242235.jar
 * Qualified Name:     com.tianshouzhi.autopro.ui.wizard.page.extra.tab_functions_ui.TemplateEngineUI
 * JD-Core Version:    0.6.2
 */