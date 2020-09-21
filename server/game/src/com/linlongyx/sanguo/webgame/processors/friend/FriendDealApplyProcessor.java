/*    */ package com.linlongyx.sanguo.webgame.processors.friend;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayer;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.app.friend.FriendComponent;
/*    */ import com.linlongyx.sanguo.webgame.app.friend.OffLineFriend;
/*    */ import com.linlongyx.sanguo.webgame.app.player.PlayerComponent;
/*    */ import com.linlongyx.sanguo.webgame.config.parameter.FriendParameter;
/*    */ import com.linlongyx.sanguo.webgame.constant.ParameterConstant;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.friend.FriendDealApplyRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.friend.FriendDealApplyResponse;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.friend.FriendInfoNoticeResponse;
/*    */ import com.linlongyx.sanguo.webgame.service.LookUpService;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class FriendDealApplyProcessor
/*    */   extends ProcessorBase<FriendDealApplyRequest, FriendDealApplyResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 24 */     this.response = (ResponseBase)new FriendDealApplyResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, FriendDealApplyRequest request) {
/* 29 */     IPlayer iPlayer = playerSession.getPlayer();
/* 30 */     FriendComponent friendComponent = (FriendComponent)iPlayer.createIfNotExist(FriendComponent.class);
/* 31 */     FriendParameter friendParameter = (FriendParameter)ParameterConstant.getParameter(32);
/* 32 */     if (friendComponent.getIds().size() >= friendParameter.getFriendMax()) {
/* 33 */       return 13402;
/*    */     }
/* 35 */     byte type = 1;
/* 36 */     if (request.agree == 1) {
/* 37 */       for (Long id : request.playerIds) {
/* 38 */         FriendComponent targetComponent; if (friendComponent.getIds().size() >= friendParameter.getFriendMax()) {
/*    */           break;
/*    */         }
/* 41 */         if (!friendComponent.getApplyMap().containsKey(id)) {
/*    */           continue;
/*    */         }
/* 44 */         if (friendComponent.getIds().contains(id)) {
/* 45 */           friendComponent.getApplyMap().remove(id);
/* 46 */           friendComponent.updateApplyMapToDB();
/*    */           
/*    */           continue;
/*    */         } 
/*    */         
/* 51 */         if (LookUpService.getByPlayerId(id.longValue()) != null) {
/* 52 */           targetComponent = (FriendComponent)LookUpService.getComponent(id.longValue(), FriendComponent.class);
/*    */         } else {
/* 54 */           targetComponent = OffLineFriend.getFriendComponent(id.longValue());
/*    */         } 
/* 56 */         if (targetComponent == null) {
/* 57 */           return 13401;
/*    */         }
/* 59 */         synchronized (targetComponent) {
/* 60 */           friendComponent.getApplyMap().remove(id);
/* 61 */           friendComponent.updateApplyMapToDB();
/* 62 */           if (targetComponent.getIds().size() >= friendParameter.getFriendMax()) {
/*    */             continue;
/*    */           }
/* 65 */           friendComponent.getIds().add(id);
/* 66 */           friendComponent.updateIdsToDB();
/* 67 */           targetComponent.getIds().add(Long.valueOf(iPlayer.getPlayerId()));
/* 68 */           targetComponent.updateIdsToDB();
/* 69 */           OffLineFriend.saveToDB(targetComponent);
/* 70 */           ((FriendDealApplyResponse)this.response).playerIds.add(id);
/*    */         } 
/*    */       } 
/*    */     } else {
/* 74 */       request.playerIds.forEach(id -> (Integer)friendComponent.getApplyMap().remove(id));
/* 75 */       friendComponent.updateApplyMapToDB();
/* 76 */       type = 1;
/*    */     } 
/* 78 */     FriendInfoNoticeResponse friendInfoNoticeResponse = new FriendInfoNoticeResponse();
/* 79 */     friendInfoNoticeResponse.type = type;
/* 80 */     PlayerComponent playerComponent = (PlayerComponent)iPlayer.createIfNotExist(PlayerComponent.class);
/* 81 */     friendInfoNoticeResponse.friendInfo = FriendUtil.buildFriendInfo(playerComponent);
/* 82 */     ((FriendDealApplyResponse)this.response).playerIds.forEach(id -> LookUpService.sendMessage(id.longValue(), (ResponseBase)friendInfoNoticeResponse));
/* 83 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\friend\FriendDealApplyProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */