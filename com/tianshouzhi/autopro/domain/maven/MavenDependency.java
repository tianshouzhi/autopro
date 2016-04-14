/*    */ package com.tianshouzhi.autopro.domain.maven;
/*    */ 
/*    */ public class MavenDependency
/*    */ {
/*    */   private String artifactId;
/*    */   private String version;
/*    */   private String scope;
/*    */   private String groupId;
/*    */ 
/*    */   public String getArtifactId()
/*    */   {
/* 13 */     return this.artifactId;
/*    */   }
/*    */ 
/*    */   public void setArtifactId(String artifactId) {
/* 17 */     this.artifactId = artifactId;
/*    */   }
/*    */ 
/*    */   public String getVersion() {
/* 21 */     return this.version;
/*    */   }
/*    */ 
/*    */   public void setVersion(String version) {
/* 25 */     this.version = version;
/*    */   }
/*    */ 
/*    */   public String getScope() {
/* 29 */     return this.scope;
/*    */   }
/*    */ 
/*    */   public void setScope(String scope) {
/* 33 */     this.scope = scope;
/*    */   }
/*    */ 
/*    */   public MavenDependency()
/*    */   {
/*    */   }
/*    */ 
/*    */   public MavenDependency(String groupId, String artifactId, String version, String scope)
/*    */   {
/* 44 */     this.groupId = groupId;
/* 45 */     this.artifactId = artifactId;
/* 46 */     this.version = version;
/* 47 */     this.scope = scope;
/*    */   }
/*    */ 
/*    */   public MavenDependency(String groudId, String artifactId, String version) {
/* 51 */     this(groudId, artifactId, version, "compile");
/*    */   }
/*    */ 
/*    */   public String getGroupId() {
/* 55 */     return this.groupId;
/*    */   }
/*    */ 
/*    */   public void setGroupId(String groupId) {
/* 59 */     this.groupId = groupId;
/*    */   }
/*    */ 
/*    */   public String toString()
/*    */   {
/* 64 */     return "MavenDependency [artifactId=" + this.artifactId + ", version=" + 
/* 65 */       this.version + ", scope=" + this.scope + ", groupId=" + this.groupId + "]";
/*    */   }
/*    */ }

/* Location:           D:\MyEclipse2015CI\dropins\com.tianshouzhi.autopro_1.0.0.201506242235.jar
 * Qualified Name:     com.tianshouzhi.autopro.domain.maven.MavenDependency
 * JD-Core Version:    0.6.2
 */