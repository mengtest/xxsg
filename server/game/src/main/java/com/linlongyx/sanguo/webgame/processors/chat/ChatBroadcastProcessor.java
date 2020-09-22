/*    */ package com.linlongyx.sanguo.webgame.processors.chat;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.chat.ChatBroadcastRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.chat.ChatBroadcastResponse;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ChatBroadcastProcessor
/*    */   extends ProcessorBase<ChatBroadcastRequest, ChatBroadcastResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 16 */     this.response = (ResponseBase)new ChatBroadcastResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, ChatBroadcastRequest request) {
/* 21 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\chat\ChatBroadcastProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */