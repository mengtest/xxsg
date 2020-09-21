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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 40 */     if (!sign.equals(makeSign(uriParams))) {
/* 41 */       System.out.println("validateSign sign:" + sign + ", makeSign:" + makeSign(uriParams));
/* 42 */       logger.info("validateSign fail: sign:" + sign + ", makeSign:" + makeSign(uriParams));
/* 43 */       StringBuilder stringBuilder = new StringBuilder();
/* 44 */       uriParams.entrySet().forEach(entry -> stringBuilder.append((String)entry.getKey()).append("=").append(((List<String>)entry.getValue()).get(0)).append(","));
/* 45 */       logger.info("validateSign fail: uriParams:" + stringBuilder.toString());
/* 46 */       return false;
/*    */     } 
/* 48 */     return true;
/*    */   }
/*    */   
/*    */   public static String makeSign(Map<String, List<String>> uriParams) {
/* 52 */     List<BasicNameValuePair> paramsArr = new ArrayList<>();
/*    */     
/* 54 */     for (Map.Entry<String, List<String>> entry : uriParams.entrySet()) {
/* 55 */       BasicNameValuePair param = new BasicNameValuePair(entry.getKey(), ((List<String>)entry.getValue()).get(0));
/* 56 */       paramsArr.add(param);
/*    */     } 
/*    */     
/* 59 */     return makeSign(paramsArr);
/*    */   }
/*    */   
/*    */   public static String makeSign(List<BasicNameValuePair> params) {
/* 63 */     Collections.sort(params, Comparator.comparing(o -> o.getName()));
/* 64 */     StringBuilder stringBuilder = new StringBuilder();
/* 65 */     stringBuilder.setLength(0);
/* 66 */     for (BasicNameValuePair param : params) {
/* 67 */       stringBuilder.append(param.getName()).append("=").append(param.getValue()).append("&");
/*    */     }
/*    */     
/* 70 */     return (new MD5()).toDigest(stringBuilder.append(AppContext.getSecretKey()).toString());
/*    */   }
/*    */   
/*    */   public static String buildUrl(Map<String, String> uriParams) {
/* 74 */     List<BasicNameValuePair> paramsArr = new ArrayList<>();
/*    */     
/* 76 */     for (Map.Entry<String, String> entry : uriParams.entrySet()) {
/* 77 */       BasicNameValuePair param = new BasicNameValuePair(entry.getKey(), entry.getValue());
/* 78 */       paramsArr.add(param);
/*    */     } 
/* 80 */     Collections.sort(paramsArr, Comparator.comparing(o -> o.getName()));
/* 81 */     StringBuilder stringBuilder = new StringBuilder();
/* 82 */     for (BasicNameValuePair param : paramsArr) {
/* 83 */       stringBuilder.append(param.getName()).append("=").append(param.getValue()).append("&");
/*    */     }
/* 85 */     stringBuilder.append("signature").append("=").append(makeSign(paramsArr));
/* 86 */     return stringBuilder.toString();
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\net\http\HttpHelper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */