/*    */ package com.linlongyx.sanguo.webgame.processors.crossTeam;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.crossTeam.CrossLeaveTeamRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.crossTeam.CrossLeaveTeamResponse;
/*    */ import com.linlongyx.sanguo.webgame.rmi.battle.Battle;
/*    */ import com.linlongyx.sanguo.webgame.rmi.battle.BattleUtil;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CrossLeaveTeamProcessor
/*    */   extends ProcessorBase<CrossLeaveTeamRequest, CrossLeaveTeamResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 20 */     this.response = (ResponseBase)new CrossLeaveTeamResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, CrossLeaveTeamRequest request) {
/* 25 */     long playerId = playerSession.getPlayer().getPlayerId();
/* 26 */     Battle battle = BattleUtil.getCurBattle(playerId);
/* 27 */     if (battle == null) {
/* 28 */       return 11314;
/*    */     }
/* 30 */     short code = battle.leaveTeam(playerId, request.teamId);
/* 31 */     return code;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\sanguo\webgame\processors\crossTeam\CrossLeaveTeamProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */