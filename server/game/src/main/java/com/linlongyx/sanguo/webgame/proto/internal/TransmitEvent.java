/*    */ package com.linlongyx.sanguo.webgame.proto.internal;
/*    */ 
/*    */ import com.linlongyx.core.framework.protocol.IEvent;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class TransmitEvent
/*    */   extends InterEvent
/*    */   implements IEvent
/*    */ {
/*    */   public ResponseBase response;
/*    */   
/*    */   public TransmitEvent() {
/* 16 */     setEventId((short)30000);
/*    */   }
/*    */   
/*    */   public TransmitEvent(long playerId) {
/* 20 */     this();
/* 21 */     setPlayerId(playerId);
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\internal\TransmitEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */