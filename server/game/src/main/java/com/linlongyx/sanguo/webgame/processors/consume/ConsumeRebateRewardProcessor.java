/*    */ package com.linlongyx.sanguo.webgame.processors.consume;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.core.utils.TimeUtil;
/*    */ import com.linlongyx.sanguo.webgame.app.consume.ConsumeRebateComponent;
/*    */ import com.linlongyx.sanguo.webgame.app.consume.ConsumeRebateEntity;
/*    */ import com.linlongyx.sanguo.webgame.common.util.FinanceUtil;
/*    */ import com.linlongyx.sanguo.webgame.config.bean.ChargeRewardBean;
/*    */ import com.linlongyx.sanguo.webgame.config.bean.ChargeRewardlistBean;
/*    */ import com.linlongyx.sanguo.webgame.config.parameter.ConsumeRebateParameter;
/*    */ import com.linlongyx.sanguo.webgame.constant.FunctionOpenConstant;
/*    */ import com.linlongyx.sanguo.webgame.constant.ParameterConstant;
/*    */ import com.linlongyx.sanguo.webgame.constant.ResourceEvent;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.consume.ConsumeRebateRewardRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.consume.ConsumeRebateRewardResponse;
/*    */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*    */ 
/*    */ public class ConsumeRebateRewardProcessor
/*    */   extends ProcessorBase<ConsumeRebateRewardRequest, ConsumeRebateRewardResponse> {
/*    */   protected void initResponse() {
/* 24 */     this.response = (ResponseBase)new ConsumeRebateRewardResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, ConsumeRebateRewardRequest request) {
/* 29 */     if (!FunctionOpenConstant.isFunctionOpen(playerSession, 52))
/* 30 */       return 10061; 
/* 31 */     ConsumeRebateComponent consumeRebateComponent = (ConsumeRebateComponent)playerSession.getPlayer().createIfNotExist(ConsumeRebateComponent.class);
/* 32 */     ConsumeRebateParameter consumeRebateParameter = (ConsumeRebateParameter)ParameterConstant.getParameter(52);
/* 33 */     if (!consumeRebateParameter.isActOpen(request.actId)) {
/* 34 */       return 12702;
/*    */     }
/* 36 */     ConsumeRebateEntity consumeRebateEntity = consumeRebateComponent.getEntity(request.actId);
/* 37 */     ChargeRewardBean chargeRewardBean = (ChargeRewardBean)JsonTableService.getJsonData(request.actId, ChargeRewardBean.class);
/* 38 */     if (null == chargeRewardBean) {
/* 39 */       return 10006;
/*    */     }
/* 41 */     ChargeRewardlistBean chargeRewardlistBean = (ChargeRewardlistBean)JsonTableService.getJsonData(request.rewardId, ChargeRewardlistBean.class);
/* 42 */     if (null == chargeRewardlistBean) {
/* 43 */       return 10006;
/*    */     }
/* 45 */     if (chargeRewardBean.getActType() != chargeRewardlistBean.getType()) {
/* 46 */       return 11808;
/*    */     }
/* 48 */     if (chargeRewardBean.getActType() == 1) {
/* 49 */       if (consumeRebateEntity.getTakeConsume() < chargeRewardlistBean.getMoney()) {
/* 50 */         return 15003;
/*    */       }
/* 52 */       if (consumeRebateEntity.getTakeReward().containsKey(Integer.valueOf(request.rewardId))) {
/* 53 */         return 15002;
/*    */       }
/* 55 */       consumeRebateEntity.getTakeReward().put(Integer.valueOf(request.rewardId), Integer.valueOf(TimeUtil.currentTime()));
/* 56 */       consumeRebateEntity.setTakeReward(consumeRebateEntity.getTakeReward());
/* 57 */       consumeRebateComponent.updateTakeRewardDB(request.actId);
/* 58 */       FinanceUtil.reward(FinanceUtil.transform(chargeRewardlistBean.getReward()), playerSession.getPlayer(), ResourceEvent.StageRebate, true);
/* 59 */     } else if (chargeRewardBean.getActType() == 2) {
/* 60 */       if (consumeRebateEntity.getPostureConsume() < chargeRewardlistBean.getMoney()) {
/* 61 */         return 15003;
/*    */       }
/* 63 */       if (consumeRebateEntity.getPostureReward().containsKey(Integer.valueOf(request.rewardId))) {
/* 64 */         return 15002;
/*    */       }
/* 66 */       consumeRebateEntity.getPostureReward().put(Integer.valueOf(request.rewardId), Integer.valueOf(TimeUtil.currentTime()));
/* 67 */       consumeRebateEntity.setPostureReward(consumeRebateEntity.getPostureReward());
/* 68 */       consumeRebateComponent.updatePostureRewardDB(request.actId);
/* 69 */       FinanceUtil.reward(FinanceUtil.transform(chargeRewardlistBean.getReward()), playerSession.getPlayer(), ResourceEvent.KungFuRebate, true);
/* 70 */     } else if (chargeRewardBean.getActType() == 3) {
/* 71 */       if (consumeRebateEntity.getZhenFaConsume() < chargeRewardlistBean.getMoney()) {
/* 72 */         return 15003;
/*    */       }
/* 74 */       if (consumeRebateEntity.getZhenFaReward().containsKey(Integer.valueOf(request.rewardId))) {
/* 75 */         return 15002;
/*    */       }
/* 77 */       consumeRebateEntity.getZhenFaReward().put(Integer.valueOf(request.rewardId), Integer.valueOf(TimeUtil.currentTime()));
/* 78 */       consumeRebateEntity.setZhenFaReward(consumeRebateEntity.getZhenFaReward());
/* 79 */       consumeRebateComponent.updatezhenFaRewardDB(request.actId);
/* 80 */       FinanceUtil.reward(FinanceUtil.transform(chargeRewardlistBean.getReward()), playerSession.getPlayer(), ResourceEvent.ZhenFaRebate, true);
/*    */     } 
/* 82 */     ((ConsumeRebateRewardResponse)this.response).actId = request.actId;
/* 83 */     ((ConsumeRebateRewardResponse)this.response).rewardId = request.rewardId;
/* 84 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\consume\ConsumeRebateRewardProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */