/*    */ package com.linlongyx.sanguo.webgame.processors.general;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.core.utils.LogUtils;
/*    */ import com.linlongyx.sanguo.webgame.app.general.GeneralComponent;
/*    */ import com.linlongyx.sanguo.webgame.app.task.TaskComponent;
/*    */ import com.linlongyx.sanguo.webgame.common.util.FinanceUtil;
/*    */ import com.linlongyx.sanguo.webgame.config.parameter.GeneralParameter;
/*    */ import com.linlongyx.sanguo.webgame.constant.CurrencyType;
/*    */ import com.linlongyx.sanguo.webgame.constant.ParameterConstant;
/*    */ import com.linlongyx.sanguo.webgame.constant.ResourceEvent;
/*    */ import com.linlongyx.sanguo.webgame.constant.TaskType;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.general.GeneralBuyOrderRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.general.GeneralBuyOrderResponse;
/*    */ 
/*    */ public class GeneralBuyOrderProcessor extends ProcessorBase<GeneralBuyOrderRequest, GeneralBuyOrderResponse> {
/*    */   protected void initResponse() {
/* 21 */     this.response = (ResponseBase)new GeneralBuyOrderResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, GeneralBuyOrderRequest request) {
/* 26 */     GeneralParameter generalParameter = (GeneralParameter)ParameterConstant.getParameter(12);
/* 27 */     GeneralComponent generalComponent = (GeneralComponent)playerSession.getPlayer().createIfNotExist(GeneralComponent.class);
/* 28 */     int buyTime = generalComponent.getBuyTime();
/* 29 */     int maxBuyTime = generalParameter.getMaxBuyTime();
/* 30 */     int maxOrder = generalParameter.getMaxOrder();
/* 31 */     int time = request.time;
/* 32 */     if (time <= 0) {
/* 33 */       return 10090;
/*    */     }
/* 35 */     if (time > 100000) {
/* 36 */       return 18004;
/*    */     }
/* 38 */     int totalTime = buyTime + time;
/* 39 */     int leftNum = GeneralUtil.getLeftOrder(generalComponent);
/* 40 */     if (leftNum + time > maxOrder || totalTime > maxBuyTime) {
/* 41 */       return 11207;
/*    */     }
/* 43 */     int ccy = 0;
/* 44 */     for (int i = 1; i <= time; i++) {
/* 45 */       ccy += generalParameter.getBuyCost(buyTime + i);
/*    */     }
/* 47 */     if (!FinanceUtil.checkCurrency(playerSession.getPlayer(), CurrencyType.CCY, ccy)) {
/* 48 */       return 10052;
/*    */     }
/* 50 */     FinanceUtil.decCurrency(playerSession.getPlayer(), CurrencyType.CCY, ccy, ResourceEvent.GeneralBuyOrder, true);
/*    */     
/* 52 */     generalComponent.setBuyTime(totalTime);
/*    */     
/* 54 */     ((GeneralBuyOrderResponse)this.response).num = GeneralUtil.getLeftOrder(generalComponent);
/* 55 */     ((GeneralBuyOrderResponse)this.response).time = generalComponent.getNextTime();
/* 56 */     ((GeneralBuyOrderResponse)this.response).orderNum = totalTime;
/* 57 */     TaskComponent taskComponent = (TaskComponent)playerSession.getPlayer().createIfNotExist(TaskComponent.class);
/* 58 */     if (null != taskComponent) {
/* 59 */       taskComponent.refreshSchedule(TaskType.GeneralBuy, 0, request.time);
/*    */     }
/* 61 */     LogUtils.errorLog(new Object[] { "GeneralBuyOrder", Long.valueOf(playerSession.getPlayer().getPlayerId()), ((GeneralBuyOrderResponse)this.response).toString() });
/* 62 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\general\GeneralBuyOrderProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */