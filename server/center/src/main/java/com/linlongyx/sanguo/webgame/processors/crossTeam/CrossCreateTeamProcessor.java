/*    */ package com.linlongyx.sanguo.webgame.processors.crossTeam;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.common.team.Team;
/*    */ import com.linlongyx.sanguo.webgame.common.team.TeamUtil;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.crossTeam.CrossCreateTeamRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.crossTeam.CrossCreateTeamResponse;
/*    */ import com.linlongyx.sanguo.webgame.rmi.battle.Battle;
/*    */ import com.linlongyx.sanguo.webgame.rmi.battle.BattleUtil;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CrossCreateTeamProcessor
/*    */   extends ProcessorBase<CrossCreateTeamRequest, CrossCreateTeamResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 21 */     this.response = (ResponseBase)new CrossCreateTeamResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, CrossCreateTeamRequest request) {
/* 26 */     long playerId = playerSession.getPlayer().getPlayerId();
/* 27 */     Battle battle = BattleUtil.getCurBattle(playerId);
/* 28 */     if (battle == null) {
/* 29 */       return 11314;
/*    */     }
/* 31 */     Team team = TeamUtil.getTeamByPlayerId(playerId);
/* 32 */     if (team != null) {
/* 33 */       return 13705;
/*    */     }
/* 35 */     return battle.createTeam(playerId);
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\sanguo\webgame\processors\crossTeam\CrossCreateTeamProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */