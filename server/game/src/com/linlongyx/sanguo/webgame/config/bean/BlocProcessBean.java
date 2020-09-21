/*    */ package com.linlongyx.sanguo.webgame.config.bean;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ public class BlocProcessBean
/*    */ {
/*    */   private int id;
/*    */   
/*    */   public int getId() {
/* 10 */     return this.id;
/*    */   }
/*    */   private ArrayList<RewardBean> reward;
/*    */   private int point;
/*    */   
/*    */   public ArrayList<RewardBean> getReward() {
/* 16 */     return this.reward;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getPoint() {
/* 22 */     return this.point;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 27 */     String retVal = "id= " + this.id + ", reward= " + this.reward + ", point= " + this.point;
/* 28 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\config\bean\BlocProcessBean.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */