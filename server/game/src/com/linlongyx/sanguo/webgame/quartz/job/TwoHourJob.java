/*    */ package com.linlongyx.sanguo.webgame.quartz.job;
/*    */ 
/*    */ import com.linlongyx.sanguo.webgame.processors.wander.WanderUtil;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class TwoHourJob
/*    */   implements IJob
/*    */ {
/*    */   public short process() {
/* 12 */     WanderUtil.sendWander();
/* 13 */     return 10001;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\quartz\job\TwoHourJob.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */