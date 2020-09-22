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
/*    */ public class MinHpPerTarget
/*    */   implements SingleSelectable
/*    */ {
/*    */   public IFighter target(IFighter attacker, byte targetSide, IFight fight) {
/* 17 */     targetSide = FightUtil.getTargetSide(attacker, targetSide);
/* 18 */     List<IFighter> fighters = fight.getSide(targetSide).getCurGroup().getFighters(true);
/* 19 */     if (null == fighters || fighters.isEmpty()) {
/* 20 */       LogUtil.errorLog(new Object[] { "MinHpPerTarget::target", "fighter not exist", attacker, Byte.valueOf(targetSide) });
/* 21 */       return null;
/*    */     } 
/*    */     
/* 24 */     long minHp = Long.MAX_VALUE;
/* 25 */     IFighter selectFighter = null;
/* 26 */     for (IFighter fighter : fighters) {
/* 27 */       long curHp = fighter.getHPPer();
/* 28 */       if (curHp < minHp) {
/* 29 */         selectFighter = fighter;
/* 30 */         minHp = curHp;
/*    */       } 
/*    */     } 
/*    */     
/* 34 */     return selectFighter;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\sanguo\webgame\common\fight\select\target\MinHpPerTarget.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */