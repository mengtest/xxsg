/*    */ package com.linlongyx.sanguo.webgame.common.fight.select.target;
/*    */ 
/*    */ import com.linlongyx.sanguo.webgame.common.fight.FightUtil;
/*    */ import com.linlongyx.sanguo.webgame.common.fight.IFight;
/*    */ import com.linlongyx.sanguo.webgame.common.fight.fighter.IFighter;
/*    */ import com.linlongyx.sanguo.webgame.common.util.RandUtil;
/*    */ import java.util.List;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class RandomTarget
/*    */   implements SingleSelectable
/*    */ {
/*    */   public IFighter target(IFighter attacker, byte targetSide, IFight fight) {
/* 18 */     targetSide = FightUtil.getTargetSide(attacker, targetSide);
/* 19 */     List<IFighter> fighters = fight.getSide(targetSide).getCurGroup().getFighters(true);
/* 20 */     if (fighters.isEmpty()) {
/* 21 */       return null;
/*    */     }
/* 23 */     int index = RandUtil.randNum(0, fighters.size() - 1);
/*    */     
/* 25 */     return fighters.get(index);
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\common\fight\select\target\RandomTarget.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */