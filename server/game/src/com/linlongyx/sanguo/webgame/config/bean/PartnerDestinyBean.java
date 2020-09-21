/*    */ package com.linlongyx.sanguo.webgame.config.bean;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ public class PartnerDestinyBean
/*    */ {
/*    */   private int desLv;
/*    */   
/*    */   public int getDesLv() {
/* 10 */     return this.desLv;
/*    */   }
/*    */   private int cleanUp; private int attrBonus; private int maxVal;
/*    */   private ArrayList<SucessProbBean> sucessProb;
/*    */   
/*    */   public int getCleanUp() {
/* 16 */     return this.cleanUp;
/*    */   }
/*    */   private ArrayList<RewardBean> cost;
/*    */   private ArrayList<DesValBean> desVal;
/*    */   
/*    */   public int getAttrBonus() {
/* 22 */     return this.attrBonus;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getMaxVal() {
/* 28 */     return this.maxVal;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ArrayList<SucessProbBean> getSucessProb() {
/* 34 */     return this.sucessProb;
/*    */   }
/*    */   
/*    */   public class SucessProbBean { private int blessVal;
/*    */     private int sucessProb;
/*    */     
/*    */     public int getBlessVal() {
/* 41 */       return this.blessVal;
/*    */     }
/*    */ 
/*    */ 
/*    */     
/*    */     public int getSucessProb() {
/* 47 */       return this.sucessProb;
/*    */     }
/*    */ 
/*    */     
/*    */     public String toString() {
/* 52 */       String retVal = "blessVal= " + this.blessVal + ", sucessProb= " + this.sucessProb;
/* 53 */       return retVal;
/*    */     } }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public ArrayList<RewardBean> getCost() {
/* 60 */     return this.cost;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ArrayList<DesValBean> getDesVal() {
/* 66 */     return this.desVal;
/*    */   }
/*    */   
/*    */   public class DesValBean { private int low;
/*    */     private int high;
/*    */     
/*    */     public int getLow() {
/* 73 */       return this.low;
/*    */     }
/*    */ 
/*    */     
/*    */     public int getHigh() {
/* 78 */       return this.high;
/*    */     }
/*    */ 
/*    */     
/*    */     public String toString() {
/* 83 */       String retVal = "low= " + this.low + ", high= " + this.high;
/* 84 */       return retVal;
/*    */     } }
/*    */ 
/*    */ 
/*    */   
/*    */   public String toString() {
/* 90 */     String retVal = "desLv= " + this.desLv + ", cleanUp= " + this.cleanUp + ", attrBonus= " + this.attrBonus + ", maxVal= " + this.maxVal + ", sucessProb= " + this.sucessProb + ", cost= " + this.cost + ", desVal= " + this.desVal;
/* 91 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\config\bean\PartnerDestinyBean.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */