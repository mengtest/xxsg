/*    */ package com.linlongyx.sanguo.webgame.processors.rank;
/*    */ 
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.RankingData;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class RankingUtil
/*    */ {
/*    */   public static RankingData transform(long playerId, String playerName, long fightValue, int level, String head, byte vip, byte sex, int wearTitle, int wearFashion, int quality, long value, int rank) {
/* 11 */     RankingData rankingData = new RankingData();
/* 12 */     rankingData.playerId = playerId;
/* 13 */     rankingData.playerName = playerName;
/* 14 */     rankingData.fightValue = fightValue;
/* 15 */     rankingData.level = level;
/* 16 */     rankingData.head = head;
/* 17 */     rankingData.vip = vip;
/* 18 */     rankingData.sex = sex;
/* 19 */     rankingData.value = value;
/* 20 */     rankingData.rank = rank;
/* 21 */     rankingData.titleId = wearTitle;
/* 22 */     rankingData.fashionId = wearFashion;
/* 23 */     rankingData.quality = quality;
/* 24 */     rankingData.blocName = "";
/* 25 */     return rankingData;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\rank\RankingUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */