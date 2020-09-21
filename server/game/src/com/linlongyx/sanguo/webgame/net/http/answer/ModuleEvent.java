/*    */ package com.linlongyx.sanguo.webgame.net.http.answer;
/*    */ 
/*    */ import com.linlongyx.sanguo.webgame.net.http.event.IAnswerHttpEvent;
/*    */ import com.linlongyx.sanguo.webgame.processors.MsgDispatcher;
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
/*    */ public class ModuleEvent
/*    */   implements IAnswerHttpEvent
/*    */ {
/*    */   public String process(Map<String, List<String>> p) {
/* 22 */     if (p.containsKey("module")) {
/* 23 */       short module = Short.parseShort(((List<String>)p.get("module")).get(0));
/* 24 */       boolean open = (Integer.parseInt(((List<String>)p.get("open")).get(0)) != 0);
/* 25 */       MsgDispatcher.setModuleOpen(module, open);
/*    */     }
/* 27 */     else if (p.containsKey("processor")) {
/* 28 */       short processor = Short.parseShort(((List<String>)p.get("processor")).get(0));
/* 29 */       boolean open = (Integer.parseInt(((List<String>)p.get("open")).get(0)) != 0);
/* 30 */       MsgDispatcher.setProcessorOpen(processor, open);
/*    */     } 
/* 32 */     return String.valueOf(10001);
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\net\http\answer\ModuleEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */