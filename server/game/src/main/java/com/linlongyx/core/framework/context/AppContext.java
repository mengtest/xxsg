/*     */ package com.linlongyx.core.framework.context;
/*     */ 
/*     */ import com.linlongyx.core.framework.dao.DAOService;
/*     */ import com.linlongyx.core.framework.dao.IDAO;
/*     */ import org.springframework.beans.BeansException;
/*     */ import org.springframework.context.ApplicationContext;
/*     */ import org.springframework.context.ApplicationContextAware;
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
/*     */ public class AppContext
/*     */   implements ApplicationContextAware
/*     */ {
/*     */   public static final String APP_CONTEXT = "appContext";
/*     */   public static final String APP_SESSION = "appSession";
/*     */   public static final String JDBC_TEMPLATE = "jdbcTemplate";
/*     */   public static final String SERVER_MANAGER = "serverManager";
/*     */   public static final String WS_SERVER = "wsServer";
/*     */   public static final String WSS_SERVER = "wssServer";
/*     */   public static final String TCP_SERVER = "tcpServer";
/*     */   public static final String HTTP_SERVER = "httpServer";
/*     */   public static final String FLASH_POLICY_SERVER = "flashPolicyServer";
/*     */   public static final String DATA_SERVICE = "dataService";
/*     */   public static final String DB_INCREMENT_SERVICE = "dbIncrementService";
/*     */   public static final String DAO_SERVICE = "daoService";
/*     */   public static final String LOOKUP_SERVICE = "lookUpService";
/*     */   public static final String PUBMAIL_SERVICE = "pubMailService";
/*     */   public static final String CAMP_SERVICE = "campService";
/*     */   public static final String GANG_SERVICE = "gangService";
/*     */   public static final String GUILD_SERVICE = "guildService";
/*     */   public static final String PLAYER_BASE_SERVICE = "playerBaseService";
/*     */   public static final String HOT_UPDATE = "hotUpdate";
/*     */   public static final String GM_LIST = "gmList";
/*     */   public static final String GOODS_SERVICE = "goodsService";
/*     */   public static final String EXRANK_SERVICE = "exRankService";
/*     */   public static final String RANK_BASE_SERVICE = "rankBaseService";
/*     */   public static final String MENTAL_RANK_SERVICE = "mentalRankService";
/*     */   protected static ApplicationContext applicationContext;
/*     */   private static DAOService daoService;
/*     */   private static boolean debug;
/*     */   private static String serverId;
/*     */   private static boolean httpSwitch;
/*     */   private static String secretKey;
/*     */   private static String url;
/*     */   private static String logTable;
/*     */   private static String platform;
/*     */   
/*     */   public static String getServerId() {
/*  58 */     return serverId;
/*     */   }
/*     */   public void setServerId(String serverId) {
/*  61 */     AppContext.serverId = serverId;
/*     */   } public static boolean getDebug() {
/*  63 */     return debug;
/*     */   } public void setDebug(boolean debug) {
/*  65 */     AppContext.debug = debug;
/*     */   } public static int getDebugInt() {
/*  67 */     return debug ? 1 : 0;
/*     */   } public static String getLogTable() {
/*  69 */     return logTable;
/*     */   }
/*     */   public void setLogTable(String logTable) {
/*  72 */     AppContext.logTable = logTable;
/*     */   }
/*     */   
/*     */   public void setDaoService(DAOService daoService) {
/*  76 */     AppContext.daoService = daoService;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
/*  82 */     AppContext.applicationContext = applicationContext;
/*     */   }
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
/*     */   public static Object getBean(String beanName) {
/*  96 */     if (null == beanName) {
/*  97 */       return null;
/*     */     }
/*  99 */     return applicationContext.getBean(beanName);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static IDAO getDAO() {
/* 107 */     return daoService.getDao();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void initialized() {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean isHttpSwitch() {
/* 124 */     return httpSwitch;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setHttpSwitch(boolean httpSwitch) {
/* 131 */     AppContext.httpSwitch = httpSwitch;
/*     */   }
/*     */   
/*     */   public static String getSecretKey() {
/* 135 */     return secretKey;
/*     */   }
/*     */   
/*     */   public void setSecretKey(String secretKey) {
/* 139 */     AppContext.secretKey = secretKey;
/*     */   }
/*     */   
/*     */   public static String getUrl() {
/* 143 */     return url;
/*     */   }
/*     */   
/*     */   public void setUrl(String url) {
/* 147 */     AppContext.url = url;
/*     */   }
/*     */   
/*     */   public static String getPlatform() {
/* 151 */     return platform;
/*     */   }
/*     */   public void setPlatform(String platform) {
/* 154 */     AppContext.platform = platform;
/*     */   }
/*     */   
/*     */   public static long getPlayerInitId() {
/* 158 */     return Long.parseLong(serverId) * 1000000L;
/*     */   }
/*     */   
/*     */   public static void gmSetPlatfrom(String platform) {
/* 162 */     AppContext.platform = platform;
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\core\framework\context\AppContext.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */