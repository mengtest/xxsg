/*    */ package com.linlongyx.sanguo.webgame.app.crossRankAct;
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
/*    */ 
/*    */ @Table(tableName = "tb_crossRankAct", prefix = "crossRankAct_%serverId_%playerId", isPlayerIdKey = true, keyField = "id")
/*    */ public class CrossRankActEntity
/*    */   implements IMapEntity
/*    */ {
/*    */   @TableField(isKey = true)
/*    */   private final transient long playerId;
/*    */   @TableField(isKey = true)
/*    */   private final transient int id;
/*    */   private long value;
/* 22 */   private Map<Integer, Integer> states = new HashMap<>();
/*    */   private boolean open;
/*    */   
/*    */   public CrossRankActEntity(long playerId, int id) {
/* 26 */     this.playerId = playerId;
/* 27 */     this.id = id;
/*    */   }
/*    */   
/*    */   public int getId() {
/* 31 */     return this.id;
/*    */   }
/*    */   
/*    */   public long getPlayerId() {
/* 35 */     return this.playerId;
/*    */   }
/*    */   
/*    */   public long getValue() {
/* 39 */     return this.value;
/*    */   }
/*    */   
/*    */   public void setValue(long value) {
/* 43 */     this.value = value;
/*    */   }
/*    */ 
/*    */   
/*    */   public Map<Integer, Integer> getStates() {
/* 48 */     return this.states;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setStates(Map<Integer, Integer> states) {
/* 53 */     this.states = states;
/*    */   }
/*    */   
/*    */   public boolean isOpen() {
/* 57 */     return this.open;
/*    */   }
/*    */   
/*    */   public void setOpen(boolean open) {
/* 61 */     this.open = open;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 66 */     return "RankActEntity{playerId=" + this.playerId + ", id=" + this.id + ", value=" + this.value + ", open=" + this.open + '}';
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Object mapKey() {
/* 76 */     return Integer.valueOf(getId());
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\app\crossRankAct\CrossRankActEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */