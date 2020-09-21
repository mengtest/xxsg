/*    */ package com.linlongyx.sanguo.webgame.config.parameter;
/*    */ 
/*    */ import com.linlongyx.sanguo.webgame.config.bean.ParameterBean;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.Reward;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class MentalParamter
/*    */   extends AbstractParameter
/*    */ {
/*    */   protected static final int TYPE_1 = 1;
/*    */   protected static final int TYPE_2 = 2;
/*    */   protected static final int TYPE_3 = 3;
/*    */   protected static final int TYPE_4 = 4;
/*    */   protected static final int TYPE_5 = 5;
/*    */   protected static final int TYPE_7 = 7;
/*    */   private int lotteryItemId;
/*    */   private int lotteryOneCost;
/*    */   
/*    */   public MentalParamter() {
/* 22 */     super(58);
/*    */   }
/*    */ 
/*    */   
/*    */   private int lotteryTenCost;
/*    */   
/*    */   private int oneAddPoint;
/*    */   
/*    */   private int tenAddPoint;
/*    */   
/*    */   private int rankNumber;
/*    */   
/*    */   private int singleRecord;
/*    */   
/*    */   private int pannelRecord;
/*    */   
/*    */   private int freeTime;
/*    */   private Reward rewards;
/*    */   
/*    */   void init(ParameterBean bean) {
/* 42 */     this.rankNumber = Integer.parseInt(((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(1))).getValue());
/* 43 */     this.pannelRecord = Integer.parseInt(((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(2))).getValue());
/* 44 */     this.singleRecord = Integer.parseInt(((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(3))).getValue());
/* 45 */     this.oneAddPoint = Integer.parseInt(((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(4))).getValue());
/* 46 */     this.tenAddPoint = Integer.parseInt(((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(5))).getValue());
/* 47 */     this.lotteryItemId = Integer.parseInt(((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(6))).getValue());
/* 48 */     this.lotteryOneCost = Integer.parseInt(((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(7))).getValue());
/* 49 */     this.lotteryTenCost = Integer.parseInt(((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(8))).getValue());
/* 50 */     this.freeTime = Integer.parseInt(((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(9))).getValue());
/*    */     
/* 52 */     String[] rewardStrs = ((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(11))).getValue().split(",");
/* 53 */     Reward reward = new Reward();
/* 54 */     reward.type = Byte.parseByte(rewardStrs[0]);
/* 55 */     reward.id = Integer.parseInt(rewardStrs[1]);
/* 56 */     reward.num = Long.parseLong(rewardStrs[2]);
/* 57 */     this.rewards = reward;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getLotteryItemId() {
/* 63 */     return this.lotteryItemId;
/*    */   }
/*    */   
/*    */   public int getLotteryOneCost() {
/* 67 */     return this.lotteryOneCost;
/*    */   }
/*    */   
/*    */   public int getLotteryTenCost() {
/* 71 */     return this.lotteryTenCost;
/*    */   }
/*    */   
/*    */   public int getOneAddPoint() {
/* 75 */     return this.oneAddPoint;
/*    */   }
/*    */   
/*    */   public int getTenAddPoint() {
/* 79 */     return this.tenAddPoint;
/*    */   }
/*    */   
/*    */   public int getRankNumber() {
/* 83 */     return this.rankNumber;
/*    */   }
/*    */   
/*    */   public int getSingleRecord() {
/* 87 */     return this.singleRecord;
/*    */   }
/*    */   
/*    */   public int getPannelRecord() {
/* 91 */     return this.pannelRecord;
/*    */   }
/*    */   
/*    */   public int getFreeTime() {
/* 95 */     return this.freeTime;
/*    */   }
/*    */   
/*    */   public Reward getRewards() {
/* 99 */     return this.rewards;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\config\parameter\MentalParamter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */