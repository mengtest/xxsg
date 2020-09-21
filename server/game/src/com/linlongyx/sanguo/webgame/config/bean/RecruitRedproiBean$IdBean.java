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
/*    */   private ArrayList<RewardBean> reward;
/*    */   private int point;
/*    */   
/*    */   public ArrayList<RewardBean> getReward() {
/* 26 */     return this.reward;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getPoint() {
/* 32 */     return this.point;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 37 */     String retVal = "reward= " + this.reward + ", point= " + this.point;
/* 38 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\config\bean\RecruitRedproiBean$IdBean.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */