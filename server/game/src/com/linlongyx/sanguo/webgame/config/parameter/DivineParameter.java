/*    */ package com.linlongyx.sanguo.webgame.config.parameter;
/*    */ 
/*    */ import com.linlongyx.sanguo.webgame.config.bean.DivineBean;
/*    */ import com.linlongyx.sanguo.webgame.config.bean.DivineRewardBean;
/*    */ import com.linlongyx.sanguo.webgame.config.bean.ParameterBean;
/*    */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*    */ import java.util.HashMap;
/*    */ import java.util.Iterator;
/*    */ import java.util.Map;
/*    */ import java.util.TreeMap;
/*    */ 
/*    */ public class DivineParameter
/*    */   extends AbstractParameter {
/*    */   private int rankSize;
/*    */   private int perTimes;
/*    */   
/*    */   public DivineParameter() {
/* 18 */     super(57);
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 23 */     this.sameNumMap = new HashMap<>();
/* 24 */     this.rankMap = new HashMap<>();
/*    */   }
/*    */   private Map<Integer, Map<Integer, Integer>> sameNumMap; private Map<Integer, Map<Integer, Integer>> rankMap;
/*    */   void init(ParameterBean bean) {
/* 28 */     this.rankSize = Integer.parseInt(((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(1))).getValue());
/* 29 */     this.perTimes = Integer.parseInt(((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(2))).getValue());
/*    */     
/* 31 */     this.sameNumMap.clear();
/* 32 */     this.rankMap.clear();
/* 33 */     for (Iterator<Integer> iterator = JsonTableService.getJsonTableKey(DivineBean.class).iterator(); iterator.hasNext(); ) { int id = ((Integer)iterator.next()).intValue();
/* 34 */       DivineBean divineBean = (DivineBean)JsonTableService.getJsonData(id, DivineBean.class);
/* 35 */       this.sameNumMap.putIfAbsent(Integer.valueOf(id), new HashMap<>());
/* 36 */       this.rankMap.putIfAbsent(Integer.valueOf(id), new TreeMap<>()); Iterator<Integer> iterator1;
/* 37 */       for (iterator1 = divineBean.getLuckyReward().iterator(); iterator1.hasNext(); ) { int rid = ((Integer)iterator1.next()).intValue();
/* 38 */         DivineRewardBean divineRewardBean = (DivineRewardBean)JsonTableService.getJsonData(rid, DivineRewardBean.class);
/* 39 */         ((Map<Integer, Integer>)this.sameNumMap.get(Integer.valueOf(id))).putIfAbsent(Integer.valueOf(divineRewardBean.getNum()), Integer.valueOf(rid)); }
/*    */       
/* 41 */       for (iterator1 = divineBean.getRankReward().iterator(); iterator1.hasNext(); ) { int rid = ((Integer)iterator1.next()).intValue();
/* 42 */         DivineRewardBean divineRewardBean = (DivineRewardBean)JsonTableService.getJsonData(rid, DivineRewardBean.class);
/* 43 */         ((Map<Integer, Integer>)this.rankMap.get(Integer.valueOf(id))).put(Integer.valueOf(divineRewardBean.getNum()), Integer.valueOf(rid)); }
/*    */        }
/*    */   
/*    */   }
/*    */   
/*    */   public int getRankSize() {
/* 49 */     return this.rankSize;
/*    */   }
/*    */   
/*    */   public int getPerTimes() {
/* 53 */     return this.perTimes;
/*    */   }
/*    */   
/*    */   public Map<Integer, Map<Integer, Integer>> getSameNumMap() {
/* 57 */     return this.sameNumMap;
/*    */   }
/*    */   
/*    */   public Map<Integer, Map<Integer, Integer>> getRankMap() {
/* 61 */     return this.rankMap;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\config\parameter\DivineParameter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */