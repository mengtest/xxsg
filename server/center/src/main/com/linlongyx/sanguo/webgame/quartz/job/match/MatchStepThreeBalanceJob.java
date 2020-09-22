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
/*    */ public class MatchStepThreeBalanceJob
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
/* 29 */     match.changeState(MatchState.stepThreeBalance);
/*    */     
/* 31 */     MatchUtil.quarterMatchFinalFight();
/*    */     
/* 33 */     LogicRmiManager.updateGroupPkData(0, MatchState.stepThreeBalance);
/* 34 */     LogUtil.debugLog(new Object[] { "8:25 match step three balance -> ", Integer.valueOf(match.getMatchId()) });
/* 35 */     MatchUtil.saveToDb();
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\sanguo\webgame\quartz\job\match\MatchStepThreeBalanceJob.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */