/*    */ package com.linlongyx.sanguo.webgame.processors.login;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.app.player.PlayerComponent;
/*    */ import com.linlongyx.sanguo.webgame.processors.player.PlayerUtil;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.login.ChangeHeadRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.login.ChangeHeadResponse;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ChangeHeadProcessor
/*    */   extends ProcessorBase<ChangeHeadRequest, ChangeHeadResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 18 */     this.response = (ResponseBase)new ChangeHeadResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, ChangeHeadRequest request) {
/* 23 */     PlayerComponent playerComponent = (PlayerComponent)playerSession.getPlayer().createIfNotExist(PlayerComponent.class);
/* 24 */     if (PlayerUtil.getActPlatform() != 1 && PlayerUtil.getActPlatform() != 5) {
/* 25 */       return 11808;
/*    */     }
/* 27 */     if (request.head == "" || request.head == null) {
/* 28 */       playerComponent.setHead(PlayerUtil.changeHead("", playerComponent.getSex()));
/*    */     } else {
/* 30 */       playerComponent.setHead(PlayerUtil.changeHead(request.head, playerComponent.getSex()));
/*    */     } 
/* 32 */     ((ChangeHeadResponse)this.response).head = playerComponent.getHead();
/* 33 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\login\ChangeHeadProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */