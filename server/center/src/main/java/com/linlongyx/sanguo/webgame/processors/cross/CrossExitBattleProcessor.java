/*    */ package com.linlongyx.sanguo.webgame.processors.cross;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.cross.CrossExitBattleRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.cross.CrossExitBattleResponse;
/*    */ import com.linlongyx.sanguo.webgame.rmi.battle.Battle;
/*    */ import com.linlongyx.sanguo.webgame.rmi.battle.BattlePlayer;
/*    */ import com.linlongyx.sanguo.webgame.rmi.battle.BattleUtil;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CrossExitBattleProcessor
/*    */   extends ProcessorBase<CrossExitBattleRequest, CrossExitBattleResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 23 */     this.response = (ResponseBase)new CrossExitBattleResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, CrossExitBattleRequest request) {
/* 28 */     long playerId = playerSession.getPlayer().getPlayerId();
/* 29 */     Battle battle = BattleUtil.getCurBattle(playerId);
/* 30 */     if (battle == null) {
/* 31 */       return 0;
/*    */     }
/* 33 */     BattlePlayer battlePlayer = (BattlePlayer)battle.getBattlePlayers().get(Long.valueOf(playerId));
/* 34 */     if (battlePlayer == null) {
/* 35 */       return 0;
/*    */     }
/* 37 */     battlePlayer.exitBattle(battle);
/*    */ 
/*    */     
/* 40 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\sanguo\webgame\processors\cross\CrossExitBattleProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */