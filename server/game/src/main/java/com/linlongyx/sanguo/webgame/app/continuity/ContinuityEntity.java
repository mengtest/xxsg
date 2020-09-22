/*    */ package com.linlongyx.sanguo.webgame.app.continuity;
/*    */ 
/*    */ import com.linlongyx.core.framework.dao.annotation.Table;
/*    */ import com.linlongyx.core.framework.dao.annotation.TableField;
/*    */ import com.linlongyx.core.framework.dao.entity.IMapEntity;
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ 
/*    */ 
/*    */ 
/*    */ @Table(tableName = "tb_continuity", prefix = "continuity_%serverId_%playerId", isPlayerIdKey = true, keyField = "id")
/*    */ public class ContinuityEntity
/*    */   implements IMapEntity
/*    */ {
/*    */   @TableField(isKey = true)
/*    */   private final transient long playerId;
/*    */   @TableField(isKey = true)
/*    */   private final transient int id;
/* 19 */   private Map<Integer, Integer> states = new HashMap<>();
/* 20 */   private Map<Integer, Long> dateCharges = new HashMap<>();
/*    */   private boolean open;
/*    */   
/*    */   public ContinuityEntity(long playerId, int id) {
/* 24 */     this.playerId = playerId;
/* 25 */     this.id = id;
/*    */   }
/*    */   
/*    */   public long getPlayerId() {
/* 29 */     return this.playerId;
/*    */   }
/*    */   
/*    */   public int getId() {
/* 33 */     return this.id;
/*    */   }
/*    */   
/*    */   public Map<Integer, Integer> getStates() {
/* 37 */     return this.states;
/*    */   }
/*    */   
/*    */   public void setStates(Map<Integer, Integer> states) {
/* 41 */     this.states = states;
/*    */   }
/*    */   
/*    */   public Map<Integer, Long> getDateCharges() {
/* 45 */     return this.dateCharges;
/*    */   }
/*    */   
/*    */   public void setDateCharges(Map<Integer, Long> dateCharges) {
/* 49 */     this.dateCharges = dateCharges;
/*    */   }
/*    */   
/*    */   public boolean isOpen() {
/* 53 */     return this.open;
/*    */   }
/*    */   
/*    */   public void setOpen(boolean open) {
/* 57 */     this.open = open;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 62 */     return "ContinuityEntity{playerId=" + this.playerId + ", id=" + this.id + ", states=" + this.states + ", dateCharges=" + this.dateCharges + ", open=" + this.open + '}';
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Object mapKey() {
/* 73 */     return Integer.valueOf(getId());
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\app\continuity\ContinuityEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */