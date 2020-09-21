/*    */ package com.linlongyx.sanguo.webgame.config.bean;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ public class BossWorldRewardBean
/*    */ {
/*    */   private int id;
/*    */   
/*    */   public int getId() {
/* 10 */     return this.id;
/*    */   }
/*    */   private int type; private long point;
/*    */   private int lowrank;
/*    */   
/*    */   public int getType() {
/* 16 */     return this.type;
/*    */   }
/*    */   private int highrank;
/*    */   private ArrayList<RewardBean> reward;
/*    */   
/*    */   public long getPoint() {
/* 22 */     return this.point;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getLowrank() {
/* 28 */     return this.lowrank;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getHighrank() {
/* 34 */     return this.highrank;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ArrayList<RewardBean> getReward() {
/* 40 */     return this.reward;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 45 */     String retVal = "id= " + this.id + ", type= " + this.type + ", point= " + this.point + ", lowrank= " + this.lowrank + ", highrank= " + this.highrank + ", reward= " + this.reward;
/* 46 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\config\bean\BossWorldRewardBean.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */