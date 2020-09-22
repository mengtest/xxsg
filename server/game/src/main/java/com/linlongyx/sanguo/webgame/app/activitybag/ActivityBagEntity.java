/*    */ package com.linlongyx.sanguo.webgame.app.activitybag;
/*    */ 
/*    */ import com.linlongyx.core.framework.dao.annotation.Table;
/*    */ import com.linlongyx.core.framework.dao.annotation.TableField;
/*    */ import com.linlongyx.core.framework.dao.entity.IMapEntity;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Table(tableName = "tb_activityBag", prefix = "activityBag_%serverId_%playerId", isPlayerIdKey = true, keyField = "itemId")
/*    */ public class ActivityBagEntity
/*    */   implements IMapEntity
/*    */ {
/*    */   @TableField(isKey = true)
/*    */   private final transient long playerId;
/*    */   @TableField(isKey = true)
/*    */   private final transient int itemId;
/*    */   private int num;
/*    */   private int end;
/*    */   
/*    */   public ActivityBagEntity(long playerId, int itemId) {
/* 23 */     this.playerId = playerId;
/* 24 */     this.itemId = itemId;
/*    */   }
/*    */   
/*    */   public long getPlayerId() {
/* 28 */     return this.playerId;
/*    */   }
/*    */   
/*    */   public int getItemId() {
/* 32 */     return this.itemId;
/*    */   }
/*    */   
/*    */   public int getNum() {
/* 36 */     return this.num;
/*    */   }
/*    */   
/*    */   public void setNum(int num) {
/* 40 */     this.num = num;
/*    */   }
/*    */   
/*    */   public int getEnd() {
/* 44 */     return this.end;
/*    */   }
/*    */   
/*    */   public void setEnd(int end) {
/* 48 */     this.end = end;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 53 */     return "ActivityBagEntity{playerId=" + this.playerId + ", itemId=" + this.itemId + ", num=" + this.num + ", end=" + this.end + '}';
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Object mapKey() {
/* 63 */     return Integer.valueOf(this.itemId);
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\app\activitybag\ActivityBagEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */