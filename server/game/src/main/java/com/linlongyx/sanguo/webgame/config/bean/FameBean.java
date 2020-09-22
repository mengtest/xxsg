/*    */ package com.linlongyx.sanguo.webgame.config.bean;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ public class FameBean {
/*    */   private int fameId;
/*    */   private int fameType;
/*    */   
/*    */   public int getFameId() {
/* 10 */     return this.fameId;
/*    */   }
/*    */   private int tagId; private int targetType; private int last; private int order; private int target;
/*    */   private int number;
/*    */   
/*    */   public int getFameType() {
/* 16 */     return this.fameType;
/*    */   }
/*    */   private String sec; private int fame;
/*    */   private ArrayList<RewardBean> fameReward;
/*    */   
/*    */   public int getTagId() {
/* 22 */     return this.tagId;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getTargetType() {
/* 28 */     return this.targetType;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getLast() {
/* 34 */     return this.last;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getOrder() {
/* 40 */     return this.order;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getTarget() {
/* 46 */     return this.target;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getNumber() {
/* 52 */     return this.number;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String getSec() {
/* 58 */     return this.sec;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getFame() {
/* 64 */     return this.fame;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ArrayList<RewardBean> getFameReward() {
/* 70 */     return this.fameReward;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 75 */     String retVal = "fameId= " + this.fameId + ", fameType= " + this.fameType + ", tagId= " + this.tagId + ", targetType= " + this.targetType + ", last= " + this.last + ", order= " + this.order + ", target= " + this.target + ", number= " + this.number + ", sec= " + this.sec + ", fame= " + this.fame + ", fameReward= " + this.fameReward;
/* 76 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\config\bean\FameBean.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */