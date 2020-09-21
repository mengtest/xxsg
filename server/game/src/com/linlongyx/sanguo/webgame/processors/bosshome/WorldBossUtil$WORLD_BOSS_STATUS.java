/*    */ package com.linlongyx.sanguo.webgame.processors.bosshome;
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
/*    */ public enum WORLD_BOSS_STATUS
/*    */ {
/* 57 */   CLOSE(1),
/* 58 */   OPENING(2),
/* 59 */   WAITING(3),
/* 60 */   END(4);
/*    */   
/*    */   byte type;
/*    */   
/*    */   WORLD_BOSS_STATUS(int type) {
/* 65 */     this.type = (byte)type;
/*    */   }
/*    */   
/*    */   public byte getType() {
/* 69 */     return this.type;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\bosshome\WorldBossUtil$WORLD_BOSS_STATUS.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */