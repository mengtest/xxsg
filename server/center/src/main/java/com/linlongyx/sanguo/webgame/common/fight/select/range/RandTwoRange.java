/*    */ package com.linlongyx.sanguo.webgame.common.fight.select.range;
/*    */ 
/*    */ import com.linlongyx.sanguo.webgame.common.fight.IFight;
/*    */ import com.linlongyx.sanguo.webgame.common.fight.fighter.IFighter;
/*    */ import com.linlongyx.sanguo.webgame.common.util.RandUtil;
/*    */ import java.util.List;
/*    */ import java.util.Set;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class RandTwoRange
/*    */   implements RangeSelectable
/*    */ {
/*    */   public void target(Set<IFighter> fighters, IFighter selectFighter, IFight fight) {
/* 18 */     List<IFighter> list = fight.getCurFightersExclude(selectFighter.getSide(), true, selectFighter.getGuid());
/* 19 */     fighters.add(selectFighter);
/* 20 */     if (null == list || list.isEmpty())
/*    */       return; 
/* 22 */     int index = RandUtil.randNum(0, list.size() - 1);
/* 23 */     fighters.add(list.get(index));
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\sanguo\webgame\common\fight\select\range\RandTwoRange.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */