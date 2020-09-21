/*     */ package com.linlongyx.sanguo.webgame.config.parameter;
/*     */ 
/*     */ import com.linlongyx.sanguo.webgame.config.bean.ParameterBean;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.VipGiftBean;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.VipWeekBean;
/*     */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class WelfareParameter
/*     */   extends AbstractParameter {
/*     */   public WelfareParameter() {
/*  16 */     super(19);
/*     */     
/*  18 */     this.vipWeekConfig = new HashMap<>();
/*  19 */     this.vipWeekLevel = new ArrayList<>();
/*  20 */     this.vipGiftConfig = new HashMap<>();
/*     */   }
/*     */   
/*     */   private Map<Integer, List<Integer>> vipWeekConfig;
/*     */   private List<Integer> vipWeekLevel;
/*     */   private Map<Integer, List<Integer>> vipGiftConfig;
/*     */   private int normalRate;
/*     */   private int specialRate;
/*     */   private int normalCCY;
/*     */   private int specialCCY;
/*     */   private int weekCCY;
/*     */   private int offlineDay;
/*     */   
/*     */   public List<Integer> getVipWeekList(int level) {
/*  34 */     return this.vipWeekConfig.get(Integer.valueOf(level));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List<Integer> getVipGiftList(int giftLevel) {
/*  42 */     return this.vipGiftConfig.get(Integer.valueOf(giftLevel));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getVipWeekLevel(int playerLevel) {
/*  51 */     int selectLv = ((Integer)this.vipWeekLevel.get(0)).intValue();
/*  52 */     for (Integer level : this.vipWeekLevel) {
/*  53 */       selectLv = level.intValue();
/*  54 */       if (playerLevel <= level.intValue()) {
/*     */         break;
/*     */       }
/*     */     } 
/*  58 */     return selectLv;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getNormalRate() {
/*  68 */     return this.normalRate;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getSpecialRate() {
/*  76 */     return this.specialRate;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getNormalCCY() {
/*  84 */     return this.normalCCY;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getSpecialCCY() {
/*  92 */     return this.specialCCY;
/*     */   }
/*     */   
/*     */   public int getWeekCCY() {
/*  96 */     return this.weekCCY;
/*     */   }
/*     */   
/*     */   private void initVipWeek() {
/* 100 */     this.vipWeekConfig.clear();
/* 101 */     Map<Integer, Object> map = JsonTableService.getJsonTable(VipWeekBean.class);
/* 102 */     for (Map.Entry<Integer, Object> entry : map.entrySet()) {
/* 103 */       VipWeekBean vipWeekBean = (VipWeekBean)entry.getValue();
/* 104 */       List<Integer> list = this.vipWeekConfig.getOrDefault(Integer.valueOf(vipWeekBean.getLevel()), new ArrayList<>());
/* 105 */       list.add(Integer.valueOf(vipWeekBean.getPackageType()));
/* 106 */       this.vipWeekConfig.put(Integer.valueOf(vipWeekBean.getLevel()), list);
/*     */     } 
/* 108 */     this.vipWeekLevel.clear();
/* 109 */     this.vipWeekLevel.addAll(this.vipWeekConfig.keySet());
/*     */     
/* 111 */     Collections.sort(this.vipWeekLevel);
/*     */   }
/*     */   
/*     */   private void initVipGift() {
/* 115 */     this.vipGiftConfig.clear();
/* 116 */     Map<Integer, Object> map = JsonTableService.getJsonTable(VipGiftBean.class);
/* 117 */     for (Map.Entry<Integer, Object> entry : map.entrySet()) {
/* 118 */       VipGiftBean vipGiftBean = (VipGiftBean)entry.getValue();
/* 119 */       List<Integer> list = this.vipGiftConfig.getOrDefault(Integer.valueOf(vipGiftBean.getVipLevel()), new ArrayList<>());
/* 120 */       list.add(Integer.valueOf(vipGiftBean.getPackageType()));
/* 121 */       this.vipGiftConfig.put(Integer.valueOf(vipGiftBean.getVipLevel()), list);
/*     */     } 
/*     */   }
/*     */   
/*     */   void init(ParameterBean bean) {
/* 126 */     this.normalRate = Integer.valueOf(((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(1))).getValue()).intValue();
/* 127 */     this.specialRate = Integer.valueOf(((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(2))).getValue()).intValue();
/* 128 */     this.normalCCY = Integer.valueOf(((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(3))).getValue()).intValue();
/* 129 */     this.specialCCY = Integer.valueOf(((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(4))).getValue()).intValue();
/* 130 */     this.weekCCY = Integer.valueOf(((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(5))).getValue()).intValue();
/* 131 */     this.offlineDay = Integer.valueOf(((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(6))).getValue()).intValue();
/* 132 */     initVipWeek();
/* 133 */     initVipGift();
/*     */   }
/*     */   
/*     */   public int getOfflineDay() {
/* 137 */     return this.offlineDay;
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\config\parameter\WelfareParameter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */