/*    */ package com.linlongyx.core.framework.protocol;
/*    */ 
/*    */ 
/*    */ public class TransmitEvent
/*    */   extends InterEvent
/*    */   implements IEvent
/*    */ {
/*    */   public ResponseBase response;
/*    */   
/*    */   public TransmitEvent() {
/* 11 */     setEventId((short)1);
/*    */   }
/*    */   
/*    */   public TransmitEvent(long playerId) {
/* 15 */     this();
/* 16 */     setPlayerId(playerId);
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\core\framework\protocol\TransmitEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */