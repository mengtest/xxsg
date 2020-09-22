/*    */ package com.linlongyx.sanguo.webgame.config.bean;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ public class OneyuanBean
/*    */ {
/*    */   private int id;
/*    */   
/*    */   public int getId() {
/* 10 */     return this.id;
/*    */   }
/*    */   private ArrayList<Integer> rewardId;
/*    */   private ArrayList<Integer> chargeId;
/*    */   
/*    */   public ArrayList<Integer> getRewardId() {
/* 16 */     return this.rewardId;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ArrayList<Integer> getChargeId() {
/* 22 */     return this.chargeId;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 27 */     String retVal = "id= " + this.id + ", rewardId= " + this.rewardId + ", chargeId= " + this.chargeId;
/* 28 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\config\bean\OneyuanBean.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */