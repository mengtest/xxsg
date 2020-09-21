/*     */ package com.linlongyx.sanguo.webgame.common.fight;
/*     */ 
/*     */ import com.linlongyx.sanguo.webgame.common.fight.attribute.AttributeType;
/*     */ import com.linlongyx.sanguo.webgame.common.fight.buff.BuffType;
/*     */ import com.linlongyx.sanguo.webgame.common.fight.fighter.IFighter;
/*     */ import com.linlongyx.sanguo.webgame.common.fight.skill.Effect;
/*     */ import com.linlongyx.sanguo.webgame.common.structure.Pair;
/*     */ import com.linlongyx.sanguo.webgame.common.util.RandUtil;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.EffectBean;
/*     */ import com.linlongyx.sanguo.webgame.log.LogUtil;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class FightCalculator
/*     */ {
/*     */   private static long basePhyHurt(IFighter attacker, IFighter target) {
/*  28 */     long atk = attacker.getCalcAttr(AttributeType.ATTACK);
/*  29 */     long def = target.getCalcAttr(AttributeType.PHY_DEF);
/*  30 */     long hurtRate = attacker.getCalcAttr(AttributeType.HURT_RATE);
/*  31 */     long dehurtRate = target.getCalcAttr(AttributeType.HURT_DERATE);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  36 */     long tmp = 10000L + hurtRate - dehurtRate;
/*  37 */     long fixHurtRate = (tmp < FightConstant.getMinHurtRate()) ? (long)FightConstant.getMinHurtRate() : tmp;
/*     */     
/*  39 */     long val = (atk - def) * fixHurtRate / 10000L;
/*  40 */     return Math.max(1L, val);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static long baseMagHurt(IFighter attacker, IFighter target) {
/*  46 */     long atk = attacker.getCalcAttr(AttributeType.ATTACK);
/*  47 */     long def = target.getCalcAttr(AttributeType.MAG_DEF);
/*  48 */     long hurtRate = attacker.getCalcAttr(AttributeType.HURT_RATE);
/*  49 */     long dehurtRate = target.getCalcAttr(AttributeType.HURT_DERATE);
/*     */     
/*  51 */     long tmp = 10000L + hurtRate - dehurtRate;
/*  52 */     long fixHurtRate = (tmp < FightConstant.getMinHurtRate()) ? (long)FightConstant.getMinHurtRate() : tmp;
/*     */     
/*  54 */     long val = (atk - def) * fixHurtRate / 10000L;
/*  55 */     return Math.max(1L, val);
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
/*     */   public static int hurtType(Effect effect, IFighter attacker, IFighter target) {
/*  67 */     long val = 0L;
/*  68 */     int damageType = effect.getBean().getDemageType();
/*  69 */     if (damageType == 0) {
/*  70 */       return 0;
/*     */     }
/*  72 */     if (damageType == 1) {
/*  73 */       long base = basePhyHurt(attacker, target);
/*  74 */       val = base * effect.getPowerVal(0) / 10000L + effect.getPowerVal(1);
/*  75 */     } else if (damageType == 2) {
/*  76 */       long base = baseMagHurt(attacker, target);
/*  77 */       val = base * effect.getPowerVal(0) / 10000L + effect.getPowerVal(1);
/*  78 */     } else if (damageType == 3) {
/*  79 */       double value = (target.getMaxHp() * effect.getPowerVal(0));
/*  80 */       val = (int)(value / 10000.0D);
/*  81 */     } else if (damageType == 4) {
/*  82 */       val = effect.getPowerVal(0);
/*  83 */     } else if (damageType == 5) {
/*  84 */       val = target.getMaxHp() * effect.getPowerVal(0) / 10000L + effect.getPowerVal(1);
/*  85 */     } else if (damageType == 6) {
/*  86 */       val = target.getMaxHp();
/*     */     } 
/*     */     
/*  89 */     if (val < 0L) {
/*  90 */       return 0;
/*     */     }
/*     */ 
/*     */     
/*  94 */     return (int)val;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean isHit(IFighter fighter, IFighter target) {
/* 103 */     double hit = fighter.getCalcAttr(AttributeType.HIT);
/* 104 */     double hitRange = FightConstant.getHitRange();
/* 105 */     double rateVal = FightConstant.getHitBaseValue();
/* 106 */     double beHit = hit + target.getCalcAttr(AttributeType.DODGE);
/* 107 */     if (beHit != 0.0D)
/* 108 */       rateVal += hitRange * hit / beHit; 
/* 109 */     rateVal = (rateVal > 10000.0D) ? 10000.0D : rateVal;
/*     */     
/* 111 */     double hitRate = fighter.getCalcAttr(AttributeType.HIT_RATE);
/* 112 */     double dodgeRate = target.getCalcAttr(AttributeType.DODGE_RATE);
/* 113 */     double fixDodgeRate = (hitRate - dodgeRate < FightConstant.getMinDogeRate()) ? FightConstant.getMinDogeRate() : (hitRate - dodgeRate);
/* 114 */     rateVal += rateVal * fixDodgeRate / 10000.0D;
/* 115 */     int rand = RandUtil.randNum(10000);
/*     */     
/* 117 */     return (rand < (int)rateVal);
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
/*     */   
/*     */   public static boolean isCrit(Effect effect, IFighter attacker, IFighter target) {
/* 130 */     double critical = attacker.getCalcAttr(AttributeType.CRIT);
/* 131 */     double critRange = FightConstant.getCriticalRange();
/* 132 */     double beCrit = critical + target.getCalcAttr(AttributeType.ANTI_CRIT);
/* 133 */     double rateVal = FightConstant.getCritBaseValue();
/* 134 */     if (beCrit != 0.0D) {
/* 135 */       rateVal += critRange * critical / beCrit;
/*     */     }
/* 137 */     double critRate = attacker.getCalcAttr(AttributeType.CRIT_RATE);
/* 138 */     double antiCritRate = target.getCalcAttr(AttributeType.ANTI_CRIT_RATE);
/* 139 */     rateVal += rateVal * (critRate - antiCritRate) / 10000.0D;
/*     */ 
/*     */     
/* 142 */     for (int i = 0; i < effect.getBean().getAffectType().size(); i++) {
/* 143 */       if (((Integer)effect.getBean().getAffectType().get(i)).intValue() == 603 && target.hasBuff(BuffType.BURN_HURT.getType())) {
/* 144 */         rateVal += rateVal * ((EffectBean.AffectParaBean)effect.getBean().getAffectPara().get(i)).getPer() / 10000.0D;
/*     */       }
/*     */     } 
/* 147 */     int rand = RandUtil.randNum(10000);
/*     */     
/* 149 */     return (rand < (int)rateVal);
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
/*     */   public static double critRate(IFighter fighter, IFighter target) {
/* 161 */     return (FightConstant.getCritHurtBaseValue() + fighter.getAttr(AttributeType.STRIKE.getType()) - target
/* 162 */       .getAttr(AttributeType.ANTI_STRIKE.getType())) / 10000.0D;
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
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static long hurtValue(Pair<Integer, Long> hurtValue, IFighter attacker, IFighter target) {
/* 178 */     hurtValue.setValue(Long.valueOf(((Long)hurtValue.getValue()).longValue() + attacker.getCalcAttr(AttributeType.HURT) - target.getCalcAttr(AttributeType.DE_HURT)));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 187 */     return ((Long)hurtValue.getValue()).longValue();
/*     */   }
/*     */   
/*     */   public static long finalHurtValue(long hurtValue, IFighter fighter, IFighter target) {
/* 191 */     long finalHurtRate = fighter.getCalcAttr(AttributeType.FINAL_HURT_RATE);
/* 192 */     long finalHurtDerate = target.getCalcAttr(AttributeType.FINAL_HURT_DERATE);
/*     */     
/* 194 */     Double value = Double.valueOf(hurtValue * (10000L + finalHurtRate - finalHurtDerate) / 10000.0D);
/* 195 */     if (value.doubleValue() < 0.0D) {
/* 196 */       return 0L;
/*     */     }
/* 198 */     return value.longValue();
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
/*     */   public static boolean isRandTrue(int rand, int rate) {
/* 210 */     return (rand / 10000 > rate / 10000);
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
/*     */ 
/*     */   
/*     */   public static int comboHurt(int hurtValue, int combo) {
/* 224 */     return hurtValue * (10000 + combo) / 10000;
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
/*     */   public static int revertHurt(int finalHurt, IFighter fighter, IFighter target) {
/* 236 */     if (finalHurt == 0) {
/* 237 */       return 0;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 244 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int basicSuckHp(int finalHurt, IFighter fighter) {
/* 255 */     if (finalHurt == 0)
/* 256 */       return 0; 
/* 257 */     return 1;
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
/*     */   
/*     */   public static long calFinalRecoverHp(long recoverHp, IFighter target) {
/*     */     try {
/* 271 */       recoverHp = (long)(recoverHp * (10000L + target.getCalcAttr(AttributeType.RECOVER_HP_RATE, true)) / 10000.0D);
/* 272 */     } catch (IndexOutOfBoundsException e) {
/* 273 */       LogUtil.errorLog(new Object[] { "old player attr value", e.getMessage() });
/*     */     } 
/* 275 */     if (recoverHp < 0L) {
/* 276 */       recoverHp = 0L;
/*     */     }
/* 278 */     return recoverHp;
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\common\fight\FightCalculator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */