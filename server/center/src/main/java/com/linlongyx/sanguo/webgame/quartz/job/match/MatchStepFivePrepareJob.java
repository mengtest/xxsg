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
/*    */ public class MatchStepFivePrepareJob
/*    */   implements Job
/*    */ {
/*    */   public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
/* 20 */     Match match = MatchUtil.getCurMatch();
/* 21 */     if (match == null) {
/* 22 */       LogUtil.errorLog(new Object[] { "match not exist, id: ", Integer.valueOf(MatchUtil.getCurMatchId()) });
/*    */ 
/*    */       
/*    */       return;
/*    */     } 
/*    */ 
/*    */     
/* 29 */     match.changeState(MatchState.stepFivePrepare);
/* 30 */     MatchUtil.finalMatchInit();
/*    */     
/* 32 */     LogicRmiManager.updateGroupPkData(1, MatchState.stepFivePrepare);
/* 33 */     LogUtil.debugLog(new Object[] { "SUN 8:10 match step five prepare -> ", Integer.valueOf(match.getMatchId()) });
/* 34 */     MatchUtil.saveToDb();
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\sanguo\webgame\quartz\job\match\MatchStepFivePrepareJob.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */