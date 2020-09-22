/*    */ package linlongyx.sanguo.webgame.processors.cross;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.core.utils.TimeUtil;
/*    */ import com.linlongyx.sanguo.webgame.common.team.Team;
/*    */ import com.linlongyx.sanguo.webgame.common.team.TeamPlayer;
/*    */ import com.linlongyx.sanguo.webgame.common.team.TeamUtil;
/*    */ import com.linlongyx.sanguo.webgame.common.util.FinanceUtil;
/*    */ import com.linlongyx.sanguo.webgame.config.bean.StarcraftBean;
/*    */ import com.linlongyx.sanguo.webgame.log.LogUtil;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.cross.CrossBattleCollectRewardRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.cross.CrossBattleCollectRewardResponse;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.Reward;
/*    */ import com.linlongyx.sanguo.webgame.rmi.battle.Battle;
/*    */ import com.linlongyx.sanguo.webgame.rmi.battle.BattlePlayer;
/*    */ import com.linlongyx.sanguo.webgame.rmi.battle.BattleResource;
/*    */ import com.linlongyx.sanguo.webgame.rmi.battle.BattleUtil;
/*    */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*    */ import com.linlongyx.sanguo.webgame.service.LookUpService;
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CrossBattleCollectRewardProcessor
/*    */   extends ProcessorBase<CrossBattleCollectRewardRequest, CrossBattleCollectRewardResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 32 */     this.response = (ResponseBase)new CrossBattleCollectRewardResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, CrossBattleCollectRewardRequest request) {
/* 37 */     long playerId = playerSession.getPlayer().getPlayerId();
/*    */     
/* 39 */     Battle battle = BattleUtil.getCurBattle(playerId);
/* 40 */     if (battle == null) {
/* 41 */       return 11302;
/*    */     }
/* 43 */     BattlePlayer battlePlayer = (BattlePlayer)battle.getBattlePlayers().get(Long.valueOf(playerId));
/* 44 */     int resourceId = battlePlayer.getResourceId();
/* 45 */     BattleResource battleResource = (BattleResource)battle.getResourceMap().get(Integer.valueOf(resourceId));
/* 46 */     Battle.CampTag playerCampTag = battle.getBattleCamps()[battlePlayer.getCampIndex()].getTag();
/* 47 */     if (battleResource == null || battleResource.getType() != BattleResource.ResourceType.RESOURCE) {
/* 48 */       return 11308;
/*    */     }
/* 50 */     if (battleResource.getOwnCamp() != playerCampTag) {
/* 51 */       return 11311;
/*    */     }
/* 53 */     if (battlePlayer.getState() != BattlePlayer.PlayerState.SEIZING) {
/* 54 */       return 11312;
/*    */     }
/* 56 */     int curTime = TimeUtil.currentTime();
/* 57 */     LogUtil.errorLog(new Object[] { Long.valueOf(playerId), Integer.valueOf(curTime), Integer.valueOf(battlePlayer.getLastCollectTime()), Integer.valueOf(battleResource.getCollectTime()) });
/* 58 */     if (curTime - battlePlayer.getLastCollectTime() < battleResource.getCollectTime()) {
/* 59 */       return 11313;
/*    */     }
/* 61 */     battlePlayer.setLastCollectTime(curTime);
/* 62 */     StarcraftBean starcraftBean = (StarcraftBean)JsonTableService.getJsonData(battleResource.getResourceId(), StarcraftBean.class);
/* 63 */     ArrayList<Reward> rewardList = FinanceUtil.transform(starcraftBean.getAcquisitionRevenue());
/* 64 */     Team team = null;
/* 65 */     if (battlePlayer.getPlayerTeamStatus() != BattlePlayer.PlayerTeamStatus.NOTEAM) {
/* 66 */       team = TeamUtil.getTeamByPlayerId(battlePlayer.getPlayerId());
/* 67 */       if (team != null) {
/* 68 */         if (!team.isLeader(playerId)) {
/* 69 */           return 13708;
/*    */         }
/* 71 */         for (TeamPlayer teamPlayer : team.getTeamPlayers()) {
/* 72 */           if (teamPlayer != null && !teamPlayer.isRobot() && LookUpService.isOnline(teamPlayer.getPlayerId())) {
/* 73 */             BattlePlayer p = (BattlePlayer)battle.getBattlePlayers().get(Long.valueOf(teamPlayer.getPlayerId()));
/* 74 */             if (p != null) {
/* 75 */               p.setPoint(p.getPoint() + starcraftBean.getPoints());
/*    */             }
/* 77 */             BattleUtil.sendBattleReward(teamPlayer.getPlayerId(), 1, rewardList);
/*    */           } 
/*    */         } 
/*    */       } else {
/* 81 */         battlePlayer.setPlayerTeamStatus(BattlePlayer.PlayerTeamStatus.NOTEAM);
/* 82 */         BattleUtil.sendBattleReward(playerId, 1, rewardList);
/* 83 */         battlePlayer.setPoint(battlePlayer.getPoint() + starcraftBean.getPoints());
/*    */       } 
/*    */     } else {
/* 86 */       BattleUtil.sendBattleReward(playerId, 1, rewardList);
/* 87 */       battlePlayer.setPoint(battlePlayer.getPoint() + starcraftBean.getPoints());
/*    */     } 
/* 89 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\sanguo\webgame\processors\cross\CrossBattleCollectRewardProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */