/*    */ package com.linlongyx.sanguo.webgame.processors.cross;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.common.structure.Pair;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.cross.CrossBattleRankRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.cross.CrossBattleRankResponse;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.CrossRankData;
/*    */ import com.linlongyx.sanguo.webgame.rmi.battle.Battle;
/*    */ import com.linlongyx.sanguo.webgame.rmi.battle.BattlePlayer;
/*    */ import com.linlongyx.sanguo.webgame.rmi.battle.BattleUtil;
/*    */ import java.util.ArrayList;
/*    */ import java.util.Collection;
/*    */ import java.util.List;
/*    */ import java.util.stream.Collectors;
/*    */ import java.util.stream.Stream;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CrossBattleRankProcessor
/*    */   extends ProcessorBase<CrossBattleRankRequest, CrossBattleRankResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 26 */     this.response = (ResponseBase)new CrossBattleRankResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, CrossBattleRankRequest request) {
/* 31 */     long playerId = playerSession.getPlayer().getPlayerId();
/* 32 */     if (BattleUtil.getBattleMap().getBattleState() != BattleUtil.BattleState.OPEN) {
/* 33 */       return 0;
/*    */     }
/* 35 */     Battle battle = BattleUtil.getCurBattle(playerId);
/* 36 */     if (battle == null) {
/* 37 */       return 0;
/*    */     }
/* 39 */     BattlePlayer battlePlayer = (BattlePlayer)battle.getBattlePlayers().get(Long.valueOf(playerId));
/* 40 */     if (battlePlayer == null || battlePlayer.getPoint() == 0) {
/* 41 */       ((CrossBattleRankResponse)this.response).myRank = 0;
/*    */     }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 50 */     List<BattlePlayer> playerList = (List<BattlePlayer>)Stream.<Collection>of(new Collection[] { battle.getBattlePlayers().values(), battle.getLeavePlayers().values() }).flatMap(Collection::stream).filter(p -> (p.getPoint() > 0)).sorted((o1, o2) -> (o1.getPoint() == o2.getPoint()) ? Integer.compare(o1.getLastCollectTime(), o2.getLastCollectTime()) : Integer.compare(o2.getPoint(), o1.getPoint())).collect(Collectors.toList());
/* 51 */     if (request.type == 0) {
/* 52 */       List<Pair<Integer, Integer>> campRankList = new ArrayList<>();
/* 53 */       int[] campRank = new int[3];
/* 54 */       int rank = 0;
/* 55 */       for (BattlePlayer p : playerList) {
/* 56 */         rank++;
/* 57 */         if (p.getPlayerId() == playerId) {
/* 58 */           ((CrossBattleRankResponse)this.response).myRank = rank;
/*    */         }
/* 60 */         campRank[p.getCampIndex()] = campRank[p.getCampIndex()] + p.getPoint();
/*    */       } 
/* 62 */       for (int i = 0; i < campRank.length; i++) {
/* 63 */         campRankList.add(new Pair(Integer.valueOf(i), Integer.valueOf(campRank[i])));
/*    */       }
/* 65 */       campRankList.sort((o1, o2) -> ((Integer)o2.getValue()).intValue() - ((Integer)o1.getValue()).intValue());
/* 66 */       rank = 0;
/* 67 */       for (Pair<Integer, Integer> kv : campRankList) {
/* 68 */         rank++;
/* 69 */         CrossRankData crossRankData = new CrossRankData();
/*    */         
/* 71 */         crossRankData.id = (((Integer)kv.getKey()).intValue() + 1);
/* 72 */         crossRankData.name = Battle.CampTag.getCampName(((Integer)kv.getKey()).intValue() + 1);
/* 73 */         crossRankData.rank = rank;
/* 74 */         crossRankData.point = ((Integer)kv.getValue()).intValue();
/* 75 */         ((CrossBattleRankResponse)this.response).rankList.add(crossRankData);
/*    */       }
/*    */     
/* 78 */     } else if (request.type == 1) {
/* 79 */       int rankSize = 3;
/* 80 */       for (int i = 0; i < playerList.size(); i++) {
/* 81 */         if (i < rankSize) {
/* 82 */           CrossRankData crossRankData = new CrossRankData();
/* 83 */           crossRankData.id = battle.getBattleCamps()[((BattlePlayer)playerList.get(i)).getCampIndex()].getTag().getTag();
/* 84 */           crossRankData.name = ((BattlePlayer)playerList.get(i)).getPlayerData().getPlayerName();
/* 85 */           crossRankData.rank = i + 1;
/* 86 */           crossRankData.point = ((BattlePlayer)playerList.get(i)).getPoint();
/* 87 */           ((CrossBattleRankResponse)this.response).rankList.add(crossRankData);
/*    */         } 
/* 89 */         if (((BattlePlayer)playerList.get(i)).getPlayerId() == playerId) {
/* 90 */           ((CrossBattleRankResponse)this.response).myRank = i + 1;
/* 91 */           if (i >= rankSize)
/*    */             break; 
/*    */         } 
/*    */       } 
/*    */     } 
/* 96 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\sanguo\webgame\processors\cross\CrossBattleRankProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */