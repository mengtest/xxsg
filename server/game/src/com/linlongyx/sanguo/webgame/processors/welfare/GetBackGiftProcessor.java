/*    */ package com.linlongyx.sanguo.webgame.processors.welfare;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.core.utils.LogUtils;
/*    */ import com.linlongyx.sanguo.webgame.app.welfare.WelfareComponent;
/*    */ import com.linlongyx.sanguo.webgame.common.util.FinanceUtil;
/*    */ import com.linlongyx.sanguo.webgame.config.bean.ReturnBean;
/*    */ import com.linlongyx.sanguo.webgame.config.bean.RewardBean;
/*    */ import com.linlongyx.sanguo.webgame.constant.ResourceEvent;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.Reward;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.welfare.GetBackGiftRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.welfare.GetBackGiftResponse;
/*    */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class GetBackGiftProcessor
/*    */   extends ProcessorBase<GetBackGiftRequest, GetBackGiftResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 26 */     this.response = (ResponseBase)new GetBackGiftResponse();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, GetBackGiftRequest request) {
/* 32 */     WelfareComponent welfareComponent = (WelfareComponent)playerSession.getPlayer().createIfNotExist(WelfareComponent.class);
/* 33 */     if (welfareComponent.getBackGiftId() == 0) {
/* 34 */       return 13008;
/*    */     }
/* 36 */     ReturnBean returnBean = (ReturnBean)JsonTableService.getJsonData(welfareComponent.getBackGiftId(), ReturnBean.class);
/* 37 */     if (null == returnBean) {
/* 38 */       return 10006;
/*    */     }
/* 40 */     ArrayList<RewardBean> rewardBeans = returnBean.getReward();
/* 41 */     boolean hash = false;
/* 42 */     Reward reward = new Reward();
/* 43 */     for (RewardBean rewardBean : rewardBeans) {
/* 44 */       if (rewardBean.getId() == request.itemId) {
/* 45 */         hash = true;
/* 46 */         reward.id = rewardBean.getId();
/* 47 */         reward.type = (byte)rewardBean.getType();
/* 48 */         reward.num = rewardBean.getNum();
/*    */         break;
/*    */       } 
/*    */     } 
/* 52 */     if (!hash) {
/* 53 */       return 13008;
/*    */     }
/* 55 */     ArrayList<Reward> rewards = new ArrayList<>();
/* 56 */     rewards.add(reward);
/* 57 */     welfareComponent.setBackGiftId(0);
/* 58 */     FinanceUtil.reward(rewards, playerSession.getPlayer(), ResourceEvent.ReturnReward, true);
/* 59 */     ((GetBackGiftResponse)this.response).list = rewards;
/* 60 */     LogUtils.errorLog(new Object[] { "GetBackGiftProcessor", Long.valueOf(playerSession.getPlayer().getPlayerId()), Integer.valueOf(request.itemId) });
/* 61 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\welfare\GetBackGiftProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */