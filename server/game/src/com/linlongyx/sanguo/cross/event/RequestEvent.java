/*    */ package com.linlongyx.sanguo.cross.event;
/*    */ 
/*    */ import com.fasterxml.jackson.annotation.JsonIgnore;
/*    */ 
/*    */ public class RequestEvent
/*    */   implements IRequestEvent {
/*    */   private int id;
/*    */   private long serverId;
/*    */   private long playerId;
/*    */   private byte mark;
/*    */   
/*    */   public long getPlayerId() {
/* 13 */     return this.playerId;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setPlayerId(long playerId) {
/* 18 */     this.playerId = playerId;
/*    */   }
/*    */ 
/*    */   
/*    */   public byte getMark() {
/* 23 */     return this.mark;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setMark(byte mark) {
/* 28 */     this.mark = mark;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getId() {
/* 33 */     return this.id;
/*    */   }
/*    */ 
/*    */   
/*    */   public int setId(int id) {
/* 38 */     return this.id = id;
/*    */   }
/*    */ 
/*    */   
/*    */   public long getServerId() {
/* 43 */     return this.serverId;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setServerId(long serverId) {
/* 48 */     this.serverId = serverId;
/*    */   }
/*    */ 
/*    */   
/*    */   @JsonIgnore
/*    */   public boolean isSync() {
/* 54 */     return ((this.mark & 0x1) == 1);
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\cross\event\RequestEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */