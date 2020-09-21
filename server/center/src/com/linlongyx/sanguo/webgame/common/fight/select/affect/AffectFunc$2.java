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
/*    */ enum null
/*    */ {
/*    */   public int affect(IFight fight, Pair<Integer, Long> hurtValue, Effect effect, IFighter attacker, IFighter target, ResultPlayData resultPlayData, EffectBean.AffectParaBean affectParaBean) {
/* 43 */     long revertHp = target.getMaxHp() * affectParaBean.getPer() / 10000L + affectParaBean.getValue();
/*    */     
/* 45 */     revertHp = FightCalculator.calFinalRecoverHp(revertHp, target);
/*    */     
/* 47 */     target.addHP(revertHp);
/* 48 */     resultPlayData.atomData.add(Integer.valueOf(3));
/* 49 */     resultPlayData.atomData.add(Integer.valueOf(target.getGuid()));
/* 50 */     resultPlayData.atomData.add(Integer.valueOf((int)revertHp));
/* 51 */     return getType();
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\sanguo\webgame\common\fight\select\affect\AffectFunc$2.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */