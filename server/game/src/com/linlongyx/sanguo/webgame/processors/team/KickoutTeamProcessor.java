/*    */ package com.linlongyx.sanguo.webgame.processors.team;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.common.team.TeamUtil;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.team.KickoutTeamRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.team.KickoutTeamResponse;
/*    */ 
/*    */ 
/*    */ public class KickoutTeamProcessor
/*    */   extends ProcessorBase<KickoutTeamRequest, KickoutTeamResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 16 */     this.response = (ResponseBase)new KickoutTeamResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, KickoutTeamRequest request) {
/* 21 */     return TeamUtil.kickoutTeam(playerSession.getPlayer().getPlayerId(), request.teamId, request.playerId);
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\team\KickoutTeamProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */