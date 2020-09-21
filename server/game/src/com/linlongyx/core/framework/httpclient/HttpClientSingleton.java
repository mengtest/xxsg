/*    */ package com.linlongyx.core.framework.httpclient;
/*    */ 
/*    */ import java.util.List;
/*    */ import org.apache.http.cookie.Cookie;
/*    */ import org.apache.http.message.BasicNameValuePair;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public enum HttpClientSingleton
/*    */ {
/* 14 */   INSTANCE;
/*    */   private final HttpSyncClient httpSyncClient;
/*    */   
/*    */   HttpClientSingleton() {
/* 18 */     this.httpSyncClient = new HttpSyncClient();
/*    */   }
/*    */   
/*    */   public String get(String url, List<BasicNameValuePair> parameters, List<Cookie> cookies) throws HttpClientException {
/* 22 */     return this.httpSyncClient.httpGet(url, parameters, cookies);
/*    */   }
/*    */   
/*    */   public String post(String url, List<BasicNameValuePair> parameters, List<Cookie> cookies) throws HttpClientException {
/* 26 */     return this.httpSyncClient.httpPost(url, parameters, cookies);
/*    */   }
/*    */   
/*    */   public String post(String url, String requestBody, String mimeType, List<Cookie> cookies) throws HttpClientException {
/* 30 */     if (mimeType == null || "".equals(mimeType)) {
/* 31 */       return this.httpSyncClient.httpPost(url, requestBody, cookies);
/*    */     }
/* 33 */     return this.httpSyncClient.httpPost(url, requestBody, mimeType, cookies);
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\core\framework\httpclient\HttpClientSingleton.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */