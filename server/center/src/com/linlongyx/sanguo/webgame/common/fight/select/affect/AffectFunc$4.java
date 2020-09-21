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
/*    */ enum null
/*    */ {
/*    */   public int affect(IFight fight, Pair<Integer, Long> hurtValue, Effect effect, IFighter attacker, IFighter target, ResultPlayData resultPlayData, EffectBean.AffectParaBean affectParaBean) {
/* 75 */     boolean isAffect = RandUtil.isRandTrue(affectParaBean.getPer());
/* 76 */     if (!isAffect) {
/* 77 */       return 0;
/*    */     }
/* 79 */     int fury = affectParaBean.getValue();
/* 80 */     int decFury = Math.min(fury, target.getFury().byteValue());
/* 81 */     attacker.addFury((byte)fury, true);
/* 82 */     target.decFury((byte)decFury);
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 89 */     resultPlayData.atomData.add(Integer.valueOf(7));
/* 90 */     resultPlayData.atomData.add(Integer.valueOf(target.getGuid()));
/* 91 */     resultPlayData.atomData.add(Integer.valueOf(-decFury));
/* 92 */     return getType();
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\sanguo\webgame\common\fight\select\affect\AffectFunc$4.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */