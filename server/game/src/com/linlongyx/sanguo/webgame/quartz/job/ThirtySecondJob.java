/*    */ package com.linlongyx.sanguo.webgame.quartz.job;
/*    */ 
/*    */ import com.linlongyx.sanguo.webgame.processors.crossRankAct.CrossRankActUtil;
/*    */ import com.linlongyx.sanguo.webgame.processors.rankAct.RankActUtil;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ThirtySecondJob
/*    */   implements IJob
/*    */ {
/*    */   public short process() {
/* 15 */     RankActUtil.refreshRanks();
/* 16 */     CrossRankActUtil.balanceCrossRankAct();
/* 17 */     return 10001;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\quartz\job\ThirtySecondJob.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */