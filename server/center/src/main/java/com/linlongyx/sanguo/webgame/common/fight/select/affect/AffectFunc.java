/*     */ package com.linlongyx.sanguo.webgame.common.fight.select.affect;
/*     */ 
/*     */ import com.linlongyx.sanguo.webgame.common.fight.FightCalculator;
/*     */ import com.linlongyx.sanguo.webgame.common.fight.FightUtil;
/*     */ import com.linlongyx.sanguo.webgame.common.fight.IFight;
/*     */ import com.linlongyx.sanguo.webgame.common.fight.attribute.AttributeType;
/*     */ import com.linlongyx.sanguo.webgame.common.fight.buff.BuffType;
/*     */ import com.linlongyx.sanguo.webgame.common.fight.fighter.IFighter;
/*     */ import com.linlongyx.sanguo.webgame.common.fight.skill.Effect;
/*     */ import com.linlongyx.sanguo.webgame.common.structure.Pair;
/*     */ import com.linlongyx.sanguo.webgame.common.util.RandUtil;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.EffectBean;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.ResultPlayData;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public enum AffectFunc
/*     */ {
/*  24 */   RECOVER_HP_1(101)
/*     */   {
/*     */     public int affect(IFight fight, Pair<Integer, Long> hurtValue, Effect effect, IFighter attacker, IFighter target, ResultPlayData resultPlayData, EffectBean.AffectParaBean affectParaBean)
/*     */     {
/*  28 */       long revertHp = attacker.getCalcAttr(AttributeType.ATTACK) * affectParaBean.getPer() / 10000L + affectParaBean.getValue();
/*  29 */       revertHp = FightCalculator.calFinalRecoverHp(revertHp, target);
/*     */       
/*  31 */       target.addHP(revertHp);
/*  32 */       resultPlayData.atomData.add(Integer.valueOf(3));
/*  33 */       resultPlayData.atomData.add(Integer.valueOf(target.getGuid()));
/*  34 */       resultPlayData.atomData.add(Integer.valueOf((int)revertHp));
/*  35 */       return getType();
/*     */     }
/*     */   },
/*     */   
/*  39 */   RECOVER_HP_2(102)
/*     */   {
/*     */     public int affect(IFight fight, Pair<Integer, Long> hurtValue, Effect effect, IFighter attacker, IFighter target, ResultPlayData resultPlayData, EffectBean.AffectParaBean affectParaBean)
/*     */     {
/*  43 */       long revertHp = target.getMaxHp() * affectParaBean.getPer() / 10000L + affectParaBean.getValue();
/*     */       
/*  45 */       revertHp = FightCalculator.calFinalRecoverHp(revertHp, target);
/*     */       
/*  47 */       target.addHP(revertHp);
/*  48 */       resultPlayData.atomData.add(Integer.valueOf(3));
/*  49 */       resultPlayData.atomData.add(Integer.valueOf(target.getGuid()));
/*  50 */       resultPlayData.atomData.add(Integer.valueOf((int)revertHp));
/*  51 */       return getType();
/*     */     }
/*     */   },
/*     */   
/*  55 */   RECOVER_HP_3(103)
/*     */   {
/*     */     public int affect(IFight fight, Pair<Integer, Long> hurtValue, Effect effect, IFighter attacker, IFighter target, ResultPlayData resultPlayData, EffectBean.AffectParaBean affectParaBean)
/*     */     {
/*  59 */       long revertHp = affectParaBean.getValue();
/*     */       
/*  61 */       revertHp = FightCalculator.calFinalRecoverHp(revertHp, target);
/*     */       
/*  63 */       target.addHP(revertHp);
/*  64 */       resultPlayData.atomData.add(Integer.valueOf(3));
/*  65 */       resultPlayData.atomData.add(Integer.valueOf(target.getGuid()));
/*  66 */       resultPlayData.atomData.add(Integer.valueOf((int)revertHp));
/*  67 */       return getType();
/*     */     }
/*     */   },
/*     */   
/*  71 */   SUCK_FURY(201)
/*     */   {
/*     */     public int affect(IFight fight, Pair<Integer, Long> hurtValue, Effect effect, IFighter attacker, IFighter target, ResultPlayData resultPlayData, EffectBean.AffectParaBean affectParaBean)
/*     */     {
/*  75 */       boolean isAffect = RandUtil.isRandTrue(affectParaBean.getPer());
/*  76 */       if (!isAffect) {
/*  77 */         return 0;
/*     */       }
/*  79 */       int fury = affectParaBean.getValue();
/*  80 */       int decFury = Math.min(fury, target.getFury().byteValue());
/*  81 */       attacker.addFury((byte)fury, true);
/*  82 */       target.decFury((byte)decFury);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  89 */       resultPlayData.atomData.add(Integer.valueOf(7));
/*  90 */       resultPlayData.atomData.add(Integer.valueOf(target.getGuid()));
/*  91 */       resultPlayData.atomData.add(Integer.valueOf(-decFury));
/*  92 */       return getType();
/*     */     }
/*     */   },
/*     */   
/*  96 */   ADD_FURY(202)
/*     */   {
/*     */     public int affect(IFight fight, Pair<Integer, Long> hurtValue, Effect effect, IFighter attacker, IFighter target, ResultPlayData resultPlayData, EffectBean.AffectParaBean affectParaBean)
/*     */     {
/* 100 */       boolean isAffect = RandUtil.isRandTrue(affectParaBean.getPer());
/* 101 */       if (!isAffect) {
/* 102 */         return 0;
/*     */       }
/* 104 */       int fury = affectParaBean.getValue();
/* 105 */       target.addFury((byte)fury, true);
/* 106 */       resultPlayData.atomData.add(Integer.valueOf(6));
/* 107 */       resultPlayData.atomData.add(Integer.valueOf(target.getGuid()));
/* 108 */       resultPlayData.atomData.add(Integer.valueOf(fury));
/*     */       
/* 110 */       return getType();
/*     */     }
/*     */   },
/*     */   
/* 114 */   DEL_FURY(203)
/*     */   {
/*     */     public int affect(IFight fight, Pair<Integer, Long> hurtValue, Effect effect, IFighter attacker, IFighter target, ResultPlayData resultPlayData, EffectBean.AffectParaBean affectParaBean)
/*     */     {
/* 118 */       boolean isAffect = RandUtil.isRandTrue(affectParaBean.getPer());
/* 119 */       if (!isAffect) {
/* 120 */         return 0;
/*     */       }
/* 122 */       int fury = affectParaBean.getValue();
/* 123 */       int decFury = Math.min(fury, target.getFury().byteValue());
/* 124 */       target.decFury((byte)decFury);
/* 125 */       resultPlayData.atomData.add(Integer.valueOf(7));
/* 126 */       resultPlayData.atomData.add(Integer.valueOf(target.getGuid()));
/* 127 */       resultPlayData.atomData.add(Integer.valueOf(-decFury));
/*     */       
/* 129 */       return getType();
/*     */     }
/*     */   },
/*     */   
/* 133 */   CLEAR_FURY(204)
/*     */   {
/*     */     public int affect(IFight fight, Pair<Integer, Long> hurtValue, Effect effect, IFighter attacker, IFighter target, ResultPlayData resultPlayData, EffectBean.AffectParaBean affectParaBean)
/*     */     {
/* 137 */       boolean isAffect = RandUtil.isRandTrue(affectParaBean.getPer());
/* 138 */       if (!isAffect) {
/* 139 */         return 0;
/*     */       }
/*     */       
/* 142 */       target.decFury(target.getFury().byteValue());
/* 143 */       resultPlayData.atomData.add(Integer.valueOf(7));
/* 144 */       resultPlayData.atomData.add(Integer.valueOf(target.getGuid()));
/* 145 */       resultPlayData.atomData.add(Integer.valueOf(-target.getFury().byteValue()));
/*     */       
/* 147 */       return getType();
/*     */     }
/*     */   },
/*     */   
/* 151 */   SUCK_HP_1(301)
/*     */   {
/*     */     public int affect(IFight fight, Pair<Integer, Long> hurtValue, Effect effect, IFighter attacker, IFighter target, ResultPlayData resultPlayData, EffectBean.AffectParaBean affectParaBean)
/*     */     {
/* 155 */       int hpPara = affectParaBean.getPer();
/* 156 */       long deltaHp = FightUtil.formatHurtVal(hpPara * ((Long)hurtValue.getValue()).longValue() / 10000L);
/* 157 */       deltaHp = FightCalculator.calFinalRecoverHp(deltaHp, attacker);
/* 158 */       attacker.addHP(deltaHp);
/* 159 */       resultPlayData.atomData.add(Integer.valueOf(3));
/* 160 */       resultPlayData.atomData.add(Integer.valueOf(attacker.getGuid()));
/* 161 */       resultPlayData.atomData.add(Integer.valueOf((int)deltaHp));
/*     */       
/* 163 */       return getType();
/*     */     }
/*     */   },
/*     */   
/* 167 */   SUCK_HP_2(302)
/*     */   {
/*     */     public int affect(IFight fight, Pair<Integer, Long> hurtValue, Effect effect, IFighter attacker, IFighter target, ResultPlayData resultPlayData, EffectBean.AffectParaBean affectParaBean)
/*     */     {
/* 171 */       int hpPara = affectParaBean.getPer();
/* 172 */       long deltaHp = FightUtil.formatHurtVal(hpPara * attacker.getCalcAttr(AttributeType.ATTACK) / 10000L);
/* 173 */       deltaHp = FightCalculator.calFinalRecoverHp(deltaHp, attacker);
/* 174 */       attacker.addHP(deltaHp);
/* 175 */       resultPlayData.atomData.add(Integer.valueOf(3));
/* 176 */       resultPlayData.atomData.add(Integer.valueOf(attacker.getGuid()));
/* 177 */       resultPlayData.atomData.add(Integer.valueOf((int)deltaHp));
/*     */       
/* 179 */       return getType();
/*     */     }
/*     */   },
/*     */   
/* 183 */   SUCK_HP_3(303)
/*     */   {
/*     */     public int affect(IFight fight, Pair<Integer, Long> hurtValue, Effect effect, IFighter attacker, IFighter target, ResultPlayData resultPlayData, EffectBean.AffectParaBean affectParaBean)
/*     */     {
/* 187 */       long deltaHp = FightUtil.formatHurtVal(affectParaBean.getValue());
/*     */       
/* 189 */       deltaHp = FightCalculator.calFinalRecoverHp(deltaHp, attacker);
/* 190 */       attacker.addHP(deltaHp);
/*     */       
/* 192 */       resultPlayData.atomData.add(Integer.valueOf(3));
/* 193 */       resultPlayData.atomData.add(Integer.valueOf(attacker.getGuid()));
/* 194 */       resultPlayData.atomData.add(Integer.valueOf((int)deltaHp));
/*     */       
/* 196 */       return getType();
/*     */     }
/*     */   },
/*     */   
/* 200 */   DEC_BUFF(401)
/*     */   {
/*     */     public int affect(IFight fight, Pair<Integer, Long> hurtValue, Effect effect, IFighter attacker, IFighter target, ResultPlayData resultPlayData, EffectBean.AffectParaBean affectParaBean)
/*     */     {
/* 204 */       int buffNum = affectParaBean.getValue();
/* 205 */       int buffProb = affectParaBean.getPer();
/* 206 */       int level = affectParaBean.getLevel();
/*     */       
/* 208 */       if (RandUtil.isRandTrue(buffProb)) {
/* 209 */         target.removeBuff(resultPlayData, 0, buffNum, level);
/* 210 */         return getType();
/*     */       } 
/* 212 */       return 0;
/*     */     }
/*     */   },
/*     */   
/* 216 */   DEC_DEBUF(402)
/*     */   {
/*     */     public int affect(IFight fight, Pair<Integer, Long> hurtValue, Effect effect, IFighter attacker, IFighter target, ResultPlayData resultPlayData, EffectBean.AffectParaBean affectParaBean)
/*     */     {
/* 220 */       int buffNum = affectParaBean.getValue();
/* 221 */       int buffProb = affectParaBean.getPer();
/* 222 */       int level = affectParaBean.getLevel();
/*     */       
/* 224 */       if (RandUtil.isRandTrue(buffProb)) {
/* 225 */         target.removeBuff(resultPlayData, 1, buffNum, level);
/* 226 */         return getType();
/*     */       } 
/* 228 */       return 0;
/*     */     }
/*     */   },
/*     */   
/* 232 */   ADD_DEBUF(403)
/*     */   {
/*     */     public int affect(IFight fight, Pair<Integer, Long> hurtValue, Effect effect, IFighter attacker, IFighter target, ResultPlayData resultPlayData, EffectBean.AffectParaBean affectParaBean)
/*     */     {
/* 236 */       int buffId = affectParaBean.getValue();
/* 237 */       int buffProb = affectParaBean.getPer();
/*     */       
/* 239 */       if (RandUtil.isRandTrue(buffProb)) {
/* 240 */         target.addBuff(fight, buffId, ((Long)hurtValue.getValue()).longValue(), attacker, resultPlayData);
/* 241 */         return buffId;
/*     */       } 
/* 243 */       return 0;
/*     */     }
/*     */   },
/*     */   
/* 247 */   HURT_ADD(501)
/*     */   {
/*     */     public int affect(IFight fight, Pair<Integer, Long> hurtValue, Effect effect, IFighter attacker, IFighter target, ResultPlayData resultPlayData, EffectBean.AffectParaBean affectParaBean)
/*     */     {
/* 251 */       long hurtVal = ((Long)hurtValue.getValue()).longValue();
/* 252 */       hurtVal += hurtVal * affectParaBean.getPer() * attacker.getKillNum() / 10000L;
/* 253 */       hurtValue.setValue(Long.valueOf(hurtVal));
/* 254 */       return 0;
/*     */     }
/*     */   },
/*     */   
/* 258 */   BURN_HURT(601)
/*     */   {
/*     */     public int affect(IFight fight, Pair<Integer, Long> hurtValue, Effect effect, IFighter attacker, IFighter target, ResultPlayData resultPlayData, EffectBean.AffectParaBean affectParaBean)
/*     */     {
/* 262 */       if (target.hasBuff(BuffType.BURN_HURT.getType())) {
/* 263 */         long hurtVal = ((Long)hurtValue.getValue()).longValue();
/* 264 */         hurtVal += hurtVal * affectParaBean.getPer() / 10000L;
/* 265 */         hurtValue.setValue(Long.valueOf(hurtVal));
/*     */       } 
/* 267 */       return 0;
/*     */     }
/*     */   },
/*     */   
/* 271 */   BURN_BUFF(602)
/*     */   {
/*     */     public int affect(IFight fight, Pair<Integer, Long> hurtValue, Effect effect, IFighter attacker, IFighter target, ResultPlayData resultPlayData, EffectBean.AffectParaBean affectParaBean)
/*     */     {
/* 275 */       int buffId = affectParaBean.getValue();
/* 276 */       int buffProb = affectParaBean.getPer();
/*     */       
/* 278 */       if (target.hasBuff(BuffType.BURN_HURT.getType()) && RandUtil.isRandTrue(buffProb)) {
/* 279 */         target.addBuff(fight, buffId, ((Long)hurtValue.getValue()).longValue(), attacker, resultPlayData);
/* 280 */         return buffId;
/*     */       } 
/* 282 */       return 0;
/*     */     }
/*     */   },
/*     */   
/* 286 */   BURN_CRIT(603)
/*     */   {
/*     */     public int affect(IFight fight, Pair<Integer, Long> hurtValue, Effect effect, IFighter attacker, IFighter target, ResultPlayData resultPlayData, EffectBean.AffectParaBean affectParaBean)
/*     */     {
/* 290 */       return 0;
/*     */     }
/*     */   };
/*     */ 
/*     */   
/*     */   private int type;
/*     */   
/*     */   AffectFunc(int type) {
/* 298 */     this.type = type;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getType() {
/* 304 */     return this.type;
/*     */   }
/*     */   
/*     */   public abstract int affect(IFight paramIFight, Pair<Integer, Long> paramPair, Effect paramEffect, IFighter paramIFighter1, IFighter paramIFighter2, ResultPlayData paramResultPlayData, EffectBean.AffectParaBean paramAffectParaBean);
/*     */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\sanguo\webgame\common\fight\select\affect\AffectFunc.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */