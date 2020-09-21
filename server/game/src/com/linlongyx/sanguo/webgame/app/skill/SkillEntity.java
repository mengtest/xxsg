/*    */ package com.linlongyx.sanguo.webgame.app.skill;
/*    */ 
/*    */ import com.linlongyx.core.framework.dao.annotation.Table;
/*    */ import com.linlongyx.core.framework.dao.annotation.TableField;
/*    */ import com.linlongyx.core.framework.dao.entity.IMapEntity;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Table(tableName = "tb_skill", prefix = "skill_%serverId_%playerId", keyField = "skillId", isPlayerIdKey = true)
/*    */ public class SkillEntity
/*    */   implements IMapEntity
/*    */ {
/*    */   @TableField(isKey = true)
/*    */   private final transient long playerId;
/*    */   @TableField(isKey = true)
/*    */   private final transient int skillId;
/*    */   private int level;
/*    */   
/*    */   public SkillEntity(long playerId, int skillId) {
/* 21 */     this.playerId = playerId;
/* 22 */     this.skillId = skillId;
/*    */   }
/*    */   
/*    */   public int getLevel() {
/* 26 */     return this.level;
/*    */   }
/*    */   
/*    */   public void setLevel(int level) {
/* 30 */     this.level = level;
/*    */   }
/*    */   
/*    */   public int getSkillId() {
/* 34 */     return this.skillId;
/*    */   }
/*    */   
/*    */   public long getPlayerId() {
/* 38 */     return this.playerId;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 43 */     return "SkillEntity{playerId=" + this.playerId + ", skillId=" + this.skillId + ", level=" + this.level + '}';
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Object mapKey() {
/* 52 */     return Integer.valueOf(this.skillId);
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\app\skill\SkillEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */