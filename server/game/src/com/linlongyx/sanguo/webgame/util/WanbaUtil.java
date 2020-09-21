/*     */ package com.linlongyx.sanguo.webgame.util;
/*     */ import com.alibaba.fastjson.JSON;
/*     */ import com.linlongyx.sanguo.webgame.constant.MContext;
/*     */ import java.net.URL;
/*     */ import java.net.URLEncoder;
/*     */ import java.security.InvalidKeyException;
/*     */ import java.security.NoSuchAlgorithmException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Comparator;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import javax.crypto.Mac;
/*     */ import javax.crypto.spec.SecretKeySpec;
/*     */ import org.apache.http.message.BasicNameValuePair;
/*     */ import org.slf4j.Logger;
/*     */ 
/*     */ public class WanbaUtil {
/*  19 */   private static Logger LOG = LoggerFactory.getLogger(WanbaUtil.class);
/*     */   
/*     */   private static final String WANBA_HTTP = "https://api.urlshare.cn";
/*     */   private static final String GET_VIP_LEVEL = "https://api.urlshare.cn/v3/user/get_vip_level";
/*     */   private static final String GET_GIFT_EXCHANG = "https://api.urlshare.cn/v3/user/gift_exchange";
/*     */   private static final String APPKEY_WANBA = "znbSTOtzmuYhKAcl";
/*     */   private static final String PF_WANBA = "wanba_ts";
/*     */   private static final String APPID_WANBA = "1107998363";
/*     */   public static final int CHANNLE_UID = 201;
/*     */   
/*     */   public static boolean isWanba() {
/*  30 */     if (MContext.getPlatform().equals("wanba")) {
/*  31 */       return true;
/*     */     }
/*  33 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public static short checkVIPGift(int gift_id, String openId, String openKey, String pf) {
/*  38 */     Map<String, String> uriParams = new HashMap<>();
/*  39 */     uriParams.put("openid", openId);
/*  40 */     uriParams.put("openkey", openKey);
/*  41 */     uriParams.put("gift_id", String.valueOf(gift_id));
/*  42 */     uriParams.put("appid", "1107998363");
/*  43 */     uriParams.put("pf", pf);
/*  44 */     uriParams.put("format", "json");
/*  45 */     String param = getSigParam("znbSTOtzmuYhKAcl", "https://api.urlshare.cn/v3/user/gift_exchange", uriParams);
/*  46 */     String json = HttpUtil.http_get("https://api.urlshare.cn/v3/user/gift_exchange", param);
/*  47 */     LOG.error(json);
/*  48 */     Map map = (Map)JSON.parse(json);
/*     */     
/*  50 */     if (map.containsKey("ret")) {
/*  51 */       return Short.valueOf(String.valueOf(map.get("ret"))).shortValue();
/*     */     }
/*  53 */     return -1;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static Map getVIPLevelMessage(String openid, String openkey, String pf) {
/*  59 */     Map<String, String> uriParams = new HashMap<>();
/*  60 */     uriParams.put("openid", openid);
/*  61 */     uriParams.put("openkey", openkey);
/*  62 */     uriParams.put("appid", "1107998363");
/*  63 */     uriParams.put("pf", pf);
/*  64 */     uriParams.put("format", "json");
/*  65 */     String param = getSigParam("znbSTOtzmuYhKAcl", "https://api.urlshare.cn/v3/user/get_vip_level", uriParams);
/*  66 */     String json = HttpUtil.http_get("https://api.urlshare.cn/v3/user/get_vip_level", param);
/*  67 */     LOG.error(json);
/*  68 */     return (Map)JSON.parse(json);
/*     */   }
/*     */   
/*     */   public static int getVIPLevel(String openid, String openkey, String pf) {
/*  72 */     Map map = getVIPLevelMessage(openid, openkey, pf);
/*  73 */     if (map.containsKey("level")) {
/*  74 */       return ((Integer)map.get("level")).intValue();
/*     */     }
/*  76 */     return 0;
/*     */   }
/*     */   
/*     */   public static int getScore(String openid, String openkey, String pf) {
/*  80 */     Map map = getVIPLevelMessage(openid, openkey, pf);
/*  81 */     if (map.containsKey("score")) {
/*  82 */       return ((Integer)map.get("score")).intValue();
/*     */     }
/*  84 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public static String getSigParam(String appkey, String urlString, Map<String, String> uriParams) {
/*  89 */     String sigUrl = "";
/*     */     try {
/*  91 */       URL url = new URL(urlString);
/*  92 */       String uriString = URLEncoder.encode(url.getPath(), "UTF-8");
/*     */       
/*  94 */       List<BasicNameValuePair> paramsArr = new ArrayList<>();
/*  95 */       for (Map.Entry<String, String> entry : uriParams.entrySet()) {
/*  96 */         if (((String)entry.getKey()).equals("sig")) {
/*     */           continue;
/*     */         }
/*  99 */         BasicNameValuePair param = new BasicNameValuePair(entry.getKey(), entry.getValue());
/* 100 */         paramsArr.add(param);
/*     */       } 
/* 102 */       Collections.sort(paramsArr, new Comparator<BasicNameValuePair>() {
/*     */             public int compare(BasicNameValuePair o1, BasicNameValuePair o2) {
/* 104 */               return o1.getName().compareTo(o2.getName());
/*     */             }
/*     */           });
/* 107 */       StringBuilder paramsStringBuilder = new StringBuilder();
/* 108 */       for (BasicNameValuePair param : paramsArr) {
/* 109 */         paramsStringBuilder.append(param.getName()).append("=").append(param.getValue()).append("&");
/*     */       }
/* 111 */       String paramsString = paramsStringBuilder.substring(0, paramsStringBuilder.length() - 1);
/* 112 */       String paramsStringEncode = URLEncoder.encode(paramsString, "UTF-8");
/* 113 */       String httpType = "GET";
/* 114 */       String sourceString = httpType + "&" + uriString + "&" + paramsStringEncode;
/* 115 */       String sig = Base64Util.encode(hamcsha1(sourceString, appkey + "&"));
/* 116 */       sigUrl = paramsString + "&sig=" + URLEncoder.encode(sig, "UTF-8");
/* 117 */     } catch (Exception e) {
/* 118 */       e.printStackTrace();
/*     */     } 
/* 120 */     return sigUrl;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static byte[] hamcsha1(String data, String key) {
/*     */     try {
/* 127 */       SecretKeySpec signingKey = new SecretKeySpec(key.getBytes(), "HmacSHA1");
/* 128 */       Mac mac = Mac.getInstance("HmacSHA1");
/* 129 */       mac.init(signingKey);
/* 130 */       return mac.doFinal(data.getBytes());
/* 131 */     } catch (NoSuchAlgorithmException e) {
/* 132 */       e.printStackTrace();
/* 133 */     } catch (InvalidKeyException e) {
/* 134 */       e.printStackTrace();
/*     */     } 
/* 136 */     return null;
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgam\\util\WanbaUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */