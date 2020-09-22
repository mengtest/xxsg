/*    */ package linlongyx.sanguo.webgame.quartz.job.race;
/*    */ 
/*    */ import com.linlongyx.core.utils.TimeUtil;
/*    */ import com.linlongyx.sanguo.webgame.rmi.race.RaceUtil;
/*    */ import org.quartz.Job;
/*    */ import org.quartz.JobExecutionContext;
/*    */ import org.quartz.JobExecutionException;
/*    */ 
/*    */ public class RaceCloseJob
/*    */   implements Job {
/*    */   public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
/* 12 */     RaceUtil.closeRace();
/*    */     
/* 14 */     if (TimeUtil.getWeek() == 5)
/* 15 */       RaceUtil.balanceRace(); 
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\sanguo\webgame\quartz\job\race\RaceCloseJob.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */