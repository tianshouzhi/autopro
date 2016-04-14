/*    */ package com.tianshouzhi.autopro.service.user;
/*    */ 
/*    */ import com.tianshouzhi.autopro.domain.user.GenerateConfig;
/*    */ import com.tianshouzhi.autopro.util.JaxbUtil;
/*    */ import com.tianshouzhi.autopro.util.PluginUtil;
/*    */ import java.io.File;
/*    */ 
/*    */ public class UserSettingService
/*    */ {
/* 18 */   static String fileSaveDir = PluginUtil.getStateLocation().getAbsolutePath();
/*    */ 
/* 15 */   static String storeFileName = "user.config.xml";
/*    */ 
/*    */   public static GenerateConfig getHistoryGenerateConfig(GenerateConfig rootElement)
/*    */     throws Exception
/*    */   {
/* 27 */     return (GenerateConfig)JaxbUtil.unmarshal(rootElement, fileSaveDir + "/" + storeFileName);
/*    */   }
/*    */ 
/*    */   public static void saveGenerateConfig()
/*    */     throws Exception
/*    */   {
/*    */   }
/*    */ }

/* Location:           D:\MyEclipse2015CI\dropins\com.tianshouzhi.autopro_1.0.0.201506242235.jar
 * Qualified Name:     com.tianshouzhi.autopro.service.user.UserSettingService
 * JD-Core Version:    0.6.2
 */