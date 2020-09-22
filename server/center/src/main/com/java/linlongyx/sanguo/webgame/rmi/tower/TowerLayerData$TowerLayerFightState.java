/*    */ package linlongyx.sanguo.webgame.rmi.tower;
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
/*    */ public enum TowerLayerFightState
/*    */ {
/* 25 */   UNKNOWN(0),
/* 26 */   CAN_OWN(1),
/* 27 */   FIGHTING_CAN_OWN(2),
/* 28 */   FIGHTING_CAN_NOT_OWN(3),
/* 29 */   OWN_CAN_FIGHT(4),
/* 30 */   OWN_PROTECT(5);
/*    */   
/*    */   int state;
/*    */   
/*    */   TowerLayerFightState(int state) {
/* 35 */     this.state = state;
/*    */   }
/*    */   
/*    */   public int getState() {
/* 39 */     return this.state;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\sanguo\webgame\rmi\tower\TowerLayerData$TowerLayerFightState.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */