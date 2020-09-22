/*    */ package com.linlongyx.sanguo.webgame.common.fight.fighter;
/*    */ 
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
/*    */ public class PetFighter
/*    */   extends PlayerFighter
/*    */ {
/*    */   public PetFighter(CommonFighterData commonFighterData, boolean copyAttr) {
/* 18 */     super(commonFighterData, (byte)0, copyAttr);
/* 19 */     this.type = 3;
/* 20 */     this.fighterData.type = this.type;
/*    */   }
/*    */   
/*    */   public PetFighter(CommonFighterData commonFighterData, byte pos, boolean copyAttr) {
/* 24 */     super(commonFighterData, pos, copyAttr);
/* 25 */     this.type = 3;
/* 26 */     this.fighterData.type = this.type;
/*    */   }
/*    */ 
/*    */   
/*    */   public PetFighter(int petId) {
/* 31 */     MonsterPetBean monsterPetBean = (MonsterPetBean)JsonTableService.getJsonData(petId, MonsterPetBean.class);
/* 32 */     if (monsterPetBean != null) {
/* 33 */       this.id = petId;
/* 34 */       this.type = 3;
/* 35 */       this.hurtType = (byte)monsterPetBean.getHurtType();
/* 36 */       this.pos = 0;
/* 37 */       this.isAuto = true;
/* 38 */       this.pid = -1L;
/*    */       
/* 40 */       addSKill(new Skill(monsterPetBean.getPetSkill(), 1));
/*    */ 
/*    */       
/* 43 */       this.fighterData.id = this.id;
/* 44 */       this.fighterData.fashionId = 0;
/* 45 */       this.fighterData.type = this.type;
/* 46 */       this.fighterData.level = (short)monsterPetBean.getStar();
/* 47 */       this.fighterData.quality = monsterPetBean.getQuality();
/* 48 */       for (AttrBean attr : monsterPetBean.getAttr()) {
/* 49 */         if (attr.getId() < AttributeType.BASE_ATTR_END.getType())
/* 50 */           this.baseAttrs[attr.getId()] = attr.getNum(); 
/* 51 */         if (attr.getId() < AttributeType.ATTRIB_TYPE_END.getType()) {
/* 52 */           this.attrs[attr.getId()] = attr.getNum();
/*    */         }
/*    */       } 
/* 55 */       this.maxHp = this.baseAttrs[AttributeType.HP.getType()];
/*    */       
/* 57 */       this.fury = (byte)(int)this.attrs[AttributeType.INIT_FURY.getType()];
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void initSkill(Skill petSkill) {
/* 63 */     setCmmSkill(petSkill);
/* 64 */     setFurySkill(null);
/* 65 */     setFitSkill(null);
/*    */   }
/*    */ 
/*    */   
/*    */   public void setId(long id) {
/* 70 */     this.id = id;
/* 71 */     this.fighterData.id = id;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\sanguo\webgame\common\fight\fighter\PetFighter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */