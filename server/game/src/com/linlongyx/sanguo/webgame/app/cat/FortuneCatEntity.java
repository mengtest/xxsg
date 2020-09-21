/*    */ package com.linlongyx.sanguo.webgame.app.cat;
/*    */ 
/*    */ import com.linlongyx.core.framework.dao.annotation.Table;
/*    */ import com.linlongyx.core.framework.dao.annotation.TableField;
/*    */ import com.linlongyx.core.framework.dao.entity.IMapEntity;
/*    */ 
/*    */ 
/*    */ @Table(tableName = "tb_cat", prefix = "cat_%serverId_%playerId", isPlayerIdKey = true, keyField = "id")
/*    */ public class FortuneCatEntity
/*    */   implements IMapEntity
/*    */ {
/*    */   @TableField(isKey = true)
/*    */   private final transient long playerId;
/*    */   @TableField(isKey = true)
/*    */   private final transient int id;
/*    */   private int count;
/*    */   private boolean open;
/*    */   private int maxCCY;
/*    */   private int minCCY;
/*    */   private long actCharge;
/*    */   
/*    */   public FortuneCatEntity(long playerId, int id) {
/* 23 */     this.playerId = playerId;
/* 24 */     this.id = id;
/*    */   }
/*    */   
/*    */   public long getPlayerId() {
/* 28 */     return this.playerId;
/*    */   }
/*    */   
/*    */   public int getId() {
/* 32 */     return this.id;
/*    */   }
/*    */   
/*    */   public int getCount() {
/* 36 */     return this.count;
/*    */   }
/*    */   
/*    */   public void setCount(int count) {
/* 40 */     this.count = count;
/*    */   }
/*    */   
/*    */   public boolean isOpen() {
/* 44 */     return this.open;
/*    */   }
/*    */   
/*    */   public void setOpen(boolean open) {
/* 48 */     this.open = open;
/*    */   }
/*    */   
/*    */   public int getMaxCCY() {
/* 52 */     return this.maxCCY;
/*    */   }
/*    */   
/*    */   public void setMaxCCY(int maxCCY) {
/* 56 */     this.maxCCY = maxCCY;
/*    */   }
/*    */   
/*    */   public int getMinCCY() {
/* 60 */     return this.minCCY;
/*    */   }
/*    */   
/*    */   public void setMinCCY(int minCCY) {
/* 64 */     this.minCCY = minCCY;
/*    */   }
/*    */   
/*    */   public long getActCharge() {
/* 68 */     return this.actCharge;
/*    */   }
/*    */   
/*    */   public void setActCharge(long actCharge) {
/* 72 */     this.actCharge = actCharge;
/*    */   }
/*    */ 
/*    */   
/*    */   public Object mapKey() {
/* 77 */     return Integer.valueOf(getId());
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 82 */     return "FortuneCatEntity{playerId=" + this.playerId + ", id=" + this.id + ", count=" + this.count + ", open=" + this.open + ", maxCCY=" + this.maxCCY + ", minCCY=" + this.minCCY + ", actCharge=" + this.actCharge + '}';
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\app\cat\FortuneCatEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */