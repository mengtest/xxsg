/*    */ package com.linlongyx.sanguo.webgame.service.rank;
/*    */ 
/*    */ import com.linlongyx.sanguo.webgame.config.bean.RankingBean;
/*    */ import com.linlongyx.sanguo.webgame.config.parameter.LoginParameter;
/*    */ import com.linlongyx.sanguo.webgame.constant.ParameterConstant;
/*    */ import com.linlongyx.sanguo.webgame.processors.rank.RankingUtil;
/*    */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class RankingLevel
/*    */   extends AbstractRank
/*    */ {
/* 17 */   private static int worldLevel = 0;
/*    */   
/*    */   public static void setWorldLevel(int worldLevel) {
/* 20 */     RankingLevel.worldLevel = worldLevel;
/*    */   }
/*    */   
/*    */   public static int getWorldLevel() {
/* 24 */     return worldLevel;
/*    */   }
/*    */ 
/*    */   
/* 28 */   int levelSize = 10;
/*    */ 
/*    */   
/*    */   public void init() {
/* 32 */     RankingBean rankingBean2 = (RankingBean)JsonTableService.getJsonData(RankingType.LEVEL.getType(), RankingBean.class);
/* 33 */     if (null != rankingBean2) {
/* 34 */       this.levelSaveSize = rankingBean2.getMaxRank();
/*    */     }
/* 36 */     LoginParameter loginParameter = (LoginParameter)ParameterConstant.getParameter(0);
/* 37 */     this.levelSize = loginParameter.getRankLevelNum();
/*    */   }
/*    */ 
/*    */   
/*    */   public void initRanksMySql() {
/* 42 */     this.ranks.clear();
/* 43 */     this.playerIdRankMap.clear();
/* 44 */     String selectSql = "SELECT distinct p.playerId,p.playerName,p.level,p.totalValue,p.head,p.sex,p.vip,p.wearTitle,p.wearFashion,p.quality FROM tb_player p  ORDER BY p.level DESC, p.totalValue DESC, p.playerId DESC LIMIT " + this.levelSaveSize;
/*    */     
/* 46 */     List<Map<String, Object>> info = this.template.queryForList(selectSql);
/* 47 */     if (info.size() == 0) {
/*    */       return;
/*    */     }
/*    */     
/* 51 */     int rank = 1;
/* 52 */     int totalLevel = 0;
/* 53 */     for (Map<String, Object> map : info) {
/* 54 */       Long playerId = Long.valueOf(((Long)map.get("playerId")).longValue());
/* 55 */       String playerName = (String)map.get("playerName");
/* 56 */       int level = ((Integer)map.get("level")).intValue();
/* 57 */       long fightValue = ((Long)map.get("totalValue")).longValue();
/* 58 */       String head = (String)map.get("head");
/* 59 */       byte vip = Byte.parseByte(map.get("vip").toString());
/* 60 */       byte sex = Byte.parseByte(map.get("sex").toString());
/* 61 */       int wearTitle = ((Integer)map.get("wearTitle")).intValue();
/* 62 */       int wearFashion = ((Integer)map.get("wearFashion")).intValue();
/* 63 */       int quality = Integer.parseInt(map.get("quality").toString());
/* 64 */       this.playerIdRankMap.put(playerId, Integer.valueOf(rank));
/* 65 */       if (rank <= this.levelSize) {
/* 66 */         totalLevel += level;
/*    */       }
/* 68 */       this.ranks.add(RankingUtil.transform(playerId.longValue(), playerName, fightValue, level, head, vip, sex, wearTitle, wearFashion, quality, level, rank++));
/*    */     } 
/* 70 */     worldLevel = totalLevel / this.levelSize;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\service\rank\RankingLevel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */