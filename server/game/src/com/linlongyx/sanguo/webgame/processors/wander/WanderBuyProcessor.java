/*    */ package com.linlongyx.sanguo.webgame.processors.wander;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.app.bag.BagComponent;
/*    */ import com.linlongyx.sanguo.webgame.app.extend.ExtendComponent;
/*    */ import com.linlongyx.sanguo.webgame.common.util.FinanceUtil;
/*    */ import com.linlongyx.sanguo.webgame.config.bean.WanderRewardBean;
/*    */ import com.linlongyx.sanguo.webgame.constant.FunctionOpenConstant;
/*    */ import com.linlongyx.sanguo.webgame.constant.ResourceEvent;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.Reward;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.wander.WanderBuyRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.wander.WanderBuyResponse;
/*    */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*    */ import com.linlongyx.sanguo.webgame.util.CostUtil;
/*    */ import java.util.ArrayList;
/*    */ import java.util.Map;
/*    */ 
/*    */ 
/*    */ public class WanderBuyProcessor
/*    */   extends ProcessorBase<WanderBuyRequest, WanderBuyResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 26 */     this.response = (ResponseBase)new WanderBuyResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, WanderBuyRequest request) {
/* 31 */     if (!FunctionOpenConstant.isFunctionOpen(playerSession, 71))
/* 32 */       return 10061; 
/* 33 */     BagComponent bagComponent = (BagComponent)playerSession.getPlayer().createIfNotExist(BagComponent.class);
/* 34 */     WanderRewardBean wanderRewardBean = (WanderRewardBean)JsonTableService.getJsonData(request.id, WanderRewardBean.class);
/* 35 */     ExtendComponent extendComponent = (ExtendComponent)playerSession.getPlayer().createIfNotExist(ExtendComponent.class);
/* 36 */     if (wanderRewardBean == null) {
/* 37 */       return 15301;
/*    */     }
/* 39 */     if (wanderRewardBean.getType() == 1 && 
/* 40 */       extendComponent.getTodayRecharge() / 100L < (wanderRewardBean.getRmb() / 100)) {
/* 41 */       return 15302;
/*    */     }
/*    */     
/* 44 */     ArrayList<Reward> costList = FinanceUtil.transform(wanderRewardBean.getCost());
/* 45 */     short resCode = CostUtil.checkRewards(costList, playerSession, bagComponent);
/* 46 */     if (resCode != 0) {
/* 47 */       return resCode;
/*    */     }
/* 49 */     CostUtil.costs(costList, playerSession, bagComponent, ResourceEvent.RunWander);
/* 50 */     FinanceUtil.reward(FinanceUtil.transform(wanderRewardBean.getReward()), playerSession.getPlayer(), ResourceEvent.RunWander, true);
/* 51 */     Map<Integer, Integer> curWander = extendComponent.getWanderReward();
/* 52 */     int num = ((Integer)curWander.getOrDefault(Integer.valueOf(request.id), Integer.valueOf(0))).intValue();
/* 53 */     num++;
/* 54 */     curWander.put(Integer.valueOf(request.id), Integer.valueOf(num));
/* 55 */     extendComponent.setWanderReward(curWander);
/* 56 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\wander\WanderBuyProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */