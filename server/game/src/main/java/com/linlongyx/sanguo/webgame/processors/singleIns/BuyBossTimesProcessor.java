/*    */ package com.linlongyx.sanguo.webgame.processors.singleIns;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.app.bag.BagComponent;
/*    */ import com.linlongyx.sanguo.webgame.app.singleIns.SingleInsComponent;
/*    */ import com.linlongyx.sanguo.webgame.config.parameter.BossHomeParameter;
/*    */ import com.linlongyx.sanguo.webgame.constant.ParameterConstant;
/*    */ import com.linlongyx.sanguo.webgame.constant.ResourceEvent;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.singleIns.BuyBossTimesRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.singleIns.BuyBossTimesResponse;
/*    */ import com.linlongyx.sanguo.webgame.util.CostUtil;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BuyBossTimesProcessor
/*    */   extends ProcessorBase<BuyBossTimesRequest, BuyBossTimesResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 22 */     this.response = (ResponseBase)new BuyBossTimesResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, BuyBossTimesRequest request) {
/* 27 */     SingleInsComponent singleInsComponent = (SingleInsComponent)playerSession.getPlayer().createIfNotExist(SingleInsComponent.class);
/* 28 */     BossHomeParameter bossHomeParameter = (BossHomeParameter)ParameterConstant.getParameter(3);
/* 29 */     if (!singleInsComponent.getBossMap().containsKey(Integer.valueOf(request.bossId))) {
/* 30 */       return 13114;
/*    */     }
/* 32 */     if (!singleInsComponent.getTimesMap().containsKey(Integer.valueOf(request.bossId))) {
/* 33 */       return 13114;
/*    */     }
/* 35 */     int value = 0;
/* 36 */     if (singleInsComponent.getBuyTimeMap().containsKey(Integer.valueOf(request.bossId))) {
/* 37 */       value = ((Integer)singleInsComponent.getBuyTimeMap().get(Integer.valueOf(request.bossId))).intValue();
/*    */     }
/* 39 */     if (value >= bossHomeParameter.getMaxBuy()) {
/* 40 */       return 13115;
/*    */     }
/* 42 */     BagComponent bagComponent = (BagComponent)playerSession.getPlayer().createIfNotExist(BagComponent.class);
/* 43 */     value++;
/* 44 */     short res = CostUtil.check(bossHomeParameter.getSingleBossBuyCost(value), playerSession, bagComponent);
/* 45 */     if (res != 0) {
/* 46 */       return 10085;
/*    */     }
/* 48 */     CostUtil.cost(bossHomeParameter.getSingleBossBuyCost(value), playerSession, bagComponent, ResourceEvent.BuySingleBoss);
/* 49 */     singleInsComponent.getBuyTimeMap().put(Integer.valueOf(request.bossId), Integer.valueOf(value));
/* 50 */     singleInsComponent.setBuyTimeMap(singleInsComponent.getBuyTimeMap());
/* 51 */     ((BuyBossTimesResponse)this.response).bossId = request.bossId;
/* 52 */     ((BuyBossTimesResponse)this.response).times = ((Integer)singleInsComponent.getBuyTimeMap().get(Integer.valueOf(request.bossId))).intValue();
/* 53 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\singleIns\BuyBossTimesProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */