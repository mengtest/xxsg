/*    */ package com.linlongyx.sanguo.webgame.config.parameter;
/*    */ 
/*    */ import com.linlongyx.sanguo.webgame.config.bean.ParameterBean;
/*    */ import java.util.ArrayList;
/*    */ import java.util.Collections;
/*    */ import java.util.HashMap;
/*    */ import java.util.Iterator;
/*    */ import java.util.Map;
/*    */ 
/*    */ public class StageParameter
/*    */   extends AbstractParameter {
/*    */   private static final int TYPE_1 = 1;
/*    */   private Map<Integer, Integer> expMap;
/*    */   
/*    */   public StageParameter() {
/* 16 */     super(54);
/*    */ 
/*    */ 
/*    */     
/* 20 */     this.expMap = new HashMap<>();
/*    */   }
/*    */   
/*    */   void init(ParameterBean bean) {
/* 24 */     this.expMap.clear();
/* 25 */     String[] strings = ((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(1))).getValue().split(";");
/* 26 */     for (String string : strings) {
/* 27 */       String[] strings2 = string.split(",");
/* 28 */       this.expMap.put(Integer.valueOf(Integer.parseInt(strings2[0])), Integer.valueOf(Integer.parseInt(strings2[1])));
/*    */     } 
/*    */   }
/*    */   
/*    */   public Map<Integer, Integer> getExpMap() {
/* 33 */     return this.expMap;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public ArrayList<Integer> GetExpList() {
/* 41 */     ArrayList<Integer> arrayList = new ArrayList<>();
/* 42 */     ArrayList<Integer> ids = new ArrayList<>(); Iterator<Integer> iterator;
/* 43 */     for (iterator = this.expMap.values().iterator(); iterator.hasNext(); ) { int num = ((Integer)iterator.next()).intValue();
/* 44 */       arrayList.add(Integer.valueOf(num)); }
/*    */     
/* 46 */     Collections.sort(arrayList);
/* 47 */     for (iterator = arrayList.iterator(); iterator.hasNext(); ) { int id = ((Integer)iterator.next()).intValue();
/* 48 */       for (Iterator<Integer> iterator1 = this.expMap.keySet().iterator(); iterator1.hasNext(); ) { int expId = ((Integer)iterator1.next()).intValue();
/* 49 */         int num = ((Integer)this.expMap.get(Integer.valueOf(expId))).intValue();
/* 50 */         if (num == id) {
/* 51 */           ids.add(Integer.valueOf(expId));
/*    */         } }
/*    */        }
/*    */     
/* 55 */     return ids;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\config\parameter\StageParameter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */