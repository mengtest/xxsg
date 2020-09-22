/*    */ package com.linlongyx.sanguo.webgame.processors.chat;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.chat.ChatRadiateRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.chat.ChatRadiateResponse;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ChatRadiateProcessor
/*    */   extends ProcessorBase<ChatRadiateRequest, ChatRadiateResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 16 */     this.response = (ResponseBase)new ChatRadiateResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, ChatRadiateRequest request) {
/* 21 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\chat\ChatRadiateProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */