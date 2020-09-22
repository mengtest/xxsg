/*    */ package com.linlongyx.sanguo.webgame.processors.timelimit;
/*    */ 
/*    */ import com.linlongyx.core.framework.logic.IPlayer;
/*    */ import com.linlongyx.sanguo.webgame.app.bag.BagComponent;
/*    */ import com.linlongyx.sanguo.webgame.app.limitbuy.LimitBuyComponent;
/*    */ import com.linlongyx.sanguo.webgame.app.limitexchange.LimitExchangeComponent;
/*    */ import com.linlongyx.sanguo.webgame.app.limitexchange.LimitExchangeEntity;
/*    */ import com.linlongyx.sanguo.webgame.common.util.FinanceUtil;
/*    */ import com.linlongyx.sanguo.webgame.config.bean.ChargeBean;
/*    */ import com.linlongyx.sanguo.webgame.config.bean.SpecialOfferListBean;
/*    */ import com.linlongyx.sanguo.webgame.config.parameter.LimitBuyActParameter;
/*    */ import com.linlongyx.sanguo.webgame.constant.ParameterConstant;
/*    */ import com.linlongyx.sanguo.webgame.constant.ResourceEvent;
/*    */ import com.linlongyx.sanguo.webgame.log.LogUtil;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.FestivalTime;
/*    */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*    */ import com.linlongyx.sanguo.webgame.service.LookUpService;
/*    */ import java.util.HashMap;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class TimeLimitUtil
/*    */ {
/*    */   public static void timeLimitBuy(IPlayer iPlayer, ChargeBean chargeBean) {
/* 28 */     LimitBuyActParameter limitBuyActParameter = (LimitBuyActParameter)ParameterConstant.getParameter(68);
/* 29 */     List<Integer> list = limitBuyActParameter.getLimitBuyAct(true);
/* 30 */     if (list.isEmpty()) {
/*    */       return;
/*    */     }
/* 33 */     LimitBuyComponent limitBuyComponent = (LimitBuyComponent)iPlayer.createIfNotExist(LimitBuyComponent.class);
/* 34 */     if (limitBuyActParameter.isLimitBuyAct(((Integer)list.get(0)).intValue())) {
/* 35 */       Map<Integer, Integer> limitBuyGoods = limitBuyComponent.getLimitBuyGoods(((Integer)list.get(0)).intValue());
/* 36 */       Integer numSpecialOfferList = (Integer)((Map)limitBuyActParameter.getSameActMap().get(list.get(0))).get(Integer.valueOf(chargeBean.getId()));
/* 37 */       if (numSpecialOfferList == null) {
/*    */         return;
/*    */       }
/* 40 */       SpecialOfferListBean targetBean = (SpecialOfferListBean)JsonTableService.getJsonData(numSpecialOfferList.intValue(), SpecialOfferListBean.class);
/*    */ 
/*    */ 
/*    */       
/* 44 */       int num = ((Integer)limitBuyGoods.getOrDefault(Integer.valueOf(targetBean.getId()), Integer.valueOf(0))).intValue();
/* 45 */       if (num >= targetBean.getNumber()) {
/* 46 */         LogUtil.errorLog(new Object[] { "已扣款, 超过购买次数", list.get(0), Integer.valueOf(chargeBean.getId()), Integer.valueOf(chargeBean.getRmb()) });
/*    */         return;
/*    */       } 
/* 49 */       limitBuyGoods.put(Integer.valueOf(targetBean.getId()), Integer.valueOf(num + 1));
/* 50 */       limitBuyComponent.setLimitBuyGoods(((Integer)list.get(0)).intValue(), limitBuyGoods);
/* 51 */       FinanceUtil.reward(FinanceUtil.transform(targetBean.getReward()), iPlayer, ResourceEvent.TimeLimit, true);
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static void resertLimitExchangeGoods(long playerId) {
/* 59 */     LimitBuyActParameter limitBuyActParameter = (LimitBuyActParameter)ParameterConstant.getParameter(68);
/* 60 */     List<Integer> orpm = limitBuyActParameter.getLimitExchangeAct(true);
/* 61 */     if (orpm.isEmpty()) {
/* 62 */       List<Integer> list = limitBuyActParameter.getLimitExchangeAct(false);
/* 63 */       LimitExchangeComponent limitExchangeComponent = (LimitExchangeComponent)LookUpService.getComponent(playerId, LimitExchangeComponent.class);
/* 64 */       Map<Integer, FestivalTime> festivalTimes = limitBuyActParameter.getActTimesExchange();
/* 65 */       for (Integer actId : list) {
/* 66 */         if (festivalTimes.containsKey(actId) && 
/* 67 */           !limitBuyActParameter.isLimitExchangeAct(actId.intValue())) {
/* 68 */           limitExchangeComponent.setLimitExchangeGoods(actId.intValue(), new HashMap<>());
/* 69 */           LimitExchangeEntity limitExchangeEntity = limitExchangeComponent.getEntity(actId.intValue());
/* 70 */           limitExchangeEntity.setOpen(false);
/*    */         } 
/*    */       } 
/*    */       
/* 74 */       BagComponent bagComponent = (BagComponent)LookUpService.getComponent(playerId, BagComponent.class);
/* 75 */       int num = (int)bagComponent.getItemNum(limitBuyActParameter.getExchangeItem());
/* 76 */       bagComponent.deleteItem(limitBuyActParameter.getExchangeItem(), num, ResourceEvent.TimeLimit, true);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\timelimit\TimeLimitUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */