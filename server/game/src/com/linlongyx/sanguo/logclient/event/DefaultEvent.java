/*    */ package com.linlongyx.sanguo.logclient.event;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import org.msgpack.annotation.Message;
/*    */ 
/*    */ @Message
/*    */ public class DefaultEvent
/*    */   implements Event, Serializable
/*    */ {
/*    */   private static final long serialVersionUID = -2829982376318158622L;
/*    */   private int id;
/*    */   
/*    */   public DefaultEvent() {}
/*    */   
/*    */   public DefaultEvent(int id) {
/* 16 */     this.id = id;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getId() {
/* 21 */     return this.id;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setId(int id) {
/* 26 */     this.id = id;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\logclient\event\DefaultEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */