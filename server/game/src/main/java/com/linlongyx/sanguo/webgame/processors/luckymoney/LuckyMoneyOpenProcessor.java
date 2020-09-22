/*    */ package com.linlongyx.sanguo.webgame.processors.luckymoney;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.core.utils.TimeUtil;
/*    */ import com.linlongyx.sanguo.webgame.app.bag.BagComponent;
/*    */ import com.linlongyx.sanguo.webgame.app.extend.ExtendComponent;
/*    */ import com.linlongyx.sanguo.webgame.app.luckymoney.LuckyMoneyComponent;
/*    */ import com.linlongyx.sanguo.webgame.app.luckymoney.LuckyMoneyEntity;
/*    */ import com.linlongyx.sanguo.webgame.config.bean.MoneyPotBean;
/*    */ import com.linlongyx.sanguo.webgame.config.parameter.LuckyMoneyActParameter;
/*    */ import com.linlongyx.sanguo.webgame.constant.FunctionOpenConstant;
/*    */ import com.linlongyx.sanguo.webgame.constant.ParameterConstant;
/*    */ import com.linlongyx.sanguo.webgame.constant.ResourceEvent;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.luckymoney.LuckyMoneyOpenRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.luckymoney.LuckyMoneyOpenResponse;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.FestivalTime;
/*    */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ 
/*    */ public class LuckyMoneyOpenProcessor
/*    */   extends ProcessorBase<LuckyMoneyOpenRequest, LuckyMoneyOpenResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 28 */     this.response = (ResponseBase)new LuckyMoneyOpenResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, LuckyMoneyOpenRequest request) {
/* 33 */     if (!FunctionOpenConstant.isFunctionOpen(playerSession, 61))
/* 34 */       return 10061; 
/* 35 */     LuckyMoneyActParameter luckyMoneyActParameter = (LuckyMoneyActParameter)ParameterConstant.getParameter(61);
/* 36 */     LuckyMoneyComponent luckyMoneyComponent = (LuckyMoneyComponent)playerSession.getPlayer().createIfNotExist(LuckyMoneyComponent.class);
/* 37 */     ExtendComponent extendComponent = (ExtendComponent)playerSession.getPlayer().createIfNotExist(ExtendComponent.class);
/* 38 */     BagComponent bagComponent = (BagComponent)playerSession.getPlayer().createIfNotExist(BagComponent.class);
/* 39 */     List<Integer> list = luckyMoneyActParameter.getLuckySaveAct(true);
/* 40 */     Map<Integer, FestivalTime> festivalTimes = luckyMoneyActParameter.getActTimes();
/* 41 */     double rechardTotal = extendComponent.getTodayRecharge();
/* 42 */     double costTotal = extendComponent.getDailyConsume();
/* 43 */     for (Integer actId : list) {
/* 44 */       if (festivalTimes.containsKey(actId)) {
/* 45 */         ((LuckyMoneyOpenResponse)this.response).list.add(festivalTimes.get(actId));
/* 46 */         LuckyMoneyEntity luckyMoneyEntity1 = luckyMoneyComponent.getEntity(actId.intValue());
/* 47 */         if (!luckyMoneyEntity1.isOpen() && extendComponent.getZeroResetDate() == TimeUtil.getCurrentYearMonthDay() && 
/* 48 */           luckyMoneyActParameter.isActLuckyOpen(actId.intValue())) {
/*    */           
/* 50 */           MoneyPotBean moneyPotBean = (MoneyPotBean)JsonTableService.getJsonData(actId.intValue(), MoneyPotBean.class);
/* 51 */           double d = ((MoneyPotBean.CostRewardBean)moneyPotBean.getCostReward().get(0)).getNum();
/* 52 */           double temCostGold = bagComponent.getItemNum(luckyMoneyActParameter.getCostGoldItem()) + luckyMoneyComponent.getEntity(actId.intValue()).getGoldMoney() * d;
/* 53 */           if (rechardTotal > temCostGold) {
/* 54 */             double cost = rechardTotal - temCostGold;
/* 55 */             long todayRecharge = (long)cost;
/* 56 */             double num = todayRecharge / d;
/* 57 */             bagComponent.addItem(luckyMoneyActParameter.getCostGoldItem(), (int)num, ResourceEvent.LuckyMoney, true);
/*    */           } 
/*    */ 
/*    */           
/* 61 */           double numSilver = ((MoneyPotBean.CostRewardBean)moneyPotBean.getCostReward().get(1)).getNum();
/* 62 */           double temCostSilver = bagComponent.getItemNum(luckyMoneyActParameter.getCostSilverItem()) + luckyMoneyComponent.getEntity(actId.intValue()).getSilverMoney() * numSilver;
/* 63 */           if (costTotal > temCostSilver) {
/* 64 */             double value = costTotal - temCostSilver;
/* 65 */             double num2 = value / numSilver;
/* 66 */             bagComponent.addItem(luckyMoneyActParameter.getCostSilverItem(), (int)num2, ResourceEvent.LuckyMoney, true);
/*    */           } 
/*    */         } 
/*    */ 
/*    */         
/* 71 */         luckyMoneyEntity1.setOpen(true);
/* 72 */         luckyMoneyComponent.updateOpenDB(actId.intValue());
/*    */       } 
/*    */     } 
/* 75 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\luckymoney\LuckyMoneyOpenProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */