/*    */ package com.linlongyx.sanguo.webgame.quartz.job.battle;
/*    */ 
/*    */ import com.linlongyx.core.utils.TimeUtil;
/*    */ import com.linlongyx.sanguo.webgame.log.LogUtil;
/*    */ import com.linlongyx.sanguo.webgame.rmi.LogicRmiUtil;
/*    */ import com.linlongyx.sanguo.webgame.rmi.battle.Battle;
/*    */ import com.linlongyx.sanguo.webgame.rmi.battle.BattleUtil;
/*    */ import java.util.Date;
/*    */ import java.util.Iterator;
/*    */ import org.quartz.Job;
/*    */ import org.quartz.JobExecutionContext;
/*    */ import org.quartz.JobExecutionException;
/*    */ 
/*    */ public class BattlePrepareJob
/*    */   implements Job
/*    */ {
/*    */   public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
/* 18 */     BattleUtil.prepareBattle();
/*    */     
/* 20 */     for (Battle battle : BattleUtil.getBattleMap().getBattlesMap().values()) {
/* 21 */       for (Iterator<Integer> iterator = battle.getServerWorldLevelMap().keySet().iterator(); iterator.hasNext(); ) { int serverId = ((Integer)iterator.next()).intValue();
/* 22 */         LogicRmiUtil.sendCrossBattleNotice("BattlePrepareJob::sendCrossBattleNotice", serverId, 1); }
/*    */     
/*    */     } 
/* 25 */     LogUtil.errorLog(new Object[] { "start battle at", TimeUtil.getFormatDate(new Date()) });
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\sanguo\webgame\quartz\job\battle\BattlePrepareJob.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */