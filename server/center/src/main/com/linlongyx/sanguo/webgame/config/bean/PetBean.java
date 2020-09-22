/*     */ package com.linlongyx.sanguo.webgame.config.bean;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class PetBean {
/*     */   private int id;
/*     */   private String name;
/*     */   
/*     */   public int getId() {
/*  11 */     return this.id;
/*     */   }
/*     */   
/*     */   private String rid;
/*     */   
/*     */   public String getName() {
/*  17 */     return this.name;
/*     */   }
/*     */   private int quality;
/*     */   private Map<Integer, StarBean> star;
/*     */   
/*     */   public String getRid() {
/*  23 */     return this.rid;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getQuality() {
/*  29 */     return this.quality;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Map<Integer, StarBean> getStar() {
/*  35 */     return this.star;
/*     */   }
/*     */   
/*     */   public class StarBean
/*     */   {
/*     */     private int petSkill;
/*     */     private ArrayList<CardBean> card;
/*     */     
/*     */     public int getPetSkill() {
/*  44 */       return this.petSkill;
/*     */     }
/*     */     private int hurtType; private ArrayList<AttrBean> attr;
/*     */     private int levelPar;
/*     */     
/*     */     public ArrayList<CardBean> getCard() {
/*  50 */       return this.card;
/*     */     }
/*     */     public class CardBean { private int type;
/*     */       private int id;
/*     */       private int num;
/*     */       
/*     */       public int getType() {
/*  57 */         return this.type;
/*     */       }
/*     */ 
/*     */ 
/*     */       
/*     */       public int getId() {
/*  63 */         return this.id;
/*     */       }
/*     */ 
/*     */       
/*     */       public int getNum() {
/*  68 */         return this.num;
/*     */       }
/*     */ 
/*     */       
/*     */       public String toString() {
/*  73 */         String retVal = "type= " + this.type + ", id= " + this.id + ", num= " + this.num;
/*  74 */         return retVal;
/*     */       } }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public int getHurtType() {
/*  81 */       return this.hurtType;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public ArrayList<AttrBean> getAttr() {
/*  87 */       return this.attr;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public int getLevelPar() {
/*  93 */       return this.levelPar;
/*     */     }
/*     */ 
/*     */     
/*     */     public String toString() {
/*  98 */       String retVal = "petSkill= " + this.petSkill + ", card= " + this.card + ", hurtType= " + this.hurtType + ", attr= " + this.attr + ", levelPar= " + this.levelPar;
/*  99 */       return retVal;
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 105 */     String retVal = "id= " + this.id + ", name= " + this.name + ", rid= " + this.rid + ", quality= " + this.quality + ", star= " + this.star;
/* 106 */     return retVal;
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\sanguo\webgame\config\bean\PetBean.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */