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
/*    */ public enum PkState
/*    */ {
/* 16 */   prepare(0),
/* 17 */   fighting(1),
/* 18 */   end(2);
/*    */   
/*    */   int state;
/*    */   
/*    */   PkState(int state) {
/* 23 */     this.state = state;
/*    */   }
/*    */   
/*    */   public int getState() {
/* 27 */     return this.state;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\sanguo\webgame\rmi\destiny\match\PkData$PkState.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */