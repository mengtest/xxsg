/*    */ package com.linlongyx.sanguo.webgame.net.processingevent;
/*    */ 
/*    */ import com.linlongyx.core.utils.TimeUtil;
/*    */ import com.linlongyx.sanguo.webgame.net.http.answer.HttpEventType;
/*    */ import com.linlongyx.sanguo.webgame.net.http.event.IAnswerHttpEvent;
/*    */ import java.util.HashMap;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ import org.slf4j.Logger;
/*    */ import org.slf4j.LoggerFactory;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ProcessingHttpEvents
/*    */ {
/* 22 */   private static final Logger logger = LoggerFactory.getLogger(ProcessingHttpEvents.class);
/* 23 */   private static String key = "legend";
/*    */   
/* 25 */   private static final Map<Integer, IAnswerHttpEvent> eventMap = new HashMap<>();
/*    */   
/*    */   static {
/* 28 */     for (HttpEventType eventType : HttpEventType.values()) {
/* 29 */       eventMap.put(Integer.valueOf(eventType.getCmd()), eventType.getiHttpEvent());
/*    */     }
/*    */   }
/*    */   
/*    */   public static String trigger(Map<String, List<String>> p) {
/* 34 */     long timeOld = TimeUtil.currentTimeMillis();
/* 35 */     int cmd = Integer.parseInt(((List<String>)p.get("cmd")).get(0));
/* 36 */     String method = "";
/*    */     try {
/* 38 */       logger.info("ProcessingHttpEvents.trigger.income.cmd:" + cmd + " method:" + method);
/* 39 */       IAnswerHttpEvent event = eventMap.get(Integer.valueOf(cmd));
/* 40 */       if (event != null) {
/* 41 */         return event.process(p);
/*    */       }
/* 43 */       return String.valueOf(10002);
/* 44 */     } catch (Exception e) {
/* 45 */       logger.error("ProcessingHttpEvents::trigger exception: ", e.getMessage(), e);
/* 46 */       logger.error("cmd: ", new Object[] { Integer.valueOf(cmd), "paramter: ", p });
/*    */     } finally {
/*    */       try {
/* 49 */         logger.debug("ProcessingHttpEvents.trigger.cmd:" + cmd + " method:" + method + " spendTime:" + (
/* 50 */             TimeUtil.currentTimeMillis() - timeOld) + " p:" + p);
/* 51 */       } catch (Exception e2) {
/* 52 */         e2.printStackTrace();
/*    */       } 
/*    */     } 
/* 55 */     return "-1";
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\sanguo\webgame\net\processingevent\ProcessingHttpEvents.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */