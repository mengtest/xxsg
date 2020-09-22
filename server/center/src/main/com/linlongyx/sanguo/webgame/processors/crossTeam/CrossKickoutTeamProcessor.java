/*    */ package com.linlongyx.sanguo.webgame.processors.crossTeam;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.common.team.Team;
/*    */ import com.linlongyx.sanguo.webgame.common.team.TeamUtil;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.crossTeam.CrossKickoutTeamRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.crossTeam.CrossKickoutTeamResponse;
/*    */ import com.linlongyx.sanguo.webgame.rmi.battle.Battle;
/*    */ import com.linlongyx.sanguo.webgame.rmi.battle.BattleUtil;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CrossKickoutTeamProcessor
/*    */   extends ProcessorBase<CrossKickoutTeamRequest, CrossKickoutTeamResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 20 */     this.response = (ResponseBase)new CrossKickoutTeamResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, CrossKickoutTeamRequest request) {
/* 25 */     long playerId = playerSession.getPlayer().getPlayerId();
/* 26 */     Battle battle = BattleUtil.getCurBattle(playerId);
/* 27 */     if (battle == null) {
/* 28 */       return 11314;
/*    */     }
/* 30 */     Team team = TeamUtil.getTeamById(request.teamId);
/* 31 */     if (team == null) {
/* 32 */       return 13701;
/*    */     }
/* 34 */     if (team.isLeader(playerId)) {
/* 35 */       return 13708;
/*    */     }
/*    */     
/* 38 */     short code = battle.leaveTeam(request.playerId, request.teamId);
/* 39 */     return code;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\sanguo\webgame\processors\crossTeam\CrossKickoutTeamProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */