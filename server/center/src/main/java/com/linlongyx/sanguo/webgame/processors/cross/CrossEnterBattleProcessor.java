/*    */ package com.linlongyx.sanguo.webgame.processors.cross;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.cross.CrossEnterBattleRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.cross.CrossEnterBattleResponse;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.cross.CrossPlayerDataNoticeResponse;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.CrossBattleTeamData;
/*    */ import com.linlongyx.sanguo.webgame.rmi.battle.Battle;
/*    */ import com.linlongyx.sanguo.webgame.rmi.battle.BattleUtil;
/*    */ import com.linlongyx.sanguo.webgame.rmi.data.PlayerData;
/*    */ import com.linlongyx.sanguo.webgame.service.LookUpService;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CrossEnterBattleProcessor
/*    */   extends ProcessorBase<CrossEnterBattleRequest, CrossEnterBattleResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 25 */     this.response = (ResponseBase)new CrossEnterBattleResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, CrossEnterBattleRequest request) {
/* 30 */     if (BattleUtil.getBattleMap() == null || BattleUtil.getBattleMap().getBattleState() != BattleUtil.BattleState.OPEN) {
/* 31 */       return 11302;
/*    */     }
/*    */ 
/*    */     
/* 35 */     long playerId = playerSession.getPlayer().getPlayerId();
/* 36 */     PlayerData playerData = LookUpService.getPlayerData(playerId);
/* 37 */     if (playerData == null) {
/* 38 */       return 10019;
/*    */     }
/* 40 */     Battle battle = BattleUtil.getCurBattle(playerId);
/*    */     
/* 42 */     CrossBattleTeamData data = null;
/* 43 */     if (battle == null) {
/* 44 */       int serverId = playerData.getServerId();
/* 45 */       boolean success = BattleUtil.joinBattle(serverId, playerId);
/* 46 */       if (!success) {
/* 47 */         return 11305;
/*    */       }
/* 49 */       battle = BattleUtil.getCurBattle(playerId);
/* 50 */       if (battle == null) {
/* 51 */         return 11305;
/*    */       }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */       
/* 59 */       CrossPlayerDataNoticeResponse resp = new CrossPlayerDataNoticeResponse();
/* 60 */       CrossBattleTeamData crossBattleTeamData = battle.getPlayerBattleTeamData(playerId);
/* 61 */       if (crossBattleTeamData != null) {
/* 62 */         resp.data = data = crossBattleTeamData;
/* 63 */         battle.getBattlePlayers().keySet().stream().filter(id -> (id.longValue() != playerId)).forEach(id -> {
/*    */               if (LookUpService.isOnline(id.longValue())) {
/*    */                 LookUpService.getByPlayerId(id.longValue()).getSession().sendMessage((ResponseBase)resp);
/*    */               }
/*    */             });
/*    */       } 
/*    */     } else {
/* 70 */       data = battle.getPlayerBattleTeamData(playerId);
/*    */     } 
/* 72 */     ((CrossEnterBattleResponse)this.response).worldLevel = ((Integer)battle.getServerWorldLevelMap().getOrDefault(Integer.valueOf(playerData.getServerId()), Integer.valueOf(0))).intValue();
/* 73 */     if (data != null) {
/* 74 */       ((CrossEnterBattleResponse)this.response).self = data;
/*    */     }
/* 76 */     ((CrossEnterBattleResponse)this.response).teamList = battle.getCurBattleTeamDataList();
/* 77 */     ((CrossEnterBattleResponse)this.response).resources = battle.getCurBattleResourceList();
/* 78 */     ((CrossEnterBattleResponse)this.response).time = BattleUtil.getBattleMap().getOpentime() + 1800;
/* 79 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\sanguo\webgame\processors\cross\CrossEnterBattleProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */