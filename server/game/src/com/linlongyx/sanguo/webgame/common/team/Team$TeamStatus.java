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
/*    */ 
/*    */ public enum TeamStatus
/*    */ {
/* 28 */   CAN_JOIN(0),
/* 29 */   FULL(1),
/* 30 */   IN_FIGHT(2);
/*    */   
/*    */   private int value;
/*    */   
/*    */   TeamStatus(int value) {
/* 35 */     this.value = value;
/*    */   }
/*    */   
/*    */   public int getValue() {
/* 39 */     return this.value;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\common\team\Team$TeamStatus.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */