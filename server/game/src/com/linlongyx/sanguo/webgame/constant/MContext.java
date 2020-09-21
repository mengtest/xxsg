/*     */ package com.linlongyx.sanguo.webgame.constant;
/*     */ 
/*     */ import com.linlongyx.core.framework.context.AppContext;
/*     */ import com.linlongyx.sanguo.webgame.log.LogUtil;
/*     */ import com.linlongyx.sanguo.webgame.net.http.ask.StartServerEvent;
/*     */ import com.linlongyx.sanguo.webgame.service.ConstantService;
/*     */ import java.text.ParseException;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.Calendar;
/*     */ import java.util.Date;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MContext
/*     */   extends AppContext
/*     */ {
/*     */   public static final String AREANRANK_SERVICE = "arenaRankService";
/*     */   public static final String GROUP_SERVICE = "groupService";
/*     */   public static final String CONSTANT_SERVICE = "constantService";
/*     */   public static final String WORLDBOSS_SERVICE = "worldbossService";
/*     */   public static final String LOG_CLIENT = "logClient";
/*     */   public static final String RMI_MANAGER = "rmiManager";
/*     */   public static final String LOGIC_RMI_MANAGER = "logicRmiManager";
/*     */   public static final String MENTAL_RANK_SERVICE = "mentalRankService";
/*     */   public static final String MARRY_SERVICE = "marryService";
/*     */   public static final String REDPACKET_SERVICE = "redpacketService";
/*     */   private static String openTime;
/*     */   private static int openTimeInt;
/*     */   private static boolean isWhite = false;
/*     */   private static String serverName;
/*     */   private static String version;
/*     */   private static String tag;
/*     */   private static boolean heFu;
/*     */   private static int serverIdInt;
/*     */   private static String pf;
/*     */   private static String crossUrl;
/*     */   private static boolean ws;
/*     */   private static boolean wss;
/*     */   private static String jks;
/*     */   private static String keystorePass;
/*     */   private static String appId;
/*     */   private static String cchId;
/*     */   
/*     */   public static void initFromLogin() {
/*  61 */     StartServerEvent startServerEvent = new StartServerEvent();
/*  62 */     Map<String, String> map = new HashMap<>();
/*  63 */     map.put("serverId", AppContext.getServerId());
/*     */     try {
/*  65 */       if (!getDebug()) {
/*  66 */         Map<String, Object> responseMap = startServerEvent.request(map);
/*  67 */         if (responseMap != null) {
/*  68 */           if (responseMap.containsKey("serverName")) {
/*  69 */             String serverName = (String)responseMap.get("serverName");
/*  70 */             LogUtil.errorLog(new Object[] { "Start server serverName:" + serverName });
/*  71 */             System.out.println("Start server serverName:" + serverName);
/*  72 */             setServerName(serverName);
/*  73 */             ConstantService.updateValue("serverName", serverName);
/*     */           } 
/*  75 */           if (responseMap.containsKey("openTime")) {
/*  76 */             long time = ((Long)responseMap.get("openTime")).longValue();
/*  77 */             SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
/*  78 */             Calendar calendar = Calendar.getInstance();
/*  79 */             calendar.setTimeInMillis(time);
/*  80 */             calendar.set(11, 0);
/*  81 */             calendar.set(12, 0);
/*  82 */             calendar.set(13, 0);
/*  83 */             String openTime = sdf.format(new Date(calendar.getTimeInMillis()));
/*  84 */             LogUtil.errorLog(new Object[] { "Start server openTime:" + openTime });
/*  85 */             System.out.println("Start server openTime:" + openTime);
/*  86 */             setOpenTime(openTime);
/*  87 */             initOpenTimeInt();
/*  88 */             ConstantService.updateValue("openTime", openTime);
/*     */           } 
/*  90 */           if (responseMap.containsKey("isWhite")) {
/*  91 */             boolean isWhite = ((Boolean)responseMap.get("isWhite")).booleanValue();
/*  92 */             LogUtil.errorLog(new Object[] { "Start server isWhite:" + isWhite });
/*  93 */             System.out.println("Start server isWhite:" + isWhite);
/*  94 */             setIsWhite(isWhite);
/*     */           } 
/*     */           
/*  97 */           if (responseMap.containsKey("pf")) {
/*  98 */             String val = (String)responseMap.get("pf");
/*  99 */             setPf(val);
/*     */           } 
/*     */         } 
/*     */       } 
/* 103 */     } catch (Exception e) {
/* 104 */       LogUtil.errorLog(new Object[] { e.getMessage(), e });
/*     */     } 
/*     */     
/* 107 */     setServerIdInt(getServerId());
/*     */   }
/*     */ 
/*     */   
/*     */   public static String getServerName() {
/* 112 */     return serverName;
/*     */   }
/*     */   
/*     */   public static void setServerName(String serverName) {
/* 116 */     MContext.serverName = serverName;
/*     */   }
/*     */   
/*     */   public static String getOpenTime() {
/* 120 */     return openTime;
/*     */   }
/*     */   
/*     */   public static void setOpenTime(String openTime) {
/* 124 */     MContext.openTime = openTime;
/*     */   }
/*     */   
/*     */   public static int getOpenTimeInt() {
/* 128 */     if (openTimeInt == 0) {
/* 129 */       initOpenTimeInt();
/*     */     }
/* 131 */     return openTimeInt;
/*     */   }
/*     */   
/*     */   public static void initOpenTimeInt() {
/* 135 */     SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
/*     */     try {
/* 137 */       Date date = sdf.parse(openTime);
/* 138 */       openTimeInt = (int)(date.getTime() / 1000L);
/* 139 */     } catch (ParseException e) {
/* 140 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void setOpenTimeInt(int openTimeInt) {
/* 145 */     MContext.openTimeInt = openTimeInt;
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean isWhite() {
/* 150 */     return isWhite;
/*     */   }
/*     */   
/*     */   public static void setIsWhite(boolean isWhite) {
/* 154 */     MContext.isWhite = isWhite;
/*     */   }
/*     */ 
/*     */   
/*     */   public static String getVersion() {
/* 159 */     return version;
/*     */   }
/*     */   
/*     */   public void setVersion(String version) {
/* 163 */     MContext.version = version;
/*     */   }
/*     */   
/*     */   public static String getTag() {
/* 167 */     return tag;
/*     */   }
/*     */   
/*     */   public void setTag(String tag) {
/* 171 */     MContext.tag = tag;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean isHeFu() {
/* 177 */     return heFu;
/*     */   }
/*     */   
/*     */   public static void setHeFu(boolean heFu) {
/* 181 */     MContext.heFu = heFu;
/*     */   }
/*     */   
/*     */   public static int getServerIdInt() {
/* 185 */     return serverIdInt;
/*     */   }
/*     */   
/*     */   public static void setServerIdInt(String serverId) {
/* 189 */     serverIdInt = Integer.parseInt(serverId);
/*     */   }
/*     */   
/*     */   public static String getPf() {
/* 193 */     return pf;
/*     */   }
/*     */   
/*     */   public static void setPf(String pf) {
/* 197 */     MContext.pf = pf;
/*     */   }
/*     */   
/*     */   public static boolean isWs() {
/* 201 */     return ws;
/*     */   }
/*     */   
/*     */   public void setWs(boolean ws) {
/* 205 */     MContext.ws = ws;
/*     */   }
/*     */   
/*     */   public static boolean isWss() {
/* 209 */     return wss;
/*     */   }
/*     */   
/*     */   public void setWss(boolean wss) {
/* 213 */     MContext.wss = wss;
/*     */   }
/*     */ 
/*     */   
/*     */   public static String getJks() {
/* 218 */     return jks;
/*     */   }
/*     */   
/*     */   public void setJks(String jks) {
/* 222 */     MContext.jks = jks;
/*     */   }
/*     */   
/*     */   public static String getKeystorePass() {
/* 226 */     return keystorePass;
/*     */   }
/*     */   
/*     */   public void setKeystorePass(String keystorePass) {
/* 230 */     MContext.keystorePass = keystorePass;
/*     */   }
/*     */   
/*     */   public static String getCrossUrl() {
/* 234 */     return crossUrl;
/*     */   }
/*     */   
/*     */   public void setCrossUrl(String crossUrl) {
/* 238 */     MContext.crossUrl = crossUrl;
/*     */   }
/*     */   
/*     */   public static String getAppId() {
/* 242 */     return appId;
/*     */   }
/*     */   
/*     */   public void setAppId(String appId) {
/* 246 */     MContext.appId = appId;
/*     */   }
/*     */   
/*     */   public static String getCchId() {
/* 250 */     return cchId;
/*     */   }
/*     */   
/*     */   public void setCchId(String cchId) {
/* 254 */     MContext.cchId = cchId;
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\constant\MContext.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */