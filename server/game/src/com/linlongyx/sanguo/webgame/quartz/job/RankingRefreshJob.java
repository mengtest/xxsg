/*    */ package com.linlongyx.sanguo.webgame.quartz.job;
/*    */ 
/*    */ import com.linlongyx.core.utils.TimeUtil;
/*    */ import com.linlongyx.sanguo.webgame.constant.MContext;
/*    */ import com.linlongyx.sanguo.webgame.log.LogType;
/*    */ import com.linlongyx.sanguo.webgame.log.LogUtil;
/*    */ import com.linlongyx.sanguo.webgame.service.LookUpService;
/*    */ import com.linlongyx.sanguo.webgame.service.RankBaseService;
/*    */ import com.linlongyx.sanguo.webgame.service.rank.RankingType;
/*    */ import java.util.Map;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class RankingRefreshJob
/*    */   implements IJob
/*    */ {
/*    */   public short process() {
/* 23 */     RankBaseService rankBaseService = (RankBaseService)MContext.getBean("rankBaseService");
/* 24 */     for (RankingType rankingType : RankingType.values()) {
/* 25 */       rankBaseService.refresh(rankingType.getType());
/*    */     }
/* 27 */     Map<Integer, Integer> map = LookUpService.getOnlineNumByChannel();
/* 28 */     for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
/* 29 */       LogUtil.gameLog(LogType.ONLINE, new Object[] { Integer.valueOf(MContext.getServerIdInt()), entry.getKey(), entry.getValue(), TimeUtil.getFormatDate() });
/*    */     } 
/* 31 */     return 10001;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\quartz\job\RankingRefreshJob.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */