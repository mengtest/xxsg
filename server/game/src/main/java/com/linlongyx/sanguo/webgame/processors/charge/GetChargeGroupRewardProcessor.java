/*    */ package com.linlongyx.sanguo.webgame.processors.charge;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.core.utils.TimeUtil;
/*    */ import com.linlongyx.sanguo.webgame.app.extend.ExtendComponent;
/*    */ import com.linlongyx.sanguo.webgame.common.util.FinanceUtil;
/*    */ import com.linlongyx.sanguo.webgame.config.bean.FirstGroupPurchaseBean;
/*    */ import com.linlongyx.sanguo.webgame.config.parameter.FundParameter;
/*    */ import com.linlongyx.sanguo.webgame.constant.MContext;
/*    */ import com.linlongyx.sanguo.webgame.constant.ParameterConstant;
/*    */ import com.linlongyx.sanguo.webgame.constant.ResourceEvent;
/*    */ import com.linlongyx.sanguo.webgame.log.LogUtil;
/*    */ import com.linlongyx.sanguo.webgame.processors.welfare.WelfareUtil;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.charge.GetChargeGroupRewardRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.charge.GetChargeGroupRewardResponse;
/*    */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class GetChargeGroupRewardProcessor
/*    */   extends ProcessorBase<GetChargeGroupRewardRequest, GetChargeGroupRewardResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 27 */     this.response = (ResponseBase)new GetChargeGroupRewardResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, GetChargeGroupRewardRequest request) {
/* 32 */     FirstGroupPurchaseBean firstGroupPurchaseBean = (FirstGroupPurchaseBean)JsonTableService.getJsonData(request.id, FirstGroupPurchaseBean.class);
/* 33 */     if (null == firstGroupPurchaseBean) {
/* 34 */       return 15001;
/*    */     }
/* 36 */     if (MContext.isHeFu()) {
/* 37 */       return 12702;
/*    */     }
/* 39 */     FundParameter fundParameter = (FundParameter)ParameterConstant.getParameter(25);
/* 40 */     if (WelfareUtil.getGiftTime(fundParameter.getFirstChargeDays()) < TimeUtil.currentTime()) {
/* 41 */       return 12702;
/*    */     }
/* 43 */     if (firstGroupPurchaseBean.getNumber() > WelfareUtil.groupCharegeNum.get()) {
/* 44 */       return 15003;
/*    */     }
/* 46 */     ExtendComponent extendComponent = (ExtendComponent)playerSession.getPlayer().createIfNotExist(ExtendComponent.class);
/* 47 */     if (extendComponent.getTotalChargeCCB() < firstGroupPurchaseBean.getCondition()) {
/* 48 */       return 15003;
/*    */     }
/* 50 */     if (extendComponent.getGroupReward().containsKey(Integer.valueOf(request.id))) {
/* 51 */       return 15002;
/*    */     }
/* 53 */     extendComponent.getGroupReward().put(Integer.valueOf(request.id), Integer.valueOf(TimeUtil.currentTime()));
/* 54 */     extendComponent.setGroupReward(extendComponent.getGroupReward());
/* 55 */     FinanceUtil.reward(FinanceUtil.transform(firstGroupPurchaseBean.getReward()), playerSession.getPlayer(), ResourceEvent.groupChargeReward, true);
/* 56 */     LogUtil.errorLog(new Object[] { "GetChargeGroupRewardProcessor", Long.valueOf(playerSession.getPlayer().getPlayerId()), Integer.valueOf(request.id) });
/* 57 */     ((GetChargeGroupRewardResponse)this.response).id = request.id;
/* 58 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\charge\GetChargeGroupRewardProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */