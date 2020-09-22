/*    */ package com.linlongyx.sanguo.client.processors;
/*    */ 
/*    */ import com.linlongyx.sanguo.client.processors.common.KeyValueUpdateProcessor;
/*    */ import com.linlongyx.sanguo.client.processors.login.CreatePlayerProcessor;
/*    */ import com.linlongyx.sanguo.client.processors.login.EnterGameProcessor;
/*    */ import com.linlongyx.sanguo.client.processors.login.LoginInfoProcessor;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.common.KeyValueUpdateResponse;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.login.CreatePlayerResponse;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.login.EnterGameResponse;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.login.LoginInfoDebugResponse;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public enum MsgProcessorRegister
/*    */ {
/* 17 */   LoginInfo((short)20010, (ProcessorBase)new LoginInfoProcessor(), LoginInfoDebugResponse.class),
/*    */ 
/*    */   
/* 20 */   CreatePlayer((short)20002, (ProcessorBase)new CreatePlayerProcessor(), CreatePlayerResponse.class),
/*    */ 
/*    */   
/* 23 */   EnterGame((short)20003, (ProcessorBase)new EnterGameProcessor(), EnterGameResponse.class),
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 29 */   KeyValueUpdate((short)21001, (ProcessorBase)new KeyValueUpdateProcessor(), KeyValueUpdateResponse.class);
/*    */   
/*    */   private short msgCode;
/*    */   
/*    */   private ProcessorBase processor;
/*    */   
/*    */   private Class response;
/*    */   
/*    */   private boolean isClose;
/*    */ 
/*    */   
/*    */   MsgProcessorRegister(short msgCode, ProcessorBase processor, Class response) {
/* 41 */     this.msgCode = msgCode;
/* 42 */     this.processor = processor;
/* 43 */     this.response = response;
/* 44 */     this.isClose = false;
/*    */   }
/*    */   
/*    */   public short getMsgCode() {
/* 48 */     return this.msgCode;
/*    */   }
/*    */   
/*    */   private boolean isClose() {
/* 52 */     return this.isClose;
/*    */   }
/*    */   
/*    */   private void setClose(boolean isClose) {
/* 56 */     this.isClose = isClose;
/*    */   }
/*    */   
/*    */   public ProcessorBase getMsgProcessor() {
/* 60 */     return this.processor;
/*    */   }
/*    */   
/*    */   public Class getResponse() {
/* 64 */     return this.response;
/*    */   }
/*    */   
/*    */   public void setResponse(Class response) {
/* 68 */     this.response = response;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\client\processors\MsgProcessorRegister.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */