/*    */ package com.linlongyx.sanguo.webgame.processors.friend;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.context.AppContext;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.app.friend.FriendComponent;
/*    */ import com.linlongyx.sanguo.webgame.app.player.PlayerComponent;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.friend.FriendSerachTwoRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.friend.FriendSerachTwoResponse;
/*    */ import com.linlongyx.sanguo.webgame.service.LookUpService;
/*    */ import com.linlongyx.sanguo.webgame.service.PlayerBaseService;
/*    */ import java.util.List;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class FriendSerachTwoProcessor
/*    */   extends ProcessorBase<FriendSerachTwoRequest, FriendSerachTwoResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 23 */     this.response = (ResponseBase)new FriendSerachTwoResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, FriendSerachTwoRequest request) {
/* 28 */     FriendComponent friendComponent = (FriendComponent)playerSession.getPlayer().createIfNotExist(FriendComponent.class);
/* 29 */     PlayerBaseService playerBaseService = (PlayerBaseService)AppContext.getBean("playerBaseService");
/* 30 */     List<Long> playerIds = playerBaseService.searchLikeName(request.name);
/* 31 */     playerIds.stream()
/* 32 */       .filter(id -> !friendComponent.getIds().contains(id))
/* 33 */       .forEach(id -> {
/*    */           PlayerComponent playerComponent = LookUpService.getPlayerComponent(id.longValue());
/*    */           if (playerComponent != null) {
/*    */             ((FriendSerachTwoResponse)this.response).friendInfos.add(FriendUtil.buildFriendInfo(playerComponent));
/*    */           }
/*    */         });
/* 39 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\friend\FriendSerachTwoProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */