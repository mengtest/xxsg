/*    */ package com.linlongyx.sanguo.webgame.net.http.answer;
/*    */ 
/*    */ import com.linlongyx.sanguo.webgame.net.http.event.IAnswerHttpEvent;
/*    */ import com.linlongyx.sanguo.webgame.rmi.race.RaceUtil;
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
/*    */ public class LogoutEvent
/*    */   implements IAnswerHttpEvent
/*    */ {
/*    */   public String process(Map<String, List<String>> p) {
/* 19 */     if (p.containsKey("playerId")) {
/* 20 */       long playerId = Long.parseLong(((List<String>)p.get("playerId")).get(0));
/*    */       
/* 22 */       return String.valueOf(10001);
/* 23 */     }  if (p.containsKey("userId")) {
/* 24 */       long userId = Long.parseLong(((List<String>)p.get("userId")).get(0));
/*    */       
/* 26 */       return String.valueOf(10001);
/*    */     } 
/* 28 */     RaceUtil.saveToDb();
/* 29 */     return String.valueOf(10001);
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\sanguo\webgame\net\http\answer\LogoutEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */