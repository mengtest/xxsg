/*    */ package com.linlongyx.sanguo.webgame.processors.partner;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.app.extend.ExtendComponent;
/*    */ import com.linlongyx.sanguo.webgame.constant.FunctionOpenConstant;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.partner.PartnerHandbookInfoRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.partner.PartnerHandbookInfoResponse;
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PartnerHandbookInfoProcessor
/*    */   extends ProcessorBase<PartnerHandbookInfoRequest, PartnerHandbookInfoResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 19 */     this.response = (ResponseBase)new PartnerHandbookInfoResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, PartnerHandbookInfoRequest request) {
/* 24 */     if (!FunctionOpenConstant.isFunctionOpen(playerSession, 10))
/* 25 */       return 10061; 
/* 26 */     ExtendComponent extendComponent = (ExtendComponent)playerSession.getPlayer().createIfNotExist(ExtendComponent.class);
/*    */     
/* 28 */     if (extendComponent.getHandbookMap().containsKey(Integer.valueOf(request.cameps))) {
/* 29 */       ((PartnerHandbookInfoResponse)this.response).currRewardIndex = (ArrayList)extendComponent.getHandbookMap().get(Integer.valueOf(request.cameps));
/*    */     }
/* 31 */     ((PartnerHandbookInfoResponse)this.response).camps = request.cameps;
/* 32 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\partner\PartnerHandbookInfoProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */