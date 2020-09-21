/*    */ package com.linlongyx.sanguo.webgame.config.bean;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ public class ShareListBean
/*    */ {
/*    */   private int id;
/*    */   
/*    */   public int getId() {
/* 10 */     return this.id;
/*    */   }
/*    */   private ArrayList<RewardBean> reward;
/*    */   private int playerLevel;
/*    */   
/*    */   public ArrayList<RewardBean> getReward() {
/* 16 */     return this.reward;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getPlayerLevel() {
/* 22 */     return this.playerLevel;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 27 */     String retVal = "id= " + this.id + ", reward= " + this.reward + ", playerLevel= " + this.playerLevel;
/* 28 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\config\bean\ShareListBean.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */