/*    */ package com.linlongyx.sanguo.webgame.processors.bag;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.app.bag.BagComponent;
/*    */ import com.linlongyx.sanguo.webgame.app.extend.ExtendComponent;
/*    */ import com.linlongyx.sanguo.webgame.app.player.PlayerComponent;
/*    */ import com.linlongyx.sanguo.webgame.common.util.FinanceUtil;
/*    */ import com.linlongyx.sanguo.webgame.config.parameter.BagParameter;
/*    */ import com.linlongyx.sanguo.webgame.constant.ParameterConstant;
/*    */ import com.linlongyx.sanguo.webgame.constant.ResourceEvent;
/*    */ import com.linlongyx.sanguo.webgame.processors.login.VipUtil;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.bag.BagAddGridRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.bag.BagAddGridResponse;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.Reward;
/*    */ import com.linlongyx.sanguo.webgame.util.CostUtil;
/*    */ 
/*    */ public class BagAddGridProcessor
/*    */   extends ProcessorBase<BagAddGridRequest, BagAddGridResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 24 */     this.response = (ResponseBase)new BagAddGridResponse();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, BagAddGridRequest request) {
/* 32 */     if (request.num <= 0) {
/* 33 */       return 13110;
/*    */     }
/* 35 */     BagParameter bagParameter = (BagParameter)ParameterConstant.getParameter(7);
/* 36 */     BagComponent bagComponent = (BagComponent)playerSession.getPlayer().createIfNotExist(BagComponent.class);
/*    */     
/* 38 */     Reward reward = FinanceUtil.transformNewReward(bagParameter.getGridPrice(), request.num);
/* 39 */     short result = CostUtil.check(reward, playerSession, bagComponent);
/* 40 */     if (result != 0) {
/* 41 */       return result;
/*    */     }
/*    */     
/* 44 */     CostUtil.cost(reward, playerSession, bagComponent, ResourceEvent.BagBuyGrid);
/* 45 */     ExtendComponent extendComponent = (ExtendComponent)playerSession.getPlayer().createIfNotExist(ExtendComponent.class);
/* 46 */     extendComponent.setBagSize(extendComponent.getBagSize() + request.num);
/* 47 */     PlayerComponent playerComponent = (PlayerComponent)playerSession.getPlayer().createIfNotExist(PlayerComponent.class);
/* 48 */     int maxCount = VipUtil.getNum(playerComponent.getVip(), 12) + extendComponent.getBagSize();
/* 49 */     ((BagAddGridResponse)this.response).num = maxCount;
/* 50 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\bag\BagAddGridProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */