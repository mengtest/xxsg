/*    */ package com.linlongyx.sanguo.webgame.processors.team;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.common.team.Team;
/*    */ import com.linlongyx.sanguo.webgame.common.team.TeamPlayer;
/*    */ import com.linlongyx.sanguo.webgame.common.team.TeamUtil;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.team.TeamPlayerFighterInfoRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.team.TeamPlayerFighterInfoResponse;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class TeamPlayerFighterInfoProcessor
/*    */   extends ProcessorBase<TeamPlayerFighterInfoRequest, TeamPlayerFighterInfoResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 19 */     this.response = (ResponseBase)new TeamPlayerFighterInfoResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, TeamPlayerFighterInfoRequest request) {
/* 24 */     Team team = TeamUtil.getTeamById(request.teamId);
/* 25 */     if (team == null) {
/* 26 */       return 13701;
/*    */     }
/* 28 */     TeamPlayer teamPlayer = team.getTeamPlayerById(playerSession.getPlayer().getPlayerId());
/* 29 */     if (teamPlayer == null) {
/* 30 */       return 13706;
/*    */     }
/* 32 */     ((TeamPlayerFighterInfoResponse)this.response).fighters.addAll(TeamUtil.createTeamFighterDataList(teamPlayer));
/* 33 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\team\TeamPlayerFighterInfoProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */