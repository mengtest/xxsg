/*    */ package com.linlongyx.sanguo.webgame.job;
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
/*    */ public abstract class AbstractSysJob
/*    */   implements SysJob
/*    */ {
/*    */   protected long initialDelay;
/*    */   protected long period;
/*    */   
/*    */   public long getInitialDelay() {
/* 21 */     return this.initialDelay;
/*    */   }
/*    */   
/*    */   public void setInitialDelay(long initialDelay) {
/* 25 */     this.initialDelay = initialDelay;
/*    */   }
/*    */   
/*    */   public long getPeriod() {
/* 29 */     return this.period;
/*    */   }
/*    */   
/*    */   public void setPeriod(long period) {
/* 33 */     this.period = period;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\job\AbstractSysJob.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */