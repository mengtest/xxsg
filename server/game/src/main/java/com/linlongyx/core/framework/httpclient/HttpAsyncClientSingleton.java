/*    */ package com.linlongyx.core.framework.httpclient;
/*    */ 
/*    */ import com.linlongyx.core.utils.LogUtils;
/*    */ import java.util.Arrays;
/*    */ import java.util.List;
/*    */ import org.apache.http.cookie.Cookie;
/*    */ import org.apache.http.message.BasicNameValuePair;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public enum HttpAsyncClientSingleton
/*    */ {
/* 16 */   INSTANCE;
/*    */   private final HttpAsyncClient httpAsyncClient;
/*    */   
/*    */   HttpAsyncClientSingleton() {
/* 20 */     this.httpAsyncClient = new HttpAsyncClient();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void asyncReq(String baseUrl, boolean isPost, List<BasicNameValuePair> urlParams, List<BasicNameValuePair> postBody, List<Cookie> cookies, HttpClientCallback callback) {
/*    */     try {
/* 27 */       this.httpAsyncClient.asyncReq(baseUrl, isPost, urlParams, postBody, cookies, callback);
/* 28 */     } catch (HttpClientException e) {
/* 29 */       LogUtils.errorLog(new Object[] { Arrays.toString((Object[])e.getStackTrace()) });
/* 30 */       if (callback != null)
/* 31 */         callback.cancelled(); 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\core\framework\httpclient\HttpAsyncClientSingleton.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */