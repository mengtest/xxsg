/*    */ package com.linlongyx.sanguo.webgame.processors.cross;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.common.team.Team;
/*    */ import com.linlongyx.sanguo.webgame.common.team.TeamPlayer;
/*    */ import com.linlongyx.sanguo.webgame.common.team.TeamUtil;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.cross.CrossBattleCollectStateRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.cross.CrossBattleCollectStateResponse;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.CrossBattlePlayerData;
/*    */ import com.linlongyx.sanguo.webgame.rmi.battle.Battle;
/*    */ import com.linlongyx.sanguo.webgame.rmi.battle.BattlePlayer;
/*    */ import com.linlongyx.sanguo.webgame.rmi.battle.BattleResource;
/*    */ import com.linlongyx.sanguo.webgame.rmi.battle.BattleUtil;
/*    */ import com.linlongyx.sanguo.webgame.rmi.data.PlayerData;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CrossBattleCollectStateProcessor
/*    */   extends ProcessorBase<CrossBattleCollectStateRequest, CrossBattleCollectStateResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 30 */     this.response = (ResponseBase)new CrossBattleCollectStateResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, CrossBattleCollectStateRequest request) {
/* 35 */     long playerId = playerSession.getPlayer().getPlayerId();
/* 36 */     int resourceId = request.resourceId;
/* 37 */     Battle battle = BattleUtil.getCurBattle(playerId);
/* 38 */     if (battle == null) {
/* 39 */       return 11302;
/*    */     }
/* 41 */     BattlePlayer battlePlayer = (BattlePlayer)battle.getBattlePlayers().get(Long.valueOf(playerId));
/* 42 */     BattleResource battleResource = (BattleResource)battle.getResourceMap().get(Integer.valueOf(resourceId));
/* 43 */     Battle.CampTag playerCampTag = battle.getBattleCamps()[battlePlayer.getCampIndex()].getTag();
/* 44 */     if (battleResource.getType() != BattleResource.ResourceType.RESOURCE) {
/* 45 */       return 11308;
/*    */     }
/* 47 */     int size = 1;
/* 48 */     Team team = null;
/* 49 */     if (battlePlayer.getPlayerTeamStatus() != BattlePlayer.PlayerTeamStatus.NOTEAM) {
/* 50 */       team = TeamUtil.getTeamByPlayerId(battlePlayer.getPlayerId());
/* 51 */       if (team != null)
/*    */       {
/* 53 */         size = team.getRealPlayerSize();
/*    */       }
/*    */     } 
/*    */ 
/*    */     
/* 58 */     ((CrossBattleCollectStateResponse)this.response).resourceId = request.resourceId;
/* 59 */     if (battleResource.getCurState() == BattleResource.ResourceState.FREE) {
/* 60 */       ((CrossBattleCollectStateResponse)this.response).state = 0;
/* 61 */       return 0;
/* 62 */     }  if (battleResource.getCurState() == BattleResource.ResourceState.SEIZED) {
/* 63 */       if (battleResource.getOwnCamp() == playerCampTag) {
/* 64 */         if (battleResource.getCount() + size >= battleResource.getLimit()) {
/* 65 */           ((CrossBattleCollectStateResponse)this.response).state = 3;
/* 66 */           return 0;
/*    */         } 
/* 68 */         ((CrossBattleCollectStateResponse)this.response).state = 1;
/* 69 */         return 0;
/*    */       } 
/*    */       
/* 72 */       ((CrossBattleCollectStateResponse)this.response).state = 2;
/* 73 */       long defensePlayerId = battleResource.getPlayerId();
/* 74 */       Team targetTeam = TeamUtil.getTeamByPlayerId(defensePlayerId);
/* 75 */       if (targetTeam == null) {
/* 76 */         PlayerData playerData = battle.getPlayerData(defensePlayerId);
/* 77 */         if (playerData != null) {
/* 78 */           CrossBattlePlayerData crossBattlePlayerData = BattleUtil.transformToCrossBattlePlayerData(battlePlayer, playerData, team);
/* 79 */           ((CrossBattleCollectStateResponse)this.response).defensePlayer.add(crossBattlePlayerData);
/*    */         } 
/*    */       } else {
/* 82 */         for (TeamPlayer teamPlayer : targetTeam.getTeamPlayers()) {
/* 83 */           if (teamPlayer != null && !teamPlayer.isRobot()) {
/* 84 */             PlayerData playerData = battle.getPlayerData(teamPlayer.getPlayerId());
/* 85 */             if (playerData != null) {
/* 86 */               CrossBattlePlayerData crossBattlePlayerData = BattleUtil.transformToCrossBattlePlayerData((BattlePlayer)battle.getBattlePlayers().get(Long.valueOf(teamPlayer.getPlayerId())), playerData, null);
/* 87 */               ((CrossBattleCollectStateResponse)this.response).defensePlayer.add(crossBattlePlayerData);
/*    */             } 
/*    */           } 
/*    */         } 
/*    */       } 
/* 92 */       return 0;
/*    */     } 
/*    */     
/* 95 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\sanguo\webgame\processors\cross\CrossBattleCollectStateProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */