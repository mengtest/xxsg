/*    */ package com.linlongyx.sanguo.client.processors;
/*    */ 
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.client.actor.Actor;
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class MsgDispatcher
/*    */ {
/* 15 */   private static final Map<Short, ProcessorBase> processorMap = new HashMap<>();
/* 16 */   private static final Map<Short, Class> responseMap = (Map)new HashMap<>();
/*    */   
/*    */   static {
/* 19 */     for (MsgProcessorRegister register : MsgProcessorRegister.values()) {
/* 20 */       processorMap.put(Short.valueOf(register.getMsgCode()), register.getMsgProcessor());
/* 21 */       responseMap.put(Short.valueOf(register.getMsgCode()), register.getResponse());
/*    */     } 
/*    */   }
/*    */   
/*    */   public static ResponseBase getResponse(short msgId) {
/* 26 */     Class<ResponseBase> clazz = responseMap.get(Short.valueOf(msgId));
/* 27 */     if (null != clazz) {
/*    */       try {
/* 29 */         return clazz.newInstance();
/* 30 */       } catch (InstantiationException|IllegalAccessException e) {
/* 31 */         e.printStackTrace();
/*    */       } 
/*    */     }
/* 34 */     return null;
/*    */   }
/*    */   
/*    */   public static void dispatch(Actor client, ResponseBase msg) {
/* 38 */     ProcessorBase processor = processorMap.get(Short.valueOf(msg.getEventId()));
/* 39 */     processor.handle(client, msg);
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\client\processors\MsgDispatcher.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */