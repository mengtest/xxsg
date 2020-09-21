/*    */ package com.linlongyx.sanguo.webgame.app.crossRace;
/*    */ 
/*    */ import com.linlongyx.core.framework.dao.annotation.Table;
/*    */ import com.linlongyx.core.framework.dao.annotation.TableField;
/*    */ import com.linlongyx.core.framework.dao.entity.IEntity;
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Table(tableName = "tb_crossRace", prefix = "crossRace_%serverId_%playerId", keyField = "playerId", isPlayerIdKey = true)
/*    */ public class CrossRaceEntity
/*    */   implements IEntity
/*    */ {
/*    */   @TableField(isKey = true)
/*    */   private final transient long playerId;
/*    */   private int raceId;
/*    */   private int point;
/*    */   private int times;
/*    */   private int winTimes;
/*    */   private int fightTimes;
/* 25 */   private Map<Integer, Integer> joinReward = new HashMap<>();
/*    */   private byte balance;
/*    */   private int totalTimes;
/*    */   
/*    */   public CrossRaceEntity(long playerId) {
/* 30 */     this.playerId = playerId;
/*    */   }
/*    */   
/*    */   public long getPlayerId() {
/* 34 */     return this.playerId;
/*    */   }
/*    */   
/*    */   public int getRaceId() {
/* 38 */     return this.raceId;
/*    */   }
/*    */   
/*    */   public void setRaceId(int raceId) {
/* 42 */     this.raceId = raceId;
/*    */   }
/*    */   
/*    */   public int getPoint() {
/* 46 */     return this.point;
/*    */   }
/*    */   
/*    */   public void setPoint(int point) {
/* 50 */     this.point = point;
/*    */   }
/*    */   
/*    */   public int getTimes() {
/* 54 */     return this.times;
/*    */   }
/*    */   
/*    */   public void setTimes(int times) {
/* 58 */     this.times = times;
/*    */   }
/*    */   
/*    */   public int getWinTimes() {
/* 62 */     return this.winTimes;
/*    */   }
/*    */   
/*    */   public void setWinTimes(int winTimes) {
/* 66 */     this.winTimes = winTimes;
/*    */   }
/*    */   
/*    */   public int getFightTimes() {
/* 70 */     return this.fightTimes;
/*    */   }
/*    */   
/*    */   public void setFightTimes(int fightTimes) {
/* 74 */     this.fightTimes = fightTimes;
/*    */   }
/*    */   
/*    */   public Map<Integer, Integer> getJoinReward() {
/* 78 */     return this.joinReward;
/*    */   }
/*    */   
/*    */   public void setJoinReward(Map<Integer, Integer> joinReward) {
/* 82 */     this.joinReward = joinReward;
/*    */   }
/*    */   
/*    */   public byte getBalance() {
/* 86 */     return this.balance;
/*    */   }
/*    */   
/*    */   public void setBalance(byte balance) {
/* 90 */     this.balance = balance;
/*    */   }
/*    */   
/*    */   public int getTotalTimes() {
/* 94 */     return this.totalTimes;
/*    */   }
/*    */   
/*    */   public void setTotalTimes(int totalTimes) {
/* 98 */     this.totalTimes = totalTimes;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\app\crossRace\CrossRaceEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */