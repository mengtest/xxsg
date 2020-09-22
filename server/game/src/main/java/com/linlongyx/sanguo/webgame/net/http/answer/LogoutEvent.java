/*    */ package com.linlongyx.sanguo.webgame.net.http.answer;
/*    */ 
/*    */ import com.linlongyx.sanguo.webgame.net.http.event.IAnswerHttpEvent;
/*    */ import com.linlongyx.sanguo.webgame.service.LookUpService;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class LogoutEvent
/*    */   implements IAnswerHttpEvent
/*    */ {
/*    */   public String process(Map<String, List<String>> p) {
/* 18 */     if (p.containsKey("playerId")) {
/* 19 */       long playerId = Long.parseLong(((List<String>)p.get("playerId")).get(0));
/* 20 */       LookUpService.bePlayerLogout(playerId);
/* 21 */       return String.valueOf(10001);
/* 22 */     }  if (p.containsKey("userId")) {
/* 23 */       long userId = Long.parseLong(((List<String>)p.get("userId")).get(0));
/* 24 */       LookUpService.beUserLogout(userId);
/* 25 */       return String.valueOf(10001);
/*    */     } 
/* 27 */     return String.valueOf(10002);
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\net\http\answer\LogoutEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */