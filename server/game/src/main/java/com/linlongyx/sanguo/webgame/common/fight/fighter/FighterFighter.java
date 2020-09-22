/*     */ package com.linlongyx.sanguo.webgame.common.fight.fighter;
/*     */ 
/*     */ import com.linlongyx.sanguo.webgame.app.partner.PartnerComponent;
/*     */ import com.linlongyx.sanguo.webgame.common.fight.IFight;
/*     */ import com.linlongyx.sanguo.webgame.common.fight.attribute.AttributeType;
/*     */ import com.linlongyx.sanguo.webgame.common.fight.skill.Skill;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.FighterBean;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.FighterStarBean;
/*     */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*     */ import java.util.Arrays;
/*     */ import java.util.List;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class FighterFighter
/*     */   extends AbstractFighter
/*     */   implements IFighter
/*     */ {
/*     */   private FighterBean fighterBean;
/*     */   private FighterStarBean.LevelBean levelBean;
/*     */   
/*     */   public FighterFighter(PartnerComponent partnerComponent, byte pos, long pid) {
/*  25 */     this.id = partnerComponent.getEntity(pid).getTableId();
/*  26 */     this.type = 1;
/*  27 */     this.pos = pos;
/*  28 */     this.fury = 0;
/*  29 */     this.isAuto = true;
/*  30 */     this.pid = pid;
/*     */     
/*  32 */     this.fighterBean = (FighterBean)JsonTableService.getJsonData((int)this.id, FighterBean.class);
/*  33 */     this.hurtType = (byte)this.fighterBean.getHurtType();
/*  34 */     FighterStarBean fighterStarBean = (FighterStarBean)JsonTableService.getJsonData((int)this.id, FighterStarBean.class);
/*  35 */     this.levelBean = (FighterStarBean.LevelBean)fighterStarBean.getLevel().get(Integer.valueOf(partnerComponent.getEntity(pid).getStars()));
/*     */     
/*  37 */     this.cmmSkill = new Skill(this.fighterBean.getSkill(), 1);
/*  38 */     this.furySkill = new Skill(this.levelBean.getHotSkill(), 1);
/*  39 */     if (this.levelBean.getFitSkill() != 0) {
/*  40 */       this.fitSkill = new Skill(this.levelBean.getFitSkill(), 1);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  48 */     for (int type = 1; type < AttributeType.ATTRIB_TYPE_END.getType(); type++) {
/*     */       
/*  50 */       long val = partnerComponent.getPartnerAttrUp().getAttrByType(type, pid);
/*  51 */       if (type < AttributeType.BASE_ATTR_END.getType())
/*  52 */         this.baseAttrs[type] = val; 
/*  53 */       this.attrs[type] = val;
/*     */     } 
/*     */ 
/*     */     
/*  57 */     this.fighterData.id = this.id;
/*  58 */     this.fighterData.type = this.type;
/*  59 */     this.fighterData.attack = this.baseAttrs[AttributeType.ATTACK.getType()];
/*  60 */     this.fighterData.magDef = this.baseAttrs[AttributeType.MAG_DEF.getType()];
/*  61 */     this.fighterData.phyDef = this.baseAttrs[AttributeType.PHY_DEF.getType()];
/*     */     
/*  63 */     this.maxHp = this.baseAttrs[AttributeType.HP.getType()];
/*  64 */     this.fury = (byte)(int)this.attrs[AttributeType.INIT_FURY.getType()];
/*     */   }
/*     */ 
/*     */   
/*     */   public FighterFighter(CommonFighterData commonFighterData, byte pos, long pid) {
/*  69 */     this.id = commonFighterData.getId();
/*  70 */     this.type = commonFighterData.getType();
/*  71 */     this.pos = pos;
/*  72 */     this.fury = 0;
/*  73 */     this.isAuto = true;
/*  74 */     this.pid = pid;
/*  75 */     this.talisman = commonFighterData.getTalisman();
/*     */     
/*  77 */     this.fighterBean = (FighterBean)JsonTableService.getJsonData((int)this.id, FighterBean.class);
/*  78 */     this.hurtType = (byte)this.fighterBean.getHurtType();
/*  79 */     FighterStarBean fighterStarBean = (FighterStarBean)JsonTableService.getJsonData((int)this.id, FighterStarBean.class);
/*  80 */     this.levelBean = (FighterStarBean.LevelBean)fighterStarBean.getLevel().get(Integer.valueOf(commonFighterData.getLevel()));
/*     */     
/*  82 */     this.cmmSkill = new Skill(this.fighterBean.getSkill(), 1);
/*  83 */     this.furySkill = new Skill(this.levelBean.getHotSkill(), 1);
/*  84 */     if (this.levelBean.getFitSkill() != 0) {
/*  85 */       this.fitSkill = new Skill(this.levelBean.getFitSkill(), 1);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*  90 */     this.baseAttrs = Arrays.copyOf(commonFighterData.getBaseAttrs(), (commonFighterData.getBaseAttrs()).length);
/*  91 */     this.attrs = Arrays.copyOf(commonFighterData.getAttrs(), (commonFighterData.getAttrs()).length);
/*     */     
/*  93 */     this.fighterData.id = this.id;
/*     */     
/*  95 */     this.fighterData.type = this.type;
/*  96 */     this.fighterData.attack = this.baseAttrs[AttributeType.ATTACK.getType()];
/*  97 */     this.fighterData.magDef = this.baseAttrs[AttributeType.MAG_DEF.getType()];
/*  98 */     this.fighterData.phyDef = this.baseAttrs[AttributeType.PHY_DEF.getType()];
/*     */     
/* 100 */     this.maxHp = this.baseAttrs[AttributeType.HP.getType()];
/* 101 */     this.fury = (byte)(int)this.attrs[AttributeType.INIT_FURY.getType()];
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int auto(IFight fight) {
/* 107 */     Skill skill = this.cmmSkill;
/* 108 */     if (canUseFurySkill()) {
/* 109 */       skill = this.furySkill;
/* 110 */       if (this.fitSkill != null && canReleaseFitSkill(fight)) {
/* 111 */         skill = this.fitSkill;
/*     */       }
/*     */     } 
/* 114 */     int ret = useSkill(fight, skill);
/* 115 */     return ret;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean canReleaseFitSkill(IFight fight) {
/* 124 */     if (null == this.furySkill)
/* 125 */       return false; 
/* 126 */     if (this.fury < this.furySkill.getBean().getFuryCost())
/* 127 */       return false; 
/* 128 */     List<IFighter> fighters = fight.getCurFightersExclude(getSide(), true, getGuid());
/* 129 */     if (fighters == null || fighters.isEmpty())
/* 130 */       return false; 
/* 131 */     if (null == this.fighterBean.getFitPartner() || this.fighterBean.getFitPartner().isEmpty()) {
/* 132 */       return false;
/*     */     }
/* 134 */     for (Integer fighterId : this.fighterBean.getFitPartner()) {
/* 135 */       boolean flag = fighters.stream().anyMatch(fighter -> (fighter.getId() == fighterId.intValue()));
/* 136 */       if (!flag)
/* 137 */         return false; 
/*     */     } 
/* 139 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public byte getMaxFury() {
/* 150 */     return (byte)this.fighterBean.getFury();
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\common\fight\fighter\FighterFighter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */