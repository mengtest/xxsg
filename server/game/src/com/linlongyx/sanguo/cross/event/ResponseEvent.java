/*    */ package com.linlongyx.sanguo.cross.event;
/*    */ 
/*    */ import com.fasterxml.jackson.annotation.JsonIgnore;
/*    */ 
/*    */ public class ResponseEvent
/*    */   implements IResponseEvent {
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
/*    */   public int getId() {
/* 23 */     return this.id;
/*    */   }
/*    */ 
/*    */   
/*    */   public int setId(int id) {
/* 28 */     return this.id = id;
/*    */   }
/*    */ 
/*    */   
/*    */   public long getServerId() {
/* 33 */     return this.serverId;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setServerId(long serverId) {
/* 38 */     this.serverId = serverId;
/*    */   }
/*    */ 
/*    */   
/*    */   @JsonIgnore
/*    */   public boolean isSync() {
/* 44 */     return ((this.mark & 0x1) == 1);
/*    */   }
/*    */ 
/*    */   
/*    */   public byte getMark() {
/* 49 */     return this.mark;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setMark(byte mark) {
/* 54 */     this.mark = mark;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\cross\event\ResponseEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */