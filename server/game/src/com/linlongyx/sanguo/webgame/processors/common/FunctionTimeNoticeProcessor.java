/*    */ package com.linlongyx.sanguo.webgame.processors.common;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.common.FunctionTimeNoticeRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.common.FunctionTimeNoticeResponse;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class FunctionTimeNoticeProcessor
/*    */   extends ProcessorBase<FunctionTimeNoticeRequest, FunctionTimeNoticeResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 16 */     this.response = (ResponseBase)new FunctionTimeNoticeResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, FunctionTimeNoticeRequest request) {
/* 21 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\common\FunctionTimeNoticeProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */