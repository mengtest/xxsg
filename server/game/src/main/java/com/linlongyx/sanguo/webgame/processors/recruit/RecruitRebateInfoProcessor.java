/*    */ package com.linlongyx.sanguo.webgame.processors.recruit;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.core.utils.TimeUtil;
/*    */ import com.linlongyx.sanguo.webgame.app.recruit.RecruitComponent;
/*    */ import com.linlongyx.sanguo.webgame.config.parameter.RecruitParamter;
/*    */ import com.linlongyx.sanguo.webgame.constant.ParameterConstant;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.recruit.RecruitRebateInfoRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.recruit.RecruitRebateInfoResponse;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.FestivalTime;
/*    */ import java.util.ArrayList;
/*    */ import java.util.HashMap;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class RecruitRebateInfoProcessor
/*    */   extends ProcessorBase<RecruitRebateInfoRequest, RecruitRebateInfoResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 26 */     this.response = (ResponseBase)new RecruitRebateInfoResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, RecruitRebateInfoRequest request) {
/* 31 */     RecruitParamter recruitParamter = (RecruitParamter)ParameterConstant.getParameter(15);
/* 32 */     List<Integer> list = recruitParamter.getActId(true);
/* 33 */     Map<Integer, FestivalTime> festivalTimes = recruitParamter.getRebateTimes();
/* 34 */     RecruitComponent recruitComponent = (RecruitComponent)playerSession.getPlayer().createIfNotExist(RecruitComponent.class);
/* 35 */     if (recruitComponent.getEndTime() < TimeUtil.currentTime()) {
/* 36 */       recruitComponent.setRebateScore(0L);
/* 37 */       recruitComponent.setRebateReward(new HashMap<>());
/*    */     } 
/* 39 */     for (Integer actId : list) {
/* 40 */       if (festivalTimes.containsKey(actId)) {
/* 41 */         ((RecruitRebateInfoResponse)this.response).festivalTime = festivalTimes.get(actId);
/* 42 */         recruitComponent.setEndTime(((RecruitRebateInfoResponse)this.response).festivalTime.endTime);
/*    */       } 
/*    */     } 
/* 45 */     ((RecruitRebateInfoResponse)this.response).score = (int)recruitComponent.getRebateScore();
/* 46 */     ((RecruitRebateInfoResponse)this.response).list = new ArrayList(recruitComponent.getRebateReward().keySet());
/* 47 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\recruit\RecruitRebateInfoProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */