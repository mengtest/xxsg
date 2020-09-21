/*    */ package com.linlongyx.sanguo.webgame.config.bean;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ public class EverydayChargeBean {
/*    */   private int id;
/*    */   private int rmb;
/*    */   
/*    */   public int getId() {
/* 10 */     return this.id;
/*    */   }
/*    */   
/*    */   private int playerLevel;
/*    */   
/*    */   public int getRmb() {
/* 16 */     return this.rmb;
/*    */   }
/*    */   
/*    */   private String name;
/*    */   
/*    */   public int getPlayerLevel() {
/* 22 */     return this.playerLevel;
/*    */   }
/*    */   
/*    */   private ArrayList<RewardBean> reward;
/*    */   
/*    */   public String getName() {
/* 28 */     return this.name;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ArrayList<RewardBean> getReward() {
/* 34 */     return this.reward;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 39 */     String retVal = "id= " + this.id + ", rmb= " + this.rmb + ", playerLevel= " + this.playerLevel + ", name= " + this.name + ", reward= " + this.reward;
/* 40 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\config\bean\EverydayChargeBean.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */