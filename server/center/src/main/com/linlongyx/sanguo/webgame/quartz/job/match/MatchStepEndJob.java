/*    */ package com.linlongyx.sanguo.webgame.quartz.job.match;
/*    */ 
/*    */ import com.linlongyx.sanguo.webgame.log.LogUtil;
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
/*    */ public class MatchStepEndJob
/*    */   implements Job
/*    */ {
/*    */   public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
/* 19 */     Match match = MatchUtil.getCurMatch();
/* 20 */     if (match == null) {
/* 21 */       LogUtil.errorLog(new Object[] { "match not exist, id: ", Integer.valueOf(MatchUtil.getCurMatchId()) });
/*    */ 
/*    */       
/*    */       return;
/*    */     } 
/*    */ 
/*    */     
/* 28 */     match.changeState(MatchState.end);
/* 29 */     match.balanceRank();
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 34 */     Match nextMatch = MatchUtil.newMatch();
/*    */     
/* 36 */     LogUtil.debugLog(new Object[] { "SUN add next match -> ", Integer.valueOf(nextMatch.getMatchId()) });
/* 37 */     MatchUtil.saveToDb();
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\sanguo\webgame\quartz\job\match\MatchStepEndJob.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */