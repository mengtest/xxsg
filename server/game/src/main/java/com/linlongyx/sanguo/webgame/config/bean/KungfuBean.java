/*    */ package com.linlongyx.sanguo.webgame.config.bean;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.Map;
/*    */ 
/*    */ public class KungfuBean {
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
/*    */   public class StarBean
/*    */   {
/*    */     private int kungfuSkill;
/*    */     private ArrayList<RewardBean> card;
/*    */     
/*    */     public int getKungfuSkill() {
/* 44 */       return this.kungfuSkill;
/*    */     }
/*    */     private ArrayList<AttrBean> attr;
/*    */     private int levelPar;
/*    */     
/*    */     public ArrayList<RewardBean> getCard() {
/* 50 */       return this.card;
/*    */     }
/*    */ 
/*    */ 
/*    */     
/*    */     public ArrayList<AttrBean> getAttr() {
/* 56 */       return this.attr;
/*    */     }
/*    */     
/*    */     public class AttrBean {
/*    */       private int id;
/*    */       private int num;
/*    */       
/*    */       public int getId() {
/* 64 */         return this.id;
/*    */       }
/*    */ 
/*    */       
/*    */       public int getNum() {
/* 69 */         return this.num;
/*    */       }
/*    */ 
/*    */       
/*    */       public String toString() {
/* 74 */         String retVal = "id= " + this.id + ", num= " + this.num;
/* 75 */         return retVal;
/*    */       }
/*    */     }
/*    */ 
/*    */ 
/*    */     
/*    */     public int getLevelPar() {
/* 82 */       return this.levelPar;
/*    */     }
/*    */ 
/*    */     
/*    */     public String toString() {
/* 87 */       String retVal = "kungfuSkill= " + this.kungfuSkill + ", card= " + this.card + ", attr= " + this.attr + ", levelPar= " + this.levelPar;
/* 88 */       return retVal;
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 94 */     String retVal = "id= " + this.id + ", name= " + this.name + ", rid= " + this.rid + ", quality= " + this.quality + ", star= " + this.star;
/* 95 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\config\bean\KungfuBean.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */