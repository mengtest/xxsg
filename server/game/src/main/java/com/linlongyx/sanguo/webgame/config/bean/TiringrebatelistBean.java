/*    */ package com.linlongyx.sanguo.webgame.config.bean;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ public class TiringrebatelistBean
/*    */ {
/*    */   private int id;
/*    */   
/*    */   public int getId() {
/* 10 */     return this.id;
/*    */   }
/*    */   private int rmb;
/*    */   private ArrayList<RewardBean> Reward;
/*    */   
/*    */   public int getRmb() {
/* 16 */     return this.rmb;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ArrayList<RewardBean> getReward() {
/* 22 */     return this.Reward;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 27 */     String retVal = "id= " + this.id + ", rmb= " + this.rmb + ", Reward= " + this.Reward;
/* 28 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\config\bean\TiringrebatelistBean.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */