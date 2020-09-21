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
/*    */ public enum ZoneState
/*    */ {
/* 25 */   empty(0),
/* 26 */   notFull(1),
/* 27 */   full(2);
/*    */   
/*    */   private int state;
/*    */   
/*    */   ZoneState(int state) {
/* 32 */     this.state = state;
/*    */   }
/*    */   
/*    */   public int getState() {
/* 36 */     return this.state;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\sanguo\webgame\rmi\destiny\match\Zone$ZoneState.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */