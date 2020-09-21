/*    */ package com.linlongyx.sanguo.webgame.processors.login;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.app.extend.ExtendComponent;
/*    */ import com.linlongyx.sanguo.webgame.app.player.PlayerComponent;
/*    */ import com.linlongyx.sanguo.webgame.app.welfare.WelfareComponent;
/*    */ import com.linlongyx.sanguo.webgame.common.util.FinanceUtil;
/*    */ import com.linlongyx.sanguo.webgame.config.parameter.LoginParameter;
/*    */ import com.linlongyx.sanguo.webgame.constant.ParameterConstant;
/*    */ import com.linlongyx.sanguo.webgame.constant.ResourceEvent;
/*    */ import com.linlongyx.sanguo.webgame.processors.player.PlayerUtil;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.login.OfflineIncomeRewardRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.login.OfflineIncomeRewardResponse;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.Reward;
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class OfflineIncomeRewardProcessor
/*    */   extends ProcessorBase<OfflineIncomeRewardRequest, OfflineIncomeRewardResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 28 */     this.response = (ResponseBase)new OfflineIncomeRewardResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, OfflineIncomeRewardRequest request) {
/* 33 */     ExtendComponent extendComponent = (ExtendComponent)playerSession.getPlayer().createIfNotExist(ExtendComponent.class);
/* 34 */     int offlineSec = extendComponent.getOfflineSec();
/* 35 */     if (offlineSec <= 0) {
/* 36 */       return 10032;
/*    */     }
/* 38 */     LoginParameter loginParameter = (LoginParameter)ParameterConstant.getParameter(0);
/* 39 */     PlayerComponent playerComponent = (PlayerComponent)playerSession.getPlayer().createIfNotExist(PlayerComponent.class);
/* 40 */     ArrayList<Reward> rewards = new ArrayList<>();
/* 41 */     WelfareComponent welfareComponent = (WelfareComponent)playerSession.getPlayer().createIfNotExist(WelfareComponent.class);
/* 42 */     double monthCardRate = welfareComponent.getMonthCardRate();
/* 43 */     PlayerUtil.getOfflineRewards(playerComponent.getMainSceneId(), offlineSec, rewards, monthCardRate);
/* 44 */     extendComponent.setOfflineSec(0);
/* 45 */     if (rewards.size() == 0) {
/* 46 */       return 10032;
/*    */     }
/* 48 */     if (request.type == 0) {
/* 49 */       for (Reward reward : rewards) {
/* 50 */         reward.num = (long)(reward.num + reward.num * loginParameter.getOffineAddtion() / 10000.0D);
/*    */       }
/*    */     }
/* 53 */     FinanceUtil.reward(rewards, playerSession.getPlayer(), ResourceEvent.OfflineIncome, true);
/* 54 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\login\OfflineIncomeRewardProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */