/*    */ package com.linlongyx.sanguo.webgame.config.bean;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ public class DropBagBean
/*    */ {
/*    */   private int id;
/*    */   
/*    */   public int getId() {
/* 10 */     return this.id;
/*    */   }
/*    */   private int times; private int repeatable;
/*    */   private int type;
/*    */   
/*    */   public int getTimes() {
/* 16 */     return this.times;
/*    */   }
/*    */   private ArrayList<DropRewardBean> dropReward;
/*    */   private int prob;
/*    */   
/*    */   public int getRepeatable() {
/* 22 */     return this.repeatable;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getType() {
/* 28 */     return this.type;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ArrayList<DropRewardBean> getDropReward() {
/* 34 */     return this.dropReward;
/*    */   }
/*    */   
/*    */   public class DropRewardBean {
/*    */     private int type;
/*    */     private int id;
/*    */     
/*    */     public int getType() {
/* 42 */       return this.type;
/*    */     }
/*    */     private int num;
/*    */     private int prob;
/*    */     
/*    */     public int getId() {
/* 48 */       return this.id;
/*    */     }
/*    */ 
/*    */     
/*    */     public int getNum() {
/* 53 */       return this.num;
/*    */     }
/*    */ 
/*    */ 
/*    */     
/*    */     public int getProb() {
/* 59 */       return this.prob;
/*    */     }
/*    */ 
/*    */     
/*    */     public String toString() {
/* 64 */       String retVal = "type= " + this.type + ", id= " + this.id + ", num= " + this.num + ", prob= " + this.prob;
/* 65 */       return retVal;
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getProb() {
/* 72 */     return this.prob;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 77 */     String retVal = "id= " + this.id + ", times= " + this.times + ", repeatable= " + this.repeatable + ", type= " + this.type + ", dropReward= " + this.dropReward + ", prob= " + this.prob;
/* 78 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\config\bean\DropBagBean.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */