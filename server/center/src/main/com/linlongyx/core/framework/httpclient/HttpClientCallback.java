/*    */ package com.linlongyx.core.framework.httpclient;
/*    */ 
/*    */ import org.apache.http.HttpResponse;
/*    */ import org.apache.http.client.utils.HttpClientUtils;
/*    */ import org.apache.http.concurrent.FutureCallback;
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class HttpClientCallback
/*    */   implements FutureCallback<HttpResponse>
/*    */ {
/*    */   public abstract void onCompleted(HttpResponse paramHttpResponse);
/*    */   
/*    */   public void completed(HttpResponse response) {
/*    */     try {
/* 16 */       onCompleted(response);
/*    */     } finally {
/* 18 */       if (response != null)
/* 19 */         HttpClientUtils.closeQuietly(response); 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\core\framework\httpclient\HttpClientCallback.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */