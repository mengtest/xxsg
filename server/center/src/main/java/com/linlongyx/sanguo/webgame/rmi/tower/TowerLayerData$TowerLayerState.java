/*    */ package com.linlongyx.sanguo.webgame.rmi.tower;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public enum TowerLayerState
/*    */ {
/*  9 */   FREE(1),
/* 10 */   OWN(2),
/* 11 */   FIGHTING(3);
/*    */   
/*    */   int state;
/*    */   
/*    */   TowerLayerState(int state) {
/* 16 */     this.state = state;
/*    */   }
/*    */   
/*    */   public int getState() {
/* 20 */     return this.state;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\sanguo\webgame\rmi\tower\TowerLayerData$TowerLayerState.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */