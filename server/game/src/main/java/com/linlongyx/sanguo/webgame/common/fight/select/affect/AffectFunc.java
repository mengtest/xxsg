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
/*     */ 
/*     */ public enum AffectFunc
/*     */ {
/*  25 */   RECOVER_HP_1(101)
/*     */   {
/*     */     public int affect(IFight fight, Pair<Integer, Long> hurtValue, Effect effect, IFighter attacker, IFighter target, ResultPlayData resultPlayData, EffectBean.AffectParaBean affectParaBean)
/*     */     {
/*  29 */       long revertHp = attacker.getCalcAttr(AttributeType.ATTACK) * affectParaBean.getPer() / 10000L + affectParaBean.getValue();
/*  30 */       revertHp = FightCalculator.calFinalRecoverHp(revertHp, target);
/*     */       
/*  32 */       target.addHP(revertHp);
/*  33 */       resultPlayData.atomData.add(Integer.valueOf(3));
/*  34 */       resultPlayData.atomData.add(Integer.valueOf(target.getGuid()));
/*  35 */       resultPlayData.atomData.add(Integer.valueOf((int)revertHp));
/*  36 */       return getType();
/*     */     }
/*     */   },
/*     */   
/*  40 */   RECOVER_HP_2(102)
/*     */   {
/*     */     public int affect(IFight fight, Pair<Integer, Long> hurtValue, Effect effect, IFighter attacker, IFighter target, ResultPlayData resultPlayData, EffectBean.AffectParaBean affectParaBean)
/*     */     {
/*  44 */       long revertHp = target.getMaxHp() * affectParaBean.getPer() / 10000L + affectParaBean.getValue();
/*     */       
/*  46 */       revertHp = FightCalculator.calFinalRecoverHp(revertHp, target);
/*     */       
/*  48 */       target.addHP(revertHp);
/*  49 */       resultPlayData.atomData.add(Integer.valueOf(3));
/*  50 */       resultPlayData.atomData.add(Integer.valueOf(target.getGuid()));
/*  51 */       resultPlayData.atomData.add(Integer.valueOf((int)revertHp));
/*  52 */       return getType();
/*     */     }
/*     */   },
/*     */   
/*  56 */   RECOVER_HP_3(103)
/*     */   {
/*     */     public int affect(IFight fight, Pair<Integer, Long> hurtValue, Effect effect, IFighter attacker, IFighter target, ResultPlayData resultPlayData, EffectBean.AffectParaBean affectParaBean)
/*     */     {
/*  60 */       long revertHp = affectParaBean.getValue();
/*     */       
/*  62 */       revertHp = FightCalculator.calFinalRecoverHp(revertHp, target);
/*     */       
/*  64 */       target.addHP(revertHp);
/*  65 */       resultPlayData.atomData.add(Integer.valueOf(3));
/*  66 */       resultPlayData.atomData.add(Integer.valueOf(target.getGuid()));
/*  67 */       resultPlayData.atomData.add(Integer.valueOf((int)revertHp));
/*  68 */       return getType();
/*     */     }
/*     */   },
/*     */   
/*  72 */   SUCK_FURY(201)
/*     */   {
/*     */     public int affect(IFight fight, Pair<Integer, Long> hurtValue, Effect effect, IFighter attacker, IFighter target, ResultPlayData resultPlayData, EffectBean.AffectParaBean affectParaBean)
/*     */     {
/*  76 */       boolean isAffect = RandUtil.isRandTrue(affectParaBean.getPer());
/*  77 */       if (!isAffect) {
/*  78 */         return 0;
/*     */       }
/*  80 */       int fury = affectParaBean.getValue();
/*  81 */       int decFury = Math.min(fury, target.getFury().byteValue());
/*  82 */       attacker.addFury((byte)fury, true);
/*  83 */       target.decFury((byte)decFury);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  90 */       resultPlayData.atomData.add(Integer.valueOf(7));
/*  91 */       resultPlayData.atomData.add(Integer.valueOf(target.getGuid()));
/*  92 */       resultPlayData.atomData.add(Integer.valueOf(-decFury));
/*  93 */       return getType();
/*     */     }
/*     */   },
/*     */   
/*  97 */   ADD_FURY(202)
/*     */   {
/*     */     public int affect(IFight fight, Pair<Integer, Long> hurtValue, Effect effect, IFighter attacker, IFighter target, ResultPlayData resultPlayData, EffectBean.AffectParaBean affectParaBean)
/*     */     {
/* 101 */       boolean isAffect = RandUtil.isRandTrue(affectParaBean.getPer());
/* 102 */       if (!isAffect) {
/* 103 */         return 0;
/*     */       }
/* 105 */       int fury = affectParaBean.getValue();
/* 106 */       target.addFury((byte)fury, true);
/* 107 */       resultPlayData.atomData.add(Integer.valueOf(6));
/* 108 */       resultPlayData.atomData.add(Integer.valueOf(target.getGuid()));
/* 109 */       resultPlayData.atomData.add(Integer.valueOf(fury));
/*     */       
/* 111 */       return getType();
/*     */     }
/*     */   },
/*     */   
/* 115 */   DEL_FURY(203)
/*     */   {
/*     */     public int affect(IFight fight, Pair<Integer, Long> hurtValue, Effect effect, IFighter attacker, IFighter target, ResultPlayData resultPlayData, EffectBean.AffectParaBean affectParaBean)
/*     */     {
/* 119 */       boolean isAffect = RandUtil.isRandTrue(affectParaBean.getPer());
/* 120 */       if (!isAffect) {
/* 121 */         return 0;
/*     */       }
/* 123 */       int fury = affectParaBean.getValue();
/* 124 */       int decFury = Math.min(fury, target.getFury().byteValue());
/* 125 */       target.decFury((byte)decFury);
/* 126 */       resultPlayData.atomData.add(Integer.valueOf(7));
/* 127 */       resultPlayData.atomData.add(Integer.valueOf(target.getGuid()));
/* 128 */       resultPlayData.atomData.add(Integer.valueOf(-decFury));
/*     */       
/* 130 */       return getType();
/*     */     }
/*     */   },
/*     */   
/* 134 */   CLEAR_FURY(204)
/*     */   {
/*     */     public int affect(IFight fight, Pair<Integer, Long> hurtValue, Effect effect, IFighter attacker, IFighter target, ResultPlayData resultPlayData, EffectBean.AffectParaBean affectParaBean)
/*     */     {
/* 138 */       boolean isAffect = RandUtil.isRandTrue(affectParaBean.getPer());
/* 139 */       if (!isAffect) {
/* 140 */         return 0;
/*     */       }
/*     */       
/* 143 */       target.decFury(target.getFury().byteValue());
/* 144 */       resultPlayData.atomData.add(Integer.valueOf(7));
/* 145 */       resultPlayData.atomData.add(Integer.valueOf(target.getGuid()));
/* 146 */       resultPlayData.atomData.add(Integer.valueOf(-target.getFury().byteValue()));
/*     */       
/* 148 */       return getType();
/*     */     }
/*     */   },
/*     */   
/* 152 */   SUCK_HP_1(301)
/*     */   {
/*     */     public int affect(IFight fight, Pair<Integer, Long> hurtValue, Effect effect, IFighter attacker, IFighter target, ResultPlayData resultPlayData, EffectBean.AffectParaBean affectParaBean)
/*     */     {
/* 156 */       int hpPara = affectParaBean.getPer();
/* 157 */       long deltaHp = FightUtil.formatHurtVal(hpPara * ((Long)hurtValue.getValue()).longValue() / 10000L);
/* 158 */       deltaHp = FightCalculator.calFinalRecoverHp(deltaHp, attacker);
/* 159 */       attacker.addHP(deltaHp);
/* 160 */       resultPlayData.atomData.add(Integer.valueOf(3));
/* 161 */       resultPlayData.atomData.add(Integer.valueOf(attacker.getGuid()));
/* 162 */       resultPlayData.atomData.add(Integer.valueOf((int)deltaHp));
/*     */       
/* 164 */       return getType();
/*     */     }
/*     */   },
/*     */   
/* 168 */   SUCK_HP_2(302)
/*     */   {
/*     */     public int affect(IFight fight, Pair<Integer, Long> hurtValue, Effect effect, IFighter attacker, IFighter target, ResultPlayData resultPlayData, EffectBean.AffectParaBean affectParaBean)
/*     */     {
/* 172 */       int hpPara = affectParaBean.getPer();
/* 173 */       long deltaHp = FightUtil.formatHurtVal(hpPara * attacker.getCalcAttr(AttributeType.ATTACK) / 10000L);
/* 174 */       deltaHp = FightCalculator.calFinalRecoverHp(deltaHp, attacker);
/* 175 */       attacker.addHP(deltaHp);
/* 176 */       resultPlayData.atomData.add(Integer.valueOf(3));
/* 177 */       resultPlayData.atomData.add(Integer.valueOf(attacker.getGuid()));
/* 178 */       resultPlayData.atomData.add(Integer.valueOf((int)deltaHp));
/*     */       
/* 180 */       return getType();
/*     */     }
/*     */   },
/*     */   
/* 184 */   SUCK_HP_3(303)
/*     */   {
/*     */     public int affect(IFight fight, Pair<Integer, Long> hurtValue, Effect effect, IFighter attacker, IFighter target, ResultPlayData resultPlayData, EffectBean.AffectParaBean affectParaBean)
/*     */     {
/* 188 */       long deltaHp = FightUtil.formatHurtVal(affectParaBean.getValue());
/*     */       
/* 190 */       deltaHp = FightCalculator.calFinalRecoverHp(deltaHp, attacker);
/* 191 */       attacker.addHP(deltaHp);
/*     */       
/* 193 */       resultPlayData.atomData.add(Integer.valueOf(3));
/* 194 */       resultPlayData.atomData.add(Integer.valueOf(attacker.getGuid()));
/* 195 */       resultPlayData.atomData.add(Integer.valueOf((int)deltaHp));
/*     */       
/* 197 */       return getType();
/*     */     }
/*     */   },
/*     */   
/* 201 */   DEC_BUFF(401)
/*     */   {
/*     */     public int affect(IFight fight, Pair<Integer, Long> hurtValue, Effect effect, IFighter attacker, IFighter target, ResultPlayData resultPlayData, EffectBean.AffectParaBean affectParaBean)
/*     */     {
/* 205 */       int buffNum = affectParaBean.getValue();
/* 206 */       int buffProb = affectParaBean.getPer();
/* 207 */       int level = affectParaBean.getLevel();
/*     */       
/* 209 */       if (RandUtil.isRandTrue(buffProb)) {
/* 210 */         target.removeBuff(resultPlayData, 0, buffNum, level);
/* 211 */         return getType();
/*     */       } 
/* 213 */       return 0;
/*     */     }
/*     */   },
/*     */   
/* 217 */   DEC_DEBUF(402)
/*     */   {
/*     */     public int affect(IFight fight, Pair<Integer, Long> hurtValue, Effect effect, IFighter attacker, IFighter target, ResultPlayData resultPlayData, EffectBean.AffectParaBean affectParaBean)
/*     */     {
/* 221 */       int buffNum = affectParaBean.getValue();
/* 222 */       int buffProb = affectParaBean.getPer();
/* 223 */       int level = affectParaBean.getLevel();
/*     */       
/* 225 */       if (RandUtil.isRandTrue(buffProb)) {
/* 226 */         target.removeBuff(resultPlayData, 1, buffNum, level);
/* 227 */         return getType();
/*     */       } 
/* 229 */       return 0;
/*     */     }
/*     */   },
/*     */   
/* 233 */   ADD_DEBUF(403)
/*     */   {
/*     */     public int affect(IFight fight, Pair<Integer, Long> hurtValue, Effect effect, IFighter attacker, IFighter target, ResultPlayData resultPlayData, EffectBean.AffectParaBean affectParaBean)
/*     */     {
/* 237 */       int buffId = affectParaBean.getValue();
/* 238 */       int buffProb = affectParaBean.getPer();
/*     */       
/* 240 */       if (RandUtil.isRandTrue(buffProb)) {
/* 241 */         target.addBuff(fight, buffId, ((Long)hurtValue.getValue()).longValue(), attacker, resultPlayData);
/* 242 */         return buffId;
/*     */       } 
/* 244 */       return 0;
/*     */     }
/*     */   },
/*     */   
/* 248 */   HURT_ADD(501)
/*     */   {
/*     */     public int affect(IFight fight, Pair<Integer, Long> hurtValue, Effect effect, IFighter attacker, IFighter target, ResultPlayData resultPlayData, EffectBean.AffectParaBean affectParaBean)
/*     */     {
/* 252 */       long hurtVal = ((Long)hurtValue.getValue()).longValue();
/* 253 */       hurtVal += hurtVal * affectParaBean.getPer() * attacker.getKillNum() / 10000L;
/* 254 */       hurtValue.setValue(Long.valueOf(hurtVal));
/* 255 */       return 0;
/*     */     }
/*     */   },
/*     */   
/* 259 */   BURN_HURT(601)
/*     */   {
/*     */     public int affect(IFight fight, Pair<Integer, Long> hurtValue, Effect effect, IFighter attacker, IFighter target, ResultPlayData resultPlayData, EffectBean.AffectParaBean affectParaBean)
/*     */     {
/* 263 */       if (target.hasBuff(BuffType.BURN_HURT.getType())) {
/* 264 */         long hurtVal = ((Long)hurtValue.getValue()).longValue();
/* 265 */         hurtVal += hurtVal * affectParaBean.getPer() / 10000L;
/* 266 */         hurtValue.setValue(Long.valueOf(hurtVal));
/*     */       } 
/* 268 */       return 0;
/*     */     }
/*     */   },
/*     */   
/* 272 */   BURN_BUFF(602)
/*     */   {
/*     */     public int affect(IFight fight, Pair<Integer, Long> hurtValue, Effect effect, IFighter attacker, IFighter target, ResultPlayData resultPlayData, EffectBean.AffectParaBean affectParaBean)
/*     */     {
/* 276 */       int buffId = affectParaBean.getValue();
/* 277 */       int buffProb = affectParaBean.getPer();
/*     */       
/* 279 */       if (target.hasBuff(BuffType.BURN_HURT.getType()) && RandUtil.isRandTrue(buffProb)) {
/* 280 */         target.addBuff(fight, buffId, ((Long)hurtValue.getValue()).longValue(), attacker, resultPlayData);
/* 281 */         return buffId;
/*     */       } 
/* 283 */       return 0;
/*     */     }
/*     */   },
/*     */   
/* 287 */   BURN_CRIT(603)
/*     */   {
/*     */     public int affect(IFight fight, Pair<Integer, Long> hurtValue, Effect effect, IFighter attacker, IFighter target, ResultPlayData resultPlayData, EffectBean.AffectParaBean affectParaBean)
/*     */     {
/* 291 */       return 0;
/*     */     }
/*     */   };
/*     */ 
/*     */   
/*     */   private int type;
/*     */   
/*     */   AffectFunc(int type) {
/* 299 */     this.type = type;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getType() {
/* 305 */     return this.type;
/*     */   }
/*     */   
/*     */   public abstract int affect(IFight paramIFight, Pair<Integer, Long> paramPair, Effect paramEffect, IFighter paramIFighter1, IFighter paramIFighter2, ResultPlayData paramResultPlayData, EffectBean.AffectParaBean paramAffectParaBean);
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\common\fight\select\affect\AffectFunc.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */