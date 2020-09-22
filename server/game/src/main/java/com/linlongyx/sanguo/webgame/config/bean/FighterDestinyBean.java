/*     */ package com.linlongyx.sanguo.webgame.config.bean;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ 
/*     */ public class FighterDestinyBean {
/*     */   private int desLv;
/*     */   private int cleanUp;
/*     */   
/*     */   public int getDesLv() {
/*  10 */     return this.desLv;
/*     */   }
/*     */   private ArrayList<AttrBean> attr; private int maxVal; private ArrayList<SucessProbBean> sucessProb;
/*     */   private ArrayList<RewardBean> cost;
/*     */   
/*     */   public int getCleanUp() {
/*  16 */     return this.cleanUp;
/*     */   }
/*     */   private ArrayList<DesValBean> desVal; private ArrayList<RewardBean> recover;
/*     */   private ArrayList<String> recover_cli;
/*     */   
/*     */   public ArrayList<AttrBean> getAttr() {
/*  22 */     return this.attr;
/*     */   }
/*     */   
/*     */   public class AttrBean { private int id;
/*     */     private int num;
/*     */     
/*     */     public int getId() {
/*  29 */       return this.id;
/*     */     }
/*     */ 
/*     */     
/*     */     public int getNum() {
/*  34 */       return this.num;
/*     */     }
/*     */ 
/*     */     
/*     */     public String toString() {
/*  39 */       String retVal = "id= " + this.id + ", num= " + this.num;
/*  40 */       return retVal;
/*     */     } }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMaxVal() {
/*  47 */     return this.maxVal;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ArrayList<SucessProbBean> getSucessProb() {
/*  53 */     return this.sucessProb;
/*     */   }
/*     */   
/*     */   public class SucessProbBean { private int blessVal;
/*     */     private int sucessProb;
/*     */     
/*     */     public int getBlessVal() {
/*  60 */       return this.blessVal;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public int getSucessProb() {
/*  66 */       return this.sucessProb;
/*     */     }
/*     */ 
/*     */     
/*     */     public String toString() {
/*  71 */       String retVal = "blessVal= " + this.blessVal + ", sucessProb= " + this.sucessProb;
/*  72 */       return retVal;
/*     */     } }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ArrayList<RewardBean> getCost() {
/*  79 */     return this.cost;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ArrayList<DesValBean> getDesVal() {
/*  85 */     return this.desVal;
/*     */   }
/*     */   
/*     */   public class DesValBean { private int low;
/*     */     private int high;
/*     */     
/*     */     public int getLow() {
/*  92 */       return this.low;
/*     */     }
/*     */ 
/*     */     
/*     */     public int getHigh() {
/*  97 */       return this.high;
/*     */     }
/*     */ 
/*     */     
/*     */     public String toString() {
/* 102 */       String retVal = "low= " + this.low + ", high= " + this.high;
/* 103 */       return retVal;
/*     */     } }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ArrayList<RewardBean> getRecover() {
/* 110 */     return this.recover;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ArrayList<String> getRecover_cli() {
/* 116 */     return this.recover_cli;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 121 */     String retVal = "desLv= " + this.desLv + ", cleanUp= " + this.cleanUp + ", attr= " + this.attr + ", maxVal= " + this.maxVal + ", sucessProb= " + this.sucessProb + ", cost= " + this.cost + ", desVal= " + this.desVal + ", recover= " + this.recover + ", recover_cli= " + this.recover_cli;
/* 122 */     return retVal;
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\config\bean\FighterDestinyBean.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */