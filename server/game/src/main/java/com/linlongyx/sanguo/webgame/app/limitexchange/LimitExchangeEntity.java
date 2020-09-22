/*    */ package com.linlongyx.sanguo.webgame.app.limitexchange;
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
/*    */ @Table(tableName = "tb_limitexchange", prefix = "limitexchange_%serverId_%playerId", isPlayerIdKey = true, keyField = "id")
/*    */ public class LimitExchangeEntity
/*    */   implements IMapEntity
/*    */ {
/*    */   @TableField(isKey = true)
/*    */   private final transient long playerId;
/*    */   @TableField(isKey = true)
/*    */   private final transient int id;
/* 20 */   private Map<Integer, Integer> limitExchangeGoods = new HashMap<>();
/*    */   private boolean open;
/*    */   
/*    */   public LimitExchangeEntity(long playerId, int id) {
/* 24 */     this.playerId = playerId;
/* 25 */     this.id = id;
/*    */   }
/*    */ 
/*    */   
/*    */   public long getPlayerId() {
/* 30 */     return this.playerId;
/*    */   }
/*    */   
/*    */   public int getId() {
/* 34 */     return this.id;
/*    */   }
/*    */   
/*    */   public Map<Integer, Integer> getLimitExchangeGoods() {
/* 38 */     return this.limitExchangeGoods;
/*    */   }
/*    */   
/*    */   public void setLimitExchangeGoods(Map<Integer, Integer> limitExchangeGoods) {
/* 42 */     this.limitExchangeGoods = limitExchangeGoods;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isOpen() {
/* 47 */     return this.open;
/*    */   }
/*    */   
/*    */   public void setOpen(boolean open) {
/* 51 */     this.open = open;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 56 */     return "limitexchangeEntity{playerId=" + this.playerId + ", id=" + this.id + ", limitExchangeGoods=" + this.limitExchangeGoods + ", open=" + this.open + '}';
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Object mapKey() {
/* 66 */     return Integer.valueOf(getId());
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\app\limitexchange\LimitExchangeEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */