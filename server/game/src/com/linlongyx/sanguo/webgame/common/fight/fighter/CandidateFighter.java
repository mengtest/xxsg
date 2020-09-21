/*     */ package com.linlongyx.sanguo.webgame.common.fight.fighter;
/*     */ 
/*     */ import com.linlongyx.sanguo.webgame.common.fight.IFight;
/*     */ import com.linlongyx.sanguo.webgame.common.fight.attribute.AttributeType;
/*     */ import com.linlongyx.sanguo.webgame.common.fight.buff.AbstractBuff;
/*     */ import com.linlongyx.sanguo.webgame.common.fight.skill.Skill;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.SoulsSetBean;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.SoulsStarBean;
/*     */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CandidateFighter
/*     */   extends AbstractFighter
/*     */   implements IFighter
/*     */ {
/*     */   private SoulsSetBean soulsSetBean;
/*     */   
/*     */   public CandidateFighter(int id, int star, long[] baseAttrs, long[] attrs) {
/*  23 */     this.id = id;
/*  24 */     this.type = 5;
/*  25 */     this.pos = 0;
/*  26 */     this.fury = 0;
/*  27 */     this.isAuto = true;
/*  28 */     this.pid = id;
/*  29 */     this.level = (short)star;
/*  30 */     this.attrs = attrs;
/*  31 */     this.baseAttrs = baseAttrs;
/*     */     
/*  33 */     this.soulsSetBean = (SoulsSetBean)JsonTableService.getJsonData((int)this.id, SoulsSetBean.class);
/*  34 */     this.hurtType = (byte)this.soulsSetBean.getHurtType();
/*     */     
/*  36 */     SoulsStarBean soulsStarBean = (SoulsStarBean)JsonTableService.getJsonData(id, SoulsStarBean.class);
/*     */     
/*  38 */     SoulsStarBean.StarBean starBean = (SoulsStarBean.StarBean)soulsStarBean.getStar().get(Integer.valueOf(star));
/*     */     
/*  40 */     this.cmmSkill = new Skill(this.soulsSetBean.getSkill(), 1);
/*  41 */     this.furySkill = new Skill(starBean.getHotSkill(), 1);
/*  42 */     this.debutSkill = new Skill(starBean.getDebutSkill(), 1);
/*     */     
/*  44 */     this.fighterData.id = id;
/*  45 */     this.fighterData.type = this.type;
/*  46 */     this.fighterData.quality = this.soulsSetBean.getQuality();
/*  47 */     this.fighterData.attack = this.baseAttrs[AttributeType.ATTACK.getType()];
/*  48 */     this.fighterData.magDef = this.baseAttrs[AttributeType.MAG_DEF.getType()];
/*  49 */     this.fighterData.phyDef = this.baseAttrs[AttributeType.PHY_DEF.getType()];
/*  50 */     this.fighterData.fury = (byte)(int)this.attrs[AttributeType.INIT_FURY.getType()];
/*  51 */     this.fighterData.hp = this.baseAttrs[AttributeType.HP.getType()];
/*  52 */     this.fighterData.maxHp = this.baseAttrs[AttributeType.HP.getType()];
/*  53 */     this.maxHp = this.baseAttrs[AttributeType.HP.getType()];
/*  54 */     this.fury = (byte)(int)this.attrs[AttributeType.INIT_FURY.getType()];
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int auto(IFight fight) {
/*  60 */     Skill skill = this.cmmSkill;
/*  61 */     if (canUseFurySkill()) {
/*  62 */       skill = this.furySkill;
/*     */     }
/*     */     
/*  65 */     int ret = useSkill(fight, skill);
/*  66 */     return ret;
/*     */   }
/*     */   
/*     */   public void replace(IFight fight, IFighter target) {
/*  70 */     this.pos = target.getPos();
/*  71 */     this.side = target.getSide();
/*  72 */     (getFighterData()).pos = target.getPos();
/*  73 */     (getFighterData()).guid = target.getGuid();
/*     */ 
/*     */     
/*  76 */     for (AbstractBuff buff : target.getBuffList()) {
/*  77 */       if (buff.getBean().getInherit() == 1) {
/*  78 */         if (buff.getOwner() == target) {
/*  79 */           buff.setOwner(this);
/*     */         }
/*  81 */         if (buff.getMaster() == target) {
/*  82 */           buff.setMaster(this);
/*     */         }
/*  84 */         getBuffList().add(buff);
/*     */       } 
/*     */     } 
/*     */     
/*  88 */     fight.getSide(target.getSide()).getCurGroup().replaceFighter(this, target);
/*  89 */     useSkill(fight, this.debutSkill);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public byte getMaxFury() {
/* 101 */     return (byte)this.soulsSetBean.getFury();
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\common\fight\fighter\CandidateFighter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */