/*     */ package com.linlongyx.sanguo.webgame.app.destiny;
/*     */ 
/*     */ import com.linlongyx.core.framework.dao.annotation.Table;
/*     */ import com.linlongyx.core.framework.dao.annotation.TableField;
/*     */ import com.linlongyx.core.framework.dao.entity.IEntity;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.BattleRecordData;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ 
/*     */ 
/*     */ 
/*     */ @Table(tableName = "tb_destiny", prefix = "destiny_%serverId_%playerId", keyField = "playerId", isPlayerIdKey = true)
/*     */ public class DestinyEntity
/*     */   implements IEntity
/*     */ {
/*     */   @TableField(isKey = true)
/*     */   private final transient long playerId;
/*  21 */   private ArrayList<BattleRecordData> attacks = new ArrayList<>();
/*  22 */   private ArrayList<BattleRecordData> defenses = new ArrayList<>();
/*  23 */   private Map<Long, Byte> battles = new HashMap<>();
/*     */   private int robTimes;
/*     */   private int totalRobTimes;
/*     */   private int refreshTimes;
/*  27 */   private Set<Integer> tasks = new HashSet<>();
/*     */   private int destinyStone;
/*     */   private int lastResetTime;
/*     */   
/*     */   public DestinyEntity(long playerId) {
/*  32 */     this.playerId = playerId;
/*     */   }
/*     */   
/*     */   public long getPlayerId() {
/*  36 */     return this.playerId;
/*     */   }
/*     */   
/*     */   public ArrayList<BattleRecordData> getAttacks() {
/*  40 */     return this.attacks;
/*     */   }
/*     */   
/*     */   public void setAttacks(ArrayList<BattleRecordData> attacks) {
/*  44 */     this.attacks = attacks;
/*     */   }
/*     */   
/*     */   public ArrayList<BattleRecordData> getDefenses() {
/*  48 */     return this.defenses;
/*     */   }
/*     */   
/*     */   public void setDefenses(ArrayList<BattleRecordData> defenses) {
/*  52 */     this.defenses = defenses;
/*     */   }
/*     */   
/*     */   public Map<Long, Byte> getBattles() {
/*  56 */     return this.battles;
/*     */   }
/*     */   
/*     */   public void setBattles(Map<Long, Byte> battles) {
/*  60 */     this.battles = battles;
/*     */   }
/*     */   
/*     */   public int getRobTimes() {
/*  64 */     return this.robTimes;
/*     */   }
/*     */   
/*     */   public void setRobTimes(int robTimes) {
/*  68 */     this.robTimes = robTimes;
/*     */   }
/*     */   
/*     */   public Set<Integer> getTasks() {
/*  72 */     return this.tasks;
/*     */   }
/*     */   
/*     */   public void setTasks(Set<Integer> tasks) {
/*  76 */     this.tasks = tasks;
/*     */   }
/*     */   
/*     */   public int getDestinyStone() {
/*  80 */     return this.destinyStone;
/*     */   }
/*     */   
/*     */   public void setDestinyStone(int destinyStone) {
/*  84 */     this.destinyStone = destinyStone;
/*     */   }
/*     */   
/*     */   public int getTotalRobTimes() {
/*  88 */     return this.totalRobTimes;
/*     */   }
/*     */   
/*     */   public void setTotalRobTimes(int totalRobTimes) {
/*  92 */     this.totalRobTimes = totalRobTimes;
/*     */   }
/*     */   
/*     */   public int getRefreshTimes() {
/*  96 */     return this.refreshTimes;
/*     */   }
/*     */   
/*     */   public void setRefreshTimes(int refreshTimes) {
/* 100 */     this.refreshTimes = refreshTimes;
/*     */   }
/*     */   
/*     */   public int getLastResetTime() {
/* 104 */     return this.lastResetTime;
/*     */   }
/*     */   
/*     */   public void setLastResetTime(int lastResetTime) {
/* 108 */     this.lastResetTime = lastResetTime;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 113 */     return "DestinyEntity{playerId=" + this.playerId + ", attacks=" + this.attacks + ", defenses=" + this.defenses + ", battles=" + this.battles + ", robTimes=" + this.robTimes + ", totalRobTimes=" + this.totalRobTimes + ", refreshTimes=" + this.refreshTimes + ", tasks=" + this.tasks + ", destinyStone=" + this.destinyStone + ", lastResetTime=" + this.lastResetTime + '}';
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\app\destiny\DestinyEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */