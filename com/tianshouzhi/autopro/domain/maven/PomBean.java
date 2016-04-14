/*    */ package com.tianshouzhi.autopro.domain.maven;
/*    */ 
/*    */ import java.util.List;
/*    */ 
/*    */ public class PomBean
/*    */ {
/*    */   private String projectName;
/*    */   private String web_container_path;
/*    */   private String groupId;
/*    */   private String artifactId;
/*    */   List<MavenDependency> dependencyBeans;
/*    */ 
/*    */   public String getProjectName()
/*    */   {
/* 12 */     return this.projectName;
/*    */   }
/*    */   public void setProjectName(String projectName) {
/* 15 */     this.projectName = projectName;
/*    */   }
/*    */   public String getWeb_container_path() {
/* 18 */     return this.web_container_path;
/*    */   }
/*    */   public void setWeb_container_path(String web_container_path) {
/* 21 */     this.web_container_path = web_container_path;
/*    */   }
/*    */   public List<MavenDependency> getDependencyBeans() {
/* 24 */     return this.dependencyBeans;
/*    */   }
/*    */   public void setDependencyBeans(List<MavenDependency> dependencyBeans) {
/* 27 */     this.dependencyBeans = dependencyBeans;
/*    */   }
/*    */ 
/*    */   public String getGroupId() {
/* 31 */     return this.groupId;
/*    */   }
/*    */   public void setGroupId(String groupId) {
/* 34 */     this.groupId = groupId;
/*    */   }
/*    */   public String getArtifactId() {
/* 37 */     return this.artifactId;
/*    */   }
/*    */   public void setArtifactId(String artifactId) {
/* 40 */     this.artifactId = artifactId;
/*    */   }
/*    */ 
/*    */   public PomBean(String projectName, String web_container_path, String groupId, String artifactId, List<MavenDependency> dependencyBeans)
/*    */   {
/* 46 */     this.projectName = projectName;
/* 47 */     this.web_container_path = web_container_path;
/* 48 */     this.groupId = groupId;
/* 49 */     this.artifactId = artifactId;
/* 50 */     this.dependencyBeans = dependencyBeans;
/*    */   }
/*    */ 
/*    */   public PomBean()
/*    */   {
/*    */   }
/*    */ }

/* Location:           D:\MyEclipse2015CI\dropins\com.tianshouzhi.autopro_1.0.0.201506242235.jar
 * Qualified Name:     com.tianshouzhi.autopro.domain.maven.PomBean
 * JD-Core Version:    0.6.2
 */