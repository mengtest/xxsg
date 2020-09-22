/*    */ package com.linlongyx.sanguo.webgame.common.fight.fighter;
/*    */ 
/*    */ import com.linlongyx.sanguo.webgame.common.fight.IFight;
/*    */ import com.linlongyx.sanguo.webgame.common.fight.attribute.AttributeType;
/*    */ import com.linlongyx.sanguo.webgame.common.fight.buff.AbstractBuff;
/*    */ import com.linlongyx.sanguo.webgame.common.fight.skill.Skill;
/*    */ import com.linlongyx.sanguo.webgame.config.bean.SoulsSetBean;
/*    */ import com.linlongyx.sanguo.webgame.config.bean.SoulsStarBean;
/*    */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CandidateFighter
/*    */   extends AbstractFighter
/*    */   implements IFighter
/*    */ {
/*    */   private SoulsSetBean soulsSetBean;
/*    */   
/*    */   public CandidateFighter(int id, int star, long[] baseAttrs, long[] attrs) {
/* 20 */     this.id = id;
/* 21 */     this.type = 5;
/* 22 */     this.pos = 0;
/* 23 */     this.fury = 0;
/* 24 */     this.isAuto = true;
/* 25 */     this.pid = id;
/* 26 */     this.level = (short)star;
/* 27 */     this.baseAttrs = baseAttrs;
/* 28 */     this.attrs = attrs;
/*    */     
/* 30 */     this.soulsSetBean = (SoulsSetBean)JsonTableService.getJsonData((int)this.id, SoulsSetBean.class);
/* 31 */     this.hurtType = (byte)this.soulsSetBean.getHurtType();
/*    */     
/* 33 */     SoulsStarBean soulsStarBean = (SoulsStarBean)JsonTableService.getJsonData(id, SoulsStarBean.class);
/*    */     
/* 35 */     SoulsStarBean.StarBean starBean = (SoulsStarBean.StarBean)soulsStarBean.getStar().get(Integer.valueOf(star));
/*    */     
/* 37 */     this.cmmSkill = new Skill(this.soulsSetBean.getSkill(), 1);
/* 38 */     this.furySkill = new Skill(starBean.getHotSkill(), 1);
/* 39 */     this.debutSkill = new Skill(starBean.getDebutSkill(), 1);
/*    */     
/* 41 */     this.fighterData.id = id;
/* 42 */     this.fighterData.type = this.type;
/* 43 */     this.fighterData.quality = this.soulsSetBean.getQuality();
/* 44 */     this.fighterData.attack = this.baseAttrs[AttributeType.ATTACK.getType()];
/* 45 */     this.fighterData.magDef = this.baseAttrs[AttributeType.MAG_DEF.getType()];
/* 46 */     this.fighterData.phyDef = this.baseAttrs[AttributeType.PHY_DEF.getType()];
/* 47 */     this.fighterData.fury = (byte)(int)this.attrs[AttributeType.INIT_FURY.getType()];
/* 48 */     this.fighterData.hp = this.baseAttrs[AttributeType.HP.getType()];
/* 49 */     this.fighterData.maxHp = this.baseAttrs[AttributeType.HP.getType()];
/* 50 */     this.maxHp = this.baseAttrs[AttributeType.HP.getType()];
/* 51 */     this.fury = (byte)(int)this.attrs[AttributeType.INIT_FURY.getType()];
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int auto(IFight fight) {
/* 57 */     Skill skill = this.cmmSkill;
/* 58 */     if (canUseFurySkill()) {
/* 59 */       skill = this.furySkill;
/*    */     }
/*    */     
/* 62 */     int ret = useSkill(fight, skill);
/* 63 */     return ret;
/*    */   }
/*    */   
/*    */   public void replace(IFight fight, IFighter target) {
/* 67 */     this.pos = target.getPos();
/* 68 */     this.side = target.getSide();
/* 69 */     (getFighterData()).pos = target.getPos();
/* 70 */     (getFighterData()).guid = target.getGuid();
/*    */ 
/*    */     
/* 73 */     for (AbstractBuff buff : target.getBuffList()) {
/* 74 */       if (buff.getOwner() == target) {
/* 75 */         buff.setOwner(this);
/*    */       }
/* 77 */       if (buff.getMaster() == target) {
/* 78 */         buff.setMaster(this);
/*    */       }
/* 80 */       if (buff.getBean().getInherit() == 1) {
/* 81 */         getBuffList().add(buff);
/*    */       }
/*    */     } 
/*    */     
/* 85 */     fight.getSide(target.getSide()).getCurGroup().replaceFighter(this, target);
/* 86 */     useSkill(fight, this.debutSkill);
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
/*    */   public byte getMaxFury() {
/* 98 */     return (byte)this.soulsSetBean.getFury();
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\sanguo\webgame\common\fight\fighter\CandidateFighter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */