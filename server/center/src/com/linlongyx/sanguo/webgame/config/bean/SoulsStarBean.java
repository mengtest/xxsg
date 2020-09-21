/*    */ package com.linlongyx.sanguo.webgame.config.bean;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.Map;
/*    */ 
/*    */ public class SoulsStarBean {
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
/*    */     private int hotSkill;
/*    */     private int debutSkill;
/*    */     private ArrayList<RewardBean> card;
/*    */     
/*    */     public int getHotSkill() {
/* 44 */       return this.hotSkill;
/*    */     }
/*    */     
/*    */     private ArrayList<AttrBean> attr;
/*    */     
/*    */     public int getDebutSkill() {
/* 50 */       return this.debutSkill;
/*    */     }
/*    */     
/*    */     private int levelPar;
/*    */     
/*    */     public ArrayList<RewardBean> getCard() {
/* 56 */       return this.card;
/*    */     }
/*    */     
/*    */     private int breakPar;
/*    */     
/*    */     public ArrayList<AttrBean> getAttr() {
/* 62 */       return this.attr;
/*    */     }
/*    */ 
/*    */ 
/*    */     
/*    */     public int getLevelPar() {
/* 68 */       return this.levelPar;
/*    */     }
/*    */ 
/*    */ 
/*    */     
/*    */     public int getBreakPar() {
/* 74 */       return this.breakPar;
/*    */     }
/*    */ 
/*    */     
/*    */     public String toString() {
/* 79 */       String retVal = "hotSkill= " + this.hotSkill + ", debutSkill= " + this.debutSkill + ", card= " + this.card + ", attr= " + this.attr + ", levelPar= " + this.levelPar + ", breakPar= " + this.breakPar;
/* 80 */       return retVal;
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 86 */     String retVal = "id= " + this.id + ", name= " + this.name + ", rid= " + this.rid + ", quality= " + this.quality + ", star= " + this.star;
/* 87 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\sanguo\webgame\config\bean\SoulsStarBean.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */