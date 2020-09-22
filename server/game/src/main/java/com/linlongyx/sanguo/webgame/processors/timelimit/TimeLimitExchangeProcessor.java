/*    */ package com.linlongyx.sanguo.webgame.processors.timelimit;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.app.bag.BagComponent;
/*    */ import com.linlongyx.sanguo.webgame.app.limitexchange.LimitExchangeComponent;
/*    */ import com.linlongyx.sanguo.webgame.common.util.FinanceUtil;
/*    */ import com.linlongyx.sanguo.webgame.config.bean.SpecialOfferListBean;
/*    */ import com.linlongyx.sanguo.webgame.constant.ResourceEvent;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.Reward;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.timelimit.TimeLimitExchangeRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.timelimit.TimeLimitExchangeResponse;
/*    */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*    */ import com.linlongyx.sanguo.webgame.util.CostUtil;
/*    */ import java.util.ArrayList;
/*    */ import java.util.Map;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class TimeLimitExchangeProcessor
/*    */   extends ProcessorBase<TimeLimitExchangeRequest, TimeLimitExchangeResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 27 */     this.response = (ResponseBase)new TimeLimitExchangeResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, TimeLimitExchangeRequest request) {
/* 32 */     LimitExchangeComponent limitExchangeComponent = (LimitExchangeComponent)playerSession.getPlayer().createIfNotExist(LimitExchangeComponent.class);
/* 33 */     BagComponent bagComponent = (BagComponent)playerSession.getPlayer().createIfNotExist(BagComponent.class);
/* 34 */     Map<Integer, Integer> limitExchangeGoods = limitExchangeComponent.getLimitExchangeGoods(request.id);
/* 35 */     int num = ((Integer)limitExchangeGoods.getOrDefault(Integer.valueOf(request.packageType), Integer.valueOf(0))).intValue();
/* 36 */     SpecialOfferListBean specialOfferListBean = (SpecialOfferListBean)JsonTableService.getJsonData(request.packageType, SpecialOfferListBean.class);
/* 37 */     if (num >= specialOfferListBean.getNumber()) {
/* 38 */       return 11903;
/*    */     }
/* 40 */     ArrayList<Reward> costList = FinanceUtil.transform(specialOfferListBean.getCost());
/* 41 */     short resCode = CostUtil.checkRewards(costList, playerSession, bagComponent);
/* 42 */     if (resCode != 0) {
/* 43 */       return resCode;
/*    */     }
/* 45 */     CostUtil.costs(costList, playerSession, bagComponent, ResourceEvent.TimeLimit);
/* 46 */     FinanceUtil.reward(FinanceUtil.transform(specialOfferListBean.getReward()), playerSession.getPlayer(), ResourceEvent.TimeLimit, true);
/* 47 */     limitExchangeGoods.put(Integer.valueOf(request.packageType), Integer.valueOf(num + 1));
/* 48 */     limitExchangeComponent.setLimitExchangeGoods(request.id, limitExchangeGoods);
/* 49 */     ((TimeLimitExchangeResponse)this.response).packageType = request.packageType;
/* 50 */     ((TimeLimitExchangeResponse)this.response).num = ((Integer)limitExchangeGoods.get(Integer.valueOf(request.packageType))).intValue();
/* 51 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\timelimit\TimeLimitExchangeProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */