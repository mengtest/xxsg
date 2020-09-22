/*    */ package com.linlongyx.sanguo.webgame.app.bag;
/*    */ 
/*    */ import com.linlongyx.core.framework.dao.annotation.Table;
/*    */ import com.linlongyx.core.framework.dao.annotation.TableField;
/*    */ import com.linlongyx.core.framework.dao.entity.IMapEntity;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Table(tableName = "tb_bag", prefix = "bag_%serverId_%playerId", isPlayerIdKey = true, keyField = "itemId")
/*    */ public class BagEntity
/*    */   implements IMapEntity
/*    */ {
/*    */   @TableField(isKey = true)
/*    */   private final transient long playerId;
/*    */   @TableField(isKey = true)
/*    */   private final int itemId;
/*    */   private int num;
/*    */   
/*    */   public BagEntity(long playerId, int itemId) {
/* 21 */     this.playerId = playerId;
/* 22 */     this.itemId = itemId;
/*    */   }
/*    */   
/*    */   public long getPlayerId() {
/* 26 */     return this.playerId;
/*    */   }
/*    */   
/*    */   public int getItemId() {
/* 30 */     return this.itemId;
/*    */   }
/*    */   
/*    */   public int getNum() {
/* 34 */     return this.num;
/*    */   }
/*    */   
/*    */   public void setNum(int num) {
/* 38 */     this.num = num;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 43 */     return "BagEntity{playerId=" + this.playerId + ", itemId=" + this.itemId + ", num=" + this.num + '}';
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Object mapKey() {
/* 52 */     return Integer.valueOf(this.itemId);
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\app\bag\BagEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */