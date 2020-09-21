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
/*    */ public class IdBean
/*    */ {
/*    */   private ArrayList<RewardBean> goods;
/*    */   private int prob;
/*    */   private int isShow;
/*    */   
/*    */   public ArrayList<RewardBean> getGoods() {
/* 26 */     return this.goods;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getProb() {
/* 32 */     return this.prob;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getIsShow() {
/* 38 */     return this.isShow;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 43 */     String retVal = "goods= " + this.goods + ", prob= " + this.prob + ", isShow= " + this.isShow;
/* 44 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\config\bean\RecruitLibBean$IdBean.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */