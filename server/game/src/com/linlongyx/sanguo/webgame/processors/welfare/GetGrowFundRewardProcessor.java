/*    */ package com.linlongyx.sanguo.webgame.processors.welfare;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.core.utils.TimeUtil;
/*    */ import com.linlongyx.sanguo.webgame.app.welfare.WelfareComponent;
/*    */ import com.linlongyx.sanguo.webgame.common.util.FinanceUtil;
/*    */ import com.linlongyx.sanguo.webgame.config.bean.GrowFondationBean;
/*    */ import com.linlongyx.sanguo.webgame.constant.ResourceEvent;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.welfare.GetGrowFundRewardRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.welfare.GetGrowFundRewardResponse;
/*    */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class GetGrowFundRewardProcessor
/*    */   extends ProcessorBase<GetGrowFundRewardRequest, GetGrowFundRewardResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 23 */     this.response = (ResponseBase)new GetGrowFundRewardResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, GetGrowFundRewardRequest request) {
/* 28 */     WelfareComponent welfareComponent = (WelfareComponent)playerSession.getPlayer().createIfNotExist(WelfareComponent.class);
/* 29 */     if (!welfareComponent.isBuyFund()) {
/* 30 */       return 15018;
/*    */     }
/* 32 */     GrowFondationBean growFondationBean = (GrowFondationBean)JsonTableService.getJsonData(request.level, GrowFondationBean.class);
/* 33 */     if (null == growFondationBean) {
/* 34 */       return 15015;
/*    */     }
/* 36 */     if (request.level > 10000) {
/* 37 */       return 15015;
/*    */     }
/* 39 */     if (welfareComponent.getFundReward().containsKey(Integer.valueOf(request.level))) {
/* 40 */       return 15014;
/*    */     }
/* 42 */     welfareComponent.getFundReward().put(Integer.valueOf(request.level), Integer.valueOf(TimeUtil.currentTime()));
/* 43 */     welfareComponent.setFundReward(welfareComponent.getFundReward());
/* 44 */     FinanceUtil.reward(FinanceUtil.transform(growFondationBean.getReward()), playerSession.getPlayer(), ResourceEvent.GetGrowFund, true);
/* 45 */     ((GetGrowFundRewardResponse)this.response).level = request.level;
/* 46 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\welfare\GetGrowFundRewardProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */