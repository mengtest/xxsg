/*    */ package com.linlongyx.sanguo.webgame.app.souls;
/*    */ 
/*    */ import com.linlongyx.core.framework.dao.annotation.Table;
/*    */ import com.linlongyx.core.framework.dao.annotation.TableField;
/*    */ import com.linlongyx.core.framework.dao.entity.IMapEntity;
/*    */ 
/*    */ 
/*    */ 
/*    */ @Table(tableName = "tb_souls", prefix = "souls_%serverId_%playerId", isPlayerIdKey = true, keyField = "id")
/*    */ public class SoulsEntity
/*    */   implements IMapEntity
/*    */ {
/*    */   @TableField(isKey = true)
/*    */   private final transient long playerId;
/*    */   @TableField(isKey = true)
/*    */   private final transient int id;
/*    */   private int level;
/*    */   private int star;
/*    */   private int exp;
/*    */   private long fightValue;
/*    */   
/*    */   public SoulsEntity(long playerId, int id) {
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
/*    */   public int getLevel() {
/* 36 */     return this.level;
/*    */   }
/*    */   
/*    */   public void setLevel(int level) {
/* 40 */     this.level = level;
/*    */   }
/*    */   
/*    */   public int getStar() {
/* 44 */     return this.star;
/*    */   }
/*    */   
/*    */   public void setStar(int star) {
/* 48 */     this.star = star;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getExp() {
/* 53 */     return this.exp;
/*    */   }
/*    */   
/*    */   public void setExp(int exp) {
/* 57 */     this.exp = exp;
/*    */   }
/*    */   
/*    */   public long getFightValue() {
/* 61 */     return this.fightValue;
/*    */   }
/*    */   
/*    */   public void setFightValue(long fightValue) {
/* 65 */     this.fightValue = fightValue;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 70 */     return "SoulsEntity{playerId=" + this.playerId + "id=" + this.id + "level=" + this.level + "star=" + this.star + "exp=" + this.exp + "fightValue=" + this.fightValue + '}';
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Object mapKey() {
/* 82 */     return Integer.valueOf(getId());
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\app\souls\SoulsEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */