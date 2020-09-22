/*    */ package com.linlongyx.sanguo.webgame.processors.luckymoney;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.app.bag.BagComponent;
/*    */ import com.linlongyx.sanguo.webgame.app.luckymoney.LuckyMoneyComponent;
/*    */ import com.linlongyx.sanguo.webgame.app.luckymoney.LuckyMoneyEntity;
/*    */ import com.linlongyx.sanguo.webgame.config.bean.MoneyPotBean;
/*    */ import com.linlongyx.sanguo.webgame.config.parameter.LuckyMoneyActParameter;
/*    */ import com.linlongyx.sanguo.webgame.constant.FunctionOpenConstant;
/*    */ import com.linlongyx.sanguo.webgame.constant.ParameterConstant;
/*    */ import com.linlongyx.sanguo.webgame.constant.ResourceEvent;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.luckymoney.LuckyMoneyAddRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.luckymoney.LuckyMoneyAddResponse;
/*    */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*    */ 
/*    */ public class LuckyMoneyAddProcessor
/*    */   extends ProcessorBase<LuckyMoneyAddRequest, LuckyMoneyAddResponse> {
/*    */   protected void initResponse() {
/* 22 */     this.response = (ResponseBase)new LuckyMoneyAddResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, LuckyMoneyAddRequest request) {
/* 27 */     if (!FunctionOpenConstant.isFunctionOpen(playerSession, 61))
/* 28 */       return 10061; 
/* 29 */     LuckyMoneyActParameter luckyMoneyActParameter = (LuckyMoneyActParameter)ParameterConstant.getParameter(61);
/* 30 */     if (!luckyMoneyActParameter.isActLuckyOpen(request.id)) {
/* 31 */       return 12402;
/*    */     }
/* 33 */     LuckyMoneyComponent luckyMoneyComponent = (LuckyMoneyComponent)playerSession.getPlayer().createIfNotExist(LuckyMoneyComponent.class);
/* 34 */     BagComponent bagComponent = (BagComponent)playerSession.getPlayer().getComponent(BagComponent.class);
/* 35 */     MoneyPotBean moneyPotBean = (MoneyPotBean)JsonTableService.getJsonData(request.id, MoneyPotBean.class);
/* 36 */     if (moneyPotBean == null) {
/* 37 */       return 12701;
/*    */     }
/* 39 */     LuckyMoneyEntity luckyMoneyEntity = luckyMoneyComponent.getEntity(request.id);
/* 40 */     long sum = luckyMoneyEntity.getGoldMoney() + luckyMoneyEntity.getSilverMoney() + request.num;
/* 41 */     if (moneyPotBean.getLimit() < sum) {
/* 42 */       return 17310;
/*    */     }
/* 44 */     if (request.type == 1) {
/* 45 */       if (!bagComponent.check(luckyMoneyActParameter.getCostGoldItem(), request.num)) {
/* 46 */         return 10050;
/*    */       }
/* 48 */       bagComponent.deleteItem(luckyMoneyActParameter.getCostGoldItem(), request.num, ResourceEvent.RedundantCard, true);
/* 49 */       luckyMoneyEntity.setGoldMoney(luckyMoneyEntity.getGoldMoney() + request.num);
/* 50 */       luckyMoneyComponent.updateGoldMoneyDB(request.id);
/* 51 */       luckyMoneyEntity.setGoldMoneySum(luckyMoneyEntity.getGoldMoneySum() + request.num);
/* 52 */       luckyMoneyComponent.updateGoldMoneySumDB(request.id);
/* 53 */       ((LuckyMoneyAddResponse)this.response).item_num = luckyMoneyEntity.getGoldMoney();
/* 54 */       ((LuckyMoneyAddResponse)this.response).type = request.type;
/* 55 */     } else if (request.type == 2) {
/* 56 */       if (!bagComponent.check(luckyMoneyActParameter.getCostSilverItem(), request.num)) {
/* 57 */         return 10050;
/*    */       }
/* 59 */       bagComponent.deleteItem(luckyMoneyActParameter.getCostSilverItem(), request.num, ResourceEvent.RedundantCard, true);
/* 60 */       luckyMoneyEntity.setSilverMoney(luckyMoneyEntity.getSilverMoney() + request.num);
/* 61 */       luckyMoneyComponent.updateSilverMoneyDB(request.id);
/* 62 */       luckyMoneyEntity.setSilverMoneySum(luckyMoneyEntity.getSilverMoneySum() + request.num);
/* 63 */       luckyMoneyComponent.updateSilverMoneySumDB(request.id);
/* 64 */       ((LuckyMoneyAddResponse)this.response).item_num = luckyMoneyEntity.getSilverMoney();
/* 65 */       ((LuckyMoneyAddResponse)this.response).type = request.type;
/*    */     } 
/*    */     
/* 68 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\luckymoney\LuckyMoneyAddProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */