/*    */ package com.linlongyx.sanguo.webgame.rmi.rank;
/*    */ 
/*    */ import com.fasterxml.jackson.core.JsonProcessingException;
/*    */ import com.fasterxml.jackson.core.type.TypeReference;
/*    */ import com.linlongyx.core.framework.dao.redis.RedisClientTemplate;
/*    */ import com.linlongyx.sanguo.webgame.constant.MContext;
/*    */ import com.linlongyx.sanguo.webgame.log.LogUtil;
/*    */ import com.linlongyx.sanguo.webgame.processors.cross.CrossUtil;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.RankingData;
/*    */ import com.linlongyx.sanguo.webgame.rmi.LogicRmiUtil;
/*    */ import java.io.IOException;
/*    */ import java.util.ArrayList;
/*    */ import java.util.Arrays;
/*    */ import java.util.Iterator;
/*    */ import java.util.Map;
/*    */ import java.util.concurrent.ConcurrentHashMap;
/*    */ 
/*    */ public class RankActUtil
/*    */ {
/* 20 */   private static String CROSS_RANK_KEY = "cross_rank";
/* 21 */   private static Map<Integer, RankDataCache> rankCacheMap = new ConcurrentHashMap<>();
/*    */   
/*    */   public static void addRankServer(int actId, int rankType, int limit, boolean isDesc, int condition, int serverId) {
/* 24 */     rankCacheMap.putIfAbsent(Integer.valueOf(actId), new RankDataCache(actId, rankType, limit, isDesc, condition));
/* 25 */     ((RankDataCache)rankCacheMap.get(Integer.valueOf(actId))).setCondition(condition);
/* 26 */     ((RankDataCache)rankCacheMap.get(Integer.valueOf(actId))).setDesc(isDesc);
/* 27 */     ((RankDataCache)rankCacheMap.get(Integer.valueOf(actId))).addServer(serverId);
/*    */   }
/*    */   
/*    */   public static void updatePlayerRankData(int actId, int rankType, int limit, boolean isDesc, int condition, int serverId, RankingData data) {
/* 31 */     rankCacheMap.putIfAbsent(Integer.valueOf(actId), new RankDataCache(actId, rankType, limit, isDesc, condition));
/* 32 */     RankDataCache rankDataCache = rankCacheMap.get(Integer.valueOf(actId));
/* 33 */     if (rankDataCache.getCondition() != condition) {
/* 34 */       rankDataCache.setCondition(condition);
/*    */     }
/* 36 */     if (rankDataCache.getDesc() != isDesc) {
/* 37 */       rankDataCache.setDesc(isDesc);
/*    */     }
/* 39 */     if (rankDataCache.getRankType() != rankType) {
/* 40 */       rankDataCache.setRankType(rankType);
/*    */     }
/* 42 */     ((RankDataCache)rankCacheMap.get(Integer.valueOf(actId))).updatePlayerRankData(serverId, data);
/*    */   }
/*    */   
/*    */   public static ArrayList<RankingData> getActRankList(int actId, boolean isClose) {
/* 46 */     if (rankCacheMap.containsKey(Integer.valueOf(actId))) {
/* 47 */       return ((RankDataCache)rankCacheMap.get(Integer.valueOf(actId))).getActRankList(isClose);
/*    */     }
/* 49 */     return new ArrayList<>();
/*    */   }
/*    */   
/*    */   public static void pushRankData() {
/* 53 */     for (RankDataCache rankDataCache : rankCacheMap.values()) {
/* 54 */       if (rankDataCache.getRankState() == RankDataCache.RankState.CLOSE && !rankDataCache.getNeedPush())
/* 55 */         continue;  if (rankDataCache.getRankState() == RankDataCache.RankState.CLOSE) {
/* 56 */         rankDataCache.setNeedPush(false);
/*    */       }
/* 58 */       ArrayList<RankingData> rankList = rankDataCache.getActRankList(false);
/* 59 */       for (Iterator<Integer> iterator = rankDataCache.getServerList().iterator(); iterator.hasNext(); ) { int serverId = ((Integer)iterator.next()).intValue();
/* 60 */         LogicRmiUtil.pushRankList("RankActUtil::pushRankData", serverId, rankDataCache.getActId(), rankList); }
/*    */     
/*    */     } 
/*    */   }
/*    */   
/*    */   public static void saveDataToDb() {
/*    */     try {
/* 67 */       RedisClientTemplate redisClientTemplate = (RedisClientTemplate)MContext.getBean("redisClientTemplate", RedisClientTemplate.class);
/* 68 */       redisClientTemplate.set(CROSS_RANK_KEY, CrossUtil.objectMapper.writeValueAsString(rankCacheMap));
/* 69 */     } catch (JsonProcessingException e) {
/* 70 */       e.printStackTrace();
/* 71 */       LogUtil.errorLog(new Object[] { Arrays.toString((Object[])e.getStackTrace()) });
/*    */     } 
/*    */   }
/*    */   
/*    */   public static void loadDataFromDb() {
/* 76 */     RedisClientTemplate redisClientTemplate = (RedisClientTemplate)MContext.getBean("redisClientTemplate", RedisClientTemplate.class);
/* 77 */     if (!redisClientTemplate.exists(CROSS_RANK_KEY).booleanValue()) {
/* 78 */       LogUtil.errorLog(new Object[] { "load cross rank data error: cross rank first open or last save error" });
/*    */       return;
/*    */     } 
/* 81 */     String battleMapJson = redisClientTemplate.get(CROSS_RANK_KEY);
/* 82 */     if (battleMapJson == null) {
/* 83 */       LogUtil.errorLog(new Object[] { "load cross rank data with key ", CROSS_RANK_KEY, "from reids error" });
/*    */       return;
/*    */     } 
/*    */     try {
/* 87 */       rankCacheMap = (Map<Integer, RankDataCache>)CrossUtil.objectMapper.readValue(battleMapJson, new TypeReference<ConcurrentHashMap<Integer, RankDataCache>>() {  });
/* 88 */     } catch (IOException e) {
/* 89 */       e.printStackTrace();
/* 90 */       LogUtil.errorLog(new Object[] { "deserialize cross rank data ", CROSS_RANK_KEY, "error", Arrays.toString((Object[])e.getStackTrace()) });
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\sanguo\webgame\rmi\rank\RankActUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */