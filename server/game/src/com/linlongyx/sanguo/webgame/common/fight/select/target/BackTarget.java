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
/*    */ public class BackTarget
/*    */   implements SingleSelectable
/*    */ {
/*    */   public IFighter target(IFighter attacker, byte targetSide, IFight fight) {
/* 25 */     targetSide = FightUtil.getTargetSide(attacker, targetSide);
/* 26 */     List<IFighter> fighters = fight.getCurFighters(targetSide, true);
/*    */     
/* 28 */     if (null == fighters || fighters.isEmpty()) {
/* 29 */       LogUtil.errorLog(new Object[] { "BackTarget::target", "fighter not exist", attacker, Byte.valueOf(targetSide) });
/* 30 */       return null;
/*    */     } 
/*    */     
/* 33 */     List<IFighter> selectFighters = new ArrayList<>();
/* 34 */     for (IFighter fighter : fighters) {
/* 35 */       if (FightUtil.isFront(fighter.getPos())) {
/*    */         continue;
/*    */       }
/* 38 */       if (FightUtil.sameLine(fighter.getCalcPos(), attacker.getCalcPos())) {
/* 39 */         return fighter;
/*    */       }
/*    */       
/* 42 */       selectFighters.add(fighter);
/*    */     } 
/*    */     
/* 45 */     if (!selectFighters.isEmpty()) {
/* 46 */       return FightUtil.getMinGuidFighter(selectFighters);
/*    */     }
/*    */ 
/*    */     
/* 50 */     for (IFighter fighter : fighters) {
/* 51 */       if (FightUtil.sameLine(fighter.getCalcPos(), attacker.getCalcPos()))
/* 52 */         return fighter; 
/*    */     } 
/* 54 */     return FightUtil.getMinGuidFighter(fighters);
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\common\fight\select\target\BackTarget.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */