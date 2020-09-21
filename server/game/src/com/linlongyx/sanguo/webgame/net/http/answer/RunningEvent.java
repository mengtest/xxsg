/*    */ package com.linlongyx.sanguo.webgame.net.http.answer;
/*    */ 
/*    */ import com.linlongyx.sanguo.webgame.net.http.event.IAnswerHttpEvent;
/*    */ import com.linlongyx.sanguo.webgame.service.LookUpService;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ import java.util.concurrent.atomic.AtomicBoolean;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class RunningEvent
/*    */   implements IAnswerHttpEvent
/*    */ {
/*    */   public String process(Map<String, List<String>> p) {
/* 19 */     if (p.containsKey("running")) {
/*    */       try {
/* 21 */         int running = Integer.parseInt(((List<String>)p.get("running")).get(0));
/* 22 */         if (running == 1) {
/* 23 */           LookUpService.allLogout();
/* 24 */           LookUpService.setRunning(new AtomicBoolean(false));
/*    */         } else {
/* 26 */           LookUpService.setRunning(new AtomicBoolean(true));
/*    */         } 
/* 28 */         return String.valueOf(10001);
/* 29 */       } catch (NumberFormatException e) {
/* 30 */         return String.valueOf(10002);
/*    */       } 
/*    */     }
/* 33 */     return String.valueOf(10002);
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\net\http\answer\RunningEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */