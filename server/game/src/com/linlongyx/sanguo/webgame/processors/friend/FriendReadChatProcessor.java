/*    */ package com.linlongyx.sanguo.webgame.processors.friend;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.app.friend.FriendComponent;
/*    */ import com.linlongyx.sanguo.webgame.app.player.PlayerComponent;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.friend.FriendReadChatRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.friend.FriendReadChatResponse;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.FriendChatInfo;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.KeyValue;
/*    */ import com.linlongyx.sanguo.webgame.service.LookUpService;
/*    */ import java.util.concurrent.LinkedBlockingQueue;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class FriendReadChatProcessor
/*    */   extends ProcessorBase<FriendReadChatRequest, FriendReadChatResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 22 */     this.response = (ResponseBase)new FriendReadChatResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, FriendReadChatRequest request) {
/* 27 */     FriendComponent friendComponent = (FriendComponent)playerSession.getPlayer().createIfNotExist(FriendComponent.class);
/* 28 */     PlayerComponent playerComponent = LookUpService.getPlayerComponent(request.playerId);
/* 29 */     if (playerComponent != null) {
/* 30 */       ((FriendReadChatResponse)this.response).friendInfo = FriendUtil.buildFriendInfo(playerComponent);
/*    */     }
/* 32 */     if (request.needChat == 1 && friendComponent.getChatMap().containsKey(Long.valueOf(request.playerId))) {
/* 33 */       FriendChatInfo friendChatInfo = new FriendChatInfo();
/* 34 */       friendChatInfo.playerId = request.playerId;
/* 35 */       while (((LinkedBlockingQueue)friendComponent.getChatMap().get(Long.valueOf(request.playerId))).peek() != null) {
/* 36 */         KeyValue keyValue = ((LinkedBlockingQueue<KeyValue>)friendComponent.getChatMap().get(Long.valueOf(request.playerId))).poll();
/* 37 */         friendChatInfo.chats.add(keyValue);
/*    */       } 
/* 39 */       ((FriendReadChatResponse)this.response).friendChatInfo = friendChatInfo;
/* 40 */       friendComponent.getChatMap().remove(Long.valueOf(request.playerId));
/* 41 */       friendComponent.updateChatMapToDB();
/*    */     } 
/* 43 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\friend\FriendReadChatProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */