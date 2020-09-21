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
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.welfare.GroupBuyFundRewardRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.welfare.GroupBuyFundRewardResponse;
/*    */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class GroupBuyFundRewardProcessor
/*    */   extends ProcessorBase<GroupBuyFundRewardRequest, GroupBuyFundRewardResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 25 */     this.response = (ResponseBase)new GroupBuyFundRewardResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, GroupBuyFundRewardRequest request) {
/* 30 */     WelfareComponent welfareComponent = (WelfareComponent)playerSession.getPlayer().createIfNotExist(WelfareComponent.class);
/* 31 */     GrowFondationBean growFondationBean = (GrowFondationBean)JsonTableService.getJsonData(request.groupId, GrowFondationBean.class);
/*    */     
/* 33 */     if (growFondationBean.getLevel() < 10000) {
/* 34 */       return 15001;
/*    */     }
/* 36 */     int group = request.groupId % 10000;
/* 37 */     if (null == growFondationBean) {
/* 38 */       return 15001;
/*    */     }
/* 40 */     if (group > WelfareUtil.growfundNum.get()) {
/* 41 */       return 12705;
/*    */     }
/* 43 */     if (welfareComponent.getFundReward().containsKey(Integer.valueOf(request.groupId))) {
/* 44 */       return 15002;
/*    */     }
/* 46 */     welfareComponent.getFundReward().put(Integer.valueOf(request.groupId), Integer.valueOf(TimeUtil.currentTime()));
/* 47 */     welfareComponent.setFundReward(welfareComponent.getFundReward());
/* 48 */     FinanceUtil.reward(FinanceUtil.transform(growFondationBean.getReward()), playerSession.getPlayer(), ResourceEvent.GroupGrowFund, true);
/* 49 */     ((GroupBuyFundRewardResponse)this.response).groupId = request.groupId;
/* 50 */     ((GroupBuyFundRewardResponse)this.response).list = new ArrayList(welfareComponent.getFundReward().keySet());
/* 51 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\welfare\GroupBuyFundRewardProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */