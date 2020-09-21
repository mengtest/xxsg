/*    */ package com.linlongyx.sanguo.webgame.rmi.destiny;
/*    */ 
/*    */ import com.fasterxml.jackson.annotation.JsonIgnore;
/*    */ import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.DestinyRankData;
/*    */ import com.linlongyx.sanguo.webgame.rmi.data.BetData;
/*    */ import java.util.ArrayList;
/*    */ import java.util.HashMap;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ import java.util.concurrent.ConcurrentHashMap;
/*    */ 
/*    */ 
/*    */ @JsonIgnoreProperties(ignoreUnknown = true)
/*    */ public class MatchCache
/*    */ {
/* 17 */   private Map<Integer, RewardCache> serverCache = new ConcurrentHashMap<>();
/*    */ 
/*    */   
/*    */   @JsonIgnore
/*    */   public RewardCache getRewardCache(int serverId) {
/* 22 */     this.serverCache.putIfAbsent(Integer.valueOf(serverId), new RewardCache(serverId));
/* 23 */     return this.serverCache.get(Integer.valueOf(serverId));
/*    */   }
/*    */ 
/*    */   
/*    */   public Map<Integer, RewardCache> getServerCache() {
/* 28 */     return this.serverCache;
/*    */   }
/*    */   
/*    */   public void setServerCache(Map<Integer, RewardCache> serverCache) {
/* 32 */     this.serverCache = serverCache;
/*    */   }
/*    */   
/*    */   public static class RewardCache {
/*    */     private int serverId;
/* 37 */     private HashMap<Long, Integer> matchRewardCache = new HashMap<>();
/* 38 */     private Map<String, List<BetData>> betDataCache = new HashMap<>();
/* 39 */     private List<DestinyRankData> destinyTopRewardCache = new ArrayList<>();
/*    */     
/*    */     public RewardCache() {}
/*    */     
/*    */     public RewardCache(int serverId) {
/* 44 */       this.serverId = serverId;
/*    */     }
/*    */     
/*    */     public int getServerId() {
/* 48 */       return this.serverId;
/*    */     }
/*    */     
/*    */     public void setServerId(int serverId) {
/* 52 */       this.serverId = serverId;
/*    */     }
/*    */     
/*    */     public HashMap<Long, Integer> getMatchRewardCache() {
/* 56 */       return this.matchRewardCache;
/*    */     }
/*    */     
/*    */     public void setMatchRewardCache(HashMap<Long, Integer> matchRewardCache) {
/* 60 */       this.matchRewardCache = matchRewardCache;
/*    */     }
/*    */     
/*    */     public Map<String, List<BetData>> getBetDataCache() {
/* 64 */       return this.betDataCache;
/*    */     }
/*    */     
/*    */     public void setBetDataCache(Map<String, List<BetData>> betDataCache) {
/* 68 */       this.betDataCache = betDataCache;
/*    */     }
/*    */     
/*    */     public List<DestinyRankData> getDestinyTopRewardCache() {
/* 72 */       return this.destinyTopRewardCache;
/*    */     }
/*    */     
/*    */     public void setDestinyTopRewardCache(List<DestinyRankData> destinyTopRewardCache) {
/* 76 */       this.destinyTopRewardCache = destinyTopRewardCache;
/*    */     }
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\sanguo\webgame\rmi\destiny\MatchCache.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */