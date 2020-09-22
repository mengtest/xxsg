/*    */ package com.linlongyx.sanguo.webgame.processors.team;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.common.team.TeamUtil;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.team.LeaveTeamRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.team.LeaveTeamResponse;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class LeaveTeamProcessor
/*    */   extends ProcessorBase<LeaveTeamRequest, LeaveTeamResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 17 */     this.response = (ResponseBase)new LeaveTeamResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, LeaveTeamRequest request) {
/* 22 */     short code = TeamUtil.leaveTeam(playerSession.getPlayer().getPlayerId(), request.teamId);
/* 23 */     return (code == 0 || code == 13701) ? 0 : code;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\team\LeaveTeamProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */