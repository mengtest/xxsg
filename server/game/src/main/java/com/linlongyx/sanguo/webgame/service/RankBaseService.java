/*    */ package com.linlongyx.sanguo.webgame.service;
/*    */ 
/*    */ import com.linlongyx.sanguo.webgame.constant.MContext;
/*    */ import com.linlongyx.sanguo.webgame.service.rank.AbstractRank;
/*    */ import com.linlongyx.sanguo.webgame.service.rank.RankingType;
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class RankBaseService
/*    */ {
/* 15 */   private final transient Object lock = new Object();
/*    */   
/* 17 */   private final Map<Integer, AbstractRank> Rank_MAP = new HashMap<>();
/*    */   
/*    */   public void init() {
/* 20 */     synchronized (this.lock) {
/* 21 */       for (RankingType rankType : RankingType.values()) {
/* 22 */         this.Rank_MAP.put(Integer.valueOf(rankType.getType()), rankType.getAbstractRank());
/* 23 */         rankType.getAbstractRank().init();
/* 24 */         rankType.getAbstractRank().initRanksMySql();
/*    */       } 
/*    */     } 
/*    */   }
/*    */   
/*    */   public void refresh(int type) {
/* 30 */     synchronized (this.lock) {
/* 31 */       if (MContext.getDAO() instanceof com.linlongyx.core.framework.dao.mysql.MysqlDAO) {
/* 32 */         ((AbstractRank)this.Rank_MAP.get(Integer.valueOf(type))).initRanksMySql();
/*    */       }
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractRank getAbstractRank(int type) {
/* 39 */     synchronized (this.lock) {
/* 40 */       return this.Rank_MAP.get(Integer.valueOf(type));
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\service\RankBaseService.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */