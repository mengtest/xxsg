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
/*     */ public class FightCalculator
/*     */ {
/*     */   private static long basePhyHurt(IFighter attacker, IFighter target) {
/*  26 */     long atk = attacker.getCalcAttr(AttributeType.ATTACK);
/*  27 */     long def = target.getCalcAttr(AttributeType.PHY_DEF);
/*  28 */     long hurtRate = attacker.getCalcAttr(AttributeType.HURT_RATE);
/*  29 */     long dehurtRate = target.getCalcAttr(AttributeType.HURT_DERATE);
/*     */ 
/*     */     
/*  32 */     long tmp = 10000L + hurtRate - dehurtRate;
/*  33 */     long fixHurtRate = (tmp < FightConstant.getMinHurtRate()) ? (long)FightConstant.getMinHurtRate() : tmp;
/*     */     
/*  35 */     long val = (atk - def) * fixHurtRate / 10000L;
/*  36 */     return Math.max(1L, val);
/*     */   }
/*     */   
/*     */   private static long baseMagHurt(IFighter attacker, IFighter target) {
/*  40 */     long atk = attacker.getCalcAttr(AttributeType.ATTACK);
/*  41 */     long def = target.getCalcAttr(AttributeType.MAG_DEF);
/*  42 */     long hurtRate = attacker.getCalcAttr(AttributeType.HURT_RATE);
/*  43 */     long dehurtRate = target.getCalcAttr(AttributeType.HURT_DERATE);
/*     */     
/*  45 */     long tmp = 10000L + hurtRate - dehurtRate;
/*  46 */     long fixHurtRate = (tmp < FightConstant.getMinHurtRate()) ? (long)FightConstant.getMinHurtRate() : tmp;
/*     */     
/*  48 */     long val = (atk - def) * fixHurtRate / 10000L;
/*  49 */     return Math.max(1L, val);
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
/*  61 */     long val = 0L;
/*  62 */     int damageType = effect.getBean().getDemageType();
/*  63 */     if (damageType == 0) {
/*  64 */       return 0;
/*     */     }
/*  66 */     if (damageType == 1) {
/*  67 */       long base = basePhyHurt(attacker, target);
/*  68 */       val = base * effect.getPowerVal(0) / 10000L + effect.getPowerVal(1);
/*  69 */     } else if (damageType == 2) {
/*  70 */       long base = baseMagHurt(attacker, target);
/*  71 */       val = base * effect.getPowerVal(0) / 10000L + effect.getPowerVal(1);
/*  72 */     } else if (damageType == 3) {
/*  73 */       double value = (target.getMaxHp() * effect.getPowerVal(0));
/*  74 */       val = (int)(value / 10000.0D);
/*  75 */     } else if (damageType == 4) {
/*  76 */       val = effect.getPowerVal(0);
/*  77 */     } else if (damageType == 5) {
/*  78 */       val = target.getMaxHp() * effect.getPowerVal(0) / 10000L + effect.getPowerVal(1);
/*  79 */     } else if (damageType == 6) {
/*  80 */       val = target.getMaxHp();
/*     */     } 
/*     */     
/*  83 */     if (val < 0L) {
/*  84 */       return 0;
/*     */     }
/*     */ 
/*     */     
/*  88 */     return (int)val;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean isHit(IFighter fighter, IFighter target) {
/*  97 */     double hit = fighter.getCalcAttr(AttributeType.HIT);
/*  98 */     double hitRange = FightConstant.getHitRange();
/*  99 */     double rateVal = FightConstant.getHitBaseValue();
/* 100 */     double beHit = hit + target.getCalcAttr(AttributeType.DODGE);
/* 101 */     if (beHit != 0.0D)
/* 102 */       rateVal += hitRange * hit / beHit; 
/* 103 */     rateVal = (rateVal > 10000.0D) ? 10000.0D : rateVal;
/*     */     
/* 105 */     double hitRate = fighter.getCalcAttr(AttributeType.HIT_RATE);
/* 106 */     double dodgeRate = target.getCalcAttr(AttributeType.DODGE_RATE);
/* 107 */     double fixDodgeRate = (hitRate - dodgeRate < FightConstant.getMinDogeRate()) ? FightConstant.getMinDogeRate() : (hitRate - dodgeRate);
/* 108 */     rateVal += rateVal * fixDodgeRate / 10000.0D;
/* 109 */     int rand = RandUtil.randNum(10000);
/*     */     
/* 111 */     return (rand < (int)rateVal);
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
/* 124 */     double critical = attacker.getCalcAttr(AttributeType.CRIT);
/* 125 */     double critRange = FightConstant.getCriticalRange();
/* 126 */     double beCrit = critical + target.getCalcAttr(AttributeType.ANTI_CRIT);
/* 127 */     double rateVal = FightConstant.getCritBaseValue();
/* 128 */     if (beCrit != 0.0D) {
/* 129 */       rateVal += critRange * critical / beCrit;
/*     */     }
/* 131 */     double critRate = attacker.getCalcAttr(AttributeType.CRIT_RATE);
/* 132 */     double antiCritRate = target.getCalcAttr(AttributeType.ANTI_CRIT_RATE);
/* 133 */     rateVal += rateVal * (critRate - antiCritRate) / 10000.0D;
/*     */ 
/*     */     
/* 136 */     for (int i = 0; i < effect.getBean().getAffectType().size(); i++) {
/* 137 */       if (((Integer)effect.getBean().getAffectType().get(i)).intValue() == 603 && target.hasBuff(BuffType.BURN_HURT.getType())) {
/* 138 */         rateVal += rateVal * ((EffectBean.AffectParaBean)effect.getBean().getAffectPara().get(i)).getPer() / 10000.0D;
/*     */       }
/*     */     } 
/* 141 */     int rand = RandUtil.randNum(10000);
/*     */     
/* 143 */     return (rand < (int)rateVal);
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
/* 155 */     return (FightConstant.getCritHurtBaseValue() + fighter.getAttr(AttributeType.STRIKE.getType()) - target
/* 156 */       .getAttr(AttributeType.ANTI_STRIKE.getType())) / 10000.0D;
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
/*     */   public static long hurtValue(Pair<Integer, Long> hurtValue, IFighter attacker, IFighter target) {
/* 170 */     hurtValue.setValue(Long.valueOf(((Long)hurtValue.getValue()).longValue() + attacker.getCalcAttr(AttributeType.HURT) - target.getCalcAttr(AttributeType.DE_HURT)));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 179 */     return ((Long)hurtValue.getValue()).longValue();
/*     */   }
/*     */   
/*     */   public static long finalHurtValue(long hurtValue, IFighter fighter, IFighter target) {
/* 183 */     long finalHurtRate = fighter.getCalcAttr(AttributeType.FINAL_HURT_RATE);
/* 184 */     long finalHurtDerate = target.getCalcAttr(AttributeType.FINAL_HURT_DERATE);
/*     */     
/* 186 */     Double value = Double.valueOf(hurtValue * (10000L + finalHurtRate - finalHurtDerate) / 10000.0D);
/* 187 */     if (value.doubleValue() < 0.0D) {
/* 188 */       return 0L;
/*     */     }
/* 190 */     return value.longValue();
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
/* 202 */     return (rand / 10000 > rate / 10000);
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
/* 216 */     return hurtValue * (10000 + combo) / 10000;
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
/* 228 */     if (finalHurt == 0) {
/* 229 */       return 0;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 236 */     return 0;
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
/* 247 */     if (finalHurt == 0)
/* 248 */       return 0; 
/* 249 */     return 1;
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
/* 263 */       recoverHp = (long)(recoverHp * (10000L + target.getCalcAttr(AttributeType.RECOVER_HP_RATE, true)) / 10000.0D);
/* 264 */     } catch (IndexOutOfBoundsException e) {
/* 265 */       LogUtil.errorLog(new Object[] { "old player attr value", e.getMessage() });
/*     */     } 
/* 267 */     if (recoverHp < 0L) {
/* 268 */       recoverHp = 0L;
/*     */     }
/* 270 */     return recoverHp;
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\sanguo\webgame\common\fight\FightCalculator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */