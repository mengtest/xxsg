/*    */ package com.linlongyx.sanguo.webgame.processors.crossTeam;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.common.team.Team;
/*    */ import com.linlongyx.sanguo.webgame.common.team.TeamPlayer;
/*    */ import com.linlongyx.sanguo.webgame.common.team.TeamUtil;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.crossTeam.CrossTeamListInfoRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.crossTeam.CrossTeamListInfoResponse;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.TeamData;
/*    */ import com.linlongyx.sanguo.webgame.rmi.battle.Battle;
/*    */ import com.linlongyx.sanguo.webgame.rmi.battle.BattlePlayer;
/*    */ import com.linlongyx.sanguo.webgame.rmi.battle.BattleUtil;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CrossTeamListInfoProcessor
/*    */   extends ProcessorBase<CrossTeamListInfoRequest, CrossTeamListInfoResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 24 */     this.response = (ResponseBase)new CrossTeamListInfoResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, CrossTeamListInfoRequest request) {
/* 29 */     long playerId = playerSession.getPlayer().getPlayerId();
/* 30 */     if (BattleUtil.getBattleMap().getBattleState() == BattleUtil.BattleState.CLOSE) {
/* 31 */       return 11302;
/*    */     }
/* 33 */     Battle battle = BattleUtil.getCurBattle(playerId);
/* 34 */     if (battle == null) {
/* 35 */       return 11302;
/*    */     }
/* 37 */     BattlePlayer battlePlayer = (BattlePlayer)battle.getBattlePlayers().get(Long.valueOf(playerId));
/* 38 */     if (battlePlayer == null) {
/* 39 */       return 11314;
/*    */     }
/* 41 */     battle.getBattlePlayers().values().stream().filter(p -> (p.getPlayerId() != playerId && p.getCampIndex() == battlePlayer.getCampIndex() && p.getPlayerTeamStatus() == BattlePlayer.PlayerTeamStatus.TEAMLEADER && p.getState() == BattlePlayer.PlayerState.BORN))
/* 42 */       .forEach(p -> {
/*    */           Team team = TeamUtil.getTeamByPlayerId(p.getPlayerId());
/*    */           if (team != null) {
/*    */             TeamPlayer leader = team.getLeader();
/*    */             TeamData teamData = new TeamData();
/*    */             teamData.teamId = team.getTeamId();
/*    */             if (leader != null) {
/*    */               teamData.head = leader.getHead();
/*    */               teamData.leaderName = leader.getName();
/*    */             } 
/*    */             teamData.size = team.getPlayerSize();
/*    */             ((CrossTeamListInfoResponse)this.response).teamList.add(teamData);
/*    */           } 
/*    */         });
/* 56 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\sanguo\webgame\processors\crossTeam\CrossTeamListInfoProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */