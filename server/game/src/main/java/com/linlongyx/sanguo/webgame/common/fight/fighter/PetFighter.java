/*    */ package com.linlongyx.sanguo.webgame.common.fight.fighter;
/*    */ 
/*    */ import com.linlongyx.sanguo.webgame.app.player.PlayerComponent;
/*    */ import com.linlongyx.sanguo.webgame.common.fight.attribute.AttributeType;
/*    */ import com.linlongyx.sanguo.webgame.common.fight.skill.Skill;
/*    */ import com.linlongyx.sanguo.webgame.config.bean.AttrBean;
/*    */ import com.linlongyx.sanguo.webgame.config.bean.MonsterPetBean;
/*    */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PetFighter
/*    */   extends PlayerFighter
/*    */ {
/*    */   public PetFighter() {}
/*    */   
/*    */   public PetFighter(PlayerComponent component, byte pos) {
/* 22 */     super(component, pos);
/* 23 */     this.type = 3;
/* 24 */     this.fighterData.type = this.type;
/*    */   }
/*    */   
/*    */   public PetFighter(CommonFighterData commonFighterData) {
/* 28 */     super(commonFighterData, (byte)0);
/* 29 */     this.type = 3;
/* 30 */     this.fighterData.type = this.type;
/*    */   }
/*    */   
/*    */   public PetFighter(CommonFighterData commonFighterData, byte pos) {
/* 34 */     super(commonFighterData, pos);
/* 35 */     this.type = 3;
/* 36 */     this.fighterData.type = this.type;
/*    */   }
/*    */ 
/*    */   
/*    */   public PetFighter(int petId) {
/* 41 */     MonsterPetBean monsterPetBean = (MonsterPetBean)JsonTableService.getJsonData(petId, MonsterPetBean.class);
/* 42 */     if (monsterPetBean != null) {
/* 43 */       this.id = petId;
/* 44 */       this.type = 3;
/* 45 */       this.hurtType = (byte)monsterPetBean.getHurtType();
/* 46 */       this.pos = 0;
/* 47 */       this.isAuto = true;
/* 48 */       this.pid = -1L;
/*    */       
/* 50 */       addSKill(new Skill(monsterPetBean.getPetSkill(), 1));
/*    */ 
/*    */       
/* 53 */       this.fighterData.id = this.id;
/* 54 */       this.fighterData.fashionId = 0;
/* 55 */       this.fighterData.type = this.type;
/* 56 */       this.fighterData.level = (short)monsterPetBean.getStar();
/* 57 */       this.fighterData.quality = monsterPetBean.getQuality();
/* 58 */       for (AttrBean attr : monsterPetBean.getAttr()) {
/* 59 */         if (attr.getId() < AttributeType.BASE_ATTR_END.getType())
/* 60 */           this.baseAttrs[attr.getId()] = attr.getNum(); 
/* 61 */         if (attr.getId() < AttributeType.ATTRIB_TYPE_END.getType()) {
/* 62 */           this.attrs[attr.getId()] = attr.getNum();
/*    */         }
/*    */       } 
/* 65 */       this.maxHp = this.baseAttrs[AttributeType.HP.getType()];
/*    */       
/* 67 */       this.fury = (byte)(int)this.attrs[AttributeType.INIT_FURY.getType()];
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void initSkill(Skill petSkill) {
/* 73 */     setCmmSkill(petSkill);
/* 74 */     setFurySkill(null);
/* 75 */     setFitSkill(null);
/*    */   }
/*    */ 
/*    */   
/*    */   public void setId(long id) {
/* 80 */     this.id = id;
/* 81 */     this.fighterData.id = id;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\common\fight\fighter\PetFighter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */