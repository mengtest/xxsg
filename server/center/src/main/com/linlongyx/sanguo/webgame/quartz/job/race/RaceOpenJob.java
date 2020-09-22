/*    */ package com.linlongyx.sanguo.webgame.quartz.job.race;
/*    */ 
/*    */ import com.linlongyx.core.utils.TimeUtil;
/*    */ import com.linlongyx.sanguo.webgame.rmi.race.RaceUtil;
/*    */ import org.quartz.Job;
/*    */ import org.quartz.JobExecutionContext;
/*    */ import org.quartz.JobExecutionException;
/*    */ 
/*    */ public class RaceOpenJob
/*    */   implements Job
/*    */ {
/*    */   public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
/* 13 */     if (TimeUtil.getWeek() == 1) {
/* 14 */       RaceUtil.newRace();
/*    */     }
/* 16 */     RaceUtil.openRace();
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\sanguo\webgame\quartz\job\race\RaceOpenJob.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */