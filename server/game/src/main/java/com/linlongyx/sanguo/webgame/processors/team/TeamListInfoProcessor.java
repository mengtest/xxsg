/*    */ package com.linlongyx.sanguo.webgame.processors.team;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.app.bossHome.BossHomeComponent;
/*    */ import com.linlongyx.sanguo.webgame.app.eightGraphic.BaguaComponent;
/*    */ import com.linlongyx.sanguo.webgame.app.player.PlayerComponent;
/*    */ import com.linlongyx.sanguo.webgame.common.team.Team;
/*    */ import com.linlongyx.sanguo.webgame.common.team.TeamPlayer;
/*    */ import com.linlongyx.sanguo.webgame.common.team.TeamUtil;
/*    */ import com.linlongyx.sanguo.webgame.config.bean.MultiInsBean;
/*    */ import com.linlongyx.sanguo.webgame.config.parameter.BaguaParameter;
/*    */ import com.linlongyx.sanguo.webgame.config.parameter.TeamParameter;
/*    */ import com.linlongyx.sanguo.webgame.constant.FunctionOpenConstant;
/*    */ import com.linlongyx.sanguo.webgame.constant.ParameterConstant;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.TeamData;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.team.TeamListInfoRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.team.TeamListInfoResponse;
/*    */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*    */ import java.util.Collections;
/*    */ import java.util.List;
/*    */ import java.util.stream.Collectors;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class TeamListInfoProcessor
/*    */   extends ProcessorBase<TeamListInfoRequest, TeamListInfoResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 34 */     this.response = (ResponseBase)new TeamListInfoResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, TeamListInfoRequest request) {
/* 39 */     if (request.insType == 1) {
/* 40 */       if (!FunctionOpenConstant.isFunctionOpen(playerSession, 37)) {
/* 41 */         return 10061;
/*    */       }
/* 43 */       PlayerComponent playerComponent = (PlayerComponent)playerSession.getPlayer().createIfNotExist(PlayerComponent.class);
/* 44 */       MultiInsBean multiInsBean = (MultiInsBean)JsonTableService.getJsonData(request.insId, MultiInsBean.class);
/* 45 */       if (multiInsBean == null || multiInsBean.getLevel() > playerComponent.getLevel()) {
/* 46 */         return 10084;
/*    */       }
/*    */       
/* 49 */       TeamParameter teamParameter = (TeamParameter)ParameterConstant.getParameter(37);
/* 50 */       BossHomeComponent bossHomeComponent = (BossHomeComponent)playerSession.getPlayer().createIfNotExist(BossHomeComponent.class);
/* 51 */       ((TeamListInfoResponse)this.response).restTimes = teamParameter.getMaxFightTimes() - bossHomeComponent.getTeamFightTimes();
/* 52 */     } else if (request.insType == 2) {
/* 53 */       if (!FunctionOpenConstant.isFunctionOpen(playerSession, 41)) {
/* 54 */         return 10061;
/*    */       }
/* 56 */       BaguaParameter baguaParameter = (BaguaParameter)ParameterConstant.getParameter(41);
/* 57 */       BaguaComponent baguaComponent = (BaguaComponent)playerSession.getPlayer().createIfNotExist(BaguaComponent.class);
/*    */       
/* 59 */       ((TeamListInfoResponse)this.response).restTimes = baguaParameter.getAsistTime() - baguaComponent.getAsistTime();
/*    */     } 
/*    */     
/* 62 */     List<Team> teamList = TeamUtil.getTeamListByInsId(request.insId);
/* 63 */     if (teamList.size() > 20) {
/* 64 */       Collections.shuffle(teamList);
/*    */     }
/* 66 */     teamList = (List<Team>)teamList.stream().filter(team -> (team.getStatus() != Team.TeamStatus.IN_FIGHT.getValue())).limit(20L).collect(Collectors.toList());
/* 67 */     for (Team team : teamList) {
/* 68 */       if (team.getStatus() != Team.TeamStatus.IN_FIGHT.getValue()) {
/* 69 */         TeamPlayer leader = team.getLeader();
/* 70 */         TeamData teamData = new TeamData();
/* 71 */         teamData.teamId = team.getTeamId();
/* 72 */         if (leader != null) {
/* 73 */           teamData.head = leader.getHead();
/* 74 */           teamData.leaderName = leader.getName();
/*    */         } 
/* 76 */         teamData.size = team.getPlayerSize();
/* 77 */         ((TeamListInfoResponse)this.response).teamList.add(teamData);
/*    */       } 
/*    */     } 
/*    */     
/* 81 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\team\TeamListInfoProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */