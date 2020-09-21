/*     */ package com.linlongyx.sanguo.webgame.config.parameter;
/*     */ 
/*     */ import com.linlongyx.sanguo.webgame.config.bean.LuckyTurntableBean;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.LuckylistBean;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.ParameterBean;
/*     */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*     */ import java.util.Collections;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class LuckyTurntableParameter extends AbstractParameter {
/*     */   private int firstDrawReward;
/*     */   private int oneDrawCoins;
/*     */   private int tenDrawCoins;
/*     */   private int oneDrawLuckyPoint;
/*     */   private int tenDrawLuckyPoint;
/*     */   private Map<Integer, Integer> totalProb;
/*     */   
/*     */   public LuckyTurntableParameter() {
/*  21 */     super(35);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  29 */     this.totalProb = new HashMap<>();
/*  30 */     this.doubleTotalProb = new HashMap<>();
/*  31 */     this.aheadTotalProb = new HashMap<>();
/*  32 */     this.aheadDoubleTotalProb = new HashMap<>();
/*     */   }
/*     */   private Map<Integer, Integer> doubleTotalProb;
/*     */   private Map<Integer, Integer> aheadTotalProb;
/*     */   private Map<Integer, Integer> aheadDoubleTotalProb;
/*     */   
/*     */   void init(ParameterBean bean) {
/*  39 */     this.firstDrawReward = Integer.parseInt(((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(1))).getValue());
/*  40 */     this.oneDrawCoins = Integer.parseInt(((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(2))).getValue());
/*  41 */     this.tenDrawCoins = Integer.parseInt(((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(3))).getValue());
/*  42 */     this.oneDrawLuckyPoint = Integer.parseInt(((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(4))).getValue());
/*  43 */     this.tenDrawLuckyPoint = Integer.parseInt(((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(5))).getValue());
/*  44 */     this.aheadTimes = Integer.parseInt(((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(6))).getValue());
/*  45 */     this.petAheadTimes = Integer.parseInt(((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(7))).getValue());
/*  46 */     this.rankSize = Integer.parseInt(((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(8))).getValue());
/*  47 */     for (Iterator<Integer> iterator = JsonTableService.getJsonTableKey(LuckylistBean.class).iterator(); iterator.hasNext(); ) { int tableId = ((Integer)iterator.next()).intValue();
/*  48 */       int tmpTotalRrob = 0, tmpDoubleTotalProb = 0, tmpAheadTotalProb = 0, tmpAheadDoubleTotalProb = 0;
/*  49 */       LuckylistBean luckylistBean = (LuckylistBean)JsonTableService.getJsonData(tableId, LuckylistBean.class);
/*  50 */       for (Iterator<Integer> iterator1 = luckylistBean.getActReward().iterator(); iterator1.hasNext(); ) { int id = ((Integer)iterator1.next()).intValue();
/*  51 */         LuckyTurntableBean luckyTurntableBean = (LuckyTurntableBean)JsonTableService.getJsonData(id, LuckyTurntableBean.class);
/*  52 */         tmpTotalRrob += luckyTurntableBean.getProbability();
/*  53 */         tmpDoubleTotalProb += luckyTurntableBean.getDoubleProbability();
/*  54 */         tmpAheadTotalProb += luckyTurntableBean.getProbability_ten();
/*  55 */         tmpAheadDoubleTotalProb += luckyTurntableBean.getDoubleProbability_ten(); }
/*     */       
/*  57 */       this.totalProb.put(Integer.valueOf(tableId), Integer.valueOf(tmpTotalRrob));
/*  58 */       this.doubleTotalProb.put(Integer.valueOf(tableId), Integer.valueOf(tmpDoubleTotalProb));
/*  59 */       this.aheadTotalProb.put(Integer.valueOf(tableId), Integer.valueOf(tmpAheadTotalProb));
/*  60 */       this.aheadDoubleTotalProb.put(Integer.valueOf(tableId), Integer.valueOf(tmpAheadDoubleTotalProb)); }
/*     */   
/*     */   }
/*     */   private int aheadTimes; private int petAheadTimes; private int rankSize;
/*     */   public int getFirstDrawReward() {
/*  65 */     return this.firstDrawReward;
/*     */   }
/*     */   
/*     */   public int getOneDrawCoins() {
/*  69 */     return this.oneDrawCoins;
/*     */   }
/*     */   
/*     */   public int getTenDrawCoins() {
/*  73 */     return this.tenDrawCoins;
/*     */   }
/*     */   
/*     */   public int getOneDrawLuckyPoint() {
/*  77 */     return this.oneDrawLuckyPoint;
/*     */   }
/*     */   
/*     */   public int getTenDrawLuckyPoint() {
/*  81 */     return this.tenDrawLuckyPoint;
/*     */   }
/*     */   
/*     */   public int getPetAheadTimes() {
/*  85 */     return this.petAheadTimes;
/*     */   }
/*     */   
/*     */   public Map<Integer, Integer> getTotalProb() {
/*  89 */     return Collections.unmodifiableMap(this.totalProb);
/*     */   }
/*     */   
/*     */   public Map<Integer, Integer> getDoubleTotalProb() {
/*  93 */     return Collections.unmodifiableMap(this.doubleTotalProb);
/*     */   }
/*     */   
/*     */   public Map<Integer, Integer> getAheadTotalProb() {
/*  97 */     return Collections.unmodifiableMap(this.aheadTotalProb);
/*     */   }
/*     */   
/*     */   public int getAheadTimes() {
/* 101 */     return this.aheadTimes;
/*     */   }
/*     */   
/*     */   public Map<Integer, Integer> getAheadDoubleTotalProb() {
/* 105 */     return Collections.unmodifiableMap(this.aheadDoubleTotalProb);
/*     */   }
/*     */   
/*     */   public int getRankSize() {
/* 109 */     return this.rankSize;
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\config\parameter\LuckyTurntableParameter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */