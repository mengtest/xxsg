/*    */ package com.linlongyx.sanguo.webgame.common.fight.select.range;
/*    */ 
/*    */ import com.linlongyx.sanguo.webgame.common.fight.FightUtil;
/*    */ import com.linlongyx.sanguo.webgame.common.fight.IFight;
/*    */ import com.linlongyx.sanguo.webgame.common.fight.fighter.IFighter;
/*    */ import java.util.List;
/*    */ import java.util.Set;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class RowRange
/*    */   implements RangeSelectable
/*    */ {
/*    */   public void target(Set<IFighter> fighters, IFighter selectFighter, IFight fight) {
/* 16 */     List<IFighter> list = fight.getCurFighters(selectFighter.getSide(), true);
/* 17 */     fighters.add(selectFighter);
/* 18 */     list.forEach(fighter -> {
/*    */           if (FightUtil.sameRow(selectFighter.getCalcPos(), fighter.getCalcPos()))
/*    */             fighters.add(fighter); 
/*    */         });
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\sanguo\webgame\common\fight\select\range\RowRange.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */