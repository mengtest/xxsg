/*    */ package com.linlongyx.sanguo.webgame.net.http;
/*    */ 
/*    */ import com.linlongyx.core.framework.context.AppContext;
/*    */ import com.linlongyx.sanguo.webgame.net.handler.MD5;
/*    */ import java.util.ArrayList;
/*    */ import java.util.Collections;
/*    */ import java.util.Comparator;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ import org.apache.http.message.BasicNameValuePair;
/*    */ import org.slf4j.Logger;
/*    */ import org.slf4j.LoggerFactory;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class HttpHelper
/*    */ {
/*    */   private static final String SIGN_NAME = "signature";
/* 19 */   private static final Logger logger = LoggerFactory.getLogger(HttpHelper.class);
/*    */   
/*    */   public static boolean validateSign(Map<String, List<String>> uriParams) {
/* 22 */     if (!AppContext.isHttpSwitch()) {
/* 23 */       return true;
/*    */     }
/* 25 */     String sign = ((List<String>)uriParams.get("signature")).get(0);
/* 26 */     uriParams.remove("signature");
/* 27 */     System.out.println(makeSign(uriParams));
/* 28 */     if (!sign.equals(makeSign(uriParams))) {
/* 29 */       logger.info("validateSign fail: sign:" + sign + ", makeSign:" + makeSign(uriParams));
/* 30 */       StringBuilder stringBuilder = new StringBuilder();
/* 31 */       uriParams.entrySet().forEach(entry -> stringBuilder.append((String)entry.getKey()).append("=").append(((List<String>)entry.getValue()).get(0)).append(","));
/* 32 */       logger.info("validateSign fail: uriParams:" + stringBuilder.toString());
/* 33 */       return false;
/*    */     } 
/* 35 */     return true;
/*    */   }
/*    */   
/*    */   public static String makeSign(Map<String, List<String>> uriParams) {
/* 39 */     List<BasicNameValuePair> paramsArr = new ArrayList<>();
/*    */     
/* 41 */     for (Map.Entry<String, List<String>> entry : uriParams.entrySet()) {
/* 42 */       BasicNameValuePair param = new BasicNameValuePair(entry.getKey(), ((List<String>)entry.getValue()).get(0));
/* 43 */       paramsArr.add(param);
/*    */     } 
/*    */     
/* 46 */     return makeSign(paramsArr);
/*    */   }
/*    */   
/*    */   public static String makeSign(List<BasicNameValuePair> params) {
/* 50 */     Collections.sort(params, Comparator.comparing(o -> o.getName()));
/* 51 */     StringBuilder stringBuilder = new StringBuilder();
/* 52 */     stringBuilder.setLength(0);
/* 53 */     for (BasicNameValuePair param : params) {
/* 54 */       stringBuilder.append(param.getName()).append("=").append(param.getValue()).append("&");
/*    */     }
/*    */     
/* 57 */     return (new MD5()).toDigest(stringBuilder.append(AppContext.getSecretKey()).toString());
/*    */   }
/*    */   
/*    */   public static String buildUrl(Map<String, String> uriParams) {
/* 61 */     List<BasicNameValuePair> paramsArr = new ArrayList<>();
/*    */     
/* 63 */     for (Map.Entry<String, String> entry : uriParams.entrySet()) {
/* 64 */       BasicNameValuePair param = new BasicNameValuePair(entry.getKey(), entry.getValue());
/* 65 */       paramsArr.add(param);
/*    */     } 
/* 67 */     Collections.sort(paramsArr, Comparator.comparing(o -> o.getName()));
/* 68 */     StringBuilder stringBuilder = new StringBuilder();
/* 69 */     for (BasicNameValuePair param : paramsArr) {
/* 70 */       stringBuilder.append(param.getName()).append("=").append(param.getValue()).append("&");
/*    */     }
/* 72 */     stringBuilder.append("signature").append("=").append(makeSign(paramsArr));
/* 73 */     return stringBuilder.toString();
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\sanguo\webgame\net\http\HttpHelper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */