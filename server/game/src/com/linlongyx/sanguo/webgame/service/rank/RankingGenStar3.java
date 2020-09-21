/*    */ package com.linlongyx.sanguo.webgame.service.rank;
/*    */ 
/*    */ import com.linlongyx.sanguo.webgame.processors.rank.RankingUtil;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class RankingGenStar3
/*    */   extends AbstractRank
/*    */ {
/*    */   public void initRanksMySql() {
/* 14 */     this.ranks.clear();
/* 15 */     this.playerIdRankMap.clear();
/* 16 */     String selectSql = "SELECT distinct p.playerId,p.playerName,p.level,p.totalValue,p.head,p.sex,p.vip,p.wearTitle,p.wearFashion,p.quality, g.nightmareStar FROM tb_player p, tb_general g  WHERE p.playerId = g.playerId and g.nightmareStar > 0 ORDER BY g.nightmareStar DESC, p.totalValue DESC LIMIT " + this.rankSize;
/*    */     
/* 18 */     List<Map<String, Object>> info = this.template.queryForList(selectSql);
/* 19 */     if (info.size() == 0) {
/*    */       return;
/*    */     }
/* 22 */     int rank = 1;
/* 23 */     for (Map<String, Object> map : info) {
/* 24 */       Long playerId = Long.valueOf(((Long)map.get("playerId")).longValue());
/* 25 */       String playerName = (String)map.get("playerName");
/* 26 */       int level = ((Integer)map.get("level")).intValue();
/* 27 */       long fightValue = ((Long)map.get("totalValue")).longValue();
/* 28 */       String head = (String)map.get("head");
/* 29 */       byte vip = Byte.parseByte(map.get("vip").toString());
/* 30 */       byte sex = Byte.parseByte(map.get("sex").toString());
/* 31 */       int nightmareStar = ((Integer)map.get("nightmareStar")).intValue();
/* 32 */       int wearTitle = ((Integer)map.get("wearTitle")).intValue();
/* 33 */       int wearFashion = ((Integer)map.get("wearFashion")).intValue();
/* 34 */       int quality = Integer.parseInt(map.get("quality").toString());
/* 35 */       this.playerIdRankMap.put(playerId, Integer.valueOf(rank));
/*    */       
/* 37 */       if (rank <= this.defaultSaveSize)
/* 38 */         this.ranks.add(RankingUtil.transform(playerId.longValue(), playerName, fightValue, level, head, vip, sex, wearTitle, wearFashion, quality, nightmareStar, rank++)); 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\service\rank\RankingGenStar3.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */