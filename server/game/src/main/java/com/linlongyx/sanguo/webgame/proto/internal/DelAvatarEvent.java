/*    */ package com.linlongyx.sanguo.webgame.proto.internal;
/*    */ 
/*    */ import com.linlongyx.core.framework.protocol.IEvent;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class DelAvatarEvent
/*    */   extends InterEvent
/*    */   implements IEvent
/*    */ {
/*    */   public DelAvatarEvent(long playerId) {
/* 13 */     this.eventId = 30003;
/* 14 */     this.playerId = playerId;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\internal\DelAvatarEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */