/*    */ package com.linlongyx.sanguo.webgame.processors.turnplate;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.app.turnplate.TurnplateComponent;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.turnplate.TurnplateActNoticeRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.turnplate.TurnplateActNoticeResponse;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class TurnplateActNoticeProcessor
/*    */   extends ProcessorBase<TurnplateActNoticeRequest, TurnplateActNoticeResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 19 */     this.response = (ResponseBase)new TurnplateActNoticeResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, TurnplateActNoticeRequest request) {
/* 24 */     TurnplateComponent turnplateComponent = (TurnplateComponent)playerSession.getPlayer().createIfNotExist(TurnplateComponent.class);
/* 25 */     if (turnplateComponent.getCurActId() != 0 && 
/* 26 */       !turnplateComponent.isActOpen(turnplateComponent.getCurActId())) {
/* 27 */       turnplateComponent.setCurActId(0);
/*    */     }
/*    */     
/* 30 */     ((TurnplateActNoticeResponse)this.response).actId = turnplateComponent.getCurActId();
/* 31 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\turnplate\TurnplateActNoticeProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */