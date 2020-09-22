/*    */ package com.linlongyx.sanguo.webgame.processors.towerOwner;
/*    */ 
/*    */ public enum TowerLayerFightState {
/*  4 */   UNKNOWN(0),
/*  5 */   CAN_OWN(1),
/*  6 */   FIGHTING_CAN_OWN(2),
/*  7 */   FIGHTING_CAN_NOT_OWN(3),
/*  8 */   OWN_CAN_FIGHT(4),
/*  9 */   OWN_PROTECT(5);
/*    */   
/*    */   int state;
/*    */   
/*    */   TowerLayerFightState(int state) {
/* 14 */     this.state = state;
/*    */   }
/*    */   
/*    */   public int getState() {
/* 18 */     return this.state;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\towerOwner\TowerLayerFightState.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */