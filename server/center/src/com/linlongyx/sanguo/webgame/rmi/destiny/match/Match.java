/*     */ package com.linlongyx.sanguo.webgame.rmi.destiny.match;
/*     */ 
/*     */ import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
/*     */ import com.fasterxml.jackson.core.JsonProcessingException;
/*     */ import com.linlongyx.core.framework.dao.redis.RedisClientTemplate;
/*     */ import com.linlongyx.core.utils.TimeUtil;
/*     */ import com.linlongyx.sanguo.webgame.constant.MContext;
/*     */ import com.linlongyx.sanguo.webgame.log.LogUtil;
/*     */ import com.linlongyx.sanguo.webgame.processors.cross.CrossUtil;
/*     */ import com.linlongyx.sanguo.webgame.rmi.data.MatchState;
/*     */ import java.io.Serializable;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @JsonIgnoreProperties(ignoreUnknown = true)
/*     */ public class Match
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   public static final int GROUP_SIZE = 8;
/*     */   public static final int PLAYER_SIZE = 32;
/*     */   private int matchId;
/*     */   private int date;
/*     */   private volatile MatchState matchState;
/*  28 */   private Map<Integer, Integer> serverIdToZoneId = new HashMap<>();
/*     */   
/*  30 */   private Map<Integer, Zone> zoneDataMap = new HashMap<>();
/*     */ 
/*     */ 
/*     */   
/*     */   public Match() {
/*  35 */     this(TimeUtil.getCurrentYearMonthDay());
/*  36 */     this.date = TimeUtil.currentTime();
/*     */   }
/*     */   
/*     */   public Match(int matchId) {
/*  40 */     this.matchId = matchId;
/*  41 */     this.date = TimeUtil.currentTime();
/*  42 */     this.matchState = MatchState.begin;
/*     */   }
/*     */   
/*     */   public void init() {
/*  46 */     for (Zone zone : this.zoneDataMap.values()) {
/*  47 */       zone.init();
/*     */     }
/*     */   }
/*     */   
/*     */   public void balanceRank() {
/*  52 */     for (Zone zone : getZoneDataMap().values()) {
/*  53 */       zone.balanceRank();
/*     */     }
/*     */   }
/*     */   
/*     */   public void saveToDb() {
/*     */     try {
/*  59 */       RedisClientTemplate redisClientTemplate = (RedisClientTemplate)MContext.getBean("redisClientTemplate", RedisClientTemplate.class);
/*     */       
/*  61 */       redisClientTemplate.set(MatchUtil.intToBytes(this.matchId), CrossUtil.objectMapper.writeValueAsBytes(this));
/*  62 */     } catch (JsonProcessingException e) {
/*  63 */       e.printStackTrace();
/*  64 */       LogUtil.errorLog(new Object[] { e.getMessage() });
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasMatch(int zoneId, long playerId) {
/*  70 */     if (!this.zoneDataMap.containsKey(Integer.valueOf(zoneId))) return false; 
/*  71 */     Zone zone = this.zoneDataMap.get(Integer.valueOf(zoneId));
/*  72 */     return zone.getCurMatchPlayers().contains(Long.valueOf(playerId));
/*     */   }
/*     */   
/*     */   public void changeState(MatchState matchState) {
/*  76 */     this.matchState = matchState;
/*     */   }
/*     */   
/*     */   public int getMatchId() {
/*  80 */     return this.matchId;
/*     */   }
/*     */   
/*     */   public void setMatchId(int matchId) {
/*  84 */     this.matchId = matchId;
/*     */   }
/*     */   
/*     */   public int getDate() {
/*  88 */     return this.date;
/*     */   }
/*     */   
/*     */   public void setDate(int date) {
/*  92 */     this.date = date;
/*     */   }
/*     */   
/*     */   public Map<Integer, Integer> getServerIdToZoneId() {
/*  96 */     return this.serverIdToZoneId;
/*     */   }
/*     */   
/*     */   public void setServerIdToZoneId(Map<Integer, Integer> serverIdToZoneId) {
/* 100 */     this.serverIdToZoneId = serverIdToZoneId;
/*     */   }
/*     */   
/*     */   public Map<Integer, Zone> getZoneDataMap() {
/* 104 */     return this.zoneDataMap;
/*     */   }
/*     */   
/*     */   public void setZoneDataMap(Map<Integer, Zone> zoneDataMap) {
/* 108 */     this.zoneDataMap = zoneDataMap;
/*     */   }
/*     */   
/*     */   public MatchState getMatchState() {
/* 112 */     return this.matchState;
/*     */   }
/*     */   
/*     */   public void setMatchState(MatchState matchState) {
/* 116 */     this.matchState = matchState;
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\sanguo\webgame\rmi\destiny\match\Match.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */