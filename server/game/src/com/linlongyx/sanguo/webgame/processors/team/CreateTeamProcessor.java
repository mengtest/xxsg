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
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.team.CreateTeamRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.team.CreateTeamResponse;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Deprecated
/*    */ public class CreateTeamProcessor
/*    */   extends ProcessorBase<CreateTeamRequest, CreateTeamResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 25 */     this.response = (ResponseBase)new CreateTeamResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, CreateTeamRequest request) {
/* 30 */     Team team = TeamUtil.createTeam(playerSession.getPlayer().getPlayerId(), request.insId, 1);
/* 31 */     if (team == null) {
/* 32 */       return 13711;
/*    */     }
/* 34 */     TeamParameter teamParameter = (TeamParameter)ParameterConstant.getParameter(37);
/* 35 */     BossHomeComponent bossHomeComponent = (BossHomeComponent)playerSession.getPlayer().createIfNotExist(BossHomeComponent.class);
/* 36 */     if (TimeUtil.currentTime() - bossHomeComponent.getLastCreateTeamTime() > teamParameter.getIntervalSeconds()) {
/* 37 */       bossHomeComponent.setLastCreateTeamTime(TimeUtil.currentTime());
/*    */     }
/*    */     
/* 40 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\team\CreateTeamProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */