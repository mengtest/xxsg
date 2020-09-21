/*    */ package com.linlongyx.sanguo.webgame.quartz.job;
/*    */ 
/*    */ import com.linlongyx.core.utils.TimeUtil;
/*    */ import com.linlongyx.sanguo.webgame.log.LogUtil;
/*    */ import com.linlongyx.sanguo.webgame.processors.crossRace.CrossRaceUtil;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CrossRaceLocalBalanceJob
/*    */   implements IJob
/*    */ {
/*    */   public short process() {
/* 18 */     LogUtil.errorLog(new Object[] { "cross race local balance at", TimeUtil.getFormatDate() });
/* 19 */     CrossRaceUtil.balanceOnlinePlayerRace();
/* 20 */     return 10001;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\quartz\job\CrossRaceLocalBalanceJob.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */