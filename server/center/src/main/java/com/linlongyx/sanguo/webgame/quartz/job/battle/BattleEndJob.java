/*    */ package com.linlongyx.sanguo.webgame.quartz.job.battle;
/*    */ 
/*    */ import com.linlongyx.core.utils.TimeUtil;
/*    */ import com.linlongyx.sanguo.webgame.log.LogUtil;
/*    */ import com.linlongyx.sanguo.webgame.rmi.battle.BattleUtil;
/*    */ import java.util.Date;
/*    */ import org.quartz.Job;
/*    */ import org.quartz.JobExecutionContext;
/*    */ import org.quartz.JobExecutionException;
/*    */ 
/*    */ public class BattleEndJob
/*    */   implements Job
/*    */ {
/*    */   public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
/* 15 */     BattleUtil.stopBattle();
/* 16 */     LogUtil.errorLog(new Object[] { "stop battle at", TimeUtil.getFormatDate(new Date()) });
/* 17 */     BattleUtil.balanceBattle();
/* 18 */     LogUtil.errorLog(new Object[] { "balance battle at", TimeUtil.getFormatDate(new Date()) });
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\sanguo\webgame\quartz\job\battle\BattleEndJob.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */