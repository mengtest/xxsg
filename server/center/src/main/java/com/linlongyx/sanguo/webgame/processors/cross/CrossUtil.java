/*    */ package com.linlongyx.sanguo.webgame.processors.cross;
/*    */ 
/*    */ import com.fasterxml.jackson.databind.ObjectMapper;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.RankingData;
/*    */ import com.linlongyx.sanguo.webgame.rmi.data.PlayerData;
/*    */ import java.io.ByteArrayInputStream;
/*    */ import java.io.ByteArrayOutputStream;
/*    */ import java.io.IOException;
/*    */ import java.io.ObjectInputStream;
/*    */ import java.io.ObjectOutputStream;
/*    */ 
/*    */ public class CrossUtil {
/* 13 */   public static ObjectMapper objectMapper = new ObjectMapper();
/*    */ 
/*    */ 
/*    */   
/*    */   public static final int CROSS_BATTLE_REWARD_TYPE = 1;
/*    */ 
/*    */ 
/*    */   
/*    */   public static boolean isHefuPlayer(int serverId, long playerId) {
/* 22 */     int srcServerId = getServerIdByPlayerId(playerId);
/* 23 */     return (srcServerId == serverId);
/*    */   }
/*    */   
/*    */   public static int getServerIdByPlayerId(long playerId) {
/* 27 */     return (int)(playerId / 1000000L);
/*    */   }
/*    */   
/*    */   public static Object deepCopy(Object o) {
/*    */     try {
/* 32 */       ByteArrayOutputStream bo = new ByteArrayOutputStream();
/* 33 */       ObjectOutputStream oo = new ObjectOutputStream(bo);
/* 34 */       oo.writeObject(o);
/*    */       
/* 36 */       ByteArrayInputStream bi = new ByteArrayInputStream(bo.toByteArray());
/* 37 */       ObjectInputStream oi = new ObjectInputStream(bi);
/* 38 */       return oi.readObject();
/* 39 */     } catch (IOException|ClassNotFoundException e) {
/* 40 */       e.printStackTrace();
/*    */       
/* 42 */       return null;
/*    */     } 
/*    */   }
/*    */   public static RankingData transform(PlayerData playerData) {
/* 46 */     RankingData rankingData = new RankingData();
/* 47 */     transform(rankingData, playerData);
/* 48 */     return rankingData;
/*    */   }
/*    */   
/*    */   public static void transform(RankingData rankingData, PlayerData playerData) {
/* 52 */     rankingData.playerId = playerData.getPlayerId();
/* 53 */     rankingData.playerName = playerData.getPlayerName();
/* 54 */     rankingData.fightValue = playerData.getTotalValue();
/* 55 */     rankingData.level = playerData.getLevel();
/* 56 */     rankingData.head = playerData.getHead();
/* 57 */     rankingData.sex = playerData.getSex();
/* 58 */     rankingData.vip = playerData.getVip();
/* 59 */     rankingData.value = 0L;
/* 60 */     rankingData.titleId = playerData.getTitleId();
/* 61 */     rankingData.fashionId = (playerData.getModelData()).fashion;
/* 62 */     rankingData.quality = playerData.getQuality();
/* 63 */     rankingData.blocName = playerData.getBlocName();
/* 64 */     rankingData.mounts = (playerData.getModelData()).mounts;
/* 65 */     rankingData.rank = 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\sanguo\webgame\processors\cross\CrossUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */