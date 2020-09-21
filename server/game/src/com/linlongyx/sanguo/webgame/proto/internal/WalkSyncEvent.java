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
/*    */ 
/*    */ public class WalkSyncEvent
/*    */   extends InterEvent
/*    */   implements IEvent
/*    */ {
/*    */   public short posx;
/*    */   public short posy;
/*    */   public byte direction;
/*    */   public boolean isStop;
/*    */   
/*    */   public WalkSyncEvent(long playerId, short posx, short posy, byte direction, boolean isStop) {
/* 23 */     this.eventId = 30004;
/* 24 */     this.playerId = playerId;
/* 25 */     this.posx = posx;
/* 26 */     this.posy = posy;
/* 27 */     this.direction = direction;
/* 28 */     this.isStop = isStop;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\internal\WalkSyncEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */