/*     */ package com.linlongyx.sanguo.webgame.app.draw;
/*     */ 
/*     */ import com.linlongyx.core.framework.dao.annotation.Table;
/*     */ import com.linlongyx.core.framework.dao.annotation.TableField;
/*     */ import com.linlongyx.core.framework.dao.entity.IMapEntity;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.KeyValue;
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @Table(tableName = "tb_draw", prefix = "draw_%serverId_%playerId", isPlayerIdKey = true, keyField = "id")
/*     */ public class DrawCardEntity
/*     */   implements IMapEntity
/*     */ {
/*     */   @TableField(isKey = true)
/*     */   private final transient long playerId;
/*     */   @TableField(isKey = true)
/*     */   private final transient int id;
/*     */   private int round;
/*     */   private int freeTime;
/*     */   private int totalPlay;
/*     */   private int refreshCount;
/*  27 */   private Map<Integer, Integer> openCards = new HashMap<>();
/*  28 */   private Set<Integer> counts = new HashSet<>();
/*  29 */   private Map<Integer, Integer> times = new HashMap<>();
/*  30 */   private Map<Integer, KeyValue> awards = new HashMap<>();
/*  31 */   private Set<Integer> numSet = new HashSet<>();
/*     */   private int actTime;
/*     */   
/*     */   public DrawCardEntity(long playerId, int id) {
/*  35 */     this.playerId = playerId;
/*  36 */     this.id = id;
/*     */   }
/*     */   
/*     */   public long getPlayerId() {
/*  40 */     return this.playerId;
/*     */   }
/*     */   
/*     */   public int getId() {
/*  44 */     return this.id;
/*     */   }
/*     */   
/*     */   public int getRound() {
/*  48 */     return this.round;
/*     */   }
/*     */   
/*     */   public void setRound(int round) {
/*  52 */     this.round = round;
/*     */   }
/*     */   
/*     */   public int getFreeTime() {
/*  56 */     return this.freeTime;
/*     */   }
/*     */   
/*     */   public void setFreeTime(int freeTime) {
/*  60 */     this.freeTime = freeTime;
/*     */   }
/*     */   
/*     */   public int getTotalPlay() {
/*  64 */     return this.totalPlay;
/*     */   }
/*     */   
/*     */   public void setTotalPlay(int totalPlay) {
/*  68 */     this.totalPlay = totalPlay;
/*     */   }
/*     */   
/*     */   public Map<Integer, Integer> getOpenCards() {
/*  72 */     return this.openCards;
/*     */   }
/*     */   
/*     */   public void setOpenCards(Map<Integer, Integer> openCards) {
/*  76 */     this.openCards = openCards;
/*     */   }
/*     */   
/*     */   public Set<Integer> getCounts() {
/*  80 */     return this.counts;
/*     */   }
/*     */   
/*     */   public void setCounts(Set<Integer> counts) {
/*  84 */     this.counts = counts;
/*     */   }
/*     */   
/*     */   public Map<Integer, Integer> getTimes() {
/*  88 */     return this.times;
/*     */   }
/*     */   
/*     */   public void setTimes(Map<Integer, Integer> times) {
/*  92 */     this.times = times;
/*     */   }
/*     */   
/*     */   public Map<Integer, KeyValue> getAwards() {
/*  96 */     return this.awards;
/*     */   }
/*     */   
/*     */   public void setAwards(Map<Integer, KeyValue> awards) {
/* 100 */     this.awards = awards;
/*     */   }
/*     */   
/*     */   public int getRefreshCount() {
/* 104 */     return this.refreshCount;
/*     */   }
/*     */   
/*     */   public void setRefreshCount(int refreshCount) {
/* 108 */     this.refreshCount = refreshCount;
/*     */   }
/*     */   
/*     */   public Set<Integer> getNumSet() {
/* 112 */     return this.numSet;
/*     */   }
/*     */   
/*     */   public void setNumSet(Set<Integer> numSet) {
/* 116 */     this.numSet = numSet;
/*     */   }
/*     */   
/*     */   public int getActTime() {
/* 120 */     return this.actTime;
/*     */   }
/*     */   
/*     */   public void setActTime(int actTime) {
/* 124 */     this.actTime = actTime;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 129 */     return "DrawCardEntity{playerId=" + this.playerId + ", id=" + this.id + ", round=" + this.round + ", freeTime=" + this.freeTime + ", totalPlay=" + this.totalPlay + ", openCards=" + this.openCards + ", counts=" + this.counts + ", times=" + this.times + ", awards=" + this.awards + ", refreshCount=" + this.refreshCount + ", numSet=" + this.numSet + ", actTime=" + this.actTime + '}';
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
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object mapKey() {
/* 147 */     return Integer.valueOf(getId());
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\app\draw\DrawCardEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */