/*    */ package com.linlongyx.sanguo.webgame.common.fight.select.range;
/*    */ 
/*    */ import com.linlongyx.sanguo.webgame.common.fight.IFight;
/*    */ import com.linlongyx.sanguo.webgame.common.fight.fighter.IFighter;
/*    */ import java.util.Set;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SingleRange
/*    */   implements RangeSelectable
/*    */ {
/*    */   public void target(Set<IFighter> fighters, IFighter selectFighter, IFight fight) {
/* 14 */     fighters.add(selectFighter);
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\common\fight\select\range\SingleRange.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */