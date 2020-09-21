/*    */ package com.linlongyx.core.framework.httpclient;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CredentialsEntity
/*    */ {
/*    */   private String host;
/*    */   private int port;
/*    */   private String username;
/*    */   private String password;
/*    */   
/*    */   public CredentialsEntity() {}
/*    */   
/*    */   public CredentialsEntity(String host, int port, String username, String password) {
/* 20 */     this.host = host;
/* 21 */     this.port = port;
/* 22 */     this.username = username;
/* 23 */     this.password = password;
/*    */   }
/*    */   
/*    */   public String getHost() {
/* 27 */     return this.host;
/*    */   }
/*    */   
/*    */   public void setHost(String host) {
/* 31 */     this.host = host;
/*    */   }
/*    */   
/*    */   public int getPort() {
/* 35 */     return this.port;
/*    */   }
/*    */   
/*    */   public void setPort(int port) {
/* 39 */     this.port = port;
/*    */   }
/*    */   
/*    */   public String getUsername() {
/* 43 */     return this.username;
/*    */   }
/*    */   
/*    */   public void setUsername(String username) {
/* 47 */     this.username = username;
/*    */   }
/*    */   
/*    */   public String getPassword() {
/* 51 */     return this.password;
/*    */   }
/*    */   
/*    */   public void setPassword(String password) {
/* 55 */     this.password = password;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\core\framework\httpclient\CredentialsEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */