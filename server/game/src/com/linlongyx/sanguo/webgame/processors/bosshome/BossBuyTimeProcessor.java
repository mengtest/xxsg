/*    */ package com.linlongyx.sanguo.webgame.processors.bosshome;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.core.utils.LogUtils;
/*    */ import com.linlongyx.sanguo.webgame.app.bossHome.BossHomeComponent;
/*    */ import com.linlongyx.sanguo.webgame.app.player.PlayerComponent;
/*    */ import com.linlongyx.sanguo.webgame.app.task.TaskComponent;
/*    */ import com.linlongyx.sanguo.webgame.common.util.FinanceUtil;
/*    */ import com.linlongyx.sanguo.webgame.config.parameter.BossHomeParameter;
/*    */ import com.linlongyx.sanguo.webgame.constant.CurrencyType;
/*    */ import com.linlongyx.sanguo.webgame.constant.ParameterConstant;
/*    */ import com.linlongyx.sanguo.webgame.constant.ResourceEvent;
/*    */ import com.linlongyx.sanguo.webgame.constant.TaskType;
/*    */ import com.linlongyx.sanguo.webgame.processors.draw.DrawCardUtil;
/*    */ import com.linlongyx.sanguo.webgame.processors.login.VipUtil;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.bosshome.BossBuyTimeRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.bosshome.BossBuyTimeResponse;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.Reward;
/*    */ import com.linlongyx.sanguo.webgame.util.CostUtil;
/*    */ import java.util.Map;
/*    */ 
/*    */ public class BossBuyTimeProcessor
/*    */   extends ProcessorBase<BossBuyTimeRequest, BossBuyTimeResponse> {
/*    */   protected void initResponse() {
/* 28 */     this.response = (ResponseBase)new BossBuyTimeResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, BossBuyTimeRequest request) {
/* 33 */     BossHomeComponent bossHomeComponent = (BossHomeComponent)playerSession.getPlayer().createIfNotExist(BossHomeComponent.class);
/* 34 */     BossHomeParameter bossHomeParameter = (BossHomeParameter)ParameterConstant.getParameter(3);
/* 35 */     if (request.type == 0) {
/* 36 */       int type = 1;
/* 37 */       Map<Integer, Integer> buys = bossHomeComponent.getBuys();
/* 38 */       int buyCount = ((Integer)buys.getOrDefault(Integer.valueOf(type), Integer.valueOf(0))).intValue();
/* 39 */       PlayerComponent playerComponent = (PlayerComponent)playerSession.getPlayer().createIfNotExist(PlayerComponent.class);
/* 40 */       int maxBuyCount = VipUtil.getNum(playerComponent.getVip(), 11);
/* 41 */       if (buyCount >= maxBuyCount) {
/* 42 */         return 10306;
/*    */       }
/*    */       
/* 45 */       Map<Integer, Integer> counts = bossHomeComponent.getCounts();
/* 46 */       Map<Integer, Integer> times = bossHomeComponent.getTimes();
/* 47 */       Map<Integer, Integer> restores = bossHomeComponent.getRestores();
/* 48 */       int fightCount = ((Integer)counts.getOrDefault(Integer.valueOf(type), Integer.valueOf(0))).intValue();
/* 49 */       int restore = ((Integer)restores.getOrDefault(Integer.valueOf(type), Integer.valueOf(0))).intValue();
/* 50 */       int maxCount = bossHomeParameter.getRankMaxCount();
/* 51 */       int leftCount = maxCount + restore + buyCount - fightCount;
/* 52 */       if (leftCount >= maxCount) {
/* 53 */         return 10306;
/*    */       }
/*    */       
/* 56 */       Reward cost = bossHomeParameter.getRankBuyCost(buyCount + 1);
/* 57 */       short code = CostUtil.handleCostReward(cost, playerSession, ResourceEvent.WorldBossTime);
/* 58 */       if (0 != code) {
/* 59 */         return code;
/*    */       }
/* 61 */       buyCount++;
/* 62 */       buys.put(Integer.valueOf(type), Integer.valueOf(buyCount));
/* 63 */       bossHomeComponent.setBuys(buys);
/* 64 */       leftCount = maxCount + restore + buyCount - fightCount;
/* 65 */       if (leftCount >= maxCount) {
/* 66 */         times.put(Integer.valueOf(type), Integer.valueOf(0));
/* 67 */         bossHomeComponent.setTimes(times);
/*    */       } 
/*    */       
/* 70 */       ((BossBuyTimeResponse)this.response).buyTime = buyCount;
/* 71 */       TaskComponent taskComponent = (TaskComponent)playerSession.getPlayer().createIfNotExist(TaskComponent.class);
/* 72 */       if (null != taskComponent) {
/* 73 */         taskComponent.refreshSchedule(TaskType.RankBossBuy, 0, buyCount);
/*    */       }
/* 75 */       LogUtils.errorLog(new Object[] { "BossBuyTime", Long.valueOf(playerSession.getPlayer().getPlayerId()), Integer.valueOf(leftCount), Integer.valueOf(restore), Integer.valueOf(buyCount), Integer.valueOf(fightCount) });
/* 76 */     } else if (request.type == 1) {
/* 77 */       PlayerComponent playerComponent = (PlayerComponent)playerSession.getPlayer().getComponent(PlayerComponent.class);
/* 78 */       int freeCount = VipUtil.getNum(playerComponent.getVip(), 23);
/* 79 */       if (freeCount <= bossHomeComponent.getBuyAlienCount()) {
/* 80 */         return 10306;
/*    */       }
/*    */       
/* 83 */       if (bossHomeComponent.getAlienCount() <= 0 || bossHomeComponent.getBuyAlienCount() + bossHomeParameter.getAlienRewardCount() - bossHomeComponent.getAlienCount() >= bossHomeParameter.getAlienRewardCount()) {
/* 84 */         return 12704;
/*    */       }
/*    */       
/* 87 */       int cost = DrawCardUtil.getRefreshCost(bossHomeComponent.getBuyAlienCount() + 1).getBoss();
/* 88 */       if (!FinanceUtil.checkCurrency(playerSession.getPlayer(), CurrencyType.CCY, cost)) {
/* 89 */         return 10052;
/*    */       }
/* 91 */       FinanceUtil.decCurrency(playerSession.getPlayer(), CurrencyType.CCY, cost, ResourceEvent.DrawCardCost, true);
/* 92 */       bossHomeComponent.setBuyAlienCount(bossHomeComponent.getBuyAlienCount() + 1);
/* 93 */       ((BossBuyTimeResponse)this.response).buyTime = bossHomeComponent.getBuyAlienCount();
/* 94 */       LogUtils.errorLog(new Object[] { "BossBuyTime", Long.valueOf(playerSession.getPlayer().getPlayerId()), Integer.valueOf(request.type), Integer.valueOf(bossHomeComponent.getAlienCount()), Integer.valueOf(bossHomeComponent.getBuyAlienCount()) });
/*    */     } 
/* 96 */     ((BossBuyTimeResponse)this.response).type = request.type;
/* 97 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\bosshome\BossBuyTimeProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */