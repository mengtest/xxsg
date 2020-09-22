/*    */ package com.linlongyx.sanguo.webgame.processors.mental;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.mental.PushLotteryRecordRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.mental.PushLotteryRecordResponse;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PushLotteryRecordProcessor
/*    */   extends ProcessorBase<PushLotteryRecordRequest, PushLotteryRecordResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 16 */     this.response = (ResponseBase)new PushLotteryRecordResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, PushLotteryRecordRequest request) {
/* 21 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\mental\PushLotteryRecordProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */