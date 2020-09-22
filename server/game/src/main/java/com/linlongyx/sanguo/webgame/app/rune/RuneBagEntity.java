/*    */ package com.linlongyx.sanguo.webgame.app.rune;
/*    */ 
/*    */ import com.linlongyx.core.framework.dao.annotation.Table;
/*    */ import com.linlongyx.core.framework.dao.annotation.TableField;
/*    */ import com.linlongyx.core.framework.dao.entity.IMapEntity;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Table(tableName = "tb_rune_bag", prefix = "rune_bag_%serverId_%playerId", isPlayerIdKey = true, keyField = "mid")
/*    */ public class RuneBagEntity
/*    */   implements IMapEntity
/*    */ {
/*    */   @TableField(isKey = true)
/*    */   private final transient long playerId;
/*    */   @TableField(isKey = true)
/*    */   private final transient long mid;
/*    */   private int itemId;
/*    */   private int level;
/*    */   private int hole;
/*    */   
/*    */   public RuneBagEntity(long playerId, long mid) {
/* 26 */     this.playerId = playerId;
/* 27 */     this.mid = mid;
/*    */   }
/*    */   
/*    */   public long getPlayerId() {
/* 31 */     return this.playerId;
/*    */   }
/*    */   
/*    */   public long getMid() {
/* 35 */     return this.mid;
/*    */   }
/*    */   
/*    */   public int getItemId() {
/* 39 */     return this.itemId;
/*    */   }
/*    */   
/*    */   public void setItemId(int itemId) {
/* 43 */     this.itemId = itemId;
/*    */   }
/*    */   
/*    */   public int getLevel() {
/* 47 */     return this.level;
/*    */   }
/*    */   
/*    */   public void setLevel(int level) {
/* 51 */     this.level = level;
/*    */   }
/*    */   
/*    */   public int getHole() {
/* 55 */     return this.hole;
/*    */   }
/*    */   
/*    */   public void setHole(int hole) {
/* 59 */     this.hole = hole;
/*    */   }
/*    */ 
/*    */   
/*    */   public Object mapKey() {
/* 64 */     return Long.valueOf(this.mid);
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 69 */     return "RuneBagEntity{playerId=" + this.playerId + ", mid=" + this.mid + ", itemId=" + this.itemId + ", level=" + this.level + ", hole=" + this.hole + '}';
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\app\rune\RuneBagEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */