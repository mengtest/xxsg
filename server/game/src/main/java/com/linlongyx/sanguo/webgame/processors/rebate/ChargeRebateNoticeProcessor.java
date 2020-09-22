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
/*    */ import com.linlongyx.sanguo.webgame.constant.RedNoticeConstant;
/*    */ import com.linlongyx.sanguo.webgame.processors.player.PlayerUtil;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.rebate.ChargeRebateNoticeRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.rebate.ChargeRebateNoticeResponse;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.FestivalTime;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ChargeRebateNoticeProcessor
/*    */   extends ProcessorBase<ChargeRebateNoticeRequest, ChargeRebateNoticeResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 26 */     this.response = (ResponseBase)new ChargeRebateNoticeResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, ChargeRebateNoticeRequest request) {
/* 31 */     ChargeRebateParameter chargeRebateParameter = (ChargeRebateParameter)ParameterConstant.getParameter(49);
/* 32 */     RechargeRebateComponent rechargeRebateComponent = (RechargeRebateComponent)playerSession.getPlayer().createIfNotExist(RechargeRebateComponent.class);
/* 33 */     List<Integer> list = chargeRebateParameter.getActId(true);
/* 34 */     Map<Integer, FestivalTime> festivalTimes = chargeRebateParameter.getActTimes();
/* 35 */     for (Integer actId : list) {
/* 36 */       if (festivalTimes.containsKey(actId)) {
/* 37 */         ((ChargeRebateNoticeResponse)this.response).list.add(festivalTimes.get(actId));
/* 38 */         RechargeRebateEntity rechargeRebateEntity = rechargeRebateComponent.getEntity(actId.intValue());
/* 39 */         if (rechargeRebateEntity.getRefChare() >= chargeRebateParameter.getOneCost()) {
/* 40 */           PlayerUtil.sendRedNotice(Long.valueOf(playerSession.getPlayer().getPlayerId()), RedNoticeConstant.ChargeRebate.getSys(), actId.intValue());
/*    */         }
/*    */       } 
/*    */     } 
/*    */     
/* 45 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\rebate\ChargeRebateNoticeProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */