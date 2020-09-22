/*    */ package com.linlongyx.sanguo.webgame.processors.fight;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.app.extend.ExtendComponent;
/*    */ import com.linlongyx.sanguo.webgame.common.util.FinanceUtil;
/*    */ import com.linlongyx.sanguo.webgame.constant.ResourceEvent;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.fight.InsRewardRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.fight.InsRewardResponse;
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class InsRewardProcessor
/*    */   extends ProcessorBase<InsRewardRequest, InsRewardResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 21 */     this.response = (ResponseBase)new InsRewardResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, InsRewardRequest request) {
/* 26 */     ExtendComponent extendComponent = (ExtendComponent)playerSession.getPlayer().createIfNotExist(ExtendComponent.class);
/* 27 */     InsRewardResponse insRewardResponse = extendComponent.getInsRewardResponse();
/* 28 */     ((InsRewardResponse)this.response).type = insRewardResponse.type;
/* 29 */     ((InsRewardResponse)this.response).insId = insRewardResponse.insId;
/* 30 */     ((InsRewardResponse)this.response).star = insRewardResponse.star;
/* 31 */     ((InsRewardResponse)this.response).list = new ArrayList(insRewardResponse.list);
/* 32 */     ((InsRewardResponse)this.response).percent = insRewardResponse.percent;
/* 33 */     if (((InsRewardResponse)this.response).list.size() > 0) {
/* 34 */       FinanceUtil.rewardNotice(((InsRewardResponse)this.response).list, playerSession.getPlayer(), ResourceEvent.RewardNotice);
/*    */     }
/* 36 */     extendComponent.clearInsRewardResponse();
/* 37 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\fight\InsRewardProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */