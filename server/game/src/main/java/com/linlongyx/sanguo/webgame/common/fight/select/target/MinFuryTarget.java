/*    */ package com.linlongyx.sanguo.webgame.common.fight.select.target;
/*    */ 
/*    */ import com.linlongyx.sanguo.webgame.common.fight.FightUtil;
/*    */ import com.linlongyx.sanguo.webgame.common.fight.IFight;
/*    */ import com.linlongyx.sanguo.webgame.common.fight.fighter.IFighter;
/*    */ import com.linlongyx.sanguo.webgame.log.LogUtil;
/*    */ import java.util.List;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class MinFuryTarget
/*    */   implements SingleSelectable
/*    */ {
/*    */   public IFighter target(IFighter attacker, byte targetSide, IFight fight) {
/* 17 */     targetSide = FightUtil.getTargetSide(attacker, targetSide);
/* 18 */     List<IFighter> fighters = fight.getCurFighters(targetSide, true);
/* 19 */     if (null == fighters || fighters.isEmpty()) {
/* 20 */       LogUtil.errorLog(new Object[] { "MinFuryTarget::target", "fighter not exist", attacker, Byte.valueOf(targetSide) });
/* 21 */       return null;
/*    */     } 
/*    */     
/* 24 */     return fighters.stream().min((f1, f2) -> f1.getFury().byteValue() - f2.getFury().byteValue()).get();
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\common\fight\select\target\MinFuryTarget.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */