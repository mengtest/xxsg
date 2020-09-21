/*    */ package com.linlongyx.sanguo.webgame.common.fight.select.affect;
/*    */ 
/*    */ import com.linlongyx.sanguo.webgame.common.fight.IFight;
/*    */ import com.linlongyx.sanguo.webgame.common.fight.fighter.IFighter;
/*    */ import com.linlongyx.sanguo.webgame.common.fight.skill.Effect;
/*    */ import com.linlongyx.sanguo.webgame.common.structure.Pair;
/*    */ import com.linlongyx.sanguo.webgame.common.util.RandUtil;
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
/*    */ 
/*    */ enum null
/*    */ {
/*    */   public int affect(IFight fight, Pair<Integer, Long> hurtValue, Effect effect, IFighter attacker, IFighter target, ResultPlayData resultPlayData, EffectBean.AffectParaBean affectParaBean) {
/* 76 */     boolean isAffect = RandUtil.isRandTrue(affectParaBean.getPer());
/* 77 */     if (!isAffect) {
/* 78 */       return 0;
/*    */     }
/* 80 */     int fury = affectParaBean.getValue();
/* 81 */     int decFury = Math.min(fury, target.getFury().byteValue());
/* 82 */     attacker.addFury((byte)fury, true);
/* 83 */     target.decFury((byte)decFury);
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 90 */     resultPlayData.atomData.add(Integer.valueOf(7));
/* 91 */     resultPlayData.atomData.add(Integer.valueOf(target.getGuid()));
/* 92 */     resultPlayData.atomData.add(Integer.valueOf(-decFury));
/* 93 */     return getType();
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\common\fight\select\affect\AffectFunc$4.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */