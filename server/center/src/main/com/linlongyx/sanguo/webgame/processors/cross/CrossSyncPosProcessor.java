/*    */ package com.linlongyx.sanguo.webgame.processors.cross;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.cross.CrossSyncPosRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.cross.CrossSyncPosResponse;
/*    */ import com.linlongyx.sanguo.webgame.rmi.battle.Battle;
/*    */ import com.linlongyx.sanguo.webgame.rmi.battle.BattlePlayer;
/*    */ import com.linlongyx.sanguo.webgame.rmi.battle.BattleUtil;
/*    */ import com.linlongyx.sanguo.webgame.service.LookUpService;
/*    */ import java.util.Set;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CrossSyncPosProcessor
/*    */   extends ProcessorBase<CrossSyncPosRequest, CrossSyncPosResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 23 */     this.response = (ResponseBase)new CrossSyncPosResponse();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, CrossSyncPosRequest request) {
/* 29 */     long playerId = playerSession.getPlayer().getPlayerId();
/*    */     
/* 31 */     if (request.playerId != playerId) {
/* 32 */       return 13708;
/*    */     }
/* 34 */     Battle battle = BattleUtil.getCurBattle(playerId);
/* 35 */     if (battle == null) {
/* 36 */       return 11306;
/*    */     }
/* 38 */     BattlePlayer battlePlayer = (BattlePlayer)battle.getBattlePlayers().get(Long.valueOf(playerId));
/* 39 */     if (battlePlayer == null) {
/* 40 */       return 11314;
/*    */     }
/* 42 */     ((CrossSyncPosResponse)this.response).x = request.x;
/* 43 */     ((CrossSyncPosResponse)this.response).y = request.y;
/* 44 */     ((CrossSyncPosResponse)this.response).playerId = playerId;
/* 45 */     if (BattleUtil.checkPos(battlePlayer.getPos(), request.x, request.y)) {
/* 46 */       return 0;
/*    */     }
/*    */     
/* 49 */     battle.playerMove(playerId, request.x, request.y);
/* 50 */     Set<Long> playerSet = battle.getBattlePlayers().keySet();
/* 51 */     CrossSyncPosResponse resp = new CrossSyncPosResponse();
/* 52 */     resp.x = request.x;
/* 53 */     resp.y = request.y;
/* 54 */     resp.playerId = request.playerId;
/* 55 */     for (Long id : playerSet) {
/* 56 */       if (id.longValue() != playerId && LookUpService.isOnline(id.longValue())) {
/* 57 */         LookUpService.getByPlayerId(id.longValue()).getSession().sendMessage((ResponseBase)resp);
/*    */       }
/*    */     } 
/* 60 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\sanguo\webgame\processors\cross\CrossSyncPosProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */