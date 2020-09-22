/*    */ package com.linlongyx.sanguo.webgame.processors.destiny;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.processors.cross.CrossUtil;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.destiny.DestinyMatchStateInfoRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.destiny.DestinyMatchStateInfoResponse;
/*    */ import com.linlongyx.sanguo.webgame.rmi.data.MatchState;
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class DestinyMatchStateInfoProcessor
/*    */   extends ProcessorBase<DestinyMatchStateInfoRequest, DestinyMatchStateInfoResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 20 */     this.response = (ResponseBase)new DestinyMatchStateInfoResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, DestinyMatchStateInfoRequest request) {
/* 25 */     MatchState curMatchState = CrossUtil.getCurrentMatchState("DestinyBetMatchInfoProcessor::handleRequest");
/* 26 */     if (curMatchState != null) {
/* 27 */       DestinyUtil.curMatchState = curMatchState;
/*    */     }
/* 29 */     boolean hasMatch = CrossUtil.hasMatch("DestinyMatchStateInfoProcessor::hasMatch", playerSession.getPlayer().getPlayerId());
/* 30 */     ArrayList<Long> players = CrossUtil.getCurBetPkPlayers("DestinyMatchStateInfoProcessor::getCurBetPkPlayers");
/* 31 */     ((DestinyMatchStateInfoResponse)this.response).state = DestinyUtil.curMatchState.getState();
/* 32 */     ((DestinyMatchStateInfoResponse)this.response).canBet = (byte)((DestinyUtil.canBet(DestinyUtil.curMatchState) && players != null && !players.contains(Long.valueOf(playerSession.getPlayer().getPlayerId()))) ? 1 : 0);
/* 33 */     ((DestinyMatchStateInfoResponse)this.response).hasMatch = (byte)(hasMatch ? 1 : 0);
/* 34 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\destiny\DestinyMatchStateInfoProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */