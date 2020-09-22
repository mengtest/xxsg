/*    */ package com.linlongyx.sanguo.webgame.app.rankAct;
/*    */ 
/*    */ import com.linlongyx.core.framework.dao.annotation.Table;
/*    */ import com.linlongyx.core.framework.dao.annotation.TableField;
/*    */ import com.linlongyx.core.framework.dao.entity.IMapEntity;
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ 
/*    */ 
/*    */ 
/*    */ @Table(tableName = "tb_rankAct", prefix = "rankAct_%serverId_%playerId", isPlayerIdKey = true, keyField = "id")
/*    */ public class RankActEntity
/*    */   implements IMapEntity
/*    */ {
/*    */   @TableField(isKey = true)
/*    */   private final transient long playerId;
/*    */   @TableField(isKey = true)
/*    */   private final transient int id;
/*    */   private long value;
/* 20 */   private Map<Integer, Integer> states = new HashMap<>();
/*    */   private boolean open;
/*    */   
/*    */   public RankActEntity(long playerId, int id) {
/* 24 */     this.playerId = playerId;
/* 25 */     this.id = id;
/*    */   }
/*    */   
/*    */   public int getId() {
/* 29 */     return this.id;
/*    */   }
/*    */   
/*    */   public long getPlayerId() {
/* 33 */     return this.playerId;
/*    */   }
/*    */   
/*    */   public long getValue() {
/* 37 */     return this.value;
/*    */   }
/*    */   
/*    */   public void setValue(long value) {
/* 41 */     this.value = value;
/*    */   }
/*    */ 
/*    */   
/*    */   public Map<Integer, Integer> getStates() {
/* 46 */     return this.states;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setStates(Map<Integer, Integer> states) {
/* 51 */     this.states = states;
/*    */   }
/*    */   
/*    */   public boolean isOpen() {
/* 55 */     return this.open;
/*    */   }
/*    */   
/*    */   public void setOpen(boolean open) {
/* 59 */     this.open = open;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 64 */     return "RankActEntity{playerId=" + this.playerId + ", id=" + this.id + ", value=" + this.value + ", open=" + this.open + '}';
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Object mapKey() {
/* 74 */     return Integer.valueOf(getId());
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\app\rankAct\RankActEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */