/*    */ package com.linlongyx.sanguo.webgame.app.achieve;
/*    */ 
/*    */ import com.linlongyx.core.framework.dao.annotation.Table;
/*    */ import com.linlongyx.core.framework.dao.annotation.TableField;
/*    */ import com.linlongyx.core.framework.dao.entity.IEntity;
/*    */ import java.util.HashMap;
/*    */ import java.util.HashSet;
/*    */ import java.util.Map;
/*    */ import java.util.Set;
/*    */ 
/*    */ 
/*    */ 
/*    */ @Table(tableName = "tb_achieve", prefix = "achieve_%serverId_%playerId", keyField = "playerId", isPlayerIdKey = true)
/*    */ public class AchieveEntity
/*    */   implements IEntity
/*    */ {
/*    */   @TableField(isKey = true)
/*    */   private final transient long playerId;
/* 19 */   private Map<Integer, Long> value = new HashMap<>();
/* 20 */   private Set<Integer> fameDone = new HashSet<>();
/* 21 */   private Set<Integer> fameReward = new HashSet<>();
/* 22 */   private Set<Integer> processReward = new HashSet<>();
/*    */   private int point;
/*    */   
/*    */   public AchieveEntity(long playerId) {
/* 26 */     this.playerId = playerId;
/*    */   }
/*    */   
/*    */   public long getPlayerId() {
/* 30 */     return this.playerId;
/*    */   }
/*    */   
/*    */   public Map<Integer, Long> getValue() {
/* 34 */     return this.value;
/*    */   }
/*    */   
/*    */   public void setValue(Map<Integer, Long> value) {
/* 38 */     this.value = value;
/*    */   }
/*    */   
/*    */   public Set<Integer> getFameReward() {
/* 42 */     return this.fameReward;
/*    */   }
/*    */   
/*    */   public void setFameReward(Set<Integer> fameReward) {
/* 46 */     this.fameReward = fameReward;
/*    */   }
/*    */   
/*    */   public Set<Integer> getProcessReward() {
/* 50 */     return this.processReward;
/*    */   }
/*    */   
/*    */   public void setProcessReward(Set<Integer> processReward) {
/* 54 */     this.processReward = processReward;
/*    */   }
/*    */   
/*    */   public int getPoint() {
/* 58 */     return this.point;
/*    */   }
/*    */   
/*    */   public void setPoint(int point) {
/* 62 */     this.point = point;
/*    */   }
/*    */   
/*    */   public Set<Integer> getFameDone() {
/* 66 */     return this.fameDone;
/*    */   }
/*    */   
/*    */   public void setFameDone(Set<Integer> fameDone) {
/* 70 */     this.fameDone = fameDone;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 75 */     return "AchieveEntity{playerId=" + this.playerId + ", value=" + this.value + ", fameDone=" + this.fameDone + ", fameReward=" + this.fameReward + ", processReward=" + this.processReward + ", point=" + this.point + '}';
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\app\achieve\AchieveEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */