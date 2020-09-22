/*    */ package com.linlongyx.sanguo.webgame.service.rank;
/*    */ 
/*    */ import com.linlongyx.sanguo.webgame.processors.rank.RankingUtil;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class RankingCardBook
/*    */   extends AbstractRank
/*    */ {
/*    */   public void initRanksMySql() {
/* 16 */     this.ranks.clear();
/* 17 */     this.playerIdRankMap.clear();
/* 18 */     String selectSql = "SELECT distinct p.playerId,p.playerName,p.level,p.totalValue,p.head,p.sex,p.vip,p.wearTitle,p.quality,c.lastToColor,c.colorTime FROM tb_player p,tb_cardbook c  where p.playerId = c.playerId and c.lastToColor>0 ORDER BY c.lastToColor DESC,c.colorTime DESC LIMIT " + this.defaultSaveSize;
/*    */     
/* 20 */     List<Map<String, Object>> info = this.template.queryForList(selectSql);
/* 21 */     if (info.size() == 0) {
/*    */       return;
/*    */     }
/* 24 */     int rank = 1;
/* 25 */     for (Map<String, Object> map : info) {
/* 26 */       Long playerId = Long.valueOf(((Long)map.get("playerId")).longValue());
/* 27 */       String playerName = (String)map.get("playerName");
/* 28 */       int level = ((Integer)map.get("level")).intValue();
/* 29 */       long fightValue = ((Long)map.get("totalValue")).longValue();
/* 30 */       String head = (String)map.get("head");
/* 31 */       byte vip = Byte.parseByte(map.get("vip").toString());
/* 32 */       byte sex = Byte.parseByte(map.get("sex").toString());
/* 33 */       int wearTitle = ((Integer)map.get("wearTitle")).intValue();
/* 34 */       int quality = Integer.parseInt(map.get("quality").toString());
/* 35 */       long lastToColor = ((Integer)map.get("lastToColor")).intValue();
/* 36 */       this.playerIdRankMap.put(playerId, Integer.valueOf(rank));
/* 37 */       this.ranks.add(RankingUtil.transform(playerId.longValue(), playerName, fightValue, level, head, vip, sex, wearTitle, 0, quality, lastToColor, rank++));
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\service\rank\RankingCardBook.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */