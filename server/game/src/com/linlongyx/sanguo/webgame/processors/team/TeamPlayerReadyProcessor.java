/*    */ package com.linlongyx.sanguo.webgame.processors.team;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.common.team.Team;
/*    */ import com.linlongyx.sanguo.webgame.common.team.TeamPlayer;
/*    */ import com.linlongyx.sanguo.webgame.common.team.TeamUtil;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.team.TeamPlayerReadyRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.team.TeamPlayerReadyResponse;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class TeamPlayerReadyProcessor
/*    */   extends ProcessorBase<TeamPlayerReadyRequest, TeamPlayerReadyResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 21 */     this.response = (ResponseBase)new TeamPlayerReadyResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, TeamPlayerReadyRequest request) {
/* 26 */     Team team = TeamUtil.getTeamById(request.teamId);
/* 27 */     if (team == null) {
/* 28 */       return 13701;
/*    */     }
/* 30 */     TeamPlayer teamPlayer = team.getTeamPlayerById(playerSession.getPlayer().getPlayerId());
/* 31 */     if (teamPlayer == null) {
/* 32 */       return 13706;
/*    */     }
/* 34 */     teamPlayer.setReady((request.isReady == 1));
/*    */ 
/*    */     
/* 37 */     TeamUtil.sendTeamPlayerReadyNotice(team, playerSession.getPlayer().getPlayerId(), request.isReady);
/* 38 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\team\TeamPlayerReadyProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */