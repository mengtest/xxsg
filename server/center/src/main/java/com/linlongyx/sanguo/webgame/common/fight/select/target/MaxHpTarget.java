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
/*    */ public class MaxHpTarget
/*    */   implements SingleSelectable
/*    */ {
/*    */   public IFighter target(IFighter attacker, byte targetSide, IFight fight) {
/* 16 */     targetSide = FightUtil.getTargetSide(attacker, targetSide);
/* 17 */     List<IFighter> fighters = fight.getSide(targetSide).getCurGroup().getFighters(true);
/* 18 */     if (null == fighters || fighters.isEmpty()) {
/* 19 */       LogUtil.errorLog(new Object[] { "MaxHpTarget::target", "fighter not exist", attacker, Byte.valueOf(targetSide) });
/* 20 */       return null;
/*    */     } 
/*    */     
/* 23 */     long maxHp = -1L;
/* 24 */     IFighter selectFighter = null;
/* 25 */     for (IFighter fighter : fighters) {
/* 26 */       long curHp = fighter.getHP();
/* 27 */       if (curHp > maxHp) {
/* 28 */         selectFighter = fighter;
/* 29 */         maxHp = curHp;
/*    */       } 
/*    */     } 
/*    */     
/* 33 */     return selectFighter;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\sanguo\webgame\common\fight\select\target\MaxHpTarget.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */