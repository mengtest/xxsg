/*    */ package com.linlongyx.sanguo.webgame.processors.friend;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.context.AppContext;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.app.friend.FriendComponent;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.friend.FriendApplyRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.friend.FriendSerachRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.friend.FriendSerachResponse;
/*    */ import com.linlongyx.sanguo.webgame.service.PlayerBaseService;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class FriendSerachProcessor
/*    */   extends ProcessorBase<FriendSerachRequest, FriendSerachResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 23 */     this.response = (ResponseBase)new FriendSerachResponse();
/*    */   }
/* 25 */   public List<Long> playerIds = new ArrayList<>();
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, FriendSerachRequest request) {
/* 28 */     FriendComponent friendComponent = (FriendComponent)playerSession.getPlayer().createIfNotExist(FriendComponent.class);
/* 29 */     PlayerBaseService playerBaseService = (PlayerBaseService)AppContext.getBean("playerBaseService");
/* 30 */     this.playerIds = playerBaseService.searchLikeName(request.name);
/*    */     
/* 32 */     if (this.playerIds.isEmpty()) {
/* 33 */       return 10081;
/*    */     }
/* 35 */     return 0;
/*    */   }
/*    */ 
/*    */   
/*    */   protected void process(IPlayerSession playerSession, FriendSerachRequest request) throws Exception {
/* 40 */     short retCode = handleRequest(playerSession, request);
/* 41 */     ((FriendSerachResponse)this.response).setRetCode(retCode);
/* 42 */     playerSession.sendMessage(this.response);
/* 43 */     if (retCode == 0) {
/* 44 */       FriendApplyRequest request1 = new FriendApplyRequest();
/* 45 */       request1.playerIds.addAll(this.playerIds);
/* 46 */       (new FriendApplyProcessor()).handle(playerSession, (RequestBase)request1);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\friend\FriendSerachProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */