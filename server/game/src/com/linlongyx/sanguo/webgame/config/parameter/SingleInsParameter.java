/*    */ package com.linlongyx.sanguo.webgame.config.parameter;
/*    */ 
/*    */ import com.linlongyx.sanguo.webgame.config.bean.ParameterBean;
/*    */ import com.linlongyx.sanguo.webgame.config.bean.PersonalInsBean;
/*    */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ 
/*    */ 
/*    */ public class SingleInsParameter
/*    */   extends AbstractParameter
/*    */ {
/*    */   private Map<Integer, Map<Integer, Integer>> data_map;
/*    */   
/*    */   public SingleInsParameter() {
/* 16 */     super(13);
/*    */ 
/*    */     
/* 19 */     this.data_map = new HashMap<>();
/*    */   }
/*    */   private void initIns() {
/* 22 */     this.data_map.clear();
/* 23 */     Map<Integer, Object> map = JsonTableService.getJsonTable(PersonalInsBean.class);
/*    */ 
/*    */     
/* 26 */     for (Map.Entry<Integer, Object> entry : map.entrySet()) {
/* 27 */       PersonalInsBean personalInsBean = (PersonalInsBean)entry.getValue();
/* 28 */       Map<Integer, Integer> temp = this.data_map.getOrDefault(Integer.valueOf(personalInsBean.getIntType()), new HashMap<>());
/* 29 */       temp.put(Integer.valueOf(personalInsBean.getCheckPoint()), Integer.valueOf(personalInsBean.getInsId()));
/* 30 */       this.data_map.put(Integer.valueOf(personalInsBean.getIntType()), temp);
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Map<Integer, Integer> getInsListByType(int type) {
/* 40 */     return this.data_map.get(Integer.valueOf(type));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   void init(ParameterBean bean) {
/* 46 */     initIns();
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\config\parameter\SingleInsParameter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */