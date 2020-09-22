/*    */ package com.linlongyx.sanguo.cross.event;
/*    */ 
/*    */ import com.fasterxml.jackson.databind.ObjectMapper;
/*    */ import com.linlongyx.sanguo.cross.CrossMsgProcessorRegister;
/*    */ import java.io.IOException;
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Events
/*    */ {
/* 13 */   private static Map<Integer, CrossMsgProcessorRegister> registerMap = new HashMap<>();
/* 14 */   private static ObjectMapper objectMapper = new ObjectMapper();
/*    */   
/*    */   static {
/* 17 */     for (CrossMsgProcessorRegister register : CrossMsgProcessorRegister.values()) {
/* 18 */       registerMap.put(Integer.valueOf(register.getEventId()), register);
/*    */     }
/*    */   }
/*    */   
/*    */   public static IResponseEvent decodeToEvent(int eventId, byte[] bytes) throws IOException {
/* 23 */     return (IResponseEvent)objectMapper.readValue(bytes, ((CrossMsgProcessorRegister)registerMap.get(Integer.valueOf(eventId))).getResponse());
/*    */   }
/*    */ 
/*    */   
/*    */   public static byte[] encodeToBytes(IRequestEvent requestEvent) throws IOException {
/* 28 */     return objectMapper.writeValueAsBytes(requestEvent);
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\cross\event\Events.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */