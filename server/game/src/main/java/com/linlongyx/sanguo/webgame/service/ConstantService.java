/*     */ package com.linlongyx.sanguo.webgame.service;
/*     */ 
/*     */ import com.google.gson.reflect.TypeToken;
/*     */ import com.linlongyx.core.framework.concurrent.Fibers;
/*     */ import com.linlongyx.core.framework.context.AppContext;
/*     */ import com.linlongyx.core.framework.dao.mysql.MysqlDAO;
/*     */ import com.linlongyx.core.utils.GsonUtil;
/*     */ import com.linlongyx.core.utils.TimeUtil;
/*     */ import com.linlongyx.sanguo.webgame.constant.MContext;
/*     */ import com.linlongyx.sanguo.webgame.log.LogUtil;
/*     */ import com.linlongyx.sanguo.webgame.processors.turnplate.TurnplateUtil;
/*     */ import com.linlongyx.sanguo.webgame.processors.welfare.WelfareUtil;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.IntIntValue;
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.TreeMap;
/*     */ import java.util.concurrent.ScheduledExecutorService;
/*     */ import java.util.concurrent.TimeUnit;
/*     */ import org.apache.commons.lang3.StringUtils;
/*     */ import org.springframework.jdbc.core.JdbcTemplate;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ConstantService
/*     */ {
/*  29 */   protected static final ScheduledExecutorService scheduler = Fibers.createScheduler();
/*     */   
/*  31 */   private static Map<String, String> constantMap = new HashMap<>();
/*     */   public static final String SERVER_NAME = "serverName";
/*     */   public static final String OPENTIME = "openTime";
/*     */   public static final String DAILY_WORLD_LEVEL = "dailyWorldLevel";
/*     */   public static final String HEFU = "hefu";
/*     */   public static final String HEFUSERVER = "hefuserver";
/*     */   public static final String GROWFUND = "growfund";
/*     */   public static final String DAILYWORLDLEVEL = "dailyWorldLevel";
/*     */   public static final String WEEKWORLDLEVEL = "weekWorldLevel";
/*     */   public static final String GROUPCHARGE = "groupcharge";
/*  41 */   public static HashSet<Integer> hefuServers = new HashSet<>();
/*     */   public static final String TURNPLATE = "turnplate";
/*  43 */   public static String TURNTABLE = "turntable";
/*  44 */   public static Map<Integer, Byte> turntableStateMap = new HashMap<>();
/*  45 */   public static String DIVINE = "divine";
/*  46 */   public static Map<Integer, IntIntValue> divineMap = new HashMap<>();
/*     */   
/*  48 */   public static String CROSS_RANK = "crossRankVersion2";
/*  49 */   public static Map<Integer, Map<Integer, Byte>> crossRankMap = new HashMap<>();
/*     */   
/*     */   public static void init() {
/*  52 */     MysqlDAO mysql = (MysqlDAO)AppContext.getDAO();
/*  53 */     JdbcTemplate template = mysql.getTemplate();
/*  54 */     String selectSql = "SELECT * FROM tb_constant";
/*  55 */     List<Map<String, Object>> info = template.queryForList(selectSql);
/*  56 */     for (Map<String, Object> map : info) {
/*  57 */       String key = (String)map.get("key");
/*  58 */       String value = (String)map.get("value");
/*  59 */       constantMap.put(key, value);
/*     */     } 
/*  61 */     MContext.initFromLogin();
/*  62 */     if (StringUtils.isEmpty(MContext.getServerName())) {
/*  63 */       if (constantMap.containsKey("serverName")) {
/*  64 */         MContext.setServerName(constantMap.get("serverName"));
/*     */       } else {
/*  66 */         insert("serverName", MContext.getServerName());
/*     */       } 
/*     */     }
/*  69 */     if (constantMap.containsKey("openTime")) {
/*  70 */       MContext.setOpenTime(constantMap.get("openTime"));
/*  71 */       MContext.initOpenTimeInt();
/*     */     } else {
/*  73 */       insert("openTime", MContext.getOpenTime());
/*     */     } 
/*  75 */     if (constantMap.containsKey("dailyWorldLevel")) {
/*  76 */       WelfareUtil.setDailyWorldLevel(Integer.parseInt(constantMap.get("dailyWorldLevel")));
/*     */     } else {
/*  78 */       WelfareUtil.initRanksMySql();
/*  79 */       int level = WelfareUtil.getDailyWorldLevel();
/*  80 */       insert("dailyWorldLevel", level + "");
/*     */     } 
/*     */     
/*  83 */     if (constantMap.containsKey("weekWorldLevel")) {
/*  84 */       WelfareUtil.weekWorldLevel.set(Integer.parseInt(constantMap.get("weekWorldLevel")));
/*     */     } else {
/*  86 */       int level = WelfareUtil.getDailyWorldLevel();
/*  87 */       insert("weekWorldLevel", level + "");
/*  88 */       WelfareUtil.weekWorldLevel.set(level);
/*     */     } 
/*     */     
/*  91 */     if (constantMap.containsKey("growfund")) {
/*  92 */       WelfareUtil.growfundNum.set(Integer.parseInt(constantMap.get("growfund")));
/*     */     } else {
/*  94 */       WelfareUtil.initGrowFundSql();
/*  95 */       insert("growfund", WelfareUtil.growfundNum.get() + "");
/*     */     } 
/*     */     
/*  98 */     if (constantMap.containsKey("groupcharge")) {
/*  99 */       WelfareUtil.groupCharegeNum.set(Integer.parseInt(constantMap.get("groupcharge")));
/*     */     } else {
/* 101 */       WelfareUtil.initGroupFirstChargeSql();
/* 102 */       insert("groupcharge", WelfareUtil.groupCharegeNum.get() + "");
/*     */     } 
/*     */ 
/*     */     
/* 106 */     if (constantMap.containsKey("turnplate")) {
/* 107 */       IntIntValue intIntValue = (IntIntValue)GsonUtil.fromJson(constantMap.get("turnplate"), IntIntValue.class);
/* 108 */       if (TimeUtil.inSameDay(intIntValue.value)) {
/* 109 */         TurnplateUtil.initGoldPool(intIntValue.key);
/*     */       } else {
/* 111 */         TurnplateUtil.initGoldPool(0);
/* 112 */         intIntValue.key = TurnplateUtil.getGoldPool();
/* 113 */         intIntValue.value = TurnplateUtil.getLastModifytime();
/* 114 */         updateValue("turnplate", GsonUtil.toJson(intIntValue));
/*     */       } 
/*     */     } else {
/* 117 */       IntIntValue intIntValue = new IntIntValue();
/* 118 */       TurnplateUtil.initGoldPool(0);
/* 119 */       intIntValue.key = TurnplateUtil.getGoldPool();
/* 120 */       intIntValue.value = TurnplateUtil.getLastModifytime();
/* 121 */       insert("turnplate", GsonUtil.toJson(intIntValue));
/*     */     } 
/*     */ 
/*     */     
/* 125 */     if (constantMap.containsKey("hefu")) {
/* 126 */       MContext.setHeFu("1".equals(constantMap.get("hefu")));
/*     */     }
/* 128 */     if (constantMap.containsKey("hefuserver") && 
/* 129 */       !"[]".equals(constantMap.get("hefuserver"))) {
/* 130 */       hefuServers = (HashSet<Integer>)GsonUtil.fromJson(constantMap.get("hefuserver"), (new TypeToken<HashSet<Integer>>() {  }).getType());
/*     */     }
/*     */ 
/*     */     
/* 134 */     if (constantMap.containsKey(TURNTABLE)) {
/* 135 */       turntableStateMap = (Map<Integer, Byte>)GsonUtil.fromJson(constantMap.get(TURNTABLE), (new TypeToken<HashMap<Integer, Byte>>() {  }).getType());
/*     */     } else {
/* 137 */       insert(TURNTABLE, GsonUtil.toJson(turntableStateMap));
/*     */     } 
/* 139 */     if (constantMap.containsKey(DIVINE)) {
/* 140 */       divineMap = (Map<Integer, IntIntValue>)GsonUtil.fromJson(constantMap.get(DIVINE), (new TypeToken<HashMap<Integer, IntIntValue>>() {  }).getType());
/*     */     } else {
/* 142 */       insert(DIVINE, GsonUtil.toJson(divineMap));
/*     */     } 
/* 144 */     if (constantMap.containsKey(CROSS_RANK)) {
/* 145 */       crossRankMap = (Map<Integer, Map<Integer, Byte>>)GsonUtil.fromJson(constantMap.get(CROSS_RANK), (new TypeToken<HashMap<Integer, TreeMap<Integer, Byte>>>() {  }).getType());
/*     */     } else {
/* 147 */       insert(CROSS_RANK, GsonUtil.toJson(crossRankMap));
/*     */     } 
/* 149 */     scheduler.scheduleWithFixedDelay(ConstantService::saveToDB, 1L, 1L, TimeUnit.MINUTES);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static String getNumberValue(String key) {
/* 155 */     if (constantMap.containsKey(key))
/* 156 */       return constantMap.get(key); 
/* 157 */     return "0";
/*     */   }
/*     */   
/*     */   public static synchronized void updateValue(String key, String value) {
/* 161 */     constantMap.put(key, value);
/* 162 */     update(key, value);
/*     */   }
/*     */   public static synchronized void reset() {
/* 165 */     saveToDB();
/* 166 */     WelfareUtil.updateWorldLevel("dailyWorldLevel");
/*     */   }
/*     */   
/*     */   public static void insert(String key, String value) {
/*     */     try {
/* 171 */       MysqlDAO mysql = (MysqlDAO)AppContext.getDAO();
/* 172 */       JdbcTemplate template = mysql.getTemplate();
/* 173 */       String insertSql = "INSERT INTO tb_constant (`key`,`value`) VALUES ('" + key + "','" + value + "');";
/* 174 */       template.update(insertSql);
/* 175 */     } catch (Exception e) {
/* 176 */       LogUtil.errorLog(new Object[] { "ConstantService::insert", e.getMessage() });
/*     */     } 
/* 178 */     constantMap.put(key, value);
/*     */   }
/*     */   
/*     */   public static void update(String key, String value) {
/*     */     try {
/* 183 */       MysqlDAO mysql = (MysqlDAO)AppContext.getDAO();
/* 184 */       JdbcTemplate template = mysql.getTemplate();
/*     */       
/* 186 */       String updateSql = "REPLACE INTO tb_constant (`key`,`value`) VALUES ('" + key + "','" + value + "')";
/* 187 */       template.update(updateSql);
/* 188 */     } catch (Exception e) {
/* 189 */       LogUtil.errorLog(new Object[] { "ConstantService::update", e.getMessage() });
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static void saveToDB() {
/* 195 */     updateValue("growfund", WelfareUtil.growfundNum.get() + "");
/* 196 */     updateValue("groupcharge", WelfareUtil.groupCharegeNum.get() + "");
/* 197 */     updateValue(TURNTABLE, GsonUtil.toJson(turntableStateMap));
/* 198 */     updateValue(DIVINE, GsonUtil.toJson(divineMap));
/* 199 */     updateValue(CROSS_RANK, GsonUtil.toJson(crossRankMap));
/*     */   }
/*     */   
/*     */   public static Map<String, String> getConstantMap() {
/* 203 */     return constantMap;
/*     */   }
/*     */   
/*     */   public static void setConstantMap(Map<String, String> constantMap) {
/* 207 */     ConstantService.constantMap = constantMap;
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\service\ConstantService.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */