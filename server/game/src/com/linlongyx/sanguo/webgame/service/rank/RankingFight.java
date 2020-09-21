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
/*    */ 
/*    */ 
/*    */ public class RankingFight
/*    */   extends AbstractRank
/*    */ {
/*    */   public void init() {
/* 17 */     RankingBean rankingBean1 = (RankingBean)JsonTableService.getJsonData(RankingType.FIGHT.getType(), RankingBean.class);
/* 18 */     if (null != rankingBean1) {
/* 19 */       this.fightSaveSize = rankingBean1.getMaxRank();
/*    */     }
/*    */   }
/*    */   
/*    */   public void initRanksMySql() {
/* 24 */     this.ranks.clear();
/* 25 */     this.playerIdRankMap.clear();
/* 26 */     String selectSql = "SELECT distinct p.playerId,p.playerName,p.level,p.totalValue,p.head,p.sex,p.vip,p.wearTitle,p.wearFashion,p.quality FROM tb_player p  ORDER BY p.totalValue DESC, p.level DESC, p.playerId DESC LIMIT " + this.fightSaveSize;
/*    */     
/* 28 */     List<Map<String, Object>> info = this.template.queryForList(selectSql);
/* 29 */     if (info.size() == 0) {
/*    */       return;
/*    */     }
/* 32 */     int rank = 1;
/* 33 */     for (Map<String, Object> map : info) {
/* 34 */       Long playerId = Long.valueOf(((Long)map.get("playerId")).longValue());
/* 35 */       String playerName = (String)map.get("playerName");
/* 36 */       int level = ((Integer)map.get("level")).intValue();
/* 37 */       long fightValue = ((Long)map.get("totalValue")).longValue();
/* 38 */       String head = (String)map.get("head");
/* 39 */       byte vip = Byte.parseByte(map.get("vip").toString());
/* 40 */       byte sex = Byte.parseByte(map.get("sex").toString());
/* 41 */       int wearTitle = ((Integer)map.get("wearTitle")).intValue();
/* 42 */       int wearFashion = ((Integer)map.get("wearFashion")).intValue();
/* 43 */       int quality = Integer.parseInt(map.get("quality").toString());
/* 44 */       this.playerIdRankMap.put(playerId, Integer.valueOf(rank));
/* 45 */       this.ranks.add(RankingUtil.transform(playerId.longValue(), playerName, fightValue, level, head, vip, sex, wearTitle, wearFashion, quality, fightValue, rank++));
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\service\rank\RankingFight.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */