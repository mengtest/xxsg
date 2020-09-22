/*    */ package com.linlongyx.sanguo.webgame.app.secreti;
/*    */ 
/*    */ import com.linlongyx.core.framework.dao.annotation.Table;
/*    */ import com.linlongyx.core.framework.dao.annotation.TableField;
/*    */ import com.linlongyx.core.framework.dao.entity.IEntity;
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ import java.util.Set;
/*    */ import java.util.TreeMap;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Table(tableName = "tb_secreti", prefix = "secreti_%serverId_%playerId", keyField = "playerId", isPlayerIdKey = true)
/*    */ public class SecretiEntity
/*    */   implements IEntity
/*    */ {
/*    */   @TableField(isKey = true)
/*    */   private final transient long playerId;
/* 22 */   private Map<Integer, Set<Integer>> insMap = new TreeMap<>();
/*    */   
/* 24 */   private Map<Integer, Integer> rewards = new HashMap<>();
/*    */   
/*    */   private int rewardTimes;
/*    */   
/*    */   private int total;
/*    */   private int totalTimes;
/*    */   
/*    */   public SecretiEntity(long playerId) {
/* 32 */     this.playerId = playerId;
/*    */   }
/*    */   
/*    */   public long getPlayerId() {
/* 36 */     return this.playerId;
/*    */   }
/*    */   
/*    */   public Map<Integer, Set<Integer>> getInsMap() {
/* 40 */     return this.insMap;
/*    */   }
/*    */   
/*    */   public void setInsMap(Map<Integer, Set<Integer>> insMap) {
/* 44 */     this.insMap = insMap;
/*    */   }
/*    */   
/*    */   public Map<Integer, Integer> getRewards() {
/* 48 */     return this.rewards;
/*    */   }
/*    */   
/*    */   public void setRewards(Map<Integer, Integer> rewards) {
/* 52 */     this.rewards = rewards;
/*    */   }
/*    */   
/*    */   public int getRewardTimes() {
/* 56 */     return this.rewardTimes;
/*    */   }
/*    */   
/*    */   public void setRewardTimes(int rewardTimes) {
/* 60 */     this.rewardTimes = rewardTimes;
/*    */   }
/*    */   
/*    */   public int getTotal() {
/* 64 */     return this.total;
/*    */   }
/*    */   
/*    */   public void setTotal(int total) {
/* 68 */     this.total = total;
/*    */   }
/*    */   
/*    */   public int getTotalTimes() {
/* 72 */     return this.totalTimes;
/*    */   }
/*    */   
/*    */   public void setTotalTimes(int totalTimes) {
/* 76 */     this.totalTimes = totalTimes;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\app\secreti\SecretiEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */