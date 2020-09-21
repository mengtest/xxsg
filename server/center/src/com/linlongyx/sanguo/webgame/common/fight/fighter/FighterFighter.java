/*     */ package com.linlongyx.sanguo.webgame.common.fight.fighter;
/*     */ 
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
/*     */   public FighterFighter(CommonFighterData commonFighterData, byte pos, long pid, boolean copyAttr) {
/*  24 */     this.id = commonFighterData.getId();
/*  25 */     this.type = commonFighterData.getType();
/*  26 */     this.pos = pos;
/*  27 */     this.fury = 0;
/*  28 */     this.isAuto = true;
/*  29 */     this.pid = pid;
/*  30 */     this.talisman = commonFighterData.getTalisman();
/*     */     
/*  32 */     this.fighterBean = (FighterBean)JsonTableService.getJsonData((int)this.id, FighterBean.class);
/*  33 */     this.hurtType = (byte)this.fighterBean.getHurtType();
/*  34 */     FighterStarBean fighterStarBean = (FighterStarBean)JsonTableService.getJsonData((int)this.id, FighterStarBean.class);
/*  35 */     this.levelBean = (FighterStarBean.LevelBean)fighterStarBean.getLevel().get(Integer.valueOf(commonFighterData.getLevel()));
/*     */     
/*  37 */     this.cmmSkill = new Skill(this.fighterBean.getSkill(), 1);
/*  38 */     this.furySkill = new Skill(this.levelBean.getHotSkill(), 1);
/*  39 */     if (this.levelBean.getFitSkill() != 0) {
/*  40 */       this.fitSkill = new Skill(this.levelBean.getFitSkill(), 1);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*  45 */     if (copyAttr) {
/*  46 */       this.baseAttrs = Arrays.copyOf(commonFighterData.getBaseAttrs(), (commonFighterData.getBaseAttrs()).length);
/*  47 */       this.attrs = Arrays.copyOf(commonFighterData.getAttrs(), (commonFighterData.getAttrs()).length);
/*     */     } else {
/*  49 */       this.baseAttrs = commonFighterData.getBaseAttrs();
/*  50 */       this.attrs = commonFighterData.getAttrs();
/*     */     } 
/*     */     
/*  53 */     this.fighterData.id = this.id;
/*  54 */     this.fighterData.type = this.type;
/*  55 */     this.fighterData.attack = this.baseAttrs[AttributeType.ATTACK.getType()];
/*  56 */     this.fighterData.magDef = this.baseAttrs[AttributeType.MAG_DEF.getType()];
/*  57 */     this.fighterData.phyDef = this.baseAttrs[AttributeType.PHY_DEF.getType()];
/*     */     
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
/*  69 */       if (this.fitSkill != null && canReleaseFitSkill(fight)) {
/*  70 */         skill = this.fitSkill;
/*     */       }
/*     */     } 
/*  73 */     int ret = useSkill(fight, skill);
/*  74 */     return ret;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean canReleaseFitSkill(IFight fight) {
/*  83 */     if (null == this.furySkill)
/*  84 */       return false; 
/*  85 */     if (this.fury < this.furySkill.getBean().getFuryCost())
/*  86 */       return false; 
/*  87 */     List<IFighter> fighters = fight.getCurFightersExclude(getSide(), true, getGuid());
/*  88 */     if (fighters == null || fighters.isEmpty())
/*  89 */       return false; 
/*  90 */     if (null == this.fighterBean.getFitPartner() || this.fighterBean.getFitPartner().isEmpty()) {
/*  91 */       return false;
/*     */     }
/*  93 */     for (Integer fighterId : this.fighterBean.getFitPartner()) {
/*  94 */       boolean flag = fighters.stream().anyMatch(fighter -> (fighter.getId() == fighterId.intValue()));
/*  95 */       if (!flag)
/*  96 */         return false; 
/*     */     } 
/*  98 */     return true;
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
/* 109 */     return (byte)this.fighterBean.getFury();
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\sanguo\webgame\common\fight\fighter\FighterFighter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */