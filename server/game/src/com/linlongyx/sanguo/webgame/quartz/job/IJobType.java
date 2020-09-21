/*    */ package com.linlongyx.sanguo.webgame.quartz.job;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public enum IJobType
/*    */ {
/* 10 */   RankingRefresh(20001, new RankingRefreshJob()),
/* 11 */   FivePer(20003, new FiveMinutesJob()),
/* 12 */   CrossRaceLocalBalance(20004, new CrossRaceLocalBalanceJob()),
/* 13 */   ThirtySecondJob(20005, new ThirtySecondJob()),
/* 14 */   TowHourWanderJob(20006, new TwoHourJob());
/*    */   
/*    */   private int cmd;
/*    */   
/*    */   private IJob iJob;
/*    */ 
/*    */   
/*    */   IJobType(int cmd, IJob iJob) {
/* 22 */     this.cmd = cmd;
/* 23 */     this.iJob = iJob;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int getCmd() {
/* 30 */     return this.cmd;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public IJob getiJob() {
/* 37 */     return this.iJob;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\quartz\job\IJobType.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */