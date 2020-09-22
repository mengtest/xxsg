/*    */ package linlongyx.sanguo.webgame.rmi.rank;
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
/*    */ public enum RankState
/*    */ {
/* 16 */   OPEN(1),
/* 17 */   CLOSE(2);
/*    */   
/*    */   private int state;
/*    */   
/*    */   RankState(int state) {
/* 22 */     this.state = state;
/*    */   }
/*    */   
/*    */   public int getState() {
/* 26 */     return this.state;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\sanguo\webgame\rmi\rank\RankDataCache$RankState.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */