/*    */ package com.linlongyx.sanguo.webgame.config.bean;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ public class TowerOwnerBean {
/*    */   private int id;
/*    */   private String name;
/*    */   
/*    */   public int getId() {
/* 10 */     return this.id;
/*    */   }
/*    */   
/*    */   private int towerNumber;
/*    */   
/*    */   public String getName() {
/* 16 */     return this.name;
/*    */   }
/*    */   
/*    */   private ArrayList<RewardBean> defenseReward;
/*    */   
/*    */   public int getTowerNumber() {
/* 22 */     return this.towerNumber;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ArrayList<RewardBean> getDefenseReward() {
/* 28 */     return this.defenseReward;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 33 */     String retVal = "id= " + this.id + ", name= " + this.name + ", towerNumber= " + this.towerNumber + ", defenseReward= " + this.defenseReward;
/* 34 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\config\bean\TowerOwnerBean.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */