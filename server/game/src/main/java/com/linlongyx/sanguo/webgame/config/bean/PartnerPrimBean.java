/*    */ package com.linlongyx.sanguo.webgame.config.bean;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ public class PartnerPrimBean
/*    */ {
/*    */   private int level;
/*    */   
/*    */   public int getLevel() {
/* 10 */     return this.level;
/*    */   }
/*    */   private ArrayList<RewardBean> cost;
/*    */   private int partnerAttc;
/*    */   
/*    */   public ArrayList<RewardBean> getCost() {
/* 16 */     return this.cost;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getPartnerAttc() {
/* 22 */     return this.partnerAttc;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 27 */     String retVal = "level= " + this.level + ", cost= " + this.cost + ", partnerAttc= " + this.partnerAttc;
/* 28 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\config\bean\PartnerPrimBean.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */