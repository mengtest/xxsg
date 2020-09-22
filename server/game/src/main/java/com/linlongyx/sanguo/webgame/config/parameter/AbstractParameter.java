/*     */ package com.linlongyx.sanguo.webgame.config.parameter;
/*     */ 
/*     */ import com.linlongyx.sanguo.webgame.config.bean.ParameterBean;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.Reward;
/*     */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class AbstractParameter
/*     */ {
/*     */   public static final int TYPE_1 = 1;
/*     */   public static final int TYPE_2 = 2;
/*     */   public static final int TYPE_3 = 3;
/*     */   public static final int TYPE_4 = 4;
/*     */   public static final int TYPE_5 = 5;
/*     */   public static final int TYPE_6 = 6;
/*     */   public static final int TYPE_7 = 7;
/*     */   public static final int TYPE_8 = 8;
/*     */   public static final int TYPE_9 = 9;
/*     */   public static final int TYPE_10 = 10;
/*     */   public static final int TYPE_11 = 11;
/*     */   public static final int TYPE_12 = 12;
/*     */   public static final int TYPE_13 = 13;
/*     */   public static final int TYPE_14 = 14;
/*     */   public static final int TYPE_15 = 15;
/*     */   public static final int TYPE_16 = 16;
/*     */   public static final int TYPE_17 = 17;
/*     */   public static final int TYPE_18 = 18;
/*     */   public static final int TYPE_19 = 19;
/*     */   public static final int TYPE_20 = 20;
/*     */   public static final int TYPE_21 = 21;
/*     */   public static final int TYPE_22 = 22;
/*     */   public static final int TYPE_23 = 23;
/*     */   public static final int TYPE_24 = 24;
/*     */   public static final int TYPE_25 = 25;
/*     */   public static final int TYPE_26 = 26;
/*     */   public static final int TYPE_27 = 27;
/*     */   public static final int TYPE_28 = 28;
/*     */   public static final int TYPE_29 = 29;
/*     */   public static final int TYPE_30 = 30;
/*     */   public static final int TYPE_31 = 31;
/*     */   public static final int TYPE_32 = 32;
/*     */   public static final int TYPE_33 = 33;
/*     */   public static final int TYPE_34 = 34;
/*     */   public static final int TYPE_35 = 35;
/*     */   public static final int TYPE_36 = 36;
/*     */   public static final int TYPE_37 = 37;
/*     */   public static final int TYPE_38 = 38;
/*     */   public static final int TYPE_39 = 39;
/*     */   public static final int TYPE_40 = 40;
/*     */   public static final int TYPE_41 = 41;
/*     */   public static final int TYPE_42 = 42;
/*     */   public static final int TYPE_43 = 43;
/*     */   public static final int TYPE_44 = 44;
/*     */   public static final int TYPE_45 = 45;
/*     */   public static final int TYPE_46 = 46;
/*     */   public static final int TYPE_47 = 47;
/*     */   public static final int TYPE_48 = 48;
/*     */   public static final int TYPE_49 = 49;
/*     */   public static final int TYPE_50 = 50;
/*     */   public static final int TYPE_51 = 51;
/*     */   public static final int TYPE_52 = 52;
/*     */   public static final int TYPE_53 = 53;
/*     */   public static final int TYPE_54 = 54;
/*     */   public static final int TYPE_55 = 55;
/*     */   public static final int TYPE_56 = 56;
/*     */   public static final int TYPE_57 = 57;
/*     */   public static final int TYPE_58 = 58;
/*     */   public static final int TYPE_59 = 59;
/*     */   protected int type;
/*     */   
/*     */   public AbstractParameter(int type) {
/*  80 */     this.type = type;
/*     */   }
/*     */   
/*     */   int getType() {
/*  84 */     return this.type;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void load() {
/*  91 */     ParameterBean parameterBean = (ParameterBean)JsonTableService.getJsonData(this.type, ParameterBean.class);
/*     */ 
/*     */ 
/*     */     
/*  95 */     init(parameterBean);
/*     */   }
/*     */   
/*     */   abstract void init(ParameterBean paramParameterBean);
/*     */   
/*     */   public void tranform(Reward reward, String[] strings1) {
/* 101 */     if (strings1.length == 3) {
/* 102 */       reward.type = Byte.parseByte(strings1[0]);
/* 103 */       reward.id = Integer.parseInt(strings1[1]);
/* 104 */       reward.num = Integer.parseInt(strings1[2]);
/*     */     } 
/*     */   }
/*     */   
/*     */   protected ArrayList<Reward> transformRewardList(String src) {
/* 109 */     String[] strings = src.split(";");
/* 110 */     ArrayList<Reward> rewards = new ArrayList<>();
/* 111 */     for (String string : strings) {
/* 112 */       Reward reward = new Reward();
/* 113 */       String[] strings2 = string.split(",");
/* 114 */       tranform(reward, strings2);
/* 115 */       rewards.add(reward);
/*     */     } 
/* 117 */     return rewards;
/*     */   }
/*     */   
/*     */   public Reward handleReward(ParameterBean bean, Map<Integer, Reward> map, int type) {
/* 121 */     String[] strings = ((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(type))).getValue().split(";");
/* 122 */     int max = 0;
/* 123 */     Reward maxReward = new Reward();
/* 124 */     for (String string : strings) {
/* 125 */       String[] strings1 = string.split(":");
/* 126 */       int times = Integer.parseInt(strings1[0]);
/* 127 */       Reward reward = new Reward();
/* 128 */       tranform(reward, strings1[1].split(","));
/* 129 */       map.put(Integer.valueOf(times), reward);
/* 130 */       if (max == 0 || max < times) {
/* 131 */         max = times;
/* 132 */         maxReward = reward;
/*     */       } 
/*     */     } 
/* 135 */     return maxReward;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List<Reward> getRewardList(ParameterBean bean, int type) {
/* 145 */     String[] strings = ((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(type))).getValue().split(";");
/* 146 */     List<Reward> rewards = new ArrayList<>();
/* 147 */     for (String string : strings) {
/* 148 */       String[] string1 = string.split(",");
/* 149 */       Reward reward = new Reward();
/* 150 */       reward.type = Byte.valueOf(string1[0]).byteValue();
/* 151 */       reward.id = Integer.valueOf(string1[1]).intValue();
/* 152 */       reward.num = Integer.valueOf(string1[2]).intValue();
/* 153 */       rewards.add(reward);
/*     */     } 
/* 155 */     return rewards;
/*     */   }
/*     */   
/*     */   public Reward getReward(String[] string1) {
/* 159 */     Reward reward = new Reward();
/* 160 */     reward.type = Byte.valueOf(string1[0]).byteValue();
/* 161 */     reward.id = Integer.valueOf(string1[1]).intValue();
/* 162 */     reward.num = Integer.valueOf(string1[2]).intValue();
/* 163 */     return reward;
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\config\parameter\AbstractParameter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */