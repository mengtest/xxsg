/*    */ package com.linlongyx.sanguo.webgame.quartz.job.match;
/*    */ 
/*    */ import com.linlongyx.sanguo.webgame.log.LogUtil;
/*    */ import com.linlongyx.sanguo.webgame.rmi.LogicRmiManager;
/*    */ import com.linlongyx.sanguo.webgame.rmi.data.MatchState;
/*    */ import com.linlongyx.sanguo.webgame.rmi.destiny.match.Match;
/*    */ import com.linlongyx.sanguo.webgame.rmi.destiny.match.MatchUtil;
/*    */ import org.quartz.Job;
/*    */ import org.quartz.JobExecutionContext;
/*    */ import org.quartz.JobExecutionException;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class MatchStepFiveBalanceJob
/*    */   implements Job
/*    */ {
/*    */   public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
/* 21 */     Match match = MatchUtil.getCurMatch();
/* 22 */     if (match == null) {
/* 23 */       LogUtil.errorLog(new Object[] { "match not exist, id: ", Integer.valueOf(MatchUtil.getCurMatchId()) });
/*    */ 
/*    */       
/*    */       return;
/*    */     } 
/*    */ 
/*    */     
/* 30 */     match.changeState(MatchState.stepFiveBalance);
/*    */     
/* 32 */     MatchUtil.finalMatchFight();
/*    */     
/* 34 */     LogicRmiManager.updateGroupPkData(1, MatchState.stepFiveBalance);
/*    */     
/* 36 */     LogUtil.debugLog(new Object[] { "SUN 8:15 match step five balance -> ", Integer.valueOf(match.getMatchId()) });
/*    */     
/* 38 */     MatchUtil.saveToDb();
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\sanguo\webgame\quartz\job\match\MatchStepFiveBalanceJob.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */