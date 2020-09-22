/*    */ package com.linlongyx.sanguo.webgame.processors.friend;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.app.friend.FriendComponent;
/*    */ import com.linlongyx.sanguo.webgame.app.player.PlayerComponent;
/*    */ import com.linlongyx.sanguo.webgame.config.parameter.FriendParameter;
/*    */ import com.linlongyx.sanguo.webgame.constant.ParameterConstant;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.friend.FriendRecommendRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.friend.FriendRecommendResponse;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.FriendInfo;
/*    */ import com.linlongyx.sanguo.webgame.service.LookUpService;
/*    */ import java.util.Collection;
/*    */ import java.util.Collections;
/*    */ import java.util.List;
/*    */ import java.util.stream.Collectors;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class FriendRecommendProcessor
/*    */   extends ProcessorBase<FriendRecommendRequest, FriendRecommendResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 26 */     this.response = (ResponseBase)new FriendRecommendResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, FriendRecommendRequest request) {
/* 31 */     PlayerComponent playerComponent = (PlayerComponent)playerSession.getPlayer().createIfNotExist(PlayerComponent.class);
/* 32 */     FriendComponent friendComponent = (FriendComponent)playerSession.getPlayer().createIfNotExist(FriendComponent.class);
/* 33 */     FriendParameter friendParameter = (FriendParameter)ParameterConstant.getParameter(32);
/*    */     
/* 35 */     List<FriendInfo> friendInfos = LookUpService.recommendFriend(friendParameter.getRecommendNum(), friendParameter.getRecommendLevel());
/* 36 */     Collections.shuffle(friendInfos);
/* 37 */     ((FriendRecommendResponse)this.response).friendInfos.addAll((Collection)friendInfos.stream()
/* 38 */         .filter(info -> (info.playerId != playerSession.getPlayer().getPlayerId() && !friendComponent.getIds().contains(Long.valueOf(info.playerId))))
/* 39 */         .collect(Collectors.toList()));
/* 40 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\friend\FriendRecommendProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */