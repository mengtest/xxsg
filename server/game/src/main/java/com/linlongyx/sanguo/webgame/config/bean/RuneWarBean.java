/*    */ package com.linlongyx.sanguo.webgame.config.bean;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ public class RuneWarBean
/*    */ {
/*    */   private int id;
/*    */   
/*    */   public int getId() {
/* 10 */     return this.id;
/*    */   }
/*    */   private String desc; private ArrayList<RewardBean> rankReward;
/*    */   private int score;
/*    */   
/*    */   public String getDesc() {
/* 16 */     return this.desc;
/*    */   }
/*    */   private int win;
/*    */   private int lose;
/*    */   
/*    */   public ArrayList<RewardBean> getRankReward() {
/* 22 */     return this.rankReward;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getScore() {
/* 28 */     return this.score;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getWin() {
/* 34 */     return this.win;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getLose() {
/* 40 */     return this.lose;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 45 */     String retVal = "id= " + this.id + ", desc= " + this.desc + ", rankReward= " + this.rankReward + ", score= " + this.score + ", win= " + this.win + ", lose= " + this.lose;
/* 46 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\config\bean\RuneWarBean.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */