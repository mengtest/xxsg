/*    */ package com.linlongyx.sanguo.webgame.processors.destiny;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.common.util.FinanceUtil;
/*    */ import com.linlongyx.sanguo.webgame.constant.CurrencyType;
/*    */ import com.linlongyx.sanguo.webgame.constant.ResourceEvent;
/*    */ import com.linlongyx.sanguo.webgame.processors.cross.CrossUtil;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.destiny.DestinyBetRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.destiny.DestinyBetResponse;
/*    */ import com.linlongyx.sanguo.webgame.rmi.data.MatchState;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class DestinyBetProcessor
/*    */   extends ProcessorBase<DestinyBetRequest, DestinyBetResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 21 */     this.response = (ResponseBase)new DestinyBetResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, DestinyBetRequest request) {
/* 26 */     MatchState curMatchState = CrossUtil.getCurrentMatchState("DestinyBetMatchInfoProcessor::handleRequest");
/* 27 */     if (!DestinyUtil.canBet(curMatchState)) {
/* 28 */       return 17413;
/*    */     }
/* 30 */     if (request.num <= 0) {
/* 31 */       return 17417;
/*    */     }
/*    */     
/* 34 */     if (!FinanceUtil.checkCurrency(playerSession.getPlayer(), CurrencyType.GuessCoin, request.num)) {
/* 35 */       return 10096;
/*    */     }
/* 37 */     int num = CrossUtil.addPlayerBetNum("DestinyBetProcessor::addPlayerBetNum", request.pkId, playerSession.getPlayer().getPlayerId(), request.playerId, request.num, 1);
/* 38 */     if (num < 0) {
/* 39 */       return 17416;
/*    */     }
/* 41 */     FinanceUtil.decCurrency(playerSession.getPlayer(), CurrencyType.GuessCoin, request.num, ResourceEvent.DestinyMatchGuess);
/* 42 */     ((DestinyBetResponse)this.response).playerId = request.playerId;
/* 43 */     ((DestinyBetResponse)this.response).num = num;
/* 44 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\destiny\DestinyBetProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */