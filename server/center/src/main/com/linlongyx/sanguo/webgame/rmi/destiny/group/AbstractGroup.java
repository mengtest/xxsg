/*    */ package com.linlongyx.sanguo.webgame.rmi.destiny.group;
/*    */ 
/*    */ import com.linlongyx.sanguo.webgame.log.LogUtil;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.DestinyRankData;
/*    */ import com.linlongyx.sanguo.webgame.rmi.LogicRmiUtil;
/*    */ import com.linlongyx.sanguo.webgame.rmi.data.DestinyPlayerData;
/*    */ import com.linlongyx.sanguo.webgame.rmi.destiny.match.PkData;
/*    */ import com.linlongyx.sanguo.webgame.rmi.destiny.match.Zone;
/*    */ import java.io.Serializable;
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class AbstractGroup
/*    */   implements Serializable
/*    */ {
/*    */   private long winner;
/* 24 */   protected Map<Long, DestinyRankData> groupPlayerMap = new HashMap<>();
/*    */   
/*    */   protected void updatePkFighters(Zone zone, PkData pkData) {
/* 27 */     DestinyPlayerData leftDestinyPlayerData = null, rightDestinyPlayerData = null;
/* 28 */     if (pkData.getLeftPlayer() != null) {
/* 29 */       leftDestinyPlayerData = LogicRmiUtil.getLocalDestinyPlayerData("QuarterMatchGroup::quarterPk::left", ((Integer)zone.getPlayerIdToServerIdMap().get(Long.valueOf(pkData.getLeftPlayer().getPlayerId()))).intValue(), pkData.getLeftPlayer().getPlayerId());
/* 30 */       if (leftDestinyPlayerData != null) {
/* 31 */         pkData.getLeftPlayer().setFighterData(leftDestinyPlayerData.getFighters());
/* 32 */         pkData.getLeftPlayer().setFightValue(leftDestinyPlayerData.getFightValue());
/* 33 */         pkData.getLeftPlayer().setFirstHandVal(leftDestinyPlayerData.getFirstHandVal());
/*    */       } else {
/*    */         
/* 36 */         pkData.getLeftPlayer().setFighterData(pkData.getLeftPlayer().getDefaultFighterData());
/* 37 */         LogUtil.errorLog(new Object[] { "get left remote destinyPlayerData error ->", Long.valueOf(pkData.getLeftPlayer().getPlayerId()) });
/*    */       } 
/*    */     } 
/* 40 */     if (pkData.getRightPlayer() != null) {
/* 41 */       rightDestinyPlayerData = LogicRmiUtil.getLocalDestinyPlayerData("QuarterMatchGroup::quarterPk::right", ((Integer)zone.getPlayerIdToServerIdMap().get(Long.valueOf(pkData.getRightPlayer().getPlayerId()))).intValue(), pkData.getRightPlayer().getPlayerId());
/* 42 */       if (rightDestinyPlayerData != null) {
/* 43 */         pkData.getRightPlayer().setFighterData(rightDestinyPlayerData.getFighters());
/* 44 */         pkData.getRightPlayer().setFightValue(rightDestinyPlayerData.getFightValue());
/* 45 */         pkData.getRightPlayer().setFirstHandVal(rightDestinyPlayerData.getFirstHandVal());
/*    */       } else {
/*    */         
/* 48 */         pkData.getRightPlayer().setFighterData(pkData.getRightPlayer().getDefaultFighterData());
/* 49 */         LogUtil.errorLog(new Object[] { "get right remote destinyPlayerData error ->", Long.valueOf(pkData.getRightPlayer().getPlayerId()) });
/*    */       } 
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public abstract PkData[] getAllPkData();
/*    */   
/*    */   public long getWinner() {
/* 58 */     return this.winner;
/*    */   }
/*    */   
/*    */   public void setWinner(long winner) {
/* 62 */     this.winner = winner;
/*    */   }
/*    */   
/*    */   public Map<Long, DestinyRankData> getGroupPlayerMap() {
/* 66 */     return this.groupPlayerMap;
/*    */   }
/*    */   
/*    */   public void setGroupPlayerMap(Map<Long, DestinyRankData> groupPlayerMap) {
/* 70 */     this.groupPlayerMap = groupPlayerMap;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\sanguo\webgame\rmi\destiny\group\AbstractGroup.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */