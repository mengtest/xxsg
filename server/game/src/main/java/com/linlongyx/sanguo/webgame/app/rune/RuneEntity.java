/*     */ package com.linlongyx.sanguo.webgame.app.rune;
/*     */ 
/*     */ import com.linlongyx.core.framework.dao.annotation.Table;
/*     */ import com.linlongyx.core.framework.dao.annotation.TableField;
/*     */ import com.linlongyx.core.framework.dao.entity.IEntity;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.BattleRecordData;
/*     */ import java.util.HashMap;
/*     */ import java.util.LinkedList;
/*     */ import java.util.Map;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @Table(tableName = "tb_rune", prefix = "rune_%serverId_%playerId", keyField = "playerId", isPlayerIdKey = true)
/*     */ public class RuneEntity
/*     */   implements IEntity
/*     */ {
/*     */   @TableField(isKey = true)
/*     */   private final transient long playerId;
/*     */   private long runeMidInit;
/*     */   private int goldEndTime;
/*  25 */   private Map<Integer, Long> runeMap = new HashMap<>();
/*     */   
/*     */   private int totalZhuHun;
/*     */   private int talismanTurn;
/*     */   private long tatalArtificeTimes;
/*     */   private int point;
/*     */   private int times;
/*     */   private int hp;
/*  33 */   private LinkedList<BattleRecordData> records = new LinkedList<>();
/*     */   
/*     */   private int lastRecoverTime;
/*  36 */   private Map<Integer, Integer> dailyReward = new HashMap<>();
/*  37 */   private Map<Integer, Integer> stageReward = new HashMap<>();
/*     */   
/*     */   public RuneEntity(long playerId) {
/*  40 */     this.playerId = playerId;
/*     */   }
/*     */   
/*     */   public long getPlayerId() {
/*  44 */     return this.playerId;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public long getRuneMidInit() {
/*  50 */     return this.runeMidInit;
/*     */   }
/*     */   
/*     */   public void setRuneMidInit(long runeMidInit) {
/*  54 */     this.runeMidInit = runeMidInit;
/*     */   }
/*     */   
/*     */   public int getGoldEndTime() {
/*  58 */     return this.goldEndTime;
/*     */   }
/*     */   
/*     */   public void setGoldEndTime(int goldEndTime) {
/*  62 */     this.goldEndTime = goldEndTime;
/*     */   }
/*     */   
/*     */   public Map<Integer, Long> getRuneMap() {
/*  66 */     return this.runeMap;
/*     */   }
/*     */   
/*     */   public void setRuneMap(Map<Integer, Long> runeMap) {
/*  70 */     this.runeMap = runeMap;
/*     */   }
/*     */   
/*     */   public int getPoint() {
/*  74 */     return this.point;
/*     */   }
/*     */   
/*     */   public void setPoint(int point) {
/*  78 */     this.point = point;
/*     */   }
/*     */   
/*     */   public int getHp() {
/*  82 */     return this.hp;
/*     */   }
/*     */   
/*     */   public void setHp(int hp) {
/*  86 */     this.hp = hp;
/*     */   }
/*     */   
/*     */   public LinkedList<BattleRecordData> getRecords() {
/*  90 */     return this.records;
/*     */   }
/*     */   
/*     */   public void setRecords(LinkedList<BattleRecordData> records) {
/*  94 */     this.records = records;
/*     */   }
/*     */   
/*     */   public int getLastRecoverTime() {
/*  98 */     return this.lastRecoverTime;
/*     */   }
/*     */   
/*     */   public void setLastRecoverTime(int lastRecoverTime) {
/* 102 */     this.lastRecoverTime = lastRecoverTime;
/*     */   }
/*     */   
/*     */   public int getTimes() {
/* 106 */     return this.times;
/*     */   }
/*     */   
/*     */   public void setTimes(int times) {
/* 110 */     this.times = times;
/*     */   }
/*     */   
/*     */   public Map<Integer, Integer> getDailyReward() {
/* 114 */     return this.dailyReward;
/*     */   }
/*     */   
/*     */   public void setDailyReward(Map<Integer, Integer> dailyReward) {
/* 118 */     this.dailyReward = dailyReward;
/*     */   }
/*     */   
/*     */   public Map<Integer, Integer> getStageReward() {
/* 122 */     return this.stageReward;
/*     */   }
/*     */   
/*     */   public void setStageReward(Map<Integer, Integer> stageReward) {
/* 126 */     this.stageReward = stageReward;
/*     */   }
/*     */   
/*     */   public int getTotalZhuHun() {
/* 130 */     return this.totalZhuHun;
/*     */   }
/*     */   
/*     */   public void setTotalZhuHun(int totalZhuHun) {
/* 134 */     this.totalZhuHun = totalZhuHun;
/*     */   }
/*     */   
/*     */   public int getTalismanTurn() {
/* 138 */     return this.talismanTurn;
/*     */   }
/*     */   
/*     */   public void setTalismanTurn(int talismanTurn) {
/* 142 */     this.talismanTurn = talismanTurn;
/*     */   }
/*     */   
/*     */   public long getTatalArtificeTimes() {
/* 146 */     return this.tatalArtificeTimes;
/*     */   }
/*     */   
/*     */   public void setTatalArtificeTimes(long tatalArtificeTimes) {
/* 150 */     this.tatalArtificeTimes = tatalArtificeTimes;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 155 */     return "RuneEntity{playerId=" + this.playerId + ", runeMidInit=" + this.runeMidInit + ", goldEndTime=" + this.goldEndTime + ", runeMap=" + this.runeMap + ", totalZhuHun=" + this.totalZhuHun + ", talismanTurn=" + this.talismanTurn + ", tatalArtificeTimes=" + this.tatalArtificeTimes + ", point=" + this.point + ", times=" + this.times + ", hp=" + this.hp + ", records=" + this.records + ", lastRecoverTime=" + this.lastRecoverTime + ", dailyReward=" + this.dailyReward + ", stageReward=" + this.stageReward + '}';
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\app\rune\RuneEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */