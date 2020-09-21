/*    */ package com.linlongyx.sanguo.webgame.processors.crossTeam;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.common.team.Team;
/*    */ import com.linlongyx.sanguo.webgame.common.team.TeamUtil;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.crossTeam.CrossJoinTeamRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.crossTeam.CrossJoinTeamResponse;
/*    */ import com.linlongyx.sanguo.webgame.rmi.battle.Battle;
/*    */ import com.linlongyx.sanguo.webgame.rmi.battle.BattlePlayer;
/*    */ import com.linlongyx.sanguo.webgame.rmi.battle.BattleUtil;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CrossJoinTeamProcessor
/*    */   extends ProcessorBase<CrossJoinTeamRequest, CrossJoinTeamResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 21 */     this.response = (ResponseBase)new CrossJoinTeamResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, CrossJoinTeamRequest request) {
/* 26 */     long playerId = playerSession.getPlayer().getPlayerId();
/* 27 */     Battle battle = BattleUtil.getCurBattle(playerId);
/* 28 */     if (battle == null) {
/* 29 */       return 11314;
/*    */     }
/* 31 */     BattlePlayer battlePlayer = (BattlePlayer)battle.getBattlePlayers().get(Long.valueOf(playerId));
/* 32 */     if (battlePlayer == null || !BattleUtil.checkPos(battlePlayer.getPos(), battle.getBattleCamps()[battlePlayer.getCampIndex()].getBornPoint())) {
/* 33 */       return 13704;
/*    */     }
/* 35 */     Team team = TeamUtil.getTeamByPlayerId(playerId);
/* 36 */     if (team != null) {
/* 37 */       return 13705;
/*    */     }
/* 39 */     short code = battle.joinTeam(playerId, request.teamId);
/* 40 */     return code;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\sanguo\webgame\processors\crossTeam\CrossJoinTeamProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */