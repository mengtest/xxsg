/*    */ package linlongyx.sanguo.webgame.common.fight.select.target;
/*    */ 
/*    */ import linlongyx.sanguo.webgame.common.fight.IFight;
/*    */ import linlongyx.sanguo.webgame.common.fight.fighter.IFighter;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SelfTarget
/*    */   implements SingleSelectable
/*    */ {
/*    */   public IFighter target(IFighter attacker, byte targetSide, IFight fight) {
/* 16 */     return attacker;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\sanguo\webgame\common\fight\select\target\SelfTarget.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */