/*    */ package com.linlongyx.sanguo.webgame.service.rank;
/*    */ 
/*    */ import com.linlongyx.sanguo.webgame.config.bean.RankingBean;
/*    */ import com.linlongyx.sanguo.webgame.processors.rank.RankingUtil;
/*    */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class RankingBarrier
/*    */   extends AbstractRank
/*    */ {
/*    */   public void init() {
/* 15 */     RankingBean rankingBean4 = (RankingBean)JsonTableService.getJsonData(RankingType.BARRIER.getType(), RankingBean.class);
/* 16 */     if (null != rankingBean4) {
/* 17 */       this.barrierSaveSize = rankingBean4.getMaxRank();
/*    */     }
/*    */   }
/*    */   
/*    */   public void initRanksMySql() {
/* 22 */     this.ranks.clear();
/* 23 */     this.playerIdRankMap.clear();
/* 24 */     String selectSql = "SELECT distinct p.playerId,p.playerName,p.level,p.totalValue,p.head,p.sex,p.vip,p.wearTitle,p.wearFashion,p.quality,task.chapter FROM tb_player p,tb_task task  where p.playerId = task.playerId and task.chapter>0 ORDER BY task.chapter DESC,p.level DESC,p.totalValue DESC LIMIT " + this.barrierSaveSize;
/*    */     
/* 26 */     List<Map<String, Object>> info = this.template.queryForList(selectSql);
/* 27 */     if (info.size() == 0) {
/*    */       return;
/*    */     }
/* 30 */     int rank = 1;
/* 31 */     for (Map<String, Object> map : info) {
/* 32 */       Long playerId = Long.valueOf(((Long)map.get("playerId")).longValue());
/* 33 */       String playerName = (String)map.get("playerName");
/* 34 */       int level = ((Integer)map.get("level")).intValue();
/* 35 */       long fightValue = ((Long)map.get("totalValue")).longValue();
/* 36 */       String head = (String)map.get("head");
/* 37 */       byte vip = Byte.parseByte(map.get("vip").toString());
/* 38 */       byte sex = Byte.parseByte(map.get("sex").toString());
/* 39 */       int wearTitle = ((Integer)map.get("wearTitle")).intValue();
/* 40 */       int wearFashion = ((Integer)map.get("wearFashion")).intValue();
/* 41 */       int quality = Integer.parseInt(map.get("quality").toString());
/* 42 */       long chapter = ((Integer)map.get("chapter")).intValue();
/* 43 */       this.playerIdRankMap.put(playerId, Integer.valueOf(rank));
/* 44 */       this.ranks.add(RankingUtil.transform(playerId.longValue(), playerName, fightValue, level, head, vip, sex, wearTitle, wearFashion, quality, chapter, rank++));
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\service\rank\RankingBarrier.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */