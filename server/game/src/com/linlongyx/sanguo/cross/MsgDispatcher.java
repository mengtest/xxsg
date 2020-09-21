/*    */ package com.linlongyx.sanguo.cross;
/*    */ 
/*    */ import com.linlongyx.sanguo.cross.event.IResponseEvent;
/*    */ import com.linlongyx.sanguo.cross.processor.ICrossProcessor;
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ 
/*    */ 
/*    */ public class MsgDispatcher
/*    */ {
/* 11 */   private static Map<Integer, ICrossProcessor> processorMap = new HashMap<>();
/*    */   
/*    */   static {
/*    */     try {
/* 15 */       for (CrossMsgProcessorRegister register : CrossMsgProcessorRegister.values()) {
/* 16 */         ICrossProcessor processor = register.getMsgProcessor().newInstance();
/* 17 */         processorMap.put(Integer.valueOf(register.getEventId()), processor);
/*    */       } 
/* 19 */     } catch (InstantiationException|IllegalAccessException e) {
/* 20 */       e.printStackTrace();
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static void dispatch(IResponseEvent event) {
/* 29 */     ((ICrossProcessor)processorMap.get(Integer.valueOf(event.getId()))).handle(event);
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\cross\MsgDispatcher.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */