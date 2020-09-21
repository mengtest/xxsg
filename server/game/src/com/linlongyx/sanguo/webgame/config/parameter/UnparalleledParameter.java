/*    */ package com.linlongyx.sanguo.webgame.config.parameter;
/*    */ 
/*    */ import com.linlongyx.sanguo.webgame.config.bean.ParameterBean;
/*    */ import com.linlongyx.sanguo.webgame.config.bean.WushuangBuffBean;
/*    */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*    */ import java.util.ArrayList;
/*    */ import java.util.Iterator;
/*    */ import java.util.Map;
/*    */ import org.apache.commons.collections.map.HashedMap;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class UnparalleledParameter
/*    */   extends AbstractParameter
/*    */ {
/*    */   private Map<Integer, Integer> resetCost;
/*    */   private Map<Integer, ArrayList<Integer>> starMap;
/*    */   
/*    */   public UnparalleledParameter() {
/* 21 */     super(18);
/*    */ 
/*    */     
/* 24 */     this.resetCost = (Map<Integer, Integer>)new HashedMap();
/*    */     
/* 26 */     this.starMap = (Map<Integer, ArrayList<Integer>>)new HashedMap();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int getCost(int num) {
/* 34 */     if (num + 1 <= this.resetCost.size()) {
/* 35 */       return ((Integer)this.resetCost.get(Integer.valueOf(num + 1))).intValue();
/*    */     }
/* 37 */     return ((Integer)this.resetCost.get(Integer.valueOf(this.resetCost.size() - 1))).intValue();
/*    */   }
/*    */ 
/*    */   
/*    */   void init(ParameterBean bean) {
/* 42 */     this.resetCost.clear();
/* 43 */     String[] strings = ((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(1))).getValue().split(";");
/* 44 */     for (String string : strings) {
/* 45 */       String[] strings2 = string.split(":");
/* 46 */       this.resetCost.put(Integer.valueOf(Integer.parseInt(strings2[0])), Integer.valueOf(Integer.parseInt(strings2[1])));
/*    */     } 
/*    */     
/* 49 */     Map<Integer, Object> maps = JsonTableService.getJsonTable(WushuangBuffBean.class);
/* 50 */     for (Iterator<Integer> iterator = maps.keySet().iterator(); iterator.hasNext(); ) { int ins = ((Integer)iterator.next()).intValue();
/* 51 */       WushuangBuffBean wushuangBuffBean = (WushuangBuffBean)maps.get(Integer.valueOf(ins));
/* 52 */       if (this.starMap.containsKey(Integer.valueOf(wushuangBuffBean.getCostStar()))) {
/* 53 */         ((ArrayList<Integer>)this.starMap.get(Integer.valueOf(wushuangBuffBean.getCostStar()))).add(Integer.valueOf(wushuangBuffBean.getBuffId())); continue;
/*    */       } 
/* 55 */       ArrayList<Integer> arrayList = new ArrayList<>();
/* 56 */       arrayList.add(Integer.valueOf(wushuangBuffBean.getBuffId()));
/* 57 */       this.starMap.put(Integer.valueOf(wushuangBuffBean.getCostStar()), arrayList); }
/*    */   
/*    */   }
/*    */ 
/*    */   
/*    */   public Map<Integer, Integer> getResetCost() {
/* 63 */     return this.resetCost;
/*    */   }
/*    */   
/*    */   public Map<Integer, ArrayList<Integer>> getStarMap() {
/* 67 */     return this.starMap;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\config\parameter\UnparalleledParameter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */