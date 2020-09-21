/*    */ package com.linlongyx.sanguo.webgame.net.http.answer;
/*    */ 
/*    */ import com.linlongyx.sanguo.webgame.constant.MContext;
/*    */ import com.linlongyx.sanguo.webgame.net.http.event.IAnswerHttpEvent;
/*    */ import com.linlongyx.sanguo.webgame.service.RankBaseService;
/*    */ import com.linlongyx.sanguo.webgame.service.rank.RankingType;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class RefreshRankingEvent
/*    */   implements IAnswerHttpEvent
/*    */ {
/*    */   public String process(Map<String, List<String>> p) {
/* 30 */     if (p.containsKey("refresh")) {
/*    */       
/* 32 */       int rankingType = Integer.parseInt(((List<String>)p.get("refresh")).get(0));
/* 33 */       RankBaseService rankBaseService = (RankBaseService)MContext.getBean("rankBaseService");
/* 34 */       rankBaseService.refresh(rankingType);
/*    */     } else {
/*    */       
/* 37 */       RankBaseService rankBaseService = (RankBaseService)MContext.getBean("rankBaseService");
/* 38 */       for (RankingType rankingType : RankingType.values()) {
/* 39 */         rankBaseService.refresh(rankingType.getType());
/*    */       }
/*    */     } 
/* 42 */     return String.valueOf(10001);
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\net\http\answer\RefreshRankingEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */