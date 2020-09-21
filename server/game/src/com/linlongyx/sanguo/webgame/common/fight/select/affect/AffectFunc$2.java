/*    */ package com.linlongyx.sanguo.webgame.common.fight.select.affect;
/*    */ 
/*    */ import com.linlongyx.sanguo.webgame.common.fight.FightCalculator;
/*    */ import com.linlongyx.sanguo.webgame.common.fight.IFight;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ enum null
/*    */ {
/*    */   public int affect(IFight fight, Pair<Integer, Long> hurtValue, Effect effect, IFighter attacker, IFighter target, ResultPlayData resultPlayData, EffectBean.AffectParaBean affectParaBean) {
/* 44 */     long revertHp = target.getMaxHp() * affectParaBean.getPer() / 10000L + affectParaBean.getValue();
/*    */     
/* 46 */     revertHp = FightCalculator.calFinalRecoverHp(revertHp, target);
/*    */     
/* 48 */     target.addHP(revertHp);
/* 49 */     resultPlayData.atomData.add(Integer.valueOf(3));
/* 50 */     resultPlayData.atomData.add(Integer.valueOf(target.getGuid()));
/* 51 */     resultPlayData.atomData.add(Integer.valueOf((int)revertHp));
/* 52 */     return getType();
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\common\fight\select\affect\AffectFunc$2.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */