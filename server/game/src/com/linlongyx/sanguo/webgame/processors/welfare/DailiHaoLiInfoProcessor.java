/*    */ package com.linlongyx.sanguo.webgame.processors.welfare;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.app.extend.ExtendComponent;
/*    */ import com.linlongyx.sanguo.webgame.app.welfare.WelfareComponent;
/*    */ import com.linlongyx.sanguo.webgame.config.parameter.DailyHaoLiParameter;
/*    */ import com.linlongyx.sanguo.webgame.constant.FunctionOpenConstant;
/*    */ import com.linlongyx.sanguo.webgame.constant.ParameterConstant;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.welfare.DailiHaoLiInfoRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.welfare.DailiHaoLiInfoResponse;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class DailiHaoLiInfoProcessor
/*    */   extends ProcessorBase<DailiHaoLiInfoRequest, DailiHaoLiInfoResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 22 */     this.response = (ResponseBase)new DailiHaoLiInfoResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, DailiHaoLiInfoRequest request) {
/* 27 */     if (!FunctionOpenConstant.isFunctionOpen(playerSession, 6405))
/* 28 */       return 10061; 
/* 29 */     DailyHaoLiParameter dailyHaoLiParameter = (DailyHaoLiParameter)ParameterConstant.getParameter(56);
/* 30 */     if (!dailyHaoLiParameter.isActOpen(request.actId)) {
/* 31 */       return 12702;
/*    */     }
/* 33 */     ExtendComponent extendComponent = (ExtendComponent)playerSession.getPlayer().createIfNotExist(ExtendComponent.class);
/* 34 */     WelfareComponent welfareComponent = (WelfareComponent)playerSession.getPlayer().createIfNotExist(WelfareComponent.class);
/* 35 */     ((DailiHaoLiInfoResponse)this.response).todayCharge = extendComponent.getTodayRecharge();
/* 36 */     ((DailiHaoLiInfoResponse)this.response).getReward = (((Integer)welfareComponent.getDailyHaoLi().getOrDefault(Integer.valueOf(request.actId), Integer.valueOf(0))).intValue() == 0) ? 0 : 1;
/* 37 */     ((DailiHaoLiInfoResponse)this.response).actId = request.actId;
/* 38 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\welfare\DailiHaoLiInfoProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */