/*    */ package com.linlongyx.sanguo.webgame.net.http.answer;
/*    */ 
/*    */ import com.linlongyx.core.utils.LogUtils;
/*    */ import com.linlongyx.sanguo.webgame.config.bean.ArenaRuleBean;
/*    */ import com.linlongyx.sanguo.webgame.constant.LanguageConstant;
/*    */ import com.linlongyx.sanguo.webgame.net.http.event.IAnswerHttpEvent;
/*    */ import com.linlongyx.sanguo.webgame.processors.arena.ArenaUtil;
/*    */ import com.linlongyx.sanguo.webgame.processors.mail.MailUtil;
/*    */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ArenaRewardEvent
/*    */   implements IAnswerHttpEvent
/*    */ {
/*    */   public String process(Map<String, List<String>> p) {
/* 21 */     ArenaUtil.refresh();
/* 22 */     Map<Integer, Long> ranks = ArenaUtil.getRanks();
/* 23 */     Map<Integer, Object> map = JsonTableService.getJsonTable(ArenaRuleBean.class);
/* 24 */     LogUtils.errorLog(new Object[] { "ArenaRewardEventSize:", Integer.valueOf(ranks.size()) });
/* 25 */     for (Object obj : map.values()) {
/* 26 */       ArenaRuleBean arenaRuleBean = (ArenaRuleBean)obj;
/*    */       
/* 28 */       if (arenaRuleBean.getHighId() != 0 && 
/* 29 */         arenaRuleBean.getDailyReward() != null && arenaRuleBean.getDailyReward().size() > 0) {
/* 30 */         for (int rank = arenaRuleBean.getHighId(); rank <= arenaRuleBean.getLowId(); rank++) {
/* 31 */           long playerId = ((Long)ranks.getOrDefault(Integer.valueOf(rank), Long.valueOf(0L))).longValue();
/* 32 */           if (0L != playerId) {
/*    */             
/* 34 */             String title = LanguageConstant.getLanguage(1701);
/* 35 */             String content = LanguageConstant.getAndReplaceLanguage(1702, new String[] { String.valueOf(rank) });
/* 36 */             LogUtils.errorLog(new Object[] { "ArenaRewardEventRank:", Integer.valueOf(rank), Long.valueOf(playerId) });
/* 37 */             MailUtil.sendSysRewardBeanMail(playerId, arenaRuleBean.getDailyReward(), title, content);
/*    */           } 
/*    */         } 
/*    */       }
/*    */     } 
/* 42 */     return String.valueOf(10001);
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\net\http\answer\ArenaRewardEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */