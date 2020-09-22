/*    */ package linlongyx.sanguo.webgame.processors.cross;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.common.team.Team;
/*    */ import com.linlongyx.sanguo.webgame.common.team.TeamUtil;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.cross.CrossBattleRebornNoticeRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.cross.CrossBattleRebornNoticeResponse;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.CrossBattleTeamData;
/*    */ import com.linlongyx.sanguo.webgame.rmi.battle.Battle;
/*    */ import com.linlongyx.sanguo.webgame.rmi.battle.BattleCamp;
/*    */ import com.linlongyx.sanguo.webgame.rmi.battle.BattlePlayer;
/*    */ import com.linlongyx.sanguo.webgame.rmi.battle.BattleUtil;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CrossBattleRebornNoticeProcessor
/*    */   extends ProcessorBase<CrossBattleRebornNoticeRequest, CrossBattleRebornNoticeResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 24 */     this.response = (ResponseBase)new CrossBattleRebornNoticeResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, CrossBattleRebornNoticeRequest request) {
/* 29 */     long playerId = playerSession.getPlayer().getPlayerId();
/* 30 */     Team team = TeamUtil.getTeamByPlayerId(playerId);
/* 31 */     Battle battle = BattleUtil.getCurBattle(playerId);
/*    */     
/* 33 */     if (battle == null) {
/* 34 */       return 11314;
/*    */     }
/* 36 */     BattlePlayer battlePlayer = (BattlePlayer)battle.getBattlePlayers().get(Long.valueOf(playerId));
/* 37 */     BattleCamp battleCamp = battle.getBattleCamps()[battlePlayer.getCampIndex()];
/* 38 */     battle.playerMove(playerId, battleCamp.getBornPoint()[0], battleCamp.getBornPoint()[1]);
/*    */ 
/*    */     
/* 41 */     CrossBattleRebornNoticeResponse resp = new CrossBattleRebornNoticeResponse();
/*    */     
/* 43 */     ((CrossBattleRebornNoticeResponse)this.response).type = 0;
/* 44 */     ((CrossBattleRebornNoticeResponse)this.response).leaderId = team.getLeaderId();
/* 45 */     ((CrossBattleRebornNoticeResponse)this.response).playerId = team.getLeaderId();
/* 46 */     ((CrossBattleRebornNoticeResponse)this.response).x = battleCamp.getBornPoint()[0];
/* 47 */     ((CrossBattleRebornNoticeResponse)this.response).y = battleCamp.getBornPoint()[1];
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 55 */     ((CrossBattleRebornNoticeResponse)this.response).type = 0;
/* 56 */     ((CrossBattleRebornNoticeResponse)this.response).leaderId = playerId;
/* 57 */     ((CrossBattleRebornNoticeResponse)this.response).playerId = playerId;
/* 58 */     ((CrossBattleRebornNoticeResponse)this.response).x = battleCamp.getBornPoint()[0];
/* 59 */     ((CrossBattleRebornNoticeResponse)this.response).y = battleCamp.getBornPoint()[1];
/*    */ 
/*    */     
/* 62 */     CrossBattleTeamData data = battle.getPlayerBattleTeamData(playerId);
/* 63 */     if (data != null) {
/* 64 */       resp.playerData = data;
/*    */     }
/* 66 */     BattleUtil.broadcastRebornNotice(battle, resp);
/* 67 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\sanguo\webgame\processors\cross\CrossBattleRebornNoticeProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */