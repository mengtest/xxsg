/*    */ package com.linlongyx.sanguo.webgame.common.fight.select.target;
/*    */ 
/*    */ import com.linlongyx.sanguo.webgame.common.fight.FightUtil;
/*    */ import com.linlongyx.sanguo.webgame.common.fight.IFight;
/*    */ import com.linlongyx.sanguo.webgame.common.fight.fighter.IFighter;
/*    */ import java.util.List;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class DefaultTarget
/*    */   implements SingleSelectable
/*    */ {
/*    */   public IFighter target(IFighter attacker, byte targetSide, IFight fight) {
/* 19 */     byte side = FightUtil.getTargetSide(attacker, targetSide);
/* 20 */     List<IFighter> fighters = fight.getCurFighters(side, true);
/*    */     
/* 22 */     for (IFighter fighter : fighters) {
/* 23 */       if (fighter.getPos() != attacker.getPos())
/*    */         continue; 
/* 25 */       return fighter;
/*    */     } 
/* 27 */     return FightUtil.getMinGuidFighter(fighters);
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\common\fight\select\target\DefaultTarget.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */