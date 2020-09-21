/*     */ package com.linlongyx.sanguo.webgame.app.tower;
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
/*     */ @Table(tableName = "tb_tower", prefix = "tower_%serverId_%playerId", isPlayerIdKey = true, keyField = "playerId")
/*     */ public class TowerEntity
/*     */   implements IEntity
/*     */ {
/*     */   @TableField(isKey = true)
/*     */   private final transient long playerId;
/*     */   private int curLayers;
/*     */   private int preLayers;
/*     */   private int todayNormalReward;
/*     */   private int todayCCYReward;
/*     */   private int passTime;
/*  25 */   private Map<Integer, Integer> actPlayTime = new HashMap<>();
/*     */   
/*     */   private int mobai;
/*     */   private byte needSend;
/*  29 */   private LinkedList<BattleRecordData> records = new LinkedList<>();
/*     */ 
/*     */   
/*     */   public TowerEntity(long playerId) {
/*  33 */     this.playerId = playerId;
/*     */   }
/*     */   public long getPlayerId() {
/*  36 */     return this.playerId;
/*     */   }
/*     */   
/*     */   public int getCurLayers() {
/*  40 */     return this.curLayers;
/*     */   }
/*     */   
/*     */   public void setCurLayers(int curLayers) {
/*  44 */     this.curLayers = curLayers;
/*     */   }
/*     */   
/*     */   public int getPreLayers() {
/*  48 */     return this.preLayers;
/*     */   }
/*     */   
/*     */   public void setPreLayers(int preLayers) {
/*  52 */     this.preLayers = preLayers;
/*     */   }
/*     */   
/*     */   public int getTodayNormalReward() {
/*  56 */     return this.todayNormalReward;
/*     */   }
/*     */   
/*     */   public void setTodayNormalReward(int todayNormalReward) {
/*  60 */     this.todayNormalReward = todayNormalReward;
/*     */   }
/*     */   
/*     */   public int getTodayCCYReward() {
/*  64 */     return this.todayCCYReward;
/*     */   }
/*     */   
/*     */   public void setTodayCCYReward(int todayCCYReward) {
/*  68 */     this.todayCCYReward = todayCCYReward;
/*     */   }
/*     */   
/*     */   public int getPassTime() {
/*  72 */     return this.passTime;
/*     */   }
/*     */   
/*     */   public void setPassTime(int passTime) {
/*  76 */     this.passTime = passTime;
/*     */   }
/*     */   
/*     */   public Map<Integer, Integer> getActPlayTime() {
/*  80 */     return this.actPlayTime;
/*     */   }
/*     */   
/*     */   public void setActPlayTime(Map<Integer, Integer> actPlayTime) {
/*  84 */     this.actPlayTime = actPlayTime;
/*     */   }
/*     */   
/*     */   public int getMobai() {
/*  88 */     return this.mobai;
/*     */   }
/*     */   
/*     */   public void setMobai(int mobai) {
/*  92 */     this.mobai = mobai;
/*     */   }
/*     */   
/*     */   public byte getNeedSend() {
/*  96 */     return this.needSend;
/*     */   }
/*     */   
/*     */   public void setNeedSend(byte needSend) {
/* 100 */     this.needSend = needSend;
/*     */   }
/*     */   
/*     */   public LinkedList<BattleRecordData> getRecords() {
/* 104 */     return this.records;
/*     */   }
/*     */   
/*     */   public void setRecords(LinkedList<BattleRecordData> records) {
/* 108 */     this.records = records;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 113 */     return "TowerEntity{playerId=" + this.playerId + ", curLayers=" + this.curLayers + ", preLayers=" + this.preLayers + ", todayNormalReward=" + this.todayNormalReward + ", todayCCYReward=" + this.todayCCYReward + ", passTime=" + this.passTime + ", actPlayTime=" + this.actPlayTime + ", mobai=" + this.mobai + ", needSend=" + this.needSend + ", records=" + this.records + '}';
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\app\tower\TowerEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */