/*    */ package com.linlongyx.sanguo.webgame.processors.rebate;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.core.utils.TimeUtil;
/*    */ import com.linlongyx.sanguo.webgame.app.rebate.RechargeRebateComponent;
/*    */ import com.linlongyx.sanguo.webgame.app.rebate.RechargeRebateEntity;
/*    */ import com.linlongyx.sanguo.webgame.common.util.FinanceUtil;
/*    */ import com.linlongyx.sanguo.webgame.config.bean.RechargeProcessBean;
/*    */ import com.linlongyx.sanguo.webgame.config.parameter.ChargeRebateParameter;
/*    */ import com.linlongyx.sanguo.webgame.constant.ParameterConstant;
/*    */ import com.linlongyx.sanguo.webgame.constant.ResourceEvent;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.rebate.ChargeRebateRewardRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.rebate.ChargeRebateRewardResponse;
/*    */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ChargeRebateRewardProcessor
/*    */   extends ProcessorBase<ChargeRebateRewardRequest, ChargeRebateRewardResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 25 */     this.response = (ResponseBase)new ChargeRebateRewardResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, ChargeRebateRewardRequest request) {
/* 30 */     RechargeRebateComponent rechargeRebateComponent = (RechargeRebateComponent)playerSession.getPlayer().createIfNotExist(RechargeRebateComponent.class);
/* 31 */     ChargeRebateParameter chargeRebateParameter = (ChargeRebateParameter)ParameterConstant.getParameter(49);
/* 32 */     if (!chargeRebateParameter.isActOpen(request.actId)) {
/* 33 */       return 12702;
/*    */     }
/* 35 */     RechargeRebateEntity rechargeRebateEntity = rechargeRebateComponent.getEntity(request.actId);
/* 36 */     RechargeProcessBean rechargeProcessBean = (RechargeProcessBean)JsonTableService.getJsonData(request.boxId, RechargeProcessBean.class);
/* 37 */     if (null == rechargeProcessBean) {
/* 38 */       return 10006;
/*    */     }
/* 40 */     if (rechargeRebateEntity.getReward().containsKey(Integer.valueOf(request.boxId))) {
/* 41 */       return 15002;
/*    */     }
/* 43 */     if (rechargeProcessBean.getRequire() > rechargeRebateEntity.getScore()) {
/* 44 */       return 15003;
/*    */     }
/* 46 */     rechargeRebateEntity.getReward().put(Integer.valueOf(request.boxId), Integer.valueOf(TimeUtil.currentTime()));
/* 47 */     rechargeRebateEntity.setReward(rechargeRebateEntity.getReward());
/* 48 */     rechargeRebateComponent.updateRewardDB(rechargeRebateEntity.getId());
/* 49 */     FinanceUtil.reward(FinanceUtil.transform(rechargeProcessBean.getReward()), playerSession.getPlayer(), ResourceEvent.RebateSorceReward, true);
/* 50 */     ((ChargeRebateRewardResponse)this.response).actId = request.actId;
/* 51 */     ((ChargeRebateRewardResponse)this.response).boxId = request.boxId;
/* 52 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\rebate\ChargeRebateRewardProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */