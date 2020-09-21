/*    */ package com.linlongyx.sanguo.webgame.rmi.data;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public enum MatchState
/*    */   implements Serializable
/*    */ {
/* 12 */   begin(0),
/*    */ 
/*    */   
/* 15 */   stepOnePrepare(1),
/* 16 */   stepOneBalance(2),
/* 17 */   stepTwoPrepare(3),
/* 18 */   stepTwoBalance(4),
/* 19 */   stepThreePrepare(5),
/* 20 */   stepThreeBalance(6),
/*    */ 
/*    */   
/* 23 */   stepFourPrepare(7),
/* 24 */   stepFourBalance(8),
/* 25 */   stepFivePrepare(9),
/* 26 */   stepFiveBalance(10),
/* 27 */   end(11);
/*    */   
/*    */   int state;
/*    */   
/*    */   public int getState() {
/* 32 */     return this.state;
/*    */   }
/*    */   
/*    */   MatchState(int state) {
/* 36 */     this.state = state;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\sanguo\webgame\rmi\data\MatchState.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */