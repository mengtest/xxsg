/*    */ package com.linlongyx.sanguo.webgame.processors.consume;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.config.parameter.ConsumeRebateParameter;
/*    */ import com.linlongyx.sanguo.webgame.constant.ParameterConstant;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.consume.ConsumeRebateNoticeRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.consume.ConsumeRebateNoticeResponse;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.FestivalTime;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ConsumeRebateNoticeProcessor
/*    */   extends ProcessorBase<ConsumeRebateNoticeRequest, ConsumeRebateNoticeResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 22 */     this.response = (ResponseBase)new ConsumeRebateNoticeResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, ConsumeRebateNoticeRequest request) {
/* 27 */     ConsumeRebateParameter consumeRebateParameter = (ConsumeRebateParameter)ParameterConstant.getParameter(52);
/* 28 */     List<Integer> list = consumeRebateParameter.getActId(true);
/* 29 */     Map<Integer, FestivalTime> festivalTimes = consumeRebateParameter.getActTimes();
/* 30 */     for (Integer actId : list) {
/* 31 */       if (festivalTimes.containsKey(actId)) {
/* 32 */         ((ConsumeRebateNoticeResponse)this.response).list.add(festivalTimes.get(actId));
/*    */       }
/*    */     } 
/* 35 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\consume\ConsumeRebateNoticeProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */