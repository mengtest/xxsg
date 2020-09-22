/*     */ package com.linlongyx.sanguo.webgame.rmi.tower;
/*     */ import com.fasterxml.jackson.core.JsonProcessingException;
/*     */ import com.fasterxml.jackson.core.type.TypeReference;
/*     */ import com.linlongyx.core.framework.dao.redis.RedisClientTemplate;
/*     */ import com.linlongyx.sanguo.webgame.common.structure.Pair;
/*     */ import com.linlongyx.sanguo.webgame.config.parameter.TowerOwnerParameter;
/*     */ import com.linlongyx.sanguo.webgame.constant.MContext;
/*     */ import com.linlongyx.sanguo.webgame.constant.ParameterConstant;
/*     */ import com.linlongyx.sanguo.webgame.log.LogUtil;
/*     */ import com.linlongyx.sanguo.webgame.processors.cross.CrossUtil;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.RankingData;
/*     */ import com.linlongyx.sanguo.webgame.rmi.LogicRmiManager;
/*     */ import com.linlongyx.sanguo.webgame.rmi.LogicRmiUtil;
/*     */ import com.linlongyx.sanguo.webgame.rmi.data.PlayerData;
/*     */ import java.io.IOException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.stream.Collectors;
/*     */ 
/*     */ public class TowerUtil {
/*  23 */   private static Map<Integer, TowerZone> towerZoneMap = new HashMap<>();
/*  24 */   private static String tower_key = "cross_tower";
/*     */   
/*     */   private static void check() {
/*  27 */     if (towerZoneMap.isEmpty()) {
/*  28 */       reZone();
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public static void balance() {
/*  34 */     HashMap<Integer, HashMap<Integer, Long>> serverRewardMap = new HashMap<>();
/*  35 */     for (TowerZone towerZone : towerZoneMap.values()) {
/*  36 */       for (TowerLayerData data : towerZone.getLayerMap().values()) {
/*  37 */         if (data.getOwnerId() > 0L) {
/*  38 */           if (towerZone.getPlayerMap().containsKey(Long.valueOf(data.getOwnerId()))) {
/*  39 */             int serverId = ((PlayerData)towerZone.getPlayerMap().get(Long.valueOf(data.getOwnerId()))).getServerId();
/*  40 */             serverRewardMap.putIfAbsent(Integer.valueOf(serverId), new HashMap<>());
/*  41 */             ((HashMap<Integer, Long>)serverRewardMap.get(Integer.valueOf(serverId))).put(Integer.valueOf(data.getLayerId()), Long.valueOf(data.getOwnerId()));
/*  42 */             LogUtil.errorLog(new Object[] { "tower balance rank: ", Integer.valueOf(towerZone.getZoneId()), Integer.valueOf(serverId), Long.valueOf(data.getOwnerId()), Integer.valueOf(data.getLayerId()) });
/*     */           } 
/*     */           
/*  45 */           towerZone.resetLayerData(data);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/*  51 */     for (Map.Entry<Integer, HashMap<Integer, Long>> kv : serverRewardMap.entrySet()) {
/*  52 */       LogicRmiUtil.sendTowerRankReward("TowerUtil::balance", ((Integer)kv.getKey()).intValue(), kv.getValue());
/*     */     }
/*     */   }
/*     */   
/*     */   public static synchronized void reZone() {
/*  57 */     towerZoneMap.clear();
/*  58 */     TowerOwnerParameter towerOwnerParameter = (TowerOwnerParameter)ParameterConstant.getParameter(67);
/*  59 */     ArrayList<Pair<Integer, Integer>> serverWorldLevelList = LogicRmiManager.getServerWorldLevel();
/*  60 */     serverWorldLevelList.sort((o1, o2) -> Integer.compare(((Integer)o2.getValue()).intValue(), ((Integer)o1.getValue()).intValue()));
/*  61 */     int pageSize = towerOwnerParameter.getZoneSize();
/*  62 */     for (int i = 0; i < serverWorldLevelList.size() / pageSize + 1; i++) {
/*  63 */       int start = i * pageSize;
/*  64 */       int max = (i + 1) * pageSize;
/*  65 */       int end = (max > serverWorldLevelList.size()) ? serverWorldLevelList.size() : max;
/*  66 */       List<Integer> subList = (List<Integer>)serverWorldLevelList.subList(start, end).stream().map(Pair::getValue).collect(Collectors.toList());
/*  67 */       TowerZone towerZone = new TowerZone(i + 1, subList);
/*  68 */       towerZoneMap.put(Integer.valueOf(towerZone.getZoneId()), towerZone);
/*     */     } 
/*     */   }
/*     */   
/*     */   public static TowerZone getZoneByServerId(int serverId) {
/*  73 */     for (TowerZone towerZone : towerZoneMap.values()) {
/*  74 */       if (towerZone.getServerSet().contains(Integer.valueOf(serverId))) {
/*  75 */         return towerZone;
/*     */       }
/*     */     } 
/*  78 */     return null;
/*     */   }
/*     */   
/*     */   public static Map<Integer, TowerZone> getTowerZoneMap() {
/*  82 */     return Collections.unmodifiableMap(towerZoneMap);
/*     */   }
/*     */   
/*     */   public static List<RankingData> getTowerData(PlayerData playerData, int curTowerId) {
/*  86 */     check();
/*  87 */     TowerOwnerParameter towerOwnerParameter = (TowerOwnerParameter)ParameterConstant.getParameter(67);
/*  88 */     TowerZone towerZone = getZoneByServerId(playerData.getServerId());
/*  89 */     if (towerZone == null) {
/*  90 */       towerZone = addServer(playerData.getServerId());
/*     */     }
/*  92 */     towerZone.updatePlayer(playerData);
/*     */ 
/*     */     
/*  95 */     for (TowerZone t : towerZoneMap.values()) {
/*  96 */       if (t.getZoneId() != towerZone.getZoneId() && t.getServerSet().contains(Integer.valueOf(playerData.getServerId()))) {
/*  97 */         t.removePlayer(playerData.getPlayerId());
/*     */       }
/*     */     } 
/* 100 */     return towerZone.getTowerData(playerData.getPlayerId(), curTowerId, towerOwnerParameter.getShowLimit());
/*     */   }
/*     */   
/*     */   private static synchronized TowerZone addServer(int serverId) {
/* 104 */     TowerOwnerParameter towerOwnerParameter = (TowerOwnerParameter)ParameterConstant.getParameter(67);
/* 105 */     for (TowerZone towerZone1 : towerZoneMap.values()) {
/* 106 */       if (towerZone1.getServerSet().size() < towerOwnerParameter.getZoneSize()) {
/* 107 */         towerZone1.getServerSet().add(Integer.valueOf(serverId));
/* 108 */         return towerZone1;
/*     */       } 
/*     */     } 
/* 111 */     TowerZone towerZone = new TowerZone(towerZoneMap.size() + 1);
/* 112 */     towerZone.getServerSet().add(Integer.valueOf(serverId));
/* 113 */     towerZoneMap.put(Integer.valueOf(towerZone.getZoneId()), towerZone);
/* 114 */     return towerZone;
/*     */   }
/*     */   
/*     */   public static Pair<RankingData, Pair<Integer, PlayerData>> fightTowerLayer(int serverId, long playerId, int layerId) {
/* 118 */     TowerZone towerZone = getZoneByServerId(serverId);
/* 119 */     if (towerZone != null) {
/* 120 */       return towerZone.fightTowerLayer(playerId, layerId);
/*     */     }
/* 122 */     return new Pair(null, new Pair(Integer.valueOf(0), null));
/*     */   }
/*     */   
/*     */   public static Pair<Integer, RankingData> towerLayerState(int serverId, long playerId, int layerId) {
/* 126 */     TowerZone towerZone = getZoneByServerId(serverId);
/* 127 */     if (towerZone != null) {
/* 128 */       return towerZone.towerLayerState(playerId, layerId);
/*     */     }
/* 130 */     return new Pair(Integer.valueOf(0), null);
/*     */   }
/*     */   
/*     */   public static Pair<Integer, ArrayList<RankingData>> getRankList(int serverId, long playerId) {
/* 134 */     TowerOwnerParameter towerOwnerParameter = (TowerOwnerParameter)ParameterConstant.getParameter(67);
/* 135 */     TowerZone towerZone = getZoneByServerId(serverId);
/* 136 */     if (towerZone == null) {
/* 137 */       return new Pair(Integer.valueOf(0), null);
/*     */     }
/* 139 */     return towerZone.getRankList(playerId, towerOwnerParameter.getRankLimit());
/*     */   }
/*     */   
/*     */   public static boolean fightTowerResult(int serverId, long playerId, long targetPlayerId, int layerId, boolean win) {
/* 143 */     TowerZone towerZone = getZoneByServerId(serverId);
/* 144 */     if (towerZone == null) {
/* 145 */       return false;
/*     */     }
/* 147 */     return towerZone.fightResult(playerId, targetPlayerId, layerId, win);
/*     */   }
/*     */   
/*     */   public static void addTowerDefRecord(int serverId, int layerId, long playerId, int type, int time, boolean win, long pkPlayerId, String pkPlayerName) {
/* 151 */     TowerZone towerZone = getZoneByServerId(serverId);
/* 152 */     if (towerZone == null || !towerZone.getPlayerMap().containsKey(Long.valueOf(playerId))) {
/*     */       return;
/*     */     }
/* 155 */     int targetServer = ((PlayerData)towerZone.getPlayerMap().get(Long.valueOf(playerId))).getServerId();
/* 156 */     LogicRmiUtil.addTowerDefRecord("TowerUtil::addTowerDefRecord", targetServer, layerId, playerId, type, time, win, pkPlayerId, pkPlayerName);
/*     */   }
/*     */   
/*     */   public static boolean giveupTowerLayer(int serverId, long playerId, int layerId) {
/* 160 */     TowerZone towerZone = getZoneByServerId(serverId);
/* 161 */     if (towerZone == null || !towerZone.getPlayerMap().containsKey(Long.valueOf(playerId))) {
/* 162 */       return true;
/*     */     }
/* 164 */     return towerZone.giveupTowerLayer(playerId, layerId);
/*     */   }
/*     */   
/*     */   public static void saveToDb() {
/* 168 */     if (!towerZoneMap.isEmpty()) {
/*     */       try {
/* 170 */         RedisClientTemplate redisClientTemplate = (RedisClientTemplate)MContext.getBean("redisClientTemplate", RedisClientTemplate.class);
/* 171 */         redisClientTemplate.set(tower_key, CrossUtil.objectMapper.writeValueAsString(towerZoneMap));
/* 172 */       } catch (JsonProcessingException e) {
/* 173 */         e.printStackTrace();
/* 174 */         LogUtil.errorLog(new Object[] { Arrays.toString((Object[])e.getStackTrace()) });
/*     */       } 
/* 176 */       LogUtil.errorLog(new Object[] { "save towerMap", tower_key });
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void loadFromDb() {
/* 181 */     RedisClientTemplate redisClientTemplate = (RedisClientTemplate)MContext.getBean("redisClientTemplate", RedisClientTemplate.class);
/*     */     
/* 183 */     if (!redisClientTemplate.exists(tower_key).booleanValue()) {
/* 184 */       LogUtil.errorLog(new Object[] { "no towerMap with key ", tower_key, "in redis" });
/*     */       return;
/*     */     } 
/* 187 */     String towerMapJson = redisClientTemplate.get(tower_key);
/* 188 */     if (towerMapJson == null) {
/* 189 */       LogUtil.errorLog(new Object[] { "load towerMap with key ", tower_key, "from redis error" });
/*     */       return;
/*     */     } 
/*     */     try {
/* 193 */       towerZoneMap = (Map<Integer, TowerZone>)CrossUtil.objectMapper.readValue(towerMapJson, new TypeReference<HashMap<Integer, TowerZone>>() {  });
/* 194 */     } catch (IOException e) {
/* 195 */       e.printStackTrace();
/* 196 */       LogUtil.errorLog(new Object[] { "load towerMap ", tower_key, "from redis error", e.getMessage() });
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\sanguo\webgame\rmi\tower\TowerUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */