/*    */ package com.linlongyx.sanguo.webgame.processors.arena;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayer;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.core.utils.LogUtils;
/*    */ import com.linlongyx.sanguo.webgame.app.arena.ArenaComponent;
/*    */ import com.linlongyx.sanguo.webgame.app.player.PlayerComponent;
/*    */ import com.linlongyx.sanguo.webgame.config.parameter.ArenaParameter;
/*    */ import com.linlongyx.sanguo.webgame.constant.ParameterConstant;
/*    */ import com.linlongyx.sanguo.webgame.constant.ResourceEvent;
/*    */ import com.linlongyx.sanguo.webgame.processors.login.VipUtil;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.arena.ArenaBuyTimesRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.arena.ArenaBuyTimesResponse;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.Reward;
/*    */ import com.linlongyx.sanguo.webgame.util.CostUtil;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ArenaBuyTimesProcessor
/*    */   extends ProcessorBase<ArenaBuyTimesRequest, ArenaBuyTimesResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 26 */     this.response = (ResponseBase)new ArenaBuyTimesResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, ArenaBuyTimesRequest request) {
/* 31 */     IPlayer iPlayer = playerSession.getPlayer();
/* 32 */     PlayerComponent playerComponent = (PlayerComponent)iPlayer.createIfNotExist(PlayerComponent.class);
/* 33 */     ArenaComponent arenaComponent = (ArenaComponent)iPlayer.createIfNotExist(ArenaComponent.class);
/* 34 */     int times = 0;
/* 35 */     if (request.times <= 0) {
/* 36 */       return 10090;
/*    */     }
/* 38 */     ArenaParameter arenaParameter = (ArenaParameter)ParameterConstant.getParameter(17);
/*    */     
/* 40 */     int maxTimes = VipUtil.getNum(playerComponent.getVip(), 10);
/* 41 */     if (arenaComponent.getBuyTimes() + request.times > maxTimes) {
/* 42 */       return 11705;
/*    */     }
/* 44 */     int buyTimes = arenaComponent.getBuyTimes();
/* 45 */     Reward buyCost = new Reward();
/* 46 */     for (int i = 0; i < request.times; i++) {
/* 47 */       buyTimes++;
/* 48 */       Reward cost = arenaParameter.getBuyCost(buyTimes);
/* 49 */       if (null == buyCost) {
/* 50 */         return 11701;
/*    */       }
/* 52 */       buyCost.id = cost.id;
/* 53 */       buyCost.type = cost.type;
/* 54 */       buyCost.num += cost.num;
/*    */     } 
/* 56 */     short code = CostUtil.handleCostReward(buyCost, playerSession, ResourceEvent.ArenaBuyTime);
/* 57 */     if (0 != code) {
/* 58 */       return code;
/*    */     }
/* 60 */     arenaComponent.setBuyTimes(buyTimes);
/* 61 */     ((ArenaBuyTimesResponse)this.response).buyTimes = buyTimes;
/* 62 */     ((ArenaBuyTimesResponse)this.response).fightTimes = arenaParameter.getMaxChallengeTime() + buyTimes - arenaComponent.getFightTimes();
/* 63 */     LogUtils.errorLog(new Object[] { "ArenaBuyTime", Long.valueOf(iPlayer.getPlayerId()), Integer.valueOf(buyTimes), Integer.valueOf(arenaComponent.getFightTimes()), Integer.valueOf(((ArenaBuyTimesResponse)this.response).fightTimes) });
/* 64 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\arena\ArenaBuyTimesProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */