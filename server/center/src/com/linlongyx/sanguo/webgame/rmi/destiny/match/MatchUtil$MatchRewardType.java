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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public enum MatchRewardType
/*    */ {
/* 40 */   reward32(6),
/* 41 */   reward16(5),
/* 42 */   reward8(4),
/* 43 */   reward4(3),
/* 44 */   reward2(2),
/* 45 */   reward1(1);
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   private int type;
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   MatchRewardType(int type) {
/* 56 */     this.type = type;
/*    */   }
/*    */   
/*    */   public int getType() {
/* 60 */     return this.type;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\sanguo\webgame\rmi\destiny\match\MatchUtil$MatchRewardType.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */