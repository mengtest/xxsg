/*     */ package com.linlongyx.sanguo.webgame.config.parameter;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.GeneralInsBean;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.ParameterBean;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import java.util.TreeMap;
/*     */ import org.apache.commons.collections.map.HashedMap;
/*     */ 
/*     */ public class GeneralParameter extends AbstractParameter {
/*     */   private Map<Integer, Map<Integer, Integer>> configs;
/*     */   private Map<Integer, Integer> maxChapter;
/*     */   private int maxOrder;
/*     */   private int restoreCd;
/*     */   
/*     */   public GeneralParameter() {
/*  17 */     super(12);
/*     */ 
/*     */     
/*  20 */     this.configs = new HashMap<>();
/*  21 */     this.maxChapter = new HashMap<>();
/*     */ 
/*     */     
/*  24 */     this.buyCost = new TreeMap<>();
/*     */     
/*  26 */     this.resetCost = new HashMap<>();
/*     */     
/*  28 */     this.levelConfig = (Map<Integer, Map<Integer, Set<Integer>>>)new HashedMap();
/*     */   } private Map<Integer, Integer> buyCost; private int maxBuyTime; private Map<Integer, Integer> resetCost; private int maxResetTime; private Map<Integer, Map<Integer, Set<Integer>>> levelConfig;
/*     */   private void initGeneral() {
/*  31 */     this.configs.clear();
/*  32 */     Map<Integer, Object> map = JsonTableService.getJsonTable(GeneralInsBean.class);
/*  33 */     for (Map.Entry<Integer, Object> entry : map.entrySet()) {
/*  34 */       GeneralInsBean generalInsBean = (GeneralInsBean)entry.getValue();
/*  35 */       Map<Integer, Integer> temp = this.configs.getOrDefault(Integer.valueOf(generalInsBean.getChapter()), new HashMap<>());
/*  36 */       int point = generalInsBean.getCheckPoint();
/*  37 */       temp.put(Integer.valueOf(point), Integer.valueOf(generalInsBean.getInsId()));
/*  38 */       this.configs.put(Integer.valueOf(generalInsBean.getChapter()), temp);
/*     */       
/*  40 */       Map<Integer, Set<Integer>> levelTemp = this.levelConfig.getOrDefault(Integer.valueOf(generalInsBean.getChapter()), new HashMap<>());
/*  41 */       if (!levelTemp.containsKey(Integer.valueOf(generalInsBean.getDifficult()))) {
/*  42 */         levelTemp.put(Integer.valueOf(generalInsBean.getDifficult()), new HashSet<>());
/*     */       }
/*  44 */       Set<Integer> levelIns = levelTemp.get(Integer.valueOf(generalInsBean.getDifficult()));
/*  45 */       levelIns.add(Integer.valueOf(generalInsBean.getInsId()));
/*  46 */       levelTemp.put(Integer.valueOf(generalInsBean.getDifficult()), levelIns);
/*  47 */       this.levelConfig.put(Integer.valueOf(generalInsBean.getChapter()), levelTemp);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Map<Integer, Integer> getPointIdMap(int chapter) {
/*  60 */     return this.configs.get(Integer.valueOf(chapter));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Map<Integer, Map<Integer, Integer>> getConfigs() {
/*  69 */     return this.configs;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Map<Integer, Integer> getMaxChapter() {
/*  78 */     return this.maxChapter;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMaxOrder() {
/*  87 */     return this.maxOrder;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getRestoreCd() {
/*  96 */     return this.restoreCd;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMaxBuyTime() {
/* 105 */     return this.maxBuyTime;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getBuyCost(int time) {
/* 115 */     int target = 1;
/* 116 */     for (Integer key : this.buyCost.keySet()) {
/* 117 */       if (time >= key.intValue()) {
/* 118 */         target = key.intValue();
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 123 */     return ((Integer)this.buyCost.get(Integer.valueOf(target))).intValue();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getResetCost(int time) {
/* 133 */     if (time >= this.maxResetTime) {
/* 134 */       return ((Integer)this.resetCost.get(Integer.valueOf(this.maxResetTime))).intValue();
/*     */     }
/* 136 */     return ((Integer)this.resetCost.get(Integer.valueOf(time))).intValue();
/*     */   }
/*     */ 
/*     */   
/*     */   void init(ParameterBean bean) {
/* 141 */     this.maxOrder = Integer.valueOf(((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(1))).getValue()).intValue();
/* 142 */     this.restoreCd = Integer.valueOf(((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(2))).getValue()).intValue();
/*     */     
/* 144 */     this.buyCost.clear();
/* 145 */     String[] strings = ((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(3))).getValue().split(";");
/* 146 */     this.maxBuyTime = getMaxTime(strings, this.buyCost);
/*     */     
/* 148 */     this.resetCost.clear();
/* 149 */     strings = ((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(4))).getValue().split(";");
/* 150 */     this.maxResetTime = getMaxTime(strings, this.resetCost);
/*     */     
/* 152 */     this.maxChapter.clear();
/* 153 */     String[] string6 = ((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(6))).getValue().split(";");
/* 154 */     for (String str : string6) {
/* 155 */       String[] strings1 = str.split(":");
/* 156 */       this.maxChapter.put(Integer.valueOf(Integer.parseInt(strings1[0])), Integer.valueOf(Integer.parseInt(strings1[1])));
/*     */     } 
/* 158 */     initGeneral();
/*     */   }
/*     */   
/*     */   private int getMaxTime(String[] strings, Map<Integer, Integer> resetCost) {
/* 162 */     int maxTime = 0;
/* 163 */     for (String string : strings) {
/* 164 */       String[] strings1 = string.split(":");
/* 165 */       int time = Integer.valueOf(strings1[0]).intValue();
/* 166 */       resetCost.put(Integer.valueOf(time), Integer.valueOf(strings1[1]));
/* 167 */       if (time > maxTime) {
/* 168 */         maxTime = time;
/*     */       }
/*     */     } 
/* 171 */     return maxTime;
/*     */   }
/*     */   
/*     */   public Map<Integer, Map<Integer, Set<Integer>>> getLevelConfig() {
/* 175 */     return this.levelConfig;
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\config\parameter\GeneralParameter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */