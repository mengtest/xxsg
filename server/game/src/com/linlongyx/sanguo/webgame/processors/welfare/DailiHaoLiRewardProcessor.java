/*    */ package com.linlongyx.sanguo.webgame.processors.welfare;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.app.extend.ExtendComponent;
/*    */ import com.linlongyx.sanguo.webgame.app.welfare.WelfareComponent;
/*    */ import com.linlongyx.sanguo.webgame.common.util.FinanceUtil;
/*    */ import com.linlongyx.sanguo.webgame.config.bean.EverydayRewardBean;
/*    */ import com.linlongyx.sanguo.webgame.config.bean.EverydayRewardlistBean;
/*    */ import com.linlongyx.sanguo.webgame.config.parameter.DailyHaoLiParameter;
/*    */ import com.linlongyx.sanguo.webgame.constant.FunctionOpenConstant;
/*    */ import com.linlongyx.sanguo.webgame.constant.ParameterConstant;
/*    */ import com.linlongyx.sanguo.webgame.constant.ResourceEvent;
/*    */ import com.linlongyx.sanguo.webgame.log.LogUtil;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.welfare.DailiHaoLiRewardRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.welfare.DailiHaoLiRewardResponse;
/*    */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*    */ 
/*    */ public class DailiHaoLiRewardProcessor
/*    */   extends ProcessorBase<DailiHaoLiRewardRequest, DailiHaoLiRewardResponse> {
/*    */   protected void initResponse() {
/* 24 */     this.response = (ResponseBase)new DailiHaoLiRewardResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, DailiHaoLiRewardRequest request) {
/* 29 */     if (!FunctionOpenConstant.isFunctionOpen(playerSession, 6405)) {
/* 30 */       return 10061;
/*    */     }
/* 32 */     DailyHaoLiParameter dailyHaoLiParameter = (DailyHaoLiParameter)ParameterConstant.getParameter(56);
/* 33 */     if (!dailyHaoLiParameter.isActOpen(request.actId)) {
/* 34 */       return 12702;
/*    */     }
/* 36 */     EverydayRewardBean everydayRewardBean = (EverydayRewardBean)JsonTableService.getJsonData(request.actId, EverydayRewardBean.class);
/* 37 */     if (null == everydayRewardBean) {
/* 38 */       return 10006;
/*    */     }
/* 40 */     EverydayRewardlistBean everydayRewardlistBean = (EverydayRewardlistBean)JsonTableService.getJsonData(everydayRewardBean.getRewardId(), EverydayRewardlistBean.class);
/* 41 */     if (null == everydayRewardlistBean) {
/* 42 */       return 10006;
/*    */     }
/* 44 */     ExtendComponent extendComponent = (ExtendComponent)playerSession.getPlayer().createIfNotExist(ExtendComponent.class);
/* 45 */     if (extendComponent.getTodayRecharge() < everydayRewardlistBean.getRmb()) {
/* 46 */       return 15003;
/*    */     }
/* 48 */     WelfareComponent welfareComponent = (WelfareComponent)playerSession.getPlayer().createIfNotExist(WelfareComponent.class);
/* 49 */     if (((Integer)welfareComponent.getDailyHaoLi().getOrDefault(Integer.valueOf(request.actId), Integer.valueOf(0))).intValue() != 0) {
/* 50 */       return 15002;
/*    */     }
/* 52 */     welfareComponent.getDailyHaoLi().put(Integer.valueOf(request.actId), Integer.valueOf(everydayRewardlistBean.getId()));
/* 53 */     welfareComponent.setDailyHaoLi(welfareComponent.getDailyHaoLi());
/* 54 */     FinanceUtil.reward(FinanceUtil.transform(everydayRewardlistBean.getReward()), playerSession.getPlayer(), ResourceEvent.DailyHaoLi, true);
/* 55 */     LogUtil.errorLog(new Object[] { "DailiHaoLiRewardProcessor", Long.valueOf(playerSession.getPlayer().getPlayerId()), Integer.valueOf(request.actId), Integer.valueOf(everydayRewardlistBean.getId()) });
/* 56 */     ((DailiHaoLiRewardResponse)this.response).actId = request.actId;
/* 57 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\welfare\DailiHaoLiRewardProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */