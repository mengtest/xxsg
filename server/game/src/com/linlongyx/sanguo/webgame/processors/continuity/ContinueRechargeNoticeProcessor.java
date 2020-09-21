/*    */ package com.linlongyx.sanguo.webgame.processors.continuity;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.config.parameter.ContinuityParameter;
/*    */ import com.linlongyx.sanguo.webgame.constant.ParameterConstant;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.continuity.ContinueRechargeNoticeRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.continuity.ContinueRechargeNoticeResponse;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ContinueRechargeNoticeProcessor
/*    */   extends ProcessorBase<ContinueRechargeNoticeRequest, ContinueRechargeNoticeResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 18 */     this.response = (ResponseBase)new ContinueRechargeNoticeResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, ContinueRechargeNoticeRequest request) {
/* 23 */     ContinuityParameter continuityParameter = (ContinuityParameter)ParameterConstant.getParameter(21);
/* 24 */     ((ContinueRechargeNoticeResponse)this.response).list = continuityParameter.getActId(true);
/* 25 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\continuity\ContinueRechargeNoticeProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */