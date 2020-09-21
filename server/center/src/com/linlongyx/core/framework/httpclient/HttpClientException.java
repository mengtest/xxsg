/*    */ package com.linlongyx.core.framework.httpclient;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class HttpClientException
/*    */   extends Exception
/*    */ {
/*  9 */   private int statusCode = -1;
/*    */   
/*    */   public HttpClientException(String msg) {
/* 12 */     super(msg);
/*    */   }
/*    */   
/*    */   public HttpClientException(Exception cause) {
/* 16 */     super(cause);
/*    */   }
/*    */   
/*    */   public HttpClientException(String msg, int statusCode) {
/* 20 */     super(msg);
/* 21 */     this.statusCode = statusCode;
/*    */   }
/*    */   
/*    */   public HttpClientException(String msg, Exception cause) {
/* 25 */     super(msg, cause);
/*    */   }
/*    */   
/*    */   public HttpClientException(String msg, Exception cause, int statusCode) {
/* 29 */     super(msg, cause);
/* 30 */     this.statusCode = statusCode;
/*    */   }
/*    */   
/*    */   public int getStatusCode() {
/* 34 */     return this.statusCode;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\core\framework\httpclient\HttpClientException.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */