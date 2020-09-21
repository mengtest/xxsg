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
/*     */ public class RewardStruct
/*     */ {
/*  16 */   public Map<Integer, Long> currencys = new HashMap<>();
/*  17 */   public Map<Integer, Long> items = new HashMap<>();
/*  18 */   public Map<Integer, Long> timings = new HashMap<>();
/*  19 */   public Map<Integer, Long> bests = new HashMap<>();
/*  20 */   public Map<Integer, Long> drops = new HashMap<>();
/*  21 */   public Map<Integer, Long> runes = new HashMap<>();
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
/*  33 */     } else if (9 == reward.type) {
/*  34 */       add(this.timings, reward.id, reward.num);
/*  35 */     } else if (8 == reward.type) {
/*  36 */       add(this.bests, reward.id, reward.num);
/*  37 */     } else if (7 == reward.type) {
/*  38 */       add(this.runes, reward.id, reward.num);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addReward(byte type, int id, int num) {
/*  46 */     if (1 == type) {
/*  47 */       add(this.currencys, id, num);
/*  48 */     } else if (2 == type) {
/*  49 */       add(this.items, id, num);
/*  50 */     } else if (3 == type) {
/*  51 */       add(this.drops, id, num);
/*  52 */     } else if (9 == type) {
/*  53 */       add(this.timings, id, num);
/*  54 */     } else if (8 == type) {
/*  55 */       add(this.bests, id, num);
/*  56 */     } else if (7 == type) {
/*  57 */       add(this.runes, id, num);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addReward(List<Reward> rewards) {
/*  66 */     for (Reward reward : rewards) {
/*  67 */       addReward(reward);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addReward(RewardStruct struct) {
/*  76 */     List<Reward> rewards = new ArrayList<>();
/*  77 */     struct.getRewards(rewards);
/*  78 */     addReward(rewards);
/*     */   }
/*     */ 
/*     */   
/*     */   private static void add(Map<Integer, Long> map, int key, long value) {
/*  83 */     if (map.containsKey(Integer.valueOf(key))) {
/*  84 */       long val = ((Long)map.get(Integer.valueOf(key))).longValue();
/*  85 */       map.put(Integer.valueOf(key), Long.valueOf(val + value));
/*     */       return;
/*     */     } 
/*  88 */     map.put(Integer.valueOf(key), Long.valueOf(value));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void getRewards(List<Reward> rewardList) {
/*  96 */     for (Map.Entry<Integer, Long> entry : this.currencys.entrySet()) {
/*  97 */       Reward reward = new Reward();
/*  98 */       reward.type = 1;
/*  99 */       reward.id = ((Integer)entry.getKey()).intValue();
/* 100 */       reward.num = ((Long)entry.getValue()).longValue();
/* 101 */       rewardList.add(reward);
/*     */     } 
/*     */     
/* 104 */     for (Map.Entry<Integer, Long> entry : this.items.entrySet()) {
/* 105 */       Reward reward = new Reward();
/* 106 */       reward.type = 2;
/* 107 */       reward.id = ((Integer)entry.getKey()).intValue();
/* 108 */       reward.num = ((Long)entry.getValue()).longValue();
/* 109 */       rewardList.add(reward);
/*     */     } 
/*     */     
/* 112 */     for (Map.Entry<Integer, Long> entry : this.drops.entrySet()) {
/* 113 */       Reward reward = new Reward();
/* 114 */       reward.type = 3;
/* 115 */       reward.id = ((Integer)entry.getKey()).intValue();
/* 116 */       reward.num = ((Long)entry.getValue()).longValue();
/* 117 */       rewardList.add(reward);
/*     */     } 
/*     */     
/* 120 */     for (Map.Entry<Integer, Long> entry : this.bests.entrySet()) {
/* 121 */       Reward reward = new Reward();
/* 122 */       reward.type = 8;
/* 123 */       reward.id = ((Integer)entry.getKey()).intValue();
/* 124 */       reward.num = ((Long)entry.getValue()).longValue();
/* 125 */       rewardList.add(reward);
/*     */     } 
/*     */     
/* 128 */     for (Map.Entry<Integer, Long> entry : this.timings.entrySet()) {
/* 129 */       Reward reward = new Reward();
/* 130 */       reward.type = 9;
/* 131 */       reward.id = ((Integer)entry.getKey()).intValue();
/* 132 */       reward.num = ((Long)entry.getValue()).longValue();
/* 133 */       rewardList.add(reward);
/*     */     } 
/*     */     
/* 136 */     for (Map.Entry<Integer, Long> entry : this.runes.entrySet()) {
/* 137 */       Reward reward = new Reward();
/* 138 */       reward.type = 7;
/* 139 */       reward.id = ((Integer)entry.getKey()).intValue();
/* 140 */       reward.num = ((Long)entry.getValue()).longValue();
/* 141 */       rewardList.add(reward);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\common\reward\RewardStruct.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */