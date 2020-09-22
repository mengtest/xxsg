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
/*    */ 
/*    */ public class WalkEvent
/*    */   extends InterEvent
/*    */   implements IEvent
/*    */ {
/*    */   public short flexPosx;
/*    */   public short flexPosy;
/*    */   public short posx;
/*    */   public short posy;
/*    */   public byte active;
/*    */   
/*    */   public WalkEvent(long playerId, short flexPosx, short flexPosy, short posx, short posy, byte active) {
/* 25 */     this.eventId = 30001;
/* 26 */     this.playerId = playerId;
/* 27 */     this.flexPosx = flexPosx;
/* 28 */     this.flexPosy = flexPosy;
/* 29 */     this.posx = posx;
/* 30 */     this.posy = posy;
/* 31 */     this.active = active;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\internal\WalkEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */