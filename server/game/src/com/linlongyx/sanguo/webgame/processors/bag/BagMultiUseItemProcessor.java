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
/*    */ import com.linlongyx.sanguo.webgame.constant.ResourceEvent;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.bag.BagMultiUseItemRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.bag.BagMultiUseItemResponse;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.Reward;
/*    */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*    */ import com.linlongyx.sanguo.webgame.util.CostUtil;
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BagMultiUseItemProcessor
/*    */   extends ProcessorBase<BagMultiUseItemRequest, BagMultiUseItemResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 27 */     this.response = (ResponseBase)new BagMultiUseItemResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, BagMultiUseItemRequest request) {
/* 32 */     int itemId = request.itemId;
/* 33 */     ItemBean itemBean = (ItemBean)JsonTableService.getJsonData(itemId, ItemBean.class);
/* 34 */     if (null == itemBean) {
/* 35 */       return 10701;
/*    */     }
/* 37 */     if (itemBean.getUseType() != 22) {
/* 38 */       return 10705;
/*    */     }
/* 40 */     int num = request.num;
/* 41 */     if (num <= 0) {
/* 42 */       return 10720;
/*    */     }
/* 44 */     BagComponent bagComponent = (BagComponent)playerSession.getPlayer().createIfNotExist(BagComponent.class);
/* 45 */     if (bagComponent.getItemNum(itemId) < num) {
/* 46 */       return 10704;
/*    */     }
/* 48 */     short code = CostUtil.check(itemBean.getBuyPrice(), num, playerSession, bagComponent);
/* 49 */     if (0 != code) {
/* 50 */       return code;
/*    */     }
/*    */     
/* 53 */     ArrayList<Reward> rewards = new ArrayList<>();
/* 54 */     int chooseId = request.chooseId;
/* 55 */     for (RewardBean rewardBean : itemBean.getReward()) {
/* 56 */       if (rewardBean.getId() == chooseId) {
/* 57 */         Reward reward = new Reward();
/* 58 */         reward.type = (byte)rewardBean.getType();
/* 59 */         reward.id = rewardBean.getId();
/* 60 */         reward.num = rewardBean.getNum() * num;
/* 61 */         rewards.add(reward);
/*    */         break;
/*    */       } 
/*    */     } 
/* 65 */     if (rewards.size() == 0) {
/* 66 */       return 10703;
/*    */     }
/*    */     
/* 69 */     CostUtil.cost(itemBean.getBuyPrice(), num, playerSession, bagComponent, ResourceEvent.MultiUseItem);
/* 70 */     bagComponent.deleteItem(itemId, num, ResourceEvent.MultiUseItem, true);
/*    */     
/* 72 */     FinanceUtil.reward(rewards, playerSession.getPlayer(), ResourceEvent.MultiUseItem, true);
/* 73 */     ((BagMultiUseItemResponse)this.response).itemId = itemId;
/* 74 */     ((BagMultiUseItemResponse)this.response).num = num;
/* 75 */     ((BagMultiUseItemResponse)this.response).chooseId = chooseId;
/* 76 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\bag\BagMultiUseItemProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */