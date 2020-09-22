/*    */ package com.linlongyx.sanguo.webgame.processors.recruit;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.app.bag.BagComponent;
/*    */ import com.linlongyx.sanguo.webgame.common.util.FinanceUtil;
/*    */ import com.linlongyx.sanguo.webgame.config.bean.ItemBean;
/*    */ import com.linlongyx.sanguo.webgame.constant.ResourceEvent;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.recruit.BuyRedRecruitItemRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.recruit.BuyRedRecruitItemResponse;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.Reward;
/*    */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*    */ import com.linlongyx.sanguo.webgame.util.CostUtil;
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BuyRedRecruitItemProcessor
/*    */   extends ProcessorBase<BuyRedRecruitItemRequest, BuyRedRecruitItemResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 25 */     this.response = (ResponseBase)new BuyRedRecruitItemResponse();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, BuyRedRecruitItemRequest request) {
/* 31 */     if (request.itemNum <= 0) {
/* 32 */       return 14020;
/*    */     }
/*    */     
/* 35 */     BagComponent bagComponent = (BagComponent)playerSession.getPlayer().createIfNotExist(BagComponent.class);
/* 36 */     ItemBean itemBean = (ItemBean)JsonTableService.getJsonData(request.itemId, ItemBean.class);
/* 37 */     if (null == itemBean || itemBean.getBuyPrice().isEmpty()) {
/* 38 */       return 10708;
/*    */     }
/* 40 */     if (request.itemNum > itemBean.getMaxBuy()) {
/* 41 */       return 18004;
/*    */     }
/* 43 */     short code = CostUtil.check(itemBean.getBuyPrice(), request.itemNum, playerSession, bagComponent);
/* 44 */     if (0 != code) {
/* 45 */       return code;
/*    */     }
/* 47 */     CostUtil.cost(itemBean.getBuyPrice(), request.itemNum, playerSession, bagComponent, ResourceEvent.RecruitRedBuy);
/* 48 */     Reward reward = new Reward();
/* 49 */     reward.type = (byte)itemBean.getItemType();
/* 50 */     reward.id = request.itemId;
/* 51 */     reward.num = request.itemNum;
/* 52 */     ArrayList<Reward> rewards = new ArrayList<>();
/* 53 */     rewards.add(reward);
/* 54 */     FinanceUtil.reward(rewards, playerSession.getPlayer(), ResourceEvent.QuickBuyItem, true);
/* 55 */     ((BuyRedRecruitItemResponse)this.response).itemId = request.itemId;
/* 56 */     ((BuyRedRecruitItemResponse)this.response).itemNum = request.itemNum;
/* 57 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\recruit\BuyRedRecruitItemProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */