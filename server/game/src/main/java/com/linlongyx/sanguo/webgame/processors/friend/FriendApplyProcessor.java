/*    */ package com.linlongyx.sanguo.webgame.processors.friend;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.core.utils.TimeUtil;
/*    */ import com.linlongyx.sanguo.webgame.app.friend.FriendComponent;
/*    */ import com.linlongyx.sanguo.webgame.app.friend.OffLineFriend;
/*    */ import com.linlongyx.sanguo.webgame.app.player.PlayerComponent;
/*    */ import com.linlongyx.sanguo.webgame.config.parameter.FriendParameter;
/*    */ import com.linlongyx.sanguo.webgame.constant.ParameterConstant;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.friend.FriendApplyRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.friend.FriendApplyResponse;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.friend.FriendDealApplyRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.friend.FriendInfoNoticeResponse;
/*    */ import com.linlongyx.sanguo.webgame.service.LookUpService;
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class FriendApplyProcessor
/*    */   extends ProcessorBase<FriendApplyRequest, FriendApplyResponse>
/*    */ {
/* 26 */   private ArrayList<Long> friend = new ArrayList<>();
/*    */   
/*    */   protected void initResponse() {
/* 29 */     this.response = (ResponseBase)new FriendApplyResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, FriendApplyRequest request) {
/* 34 */     FriendComponent friendComponent = (FriendComponent)playerSession.getPlayer().getComponent(FriendComponent.class);
/* 35 */     FriendParameter friendParameter = (FriendParameter)ParameterConstant.getParameter(32);
/* 36 */     long playerId = playerSession.getPlayer().getPlayerId();
/* 37 */     int now = TimeUtil.currentTime();
/* 38 */     for (Long id : request.playerIds) {
/* 39 */       FriendComponent targetComponent; if (friendComponent.getIds().contains(id) || playerId == id.longValue()) {
/*    */         continue;
/*    */       }
/*    */       
/* 43 */       if (friendComponent.getApplyMap().containsKey(id)) {
/* 44 */         this.friend.add(id);
/*    */         
/*    */         continue;
/*    */       } 
/* 48 */       if (LookUpService.getByPlayerId(id.longValue()) != null) {
/* 49 */         targetComponent = (FriendComponent)LookUpService.getByPlayerId(id.longValue()).getComponent(FriendComponent.class);
/*    */       } else {
/* 51 */         targetComponent = OffLineFriend.getFriendComponent(id.longValue());
/*    */       } 
/* 53 */       if (targetComponent == null) {
/* 54 */         return 13401;
/*    */       }
/* 56 */       synchronized (targetComponent.getApplyMap()) {
/* 57 */         if (targetComponent.getApplyMap().size() >= friendParameter.getApplyListMax()) {
/* 58 */           targetComponent.getApplyMap().remove(targetComponent.getEaryApply());
/*    */         }
/* 60 */         targetComponent.getApplyMap().put(Long.valueOf(playerId), Integer.valueOf(now));
/* 61 */         targetComponent.updateApplyMapToDB();
/* 62 */         OffLineFriend.saveToDB(targetComponent);
/*    */       } 
/*    */       
/* 65 */       FriendInfoNoticeResponse friendInfoNoticeResponse = new FriendInfoNoticeResponse();
/* 66 */       friendInfoNoticeResponse.type = 2;
/* 67 */       PlayerComponent playerComponent = (PlayerComponent)playerSession.getPlayer().createIfNotExist(PlayerComponent.class);
/* 68 */       friendInfoNoticeResponse.friendInfo = FriendUtil.buildFriendInfo(playerComponent);
/* 69 */       if (LookUpService.getByPlayerId(id.longValue()) != null && LookUpService.getByPlayerId(id.longValue()).getSession() != null && LookUpService.getByPlayerId(id.longValue()).getSession().isLogin()) {
/* 70 */         LookUpService.getByPlayerId(id.longValue()).getSession().sendMessage((ResponseBase)friendInfoNoticeResponse);
/*    */       }
/*    */     } 
/* 73 */     return 0;
/*    */   }
/*    */ 
/*    */   
/*    */   protected void process(IPlayerSession playerSession, FriendApplyRequest request) throws Exception {
/* 78 */     short retCode = handleRequest(playerSession, request);
/* 79 */     ((FriendApplyResponse)this.response).setRetCode(retCode);
/* 80 */     playerSession.sendMessage(this.response);
/* 81 */     if (!this.friend.isEmpty()) {
/* 82 */       FriendDealApplyRequest request1 = new FriendDealApplyRequest();
/* 83 */       request1.agree = 1;
/* 84 */       request1.playerIds.addAll(this.friend);
/* 85 */       (new FriendDealApplyProcessor()).handle(playerSession, (RequestBase)request1);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\friend\FriendApplyProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */