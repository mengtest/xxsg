/*    */ package com.linlongyx.core.framework.protocol;
/*    */ 
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
/* 14 */   private transient IEvent.EVENT_TYPE type = IEvent.EVENT_TYPE.EVENT_INTER;
/*    */   protected short eventId;
/*    */   
/*    */   public IEvent.EVENT_TYPE getType() {
/* 18 */     return this.type;
/*    */   }
/*    */   protected long playerId;
/*    */   public void setType(IEvent.EVENT_TYPE type) {
/* 22 */     this.type = type;
/*    */   }
/*    */   
/*    */   public short getEventId() {
/* 26 */     return this.eventId;
/*    */   }
/*    */   
/*    */   public void setEventId(short id) {
/* 30 */     this.eventId = id;
/*    */   }
/*    */   
/*    */   public long getPlayerId() {
/* 34 */     return this.playerId;
/*    */   }
/*    */   
/*    */   public void setPlayerId(long playerId) {
/* 38 */     this.playerId = playerId;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\core\framework\protocol\InterEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */