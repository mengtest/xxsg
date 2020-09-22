/*     */ package linlongyx.sanguo.webgame.common.util;
/*     */ 
/*     */ import linlongyx.sanguo.webgame.config.bean.PetBean;
/*     */ import linlongyx.sanguo.webgame.config.bean.RewardBean;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class FinanceUtil
/*     */ {
/*     */   public static ArrayList<Reward> transform(List<RewardBean> rewardBeanList) {
/*  26 */     ArrayList<Reward> rewards = new ArrayList<>();
/*  27 */     if (null == rewardBeanList) {
/*  28 */       return rewards;
/*     */     }
/*  30 */     for (RewardBean rewardBean : rewardBeanList) {
/*  31 */       Reward reward = new Reward();
/*  32 */       reward.type = (byte)rewardBean.getType();
/*  33 */       reward.id = rewardBean.getId();
/*  34 */       reward.num = rewardBean.getNum();
/*  35 */       rewards.add(reward);
/*     */     } 
/*  37 */     return rewards;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static ArrayList<Reward> transformRatio(List<RewardBean> rewardBeanList, int num) {
/*  47 */     ArrayList<Reward> rewards = new ArrayList<>();
/*  48 */     if (null == rewardBeanList) {
/*  49 */       return rewards;
/*     */     }
/*  51 */     for (RewardBean rewardBean : rewardBeanList) {
/*  52 */       Reward reward = new Reward();
/*  53 */       reward.type = (byte)rewardBean.getType();
/*  54 */       reward.id = rewardBean.getId();
/*  55 */       reward.num = (long)Math.ceil((rewardBean.getNum() * num) / 10000.0D);
/*  56 */       rewards.add(reward);
/*     */     } 
/*  58 */     return rewards;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static ArrayList<Reward> transformReward(Reward reward, int count) {
/*  69 */     ArrayList<Reward> rewards = new ArrayList<>();
/*  70 */     if (null == reward) {
/*  71 */       return rewards;
/*     */     }
/*  73 */     Reward reward1 = new Reward();
/*  74 */     reward1.type = reward.type;
/*  75 */     reward1.id = reward.id;
/*  76 */     reward.num *= count;
/*  77 */     rewards.add(reward1);
/*  78 */     return rewards;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static ArrayList<Reward> transformWarPet(List<PetBean.StarBean.CardBean> rewardBeanList) {
/*  89 */     ArrayList<Reward> rewards = new ArrayList<>();
/*  90 */     if (null == rewardBeanList) {
/*  91 */       return rewards;
/*     */     }
/*  93 */     for (PetBean.StarBean.CardBean rewardBean : rewardBeanList) {
/*  94 */       Reward reward = new Reward();
/*  95 */       reward.type = (byte)rewardBean.getType();
/*  96 */       reward.id = rewardBean.getId();
/*  97 */       reward.num = rewardBean.getNum();
/*  98 */       rewards.add(reward);
/*     */     } 
/* 100 */     return rewards;
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
/*     */   
/*     */   public static ArrayList<Reward> transformPiceReward(ArrayList<RewardBean> reward, int count) {
/* 113 */     ArrayList<Reward> rewards = new ArrayList<>();
/* 114 */     if (null == reward) {
/* 115 */       return rewards;
/*     */     }
/* 117 */     for (RewardBean rewardBean : reward) {
/* 118 */       Reward reward1 = new Reward();
/* 119 */       reward1.type = (byte)rewardBean.getType();
/* 120 */       reward1.id = rewardBean.getId();
/* 121 */       reward1.num = (rewardBean.getNum() * count);
/* 122 */       rewards.add(reward1);
/*     */     } 
/* 124 */     return rewards;
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
/*     */   public static Reward transformNewReward(Reward reward, int count) {
/* 136 */     Reward reward1 = new Reward();
/* 137 */     reward1.type = reward.type;
/* 138 */     reward1.id = reward.id;
/* 139 */     reward.num *= count;
/* 140 */     return reward1;
/*     */   }
/*     */   
/*     */   public static ArrayList<Reward> transform2(ArrayList<RewardBean> costBeans, int num) {
/* 144 */     ArrayList<Reward> rewards = new ArrayList<>();
/* 145 */     if (null == costBeans) {
/* 146 */       return rewards;
/*     */     }
/* 148 */     for (RewardBean costBean : costBeans) {
/* 149 */       Reward reward = new Reward();
/* 150 */       reward.type = (byte)costBean.getType();
/* 151 */       reward.id = costBean.getId();
/* 152 */       reward.num = (costBean.getNum() * num);
/* 153 */       rewards.add(reward);
/*     */     } 
/* 155 */     return rewards;
/*     */   }
/*     */   
/*     */   public static Reward transform4(RewardBean rewardBean, int param) {
/* 159 */     Reward reward = new Reward();
/* 160 */     reward.type = (byte)rewardBean.getType();
/* 161 */     reward.id = rewardBean.getId();
/* 162 */     reward.num = (rewardBean.getNum() * param);
/* 163 */     return reward;
/*     */   }
/*     */   
/*     */   public static void overlyingMap(Map<Long, Long> map, ArrayList<Reward> rewards) {
/* 167 */     long para = 1000000000L;
/* 168 */     for (Reward reward : rewards) {
/* 169 */       long key = reward.type * para + reward.id;
/* 170 */       map.putIfAbsent(Long.valueOf(key), Long.valueOf(0L));
/* 171 */       map.put(Long.valueOf(key), Long.valueOf(((Long)map.get(Long.valueOf(key))).longValue() + reward.num));
/*     */     } 
/*     */   }
/*     */   
/*     */   public static ArrayList<Reward> overlyingReward(Map<Long, Long> map) {
/* 176 */     long para = 1000000000L;
/* 177 */     ArrayList<Reward> rewards = new ArrayList<>();
/* 178 */     for (Map.Entry<Long, Long> entry : map.entrySet()) {
/* 179 */       Reward reward = new Reward();
/* 180 */       reward.type = (byte)(int)(((Long)entry.getKey()).longValue() / para);
/* 181 */       reward.id = (int)(((Long)entry.getKey()).longValue() % para);
/* 182 */       reward.num = ((Long)entry.getValue()).longValue();
/* 183 */       rewards.add(reward);
/*     */     } 
/* 185 */     return rewards;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static ArrayList<Reward> transform(RewardBean rewardBean) {
/* 195 */     ArrayList<Reward> rewards = new ArrayList<>();
/* 196 */     Reward reward = new Reward();
/* 197 */     reward.type = (byte)rewardBean.getType();
/* 198 */     reward.id = rewardBean.getId();
/* 199 */     reward.num = rewardBean.getNum();
/* 200 */     rewards.add(reward);
/* 201 */     return rewards;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Reward transform(int type, int id, int num) {
/* 209 */     Reward reward = new Reward();
/* 210 */     reward.type = (byte)type;
/* 211 */     reward.id = id;
/* 212 */     reward.num = num;
/* 213 */     return reward;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static ArrayList<Reward> handleRepeat(List<Reward> rewards) {
/* 223 */     Map<Integer, Reward> map = new HashMap<>();
/* 224 */     for (Reward reward : rewards) {
/* 225 */       Reward r = map.getOrDefault(Integer.valueOf(reward.id), new Reward());
/* 226 */       r.type = reward.type;
/* 227 */       r.id = reward.id;
/* 228 */       r.num += reward.num;
/* 229 */       map.put(Integer.valueOf(reward.id), r);
/*     */     } 
/* 231 */     return new ArrayList<>(map.values());
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\sanguo\webgame\commo\\util\FinanceUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */