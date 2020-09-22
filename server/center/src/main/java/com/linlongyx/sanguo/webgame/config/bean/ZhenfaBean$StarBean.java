/*     */ package com.linlongyx.sanguo.webgame.config.bean;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class StarBean
/*     */ {
/*     */   private ArrayList<CardBean> card;
/*     */   private ArrayList<Integer> buffOne;
/*     */   private ArrayList<Integer> buffTwo;
/*     */   private ArrayList<Integer> buffThree;
/*     */   private ArrayList<Integer> buffFour;
/*     */   private ArrayList<Integer> buffFive;
/*     */   private ArrayList<Integer> buffSix;
/*     */   private int levelPar;
/*     */   
/*     */   public ArrayList<CardBean> getCard() {
/*  44 */     return this.card;
/*     */   }
/*     */   
/*     */   public class CardBean {
/*     */     private int type;
/*     */     
/*     */     public int getType() {
/*  51 */       return this.type;
/*     */     }
/*     */     
/*     */     private int id;
/*     */     
/*     */     public int getId() {
/*  57 */       return this.id;
/*     */     }
/*     */     private int num;
/*     */     
/*     */     public int getNum() {
/*  62 */       return this.num;
/*     */     }
/*     */ 
/*     */     
/*     */     public String toString() {
/*  67 */       String retVal = "type= " + this.type + ", id= " + this.id + ", num= " + this.num;
/*  68 */       return retVal;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ArrayList<Integer> getBuffOne() {
/*  75 */     return this.buffOne;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ArrayList<Integer> getBuffTwo() {
/*  81 */     return this.buffTwo;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ArrayList<Integer> getBuffThree() {
/*  87 */     return this.buffThree;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ArrayList<Integer> getBuffFour() {
/*  93 */     return this.buffFour;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ArrayList<Integer> getBuffFive() {
/*  99 */     return this.buffFive;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ArrayList<Integer> getBuffSix() {
/* 105 */     return this.buffSix;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getLevelPar() {
/* 111 */     return this.levelPar;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 116 */     String retVal = "card= " + this.card + ", buffOne= " + this.buffOne + ", buffTwo= " + this.buffTwo + ", buffThree= " + this.buffThree + ", buffFour= " + this.buffFour + ", buffFive= " + this.buffFive + ", buffSix= " + this.buffSix + ", levelPar= " + this.levelPar;
/* 117 */     return retVal;
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\sanguo\webgame\config\bean\ZhenfaBean$StarBean.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */