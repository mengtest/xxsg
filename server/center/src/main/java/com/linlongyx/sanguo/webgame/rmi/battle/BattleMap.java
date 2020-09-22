/*     */ package com.linlongyx.sanguo.webgame.rmi.battle;
/*     */ 
/*     */ import com.fasterxml.jackson.annotation.JsonIgnore;
/*     */ import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
/*     */ import com.linlongyx.core.utils.TimeUtil;
/*     */ import com.linlongyx.sanguo.webgame.common.structure.Pair;
/*     */ import java.io.Serializable;
/*     */ import java.util.Collection;
/*     */ import java.util.Collections;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.concurrent.atomic.AtomicInteger;
/*     */ import java.util.concurrent.locks.ReentrantReadWriteLock;
/*     */ 
/*     */ @JsonIgnoreProperties(ignoreUnknown = true)
/*     */ public class BattleMap
/*     */   implements Serializable
/*     */ {
/*     */   private String battleMapKey;
/*     */   private int opentime;
/*  22 */   private volatile BattleUtil.BattleState battleState = BattleUtil.BattleState.CLOSE;
/*  23 */   private AtomicInteger battleMapCounter = new AtomicInteger(0);
/*     */   
/*  25 */   private Map<String, Battle> battlesMap = new HashMap<>();
/*     */   @JsonIgnore
/*  27 */   private final ReentrantReadWriteLock battlesMapLock = new ReentrantReadWriteLock();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public BattleMap(String battleMapKey, List<Pair<Integer, Integer>> serverWorldLevelList) {
/*     */     try {
/*  35 */       this.battlesMapLock.writeLock().lock();
/*  36 */       this.battleMapKey = battleMapKey;
/*  37 */       if (serverWorldLevelList.size() > 0) {
/*  38 */         serverWorldLevelList.sort((o1, o2) -> Integer.compare(((Integer)o2.getValue()).intValue(), ((Integer)o1.getValue()).intValue()));
/*  39 */         String keyPrefix = battleMapKey + "_";
/*  40 */         int pageSize = 9;
/*  41 */         for (int i = 0; i < serverWorldLevelList.size() / pageSize + 1; i++) {
/*  42 */           int start = i * pageSize;
/*  43 */           int max = (i + 1) * pageSize;
/*  44 */           int end = (max > serverWorldLevelList.size()) ? serverWorldLevelList.size() : max;
/*  45 */           List<Pair<Integer, Integer>> subList = serverWorldLevelList.subList(start, end);
/*  46 */           Battle battle = new Battle(keyPrefix + generateBattleId(), subList);
/*  47 */           this.battlesMap.putIfAbsent(battle.getBattleKey(), battle);
/*  48 */           for (Pair<Integer, Integer> kv : subList) {
/*  49 */             ((Battle)this.battlesMap.get(battle.getBattleKey())).getServerWorldLevelMap().putIfAbsent(kv.getKey(), kv.getValue());
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } finally {
/*  54 */       this.battlesMapLock.writeLock().unlock();
/*     */     } 
/*     */   }
/*     */   public void prepare() {
/*  58 */     this.battleState = BattleUtil.BattleState.PREPARE;
/*     */   }
/*     */   
/*     */   public void open() {
/*  62 */     this.battleState = BattleUtil.BattleState.OPEN;
/*  63 */     this.opentime = TimeUtil.currentTime();
/*     */   }
/*     */   
/*     */   public boolean isOpen() {
/*  67 */     return (this.battleState == BattleUtil.BattleState.OPEN);
/*     */   }
/*     */   
/*     */   public void shutdown() {
/*  71 */     if (getBattleState() != BattleUtil.BattleState.CLOSE) {
/*  72 */       for (Battle battle : getBattlesMap().values()) {
/*  73 */         battle.shutdown();
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public void close() {
/*  79 */     if (getBattleState() == BattleUtil.BattleState.OPEN) {
/*  80 */       for (Battle battle : getBattlesMap().values()) {
/*  81 */         battle.shutdown();
/*     */       }
/*     */     }
/*  84 */     this.battleState = BattleUtil.BattleState.CLOSE;
/*     */   }
/*     */   
/*     */   public int generateBattleId() {
/*  88 */     return this.battleMapCounter.getAndIncrement();
/*     */   }
/*     */   
/*     */   public void addBattle(Battle battle) {
/*     */     try {
/*  93 */       this.battlesMapLock.writeLock().lock();
/*  94 */       this.battlesMap.put(battle.getBattleKey(), battle);
/*     */     } finally {
/*  96 */       this.battlesMapLock.writeLock().unlock();
/*     */     } 
/*     */   }
/*     */   
/*     */   public Collection<Battle> battleCollections() {
/*     */     try {
/* 102 */       this.battlesMapLock.readLock().lock();
/* 103 */       return this.battlesMap.values();
/*     */     } finally {
/* 105 */       this.battlesMapLock.readLock().unlock();
/*     */     } 
/*     */   }
/*     */   
/*     */   public String getBattleMapKey() {
/* 110 */     return this.battleMapKey;
/*     */   }
/*     */   
/*     */   public void setBattleMapKey(String battleMapKey) {
/* 114 */     this.battleMapKey = battleMapKey;
/*     */   }
/*     */   
/*     */   public BattleUtil.BattleState getBattleState() {
/* 118 */     return this.battleState;
/*     */   }
/*     */   
/*     */   public void setBattleState(BattleUtil.BattleState battleState) {
/* 122 */     this.battleState = battleState;
/*     */   }
/*     */   
/*     */   public Map<String, Battle> getBattlesMap() {
/* 126 */     return Collections.unmodifiableMap(this.battlesMap);
/*     */   }
/*     */   
/*     */   public void setBattlesMap(Map<String, Battle> battlesMap) {
/* 130 */     this.battlesMap = battlesMap;
/*     */   }
/*     */   
/*     */   public AtomicInteger getBattleMapCounter() {
/* 134 */     return this.battleMapCounter;
/*     */   }
/*     */   
/*     */   public void setBattleMapCounter(AtomicInteger battleMapCounter) {
/* 138 */     this.battleMapCounter = battleMapCounter;
/*     */   }
/*     */   
/*     */   public int getOpentime() {
/* 142 */     return this.opentime;
/*     */   }
/*     */   
/*     */   public void setOpentime(int opentime) {
/* 146 */     this.opentime = opentime;
/*     */   }
/*     */   
/*     */   public BattleMap() {}
/*     */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\sanguo\webgame\rmi\battle\BattleMap.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */