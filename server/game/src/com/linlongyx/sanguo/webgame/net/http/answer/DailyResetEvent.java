/*    */ package com.linlongyx.sanguo.webgame.net.http.answer;
/*    */ 
/*    */ import com.linlongyx.core.utils.LogUtils;
/*    */ import com.linlongyx.sanguo.webgame.constant.MContext;
/*    */ import com.linlongyx.sanguo.webgame.net.http.event.IAnswerHttpEvent;
/*    */ import com.linlongyx.sanguo.webgame.processors.luckyTurntable.LuckyTurntableUtil;
/*    */ import com.linlongyx.sanguo.webgame.processors.turnplate.TurnplateUtil;
/*    */ import com.linlongyx.sanguo.webgame.processors.welfare.WelfareUtil;
/*    */ import com.linlongyx.sanguo.webgame.service.ConstantService;
/*    */ import com.linlongyx.sanguo.webgame.service.GroupService;
/*    */ import com.linlongyx.sanguo.webgame.service.LookUpService;
/*    */ import com.linlongyx.sanguo.webgame.service.MentalRankService;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class DailyResetEvent
/*    */   implements IAnswerHttpEvent
/*    */ {
/*    */   public String process(Map<String, List<String>> p) {
/* 24 */     LogUtils.errorLog(new Object[] { "DailyResetEvent reset start" });
/*    */     
/* 26 */     LookUpService.reset();
/* 27 */     WelfareUtil.initRanksMySql();
/*    */ 
/*    */ 
/*    */     
/* 31 */     GroupService groupService = (GroupService)MContext.getBean("groupService");
/* 32 */     groupService.reset();
/*    */     
/* 34 */     TurnplateUtil.resetGoldPool();
/* 35 */     MentalRankService rankService = (MentalRankService)MContext.getBean("mentalRankService");
/* 36 */     rankService.reset();
/*    */     
/* 38 */     WelfareUtil.updateWorldLevel("dailyWorldLevel");
/* 39 */     ConstantService.reset();
/* 40 */     LogUtils.errorLog(new Object[] { "DailyResetEvent reset end" });
/* 41 */     LuckyTurntableUtil.balanceRank();
/* 42 */     return String.valueOf(10001);
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\net\http\answer\DailyResetEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */