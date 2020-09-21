/*    */ package com.linlongyx.sanguo.webgame.app.fashion;
/*    */ 
/*    */ import com.linlongyx.core.framework.dao.annotation.Table;
/*    */ import com.linlongyx.core.framework.dao.annotation.TableField;
/*    */ import com.linlongyx.core.framework.dao.entity.IMapEntity;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Table(tableName = "tb_fashion", prefix = "fashion_%serverId_%playerId", keyField = "fashionId", isPlayerIdKey = true)
/*    */ public class FashionEntity
/*    */   implements IMapEntity
/*    */ {
/*    */   @TableField(isKey = true)
/*    */   private final transient long playerId;
/*    */   @TableField(isKey = true)
/*    */   private final transient int fashionId;
/*    */   private int level;
/*    */   private int expired;
/*    */   
/*    */   public FashionEntity(long playerId, int fashionId) {
/* 23 */     this.playerId = playerId;
/* 24 */     this.fashionId = fashionId;
/*    */   }
/*    */   
/*    */   public int getFashionId() {
/* 28 */     return this.fashionId;
/*    */   }
/*    */   
/*    */   public long getPlayerId() {
/* 32 */     return this.playerId;
/*    */   }
/*    */   
/*    */   public int getLevel() {
/* 36 */     return this.level;
/*    */   }
/*    */   
/*    */   public void setLevel(int level) {
/* 40 */     this.level = level;
/*    */   }
/*    */   
/*    */   public int getExpired() {
/* 44 */     return this.expired;
/*    */   }
/*    */   
/*    */   public void setExpired(int expired) {
/* 48 */     this.expired = expired;
/*    */   }
/*    */ 
/*    */   
/*    */   public Object mapKey() {
/* 53 */     return Integer.valueOf(this.fashionId);
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\app\fashion\FashionEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */