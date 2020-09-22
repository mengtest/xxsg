/*     */ package com.linlongyx.sanguo.webgame.rmi.runewar;
/*     */ 
/*     */ import com.fasterxml.jackson.core.JsonProcessingException;
/*     */ import com.linlongyx.core.framework.dao.redis.RedisClientTemplate;
/*     */ import com.linlongyx.core.utils.TimeUtil;
/*     */ import com.linlongyx.sanguo.webgame.constant.MContext;
/*     */ import com.linlongyx.sanguo.webgame.log.LogUtil;
/*     */ import com.linlongyx.sanguo.webgame.processors.cross.CrossUtil;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.BattleRecordData;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.RankingData;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.RunewarFightersData;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.RunewarPlayerData;
/*     */ import com.linlongyx.sanguo.webgame.rmi.data.CoordinateData;
/*     */ import com.linlongyx.sanguo.webgame.rmi.data.PlayerData;
/*     */ import java.io.IOException;
/*     */ import java.util.Arrays;
/*     */ import java.util.List;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class RunewarUtil
/*     */ {
/*  27 */   public static int SERVER_SIZE = 50;
/*     */   public static final int DEFAULT_DISTANCE = 3;
/*     */   public static final int MAX_PLAYER_NODE = 2;
/*  30 */   private static String RUNEWAR_KEY_PREFIX = "runewar_";
/*     */   
/*  32 */   private static RunewarCache runewarCache = new RunewarCache();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void balanceRank() {
/*  38 */     runewarCache.balanceRank();
/*  39 */     saveToDb();
/*     */   }
/*     */ 
/*     */   
/*     */   public static void newRunewar() {
/*  44 */     runewarCache.reZone();
/*  45 */     saveToDb();
/*     */   }
/*     */ 
/*     */   
/*     */   public static void playerJoin(PlayerData playerData) {
/*  50 */     if (runewarCache.isOpen() && runewarCache.getServerToZone().isEmpty()) {
/*  51 */       runewarCache.reZone();
/*     */     }
/*  53 */     runewarCache.playerJoin(playerData);
/*     */   }
/*     */   
/*     */   public static boolean isOpen() {
/*  57 */     return runewarCache.isOpen();
/*     */   }
/*     */   
/*     */   public static List<RunewarPlayerData> getRunewardPlayerData(int serverId, long playerId) {
/*  61 */     return runewarCache.getRunewardPlayerData(serverId, playerId);
/*     */   }
/*     */   
/*     */   public static RunewarFightersData getRunewarFighterData(int serverId, long playerId, long targetPlayerId) {
/*  65 */     return runewarCache.getRunewarFighterData(serverId, playerId, targetPlayerId);
/*     */   }
/*     */   
/*     */   public static List<RankingData> getRankList(int serverId) {
/*  69 */     return runewarCache.getRankList(serverId);
/*     */   }
/*     */   
/*     */   public static PlayerData getRunewarTargetPlayerdata(int serverId, long playerId, long targetPlayerId) {
/*  73 */     return runewarCache.getRunewarTargetPlayerdata(serverId, playerId, targetPlayerId);
/*     */   }
/*     */   
/*     */   public static int runewarFightResult(int serverId, long playerId, long targetPlayerId, int targetServerId, int point, boolean win, BattleRecordData record) {
/*  77 */     return runewarCache.runewarFightResult(serverId, playerId, targetPlayerId, targetServerId, point, win, record);
/*     */   }
/*     */   
/*     */   public static List<RunewarPlayerData> refreshPlayerCoordinate(int serverId, long playerId) {
/*  81 */     return runewarCache.refreshPlayerCoordinate(serverId, playerId);
/*     */   }
/*     */ 
/*     */   
/*     */   public static String getCurRunewarKey() {
/*  86 */     int days = TimeUtil.getPrevTargetWeekDay(2);
/*  87 */     return RUNEWAR_KEY_PREFIX + days;
/*     */   }
/*     */ 
/*     */   
/*     */   public static String getNextRunewarKey() {
/*  92 */     int days = TimeUtil.getNextTargetWeekDay(2);
/*  93 */     return RUNEWAR_KEY_PREFIX + days;
/*     */   }
/*     */ 
/*     */   
/*     */   public static void saveToDb() {
/*  98 */     String key = getCurRunewarKey();
/*     */     try {
/* 100 */       RedisClientTemplate redisClientTemplate = (RedisClientTemplate)MContext.getBean("redisClientTemplate", RedisClientTemplate.class);
/* 101 */       redisClientTemplate.set(key, CrossUtil.objectMapper.writeValueAsString(runewarCache));
/* 102 */     } catch (JsonProcessingException e) {
/* 103 */       e.printStackTrace();
/* 104 */       LogUtil.errorLog(new Object[] { Arrays.toString((Object[])e.getStackTrace()) });
/*     */     } 
/* 106 */     LogUtil.errorLog(new Object[] { "save runewarCache", key });
/*     */   }
/*     */ 
/*     */   
/*     */   public static void loadFromDb() {
/* 111 */     RedisClientTemplate redisClientTemplate = (RedisClientTemplate)MContext.getBean("redisClientTemplate", RedisClientTemplate.class);
/* 112 */     String key = getCurRunewarKey();
/* 113 */     if (!redisClientTemplate.exists(key).booleanValue()) {
/* 114 */       runewarCache.reZone();
/* 115 */       LogUtil.errorLog(new Object[] { "no runewarCache with key ", key, "in redis, may be first load" });
/*     */       return;
/*     */     } 
/* 118 */     String runeMapJson = redisClientTemplate.get(key);
/* 119 */     if (runeMapJson == null) {
/* 120 */       LogUtil.errorLog(new Object[] { "load runewarCache with key ", key, "from redis error" });
/*     */       return;
/*     */     } 
/*     */     try {
/* 124 */       runewarCache = (RunewarCache)CrossUtil.objectMapper.readValue(runeMapJson, RunewarCache.class);
/* 125 */     } catch (IOException e) {
/* 126 */       e.printStackTrace();
/* 127 */       LogUtil.errorLog(new Object[] { "load runewarCache ", key, "from redis error", e.getMessage() });
/*     */     } 
/*     */   }
/*     */   
/*     */   public static RunewarPlayerData transform(CoordinateData data, int axis, int distance) {
/* 132 */     RunewarPlayerData runewarPlayerData = new RunewarPlayerData();
/* 133 */     runewarPlayerData.playerId = data.getPlayerData().getPlayerId();
/* 134 */     runewarPlayerData.name = data.getPlayerData().getPlayerName();
/* 135 */     runewarPlayerData.fightValue = data.getPlayerData().getTotalValue();
/* 136 */     runewarPlayerData.point = data.getPoint();
/* 137 */     runewarPlayerData.axis = axis;
/* 138 */     runewarPlayerData.distance = distance;
/* 139 */     runewarPlayerData.data = data.getPlayerData().getModelData();
/* 140 */     return runewarPlayerData;
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\sanguo\webgame\rmi\runewar\RunewarUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */