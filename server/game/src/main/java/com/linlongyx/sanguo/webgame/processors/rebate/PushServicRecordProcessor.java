/*    */ package com.linlongyx.sanguo.webgame.processors.rebate;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.rebate.PushServicRecordRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.rebate.PushServicRecordResponse;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PushServicRecordProcessor
/*    */   extends ProcessorBase<PushServicRecordRequest, PushServicRecordResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 16 */     this.response = (ResponseBase)new PushServicRecordResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, PushServicRecordRequest request) {
/* 21 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\rebate\PushServicRecordProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */