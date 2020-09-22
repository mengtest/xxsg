/*    */ package com.linlongyx.sanguo.webgame.app.sanguozhi;
/*    */ 
/*    */ import com.linlongyx.core.framework.dao.annotation.Table;
/*    */ import com.linlongyx.core.framework.dao.annotation.TableField;
/*    */ import com.linlongyx.core.framework.dao.entity.IMapEntity;
/*    */ import java.util.HashMap;
/*    */ import java.util.HashSet;
/*    */ import java.util.Map;
/*    */ import java.util.Set;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Table(tableName = "tb_sanGuoZhi", prefix = "sanGuoZhi_%serverId_%playerId", isPlayerIdKey = true, keyField = "recordStarId")
/*    */ public class SanGuoZhiEntity
/*    */   implements IMapEntity
/*    */ {
/*    */   @TableField(isKey = true)
/*    */   private final transient long playerId;
/*    */   @TableField(isKey = true)
/*    */   private final transient int recordStarId;
/* 22 */   private Set<Integer> rewarded = new HashSet<>();
/* 23 */   private Set<Integer> finishes = new HashSet<>();
/*    */   private boolean activity;
/* 25 */   private Map<Integer, Long> values = new HashMap<>();
/*    */   
/*    */   public SanGuoZhiEntity(long playerId, int recordStarId) {
/* 28 */     this.playerId = playerId;
/* 29 */     this.recordStarId = recordStarId;
/*    */   }
/*    */   
/*    */   public long getPlayerId() {
/* 33 */     return this.playerId;
/*    */   }
/*    */   
/*    */   public int getRecordStarId() {
/* 37 */     return this.recordStarId;
/*    */   }
/*    */   
/*    */   public Set<Integer> getRewarded() {
/* 41 */     return this.rewarded;
/*    */   }
/*    */   
/*    */   public void setRewarded(Set<Integer> rewarded) {
/* 45 */     this.rewarded = rewarded;
/*    */   }
/*    */   
/*    */   public Set<Integer> getFinishes() {
/* 49 */     return this.finishes;
/*    */   }
/*    */   
/*    */   public void setFinishes(Set<Integer> finishes) {
/* 53 */     this.finishes = finishes;
/*    */   }
/*    */   
/*    */   public boolean isActivity() {
/* 57 */     return this.activity;
/*    */   }
/*    */   
/*    */   public void setActivity(boolean activity) {
/* 61 */     this.activity = activity;
/*    */   }
/*    */   
/*    */   public Map<Integer, Long> getValues() {
/* 65 */     return this.values;
/*    */   }
/*    */   
/*    */   public void setValues(Map<Integer, Long> values) {
/* 69 */     this.values = values;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 74 */     return "SanGuoZhiEntity{playerId=" + this.playerId + ", recordStarId=" + this.recordStarId + ", rewarded=" + this.rewarded + ", finishes=" + this.finishes + ", activity=" + this.activity + ", values=" + this.values + '}';
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
/*    */   public Object mapKey() {
/* 86 */     return Integer.valueOf(getRecordStarId());
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\app\sanguozhi\SanGuoZhiEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */