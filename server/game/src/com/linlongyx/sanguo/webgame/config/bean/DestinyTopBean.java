/*    */ package com.linlongyx.sanguo.webgame.config.bean;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ public class DestinyTopBean
/*    */ {
/*    */   private int id;
/*    */   
/*    */   public int getId() {
/* 10 */     return this.id;
/*    */   }
/*    */   private int rank;
/*    */   private ArrayList<RewardBean> reward;
/*    */   
/*    */   public int getRank() {
/* 16 */     return this.rank;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ArrayList<RewardBean> getReward() {
/* 22 */     return this.reward;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 27 */     String retVal = "id= " + this.id + ", rank= " + this.rank + ", reward= " + this.reward;
/* 28 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\config\bean\DestinyTopBean.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */