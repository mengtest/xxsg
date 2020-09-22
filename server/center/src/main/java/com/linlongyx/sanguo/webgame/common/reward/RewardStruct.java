/*     */ package com.linlongyx.sanguo.webgame.common.reward;
/*     */ 
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.Reward;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class RewardStruct
/*     */ {
/*  17 */   public Map<Integer, Long> currencys = new HashMap<>();
/*  18 */   public Map<Integer, Long> items = new HashMap<>();
/*  19 */   public Map<Integer, Long> timings = new HashMap<>();
/*  20 */   public Map<Integer, Long> bests = new HashMap<>();
/*  21 */   public Map<Integer, Long> drops = new HashMap<>();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addReward(Reward reward) {
/*  27 */     if (1 == reward.type) {
/*  28 */       add(this.currencys, reward.id, reward.num);
/*  29 */     } else if (2 == reward.type) {
/*  30 */       add(this.items, reward.id, reward.num);
/*  31 */     } else if (3 == reward.type) {
/*  32 */       add(this.drops, reward.id, reward.num);
/*  33 */     } else if (7 == reward.type) {
/*  34 */       add(this.timings, reward.id, reward.num);
/*  35 */     } else if (8 == reward.type) {
/*  36 */       add(this.bests, reward.id, reward.num);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addReward(byte type, int id, int num) {
/*  44 */     if (1 == type) {
/*  45 */       add(this.currencys, id, num);
/*  46 */     } else if (2 == type) {
/*  47 */       add(this.items, id, num);
/*  48 */     } else if (3 == type) {
/*  49 */       add(this.drops, id, num);
/*  50 */     } else if (7 == type) {
/*  51 */       add(this.timings, id, num);
/*  52 */     } else if (8 == type) {
/*  53 */       add(this.bests, id, num);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addReward(List<Reward> rewards) {
/*  62 */     for (Reward reward : rewards) {
/*  63 */       addReward(reward);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addReward(RewardStruct struct) {
/*  72 */     List<Reward> rewards = new ArrayList<>();
/*  73 */     struct.getRewards(rewards);
/*  74 */     addReward(rewards);
/*     */   }
/*     */ 
/*     */   
/*     */   private static void add(Map<Integer, Long> map, int key, long value) {
/*  79 */     if (map.containsKey(Integer.valueOf(key))) {
/*  80 */       long val = ((Long)map.get(Integer.valueOf(key))).longValue();
/*  81 */       map.put(Integer.valueOf(key), Long.valueOf(val + value));
/*     */       return;
/*     */     } 
/*  84 */     map.put(Integer.valueOf(key), Long.valueOf(value));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void getRewards(List<Reward> rewardList) {
/*  92 */     for (Map.Entry<Integer, Long> entry : this.currencys.entrySet()) {
/*  93 */       Reward reward = new Reward();
/*  94 */       reward.type = 1;
/*  95 */       reward.id = ((Integer)entry.getKey()).intValue();
/*  96 */       reward.num = ((Long)entry.getValue()).longValue();
/*  97 */       rewardList.add(reward);
/*     */     } 
/*     */     
/* 100 */     for (Map.Entry<Integer, Long> entry : this.items.entrySet()) {
/* 101 */       Reward reward = new Reward();
/* 102 */       reward.type = 2;
/* 103 */       reward.id = ((Integer)entry.getKey()).intValue();
/* 104 */       reward.num = ((Long)entry.getValue()).longValue();
/* 105 */       rewardList.add(reward);
/*     */     } 
/*     */     
/* 108 */     for (Map.Entry<Integer, Long> entry : this.drops.entrySet()) {
/* 109 */       Reward reward = new Reward();
/* 110 */       reward.type = 3;
/* 111 */       reward.id = ((Integer)entry.getKey()).intValue();
/* 112 */       reward.num = ((Long)entry.getValue()).longValue();
/* 113 */       rewardList.add(reward);
/*     */     } 
/*     */     
/* 116 */     for (Map.Entry<Integer, Long> entry : this.bests.entrySet()) {
/* 117 */       Reward reward = new Reward();
/* 118 */       reward.type = 8;
/* 119 */       reward.id = ((Integer)entry.getKey()).intValue();
/* 120 */       reward.num = ((Long)entry.getValue()).longValue();
/* 121 */       rewardList.add(reward);
/*     */     } 
/*     */     
/* 124 */     for (Map.Entry<Integer, Long> entry : this.timings.entrySet()) {
/* 125 */       Reward reward = new Reward();
/* 126 */       reward.type = 7;
/* 127 */       reward.id = ((Integer)entry.getKey()).intValue();
/* 128 */       reward.num = ((Long)entry.getValue()).longValue();
/* 129 */       rewardList.add(reward);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\sanguo\webgame\common\reward\RewardStruct.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */