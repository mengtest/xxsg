/*    */ package com.linlongyx.sanguo.webgame.processors.friend;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.core.utils.TimeUtil;
/*    */ import com.linlongyx.sanguo.webgame.app.extend.ExtendComponent;
/*    */ import com.linlongyx.sanguo.webgame.app.friend.FriendComponent;
/*    */ import com.linlongyx.sanguo.webgame.config.parameter.FriendParameter;
/*    */ import com.linlongyx.sanguo.webgame.constant.ParameterConstant;
/*    */ import com.linlongyx.sanguo.webgame.log.LogUtil;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.friend.FriendSendPointRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.friend.FriendSendPointResponse;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.friend.FriendStateNoticeResponse;
/*    */ import com.linlongyx.sanguo.webgame.service.LookUpService;
/*    */ import java.util.Iterator;
/*    */ 
/*    */ 
/*    */ public class FriendSendPointProcessor
/*    */   extends ProcessorBase<FriendSendPointRequest, FriendSendPointResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 24 */     this.response = (ResponseBase)new FriendSendPointResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, FriendSendPointRequest request) {
/* 29 */     long myPlayerId = playerSession.getPlayer().getPlayerId();
/* 30 */     FriendComponent friendComponent = (FriendComponent)playerSession.getPlayer().createIfNotExist(FriendComponent.class);
/* 31 */     for (Iterator<Long> iterator = request.playerIds.iterator(); iterator.hasNext(); ) { long playerId = ((Long)iterator.next()).longValue();
/* 32 */       if (!friendComponent.getIds().contains(Long.valueOf(playerId))) {
/* 33 */         return 13405;
/*    */       }
/* 35 */       if (friendComponent.getSendIds().contains(Long.valueOf(playerId))) {
/* 36 */         return 13406;
/*    */       }
/* 38 */       FriendParameter friendParameter = (FriendParameter)ParameterConstant.getParameter(32);
/*    */ 
/*    */ 
/*    */       
/* 42 */       friendComponent.getSendIds().add(Long.valueOf(playerId));
/* 43 */       friendComponent.updateSendIdsToDB();
/* 44 */       FriendComponent targetFriendComponent = (FriendComponent)LookUpService.getComponent(playerId, FriendComponent.class);
/* 45 */       if (null == targetFriendComponent) {
/* 46 */         return 13405;
/*    */       }
/* 48 */       ExtendComponent targetExtend = (ExtendComponent)LookUpService.getComponent(playerId, ExtendComponent.class);
/* 49 */       if (null == targetExtend) {
/* 50 */         return 13405;
/*    */       }
/*    */       
/* 53 */       synchronized (targetFriendComponent) {
/* 54 */         if (LookUpService.getByPlayerId(playerId) == null && 
/* 55 */           targetExtend.getZeroResetDate() != TimeUtil.getCurrentYearMonthDay()) {
/* 56 */           targetFriendComponent.getOfflineReceiveIds().add(Long.valueOf(playerSession.getPlayer().getPlayerId()));
/* 57 */           targetFriendComponent.setOfflineReceiveIds(targetFriendComponent.getOfflineReceiveIds());
/* 58 */           targetFriendComponent.updateOfflineReceiveIdsoDB();
/*    */         } 
/*    */         
/* 61 */         targetFriendComponent.getNeedReceiveIds().add(Long.valueOf(friendComponent.getPlayerId()));
/* 62 */         targetFriendComponent.setNeedReceiveIds(targetFriendComponent.getNeedReceiveIds());
/* 63 */         targetFriendComponent.updateNeedReceiveIdsToDB();
/* 64 */         targetFriendComponent.addFavorValue(myPlayerId, friendParameter.getGoodFeeling());
/* 65 */         targetFriendComponent.saveAllToDB();
/*    */       } 
/* 67 */       friendComponent.setTotalSendCount(friendComponent.getTotalSendCount() + 1);
/* 68 */       friendComponent.updateTotalSendCountToDB();
/* 69 */       friendComponent.addFavorValue(playerId, friendParameter.getGoodFeeling());
/*    */ 
/*    */       
/* 72 */       long value1 = friendComponent.getFavorValue(playerId);
/* 73 */       long value2 = targetFriendComponent.getFavorValue(friendComponent.getPlayerId());
/* 74 */       if (value1 != value2) {
/* 75 */         long max = Math.max(value1, value2);
/* 76 */         synchronized (targetFriendComponent) {
/* 77 */           targetFriendComponent.clearFavor(myPlayerId);
/* 78 */           targetFriendComponent.addFavorValue(myPlayerId, max);
/* 79 */           targetFriendComponent.saveAllToDB();
/*    */         } 
/* 81 */         friendComponent.clearFavor(playerId);
/* 82 */         friendComponent.addFavorValue(playerId, max);
/*    */       } 
/*    */       
/* 85 */       LogUtil.errorLog(new Object[] { "FriendSendPoint", Long.valueOf(myPlayerId), Long.valueOf(playerId), Long.valueOf(friendComponent.getFavorValue(playerId)) });
/*    */ 
/*    */       
/* 88 */       if (LookUpService.isOnline(playerId)) {
/* 89 */         FriendStateNoticeResponse friendStateNoticeResponse = new FriendStateNoticeResponse();
/* 90 */         friendStateNoticeResponse.type = 5;
/* 91 */         friendStateNoticeResponse.playerId = myPlayerId;
/* 92 */         friendStateNoticeResponse.name = playerSession.getPlayer().getPlayerName();
/* 93 */         LookUpService.getByPlayerId(playerId).getSession().sendMessage((ResponseBase)friendStateNoticeResponse);
/*    */       } 
/* 95 */       ((FriendSendPointResponse)this.response).playerIds.add(Long.valueOf(playerId));
/* 96 */       ((FriendSendPointResponse)this.response).sendNum = friendComponent.getSendIds().size();
/* 97 */       ((FriendSendPointResponse)this.response).favorValues.add(Long.valueOf(friendComponent.getFavorValue(playerId))); }
/*    */     
/* 99 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\friend\FriendSendPointProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */