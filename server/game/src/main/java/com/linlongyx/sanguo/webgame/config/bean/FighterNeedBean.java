/*    */ package com.linlongyx.sanguo.webgame.config.bean;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ public class FighterNeedBean {
/*    */   private int id;
/*    */   private ArrayList<RewardBean> reward;
/*    */   
/*    */   public int getId() {
/* 10 */     return this.id;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ArrayList<RewardBean> getReward() {
/* 16 */     return this.reward;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 21 */     String retVal = "id= " + this.id + ", reward= " + this.reward;
/* 22 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\config\bean\FighterNeedBean.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */