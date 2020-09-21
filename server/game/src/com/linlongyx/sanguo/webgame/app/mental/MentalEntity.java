/*    */ package com.linlongyx.sanguo.webgame.app.mental;
/*    */ 
/*    */ import com.linlongyx.core.framework.dao.annotation.Table;
/*    */ import com.linlongyx.core.framework.dao.annotation.TableField;
/*    */ import com.linlongyx.core.framework.dao.entity.IEntity;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.MyMentalStruct;
/*    */ import java.util.ArrayList;
/*    */ import java.util.HashSet;
/*    */ import java.util.List;
/*    */ import java.util.Set;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Table(tableName = "tb_player_mental", prefix = "mental_%serverId_%playerId", keyField = "playerId", isPlayerIdKey = true)
/*    */ public class MentalEntity
/*    */   implements IEntity
/*    */ {
/*    */   @TableField(isKey = true)
/*    */   private final transient long playerId;
/*    */   private int times;
/*    */   private int totalTimes;
/*    */   private int freeTime;
/* 25 */   private Set<Integer> rewardIds = new HashSet<>();
/*    */   private int point;
/* 27 */   private List<MyMentalStruct> records = new ArrayList<>();
/*    */   
/*    */   public MentalEntity(long playerId) {
/* 30 */     this.playerId = playerId;
/*    */   }
/*    */   
/*    */   public long getPlayerId() {
/* 34 */     return this.playerId;
/*    */   }
/*    */   
/*    */   public int getTimes() {
/* 38 */     return this.times;
/*    */   }
/*    */   
/*    */   public void setTimes(int times) {
/* 42 */     this.times = times;
/*    */   }
/*    */   
/*    */   public int getTotalTimes() {
/* 46 */     return this.totalTimes;
/*    */   }
/*    */   
/*    */   public void setTotalTimes(int totalTimes) {
/* 50 */     this.totalTimes = totalTimes;
/*    */   }
/*    */   
/*    */   public int getFreeTime() {
/* 54 */     return this.freeTime;
/*    */   }
/*    */   
/*    */   public void setFreeTime(int freeTime) {
/* 58 */     this.freeTime = freeTime;
/*    */   }
/*    */   
/*    */   public Set<Integer> getRewardIds() {
/* 62 */     return this.rewardIds;
/*    */   }
/*    */   
/*    */   public void setRewardIds(Set<Integer> rewardIds) {
/* 66 */     this.rewardIds = rewardIds;
/*    */   }
/*    */   
/*    */   public int getPoint() {
/* 70 */     return this.point;
/*    */   }
/*    */   
/*    */   public void setPoint(int point) {
/* 74 */     this.point = point;
/*    */   }
/*    */   
/*    */   public List<MyMentalStruct> getRecords() {
/* 78 */     return this.records;
/*    */   }
/*    */   
/*    */   public void setRecords(List<MyMentalStruct> records) {
/* 82 */     this.records = records;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String toString() {
/* 88 */     return "MentalEntity{playerId=" + this.playerId + ", times=" + this.times + ", totalTimes=" + this.totalTimes + ", freeTime=" + this.freeTime + ", rewardIds=" + this.rewardIds + ", point=" + this.point + ", records=" + this.records + '}';
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\app\mental\MentalEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */