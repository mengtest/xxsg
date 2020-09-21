/*    */ package com.linlongyx.sanguo.webgame.processors.rebate;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.app.rebate.RechargeRebateComponent;
/*    */ import com.linlongyx.sanguo.webgame.app.rebate.RechargeRebateEntity;
/*    */ import com.linlongyx.sanguo.webgame.config.parameter.ChargeRebateParameter;
/*    */ import com.linlongyx.sanguo.webgame.constant.ParameterConstant;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.rebate.ChargeRebateInfoRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.rebate.ChargeRebateInfoResponse;
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ChargeRebateInfoProcessor
/*    */   extends ProcessorBase<ChargeRebateInfoRequest, ChargeRebateInfoResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 22 */     this.response = (ResponseBase)new ChargeRebateInfoResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, ChargeRebateInfoRequest request) {
/* 27 */     RechargeRebateComponent rechargeRebateComponent = (RechargeRebateComponent)playerSession.getPlayer().createIfNotExist(RechargeRebateComponent.class);
/* 28 */     ChargeRebateParameter chargeRebateParameter = (ChargeRebateParameter)ParameterConstant.getParameter(49);
/* 29 */     if (!chargeRebateParameter.isActOpen(request.actId)) {
/* 30 */       return 12702;
/*    */     }
/* 32 */     RechargeRebateEntity rechargeRebateEntity = rechargeRebateComponent.getEntity(request.actId);
/* 33 */     ((ChargeRebateInfoResponse)this.response).times = (int)(rechargeRebateEntity.getRefChare() / chargeRebateParameter.getOneCost());
/* 34 */     ((ChargeRebateInfoResponse)this.response).actId = request.actId;
/*    */     
/* 36 */     ((ChargeRebateInfoResponse)this.response).serviceRecords = ChargeRebateUtil.getRecordList();
/* 37 */     ((ChargeRebateInfoResponse)this.response).sorce = rechargeRebateEntity.getScore();
/* 38 */     ((ChargeRebateInfoResponse)this.response).list = new ArrayList(rechargeRebateEntity.getReward().keySet());
/* 39 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\rebate\ChargeRebateInfoProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */