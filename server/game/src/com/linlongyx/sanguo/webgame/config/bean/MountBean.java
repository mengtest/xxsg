/*    */ package com.linlongyx.sanguo.webgame.config.bean;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.Map;
/*    */ 
/*    */ public class MountBean {
/*    */   private int id;
/*    */   private String name;
/*    */   
/*    */   public int getId() {
/* 11 */     return this.id;
/*    */   }
/*    */   
/*    */   private String rid;
/*    */   
/*    */   public String getName() {
/* 17 */     return this.name;
/*    */   }
/*    */   
/*    */   private int quality;
/*    */   
/*    */   public String getRid() {
/* 23 */     return this.rid;
/*    */   }
/*    */   
/*    */   private Map<Integer, StarBean> star;
/*    */   
/*    */   public int getQuality() {
/* 29 */     return this.quality;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public Map<Integer, StarBean> getStar() {
/* 35 */     return this.star;
/*    */   }
/*    */   
/*    */   public class StarBean {
/*    */     private ArrayList<RewardBean> card;
/*    */     private ArrayList<AttrBean> attr;
/*    */     private int levelPar;
/*    */     
/*    */     public ArrayList<RewardBean> getCard() {
/* 44 */       return this.card;
/*    */     }
/*    */ 
/*    */ 
/*    */     
/*    */     public ArrayList<AttrBean> getAttr() {
/* 50 */       return this.attr;
/*    */     }
/*    */ 
/*    */ 
/*    */     
/*    */     public int getLevelPar() {
/* 56 */       return this.levelPar;
/*    */     }
/*    */ 
/*    */     
/*    */     public String toString() {
/* 61 */       String retVal = "card= " + this.card + ", attr= " + this.attr + ", levelPar= " + this.levelPar;
/* 62 */       return retVal;
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 68 */     String retVal = "id= " + this.id + ", name= " + this.name + ", rid= " + this.rid + ", quality= " + this.quality + ", star= " + this.star;
/* 69 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\config\bean\MountBean.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */