/*    */ package com.linlongyx.sanguo.webgame.app.limitbuy;
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
/*    */ @Table(tableName = "tb_limitbuy", prefix = "limitbuy_%serverId_%playerId", isPlayerIdKey = true, keyField = "id")
/*    */ public class LimitBuyEntity
/*    */   implements IMapEntity
/*    */ {
/*    */   @TableField(isKey = true)
/*    */   private final transient long playerId;
/*    */   @TableField(isKey = true)
/*    */   private final transient int id;
/* 20 */   private Map<Integer, Integer> limitBuyGoods = new HashMap<>();
/*    */   private boolean open;
/*    */   
/*    */   public LimitBuyEntity(long playerId, int id) {
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
/*    */   public Map<Integer, Integer> getLimitBuyGoods() {
/* 38 */     return this.limitBuyGoods;
/*    */   }
/*    */   
/*    */   public void setLimitBuyGoods(Map<Integer, Integer> limitBuyGoods) {
/* 42 */     this.limitBuyGoods = limitBuyGoods;
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
/*    */ 
/*    */   
/*    */   public String toString() {
/* 58 */     return "limitbuyEntity{playerId=" + this.playerId + ", id=" + this.id + ", limitBuyGoods=" + this.limitBuyGoods + ", open=" + this.open + '}';
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Object mapKey() {
/* 68 */     return Integer.valueOf(getId());
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\app\limitbuy\LimitBuyEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */