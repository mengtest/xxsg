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
/*    */ 
/*    */ public class CrossRange
/*    */   implements RangeSelectable
/*    */ {
/*    */   public void target(Set<IFighter> fighters, IFighter selectFighter, IFight fight) {
/* 17 */     List<IFighter> list = fight.getCurFighters(selectFighter.getSide(), true);
/*    */     
/* 19 */     list.forEach(fighter -> {
/*    */           if (fighter.getCalcPos() != selectFighter.getCalcPos())
/*    */             if (FightUtil.sameLine(selectFighter.getCalcPos(), fighter.getCalcPos())) {
/*    */               fighters.add(fighter);
/*    */             } else if (FightUtil.isAround(selectFighter.getCalcPos(), fighter.getCalcPos())) {
/*    */               fighters.add(fighter);
/*    */             }  
/*    */         });
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\common\fight\select\range\CrossRange.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */