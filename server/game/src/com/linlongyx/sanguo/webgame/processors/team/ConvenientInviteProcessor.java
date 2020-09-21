/*    */ package com.linlongyx.sanguo.webgame.processors.team;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.core.utils.TimeUtil;
/*    */ import com.linlongyx.sanguo.webgame.app.bossHome.BossHomeComponent;
/*    */ import com.linlongyx.sanguo.webgame.common.team.Team;
/*    */ import com.linlongyx.sanguo.webgame.common.team.TeamUtil;
/*    */ import com.linlongyx.sanguo.webgame.config.parameter.TeamParameter;
/*    */ import com.linlongyx.sanguo.webgame.constant.ParameterConstant;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.team.ConvenientInviteRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.team.ConvenientInviteResponse;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ConvenientInviteProcessor
/*    */   extends ProcessorBase<ConvenientInviteRequest, ConvenientInviteResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 28 */     this.response = (ResponseBase)new ConvenientInviteResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, ConvenientInviteRequest request) {
/* 33 */     Team team = TeamUtil.getTeamById(request.teamId);
/* 34 */     if (team == null) {
/* 35 */       return 13701;
/*    */     }
/* 37 */     if (!team.isLeader(playerSession.getPlayer().getPlayerId())) {
/* 38 */       return 13708;
/*    */     }
/* 40 */     if (request.type == 0) {
/* 41 */       int curTime = TimeUtil.currentTime();
/* 42 */       TeamParameter teamParameter = (TeamParameter)ParameterConstant.getParameter(37);
/* 43 */       BossHomeComponent bossHomeComponent = (BossHomeComponent)playerSession.getPlayer().createIfNotExist(BossHomeComponent.class);
/* 44 */       if (curTime - bossHomeComponent.getLastCreateTeamTime() > teamParameter.getIntervalSeconds()) {
/* 45 */         bossHomeComponent.setLastCreateTeamTime(curTime);
/* 46 */         TeamUtil.sendCreateTeamChatNotice(playerSession.getPlayer().getPlayerId(), TeamUtil.getInsIdByTeamId(team.getTeamId()), team.getTeamId(), request.insType);
/*    */       } 
/* 48 */     } else if (request.type == 1) {
/* 49 */       return TeamUtil.matchRobot(team);
/*    */     } 
/* 51 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\team\ConvenientInviteProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */