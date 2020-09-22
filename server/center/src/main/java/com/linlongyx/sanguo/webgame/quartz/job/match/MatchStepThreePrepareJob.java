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
/*    */ public class MatchStepThreePrepareJob
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
/* 30 */     match.changeState(MatchState.stepThreePrepare);
/* 31 */     MatchUtil.quarterMatchFinalInit();
/*    */     
/* 33 */     LogicRmiManager.updateGroupPkData(0, MatchState.stepThreePrepare);
/* 34 */     LogUtil.debugLog(new Object[] { "8:20 match step three prepare -> ", Integer.valueOf(match.getMatchId()) });
/* 35 */     MatchUtil.saveToDb();
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\sanguo\webgame\quartz\job\match\MatchStepThreePrepareJob.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */