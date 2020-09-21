/*    */ package com.linlongyx.sanguo.webgame.processors.bosshome;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.core.utils.LogUtils;
/*    */ import com.linlongyx.sanguo.webgame.app.bossHome.BossHomeComponent;
/*    */ import com.linlongyx.sanguo.webgame.config.parameter.BossHomeParameter;
/*    */ import com.linlongyx.sanguo.webgame.constant.ParameterConstant;
/*    */ import com.linlongyx.sanguo.webgame.constant.ResourceEvent;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.bosshome.WorldBuyTimeRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.bosshome.WorldBuyTimeResponse;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.Reward;
/*    */ import com.linlongyx.sanguo.webgame.util.CostUtil;
/*    */ import java.util.Map;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class WorldBuyTimeProcessor
/*    */   extends ProcessorBase<WorldBuyTimeRequest, WorldBuyTimeResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 25 */     this.response = (ResponseBase)new WorldBuyTimeResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, WorldBuyTimeRequest request) {
/* 30 */     int type = 2;
/* 31 */     BossHomeComponent bossHomeComponent = (BossHomeComponent)playerSession.getPlayer().createIfNotExist(BossHomeComponent.class);
/* 32 */     BossHomeParameter bossHomeParameter = (BossHomeParameter)ParameterConstant.getParameter(3);
/* 33 */     Map<Integer, Integer> buys = bossHomeComponent.getBuys();
/* 34 */     int buyCount = ((Integer)buys.getOrDefault(Integer.valueOf(type), Integer.valueOf(0))).intValue();
/* 35 */     if (buyCount >= bossHomeParameter.getWorldMaxBuyTime()) {
/* 36 */       return 10306;
/*    */     }
/* 38 */     Reward cost = bossHomeParameter.getWorldBuyCost(buyCount + 1);
/* 39 */     short code = CostUtil.handleCostReward(cost, playerSession, ResourceEvent.WorldBossTime);
/* 40 */     if (0 != code) {
/* 41 */       return code;
/*    */     }
/* 43 */     buyCount++;
/* 44 */     buys.put(Integer.valueOf(type), Integer.valueOf(buyCount));
/* 45 */     bossHomeComponent.setBuys(buys);
/*    */     
/* 47 */     ((WorldBuyTimeResponse)this.response).buyTime = buyCount;
/*    */     
/* 49 */     ((WorldBuyTimeResponse)this.response).fightTimes = BossUtil.getWorldBossLeftCount(bossHomeComponent);
/* 50 */     LogUtils.errorLog(new Object[] { "WorldBuyTime", Long.valueOf(playerSession.getPlayer().getPlayerId()), Integer.valueOf(buyCount), Integer.valueOf(((WorldBuyTimeResponse)this.response).fightTimes) });
/* 51 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\bosshome\WorldBuyTimeProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */