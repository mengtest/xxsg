/*     */ package com.linlongyx.sanguo.webgame.rmi.battle;
/*     */ 
/*     */ import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
/*     */ import java.io.Serializable;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import java.util.concurrent.ConcurrentSkipListSet;
/*     */ import java.util.concurrent.atomic.AtomicInteger;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @JsonIgnoreProperties(ignoreUnknown = true)
/*     */ public class BattleCamp
/*     */   implements Serializable
/*     */ {
/*     */   private String battleKey;
/*     */   private String campKey;
/*     */   private int campIndex;
/*     */   private Battle.CampTag tag;
/*     */   private int[] bornPoint;
/*     */   private int resourceId;
/*     */   private int campPoint;
/*     */   private int limit;
/*  26 */   private AtomicInteger count = new AtomicInteger(0);
/*     */   
/*  28 */   private Map<Integer, Set<Long>> serverPlayers = new HashMap<>();
/*     */ 
/*     */   
/*     */   public BattleCamp() {}
/*     */   
/*     */   public BattleCamp(String battleKey, int campIndex, Battle.CampTag tag) {
/*  34 */     this.battleKey = battleKey;
/*  35 */     this.campIndex = campIndex;
/*  36 */     this.campKey = battleKey + "_" + campIndex;
/*  37 */     this.tag = tag;
/*     */   }
/*     */   
/*     */   public boolean addPlayer(int serverId, long playerId) {
/*  41 */     if (!this.serverPlayers.containsKey(Integer.valueOf(serverId)) && this.serverPlayers.size() >= 3)
/*  42 */       return false; 
/*  43 */     if (this.count.get() >= this.limit) {
/*  44 */       return false;
/*     */     }
/*  46 */     this.serverPlayers.putIfAbsent(Integer.valueOf(serverId), new ConcurrentSkipListSet<>());
/*  47 */     if (!((Set)this.serverPlayers.get(Integer.valueOf(serverId))).contains(Long.valueOf(playerId))) {
/*  48 */       this.count.incrementAndGet();
/*  49 */       ((Set<Long>)this.serverPlayers.get(Integer.valueOf(serverId))).add(Long.valueOf(playerId));
/*     */     } 
/*  51 */     return true;
/*     */   }
/*     */   
/*     */   public boolean removePlayer(long playerId) {
/*  55 */     for (Set<Long> value : this.serverPlayers.values()) {
/*  56 */       if (value.contains(Long.valueOf(playerId))) {
/*  57 */         value.remove(Long.valueOf(playerId));
/*  58 */         this.count.decrementAndGet();
/*     */       } 
/*     */     } 
/*  61 */     return true;
/*     */   }
/*     */   
/*     */   public boolean existPlayer(long playerId) {
/*  65 */     boolean exist = false;
/*  66 */     for (Set<Long> value : this.serverPlayers.values()) {
/*  67 */       if (value.contains(Long.valueOf(playerId))) {
/*  68 */         exist = true; break;
/*     */       } 
/*     */     } 
/*  71 */     return exist;
/*     */   }
/*     */   
/*     */   public boolean containsServer(int serverId) {
/*  75 */     return this.serverPlayers.containsKey(Integer.valueOf(serverId));
/*     */   }
/*     */   
/*     */   public String getCampKey() {
/*  79 */     return this.campKey;
/*     */   }
/*     */   
/*     */   public void setCampKey(String campKey) {
/*  83 */     this.campKey = campKey;
/*     */   }
/*     */   
/*     */   public Battle.CampTag getTag() {
/*  87 */     return this.tag;
/*     */   }
/*     */   
/*     */   public void setTag(Battle.CampTag tag) {
/*  91 */     this.tag = tag;
/*     */   }
/*     */   
/*     */   public int[] getBornPoint() {
/*  95 */     return this.bornPoint;
/*     */   }
/*     */   
/*     */   public void setBornPoint(int[] bornPoint) {
/*  99 */     this.bornPoint = bornPoint;
/*     */   }
/*     */   
/*     */   public Map<Integer, Set<Long>> getServerPlayers() {
/* 103 */     return this.serverPlayers;
/*     */   }
/*     */   
/*     */   public void setServerPlayers(Map<Integer, Set<Long>> serverPlayers) {
/* 107 */     this.serverPlayers = serverPlayers;
/*     */   }
/*     */   
/*     */   public int getLimit() {
/* 111 */     return this.limit;
/*     */   }
/*     */   
/*     */   public void setLimit(int limit) {
/* 115 */     this.limit = limit;
/*     */   }
/*     */   
/*     */   public AtomicInteger getCount() {
/* 119 */     return this.count;
/*     */   }
/*     */   
/*     */   public void setCount(AtomicInteger count) {
/* 123 */     this.count = count;
/*     */   }
/*     */   
/*     */   public String getBattleKey() {
/* 127 */     return this.battleKey;
/*     */   }
/*     */   
/*     */   public void setBattleKey(String battleKey) {
/* 131 */     this.battleKey = battleKey;
/*     */   }
/*     */   
/*     */   public int getCampIndex() {
/* 135 */     return this.campIndex;
/*     */   }
/*     */   
/*     */   public void setCampIndex(int campIndex) {
/* 139 */     this.campIndex = campIndex;
/*     */   }
/*     */   
/*     */   public int getResourceId() {
/* 143 */     return this.resourceId;
/*     */   }
/*     */   
/*     */   public void setResourceId(int resourceId) {
/* 147 */     this.resourceId = resourceId;
/*     */   }
/*     */   
/*     */   public int getCampPoint() {
/* 151 */     return this.campPoint;
/*     */   }
/*     */   
/*     */   public void setCampPoint(int campPoint) {
/* 155 */     this.campPoint = campPoint;
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\sanguo\webgame\rmi\battle\BattleCamp.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */