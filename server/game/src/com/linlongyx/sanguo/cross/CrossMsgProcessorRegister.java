/*    */ package com.linlongyx.sanguo.cross;
/*    */ 
/*    */ import com.linlongyx.sanguo.cross.event.IResponseEvent;
/*    */ import com.linlongyx.sanguo.cross.processor.ICrossProcessor;
/*    */ import com.linlongyx.sanguo.cross.processor.add.AddProcessor;
/*    */ import com.linlongyx.sanguo.cross.proto.add.AddResponse;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public enum CrossMsgProcessorRegister
/*    */ {
/* 14 */   Add(1001, (Class)AddProcessor.class, (Class)AddResponse.class);
/*    */   
/*    */   private boolean isClose;
/*    */   
/*    */   private Class<? extends IResponseEvent> response;
/*    */   
/*    */   private Class<? extends ICrossProcessor> processor;
/*    */   
/*    */   private int eventId;
/*    */   
/*    */   CrossMsgProcessorRegister(int eventId, Class<? extends ICrossProcessor> processor, Class<? extends IResponseEvent> request) {
/* 25 */     this.eventId = eventId;
/* 26 */     this.processor = processor;
/* 27 */     this.response = request;
/* 28 */     this.isClose = false;
/*    */   }
/*    */   
/*    */   public int getEventId() {
/* 32 */     return this.eventId;
/*    */   }
/*    */   
/*    */   private boolean isClose() {
/* 36 */     return this.isClose;
/*    */   }
/*    */   
/*    */   private void setClose(boolean isClose) {
/* 40 */     this.isClose = isClose;
/*    */   }
/*    */   
/*    */   public Class<? extends ICrossProcessor> getMsgProcessor() {
/* 44 */     return this.processor;
/*    */   }
/*    */   
/*    */   public Class<? extends IResponseEvent> getResponse() {
/* 48 */     return this.response;
/*    */   }
/*    */   
/*    */   public void setResponse(Class<? extends IResponseEvent> response) {
/* 52 */     this.response = response;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\cross\CrossMsgProcessorRegister.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */