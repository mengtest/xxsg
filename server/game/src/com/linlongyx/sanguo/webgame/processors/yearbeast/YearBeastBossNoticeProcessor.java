/*    */ package com.linlongyx.sanguo.webgame.processors.yearbeast;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.config.parameter.BossHomeParameter;
/*    */ import com.linlongyx.sanguo.webgame.constant.FunctionOpenConstant;
/*    */ import com.linlongyx.sanguo.webgame.constant.ParameterConstant;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.FestivalTime;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.yearbeast.YearBeastBossNoticeRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.yearbeast.YearBeastBossNoticeResponse;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class YearBeastBossNoticeProcessor
/*    */   extends ProcessorBase<YearBeastBossNoticeRequest, YearBeastBossNoticeResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 24 */     this.response = (ResponseBase)new YearBeastBossNoticeResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, YearBeastBossNoticeRequest request) {
/* 29 */     if (!FunctionOpenConstant.isFunctionOpen(playerSession, 62))
/* 30 */       return 10061; 
/* 31 */     BossHomeParameter bossHomeParameter = (BossHomeParameter)ParameterConstant.getParameter(3);
/* 32 */     List<Integer> list = bossHomeParameter.getActId(true);
/* 33 */     Map<Integer, FestivalTime> festivalTimes = bossHomeParameter.getActTimes();
/* 34 */     for (Integer actId : list) {
/* 35 */       if (festivalTimes.containsKey(actId)) {
/* 36 */         ((YearBeastBossNoticeResponse)this.response).list.add(festivalTimes.get(actId));
/*    */       }
/*    */     } 
/* 39 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\yearbeast\YearBeastBossNoticeProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */