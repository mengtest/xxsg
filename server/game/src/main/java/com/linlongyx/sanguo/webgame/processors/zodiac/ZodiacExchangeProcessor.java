/*    */ package com.linlongyx.sanguo.webgame.processors.zodiac;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.app.bag.BagComponent;
/*    */ import com.linlongyx.sanguo.webgame.app.zodiac.ZodiacComponent;
/*    */ import com.linlongyx.sanguo.webgame.common.util.FinanceUtil;
/*    */ import com.linlongyx.sanguo.webgame.config.bean.ZodiacShopBean;
/*    */ import com.linlongyx.sanguo.webgame.config.parameter.ZodiacParameter;
/*    */ import com.linlongyx.sanguo.webgame.constant.CurrencyType;
/*    */ import com.linlongyx.sanguo.webgame.constant.FunctionOpenConstant;
/*    */ import com.linlongyx.sanguo.webgame.constant.ParameterConstant;
/*    */ import com.linlongyx.sanguo.webgame.constant.ResourceEvent;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.Reward;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.zodiac.ZodiacExchangeRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.zodiac.ZodiacExchangeResponse;
/*    */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*    */ import com.linlongyx.sanguo.webgame.util.CostUtil;
/*    */ import java.util.ArrayList;
/*    */ import java.util.Map;
/*    */ 
/*    */ public class ZodiacExchangeProcessor
/*    */   extends ProcessorBase<ZodiacExchangeRequest, ZodiacExchangeResponse> {
/*    */   protected void initResponse() {
/* 27 */     this.response = (ResponseBase)new ZodiacExchangeResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, ZodiacExchangeRequest request) {
/* 32 */     if (!FunctionOpenConstant.isFunctionOpen(playerSession, 75))
/* 33 */       return 10061; 
/* 34 */     ZodiacParameter zodiacParameter = (ZodiacParameter)ParameterConstant.getParameter(73);
/* 35 */     ZodiacComponent zodiacComponent = (ZodiacComponent)playerSession.getPlayer().createIfNotExist(ZodiacComponent.class);
/* 36 */     if (!zodiacParameter.isZodiacAct(request.actId)) {
/* 37 */       return 14301;
/*    */     }
/* 39 */     ZodiacShopBean zodiacShopBean = (ZodiacShopBean)JsonTableService.getJsonData(request.itemId, ZodiacShopBean.class);
/* 40 */     if (zodiacShopBean == null) {
/* 41 */       return 17501;
/*    */     }
/* 43 */     if (request.num <= 0) {
/* 44 */       return 13108;
/*    */     }
/* 46 */     Map<Integer, Integer> zodiacShop = zodiacComponent.getZodiacShop(request.actId);
/* 47 */     int num = ((Integer)zodiacShop.getOrDefault(Integer.valueOf(request.itemId), Integer.valueOf(0))).intValue();
/*    */     
/* 49 */     int buyNum = num + request.num;
/* 50 */     if (buyNum <= 0 || buyNum > zodiacShopBean.getSellTimes()) {
/* 51 */       return 13108;
/*    */     }
/*    */     
/* 54 */     int cost = request.num * zodiacShopBean.getCostNum();
/* 55 */     if (cost != 0) {
/* 56 */       if (!FinanceUtil.checkCurrency(playerSession.getPlayer(), CurrencyType.getCurrencyType(zodiacShopBean.getCostType()), cost)) {
/* 57 */         return 10085;
/*    */       }
/* 59 */       FinanceUtil.decCurrency(playerSession.getPlayer(), CurrencyType.getCurrencyType(zodiacShopBean.getCostType()), cost, ResourceEvent.ZodiacReward, true);
/*    */     } else {
/* 61 */       BagComponent bagComponent = (BagComponent)playerSession.getPlayer().createIfNotExist(BagComponent.class);
/* 62 */       if (zodiacShopBean.getCostItem() != 0) {
/* 63 */         Reward reward = new Reward();
/* 64 */         int itemType = FinanceUtil.correctType(2, zodiacShopBean.getCostItem());
/* 65 */         reward.type = (byte)itemType;
/* 66 */         reward.num = zodiacShopBean.getItemNum() * request.num;
/* 67 */         reward.id = zodiacShopBean.getCostItem();
/* 68 */         short res = CostUtil.checkAndCost(reward, playerSession, bagComponent, ResourceEvent.RoutineBuy);
/* 69 */         if (res != 0) {
/* 70 */           return res;
/*    */         }
/*    */       } 
/*    */     } 
/*    */     
/* 75 */     ArrayList<Reward> rewardList = FinanceUtil.transform(zodiacShopBean.getReward());
/* 76 */     for (Reward r : rewardList) {
/* 77 */       r.num *= request.num;
/*    */     }
/*    */     
/* 80 */     FinanceUtil.reward(rewardList, playerSession.getPlayer(), ResourceEvent.ZodiacReward, true);
/* 81 */     zodiacShop.put(Integer.valueOf(request.itemId), Integer.valueOf(buyNum));
/* 82 */     zodiacComponent.setZodiacShop(request.actId, zodiacShop);
/* 83 */     ((ZodiacExchangeResponse)this.response).actId = request.actId;
/* 84 */     ((ZodiacExchangeResponse)this.response).itemId = request.itemId;
/* 85 */     ((ZodiacExchangeResponse)this.response).num = buyNum;
/* 86 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\zodiac\ZodiacExchangeProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */