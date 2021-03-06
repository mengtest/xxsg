/*    */ package com.linlongyx.sanguo.webgame.app.stage;
/*    */ 
/*    */ import com.linlongyx.core.framework.dao.annotation.Table;
/*    */ import com.linlongyx.core.framework.dao.annotation.TableField;
/*    */ import com.linlongyx.core.framework.dao.entity.IMapEntity;
/*    */ 
/*    */ 
/*    */ 
/*    */ @Table(tableName = "tb_stage", prefix = "stage_%serverId_%playerId", isPlayerIdKey = true, keyField = "id")
/*    */ public class StageEntity
/*    */   implements IMapEntity
/*    */ {
/*    */   @TableField(isKey = true)
/*    */   private final transient long playerId;
/*    */   @TableField(isKey = true)
/*    */   private final transient int id;
/*    */   private int level;
/*    */   private int star;
/*    */   private int battle;
/*    */   private int exp;
/*    */   private long fightValue;
/*    */   
/*    */   public StageEntity(long playerId, int id) {
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
/*    */   public int getLevel() {
/* 37 */     return this.level;
/*    */   }
/*    */   
/*    */   public void setLevel(int level) {
/* 41 */     this.level = level;
/*    */   }
/*    */   
/*    */   public int getStar() {
/* 45 */     return this.star;
/*    */   }
/*    */   
/*    */   public void setStar(int star) {
/* 49 */     this.star = star;
/*    */   }
/*    */   
/*    */   public int getBattle() {
/* 53 */     return this.battle;
/*    */   }
/*    */   
/*    */   public void setBattle(int battle) {
/* 57 */     this.battle = battle;
/*    */   }
/*    */   
/*    */   public int getExp() {
/* 61 */     return this.exp;
/*    */   }
/*    */   
/*    */   public void setExp(int exp) {
/* 65 */     this.exp = exp;
/*    */   }
/*    */   
/*    */   public long getFightValue() {
/* 69 */     return this.fightValue;
/*    */   }
/*    */   
/*    */   public void setFightValue(long fightValue) {
/* 73 */     this.fightValue = fightValue;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 78 */     return "StageEntity{playerId=" + this.playerId + "id=" + this.id + "level=" + this.level + "star=" + this.star + "battle=" + this.battle + "exp=" + this.exp + "fightValue=" + this.fightValue + '}';
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
/*    */   
/*    */   public Object mapKey() {
/* 91 */     return Integer.valueOf(getId());
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\app\stage\StageEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */