/*    */ package com.linlongyx.sanguo.webgame.common.team;
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
/*    */ public enum TeamStatus
/*    */ {
/* 27 */   CAN_JOIN(0),
/* 28 */   FULL(1),
/* 29 */   IN_FIGHT(2);
/*    */   
/*    */   private int value;
/*    */   
/*    */   TeamStatus(int value) {
/* 34 */     this.value = value;
/*    */   }
/*    */   
/*    */   public int getValue() {
/* 38 */     return this.value;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\sanguo\webgame\common\team\Team$TeamStatus.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */