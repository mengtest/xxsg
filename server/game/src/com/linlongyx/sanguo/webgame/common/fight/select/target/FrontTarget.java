/*    */ package com.linlongyx.sanguo.webgame.common.fight.select.target;
/*    */ 
/*    */ import com.linlongyx.sanguo.webgame.common.fight.FightUtil;
/*    */ import com.linlongyx.sanguo.webgame.common.fight.IFight;
/*    */ import com.linlongyx.sanguo.webgame.common.fight.fighter.IFighter;
/*    */ import com.linlongyx.sanguo.webgame.log.LogUtil;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
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
/*    */ public class FrontTarget
/*    */   implements SingleSelectable
/*    */ {
/*    */   public IFighter target(IFighter attacker, byte targetSide, IFight fight) {
/* 25 */     targetSide = FightUtil.getTargetSide(attacker, targetSide);
/* 26 */     List<IFighter> fighters = fight.getCurFighters(targetSide, true);
/*    */     
/* 28 */     if (null == fighters || fighters.isEmpty()) {
/* 29 */       LogUtil.errorLog(new Object[] { "FrontTarget::target", "fighter not exist", attacker, Byte.valueOf(targetSide) });
/* 30 */       return null;
/*    */     } 
/*    */ 
/*    */     
/* 34 */     List<IFighter> selectFighters = new ArrayList<>();
/* 35 */     for (IFighter fighter : fighters) {
/* 36 */       if (!FightUtil.isFront(fighter.getPos())) {
/*    */         continue;
/*    */       }
/* 39 */       if (FightUtil.sameLine(fighter.getCalcPos(), attacker.getCalcPos())) {
/* 40 */         return fighter;
/*    */       }
/* 42 */       selectFighters.add(fighter);
/*    */     } 
/* 44 */     if (!selectFighters.isEmpty()) {
/* 45 */       return FightUtil.getMinGuidFighter(selectFighters);
/*    */     }
/*    */     
/* 48 */     for (IFighter fighter : fighters) {
/* 49 */       if (FightUtil.sameLine(fighter.getCalcPos(), attacker.getCalcPos())) {
/* 50 */         return fighter;
/*    */       }
/*    */     } 
/* 53 */     return FightUtil.getMinGuidFighter(fighters);
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\common\fight\select\target\FrontTarget.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */