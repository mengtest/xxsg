/*    */ package com.linlongyx.sanguo.webgame.config.parameter;
/*    */ 
/*    */ import com.linlongyx.sanguo.webgame.config.bean.ParameterBean;
/*    */ import java.util.ArrayList;
/*    */ import java.util.Collections;
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ 
/*    */ 
/*    */ public class RuneParameter
/*    */   extends AbstractParameter
/*    */ {
/*    */   private int holeLimit;
/*    */   private ArrayList<Integer> levelList;
/*    */   private Map<Integer, Integer> levelMap;
/*    */   
/*    */   public RuneParameter() {
/* 18 */     super(69);
/*    */ 
/*    */ 
/*    */     
/* 22 */     this.levelList = new ArrayList<>();
/* 23 */     this.levelMap = new HashMap<>();
/*    */   }
/*    */ 
/*    */   
/*    */   void init(ParameterBean bean) {
/* 28 */     this.holeLimit = Integer.parseInt(((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(1))).getValue());
/*    */     
/* 30 */     this.levelList.clear();
/* 31 */     String[] string2 = ((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(2))).getValue().split(";");
/* 32 */     for (String string : string2) {
/* 33 */       this.levelList.add(Integer.valueOf(Integer.parseInt(string)));
/*    */     }
/* 35 */     updateLevelMap();
/*    */   }
/*    */   
/*    */   public int getHoleLimit() {
/* 39 */     return this.holeLimit;
/*    */   }
/*    */   
/*    */   public ArrayList<Integer> getLevelList() {
/* 43 */     return this.levelList;
/*    */   }
/*    */   
/*    */   public void updateLevelMap() {
/* 47 */     ArrayList<Integer> arrayList = getLevelList();
/* 48 */     Collections.sort(arrayList);
/* 49 */     for (int i = 1; i <= arrayList.size(); i++) {
/* 50 */       this.levelMap.put(Integer.valueOf(i), arrayList.get(i - 1));
/*    */     }
/*    */   }
/*    */   
/*    */   public int getHoleLevel(int hole) {
/* 55 */     return ((Integer)this.levelMap.get(Integer.valueOf(hole))).intValue();
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\config\parameter\RuneParameter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */