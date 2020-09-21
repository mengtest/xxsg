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
/*     */ public class StarBean
/*     */ {
/*     */   private int additionId;
/*     */   private ArrayList<CardBean> card;
/*     */   private ArrayList<Integer> buffOne;
/*     */   private ArrayList<Integer> buffTwo;
/*     */   private ArrayList<Integer> buffThree;
/*     */   private ArrayList<Integer> buffFour;
/*     */   private ArrayList<Integer> buffFive;
/*     */   private ArrayList<Integer> buffSix;
/*     */   private int levelPar;
/*     */   
/*     */   public int getAdditionId() {
/*  44 */     return this.additionId;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ArrayList<CardBean> getCard() {
/*  50 */     return this.card;
/*     */   }
/*     */   
/*     */   public class CardBean {
/*     */     private int type;
/*     */     
/*     */     public int getType() {
/*  57 */       return this.type;
/*     */     }
/*     */     
/*     */     private int id;
/*     */     
/*     */     public int getId() {
/*  63 */       return this.id;
/*     */     }
/*     */     private int num;
/*     */     
/*     */     public int getNum() {
/*  68 */       return this.num;
/*     */     }
/*     */ 
/*     */     
/*     */     public String toString() {
/*  73 */       String retVal = "type= " + this.type + ", id= " + this.id + ", num= " + this.num;
/*  74 */       return retVal;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ArrayList<Integer> getBuffOne() {
/*  81 */     return this.buffOne;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ArrayList<Integer> getBuffTwo() {
/*  87 */     return this.buffTwo;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ArrayList<Integer> getBuffThree() {
/*  93 */     return this.buffThree;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ArrayList<Integer> getBuffFour() {
/*  99 */     return this.buffFour;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ArrayList<Integer> getBuffFive() {
/* 105 */     return this.buffFive;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ArrayList<Integer> getBuffSix() {
/* 111 */     return this.buffSix;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getLevelPar() {
/* 117 */     return this.levelPar;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 122 */     String retVal = "additionId= " + this.additionId + ", card= " + this.card + ", buffOne= " + this.buffOne + ", buffTwo= " + this.buffTwo + ", buffThree= " + this.buffThree + ", buffFour= " + this.buffFour + ", buffFive= " + this.buffFive + ", buffSix= " + this.buffSix + ", levelPar= " + this.levelPar;
/* 123 */     return retVal;
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\config\bean\ZhenfaBean$StarBean.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */