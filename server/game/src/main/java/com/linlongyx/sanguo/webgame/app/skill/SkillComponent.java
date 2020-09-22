/*    */ package com.linlongyx.sanguo.webgame.app.skill;
/*    */ 
/*    */ import com.linlongyx.core.framework.dao.entity.IEntity;
/*    */ import com.linlongyx.core.framework.dao.entity.IMapEntity;
/*    */ import com.linlongyx.core.framework.logic.AbstractMapComponent;
/*    */ import com.linlongyx.sanguo.webgame.common.fight.skill.Skill;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.KeyValue;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SkillComponent
/*    */   extends AbstractMapComponent<SkillEntity>
/*    */ {
/*    */   public SkillComponent(long playerId) {
/* 17 */     super(SkillEntity.class, playerId);
/*    */   }
/*    */   
/*    */   public SkillEntity getSkillEntity(int skillId) {
/* 21 */     return (SkillEntity)getEntity(String.valueOf(skillId));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int getHighLevel() {
/* 30 */     int result = 0;
/* 31 */     for (IMapEntity iMapEntity : getEntityMap().values()) {
/* 32 */       SkillEntity skillEntity = (SkillEntity)iMapEntity;
/* 33 */       if (skillEntity.getLevel() > result) {
/* 34 */         result = skillEntity.getLevel();
/*    */       }
/*    */     } 
/* 37 */     return result;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public KeyValue getStronger(int level) {
/* 46 */     KeyValue keyValue = new KeyValue();
/* 47 */     keyValue.value = (getEntityMap().size() * level);
/* 48 */     for (IMapEntity iMapEntity : getEntityMap().values()) {
/* 49 */       SkillEntity skillEntity = (SkillEntity)iMapEntity;
/* 50 */       keyValue.key += skillEntity.getLevel();
/*    */     } 
/* 52 */     return keyValue;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void addSkillEntity(long playerId, int skillId) {
/* 61 */     if (getSkillEntity(skillId) != null)
/* 62 */       return;  SkillEntity skillEntity = new SkillEntity(playerId, skillId);
/* 63 */     addEntity((IEntity)skillEntity);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public List<Skill> getSkills() {
/* 70 */     List<Skill> skillList = new ArrayList<>();
/* 71 */     for (IMapEntity entity : getEntityMap().values()) {
/* 72 */       SkillEntity skillEntity = (SkillEntity)entity;
/* 73 */       Skill skill = new Skill(skillEntity.getSkillId(), skillEntity.getLevel());
/* 74 */       skillList.add(skill);
/*    */     } 
/* 76 */     return skillList;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getType() {
/* 81 */     return getClass().getSimpleName();
/*    */   }
/*    */ 
/*    */   
/*    */   public void build(long playerId) {
/* 86 */     this.playerId = playerId;
/*    */   }
/*    */   
/*    */   public void updateLevelToDB(SkillEntity skillEntity) {
/* 90 */     getProxy().setUpdate(String.valueOf(skillEntity.getSkillId()), "level");
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\app\skill\SkillComponent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */