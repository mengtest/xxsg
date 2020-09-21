/*    */ package com.linlongyx.sanguo.webgame.app.gm;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.sanguo.webgame.constant.MContext;
/*    */ import com.linlongyx.sanguo.webgame.processors.team.ConvenientInviteProcessor;
/*    */ import com.linlongyx.sanguo.webgame.processors.team.KickoutTeamProcessor;
/*    */ import com.linlongyx.sanguo.webgame.processors.team.TeamInfoProcessor;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.team.ConvenientInviteRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.team.JoinTeamRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.team.KickoutTeamRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.team.LeaveTeamRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.team.TeamInfoRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.team.TeamListInfoRequest;
/*    */ 
/*    */ public class TeamGm implements IGm {
/* 16 */   private long teamId = Long.parseLong(MContext.getServerId()) * 1000000L;
/*    */   
/*    */   public void gm(IPlayerSession playerSession, String[] strings) {
/* 19 */     if (strings[2].equals("create")) {
/* 20 */       JoinTeamRequest request = new JoinTeamRequest();
/* 21 */       request.insId = 100000;
/* 22 */       request.teamId = -2L;
/* 23 */       (new JoinTeamProcessor()).handle(playerSession, (RequestBase)request);
/* 24 */     } else if (strings[2].equals("teaminfo")) {
/* 25 */       TeamInfoRequest request = new TeamInfoRequest();
/* 26 */       request.teamId = this.teamId;
/* 27 */       (new TeamInfoProcessor()).handle(playerSession, (RequestBase)request);
/* 28 */     } else if (strings[2].equals("list")) {
/* 29 */       TeamListInfoRequest request = new TeamListInfoRequest();
/* 30 */       request.insId = 100000;
/* 31 */       (new TeamListInfoProcessor()).handle(playerSession, (RequestBase)request);
/* 32 */     } else if (strings[2].equals("leave")) {
/* 33 */       LeaveTeamRequest request = new LeaveTeamRequest();
/* 34 */       request.teamId = this.teamId;
/* 35 */       (new LeaveTeamProcessor()).handle(playerSession, (RequestBase)request);
/* 36 */     } else if (strings[2].equals("kickout")) {
/* 37 */       KickoutTeamRequest request = new KickoutTeamRequest();
/* 38 */       request.teamId = this.teamId;
/* 39 */       request.playerId = Long.parseLong(strings[3]);
/* 40 */       (new KickoutTeamProcessor()).handle(playerSession, (RequestBase)request);
/* 41 */     } else if (strings[2].equals("match")) {
/* 42 */       ConvenientInviteRequest request = new ConvenientInviteRequest();
/* 43 */       request.teamId = this.teamId;
/* 44 */       request.type = 1;
/* 45 */       (new ConvenientInviteProcessor()).handle(playerSession, (RequestBase)request);
/* 46 */     } else if (strings[2].equals("remove")) {
/* 47 */       long playerId = Long.parseLong(strings[3]);
/* 48 */       TeamUtil.removePlayerFromTeam(playerId);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\app\gm\TeamGm.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */