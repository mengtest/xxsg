/*    */ package com.linlongyx.sanguo.webgame.processors.friend;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.app.friend.FriendComponent;
/*    */ import com.linlongyx.sanguo.webgame.app.friend.OffLineFriend;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.friend.FriendDeleteRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.friend.FriendDeleteResponse;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.friend.FriendStateNoticeResponse;
/*    */ import com.linlongyx.sanguo.webgame.service.LookUpService;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class FriendDeleteProcessor
/*    */   extends ProcessorBase<FriendDeleteRequest, FriendDeleteResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 21 */     this.response = (ResponseBase)new FriendDeleteResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, FriendDeleteRequest request) {
/* 26 */     FriendComponent friendComponent = (FriendComponent)playerSession.getPlayer().createIfNotExist(FriendComponent.class);
/* 27 */     for (Long id : request.playerIds) {
/* 28 */       FriendComponent targetComponent; if (!friendComponent.getIds().contains(id)) {
/*    */         continue;
/*    */       }
/*    */       
/* 32 */       if (LookUpService.getByPlayerId(id.longValue()) != null) {
/* 33 */         targetComponent = (FriendComponent)LookUpService.getByPlayerId(id.longValue()).getComponent(FriendComponent.class);
/*    */       } else {
/* 35 */         targetComponent = OffLineFriend.getFriendComponent(id.longValue());
/*    */       } 
/* 37 */       if (targetComponent == null) {
/* 38 */         return 13401;
/*    */       }
/*    */       
/* 41 */       synchronized (targetComponent.getIds()) {
/* 42 */         targetComponent.getIds().remove(Long.valueOf(playerSession.getPlayer().getPlayerId()));
/* 43 */         targetComponent.updateIdsToDB();
/* 44 */         OffLineFriend.saveToDB(targetComponent);
/* 45 */         friendComponent.getIds().remove(id);
/* 46 */         friendComponent.updateIdsToDB();
/* 47 */         ((FriendDeleteResponse)this.response).playerIds.add(id);
/*    */       } 
/*    */     } 
/* 50 */     FriendStateNoticeResponse friendStateNoticeResponse = new FriendStateNoticeResponse();
/* 51 */     friendStateNoticeResponse.type = 1;
/* 52 */     friendStateNoticeResponse.playerId = playerSession.getPlayer().getPlayerId();
/* 53 */     friendStateNoticeResponse.name = playerSession.getPlayer().getPlayerName();
/* 54 */     ((FriendDeleteResponse)this.response).playerIds.forEach(id -> LookUpService.sendMessage(id.longValue(), (ResponseBase)friendStateNoticeResponse));
/*    */     
/* 56 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\friend\FriendDeleteProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */