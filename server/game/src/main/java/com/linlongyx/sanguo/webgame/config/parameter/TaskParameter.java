/*    */ package com.linlongyx.sanguo.webgame.config.parameter;
/*    */ 
/*    */ import com.linlongyx.core.utils.TimeUtil;
/*    */ import com.linlongyx.sanguo.webgame.config.bean.DailyBean;
/*    */ import com.linlongyx.sanguo.webgame.config.bean.ParameterBean;
/*    */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*    */ import java.util.ArrayList;
/*    */ import java.util.HashMap;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ 
/*    */ public class TaskParameter
/*    */   extends AbstractParameter {
/*    */   private Map<Integer, List<Integer>> configs;
/*    */   private int days;
/*    */   private String effectiveDate;
/*    */   
/*    */   public TaskParameter() {
/* 19 */     super(22);
/*    */ 
/*    */     
/* 22 */     this.configs = new HashMap<>();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public List<Integer> getDaily(int taskType) {
/* 33 */     return this.configs.get(Integer.valueOf(taskType));
/*    */   }
/*    */   
/*    */   private void initDaily() {
/* 37 */     this.configs.clear();
/*    */     
/* 39 */     Map<Integer, Object> map = JsonTableService.getJsonTable(DailyBean.class);
/* 40 */     for (Map.Entry<Integer, Object> entry : map.entrySet()) {
/* 41 */       DailyBean dailyBean = (DailyBean)entry.getValue();
/* 42 */       List<Integer> list = this.configs.getOrDefault(Integer.valueOf(dailyBean.getTaskType()), new ArrayList<>());
/* 43 */       list.add(Integer.valueOf(dailyBean.getId()));
/* 44 */       this.configs.put(Integer.valueOf(dailyBean.getTaskType()), list);
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   void init(ParameterBean bean) {
/* 51 */     this.days = Integer.parseInt(((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(1))).getValue());
/* 52 */     this.effectiveDate = ((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(2))).getValue();
/* 53 */     initDaily();
/*    */   }
/*    */   
/*    */   public int getDays() {
/* 57 */     return this.days;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getEffectiveDate() {
/* 62 */     return this.effectiveDate;
/*    */   }
/*    */   
/*    */   public int getEffectDate() {
/* 66 */     int time = TimeUtil.getTimeFromDate(getEffectiveDate());
/* 67 */     return TimeUtil.getYearMonthDay(time);
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\config\parameter\TaskParameter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */