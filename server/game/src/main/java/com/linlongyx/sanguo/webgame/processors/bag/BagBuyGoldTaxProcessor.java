/*    */ package com.linlongyx.sanguo.webgame.processors.bag;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayer;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.app.bag.BagComponent;
/*    */ import com.linlongyx.sanguo.webgame.app.extend.ExtendComponent;
/*    */ import com.linlongyx.sanguo.webgame.app.player.PlayerComponent;
/*    */ import com.linlongyx.sanguo.webgame.common.util.FinanceUtil;
/*    */ import com.linlongyx.sanguo.webgame.config.bean.TaxBean;
/*    */ import com.linlongyx.sanguo.webgame.config.parameter.BagParameter;
/*    */ import com.linlongyx.sanguo.webgame.constant.CurrencyType;
/*    */ import com.linlongyx.sanguo.webgame.constant.ParameterConstant;
/*    */ import com.linlongyx.sanguo.webgame.constant.ResourceEvent;
/*    */ import com.linlongyx.sanguo.webgame.processors.login.VipUtil;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.bag.BagBuyGoldTaxRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.bag.BagBuyGoldTaxResponse;
/*    */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*    */ import java.util.Set;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BagBuyGoldTaxProcessor
/*    */   extends ProcessorBase<BagBuyGoldTaxRequest, BagBuyGoldTaxResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 30 */     this.response = (ResponseBase)new BagBuyGoldTaxResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, BagBuyGoldTaxRequest request) {
/* 35 */     IPlayer iPlayer = playerSession.getPlayer();
/* 36 */     PlayerComponent playerComponent = (PlayerComponent)iPlayer.createIfNotExist(PlayerComponent.class);
/* 37 */     ExtendComponent extendComponent = (ExtendComponent)iPlayer.createIfNotExist(ExtendComponent.class);
/* 38 */     if (extendComponent.getBuyGoldTimes() + 1 > VipUtil.getNum(playerComponent.getVip(), 20)) {
/* 39 */       return 11205;
/*    */     }
/* 41 */     TaxBean taxBeanaxBean = null;
/* 42 */     int target = -1;
/* 43 */     Set<Integer> keys = JsonTableService.getJsonTableKey(TaxBean.class);
/* 44 */     for (Integer id : keys) {
/* 45 */       if (playerComponent.getLevel() <= id.intValue()) {
/* 46 */         target = id.intValue(); break;
/*    */       } 
/*    */     } 
/* 49 */     taxBeanaxBean = (TaxBean)JsonTableService.getJsonData(target, TaxBean.class);
/* 50 */     BagComponent bagComponent = (BagComponent)iPlayer.createIfNotExist(BagComponent.class);
/* 51 */     BagParameter bagParameter = (BagParameter)ParameterConstant.getParameter(7);
/* 52 */     int itemId = bagParameter.getTaxItemId();
/* 53 */     if (bagComponent.check(itemId, 1L)) {
/* 54 */       bagComponent.deleteItem(itemId, 1, ResourceEvent.TaxaTionCost, true);
/* 55 */       FinanceUtil.addCurrency(iPlayer, CurrencyType.COIN, taxBeanaxBean.getGold(), ResourceEvent.TaxaTionGet);
/* 56 */       extendComponent.setBuyGoldTimes(extendComponent.getBuyGoldTimes() + 1);
/*    */     } else {
/* 58 */       long cost_CCY = 0L;
/* 59 */       int size = taxBeanaxBean.getCost().size();
/* 60 */       if (extendComponent.getBuyGoldCost() >= size) {
/* 61 */         cost_CCY = ((Integer)taxBeanaxBean.getCost().get(size - 1)).intValue();
/*    */       } else {
/* 63 */         cost_CCY = ((Integer)taxBeanaxBean.getCost().get(extendComponent.getBuyGoldCost())).intValue();
/*    */       } 
/* 65 */       if (FinanceUtil.checkCurrency(iPlayer, CurrencyType.CCY, cost_CCY)) {
/* 66 */         FinanceUtil.decCurrency(iPlayer, CurrencyType.CCY, cost_CCY, ResourceEvent.TaxaTionCost, true);
/* 67 */         FinanceUtil.addCurrency(iPlayer, CurrencyType.COIN, taxBeanaxBean.getGold(), ResourceEvent.TaxaTionGet);
/* 68 */         extendComponent.setBuyGoldTimes(extendComponent.getBuyGoldTimes() + 1);
/* 69 */         extendComponent.setBuyGoldCost(extendComponent.getBuyGoldCost() + 1);
/*    */       } else {
/* 71 */         return 10052;
/*    */       } 
/*    */     } 
/* 74 */     ((BagBuyGoldTaxResponse)this.response).buyGoldTimes = extendComponent.getBuyGoldTimes();
/* 75 */     ((BagBuyGoldTaxResponse)this.response).buyGoldCost = extendComponent.getBuyGoldCost();
/* 76 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\bag\BagBuyGoldTaxProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */