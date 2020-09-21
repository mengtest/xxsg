/*    */ package com.linlongyx.sanguo.webgame.rmi.destiny.match;
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
/*    */ public enum PkSideState
/*    */ {
/* 32 */   none(0),
/* 33 */   singleLeft(1),
/* 34 */   singleRight(2),
/* 35 */   doubleSide(3);
/*    */   
/*    */   int state;
/*    */ 
/*    */   
/*    */   PkSideState(int state) {
/* 41 */     this.state = state;
/*    */   }
/*    */   
/*    */   public int getState() {
/* 45 */     return this.state;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\sanguo\webgame\rmi\destiny\match\PkData$PkSideState.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */