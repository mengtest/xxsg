/*    */ package com.linlongyx.sanguo.webgame.processors.team;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.app.bossHome.BossHomeComponent;
/*    */ import com.linlongyx.sanguo.webgame.app.eightGraphic.BaguaComponent;
/*    */ import com.linlongyx.sanguo.webgame.common.team.Team;
/*    */ import com.linlongyx.sanguo.webgame.common.team.TeamUtil;
/*    */ import com.linlongyx.sanguo.webgame.config.parameter.BaguaParameter;
/*    */ import com.linlongyx.sanguo.webgame.config.parameter.TeamParameter;
/*    */ import com.linlongyx.sanguo.webgame.constant.FunctionOpenConstant;
/*    */ import com.linlongyx.sanguo.webgame.constant.ParameterConstant;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.team.TeamInfoRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.team.TeamInfoResponse;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class TeamInfoProcessor
/*    */   extends ProcessorBase<TeamInfoRequest, TeamInfoResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 25 */     this.response = (ResponseBase)new TeamInfoResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, TeamInfoRequest request) {
/* 30 */     if (request.insType == 1) {
/* 31 */       if (!FunctionOpenConstant.isFunctionOpen(playerSession, 37)) {
/* 32 */         return 10061;
/*    */       }
/* 34 */     } else if (request.insType == 2 && 
/* 35 */       !FunctionOpenConstant.isFunctionOpen(playerSession, 41)) {
/* 36 */       return 10061;
/*    */     } 
/*    */     
/* 39 */     Team team = TeamUtil.getTeamById(request.teamId);
/* 40 */     if (team == null) {
/* 41 */       return 13701;
/*    */     }
/* 43 */     if (request.insType == 1) {
/* 44 */       TeamParameter teamParameter = (TeamParameter)ParameterConstant.getParameter(37);
/* 45 */       BossHomeComponent bossHomeComponent = (BossHomeComponent)playerSession.getPlayer().createIfNotExist(BossHomeComponent.class);
/* 46 */       ((TeamInfoResponse)this.response).restTimes = teamParameter.getMaxFightTimes() - bossHomeComponent.getTeamFightTimes();
/* 47 */     } else if (request.insType == 2) {
/* 48 */       BaguaParameter baguaParameter = (BaguaParameter)ParameterConstant.getParameter(41);
/* 49 */       BaguaComponent baguaComponent = (BaguaComponent)playerSession.getPlayer().createIfNotExist(BaguaComponent.class);
/*    */       
/* 51 */       ((TeamInfoResponse)this.response).restTimes = baguaParameter.getAsistTime() - baguaComponent.getAsistTime();
/*    */     } 
/* 53 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\team\TeamInfoProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */