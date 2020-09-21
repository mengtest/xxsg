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
/*    */ public class RandThreeRange
/*    */   implements RangeSelectable
/*    */ {
/*    */   public void target(Set<IFighter> fighters, IFighter selectFighter, IFight fight) {
/* 18 */     List<IFighter> list = fight.getCurFightersExclude(selectFighter.getSide(), true, selectFighter.getGuid());
/* 19 */     fighters.add(selectFighter);
/* 20 */     if (null == list || list.isEmpty()) {
/*    */       return;
/*    */     }
/* 23 */     while (list.size() > 2) {
/* 24 */       int index = RandUtil.randNum(0, list.size() - 1);
/* 25 */       list.remove(index);
/*    */     } 
/*    */     
/* 28 */     fighters.addAll(list);
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\common\fight\select\range\RandThreeRange.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */