/*     */ package com.linlongyx.sanguo.webgame.processors.cross;
/*     */ 
/*     */ import com.linlongyx.core.framework.base.ProcessorBase;
/*     */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*     */ import com.linlongyx.core.framework.protocol.RequestBase;
/*     */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*     */ import com.linlongyx.core.utils.TimeUtil;
/*     */ import com.linlongyx.sanguo.webgame.common.team.Team;
/*     */ import com.linlongyx.sanguo.webgame.common.team.TeamPlayer;
/*     */ import com.linlongyx.sanguo.webgame.common.team.TeamUtil;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.cross.CrossBattleCollectRequest;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.cross.CrossBattleCollectResponse;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.cross.CrossBattleResourceNoticeResponse;
/*     */ import com.linlongyx.sanguo.webgame.rmi.battle.Battle;
/*     */ import com.linlongyx.sanguo.webgame.rmi.battle.BattlePlayer;
/*     */ import com.linlongyx.sanguo.webgame.rmi.battle.BattleResource;
/*     */ import com.linlongyx.sanguo.webgame.rmi.battle.BattleUtil;
/*     */ import com.linlongyx.sanguo.webgame.service.LookUpService;
/*     */ import java.util.HashSet;
/*     */ import java.util.Iterator;
/*     */ import java.util.Set;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CrossBattleCollectProcessor
/*     */   extends ProcessorBase<CrossBattleCollectRequest, CrossBattleCollectResponse>
/*     */ {
/*     */   protected void initResponse() {
/*  35 */     this.response = (ResponseBase)new CrossBattleCollectResponse();
/*     */   }
/*     */ 
/*     */   
/*     */   protected short handleRequest(IPlayerSession playerSession, CrossBattleCollectRequest request) {
/*  40 */     long playerId = playerSession.getPlayer().getPlayerId();
/*  41 */     int resourceId = request.resourceId;
/*  42 */     Battle battle = BattleUtil.getCurBattle(playerId);
/*  43 */     if (battle == null) {
/*  44 */       return 11302;
/*     */     }
/*  46 */     BattlePlayer battlePlayer = (BattlePlayer)battle.getBattlePlayers().get(Long.valueOf(playerId));
/*  47 */     BattleResource battleResource = (BattleResource)battle.getResourceMap().get(Integer.valueOf(resourceId));
/*  48 */     Battle.CampTag playerCampTag = battle.getBattleCamps()[battlePlayer.getCampIndex()].getTag();
/*  49 */     if (battleResource.getType() != BattleResource.ResourceType.RESOURCE) {
/*  50 */       return 11308;
/*     */     }
/*  52 */     int size = 1;
/*  53 */     Set<Long> playerSet = new HashSet<>();
/*  54 */     Team team = null;
/*  55 */     if (battlePlayer.getPlayerTeamStatus() != BattlePlayer.PlayerTeamStatus.NOTEAM) {
/*  56 */       team = TeamUtil.getTeamByPlayerId(battlePlayer.getPlayerId());
/*  57 */       if (team != null) {
/*  58 */         if (playerId != team.getLeaderId())
/*  59 */           return 13708; 
/*  60 */         size = team.getRealPlayerSize();
/*  61 */         for (TeamPlayer teamPlayer : team.getTeamPlayers()) {
/*  62 */           if (teamPlayer != null && !teamPlayer.isRobot()) {
/*  63 */             playerSet.add(Long.valueOf(teamPlayer.getPlayerId()));
/*     */           }
/*     */         } 
/*     */       } else {
/*     */         
/*  68 */         playerSet.add(Long.valueOf(playerId));
/*     */       } 
/*     */     } else {
/*  71 */       playerSet.add(Long.valueOf(playerId));
/*     */     } 
/*  73 */     ((CrossBattleCollectResponse)this.response).resourceId = request.resourceId;
/*     */     try {
/*  75 */       battleResource.lock();
/*  76 */       if (battleResource.getCurState() == BattleResource.ResourceState.FREE) {
/*  77 */         battleResource.setCurState(BattleResource.ResourceState.SEIZED);
/*  78 */         battleResource.setPlayerId(battlePlayer.getPlayerId());
/*  79 */         battleResource.setOwnCamp(playerCampTag);
/*  80 */         battleResource.updateBattlePlayer(battle, team, battlePlayer, TimeUtil.currentTime());
/*  81 */         for (Iterator<Long> iterator = playerSet.iterator(); iterator.hasNext(); ) { long id = ((Long)iterator.next()).longValue();
/*  82 */           if (battle.getBattlePlayers().containsKey(Long.valueOf(id))) {
/*  83 */             battleResource.addPlayer((BattlePlayer)battle.getBattlePlayers().get(Long.valueOf(id)));
/*     */           } }
/*     */ 
/*     */         
/*  87 */         ((CrossBattleCollectResponse)this.response).time = battlePlayer.getLastCollectTime() + battleResource.getCollectTime();
/*     */ 
/*     */         
/*  90 */         playerSet.stream().filter(id -> (id.longValue() != playerId)).forEach(id -> {
/*     */               if (LookUpService.isOnline(id.longValue())) {
/*     */                 LookUpService.getByPlayerId(id.longValue()).getSession().sendMessage(this.response);
/*     */               }
/*     */             });
/*     */ 
/*     */         
/*  97 */         CrossBattleResourceNoticeResponse resp = new CrossBattleResourceNoticeResponse();
/*  98 */         resp.resourceId = battleResource.getResourceId();
/*  99 */         resp.state = 1;
/* 100 */         resp.playerId = playerId;
/* 101 */         BattleUtil.broadcastResourceNotice(battle, resp);
/*     */ 
/*     */         
/* 104 */         BattleUtil.broadcastResourceOwnNotice(battle, battleResource.getResourceId(), battleResource.getOwnCamp().getTag(), battlePlayer.getPlayerData().getPlayerName());
/*     */         
/* 106 */         return 0;
/* 107 */       }  if (battleResource.getCurState() == BattleResource.ResourceState.SEIZED) {
/* 108 */         if (battleResource.getOwnCamp() == playerCampTag) {
/* 109 */           if (battleResource.getCount() + size >= battleResource.getLimit())
/* 110 */             return 11309; 
/* 111 */           if (battleResource.getCount() == 0) {
/* 112 */             battleResource.setPlayerId(battlePlayer.getPlayerId());
/* 113 */             battleResource.updateBattlePlayer(battle, team, battlePlayer, TimeUtil.currentTime());
/* 114 */             for (Iterator<Long> iterator = playerSet.iterator(); iterator.hasNext(); ) { long id = ((Long)iterator.next()).longValue();
/* 115 */               if (battle.getBattlePlayers().containsKey(Long.valueOf(id))) {
/* 116 */                 battleResource.addPlayer((BattlePlayer)battle.getBattlePlayers().get(Long.valueOf(id)));
/*     */               } }
/*     */ 
/*     */             
/* 120 */             ((CrossBattleCollectResponse)this.response).time = battlePlayer.getLastCollectTime() + battleResource.getCollectTime();
/*     */ 
/*     */             
/* 123 */             CrossBattleResourceNoticeResponse resp = new CrossBattleResourceNoticeResponse();
/* 124 */             resp.resourceId = battleResource.getResourceId();
/* 125 */             resp.state = 1;
/* 126 */             resp.playerId = playerId;
/* 127 */             BattleUtil.broadcastResourceNotice(battle, resp);
/*     */           } else {
/* 129 */             battleResource.updateBattlePlayer(battle, team, battlePlayer, TimeUtil.currentTime());
/* 130 */             for (Iterator<Long> iterator = playerSet.iterator(); iterator.hasNext(); ) { long id = ((Long)iterator.next()).longValue();
/* 131 */               if (battle.getBattlePlayers().containsKey(Long.valueOf(id))) {
/* 132 */                 battleResource.addPlayer((BattlePlayer)battle.getBattlePlayers().get(Long.valueOf(id)));
/*     */               } }
/*     */ 
/*     */             
/* 136 */             ((CrossBattleCollectResponse)this.response).time = battlePlayer.getLastCollectTime() + battleResource.getCollectTime();
/*     */             
/* 138 */             playerSet.stream().filter(id -> (id.longValue() != playerId)).forEach(id -> {
/*     */                   if (LookUpService.isOnline(id.longValue())) {
/*     */                     LookUpService.getByPlayerId(id.longValue()).getSession().sendMessage(this.response);
/*     */                   }
/*     */                 });
/* 143 */             return 0;
/*     */           } 
/*     */         } else {
/* 146 */           return 11311;
/*     */         } 
/*     */       }
/*     */     } finally {
/*     */       
/* 151 */       battleResource.unlock();
/*     */     } 
/*     */ 
/*     */     
/* 155 */     return 0;
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\sanguo\webgame\processors\cross\CrossBattleCollectProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */