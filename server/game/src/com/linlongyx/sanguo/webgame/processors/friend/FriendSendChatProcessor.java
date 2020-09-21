/*    */ package com.linlongyx.sanguo.webgame.processors.friend;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayer;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.core.utils.ChatUtil;
/*    */ import com.linlongyx.core.utils.TimeUtil;
/*    */ import com.linlongyx.sanguo.webgame.app.friend.FriendComponent;
/*    */ import com.linlongyx.sanguo.webgame.app.friend.OffLineFriend;
/*    */ import com.linlongyx.sanguo.webgame.config.parameter.FriendParameter;
/*    */ import com.linlongyx.sanguo.webgame.constant.ParameterConstant;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.friend.FriendReadChatResponse;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.friend.FriendSendChatRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.friend.FriendSendChatResponse;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.friend.FriendStateNoticeResponse;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.FriendChatInfo;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.KeyValue;
/*    */ import com.linlongyx.sanguo.webgame.service.LookUpService;
/*    */ import java.util.concurrent.LinkedBlockingQueue;
/*    */ 
/*    */ 
/*    */ public class FriendSendChatProcessor
/*    */   extends ProcessorBase<FriendSendChatRequest, FriendSendChatResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 28 */     this.response = (ResponseBase)new FriendSendChatResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, FriendSendChatRequest request) {
/* 33 */     FriendParameter friendParameter = (FriendParameter)ParameterConstant.getParameter(32);
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 38 */     String context = ChatUtil.replaceSensitiveWord(request.context, "*");
/* 39 */     if (LookUpService.getByPlayerId(request.playerId) != null) {
/* 40 */       IPlayer targetPlayer = LookUpService.getByPlayerId(request.playerId);
/* 41 */       FriendStateNoticeResponse friendStateNoticeResponse = new FriendStateNoticeResponse();
/* 42 */       friendStateNoticeResponse.type = 2;
/* 43 */       friendStateNoticeResponse.playerId = playerSession.getPlayer().getPlayerId();
/* 44 */       friendStateNoticeResponse.name = playerSession.getPlayer().getPlayerName();
/* 45 */       targetPlayer.getSession().sendMessage((ResponseBase)friendStateNoticeResponse);
/* 46 */       FriendReadChatResponse friendReadChatResponse = new FriendReadChatResponse();
/* 47 */       FriendChatInfo friendChatInfo = new FriendChatInfo();
/* 48 */       friendChatInfo.playerId = playerSession.getPlayer().getPlayerId();
/* 49 */       KeyValue keyValue = new KeyValue();
/* 50 */       keyValue.key = TimeUtil.currentTime();
/* 51 */       keyValue.valueStr = context;
/* 52 */       friendChatInfo.chats.add(keyValue);
/* 53 */       friendReadChatResponse.friendChatInfo = friendChatInfo;
/* 54 */       targetPlayer.getSession().sendMessage((ResponseBase)friendReadChatResponse);
/* 55 */       ((FriendSendChatResponse)this.response).playerId = request.playerId;
/* 56 */       ((FriendSendChatResponse)this.response).context = context;
/* 57 */       return 0;
/*    */     } 
/* 59 */     FriendComponent friendComponent = OffLineFriend.getFriendComponent(request.playerId);
/*    */     
/* 61 */     if (friendComponent == null) {
/* 62 */       return 13401;
/*    */     }
/* 64 */     synchronized (friendComponent.getChatMap()) {
/* 65 */       KeyValue keyValue = new KeyValue();
/* 66 */       keyValue.key = TimeUtil.currentTime();
/*    */       
/* 68 */       String reg = "[^(a-zA-Z0-9\\u4e00-\\u9fa5\\u00C0-\\u1EF9\\u0020\\pP)]";
/* 69 */       keyValue.valueStr = context.replaceAll(reg, "");
/*    */       try {
/* 71 */         ((LinkedBlockingQueue<KeyValue>)friendComponent.getChatMap().get(Long.valueOf(playerSession.getPlayer().getPlayerId()))).put(keyValue);
/* 72 */         friendComponent.updateChatMapToDB();
/* 73 */       } catch (InterruptedException e) {
/* 74 */         e.printStackTrace();
/*    */       } finally {
/* 76 */         OffLineFriend.saveToDB(friendComponent);
/*    */       } 
/*    */     } 
/*    */     
/* 80 */     ((FriendSendChatResponse)this.response).playerId = request.playerId;
/* 81 */     ((FriendSendChatResponse)this.response).context = context;
/* 82 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\friend\FriendSendChatProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */