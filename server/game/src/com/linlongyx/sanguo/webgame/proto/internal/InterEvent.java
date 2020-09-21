/*    */ package com.linlongyx.sanguo.webgame.proto.internal;
/*    */ 
/*    */ import com.linlongyx.core.framework.protocol.IEvent;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class InterEvent
/*    */   implements IEvent
/*    */ {
/* 15 */   private transient IEvent.EVENT_TYPE type = IEvent.EVENT_TYPE.EVENT_INTER;
/*    */   protected short eventId;
/*    */   
/*    */   public IEvent.EVENT_TYPE getType() {
/* 19 */     return this.type;
/*    */   }
/*    */   protected long playerId;
/*    */   public void setType(IEvent.EVENT_TYPE type) {
/* 23 */     this.type = type;
/*    */   }
/*    */   
/*    */   public short getEventId() {
/* 27 */     return this.eventId;
/*    */   }
/*    */   
/*    */   public void setEventId(short id) {
/* 31 */     this.eventId = id;
/*    */   }
/*    */   
/*    */   public long getPlayerId() {
/* 35 */     return this.playerId;
/*    */   }
/*    */   
/*    */   public void setPlayerId(long playerId) {
/* 39 */     this.playerId = playerId;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\internal\InterEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */