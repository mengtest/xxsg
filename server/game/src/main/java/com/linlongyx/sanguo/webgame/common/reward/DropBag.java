/*     */ package com.linlongyx.sanguo.webgame.common.reward;
/*     */ 
/*     */ import com.linlongyx.sanguo.webgame.common.util.RandUtil;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.DropBagBean;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.Reward;
/*     */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class DropBag
/*     */ {
/*     */   private static final int DROP_BAG_TYPE_WEIGHT = 0;
/*     */   private static final int DROP_BAG_TYPE_PERCENT = 1;
/*     */   private static final int DROG_BAG_TYPE_ALL = 2;
/*     */   
/*     */   public static void getDropReward(int dropId, List<Reward> rewards) {
/*  30 */     DropBagBean dropBag = (DropBagBean)JsonTableService.getJsonData(dropId, DropBagBean.class);
/*  31 */     if (null == dropBag)
/*     */       return; 
/*  33 */     int times = dropBag.getTimes();
/*  34 */     if (times == 0)
/*  35 */       times = 1; 
/*  36 */     getDropReward(dropId, rewards, times);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void getDropReward(int dropId, List<Reward> rewards, int times) {
/*  46 */     DropBagBean dropBag = (DropBagBean)JsonTableService.getJsonData(dropId, DropBagBean.class);
/*  47 */     if (null == dropBag) {
/*     */       return;
/*     */     }
/*  50 */     int repeatable = dropBag.getRepeatable();
/*  51 */     int type = dropBag.getType();
/*     */     
/*  53 */     int rand = dropBag.getProb();
/*  54 */     if (dropBag.getType() == 1 && rand != 0 && !RandUtil.isRandTrue(rand)) {
/*     */       return;
/*     */     }
/*  57 */     List<DropBagBean.DropRewardBean> list = new ArrayList<>();
/*  58 */     for (int i = 0; i < times; i++) {
/*  59 */       if (2 == type) {
/*  60 */         for (DropBagBean.DropRewardBean rewardBean : dropBag.getDropReward()) {
/*  61 */           Reward reward = new Reward();
/*  62 */           reward.id = rewardBean.getId();
/*  63 */           reward.type = (byte)rewardBean.getType();
/*  64 */           reward.num = rewardBean.getNum();
/*  65 */           rewards.add(reward);
/*     */         } 
/*     */         
/*     */         return;
/*     */       } 
/*  70 */       if (repeatable == 0) {
/*  71 */         DropBagBean.DropRewardBean bean = randReward(dropBag.getDropReward(), list, type);
/*  72 */         if (null != bean) {
/*  73 */           list.add(bean);
/*     */         }
/*     */       } else {
/*  76 */         DropBagBean.DropRewardBean bean = randReward(dropBag.getDropReward(), null, type);
/*  77 */         if (null != bean) {
/*  78 */           list.add(bean);
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/*  83 */     for (DropBagBean.DropRewardBean bean : list) {
/*  84 */       Reward reward = new Reward();
/*  85 */       reward.type = (byte)bean.getType();
/*  86 */       reward.id = bean.getId();
/*  87 */       reward.num = bean.getNum();
/*  88 */       rewards.add(reward);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void getDropReward(int dropId, RewardStruct struct) {
/*  98 */     DropBagBean dropBag = (DropBagBean)JsonTableService.getJsonData(dropId, DropBagBean.class);
/*  99 */     if (null == dropBag)
/*     */       return; 
/* 101 */     int times = dropBag.getTimes();
/* 102 */     if (times == 0) {
/* 103 */       times = 1;
/*     */     }
/* 105 */     int repeatable = dropBag.getRepeatable();
/* 106 */     int type = dropBag.getType();
/*     */     
/* 108 */     int rand = dropBag.getProb();
/* 109 */     if (rand != 0 && !RandUtil.isRandTrue(rand)) {
/*     */       return;
/*     */     }
/* 112 */     List<DropBagBean.DropRewardBean> list = new ArrayList<>();
/* 113 */     for (int i = 0; i < times; i++) {
/* 114 */       if (2 == type) {
/* 115 */         for (DropBagBean.DropRewardBean rewardBean : dropBag.getDropReward()) {
/* 116 */           struct.addReward((byte)rewardBean.getType(), rewardBean.getId(), rewardBean.getNum());
/*     */         }
/*     */         
/*     */         return;
/*     */       } 
/* 121 */       if (repeatable == 0) {
/* 122 */         DropBagBean.DropRewardBean bean = randReward(dropBag.getDropReward(), list, type);
/* 123 */         if (null != bean) {
/* 124 */           list.add(bean);
/*     */         }
/*     */       } else {
/* 127 */         DropBagBean.DropRewardBean bean = randReward(dropBag.getDropReward(), null, type);
/* 128 */         if (null != bean) {
/* 129 */           list.add(bean);
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 134 */     for (DropBagBean.DropRewardBean bean : list) {
/* 135 */       struct.addReward((byte)bean.getType(), bean.getId(), bean.getNum());
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private static DropBagBean.DropRewardBean randReward(List<DropBagBean.DropRewardBean> rewardBeanList, List<DropBagBean.DropRewardBean> excludeList, int type) {
/* 141 */     List<DropBagBean.DropRewardBean> list = new ArrayList<>();
/* 142 */     for (DropBagBean.DropRewardBean bean : rewardBeanList) {
/* 143 */       if (null != excludeList && excludeList.contains(bean))
/*     */         continue; 
/* 145 */       list.add(bean);
/*     */     } 
/*     */     
/* 148 */     int total = total(list);
/* 149 */     if (type == 1) {
/* 150 */       total = 10000;
/*     */     }
/* 152 */     else if (type > 100) {
/* 153 */       total = type;
/*     */     } 
/*     */     
/* 156 */     return randOne(list, total);
/*     */   }
/*     */   
/*     */   private static int total(List<DropBagBean.DropRewardBean> rewardBeanList) {
/* 160 */     int total = 0;
/* 161 */     for (DropBagBean.DropRewardBean bean : rewardBeanList) {
/* 162 */       total += bean.getProb();
/*     */     }
/* 164 */     return total;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static DropBagBean.DropRewardBean randOne(List<DropBagBean.DropRewardBean> rewardBeanList, int randBase) {
/* 174 */     int randNum = RandUtil.randNum(1, randBase);
/* 175 */     int total = 0;
/* 176 */     for (DropBagBean.DropRewardBean bean : rewardBeanList) {
/* 177 */       total += bean.getProb();
/* 178 */       if (randNum <= total)
/* 179 */         return bean; 
/*     */     } 
/* 181 */     return null;
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\common\reward\DropBag.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */