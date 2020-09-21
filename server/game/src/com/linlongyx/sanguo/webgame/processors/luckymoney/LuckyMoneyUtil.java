/*    */ package com.linlongyx.sanguo.webgame.processors.luckymoney;
/*    */ 
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.sanguo.webgame.app.bag.BagComponent;
/*    */ import com.linlongyx.sanguo.webgame.app.luckymoney.LuckyMoneyComponent;
/*    */ import com.linlongyx.sanguo.webgame.app.luckymoney.LuckyMoneyEntity;
/*    */ import com.linlongyx.sanguo.webgame.config.bean.MoneyPotBean;
/*    */ import com.linlongyx.sanguo.webgame.config.parameter.LuckyMoneyActParameter;
/*    */ import com.linlongyx.sanguo.webgame.constant.ParameterConstant;
/*    */ import com.linlongyx.sanguo.webgame.constant.ResourceEvent;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.FestivalTime;
/*    */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*    */ import com.linlongyx.sanguo.webgame.service.LookUpService;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class LuckyMoneyUtil
/*    */ {
/*    */   public static void addLuckyItem(int cost, IPlayerSession playerSession) {
/* 25 */     LuckyMoneyActParameter luckyMoneyActParameter = (LuckyMoneyActParameter)ParameterConstant.getParameter(61);
/* 26 */     List<Integer> list = luckyMoneyActParameter.getLuckySaveAct(true);
/* 27 */     Map<Integer, FestivalTime> festivalTimes = luckyMoneyActParameter.getActTimes();
/* 28 */     BagComponent bagComponent = (BagComponent)playerSession.getPlayer().createIfNotExist(BagComponent.class);
/* 29 */     for (Integer actId : list) {
/* 30 */       if (festivalTimes.containsKey(actId) && 
/* 31 */         luckyMoneyActParameter.isActLuckyOpen(actId.intValue())) {
/* 32 */         MoneyPotBean moneyPotBean = (MoneyPotBean)JsonTableService.getJsonData(actId.intValue(), MoneyPotBean.class);
/* 33 */         double d = ((MoneyPotBean.CostRewardBean)moneyPotBean.getCostReward().get(0)).getNum();
/* 34 */         long todayRecharge = cost;
/* 35 */         double num = todayRecharge / d;
/* 36 */         bagComponent.addItem(luckyMoneyActParameter.getCostGoldItem(), (int)num, ResourceEvent.LuckyMoney, true);
/*    */       } 
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static void addLucky(long playerId, long value) {
/* 47 */     LuckyMoneyActParameter luckyMoneyActParameter = (LuckyMoneyActParameter)ParameterConstant.getParameter(61);
/* 48 */     List<Integer> list = luckyMoneyActParameter.getLuckySaveAct(true);
/* 49 */     BagComponent bagComponent = (BagComponent)LookUpService.getComponent(playerId, BagComponent.class);
/* 50 */     Map<Integer, FestivalTime> festivalTimes = luckyMoneyActParameter.getActTimes();
/* 51 */     for (Integer actId : list) {
/* 52 */       if (festivalTimes.containsKey(actId) && 
/* 53 */         luckyMoneyActParameter.isActLuckyOpen(actId.intValue())) {
/* 54 */         MoneyPotBean moneyPotBean = (MoneyPotBean)JsonTableService.getJsonData(actId.intValue(), MoneyPotBean.class);
/* 55 */         double d = ((MoneyPotBean.CostRewardBean)moneyPotBean.getCostReward().get(1)).getNum();
/* 56 */         double num = value / d;
/* 57 */         bagComponent.addItem(luckyMoneyActParameter.getCostSilverItem(), (int)num, ResourceEvent.LuckyMoney, true);
/*    */       } 
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static void addPoint(long point, IPlayerSession playerSession) {
/* 70 */     LuckyMoneyActParameter luckyMoneyActParameter = (LuckyMoneyActParameter)ParameterConstant.getParameter(61);
/* 71 */     List<Integer> list = luckyMoneyActParameter.getLuckySaveAct(true);
/* 72 */     Map<Integer, FestivalTime> festivalTimes = luckyMoneyActParameter.getActTimes();
/* 73 */     for (Integer actId : list) {
/* 74 */       if (festivalTimes.containsKey(actId) && 
/* 75 */         luckyMoneyActParameter.isActSaveLuckyOpen(actId.intValue())) {
/* 76 */         LuckyMoneyComponent luckyMoneyComponent = (LuckyMoneyComponent)playerSession.getPlayer().createIfNotExist(LuckyMoneyComponent.class);
/* 77 */         LuckyMoneyEntity luckyMoneyEntity = luckyMoneyComponent.getEntity(actId.intValue());
/* 78 */         luckyMoneyEntity.setTaskPoint(point);
/* 79 */         luckyMoneyComponent.updateTaskPoint(actId.intValue());
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\luckymoney\LuckyMoneyUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */