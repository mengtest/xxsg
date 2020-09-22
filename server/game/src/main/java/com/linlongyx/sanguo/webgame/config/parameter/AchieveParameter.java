/*    */ package com.linlongyx.sanguo.webgame.config.parameter;
/*    */ 
/*    */ import com.linlongyx.sanguo.webgame.config.bean.FameBean;
/*    */ import com.linlongyx.sanguo.webgame.config.bean.ParameterBean;
/*    */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*    */ import java.util.ArrayList;
/*    */ import java.util.HashMap;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ 
/*    */ public class AchieveParameter extends AbstractParameter {
/*    */   private Map<Integer, List<Integer>> configs;
/*    */   
/*    */   public AchieveParameter() {
/* 15 */     super(22);
/*    */ 
/*    */     
/* 18 */     this.configs = new HashMap<>();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public List<Integer> getAchieveList(int taskType) {
/* 26 */     return this.configs.get(Integer.valueOf(taskType));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Map<Integer, List<Integer>> getConfigs() {
/* 34 */     return this.configs;
/*    */   }
/*    */   
/*    */   private void initAchieve() {
/* 38 */     this.configs.clear();
/*    */     
/* 40 */     Map<Integer, Object> map = JsonTableService.getJsonTable(FameBean.class);
/* 41 */     for (Map.Entry<Integer, Object> entry : map.entrySet()) {
/* 42 */       FameBean fameBean = (FameBean)entry.getValue();
/* 43 */       List<Integer> list = this.configs.getOrDefault(Integer.valueOf(fameBean.getTargetType()), new ArrayList<>());
/* 44 */       list.add(Integer.valueOf(fameBean.getFameId()));
/* 45 */       this.configs.put(Integer.valueOf(fameBean.getTargetType()), list);
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   void init(ParameterBean bean) {
/* 52 */     initAchieve();
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\config\parameter\AchieveParameter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */