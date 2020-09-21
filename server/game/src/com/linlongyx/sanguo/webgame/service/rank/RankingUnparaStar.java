/*    */ package com.linlongyx.sanguo.webgame.service.rank;
/*    */ 
/*    */ import com.linlongyx.sanguo.webgame.processors.rank.RankingUtil;
/*    */ import com.linlongyx.sanguo.webgame.processors.unparalleled.UnparalleledUtil;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.UnparalleledRankInfo;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class RankingUnparaStar
/*    */   extends AbstractRank
/*    */ {
/* 16 */   private static ArrayList<UnparalleledRankInfo> rankInfos = new ArrayList<>();
/*    */   
/*    */   public static ArrayList<UnparalleledRankInfo> getRankInfos() {
/* 19 */     return rankInfos;
/*    */   }
/*    */   
/*    */   public static void setRankInfos(ArrayList<UnparalleledRankInfo> rankInfos) {
/* 23 */     RankingUnparaStar.rankInfos = rankInfos;
/*    */   }
/*    */ 
/*    */   
/*    */   public void initRanksMySql() {
/* 28 */     this.ranks.clear();
/* 29 */     this.playerIdRankMap.clear();
/* 30 */     rankInfos.clear();
/* 31 */     String selectSql = "SELECT distinct p.playerId,p.playerName,u.lastMaxStar,u.lastPassTime,p.level,p.totalValue,p.head,p.sex,p.vip,p.wearTitle,p.wearFashion,p.quality FROM tb_player p, tb_unparalleled u where p.playerId = u.playerId and u.lastMaxStar > 0 ORDER BY u.lastMaxStar DESC,u.lastPassTime ASC limit " + this.rankSize;
/*    */ 
/*    */     
/* 34 */     List<Map<String, Object>> info = this.template.queryForList(selectSql);
/* 35 */     if (info.size() == 0) {
/*    */       return;
/*    */     }
/* 38 */     int rank = 1;
/* 39 */     for (Map<String, Object> map : info) {
/* 40 */       Long playerId = Long.valueOf(((Long)map.get("playerId")).longValue());
/* 41 */       String playerName = (String)map.get("playerName");
/* 42 */       int level = ((Integer)map.get("level")).intValue();
/* 43 */       long fightValue = ((Long)map.get("totalValue")).longValue();
/* 44 */       String head = (String)map.get("head");
/* 45 */       byte vip = Byte.parseByte(map.get("vip").toString());
/* 46 */       byte sex = Byte.parseByte(map.get("sex").toString());
/* 47 */       int quality = Integer.parseInt(map.get("quality").toString());
/* 48 */       int lastMaxStar = ((Integer)map.get("lastMaxStar")).intValue();
/* 49 */       int wearTitle = ((Integer)map.get("wearTitle")).intValue();
/* 50 */       int wearFashion = ((Integer)map.get("wearFashion")).intValue();
/* 51 */       this.playerIdRankMap.put(playerId, Integer.valueOf(rank));
/*    */       
/* 53 */       if (rank <= this.defaultSaveSize) {
/* 54 */         rankInfos.add(UnparalleledUtil.tramformRankInfo(playerId.longValue(), lastMaxStar, playerName, fightValue, head, rank));
/* 55 */         this.ranks.add(RankingUtil.transform(playerId.longValue(), playerName, fightValue, level, head, vip, sex, wearTitle, wearFashion, quality, lastMaxStar, rank++));
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\service\rank\RankingUnparaStar.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */