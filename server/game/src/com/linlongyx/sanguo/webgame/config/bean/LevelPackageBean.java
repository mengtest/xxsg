/*    */ package com.linlongyx.sanguo.webgame.config.bean;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ public class LevelPackageBean
/*    */ {
/*    */   private int level;
/*    */   
/*    */   public int getLevel() {
/* 10 */     return this.level;
/*    */   }
/*    */   private ArrayList<RewardBean> reward; private int price;
/*    */   private int originalprice;
/*    */   
/*    */   public ArrayList<RewardBean> getReward() {
/* 16 */     return this.reward;
/*    */   }
/*    */   private int selltimes;
/*    */   private int effectivetime;
/*    */   
/*    */   public int getPrice() {
/* 22 */     return this.price;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getOriginalprice() {
/* 28 */     return this.originalprice;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getSelltimes() {
/* 34 */     return this.selltimes;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getEffectivetime() {
/* 40 */     return this.effectivetime;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 45 */     String retVal = "level= " + this.level + ", reward= " + this.reward + ", price= " + this.price + ", originalprice= " + this.originalprice + ", selltimes= " + this.selltimes + ", effectivetime= " + this.effectivetime;
/* 46 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\config\bean\LevelPackageBean.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */