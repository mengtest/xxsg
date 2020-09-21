/*    */ package com.linlongyx.sanguo.webgame.processors.destiny;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.app.destiny.DestinyComponent;
/*    */ import com.linlongyx.sanguo.webgame.app.player.PlayerComponent;
/*    */ import com.linlongyx.sanguo.webgame.common.util.FinanceUtil;
/*    */ import com.linlongyx.sanguo.webgame.config.parameter.DestinyParameter;
/*    */ import com.linlongyx.sanguo.webgame.constant.CurrencyType;
/*    */ import com.linlongyx.sanguo.webgame.constant.FunctionOpenConstant;
/*    */ import com.linlongyx.sanguo.webgame.constant.ParameterConstant;
/*    */ import com.linlongyx.sanguo.webgame.constant.ResourceEvent;
/*    */ import com.linlongyx.sanguo.webgame.processors.login.VipUtil;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.destiny.DestinyBattleRefreshRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.destiny.DestinyBattleRefreshResponse;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class DestinyBattleRefreshProcessor
/*    */   extends ProcessorBase<DestinyBattleRefreshRequest, DestinyBattleRefreshResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 25 */     this.response = (ResponseBase)new DestinyBattleRefreshResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, DestinyBattleRefreshRequest request) {
/* 30 */     if (!FunctionOpenConstant.isFunctionOpen(playerSession, 74))
/* 31 */       return 10061; 
/* 32 */     PlayerComponent playerComponent = (PlayerComponent)playerSession.getPlayer().createIfNotExist(PlayerComponent.class);
/* 33 */     DestinyComponent destinyComponent = (DestinyComponent)playerSession.getPlayer().createIfNotExist(DestinyComponent.class);
/* 34 */     int maxRefreshTimes = VipUtil.getNum(playerComponent.getVip(), 18);
/* 35 */     if (maxRefreshTimes == 0) {
/* 36 */       return 10088;
/*    */     }
/* 38 */     if (destinyComponent.getRefreshTimes() >= maxRefreshTimes) {
/* 39 */       return 17401;
/*    */     }
/*    */     
/* 42 */     int refreshTimes = destinyComponent.getRefreshTimes();
/* 43 */     DestinyParameter destinyParameter = (DestinyParameter)ParameterConstant.getParameter(74);
/* 44 */     int gold = ((Integer)destinyParameter.getRefreshPriceMap().get(Integer.valueOf(refreshTimes + 1))).intValue();
/* 45 */     if (!FinanceUtil.checkCurrency(playerSession.getPlayer(), CurrencyType.CCY, gold)) {
/* 46 */       return 10052;
/*    */     }
/* 48 */     FinanceUtil.decCurrency(playerSession.getPlayer(), CurrencyType.CCY, gold, ResourceEvent.DestinyRefresh);
/* 49 */     destinyComponent.setRefreshTimes(refreshTimes + 1);
/* 50 */     destinyComponent.addRobTimes(destinyParameter.getInitRobTimes() - destinyComponent.getRobTimes());
/*    */     
/* 52 */     destinyComponent.refreshRecomment(destinyParameter.getPlayersSize(), true);
/* 53 */     ((DestinyBattleRefreshResponse)this.response).refreshTimes = destinyComponent.getRefreshTimes();
/* 54 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\destiny\DestinyBattleRefreshProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */