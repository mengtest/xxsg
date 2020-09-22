/*    */ package com.linlongyx.sanguo.webgame.net.http.ask;
/*    */ 
/*    */ import com.fasterxml.jackson.databind.ObjectMapper;
/*    */ import com.linlongyx.core.framework.context.AppContext;
/*    */ import com.linlongyx.core.framework.httpclient.HttpClientException;
/*    */ import com.linlongyx.core.framework.httpclient.HttpClientSingleton;
/*    */ import com.linlongyx.sanguo.webgame.net.http.HttpHelper;
/*    */ import com.linlongyx.sanguo.webgame.net.http.event.IHttpEvent;
/*    */ import java.util.ArrayList;
/*    */ import java.util.HashMap;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ import org.apache.http.message.BasicNameValuePair;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class AbstractAskHttpEvent
/*    */   implements IHttpEvent
/*    */ {
/*    */   private String urlPath;
/*    */   private boolean isSignature;
/* 24 */   private ObjectMapper objectMapper = new ObjectMapper();
/* 25 */   private Map<String, Object> responseMap = new HashMap<>();
/*    */ 
/*    */   
/*    */   public AbstractAskHttpEvent(String urlPath) {
/* 29 */     this.urlPath = urlPath;
/* 30 */     this.isSignature = false;
/* 31 */     if (this.urlPath.contains("oauth/")) {
/* 32 */       this.isSignature = true;
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Map<String, Object> request(Map<String, String> params) {
/*    */     try {
/* 41 */       process(params);
/* 42 */     } catch (Exception e) {
/* 43 */       e.printStackTrace();
/* 44 */       this.responseMap.put("code", Short.valueOf((short)10012));
/* 45 */       return this.responseMap;
/*    */     } 
/* 47 */     return this.responseMap;
/*    */   }
/*    */   
/*    */   public short requestSuccess(Map<String, String> params) {
/*    */     try {
/* 52 */       process(params);
/* 53 */     } catch (Exception e) {
/* 54 */       e.printStackTrace();
/* 55 */       return 10012;
/*    */     } 
/* 57 */     if (this.responseMap.isEmpty()) {
/* 58 */       return 10012;
/*    */     }
/* 60 */     int code = ((Integer)this.responseMap.get("code")).intValue();
/* 61 */     if (code != 10001) {
/* 62 */       return 10012;
/*    */     }
/* 64 */     return 0;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public final Map<String, Object> getResult() {
/* 72 */     return this.responseMap;
/*    */   }
/*    */   
/*    */   private void process(Map<String, String> params) {
/* 76 */     List<BasicNameValuePair> paramsArr = new ArrayList<>();
/*    */     
/* 78 */     for (Map.Entry<String, String> entry : params.entrySet()) {
/* 79 */       BasicNameValuePair param = new BasicNameValuePair(entry.getKey(), entry.getValue());
/* 80 */       paramsArr.add(param);
/*    */     } 
/*    */     
/* 83 */     if (this.isSignature) {
/* 84 */       String sign = HttpHelper.makeSign(paramsArr);
/* 85 */       paramsArr.add(new BasicNameValuePair("signature", sign));
/*    */     } 
/*    */     
/*    */     try {
/* 89 */       String url = AppContext.getUrl() + this.urlPath;
/* 90 */       this.responseMap.clear();
/* 91 */       String response = HttpClientSingleton.INSTANCE.get(url, paramsArr, null);
/* 92 */       this.responseMap = (Map<String, Object>)this.objectMapper.readValue(response, this.responseMap.getClass());
/*    */     }
/* 94 */     catch (HttpClientException|java.io.IOException e) {
/* 95 */       e.printStackTrace();
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\net\http\ask\AbstractAskHttpEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */