/*    */ package com.linlongyx.sanguo.webgame.config.parameter;
/*    */ 
/*    */ import com.linlongyx.sanguo.webgame.config.bean.ParameterBean;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.Reward;
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class GoldBuyParameter
/*    */   extends AbstractParameter
/*    */ {
/*    */   private int chargeId;
/*    */   private ArrayList<Reward> goldRewards;
/*    */   private int limitTime;
/*    */   
/*    */   public GoldBuyParameter() {
/* 19 */     super(70);
/*    */ 
/*    */ 
/*    */     
/* 23 */     this.goldRewards = new ArrayList<>();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   void init(ParameterBean bean) {
/* 29 */     this.chargeId = Integer.parseInt(((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(2))).getValue());
/*    */     
/* 31 */     this.goldRewards.clear();
/* 32 */     String[] string2 = ((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(1))).getValue().split(";");
/* 33 */     for (String string : string2) {
/* 34 */       Reward reward = new Reward();
/* 35 */       String[] strings2 = string.split(",");
/* 36 */       tranform(reward, strings2);
/* 37 */       this.goldRewards.add(reward);
/*    */     } 
/* 39 */     this.limitTime = Integer.parseInt(((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(4))).getValue());
/*    */   }
/*    */   
/*    */   public int getChargeId() {
/* 43 */     return this.chargeId;
/*    */   }
/*    */   
/*    */   public ArrayList<Reward> getGoldRewards() {
/* 47 */     return this.goldRewards;
/*    */   }
/*    */   
/*    */   public int getLimitTime() {
/* 51 */     return this.limitTime;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\config\parameter\GoldBuyParameter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */