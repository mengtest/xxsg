/*     */ package com.linlongyx.sanguo.webgame.rmi.race;
/*     */ import com.linlongyx.sanguo.webgame.log.LogUtil;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.RankingData;
/*     */ import com.linlongyx.sanguo.webgame.rmi.LogicRmiUtil;
/*     */ import com.linlongyx.sanguo.webgame.rmi.data.RacePlayerData;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Comparator;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ @JsonIgnoreProperties(ignoreUnknown = true)
/*     */ public class RaceZone {
/*  14 */   private int limit = 100; private int zoneId;
/*  15 */   private Map<Integer, Map<Long, RacePlayerData>> serverToPlayerMap = new HashMap<>();
/*     */   
/*  17 */   private ArrayList<RankingData> cacheRankList = new ArrayList<>();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public RaceZone(int zoneId) {
/*  23 */     this.zoneId = zoneId;
/*     */   }
/*     */   
/*     */   public void addServer(int serverId) {
/*  27 */     this.serverToPlayerMap.putIfAbsent(Integer.valueOf(serverId), new HashMap<>());
/*     */   }
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public void balance() {
/*  33 */     ArrayList<RankingData> rankList = rank();
/*  34 */     for (RankingData data : rankList) {
/*  35 */       int serverId = selectServer(data.playerId);
/*  36 */       if (serverId == -1) {
/*  37 */         LogUtil.errorLog(new Object[] { "player not exist in this race zone", Long.valueOf(data.playerId), Long.valueOf(data.value) }); continue;
/*     */       } 
/*  39 */       boolean success = LogicRmiUtil.sendCrossRaceReward("RaceZone::balance", serverId, data.playerId, (int)data.value, data.rank);
/*  40 */       if (!success)
/*     */       {
/*  42 */         LogUtil.errorLog(new Object[] { "send race rank reward error", data });
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/*  47 */     this.cacheRankList = rankList;
/*     */   }
/*     */   
/*     */   public ArrayList<RankingData> rank() {
/*  51 */     if (!this.cacheRankList.isEmpty()) {
/*  52 */       return this.cacheRankList;
/*     */     }
/*  54 */     List<RacePlayerData> playerList = new ArrayList<>();
/*  55 */     getServerToPlayerMap().values().forEach(sp -> sp.values().stream().filter(()).limit(this.limit).forEach(playerList::add));
/*     */ 
/*     */     
/*  58 */     ArrayList<RankingData> resultList = new ArrayList<>();
/*  59 */     int[] rank = { 0 };
/*  60 */     playerList.stream().sorted(Comparator.comparingInt(RacePlayerData::getRacePoint).reversed().thenComparing(RacePlayerData::getUpdateTime)).limit(this.limit).forEach(p -> {
/*     */           rank[0] = rank[0] + 1;
/*     */           RankingData rankingData = RaceUtil.transform(p);
/*     */           rankingData.rank = rank[0];
/*     */           resultList.add(rankingData);
/*     */         });
/*  66 */     return resultList;
/*     */   }
/*     */   
/*     */   public int selectServer(long playerId) {
/*  70 */     for (Map.Entry<Integer, Map<Long, RacePlayerData>> kv : this.serverToPlayerMap.entrySet()) {
/*  71 */       if (((Map)kv.getValue()).containsKey(Long.valueOf(playerId))) {
/*  72 */         return ((Integer)kv.getKey()).intValue();
/*     */       }
/*     */     } 
/*  75 */     return -1;
/*     */   }
/*     */   
/*     */   public int getZoneId() {
/*  79 */     return this.zoneId;
/*     */   }
/*     */   
/*     */   public void setZoneId(int zoneId) {
/*  83 */     this.zoneId = zoneId;
/*     */   }
/*     */   
/*     */   public int getLimit() {
/*  87 */     return this.limit;
/*     */   }
/*     */   
/*     */   public void setLimit(int limit) {
/*  91 */     this.limit = limit;
/*     */   }
/*     */   
/*     */   public Map<Integer, Map<Long, RacePlayerData>> getServerToPlayerMap() {
/*  95 */     return this.serverToPlayerMap;
/*     */   }
/*     */   
/*     */   public void setServerToPlayerMap(Map<Integer, Map<Long, RacePlayerData>> serverToPlayerMap) {
/*  99 */     this.serverToPlayerMap = serverToPlayerMap;
/*     */   }
/*     */   
/*     */   public ArrayList<RankingData> getCacheRankList() {
/* 103 */     return this.cacheRankList;
/*     */   }
/*     */   
/*     */   public void setCacheRankList(ArrayList<RankingData> cacheRankList) {
/* 107 */     this.cacheRankList = cacheRankList;
/*     */   }
/*     */   
/*     */   public RaceZone() {}
/*     */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\sanguo\webgame\rmi\race\RaceZone.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */