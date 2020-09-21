/*    */ package com.linlongyx.sanguo.webgame.rmi.battle;
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
/*    */ public enum BattleState
/*    */ {
/* 62 */   PREPARE(0),
/* 63 */   OPEN(1),
/* 64 */   CLOSE(2);
/*    */   private int state;
/*    */   
/*    */   BattleState(int state) {
/* 68 */     this.state = state;
/*    */   }
/*    */   
/*    */   public int getState() {
/* 72 */     return this.state;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\sanguo\webgame\rmi\battle\BattleUtil$BattleState.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */