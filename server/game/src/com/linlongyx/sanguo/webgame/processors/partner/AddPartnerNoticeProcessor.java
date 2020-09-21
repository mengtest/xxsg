/*    */ package com.linlongyx.sanguo.webgame.processors.partner;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.partner.AddPartnerNoticeRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.partner.AddPartnerNoticeResponse;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class AddPartnerNoticeProcessor
/*    */   extends ProcessorBase<AddPartnerNoticeRequest, AddPartnerNoticeResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 16 */     this.response = (ResponseBase)new AddPartnerNoticeResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, AddPartnerNoticeRequest request) {
/* 21 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\partner\AddPartnerNoticeProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */