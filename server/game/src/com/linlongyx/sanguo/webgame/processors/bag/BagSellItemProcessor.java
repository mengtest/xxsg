/*    */ package com.linlongyx.sanguo.webgame.processors.bag;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.app.bag.BagComponent;
/*    */ import com.linlongyx.sanguo.webgame.common.util.FinanceUtil;
/*    */ import com.linlongyx.sanguo.webgame.config.bean.ItemBean;
/*    */ import com.linlongyx.sanguo.webgame.config.bean.RewardBean;
/*    */ import com.linlongyx.sanguo.webgame.constant.FunctionOpenConstant;
/*    */ import com.linlongyx.sanguo.webgame.constant.ResourceEvent;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.bag.BagSellItemRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.bag.BagSellItemResponse;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.Reward;
/*    */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*    */ import java.util.ArrayList;
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BagSellItemProcessor
/*    */   extends ProcessorBase<BagSellItemRequest, BagSellItemResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 28 */     this.response = (ResponseBase)new BagSellItemResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, BagSellItemRequest request) {
/* 33 */     if (!FunctionOpenConstant.isFunctionOpen(playerSession, 1004))
/* 34 */       return 10061; 
/* 35 */     BagComponent bagComponent = (BagComponent)playerSession.getPlayer().createIfNotExist(BagComponent.class);
/* 36 */     ArrayList<Reward> rewards = new ArrayList<>();
/* 37 */     for (int i = 0; i < request.ids.size(); i++) {
/* 38 */       int itemId = ((Integer)request.ids.get(i)).intValue();
/* 39 */       int itemNum = ((Integer)request.nums.get(i)).intValue();
/* 40 */       boolean enough = bagComponent.check(itemId, itemNum);
/* 41 */       if (itemNum <= 0) {
/* 42 */         return 10720;
/*    */       }
/* 44 */       if (!enough) {
/* 45 */         return 10709;
/*    */       }
/* 47 */       ItemBean itemBean = (ItemBean)JsonTableService.getJsonData(itemId, ItemBean.class);
/* 48 */       if (null != itemBean && !itemBean.getFurnace().isEmpty()) {
/*    */ 
/*    */         
/* 51 */         bagComponent.deleteItem(itemId, itemNum, ResourceEvent.BagSell, false);
/* 52 */         ArrayList<Reward> temp = new ArrayList<>();
/* 53 */         itemBean.getFurnace().forEach(rewardBean -> {
/*    */               Reward reward = new Reward();
/*    */               reward.type = (byte)rewardBean.getType();
/*    */               reward.id = rewardBean.getId();
/*    */               reward.num = rewardBean.getNum() * itemNum;
/*    */               temp.add(reward);
/*    */             });
/* 60 */         rewards.addAll(temp);
/*    */       } 
/* 62 */     }  bagComponent.notice();
/*    */     
/* 64 */     Map<Long, Long> mapRewards = new HashMap<>();
/* 65 */     FinanceUtil.overlyingMap(mapRewards, rewards);
/* 66 */     ArrayList<Reward> reward2 = FinanceUtil.overlyingReward(mapRewards);
/* 67 */     if (!reward2.isEmpty()) {
/* 68 */       FinanceUtil.reward(reward2, playerSession.getPlayer(), ResourceEvent.BagSell, true);
/*    */     }
/* 70 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\bag\BagSellItemProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */