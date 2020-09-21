/*     */ package com.linlongyx.sanguo.webgame.app.singleIns;
/*     */ 
/*     */ import com.linlongyx.core.framework.dao.annotation.Table;
/*     */ import com.linlongyx.core.framework.dao.annotation.TableField;
/*     */ import com.linlongyx.core.framework.dao.entity.IEntity;
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import org.apache.commons.collections.map.HashedMap;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @Table(tableName = "tb_single_ins", prefix = "single_ins_%serverId_%playerId", keyField = "playerId", isPlayerIdKey = true)
/*     */ public class SingleInsEntity
/*     */   implements IEntity
/*     */ {
/*     */   @TableField(isKey = true)
/*     */   private final transient long playerId;
/*  21 */   private Map<Integer, Integer> times = new HashMap<>();
/*  22 */   private Map<Integer, Integer> maxPoints = new HashMap<>();
/*  23 */   private Set<Integer> clears = new HashSet<>();
/*     */   private int totalTime;
/*  25 */   private Map<Integer, Integer> bossMap = (Map<Integer, Integer>)new HashedMap();
/*  26 */   private Map<Integer, Integer> timesMap = (Map<Integer, Integer>)new HashedMap();
/*  27 */   private Map<Integer, Integer> buyTimeMap = (Map<Integer, Integer>)new HashedMap();
/*  28 */   private Map<Integer, Integer> totalChallengeMap = (Map<Integer, Integer>)new HashedMap();
/*  29 */   private Map<Integer, Integer> vipSweepTimes = new HashMap<>();
/*     */   
/*  31 */   private Map<Integer, Integer> kfHandbook = new HashMap<>();
/*  32 */   private Map<Integer, Integer> stageHandbook = new HashMap<>();
/*  33 */   private Map<Integer, Integer> zfHandbook = new HashMap<>();
/*     */   
/*  35 */   private Map<Integer, Map<Integer, Integer>> actPlayTime = new HashMap<>();
/*     */   
/*     */   public SingleInsEntity(long playerId) {
/*  38 */     this.playerId = playerId;
/*     */   }
/*     */   
/*     */   public long getPlayerId() {
/*  42 */     return this.playerId;
/*     */   }
/*     */   
/*     */   public Map<Integer, Integer> getTimes() {
/*  46 */     return this.times;
/*     */   }
/*     */   
/*     */   public void setTimes(Map<Integer, Integer> times) {
/*  50 */     this.times = times;
/*     */   }
/*     */   
/*     */   public Map<Integer, Integer> getMaxPoints() {
/*  54 */     return this.maxPoints;
/*     */   }
/*     */   
/*     */   public void setMaxPoints(Map<Integer, Integer> maxPoints) {
/*  58 */     this.maxPoints = maxPoints;
/*     */   }
/*     */   
/*     */   public Set<Integer> getClears() {
/*  62 */     return this.clears;
/*     */   }
/*     */   
/*     */   public void setClears(Set<Integer> clears) {
/*  66 */     this.clears = clears;
/*     */   }
/*     */   
/*     */   public int getTotalTime() {
/*  70 */     return this.totalTime;
/*     */   }
/*     */   
/*     */   public void setTotalTime(int totalTime) {
/*  74 */     this.totalTime = totalTime;
/*     */   }
/*     */   
/*     */   public Map<Integer, Integer> getBossMap() {
/*  78 */     return this.bossMap;
/*     */   }
/*     */   
/*     */   public void setBossMap(Map<Integer, Integer> bossMap) {
/*  82 */     this.bossMap = bossMap;
/*     */   }
/*     */   
/*     */   public Map<Integer, Integer> getTimesMap() {
/*  86 */     return this.timesMap;
/*     */   }
/*     */   
/*     */   public void setTimesMap(Map<Integer, Integer> timesMap) {
/*  90 */     this.timesMap = timesMap;
/*     */   }
/*     */   
/*     */   public Map<Integer, Integer> getBuyTimeMap() {
/*  94 */     return this.buyTimeMap;
/*     */   }
/*     */   
/*     */   public void setBuyTimeMap(Map<Integer, Integer> buyTimeMap) {
/*  98 */     this.buyTimeMap = buyTimeMap;
/*     */   }
/*     */   
/*     */   public Map<Integer, Integer> getTotalChallengeMap() {
/* 102 */     return this.totalChallengeMap;
/*     */   }
/*     */   
/*     */   public void setTotalChallengeMap(Map<Integer, Integer> totalChallengeMap) {
/* 106 */     this.totalChallengeMap = totalChallengeMap;
/*     */   }
/*     */   
/*     */   public Map<Integer, Integer> getVipSweepTimes() {
/* 110 */     return this.vipSweepTimes;
/*     */   }
/*     */   
/*     */   public void setVipSweepTimes(Map<Integer, Integer> vipSweepTimes) {
/* 114 */     this.vipSweepTimes = vipSweepTimes;
/*     */   }
/*     */ 
/*     */   
/*     */   public Map<Integer, Integer> getKfHandbook() {
/* 119 */     return this.kfHandbook;
/*     */   }
/*     */   
/*     */   public void setKfHandbook(Map<Integer, Integer> kfHandbook) {
/* 123 */     this.kfHandbook = kfHandbook;
/*     */   }
/*     */   
/*     */   public Map<Integer, Integer> getStageHandbook() {
/* 127 */     return this.stageHandbook;
/*     */   }
/*     */   
/*     */   public void setStageHandbook(Map<Integer, Integer> stageHandbook) {
/* 131 */     this.stageHandbook = stageHandbook;
/*     */   }
/*     */   
/*     */   public Map<Integer, Integer> getZfHandbook() {
/* 135 */     return this.zfHandbook;
/*     */   }
/*     */   
/*     */   public void setZfHandbook(Map<Integer, Integer> zfHandbook) {
/* 139 */     this.zfHandbook = zfHandbook;
/*     */   }
/*     */   
/*     */   public Map<Integer, Map<Integer, Integer>> getActPlayTime() {
/* 143 */     return this.actPlayTime;
/*     */   }
/*     */   
/*     */   public void setActPlayTime(Map<Integer, Map<Integer, Integer>> actPlayTime) {
/* 147 */     this.actPlayTime = actPlayTime;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 153 */     return "SingleInsEntity{playerId=" + this.playerId + ", times=" + this.times + ", maxPoints=" + this.maxPoints + ", clears=" + this.clears + ", totalTime=" + this.totalTime + ", bossMap=" + this.bossMap + ", timesMap=" + this.timesMap + ", buyTimeMap=" + this.buyTimeMap + ", totalChallengeMap=" + this.totalChallengeMap + ", vipSweepTimes=" + this.vipSweepTimes + ", kfHandbook=" + this.kfHandbook + ", stageHandbook=" + this.stageHandbook + ", zfHandbook=" + this.zfHandbook + ", actPlayTime=" + this.actPlayTime + '}';
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\app\singleIns\SingleInsEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */