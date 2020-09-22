/*    */ package com.linlongyx.sanguo.webgame.config.parameter;
/*    */ 
/*    */ import com.linlongyx.sanguo.webgame.config.bean.ParameterBean;
/*    */ import java.util.ArrayList;
/*    */ import java.util.Collections;
/*    */ import java.util.HashMap;
/*    */ import java.util.Iterator;
/*    */ import java.util.Map;
/*    */ 
/*    */ public class SoulsParameter
/*    */   extends AbstractParameter {
/*    */   private static final int TYPE_1 = 1;
/*    */   private Map<Integer, Integer> expMap;
/*    */   
/*    */   public SoulsParameter() {
/* 16 */     super(55);
/*    */ 
/*    */ 
/*    */     
/* 20 */     this.expMap = new HashMap<>();
/* 21 */     this.levelOpen = new HashMap<>();
/*    */   }
/*    */   private Map<Integer, Integer> levelOpen; private int rankLevel;
/*    */   private int reCastCCY;
/*    */   
/*    */   void init(ParameterBean bean) {
/* 27 */     this.expMap.clear();
/* 28 */     String[] strings = ((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(1))).getValue().split(";");
/* 29 */     for (String string : strings) {
/* 30 */       String[] str = string.split(":");
/* 31 */       this.expMap.put(Integer.valueOf(Integer.parseInt(str[0])), Integer.valueOf(Integer.parseInt(str[1])));
/*    */     } 
/* 33 */     this.levelOpen.clear();
/* 34 */     String[] strings2 = ((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(2))).getValue().split(";");
/* 35 */     for (String str2 : strings2) {
/* 36 */       String[] str = str2.split(":");
/* 37 */       this.levelOpen.put(Integer.valueOf(Integer.parseInt(str[0])), Integer.valueOf(Integer.parseInt(str[1])));
/*    */     } 
/* 39 */     this.rankLevel = Integer.parseInt(((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(3))).getValue());
/* 40 */     this.reCastCCY = Integer.parseInt(((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(8))).getValue());
/*    */   }
/*    */   
/*    */   public Map<Integer, Integer> getExpMap() {
/* 44 */     return this.expMap;
/*    */   }
/*    */   
/*    */   public Map<Integer, Integer> getLevelOpen() {
/* 48 */     return this.levelOpen;
/*    */   }
/*    */   
/*    */   public int getRankLevel() {
/* 52 */     return this.rankLevel;
/*    */   }
/*    */   
/*    */   public int getReCastCCY() {
/* 56 */     return this.reCastCCY;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public ArrayList<Integer> GetExpList() {
/* 64 */     ArrayList<Integer> arrayList = new ArrayList<>();
/* 65 */     ArrayList<Integer> ids = new ArrayList<>(); Iterator<Integer> iterator;
/* 66 */     for (iterator = this.expMap.values().iterator(); iterator.hasNext(); ) { int num = ((Integer)iterator.next()).intValue();
/* 67 */       arrayList.add(Integer.valueOf(num)); }
/*    */     
/* 69 */     Collections.sort(arrayList);
/* 70 */     for (iterator = arrayList.iterator(); iterator.hasNext(); ) { int id = ((Integer)iterator.next()).intValue();
/* 71 */       for (Iterator<Integer> iterator1 = this.expMap.keySet().iterator(); iterator1.hasNext(); ) { int expId = ((Integer)iterator1.next()).intValue();
/* 72 */         int num = ((Integer)this.expMap.get(Integer.valueOf(expId))).intValue();
/* 73 */         if (num == id) {
/* 74 */           ids.add(Integer.valueOf(expId));
/*    */         } }
/*    */        }
/*    */     
/* 78 */     return ids;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\config\parameter\SoulsParameter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */