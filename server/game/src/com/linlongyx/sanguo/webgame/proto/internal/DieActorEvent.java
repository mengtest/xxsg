/*    */ package com.linlongyx.sanguo.webgame.proto.internal;
/*    */ 
/*    */ import com.linlongyx.core.framework.protocol.IEvent;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class DieActorEvent
/*    */   extends InterEvent
/*    */   implements IEvent
/*    */ {
/*    */   public DieActorEvent(long playerId) {
/* 13 */     this.eventId = 30007;
/* 14 */     this.playerId = playerId;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\internal\DieActorEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */