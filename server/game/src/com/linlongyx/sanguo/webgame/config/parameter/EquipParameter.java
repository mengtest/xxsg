/*    */ package com.linlongyx.sanguo.webgame.config.parameter;
/*    */ 
/*    */ import com.linlongyx.sanguo.webgame.config.bean.MasterForgingBean;
/*    */ import com.linlongyx.sanguo.webgame.config.bean.ParameterBean;
/*    */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EquipParameter
/*    */   extends AbstractParameter
/*    */ {
/*    */   private Map<Integer, Map<Integer, MasterForgingBean>> goals;
/*    */   
/*    */   public EquipParameter() {
/* 19 */     super(78);
/*    */ 
/*    */     
/* 22 */     this.goals = new HashMap<>();
/*    */   }
/*    */   public Map<Integer, MasterForgingBean> getGoals(int type) {
/* 25 */     return this.goals.get(Integer.valueOf(type));
/*    */   }
/*    */   
/*    */   public Map<Integer, Map<Integer, MasterForgingBean>> getGoals() {
/* 29 */     return this.goals;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   void init(ParameterBean bean) {
/* 36 */     Map<Integer, Object> config = JsonTableService.getJsonTable(MasterForgingBean.class);
/* 37 */     this.goals.clear();
/*    */ 
/*    */     
/* 40 */     for (Map.Entry<Integer, Object> entry : config.entrySet()) {
/* 41 */       Map<Integer, MasterForgingBean> map; MasterForgingBean formGoalsBean = (MasterForgingBean)entry.getValue();
/* 42 */       if (this.goals.containsKey(Integer.valueOf(formGoalsBean.getType()))) {
/* 43 */         map = this.goals.get(Integer.valueOf(formGoalsBean.getType()));
/*    */       } else {
/* 45 */         map = new HashMap<>();
/*    */       } 
/* 47 */       map.put(Integer.valueOf(formGoalsBean.getLevel()), formGoalsBean);
/* 48 */       this.goals.put(Integer.valueOf(formGoalsBean.getType()), map);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\config\parameter\EquipParameter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */