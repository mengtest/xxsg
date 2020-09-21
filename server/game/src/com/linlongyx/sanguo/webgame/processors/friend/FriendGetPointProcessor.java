/*    */ package com.linlongyx.sanguo.webgame.processors.friend;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.app.friend.FriendComponent;
/*    */ import com.linlongyx.sanguo.webgame.common.util.FinanceUtil;
/*    */ import com.linlongyx.sanguo.webgame.config.parameter.FriendParameter;
/*    */ import com.linlongyx.sanguo.webgame.constant.ParameterConstant;
/*    */ import com.linlongyx.sanguo.webgame.constant.ResourceEvent;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.friend.FriendGetPointRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.friend.FriendGetPointResponse;
/*    */ import java.util.Iterator;
/*    */ 
/*    */ 
/*    */ public class FriendGetPointProcessor
/*    */   extends ProcessorBase<FriendGetPointRequest, FriendGetPointResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 21 */     this.response = (ResponseBase)new FriendGetPointResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, FriendGetPointRequest request) {
/* 26 */     FriendComponent friendComponent = (FriendComponent)playerSession.getPlayer().createIfNotExist(FriendComponent.class);
/*    */     
/* 28 */     for (Iterator<Long> iterator = request.playerId.iterator(); iterator.hasNext(); ) { long playerId = ((Long)iterator.next()).longValue();
/* 29 */       if (!friendComponent.getNeedReceiveIds().contains(Long.valueOf(playerId))) {
/* 30 */         return 13403;
/*    */       }
/*    */       
/* 33 */       if (friendComponent.getReceivedIds().contains(Long.valueOf(playerId))) {
/* 34 */         return 13404;
/*    */       }
/* 36 */       FriendParameter friendParameter = (FriendParameter)ParameterConstant.getParameter(32);
/*    */       
/* 38 */       if (friendComponent.getReceivedIds().size() >= friendParameter.getGiftMax()) {
/* 39 */         return 13407;
/*    */       }
/*    */       
/* 42 */       friendComponent.getReceivedIds().add(Long.valueOf(playerId));
/* 43 */       friendComponent.updateReceivedIdsToDB();
/*    */       
/* 45 */       FinanceUtil.reward(FinanceUtil.transformReward(friendParameter.getFriendReward(), 1), playerSession.getPlayer(), ResourceEvent.FriendSendFlower, false);
/* 46 */       ((FriendGetPointResponse)this.response).playerIds.add(Long.valueOf(playerId));
/* 47 */       ((FriendGetPointResponse)this.response).receivedNum = friendComponent.getReceivedIds().size();
/* 48 */       ((FriendGetPointResponse)this.response).favorValues.add(Long.valueOf(friendComponent.getFavorValue(playerId))); }
/*    */     
/* 50 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\friend\FriendGetPointProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */