/*    */ package com.linlongyx.sanguo.webgame.config.bean;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class IdBean
/*    */ {
/*    */   private ArrayList<RewardBean> tenthGoods;
/*    */   private int prob;
/*    */   
/*    */   public ArrayList<RewardBean> getTenthGoods() {
/* 26 */     return this.tenthGoods;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getProb() {
/* 32 */     return this.prob;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 37 */     String retVal = "tenthGoods= " + this.tenthGoods + ", prob= " + this.prob;
/* 38 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\config\bean\RecruitRedTenthBean$IdBean.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */