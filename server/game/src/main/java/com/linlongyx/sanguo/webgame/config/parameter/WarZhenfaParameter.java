/*    */ package com.linlongyx.sanguo.webgame.config.parameter;
/*    */ 
/*    */ import com.linlongyx.sanguo.webgame.config.bean.ParameterBean;
/*    */ import java.util.ArrayList;
/*    */ import java.util.Collections;
/*    */ import java.util.HashMap;
/*    */ import java.util.Iterator;
/*    */ import java.util.Map;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class WarZhenfaParameter
/*    */   extends AbstractParameter
/*    */ {
/*    */   private Map<Integer, Integer> expMap;
/*    */   
/*    */   public WarZhenfaParameter() {
/* 21 */     super(60);
/*    */ 
/*    */     
/* 24 */     this.expMap = new HashMap<>();
/*    */   }
/*    */   
/*    */   void init(ParameterBean bean) {
/* 28 */     this.expMap.clear();
/* 29 */     String[] strings = ((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(1))).getValue().split(";");
/* 30 */     for (String string : strings) {
/* 31 */       String[] strings2 = string.split(":");
/* 32 */       this.expMap.put(Integer.valueOf(Integer.parseInt(strings2[0])), Integer.valueOf(Integer.parseInt(strings2[1])));
/*    */     } 
/*    */   }
/*    */   
/*    */   public Map<Integer, Integer> getExpMap() {
/* 37 */     return this.expMap;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public ArrayList<Integer> GetExpList() {
/* 45 */     ArrayList<Integer> arrayList = new ArrayList<>();
/* 46 */     ArrayList<Integer> ids = new ArrayList<>(); Iterator<Integer> iterator;
/* 47 */     for (iterator = this.expMap.values().iterator(); iterator.hasNext(); ) { int num = ((Integer)iterator.next()).intValue();
/* 48 */       arrayList.add(Integer.valueOf(num)); }
/*    */     
/* 50 */     Collections.sort(arrayList);
/* 51 */     for (iterator = arrayList.iterator(); iterator.hasNext(); ) { int id = ((Integer)iterator.next()).intValue();
/* 52 */       for (Iterator<Integer> iterator1 = this.expMap.keySet().iterator(); iterator1.hasNext(); ) { int expId = ((Integer)iterator1.next()).intValue();
/* 53 */         int num = ((Integer)this.expMap.get(Integer.valueOf(expId))).intValue();
/* 54 */         if (num == id) {
/* 55 */           ids.add(Integer.valueOf(expId));
/*    */         } }
/*    */        }
/*    */     
/* 59 */     return ids;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\config\parameter\WarZhenfaParameter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */