/*    */ package com.linlongyx.sanguo.webgame.quartz.job.match;
/*    */ 
/*    */ import com.linlongyx.sanguo.webgame.log.LogUtil;
/*    */ import com.linlongyx.sanguo.webgame.rmi.LogicRmiManager;
/*    */ import com.linlongyx.sanguo.webgame.rmi.data.MatchState;
/*    */ import com.linlongyx.sanguo.webgame.rmi.destiny.DestinyFileUtil;
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
/*    */ public class MatchStepBeginJob
/*    */   implements Job
/*    */ {
/*    */   public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
/* 21 */     LogicRmiManager.balanceDestinyTops();
/* 22 */     DestinyFileUtil.saveRecord();
/*    */     
/* 24 */     Match match = MatchUtil.getCurMatch();
/* 25 */     if (match == null) {
/* 26 */       LogUtil.errorLog(new Object[] { "match not exist, id: ", Integer.valueOf(MatchUtil.getCurMatchId()) });
/*    */       
/*    */       return;
/*    */     } 
/* 30 */     match.changeState(MatchState.begin);
/*    */     
/* 32 */     MatchUtil.quarterMatchQuarterInit();
/*    */     
/* 34 */     MatchUtil.saveToDb();
/* 35 */     LogUtil.debugLog(new Object[] { "match begin id -> ", Integer.valueOf(match.getMatchId()) });
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\sanguo\webgame\quartz\job\match\MatchStepBeginJob.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */