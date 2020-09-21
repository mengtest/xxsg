/*    */ package com.linlongyx.sanguo.webgame.processors.login;
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.app.bag.BagComponent;
/*    */ import com.linlongyx.sanguo.webgame.app.extend.ExtendComponent;
/*    */ import com.linlongyx.sanguo.webgame.app.player.PlayerComponent;
/*    */ import com.linlongyx.sanguo.webgame.app.task.TaskComponent;
/*    */ import com.linlongyx.sanguo.webgame.app.welfare.WelfareComponent;
/*    */ import com.linlongyx.sanguo.webgame.common.util.FinanceUtil;
/*    */ import com.linlongyx.sanguo.webgame.config.parameter.LoginParameter;
/*    */ import com.linlongyx.sanguo.webgame.constant.CurrencyType;
/*    */ import com.linlongyx.sanguo.webgame.constant.FunctionOpenConstant;
/*    */ import com.linlongyx.sanguo.webgame.constant.MilitaryInsType;
/*    */ import com.linlongyx.sanguo.webgame.constant.ParameterConstant;
/*    */ import com.linlongyx.sanguo.webgame.constant.ResourceEvent;
/*    */ import com.linlongyx.sanguo.webgame.constant.TaskType;
/*    */ import com.linlongyx.sanguo.webgame.processors.offices.MilitaryUtil;
/*    */ import com.linlongyx.sanguo.webgame.processors.player.PlayerUtil;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.login.RapidCombatRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.login.RapidCombatResponse;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.Reward;
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ public class RapidCombatProcessor extends ProcessorBase<RapidCombatRequest, RapidCombatResponse> {
/*    */   protected void initResponse() {
/* 28 */     this.response = (ResponseBase)new RapidCombatResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, RapidCombatRequest request) {
/* 33 */     if (!FunctionOpenConstant.isFunctionOpen(playerSession, 7))
/* 34 */       return 10061; 
/* 35 */     PlayerComponent playerComponent = (PlayerComponent)playerSession.getPlayer().createIfNotExist(PlayerComponent.class);
/* 36 */     ExtendComponent extendComponent = (ExtendComponent)playerSession.getPlayer().createIfNotExist(ExtendComponent.class);
/* 37 */     LoginParameter loginParameter = (LoginParameter)ParameterConstant.getParameter(0);
/* 38 */     BagComponent bagComponent = (BagComponent)playerSession.getPlayer().createIfNotExist(BagComponent.class);
/* 39 */     int maxCount = VipUtil.getNum(playerComponent.getVip(), 1) + loginParameter.getInitCombatTimes();
/* 40 */     if (extendComponent.getCombatTimes() >= maxCount) {
/* 41 */       return 10033;
/*    */     }
/* 43 */     boolean isEngouh = bagComponent.check(loginParameter.getCombatItem(), 1L);
/* 44 */     if (!isEngouh) {
/* 45 */       boolean isEngouh2 = FinanceUtil.checkCurrency(playerSession.getPlayer(), CurrencyType.CCY, loginParameter.getCost(extendComponent.getCombatCostTimes()));
/* 46 */       if (!isEngouh2) {
/* 47 */         return 10052;
/*    */       }
/*    */     } 
/*    */     
/* 51 */     ArrayList<Reward> rewards = new ArrayList<>();
/*    */     
/* 53 */     WelfareComponent welfareComponent = (WelfareComponent)playerSession.getPlayer().createIfNotExist(WelfareComponent.class);
/* 54 */     double monthCardRate = welfareComponent.getMonthCardRate();
/* 55 */     PlayerUtil.getOfflineRewards(playerComponent.getMainSceneId(), loginParameter.getHangTime(), rewards, monthCardRate);
/*    */     
/* 57 */     MilitaryUtil.getRewardResult(rewards, MilitaryInsType.Rabit.getType(), playerSession.getPlayer().getPlayerId(), 0);
/* 58 */     if (rewards.size() == 0) {
/* 59 */       return 10032;
/*    */     }
/* 61 */     if (isEngouh) {
/* 62 */       bagComponent.deleteItem(loginParameter.getCombatItem(), 1, ResourceEvent.RapidCombat, true);
/*    */     } else {
/* 64 */       FinanceUtil.decCurrency(playerSession.getPlayer(), CurrencyType.CCY, loginParameter.getCost(extendComponent.getCombatCostTimes()), ResourceEvent.RapidCombat);
/* 65 */       extendComponent.setCombatCostTimes(extendComponent.getCombatCostTimes() + 1);
/*    */     } 
/* 67 */     extendComponent.setCombatTimes(extendComponent.getCombatTimes() + 1);
/* 68 */     FinanceUtil.reward(rewards, playerSession.getPlayer(), ResourceEvent.RapidCombat, true);
/*    */     
/* 70 */     ((RapidCombatResponse)this.response).combatCostTimes = extendComponent.getCombatCostTimes();
/* 71 */     ((RapidCombatResponse)this.response).times = maxCount - extendComponent.getCombatTimes();
/* 72 */     TaskComponent taskComponent = (TaskComponent)playerSession.getPlayer().createIfNotExist(TaskComponent.class);
/* 73 */     if (taskComponent != null) {
/* 74 */       taskComponent.refreshSchedule(TaskType.RapitCombit, 0, 1L);
/*    */     }
/* 76 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\login\RapidCombatProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */