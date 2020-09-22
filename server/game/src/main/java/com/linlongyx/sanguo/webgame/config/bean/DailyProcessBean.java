/*    */ package com.linlongyx.sanguo.webgame.config.bean;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ public class DailyProcessBean {
/*    */   private int id;
/*    */   private ArrayList<RewardBean> reward;
/*    */   
/*    */   public int getId() {
/* 10 */     return this.id;
/*    */   }
/*    */   
/*    */   private int point;
/*    */   
/*    */   public ArrayList<RewardBean> getReward() {
/* 16 */     return this.reward;
/*    */   }
/*    */   
/*    */   private int showLevel;
/*    */   
/*    */   public int getPoint() {
/* 22 */     return this.point;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getShowLevel() {
/* 28 */     return this.showLevel;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 33 */     String retVal = "id= " + this.id + ", reward= " + this.reward + ", point= " + this.point + ", showLevel= " + this.showLevel;
/* 34 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\config\bean\DailyProcessBean.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */