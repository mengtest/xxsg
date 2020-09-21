/*     */ package com.linlongyx.sanguo.webgame.common.fight.fighter;
/*     */ 
/*     */ import com.linlongyx.sanguo.webgame.common.fight.IFight;
/*     */ import com.linlongyx.sanguo.webgame.common.fight.attribute.AttributeType;
/*     */ import com.linlongyx.sanguo.webgame.common.fight.buff.AbstractBuff;
/*     */ import com.linlongyx.sanguo.webgame.common.fight.skill.Skill;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.AttrBean;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.MonsterSoulsBean;
/*     */ import com.linlongyx.sanguo.webgame.log.LogUtil;
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
/*  24 */     this.monsterSoulsBean = (MonsterSoulsBean)JsonTableService.getJsonData(id, MonsterSoulsBean.class);
/*     */     
/*  26 */     this.id = id;
/*  27 */     this.type = 5;
/*  28 */     this.pos = 0;
/*  29 */     this.fury = 0;
/*  30 */     this.isAuto = true;
/*  31 */     this.pid = id;
/*  32 */     this.level = 1;
/*     */ 
/*     */     
/*  35 */     this.hurtType = (byte)this.monsterSoulsBean.getHurtType();
/*     */ 
/*     */     
/*  38 */     for (AttrBean attrBean : this.monsterSoulsBean.getAttr()) {
/*  39 */       if (attrBean.getId() < AttributeType.BASE_ATTR_END.getType()) {
/*  40 */         this.baseAttrs[attrBean.getId()] = attrBean.getNum();
/*     */       }
/*  42 */       if (attrBean.getId() < AttributeType.ATTRIB_TYPE_END.getType()) {
/*  43 */         this.attrs[attrBean.getId()] = attrBean.getNum();
/*     */       }
/*     */     } 
/*     */     
/*  47 */     this.cmmSkill = new Skill(this.monsterSoulsBean.getSkill(), 1);
/*  48 */     this.furySkill = new Skill(this.monsterSoulsBean.getHotSkill(), 1);
/*  49 */     this.debutSkill = new Skill(this.monsterSoulsBean.getDebutSkill(), 1);
/*     */     
/*  51 */     this.fighterData.id = id;
/*  52 */     this.fighterData.type = this.type;
/*  53 */     this.fighterData.quality = this.monsterSoulsBean.getQuality();
/*  54 */     this.fighterData.attack = this.baseAttrs[AttributeType.ATTACK.getType()];
/*  55 */     this.fighterData.magDef = this.baseAttrs[AttributeType.MAG_DEF.getType()];
/*  56 */     this.fighterData.phyDef = this.baseAttrs[AttributeType.PHY_DEF.getType()];
/*  57 */     this.fighterData.fury = (byte)(int)this.attrs[AttributeType.INIT_FURY.getType()];
/*  58 */     this.fighterData.hp = this.baseAttrs[AttributeType.HP.getType()];
/*  59 */     this.fighterData.maxHp = this.baseAttrs[AttributeType.HP.getType()];
/*  60 */     this.maxHp = this.baseAttrs[AttributeType.HP.getType()];
/*  61 */     this.fury = (byte)(int)this.attrs[AttributeType.INIT_FURY.getType()];
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int auto(IFight fight) {
/*  67 */     Skill skill = this.cmmSkill;
/*  68 */     if (canUseFurySkill()) {
/*  69 */       skill = this.furySkill;
/*     */     }
/*     */     
/*  72 */     int ret = useSkill(fight, skill);
/*  73 */     return ret;
/*     */   }
/*     */   
/*     */   public void replace(IFight fight, IFighter target) {
/*  77 */     this.pos = target.getPos();
/*  78 */     this.side = target.getSide();
/*  79 */     (getFighterData()).pos = target.getPos();
/*  80 */     (getFighterData()).guid = target.getGuid();
/*     */ 
/*     */     
/*  83 */     for (AbstractBuff buff : target.getBuffList()) {
/*  84 */       if (buff.getBean().getInherit() == 1) {
/*  85 */         if (buff.getOwner() == target) {
/*  86 */           buff.setOwner(this);
/*     */         }
/*  88 */         if (buff.getMaster() == target) {
/*  89 */           buff.setMaster(this);
/*     */         }
/*  91 */         getBuffList().add(buff);
/*     */       } 
/*     */     } 
/*     */     
/*  95 */     LogUtil.errorLog(new Object[] { "monster candidate hp before:", Long.valueOf(getHP()), Long.valueOf(getMaxHp()) });
/*  96 */     fight.getSide(target.getSide()).getCurGroup().replaceFighter(this, target);
/*  97 */     useSkill(fight, this.debutSkill);
/*  98 */     LogUtil.errorLog(new Object[] { "monster candidate hp after:", Long.valueOf(getHP()), Long.valueOf(getMaxHp()) });
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
/* 110 */     return (byte)this.monsterSoulsBean.getFury();
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\common\fight\fighter\MonsterCandidateFighter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */