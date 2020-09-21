/*    */ package com.linlongyx.sanguo.webgame.processors.welfare;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.config.parameter.DailyHaoLiParameter;
/*    */ import com.linlongyx.sanguo.webgame.constant.FunctionOpenConstant;
/*    */ import com.linlongyx.sanguo.webgame.constant.ParameterConstant;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.FestivalTime;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.welfare.DailiHaoLiNoticeRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.welfare.DailiHaoLiNoticeResponse;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class DailiHaoLiNoticeProcessor
/*    */   extends ProcessorBase<DailiHaoLiNoticeRequest, DailiHaoLiNoticeResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 24 */     this.response = (ResponseBase)new DailiHaoLiNoticeResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, DailiHaoLiNoticeRequest request) {
/* 29 */     if (!FunctionOpenConstant.isFunctionOpen(playerSession, 6405))
/* 30 */       return 10061; 
/* 31 */     DailyHaoLiParameter dailyHaoLiParameter = (DailyHaoLiParameter)ParameterConstant.getParameter(56);
/* 32 */     List<Integer> list = dailyHaoLiParameter.getActId(true);
/* 33 */     Map<Integer, FestivalTime> festivalTimes = dailyHaoLiParameter.getActTimes();
/* 34 */     for (Integer actId : list) {
/* 35 */       if (festivalTimes.containsKey(actId)) {
/* 36 */         ((DailiHaoLiNoticeResponse)this.response).list.add(festivalTimes.get(actId));
/*    */       }
/*    */     } 
/* 39 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\welfare\DailiHaoLiNoticeProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */