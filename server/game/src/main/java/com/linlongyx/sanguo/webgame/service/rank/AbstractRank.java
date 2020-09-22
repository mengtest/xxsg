/*    */ package com.linlongyx.sanguo.webgame.service.rank;
/*    */ 
/*    */ import com.linlongyx.core.framework.context.AppContext;
/*    */ import com.linlongyx.core.framework.dao.mysql.MysqlDAO;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.RankingData;
/*    */ import java.util.ArrayList;
/*    */ import java.util.HashMap;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ import org.springframework.jdbc.core.JdbcTemplate;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class AbstractRank
/*    */ {
/* 17 */   MysqlDAO mysql = (MysqlDAO)AppContext.getDAO();
/* 18 */   JdbcTemplate template = this.mysql.getTemplate();
/*    */   
/* 20 */   protected List<RankingData> ranks = new ArrayList<>();
/* 21 */   protected Map<Long, Integer> playerIdRankMap = new HashMap<>();
/*    */   
/* 23 */   public int defaultSaveSize = 40;
/* 24 */   public int levelSaveSize = 40;
/* 25 */   public int arenaSaveSize = 40;
/* 26 */   public int fightSaveSize = 40;
/* 27 */   public int barrierSaveSize = 40;
/* 28 */   public int rankSize = 200;
/*    */ 
/*    */   
/*    */   public void init() {}
/*    */ 
/*    */   
/*    */   public int getPlayerRank(long playerId) {
/* 35 */     if (this.playerIdRankMap.containsKey(Long.valueOf(playerId))) {
/* 36 */       return ((Integer)this.playerIdRankMap.get(Long.valueOf(playerId))).intValue();
/*    */     }
/* 38 */     return 0;
/*    */   }
/*    */   
/*    */   public long getFirst() {
/* 42 */     if (this.ranks.size() > 0)
/* 43 */       return ((RankingData)this.ranks.get(0)).playerId; 
/* 44 */     return 0L;
/*    */   }
/*    */   
/*    */   public abstract void initRanksMySql();
/*    */   
/*    */   public List<RankingData> getRanks() {
/* 50 */     return this.ranks;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\service\rank\AbstractRank.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */