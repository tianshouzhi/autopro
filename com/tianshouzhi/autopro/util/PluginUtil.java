/*    */ package com.tianshouzhi.autopro.util;
/*    */ 
/*    */ import java.io.File;
/*    */ import java.net.URL;
/*    */ import org.eclipse.core.commands.ExecutionEvent;
/*    */ import org.eclipse.core.commands.ExecutionException;
/*    */ import org.eclipse.core.runtime.FileLocator;
/*    */ import org.eclipse.core.runtime.IPath;
/*    */ import org.eclipse.core.runtime.Platform;
/*    */ import org.eclipse.jdt.core.IJavaElement;
/*    */ import org.eclipse.jdt.core.IJavaProject;
/*    */ import org.eclipse.jface.viewers.ISelection;
/*    */ import org.eclipse.jface.viewers.IStructuredSelection;
/*    */ import org.eclipse.ui.ISelectionService;
/*    */ import org.eclipse.ui.IWorkbenchWindow;
/*    */ import org.eclipse.ui.handlers.HandlerUtil;
/*    */ import org.osgi.framework.Bundle;
/*    */ 
/*    */ public class PluginUtil
/*    */ {
/*    */   public static File getResourceFile(String path)
/*    */   {
/* 33 */     URL url = getBundle().getResource(path);
/*    */     try {
/* 35 */       return new File(FileLocator.toFileURL(url).getPath());
/*    */     } catch (Exception e) {
/* 37 */       throw new RuntimeException("资源文件不存在:" + path, e);
/*    */     }
/*    */   }
/*    */ 
/*    */   public static File getStateLocation()
/*    */   {
/* 46 */     IPath url = Platform.getStateLocation(getBundle());
/* 47 */     return url.toFile();
/*    */   }
/*    */ 
/*    */   public static Bundle getBundle() {
/* 51 */     return Platform.getBundle("com.tianshouzhi.autopro");
/*    */   }
/*    */ 
/*    */   public static IJavaProject getCurrentProject(ExecutionEvent event)
/*    */     throws ExecutionException
/*    */   {
/* 61 */     IWorkbenchWindow activeWorkbenchWindowChecked = HandlerUtil.getActiveWorkbenchWindowChecked(event);
/* 62 */     ISelectionService selectionService = activeWorkbenchWindowChecked.getSelectionService();
/* 63 */     ISelection selection = selectionService.getSelection();
/* 64 */     IJavaProject project = null;
/* 65 */     if ((selection instanceof IStructuredSelection)) {
/* 66 */       Object element = ((IStructuredSelection)selection).getFirstElement();
/* 67 */       if ((element instanceof IJavaProject)) {
/* 68 */         project = ((IJavaElement)element).getJavaProject();
/*    */       }
/*    */     }
/* 71 */     return project;
/*    */   }
/*    */ }

/* Location:           D:\MyEclipse2015CI\dropins\com.tianshouzhi.autopro_1.0.0.201506242235.jar
 * Qualified Name:     com.tianshouzhi.autopro.util.PluginUtil
 * JD-Core Version:    0.6.2
 */