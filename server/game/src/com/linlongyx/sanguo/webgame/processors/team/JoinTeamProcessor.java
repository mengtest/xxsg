/*    */ package com.linlongyx.sanguo.webgame.processors.team;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.app.player.PlayerComponent;
/*    */ import com.linlongyx.sanguo.webgame.common.team.Team;
/*    */ import com.linlongyx.sanguo.webgame.common.team.TeamUtil;
/*    */ import com.linlongyx.sanguo.webgame.config.bean.MultiInsBean;
/*    */ import com.linlongyx.sanguo.webgame.constant.FunctionOpenConstant;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.team.JoinTeamRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.team.JoinTeamResponse;
/*    */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class JoinTeamProcessor
/*    */   extends ProcessorBase<JoinTeamRequest, JoinTeamResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 23 */     this.response = (ResponseBase)new JoinTeamResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, JoinTeamRequest request) {
/* 28 */     if (request.type == 1) {
/* 29 */       if (!FunctionOpenConstant.isFunctionOpen(playerSession, 37)) {
/* 30 */         return 10061;
/*    */       }
/* 32 */       PlayerComponent playerComponent = (PlayerComponent)playerSession.getPlayer().createIfNotExist(PlayerComponent.class);
/* 33 */       MultiInsBean multiInsBean = (MultiInsBean)JsonTableService.getJsonData(request.insId, MultiInsBean.class);
/* 34 */       if (multiInsBean == null || multiInsBean.getLevel() > playerComponent.getLevel()) {
/* 35 */         return 10084;
/*    */       }
/* 37 */     } else if (request.type == 2 && 
/* 38 */       !FunctionOpenConstant.isFunctionOpen(playerSession, 41)) {
/* 39 */       return 10061;
/*    */     } 
/*    */     
/* 42 */     long teamId = request.teamId;
/* 43 */     if (request.teamId == -1L) {
/* 44 */       Team team = TeamUtil.quickJoinTeam(playerSession.getPlayer().getPlayerId(), request.insId, request.type);
/* 45 */       if (team != null) {
/* 46 */         teamId = team.getTeamId();
/*    */       } else {
/*    */         
/* 49 */         return 13701;
/*    */       } 
/* 51 */     } else if (request.teamId == -2L) {
/* 52 */       Team team = TeamUtil.createTeam(playerSession.getPlayer().getPlayerId(), request.insId, request.type);
/* 53 */       if (team == null) {
/* 54 */         return 13711;
/*    */       }
/* 56 */       teamId = team.getTeamId();
/*    */     } else {
/*    */       
/* 59 */       short code = TeamUtil.joinTeam(playerSession.getPlayer().getPlayerId(), request.insId, request.teamId);
/* 60 */       if (code != 0) {
/* 61 */         return code;
/*    */       }
/*    */     } 
/* 64 */     ((JoinTeamResponse)this.response).teamId = teamId;
/* 65 */     ((JoinTeamResponse)this.response).insId = request.insId;
/* 66 */     ((JoinTeamResponse)this.response).type = request.type;
/* 67 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\team\JoinTeamProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */