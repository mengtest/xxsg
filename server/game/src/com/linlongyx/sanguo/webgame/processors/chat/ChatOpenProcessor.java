/*    */ package com.linlongyx.sanguo.webgame.processors.chat;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.app.chat.ChatComponent;
/*    */ import com.linlongyx.sanguo.webgame.app.chat.ChatEntity;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.chat.ChatOpenRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.chat.ChatOpenResponse;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.ChatPersonal;
/*    */ import java.util.Map;
/*    */ 
/*    */ 
/*    */ public class ChatOpenProcessor
/*    */   extends ProcessorBase<ChatOpenRequest, ChatOpenResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 19 */     this.response = (ResponseBase)new ChatOpenResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, ChatOpenRequest request) {
/* 24 */     ChatComponent chatComponent = (ChatComponent)playerSession.getPlayer().createIfNotExist(ChatComponent.class);
/* 25 */     chatComponent.getEntityMap().entrySet().parallelStream().forEach(entry -> {
/*    */           ChatEntity chatEntity = (ChatEntity)entry.getValue();
/*    */           ChatPersonal chatPersonal = new ChatPersonal();
/*    */           chatPersonal.targetPlayerId = chatEntity.getToId();
/*    */           chatPersonal.targetPlayerName = chatEntity.getToName();
/*    */           chatPersonal.chatPersonalInfos = chatEntity.getPrivateList();
/*    */           ((ChatOpenResponse)this.response).chatPersonals.add(chatPersonal);
/*    */         });
/* 33 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\chat\ChatOpenProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */