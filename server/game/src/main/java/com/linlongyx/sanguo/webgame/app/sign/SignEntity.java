/*     */ package com.linlongyx.sanguo.webgame.app.sign;
/*     */ 
/*     */ import com.linlongyx.core.framework.dao.annotation.Table;
/*     */ import com.linlongyx.core.framework.dao.annotation.TableField;
/*     */ import com.linlongyx.core.framework.dao.entity.IEntity;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Map;
/*     */ import org.apache.commons.collections.map.HashedMap;
/*     */ 
/*     */ 
/*     */ 
/*     */ @Table(tableName = "tb_sign", prefix = "sign_%serverId_%playerId", isPlayerIdKey = true, keyField = "playerId")
/*     */ public class SignEntity
/*     */   implements IEntity
/*     */ {
/*     */   @TableField(isKey = true)
/*     */   private final transient long playerId;
/*     */   private boolean signToday;
/*     */   private int signCount;
/*     */   private long weekCharge;
/*     */   private long dayCharge;
/*  22 */   private ArrayList<Integer> todayReward = new ArrayList<>();
/*  23 */   private ArrayList<Integer> weekReward = new ArrayList<>();
/*  24 */   private Map<Integer, Integer> signReward = (Map<Integer, Integer>)new HashedMap();
/*  25 */   private Map<Integer, Integer> levelMap = (Map<Integer, Integer>)new HashedMap();
/*  26 */   private Map<Integer, Integer> timeMap = (Map<Integer, Integer>)new HashedMap();
/*     */   private int showLevel;
/*     */   
/*     */   public SignEntity(long playerId) {
/*  30 */     this.playerId = playerId;
/*     */   }
/*     */   public long getPlayerId() {
/*  33 */     return this.playerId;
/*     */   }
/*     */   
/*     */   public boolean isSignToday() {
/*  37 */     return this.signToday;
/*     */   }
/*     */   
/*     */   public void setSignToday(boolean signToday) {
/*  41 */     this.signToday = signToday;
/*     */   }
/*     */   
/*     */   public int getSignCount() {
/*  45 */     return this.signCount;
/*     */   }
/*     */   
/*     */   public void setSignCount(int signCount) {
/*  49 */     this.signCount = signCount;
/*     */   }
/*     */   
/*     */   public long getWeekCharge() {
/*  53 */     return this.weekCharge;
/*     */   }
/*     */   
/*     */   public void setWeekCharge(long weekCharge) {
/*  57 */     this.weekCharge = weekCharge;
/*     */   }
/*     */   
/*     */   public long getDayCharge() {
/*  61 */     return this.dayCharge;
/*     */   }
/*     */   
/*     */   public void setDayCharge(long dayCharge) {
/*  65 */     this.dayCharge = dayCharge;
/*     */   }
/*     */   
/*     */   public ArrayList<Integer> getTodayReward() {
/*  69 */     return this.todayReward;
/*     */   }
/*     */   
/*     */   public void setTodayReward(ArrayList<Integer> todayReward) {
/*  73 */     this.todayReward = todayReward;
/*     */   }
/*     */   
/*     */   public ArrayList<Integer> getWeekReward() {
/*  77 */     return this.weekReward;
/*     */   }
/*     */   
/*     */   public void setWeekReward(ArrayList<Integer> weekReward) {
/*  81 */     this.weekReward = weekReward;
/*     */   }
/*     */   
/*     */   public Map<Integer, Integer> getSignReward() {
/*  85 */     return this.signReward;
/*     */   }
/*     */   
/*     */   public void setSignReward(Map<Integer, Integer> signReward) {
/*  89 */     this.signReward = signReward;
/*     */   }
/*     */   
/*     */   public Map<Integer, Integer> getLevelMap() {
/*  93 */     return this.levelMap;
/*     */   }
/*     */   
/*     */   public void setLevelMap(Map<Integer, Integer> levelMap) {
/*  97 */     this.levelMap = levelMap;
/*     */   }
/*     */   
/*     */   public Map<Integer, Integer> getTimeMap() {
/* 101 */     return this.timeMap;
/*     */   }
/*     */   
/*     */   public void setTimeMap(Map<Integer, Integer> timeMap) {
/* 105 */     this.timeMap = timeMap;
/*     */   }
/*     */   
/*     */   public int getShowLevel() {
/* 109 */     return this.showLevel;
/*     */   }
/*     */   
/*     */   public void setShowLevel(int showLevel) {
/* 113 */     this.showLevel = showLevel;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 118 */     return "SignEntity{playerId=" + this.playerId + "signToday=" + this.signToday + "signCount=" + this.signCount + "weekCharge=" + this.weekCharge + "dayCharge=" + this.dayCharge + "todayReward=" + this.todayReward + "weekReward=" + this.weekReward + "signReward=" + this.signReward + "levelMap=" + this.levelMap + "timeMap=" + this.timeMap + "showLevel=" + this.showLevel + '}';
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\app\sign\SignEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */