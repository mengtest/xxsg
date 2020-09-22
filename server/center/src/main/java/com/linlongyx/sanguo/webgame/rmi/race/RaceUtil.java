/*     */ package com.linlongyx.sanguo.webgame.rmi.race;
/*     */ 
/*     */ import com.fasterxml.jackson.core.JsonProcessingException;
/*     */ import com.linlongyx.core.framework.dao.redis.RedisClientTemplate;
/*     */ import com.linlongyx.core.utils.TimeUtil;
/*     */ import com.linlongyx.sanguo.webgame.common.structure.Pair;
/*     */ import com.linlongyx.sanguo.webgame.constant.MContext;
/*     */ import com.linlongyx.sanguo.webgame.log.LogUtil;
/*     */ import com.linlongyx.sanguo.webgame.processors.cross.CrossUtil;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.RankingData;
/*     */ import com.linlongyx.sanguo.webgame.rmi.LogicRmiManager;
/*     */ import com.linlongyx.sanguo.webgame.rmi.LogicRmiUtil;
/*     */ import com.linlongyx.sanguo.webgame.rmi.data.PlayerData;
/*     */ import com.linlongyx.sanguo.webgame.rmi.data.RacePlayerData;
/*     */ import java.io.IOException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ 
/*     */ 
/*     */ public class RaceUtil
/*     */ {
/*  24 */   private static String RACE_KEY_PREFIX = "cross_race_";
/*     */   
/*  26 */   private static CrossRaceCache crossRaceCache = null;
/*  27 */   private static volatile int curRaceId = TimeUtil.getPrevTargetWeekDay(2);
/*     */   
/*     */   public static void saveToDb() {
/*  30 */     if (crossRaceCache == null)
/*     */       return;  try {
/*  32 */       RedisClientTemplate redisClientTemplate = (RedisClientTemplate)MContext.getBean("redisClientTemplate", RedisClientTemplate.class);
/*  33 */       redisClientTemplate.set(RACE_KEY_PREFIX + curRaceId, CrossUtil.objectMapper.writeValueAsString(crossRaceCache));
/*  34 */     } catch (JsonProcessingException e) {
/*  35 */       e.printStackTrace();
/*  36 */       LogUtil.errorLog(new Object[] { Arrays.toString((Object[])e.getStackTrace()) });
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void loadDataFromDb() {
/*  41 */     String key = RACE_KEY_PREFIX + curRaceId;
/*  42 */     RedisClientTemplate redisClientTemplate = (RedisClientTemplate)MContext.getBean("redisClientTemplate", RedisClientTemplate.class);
/*  43 */     if (!redisClientTemplate.exists(key).booleanValue()) {
/*  44 */       LogUtil.errorLog(new Object[] { "load cross race data error: cross race firsttime open or last save error" });
/*     */       return;
/*     */     } 
/*  47 */     String json = redisClientTemplate.get(key);
/*  48 */     if (json == null) {
/*  49 */       LogUtil.errorLog(new Object[] { "load cross race data with key", key, "from reids error" });
/*     */       return;
/*     */     } 
/*     */     try {
/*  53 */       crossRaceCache = (CrossRaceCache)CrossUtil.objectMapper.readValue(json, CrossRaceCache.class);
/*  54 */     } catch (IOException e) {
/*  55 */       e.printStackTrace();
/*  56 */       LogUtil.errorLog(new Object[] { "load cross race ", key, "from redis error", e.getMessage() });
/*     */     } 
/*     */   }
/*     */   
/*     */   private static synchronized void buildCrossRaceCache() {
/*  61 */     if (crossRaceCache != null)
/*  62 */       return;  ArrayList<Pair<Integer, Integer>> serverWorldLevels = LogicRmiManager.getServerWorldLevel();
/*  63 */     crossRaceCache = new CrossRaceCache();
/*  64 */     crossRaceCache.init(serverWorldLevels);
/*     */   }
/*     */   
/*     */   public static synchronized int newRace() {
/*  68 */     int oldRaceId = curRaceId;
/*  69 */     int newRaceId = TimeUtil.getPrevTargetWeekDay(2);
/*  70 */     if (oldRaceId == newRaceId) return oldRaceId; 
/*  71 */     saveToDb();
/*  72 */     curRaceId = newRaceId;
/*  73 */     crossRaceCache = null;
/*  74 */     buildCrossRaceCache();
/*  75 */     saveToDb();
/*  76 */     LogUtil.debugLog(new Object[] { "create new race", Integer.valueOf(curRaceId), "at", TimeUtil.getFormatDate() });
/*  77 */     return curRaceId;
/*     */   }
/*     */   
/*     */   public static int getCurRaceId() {
/*  81 */     if (crossRaceCache == null) {
/*  82 */       buildCrossRaceCache();
/*     */     }
/*  84 */     return curRaceId;
/*     */   }
/*     */   
/*     */   public static void balanceRace() {
/*  88 */     LogUtil.errorLog(new Object[] { "balance cross race", TimeUtil.getFormatDate() });
/*  89 */     crossRaceCache.balance();
/*     */   }
/*     */   
/*     */   public static void openRace() {
/*  93 */     if (crossRaceCache == null) {
/*  94 */       newRace();
/*     */     }
/*  96 */     crossRaceCache.open();
/*  97 */     saveToDb();
/*  98 */     for (Iterator<Integer> iterator = crossRaceCache.getRaceServerMap().keySet().iterator(); iterator.hasNext(); ) { int serverId = ((Integer)iterator.next()).intValue();
/*  99 */       LogicRmiUtil.sendCrossRaceNotice("RaceUtil:openRace", serverId, 1); }
/*     */     
/* 101 */     LogUtil.debugLog(new Object[] { "open race", Integer.valueOf(curRaceId), "at", TimeUtil.getFormatDate() });
/*     */   }
/*     */   
/*     */   public static void closeRace() {
/* 105 */     if (crossRaceCache != null) {
/* 106 */       crossRaceCache.close();
/* 107 */       saveToDb();
/* 108 */       LogUtil.debugLog(new Object[] { "close race", Integer.valueOf(curRaceId), "at", TimeUtil.getFormatDate() });
/*     */     } 
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
/*     */   public static int updatePlayerRacePoint(int serverId, long playerId, int point) {
/* 121 */     if (crossRaceCache == null) {
/* 122 */       buildCrossRaceCache();
/*     */     }
/* 124 */     if (!isRaceOpen()) return -1; 
/* 125 */     return crossRaceCache.updatePlayerRacePoint(serverId, playerId, point);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void updateTalismanMap(int serverId, long playerId, Map<Long, Map<Integer, Integer>> talismanMap) {
/* 135 */     if (crossRaceCache == null) {
/* 136 */       buildCrossRaceCache();
/*     */     }
/* 138 */     if (!isRaceOpen())
/* 139 */       return;  crossRaceCache.updateTalismanMap(serverId, playerId, talismanMap);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static ArrayList<RankingData> getRaceRankList(int serverId, int pageSize) {
/* 149 */     if (crossRaceCache == null) {
/* 150 */       buildCrossRaceCache();
/*     */     }
/* 152 */     return crossRaceCache.getRankList(serverId, pageSize);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static RankingData getPlayerRaceRank(int serverId, long playerId, int curPoint) {
/* 163 */     if (crossRaceCache == null) {
/* 164 */       buildCrossRaceCache();
/*     */     }
/* 166 */     return crossRaceCache.getPlayerRank(serverId, playerId, curPoint);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean isRaceOpen() {
/* 174 */     if (crossRaceCache == null) {
/* 175 */       buildCrossRaceCache();
/*     */     }
/* 177 */     return (crossRaceCache.getRaceState() == CrossRaceCache.RaceState.OPEN);
/*     */   }
/*     */   
/*     */   public static int getRaceState() {
/* 181 */     if (crossRaceCache == null) {
/* 182 */       buildCrossRaceCache();
/*     */     }
/* 184 */     return crossRaceCache.getRaceState().getState();
/*     */   }
/*     */   
/*     */   public static void addPlayerData(int worldLevel, PlayerData playerData) {
/* 188 */     if (crossRaceCache == null) {
/* 189 */       buildCrossRaceCache();
/*     */     }
/* 191 */     crossRaceCache.addServerIfAbsent(playerData.getServerId(), worldLevel);
/* 192 */     crossRaceCache.playerJoin(playerData);
/*     */   }
/*     */   
/*     */   public static RacePlayerData recommentOne(int serverId, long playerId, long fightValue, int point) {
/* 196 */     if (crossRaceCache.getRaceState() != CrossRaceCache.RaceState.OPEN) return null; 
/* 197 */     return crossRaceCache.recommentOne(serverId, playerId, fightValue, point);
/*     */   }
/*     */ 
/*     */   
/*     */   public static RankingData transform(RacePlayerData racePlayerData) {
/* 202 */     RankingData data = new RankingData();
/* 203 */     data.playerId = racePlayerData.getPlayerData().getPlayerId();
/* 204 */     data.playerName = racePlayerData.getPlayerData().getPlayerName();
/* 205 */     data.fightValue = racePlayerData.getPlayerData().getFightValue();
/* 206 */     data.level = racePlayerData.getPlayerData().getLevel();
/* 207 */     data.head = racePlayerData.getPlayerData().getHead();
/* 208 */     data.sex = racePlayerData.getPlayerData().getSex();
/* 209 */     data.vip = racePlayerData.getPlayerData().getVip();
/* 210 */     data.titleId = racePlayerData.getPlayerData().getTitleId();
/* 211 */     data.quality = racePlayerData.getPlayerData().getQuality();
/* 212 */     data.fashionId = (racePlayerData.getPlayerData().getModelData()).fashion;
/* 213 */     data.value = racePlayerData.getRacePoint();
/* 214 */     data.rank = 0;
/* 215 */     return data;
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\sanguo\webgame\rmi\race\RaceUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */