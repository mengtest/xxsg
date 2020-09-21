/*    */ package com.linlongyx.sanguo.webgame.service.rank;
/*    */ 
/*    */ import com.linlongyx.sanguo.webgame.config.bean.ArenaRankingBean;
/*    */ import com.linlongyx.sanguo.webgame.config.bean.ArenaRobotBean;
/*    */ import com.linlongyx.sanguo.webgame.config.bean.RankingBean;
/*    */ import com.linlongyx.sanguo.webgame.processors.player.PlayerUtil;
/*    */ import com.linlongyx.sanguo.webgame.processors.rank.RankingUtil;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.RankingData;
/*    */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*    */ import java.util.HashMap;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class RankingArena
/*    */   extends AbstractRank
/*    */ {
/*    */   public void init() {
/* 25 */     RankingBean rankingBean3 = (RankingBean)JsonTableService.getJsonData(RankingType.ARENA.getType(), RankingBean.class);
/* 26 */     if (null != rankingBean3) {
/* 27 */       this.arenaSaveSize = rankingBean3.getMaxRank();
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void initRanksMySql() {
/* 34 */     this.ranks.clear();
/* 35 */     this.playerIdRankMap.clear();
/* 36 */     String selectSql = "SELECT distinct p.playerId,p.playerName,p.level,p.totalValue,p.head,p.sex,p.vip,p.wearTitle,p.wearFashion,p.quality,arank.rank FROM tb_player p,tb_arena arank where p.playerId = arank.playerId and arank.rank != 0 ORDER BY arank.rank LIMIT " + this.arenaSaveSize;
/*    */     
/* 38 */     List<Map<String, Object>> info = this.template.queryForList(selectSql);
/*    */     
/* 40 */     Map<Integer, RankingData> rankMap = new HashMap<>();
/* 41 */     for (Map<String, Object> map : info) {
/* 42 */       int rank = ((Integer)map.get("rank")).intValue();
/* 43 */       if (rank >= this.rankSize)
/* 44 */         continue;  Long playerId = Long.valueOf(((Long)map.get("playerId")).longValue());
/* 45 */       String playerName = (String)map.get("playerName");
/* 46 */       int level = ((Integer)map.get("level")).intValue();
/* 47 */       long fightValue = ((Long)map.get("totalValue")).longValue();
/* 48 */       String head = (String)map.get("head");
/* 49 */       byte vip = Byte.parseByte(map.get("vip").toString());
/* 50 */       byte sex = Byte.parseByte(map.get("sex").toString());
/* 51 */       int wearTitle = ((Integer)map.get("wearTitle")).intValue();
/* 52 */       int wearFashion = ((Integer)map.get("wearFashion")).intValue();
/* 53 */       int quality = Integer.parseInt(map.get("quality").toString());
/* 54 */       this.playerIdRankMap.put(playerId, Integer.valueOf(rank));
/* 55 */       rankMap.put(Integer.valueOf(rank), RankingUtil.transform(playerId.longValue(), playerName, fightValue, level, head, vip, sex, wearTitle, wearFashion, quality, rank, rank));
/*    */     } 
/* 57 */     for (int i = 1; i <= this.arenaSaveSize; i++) {
/* 58 */       if (rankMap.containsKey(Integer.valueOf(i))) {
/* 59 */         this.ranks.add(rankMap.get(Integer.valueOf(i)));
/*    */       } else {
/* 61 */         ArenaRankingBean arenaRankingBean = (ArenaRankingBean)JsonTableService.getJsonData(i, ArenaRankingBean.class);
/* 62 */         if (null != arenaRankingBean) {
/* 63 */           ArenaRobotBean rebooterBean = (ArenaRobotBean)JsonTableService.getJsonData(arenaRankingBean.getId(), ArenaRobotBean.class);
/* 64 */           if (null != rebooterBean)
/* 65 */             this.ranks.add(RankingUtil.transform(rebooterBean.getRobotId(), rebooterBean.getRobotName(), rebooterBean.getRoboteff(), rebooterBean.getLevel(), PlayerUtil.getRebotHeadUrl("", rebooterBean.getSex()), (byte)0, (byte)rebooterBean.getSex(), 0, 0, rebooterBean.getQuality(), i, i)); 
/*    */         } 
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\service\rank\RankingArena.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */