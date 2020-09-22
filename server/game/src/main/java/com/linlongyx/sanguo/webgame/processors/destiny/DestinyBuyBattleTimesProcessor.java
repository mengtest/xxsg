/*    */ package com.linlongyx.sanguo.webgame.processors.destiny;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.app.destiny.DestinyComponent;
/*    */ import com.linlongyx.sanguo.webgame.common.util.FinanceUtil;
/*    */ import com.linlongyx.sanguo.webgame.config.parameter.DestinyParameter;
/*    */ import com.linlongyx.sanguo.webgame.constant.CurrencyType;
/*    */ import com.linlongyx.sanguo.webgame.constant.FunctionOpenConstant;
/*    */ import com.linlongyx.sanguo.webgame.constant.ParameterConstant;
/*    */ import com.linlongyx.sanguo.webgame.constant.ResourceEvent;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.destiny.DestinyBuyBattleTimesRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.destiny.DestinyBuyBattleTimesResponse;
/*    */ 
/*    */ public class DestinyBuyBattleTimesProcessor extends ProcessorBase<DestinyBuyBattleTimesRequest, DestinyBuyBattleTimesResponse> {
/*    */   protected void initResponse() {
/* 19 */     this.response = (ResponseBase)new DestinyBuyBattleTimesResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, DestinyBuyBattleTimesRequest request) {
/* 24 */     if (!FunctionOpenConstant.isFunctionOpen(playerSession, 74))
/* 25 */       return 10061; 
/* 26 */     DestinyComponent destinyComponent = (DestinyComponent)playerSession.getPlayer().createIfNotExist(DestinyComponent.class);
/* 27 */     DestinyParameter destinyParameter = (DestinyParameter)ParameterConstant.getParameter(74);
/*    */     
/* 29 */     if (destinyComponent.getRobTimes() >= destinyParameter.getInitRobTimes()) {
/* 30 */       return 17402;
/*    */     }
/* 32 */     if (!FinanceUtil.checkCurrency(playerSession.getPlayer(), CurrencyType.CCY, destinyParameter.getBuyTimePrice())) {
/* 33 */       return 10052;
/*    */     }
/* 35 */     FinanceUtil.decCurrency(playerSession.getPlayer(), CurrencyType.CCY, destinyParameter.getBuyTimePrice(), ResourceEvent.DestinyBuyTimes);
/* 36 */     destinyComponent.addRobTimes(1);
/*    */     
/* 38 */     ((DestinyBuyBattleTimesResponse)this.response).robTimes = destinyComponent.getRobTimes();
/*    */     
/* 40 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\destiny\DestinyBuyBattleTimesProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */