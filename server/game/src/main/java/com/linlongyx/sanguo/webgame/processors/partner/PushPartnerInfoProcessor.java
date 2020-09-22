/*    */ package com.linlongyx.sanguo.webgame.processors.partner;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.partner.PushPartnerInfoRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.partner.PushPartnerInfoResponse;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PushPartnerInfoProcessor
/*    */   extends ProcessorBase<PushPartnerInfoRequest, PushPartnerInfoResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 16 */     this.response = (ResponseBase)new PushPartnerInfoResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, PushPartnerInfoRequest request) {
/* 21 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\partner\PushPartnerInfoProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */