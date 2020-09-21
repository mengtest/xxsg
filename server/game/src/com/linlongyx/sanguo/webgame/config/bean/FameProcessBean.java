/*    */ package com.linlongyx.sanguo.webgame.config.bean;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ public class FameProcessBean {
/*    */   private int id;
/*    */   private ArrayList<RewardBean> reward;
/*    */   
/*    */   public int getId() {
/* 10 */     return this.id;
/*    */   }
/*    */   
/*    */   private int fame;
/*    */   
/*    */   public ArrayList<RewardBean> getReward() {
/* 16 */     return this.reward;
/*    */   }
/*    */   
/*    */   private int showFame;
/*    */   
/*    */   public int getFame() {
/* 22 */     return this.fame;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getShowFame() {
/* 28 */     return this.showFame;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 33 */     String retVal = "id= " + this.id + ", reward= " + this.reward + ", fame= " + this.fame + ", showFame= " + this.showFame;
/* 34 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\config\bean\FameProcessBean.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */