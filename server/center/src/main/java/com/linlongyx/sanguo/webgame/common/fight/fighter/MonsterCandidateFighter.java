/*     */ package com.linlongyx.sanguo.webgame.common.fight.fighter;
/*     */ 
/*     */ import com.linlongyx.sanguo.webgame.common.fight.IFight;
/*     */ import com.linlongyx.sanguo.webgame.common.fight.attribute.AttributeType;
/*     */ import com.linlongyx.sanguo.webgame.common.fight.buff.AbstractBuff;
/*     */ import com.linlongyx.sanguo.webgame.common.fight.skill.Skill;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.AttrBean;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.MonsterSoulsBean;
/*     */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MonsterCandidateFighter
/*     */   extends AbstractFighter
/*     */   implements IFighter
/*     */ {
/*     */   private MonsterSoulsBean monsterSoulsBean;
/*     */   
/*     */   public MonsterCandidateFighter(int id) {
/*  23 */     this.monsterSoulsBean = (MonsterSoulsBean)JsonTableService.getJsonData(id, MonsterSoulsBean.class);
/*     */     
/*  25 */     this.id = id;
/*  26 */     this.type = 5;
/*  27 */     this.pos = 0;
/*  28 */     this.fury = 0;
/*  29 */     this.isAuto = true;
/*  30 */     this.pid = id;
/*  31 */     this.level = 1;
/*     */ 
/*     */     
/*  34 */     this.hurtType = (byte)this.monsterSoulsBean.getHurtType();
/*     */ 
/*     */     
/*  37 */     for (AttrBean attrBean : this.monsterSoulsBean.getAttr()) {
/*  38 */       if (attrBean.getId() < AttributeType.BASE_ATTR_END.getType()) {
/*  39 */         this.baseAttrs[attrBean.getId()] = attrBean.getNum();
/*     */       }
/*  41 */       if (attrBean.getId() < AttributeType.ATTRIB_TYPE_END.getType()) {
/*  42 */         this.attrs[attrBean.getId()] = attrBean.getNum();
/*     */       }
/*     */     } 
/*     */     
/*  46 */     this.cmmSkill = new Skill(this.monsterSoulsBean.getSkill(), 1);
/*  47 */     this.furySkill = new Skill(this.monsterSoulsBean.getHotSkill(), 1);
/*  48 */     this.debutSkill = new Skill(this.monsterSoulsBean.getDebutSkill(), 1);
/*     */     
/*  50 */     this.fighterData.id = id;
/*  51 */     this.fighterData.type = this.type;
/*  52 */     this.fighterData.quality = this.monsterSoulsBean.getQuality();
/*  53 */     this.fighterData.attack = this.baseAttrs[AttributeType.ATTACK.getType()];
/*  54 */     this.fighterData.magDef = this.baseAttrs[AttributeType.MAG_DEF.getType()];
/*  55 */     this.fighterData.phyDef = this.baseAttrs[AttributeType.PHY_DEF.getType()];
/*  56 */     this.fighterData.fury = (byte)(int)this.attrs[AttributeType.INIT_FURY.getType()];
/*  57 */     this.fighterData.hp = this.baseAttrs[AttributeType.HP.getType()];
/*  58 */     this.fighterData.maxHp = this.baseAttrs[AttributeType.HP.getType()];
/*  59 */     this.maxHp = this.baseAttrs[AttributeType.HP.getType()];
/*  60 */     this.fury = (byte)(int)this.attrs[AttributeType.INIT_FURY.getType()];
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int auto(IFight fight) {
/*  66 */     Skill skill = this.cmmSkill;
/*  67 */     if (canUseFurySkill()) {
/*  68 */       skill = this.furySkill;
/*     */     }
/*     */     
/*  71 */     int ret = useSkill(fight, skill);
/*  72 */     return ret;
/*     */   }
/*     */   
/*     */   public void replace(IFight fight, IFighter target) {
/*  76 */     this.pos = target.getPos();
/*  77 */     this.side = target.getSide();
/*  78 */     (getFighterData()).pos = target.getPos();
/*  79 */     (getFighterData()).guid = target.getGuid();
/*     */ 
/*     */     
/*  82 */     for (AbstractBuff buff : target.getBuffList()) {
/*  83 */       if (buff.getOwner() == target) {
/*  84 */         buff.setOwner(this);
/*     */       }
/*  86 */       if (buff.getMaster() == target) {
/*  87 */         buff.setMaster(this);
/*     */       }
/*  89 */       if (buff.getBean().getInherit() == 1) {
/*  90 */         getBuffList().add(buff);
/*     */       }
/*     */     } 
/*     */     
/*  94 */     fight.getSide(target.getSide()).getCurGroup().replaceFighter(this, target);
/*  95 */     useSkill(fight, this.debutSkill);
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
/* 107 */     return (byte)this.monsterSoulsBean.getFury();
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\sanguo\webgame\common\fight\fighter\MonsterCandidateFighter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */