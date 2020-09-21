/*    */ package com.linlongyx.sanguo.webgame.app.rechargeActivity;
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
/*    */ 
/*    */ 
/*    */ @Table(tableName = "tb_rechargeActivity", prefix = "continuity_%serverId_%playerId", isPlayerIdKey = true, keyField = "id")
/*    */ public class RechargeActivityEntity
/*    */   implements IMapEntity
/*    */ {
/*    */   @TableField(isKey = true)
/*    */   private final transient long playerId;
/*    */   @TableField(isKey = true)
/*    */   private final transient int id;
/*    */   private long recharge;
/* 24 */   private Map<Integer, Integer> states = new HashMap<>();
/*    */   
/*    */   public RechargeActivityEntity(long playerId, int id) {
/* 27 */     this.playerId = playerId;
/* 28 */     this.id = id;
/*    */   }
/*    */   
/*    */   public long getPlayerId() {
/* 32 */     return this.playerId;
/*    */   }
/*    */   
/*    */   public int getId() {
/* 36 */     return this.id;
/*    */   }
/*    */   
/*    */   public long getRecharge() {
/* 40 */     return this.recharge;
/*    */   }
/*    */   
/*    */   public void setRecharge(long recharge) {
/* 44 */     this.recharge = recharge;
/*    */   }
/*    */   
/*    */   public Map<Integer, Integer> getStates() {
/* 48 */     return this.states;
/*    */   }
/*    */   
/*    */   public void setStates(Map<Integer, Integer> states) {
/* 52 */     this.states = states;
/*    */   }
/*    */ 
/*    */   
/*    */   public Object mapKey() {
/* 57 */     return Integer.valueOf(this.id);
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\app\rechargeActivity\RechargeActivityEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */