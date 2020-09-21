/*    */ package com.linlongyx.sanguo.webgame.config.parameter;
/*    */ import com.linlongyx.sanguo.webgame.config.bean.RankingKuafuBean;
/*    */ import com.linlongyx.sanguo.webgame.config.bean.RankingKuafulistBean;
/*    */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*    */ import java.util.HashMap;
/*    */ import java.util.HashSet;
/*    */ import java.util.Iterator;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ import java.util.Set;
/*    */ 
/*    */ public class CrossRankParameter extends AbstractParameter {
/*    */   public CrossRankParameter() {
/* 14 */     super(59);
/*    */     
/* 16 */     this.ranges = new HashMap<>();
/* 17 */     this.crossTypes = new HashMap<>();
/*    */   }
/*    */ 
/*    */   
/*    */   private Map<Integer, List<Integer>> ranges;
/*    */   
/*    */   private Map<Integer, Set<Integer>> crossTypes;
/*    */   
/*    */   public Set<Integer> getCrossActIdsByType(int type) {
/* 26 */     return this.crossTypes.getOrDefault(Integer.valueOf(type), new HashSet<>());
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public List<Integer> getRange(int id) {
/* 35 */     return this.ranges.get(Integer.valueOf(id));
/*    */   }
/*    */   
/*    */   private void initConfig() {
/* 39 */     Map<Integer, Object> map = JsonTableService.getJsonTable(RankingKuafulistBean.class);
/*    */     
/* 41 */     this.crossTypes.clear();
/* 42 */     for (null = JsonTableService.getJsonTableKey(RankingKuafuBean.class).iterator(); null.hasNext(); ) { int id = ((Integer)null.next()).intValue();
/* 43 */       RankingKuafuBean bean = (RankingKuafuBean)JsonTableService.getJsonData(id, RankingKuafuBean.class);
/* 44 */       this.crossTypes.putIfAbsent(Integer.valueOf(bean.getType()), new HashSet<>());
/* 45 */       ((Set<Integer>)this.crossTypes.get(Integer.valueOf(bean.getType()))).add(Integer.valueOf(bean.getId())); }
/*    */ 
/*    */     
/* 48 */     this.ranges.clear();
/* 49 */     for (Map.Entry<Integer, Object> entry : map.entrySet()) {
/* 50 */       RankingKuafulistBean rankingKuafulistBean = (RankingKuafulistBean)entry.getValue();
/* 51 */       String[] strings = rankingKuafulistBean.getTarget().split(";");
/* 52 */       if (2 == strings.length) {
/* 53 */         List<Integer> temp = new ArrayList<>();
/* 54 */         temp.add(Integer.valueOf(strings[0]));
/* 55 */         temp.add(Integer.valueOf(strings[1]));
/*    */         
/* 57 */         this.ranges.put(Integer.valueOf(rankingKuafulistBean.getId()), temp);
/*    */       } 
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   void init(ParameterBean bean) {
/* 64 */     initConfig();
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\config\parameter\CrossRankParameter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */