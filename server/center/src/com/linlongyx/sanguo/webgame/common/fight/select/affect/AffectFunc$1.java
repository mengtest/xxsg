/*    */ package com.linlongyx.sanguo.webgame.common.fight.select.affect;
/*    */ 
/*    */ import com.linlongyx.sanguo.webgame.common.fight.FightCalculator;
/*    */ import com.linlongyx.sanguo.webgame.common.fight.IFight;
/*    */ import com.linlongyx.sanguo.webgame.common.fight.attribute.AttributeType;
/*    */ import com.linlongyx.sanguo.webgame.common.fight.fighter.IFighter;
/*    */ import com.linlongyx.sanguo.webgame.common.fight.skill.Effect;
/*    */ import com.linlongyx.sanguo.webgame.common.structure.Pair;
/*    */ import com.linlongyx.sanguo.webgame.config.bean.EffectBean;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.ResultPlayData;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ enum null
/*    */ {
/*    */   public int affect(IFight fight, Pair<Integer, Long> hurtValue, Effect effect, IFighter attacker, IFighter target, ResultPlayData resultPlayData, EffectBean.AffectParaBean affectParaBean) {
/* 28 */     long revertHp = attacker.getCalcAttr(AttributeType.ATTACK) * affectParaBean.getPer() / 10000L + affectParaBean.getValue();
/* 29 */     revertHp = FightCalculator.calFinalRecoverHp(revertHp, target);
/*    */     
/* 31 */     target.addHP(revertHp);
/* 32 */     resultPlayData.atomData.add(Integer.valueOf(3));
/* 33 */     resultPlayData.atomData.add(Integer.valueOf(target.getGuid()));
/* 34 */     resultPlayData.atomData.add(Integer.valueOf((int)revertHp));
/* 35 */     return getType();
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\sanguo\webgame\common\fight\select\affect\AffectFunc$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */