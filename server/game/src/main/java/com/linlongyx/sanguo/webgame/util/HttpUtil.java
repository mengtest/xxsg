/*     */ package com.linlongyx.sanguo.webgame.util;
/*     */ 
/*     */ import com.linlongyx.core.framework.concurrent.Fibers;
/*     */ import com.linlongyx.core.utils.GsonUtil;
/*     */ import com.linlongyx.sanguo.webgame.log.LogUtil;
/*     */ import java.io.BufferedReader;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStreamReader;
/*     */ import java.io.OutputStreamWriter;
/*     */ import java.net.HttpURLConnection;
/*     */ import java.net.URL;
/*     */ import java.net.URLConnection;
/*     */ import java.util.concurrent.Executor;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class HttpUtil
/*     */ {
/*  20 */   private static Executor executor = Fibers.createExecutorService();
/*     */   
/*     */   public static void get(String url, String param) {
/*  23 */     executor.execute(() -> http_get(url, param));
/*     */   }
/*     */   
/*     */   public static void post(String url, String param) {
/*  27 */     executor.execute(() -> http_post(url, param));
/*     */   }
/*     */   
/*     */   public static String http_get(String url, String param) {
/*  31 */     String result = "";
/*  32 */     BufferedReader in = null;
/*     */     try {
/*  34 */       String urlNameString = url + "?" + param;
/*  35 */       URL realUrl = new URL(urlNameString);
/*     */       
/*  37 */       URLConnection connection = realUrl.openConnection();
/*     */       
/*  39 */       connection.setRequestProperty("accept", "*/*");
/*  40 */       connection.setRequestProperty("connection", "Keep-Alive");
/*  41 */       connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
/*     */       
/*  43 */       connection.connect();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  51 */       in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
/*     */       String line;
/*  53 */       while ((line = in.readLine()) != null) {
/*  54 */         result = result + line;
/*     */       }
/*  56 */     } catch (Exception e) {
/*  57 */       LogUtil.errorLog(new Object[] { "HttpUtil::http_get::error1", url, "发送GET请求出现异常！" + e.getMessage() });
/*     */     } finally {
/*     */ 
/*     */       
/*     */       try {
/*  62 */         if (in != null) {
/*  63 */           in.close();
/*     */         }
/*  65 */       } catch (Exception e2) {
/*  66 */         LogUtil.errorLog(new Object[] { "HttpUtil::http_get::error1", url, "发送GET请求出现异常！" + e2.getMessage() });
/*     */       } 
/*     */     } 
/*     */     
/*  70 */     LogUtil.errorLog(new Object[] { "HttpUtil::http_get", url, GsonUtil.toJson(result) });
/*  71 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String http_post(String strURL, String params) {
/*  81 */     String result = "";
/*  82 */     BufferedReader in = null;
/*     */     try {
/*  84 */       URL url = new URL(strURL);
/*  85 */       HttpURLConnection connection = (HttpURLConnection)url.openConnection();
/*  86 */       connection.setDoOutput(true);
/*  87 */       connection.setDoInput(true);
/*  88 */       connection.setUseCaches(false);
/*  89 */       connection.setInstanceFollowRedirects(true);
/*  90 */       connection.setRequestMethod("POST");
/*  91 */       connection.setRequestProperty("connection", "Keep-Alive");
/*  92 */       connection.setRequestProperty("accept", "*/*");
/*  93 */       connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
/*  94 */       connection.connect();
/*  95 */       OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream(), "UTF-8");
/*  96 */       out.write(params);
/*  97 */       out.flush();
/*  98 */       out.close();
/*     */       
/* 100 */       in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
/*     */       String line;
/* 102 */       while ((line = in.readLine()) != null) {
/* 103 */         result = result + line;
/*     */       }
/* 105 */     } catch (IOException e) {
/* 106 */       LogUtil.errorLog(new Object[] { "HttpUtil::http_post", strURL, "发送post请求出现异常！" + e.getMessage() });
/*     */     } finally {
/*     */       try {
/* 109 */         if (in != null) {
/* 110 */           in.close();
/*     */         }
/* 112 */       } catch (Exception e) {
/* 113 */         LogUtil.errorLog(new Object[] { "HttpUtil::http_post", strURL, "发送post关闭输入流出现异常！" + e.getMessage() });
/*     */       } 
/*     */     } 
/* 116 */     return result;
/*     */   }
/*     */ 
/*     */   
/*     */   public static void main(String[] args) {
/* 121 */     String param = "";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 131 */     String url = "http://www.baidu.com";
/*     */     
/* 133 */     for (int i = 0; i < 100; i++)
/* 134 */       get(url, param); 
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgam\\util\HttpUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */