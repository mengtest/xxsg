/*     */ package com.linlongyx.sanguo.webgame.config.parameter;
/*     */ 
/*     */ import com.linlongyx.sanguo.webgame.config.bean.ArenaRuleBean;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.ParameterBean;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.Reward;
/*     */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class ArenaParameter
/*     */   extends AbstractParameter
/*     */ {
/*     */   private int maxReport;
/*     */   private int maxChallengeTime;
/*     */   
/*     */   public ArenaParameter() {
/*  17 */     super(17);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  24 */     this.buyCosts = new HashMap<>();
/*  25 */     this.maxBuyCost = new Reward();
/*  26 */     this.vipBuyTimes = new HashMap<>();
/*     */ 
/*     */     
/*  29 */     this.needRank = new HashMap<>();
/*     */   }
/*     */   private int defaultRank; private int refreshCD; private Map<Integer, Reward> buyCosts;
/*     */   private Reward maxBuyCost;
/*     */   private Map<Integer, Integer> vipBuyTimes;
/*     */   
/*     */   public int getMaxReport() {
/*  36 */     return this.maxReport;
/*     */   }
/*     */   
/*     */   private int challengeNum;
/*     */   private int rewardRank;
/*     */   private Map<Integer, Integer> needRank;
/*     */   
/*     */   public int getMaxChallengeTime() {
/*  44 */     return this.maxChallengeTime;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getDefaultRank() {
/*  52 */     return this.defaultRank;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getRefreshCD() {
/*  60 */     return this.refreshCD;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Reward getBuyCost(int times) {
/*  69 */     if (this.buyCosts.containsKey(Integer.valueOf(times))) {
/*  70 */       return this.buyCosts.get(Integer.valueOf(times));
/*     */     }
/*  72 */     return this.maxBuyCost;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getVipBuyTimes(int vip) {
/*  81 */     return ((Integer)this.vipBuyTimes.getOrDefault(Integer.valueOf(vip), Integer.valueOf(0))).intValue();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getChallengeNum() {
/*  89 */     return this.challengeNum;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getRewardRank() {
/*  97 */     return this.rewardRank;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getNeedRank(int challengeRank) {
/* 106 */     return ((Integer)this.needRank.getOrDefault(Integer.valueOf(challengeRank), Integer.valueOf(0))).intValue();
/*     */   }
/*     */ 
/*     */   
/*     */   void init(ParameterBean bean) {
/* 111 */     this.maxReport = Integer.valueOf(((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(1))).getValue()).intValue();
/* 112 */     this.maxChallengeTime = Integer.valueOf(((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(2))).getValue()).intValue();
/* 113 */     this.defaultRank = Integer.valueOf(((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(3))).getValue()).intValue();
/* 114 */     this.refreshCD = Integer.valueOf(((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(4))).getValue()).intValue();
/*     */     
/* 116 */     this.buyCosts.clear();
/* 117 */     this.maxBuyCost = handleReward(bean, this.buyCosts, 5);
/*     */     
/* 119 */     this.vipBuyTimes.clear();
/* 120 */     String[] strings = ((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(6))).getValue().split(";");
/* 121 */     for (String string : strings) {
/* 122 */       String[] strings1 = string.split(":");
/* 123 */       this.vipBuyTimes.put(Integer.valueOf(Integer.parseInt(strings1[0])), Integer.valueOf(Integer.parseInt(strings1[1])));
/*     */     } 
/*     */     
/* 126 */     this.challengeNum = Integer.valueOf(((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(7))).getValue()).intValue();
/*     */     
/* 128 */     Map<Integer, Object> map = JsonTableService.getJsonTable(ArenaRuleBean.class);
/* 129 */     for (Map.Entry<Integer, Object> entry : map.entrySet()) {
/* 130 */       ArenaRuleBean arenaRuleBean = (ArenaRuleBean)entry.getValue();
/* 131 */       if (arenaRuleBean.getBreakReward().size() == 0) {
/*     */         continue;
/*     */       }
/* 134 */       if (arenaRuleBean.getLowId() > this.rewardRank) {
/* 135 */         this.rewardRank = arenaRuleBean.getLowId();
/*     */       }
/*     */     } 
/*     */     
/* 139 */     this.needRank.clear();
/* 140 */     strings = ((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(8))).getValue().split(";");
/* 141 */     int index = 1;
/* 142 */     for (String string : strings)
/* 143 */       this.needRank.put(Integer.valueOf(index++), Integer.valueOf(Integer.parseInt(string))); 
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\config\parameter\ArenaParameter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */