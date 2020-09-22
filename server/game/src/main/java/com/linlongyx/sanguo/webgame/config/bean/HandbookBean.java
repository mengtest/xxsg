/*    */ package com.linlongyx.sanguo.webgame.config.bean;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.Map;
/*    */ 
/*    */ public class HandbookBean {
/*    */   private int camp;
/*    */   private Map<Integer, StarsBean> stars;
/*    */   
/*    */   public int getCamp() {
/* 11 */     return this.camp;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public Map<Integer, StarsBean> getStars() {
/* 17 */     return this.stars;
/*    */   }
/*    */ 
/*    */   
/*    */   public class StarsBean
/*    */   {
/*    */     private ArrayList<RewardBean> reward;
/*    */     
/*    */     public ArrayList<RewardBean> getReward() {
/* 26 */       return this.reward;
/*    */     }
/*    */     
/*    */     public class RewardBean {
/*    */       private int type;
/*    */       
/*    */       public int getType() {
/* 33 */         return this.type;
/*    */       }
/*    */       private int id; private int num;
/*    */       
/*    */       public int getId() {
/* 38 */         return this.id;
/*    */       }
/*    */ 
/*    */       
/*    */       public int getNum() {
/* 43 */         return this.num;
/*    */       }
/*    */ 
/*    */       
/*    */       public String toString() {
/* 48 */         String retVal = "type= " + this.type + ", id= " + this.id + ", num= " + this.num;
/* 49 */         return retVal;
/*    */       }
/*    */     }
/*    */ 
/*    */     
/*    */     public String toString() {
/* 55 */       String retVal = "reward= " + this.reward;
/* 56 */       return retVal;
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 62 */     String retVal = "camp= " + this.camp + ", stars= " + this.stars;
/* 63 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\config\bean\HandbookBean.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */