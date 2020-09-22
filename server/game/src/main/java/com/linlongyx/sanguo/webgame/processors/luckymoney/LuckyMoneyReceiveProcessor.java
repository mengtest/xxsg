/*    */ package com.linlongyx.sanguo.webgame.processors.luckymoney;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.core.utils.TimeUtil;
/*    */ import com.linlongyx.sanguo.webgame.app.luckymoney.LuckyMoneyComponent;
/*    */ import com.linlongyx.sanguo.webgame.app.luckymoney.LuckyMoneyEntity;
/*    */ import com.linlongyx.sanguo.webgame.common.util.FinanceUtil;
/*    */ import com.linlongyx.sanguo.webgame.config.bean.MoneyPotBean;
/*    */ import com.linlongyx.sanguo.webgame.config.parameter.LuckyMoneyActParameter;
/*    */ import com.linlongyx.sanguo.webgame.constant.CurrencyType;
/*    */ import com.linlongyx.sanguo.webgame.constant.FunctionOpenConstant;
/*    */ import com.linlongyx.sanguo.webgame.constant.ParameterConstant;
/*    */ import com.linlongyx.sanguo.webgame.constant.ResourceEvent;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.luckymoney.LuckyMoneyReceiveRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.luckymoney.LuckyMoneyReceiveResponse;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.FestivalTime;
/*    */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*    */ 
/*    */ public class LuckyMoneyReceiveProcessor extends ProcessorBase<LuckyMoneyReceiveRequest, LuckyMoneyReceiveResponse> {
/*    */   protected void initResponse() {
/* 24 */     this.response = (ResponseBase)new LuckyMoneyReceiveResponse();
/*    */   }
/* 26 */   long costGold = 0L;
/* 27 */   long costSilver = 0L;
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, LuckyMoneyReceiveRequest request) {
/* 30 */     if (!FunctionOpenConstant.isFunctionOpen(playerSession, 61))
/* 31 */       return 10061; 
/* 32 */     LuckyMoneyActParameter luckyMoneyActParameter = (LuckyMoneyActParameter)ParameterConstant.getParameter(61);
/* 33 */     if (!luckyMoneyActParameter.isActSaveLuckyOpen(request.id)) {
/* 34 */       return 12102;
/*    */     }
/* 36 */     MoneyPotBean moneyPotBean = (MoneyPotBean)JsonTableService.getJsonData(request.id, MoneyPotBean.class);
/* 37 */     LuckyMoneyComponent luckyMoneyComponent = (LuckyMoneyComponent)playerSession.getPlayer().createIfNotExist(LuckyMoneyComponent.class);
/* 38 */     LuckyMoneyEntity luckyMoneyEntity = luckyMoneyComponent.getEntity(request.id);
/* 39 */     if (luckyMoneyEntity.getTaskPoint() < moneyPotBean.getReceivePoints()) {
/* 40 */       return 12213;
/*    */     }
/* 42 */     if (luckyMoneyEntity.getBattle() == 1) {
/* 43 */       return 10091;
/*    */     }
/* 45 */     FestivalTime festivalTime = luckyMoneyActParameter.getActTime(request.id);
/* 46 */     int day = TimeUtil.getDayDiff(festivalTime.endTime) + 1;
/* 47 */     if (luckyMoneyEntity.getGoldMoney() > 0L) {
/* 48 */       long tempCostGold = luckyMoneyEntity.getGoldMoneySum() * ((Integer)luckyMoneyActParameter.getCostDay().get(Integer.valueOf(day))).intValue() / 10000L;
/* 49 */       this.costGold = (long)Math.floor(tempCostGold);
/* 50 */       if (this.costGold >= luckyMoneyEntity.getGoldMoney()) {
/* 51 */         this.costGold = luckyMoneyEntity.getGoldMoney();
/* 52 */         luckyMoneyEntity.setGoldMoney(0L);
/* 53 */         luckyMoneyComponent.updateGoldMoneyDB(request.id);
/*    */       } else {
/* 55 */         luckyMoneyEntity.setGoldMoney(luckyMoneyEntity.getGoldMoney() - this.costGold);
/* 56 */         luckyMoneyComponent.updateGoldMoneyDB(request.id);
/*    */       } 
/*    */     } 
/* 59 */     if (luckyMoneyEntity.getSilverMoney() > 0L) {
/* 60 */       long tempCostSilver = luckyMoneyEntity.getSilverMoneySum() * ((Integer)luckyMoneyActParameter.getCostDay().get(Integer.valueOf(day))).intValue() / 10000L;
/* 61 */       this.costSilver = (long)Math.floor(tempCostSilver);
/* 62 */       if (this.costSilver >= luckyMoneyEntity.getSilverMoney()) {
/* 63 */         this.costSilver = luckyMoneyEntity.getSilverMoney();
/* 64 */         luckyMoneyEntity.setSilverMoney(0L);
/* 65 */         luckyMoneyComponent.updateSilverMoneyDB(request.id);
/*    */       } else {
/* 67 */         luckyMoneyEntity.setSilverMoney(luckyMoneyEntity.getSilverMoney() - this.costSilver);
/* 68 */         luckyMoneyComponent.updateSilverMoneyDB(request.id);
/*    */       } 
/*    */     } 
/* 71 */     double sum_gold = this.costGold * (((MoneyPotBean.RebateRewardBean)moneyPotBean.getRebateReward().get(0)).getNum() * ((MoneyPotBean.CostRewardBean)moneyPotBean.getCostReward().get(0)).getNum()) / 10000.0D;
/* 72 */     double sum_silver = this.costSilver * (((MoneyPotBean.RebateRewardBean)moneyPotBean.getRebateReward().get(1)).getNum() * ((MoneyPotBean.CostRewardBean)moneyPotBean.getCostReward().get(1)).getNum()) / 10000.0D;
/* 73 */     long sum_lucky = (long)Math.floor(sum_gold) + (long)Math.floor(sum_silver);
/* 74 */     FinanceUtil.addCurrency(playerSession.getPlayer(), CurrencyType.CCY, sum_lucky, ResourceEvent.LuckyMoney);
/*    */     
/* 76 */     luckyMoneyEntity.setBattle(1);
/* 77 */     luckyMoneyComponent.updateBattle(request.id);
/* 78 */     ((LuckyMoneyReceiveResponse)this.response).goldMoneypot = luckyMoneyEntity.getGoldMoney();
/* 79 */     ((LuckyMoneyReceiveResponse)this.response).silverMoneypot = luckyMoneyEntity.getSilverMoney();
/* 80 */     ((LuckyMoneyReceiveResponse)this.response).battle = luckyMoneyEntity.getBattle();
/*    */     
/* 82 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\luckymoney\LuckyMoneyReceiveProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */