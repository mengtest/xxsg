/*    */ package com.linlongyx.sanguo.webgame.app.rebate;
/*    */ 
/*    */ import com.linlongyx.core.framework.dao.annotation.Table;
/*    */ import com.linlongyx.core.framework.dao.annotation.TableField;
/*    */ import com.linlongyx.core.framework.dao.entity.IMapEntity;
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Table(tableName = "tb_rebate", prefix = "rebate_%serverId_%playerId", isPlayerIdKey = true, keyField = "id")
/*    */ public class RechargeRebateEntity
/*    */   implements IMapEntity
/*    */ {
/*    */   @TableField(isKey = true)
/*    */   private final transient long playerId;
/*    */   @TableField(isKey = true)
/*    */   private final transient int id;
/*    */   private long charge;
/*    */   private long refChare;
/*    */   private int score;
/* 23 */   private Map<Integer, Integer> reward = new HashMap<>();
/*    */   
/*    */   private int times;
/*    */   
/*    */   public RechargeRebateEntity(long playerId, int id) {
/* 28 */     this.playerId = playerId;
/* 29 */     this.id = id;
/*    */   }
/*    */   
/*    */   public long getPlayerId() {
/* 33 */     return this.playerId;
/*    */   }
/*    */   
/*    */   public int getId() {
/* 37 */     return this.id;
/*    */   }
/*    */   
/*    */   public long getCharge() {
/* 41 */     return this.charge;
/*    */   }
/*    */   
/*    */   public void setCharge(long charge) {
/* 45 */     this.charge = charge;
/*    */   }
/*    */   
/*    */   public long getRefChare() {
/* 49 */     return this.refChare;
/*    */   }
/*    */   
/*    */   public void setRefChare(long refChare) {
/* 53 */     this.refChare = refChare;
/*    */   }
/*    */   
/*    */   public int getScore() {
/* 57 */     return this.score;
/*    */   }
/*    */   
/*    */   public void setScore(int score) {
/* 61 */     this.score = score;
/*    */   }
/*    */   
/*    */   public Map<Integer, Integer> getReward() {
/* 65 */     return this.reward;
/*    */   }
/*    */   
/*    */   public void setReward(Map<Integer, Integer> reward) {
/* 69 */     this.reward = reward;
/*    */   }
/*    */   
/*    */   public int getTimes() {
/* 73 */     return this.times;
/*    */   }
/*    */   
/*    */   public void setTimes(int times) {
/* 77 */     this.times = times;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 82 */     return "RechargeRebateEntity{playerId=" + this.playerId + ", id=" + this.id + ", charge=" + this.charge + ", refChare=" + this.refChare + ", score=" + this.score + ", reward=" + this.reward + ", times=" + this.times + '}';
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Object mapKey() {
/* 95 */     return Integer.valueOf(getId());
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\app\rebate\RechargeRebateEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */