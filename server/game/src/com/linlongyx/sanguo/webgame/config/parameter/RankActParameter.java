/*    */ package com.linlongyx.sanguo.webgame.config.parameter;
/*    */ 
/*    */ import com.linlongyx.sanguo.webgame.config.bean.ParameterBean;
/*    */ import com.linlongyx.sanguo.webgame.config.bean.RankingActivityBean;
/*    */ import com.linlongyx.sanguo.webgame.config.bean.RankingActivitylistBean;
/*    */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*    */ import java.util.ArrayList;
/*    */ import java.util.HashMap;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ 
/*    */ public class RankActParameter extends AbstractParameter {
/*    */   private Map<Integer, List<Integer>> types;
/*    */   private Map<Integer, List<Integer>> ranges;
/*    */   
/*    */   public RankActParameter() {
/* 17 */     super(20);
/*    */     
/* 19 */     this.types = new HashMap<>();
/* 20 */     this.ranges = new HashMap<>();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public List<Integer> getIdsByType(int type) {
/* 28 */     return this.types.get(Integer.valueOf(type));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public List<Integer> getRange(int id) {
/* 37 */     return this.ranges.get(Integer.valueOf(id));
/*    */   }
/*    */   
/*    */   private void initConfig() {
/* 41 */     this.types.clear();
/* 42 */     Map<Integer, Object> map = JsonTableService.getJsonTable(RankingActivityBean.class);
/*    */     
/* 44 */     for (Map.Entry<Integer, Object> entry : map.entrySet()) {
/* 45 */       RankingActivityBean rankingActivityBean = (RankingActivityBean)entry.getValue();
/* 46 */       List<Integer> list = this.types.getOrDefault(Integer.valueOf(rankingActivityBean.getType()), new ArrayList<>());
/* 47 */       list.add(Integer.valueOf(rankingActivityBean.getId()));
/* 48 */       this.types.put(Integer.valueOf(rankingActivityBean.getType()), list);
/*    */     } 
/*    */     
/* 51 */     this.ranges.clear();
/* 52 */     map = JsonTableService.getJsonTable(RankingActivitylistBean.class);
/* 53 */     for (Map.Entry<Integer, Object> entry : map.entrySet()) {
/* 54 */       RankingActivitylistBean rankingActivitylistBean = (RankingActivitylistBean)entry.getValue();
/* 55 */       String[] strings = rankingActivitylistBean.getTarget().split(";");
/* 56 */       if (2 == strings.length) {
/* 57 */         List<Integer> temp = new ArrayList<>();
/* 58 */         temp.add(Integer.valueOf(strings[0]));
/* 59 */         temp.add(Integer.valueOf(strings[1]));
/* 60 */         temp.add(Integer.valueOf(rankingActivitylistBean.getEntryConditions()));
/* 61 */         this.ranges.put(Integer.valueOf(rankingActivitylistBean.getId()), temp);
/*    */       } 
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   void init(ParameterBean bean) {
/* 68 */     initConfig();
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\config\parameter\RankActParameter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */