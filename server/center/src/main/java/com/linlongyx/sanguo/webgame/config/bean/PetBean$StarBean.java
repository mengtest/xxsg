/*    */ package com.linlongyx.sanguo.webgame.config.bean;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class StarBean
/*    */ {
/*    */   private int petSkill;
/*    */   private ArrayList<CardBean> card;
/*    */   private int hurtType;
/*    */   private ArrayList<AttrBean> attr;
/*    */   private int levelPar;
/*    */   
/*    */   public int getPetSkill() {
/* 44 */     return this.petSkill;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ArrayList<CardBean> getCard() {
/* 50 */     return this.card;
/*    */   }
/*    */   
/*    */   public class CardBean {
/*    */     private int type;
/*    */     
/*    */     public int getType() {
/* 57 */       return this.type;
/*    */     }
/*    */     
/*    */     private int id;
/*    */     
/*    */     public int getId() {
/* 63 */       return this.id;
/*    */     }
/*    */     private int num;
/*    */     
/*    */     public int getNum() {
/* 68 */       return this.num;
/*    */     }
/*    */ 
/*    */     
/*    */     public String toString() {
/* 73 */       String retVal = "type= " + this.type + ", id= " + this.id + ", num= " + this.num;
/* 74 */       return retVal;
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getHurtType() {
/* 81 */     return this.hurtType;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ArrayList<AttrBean> getAttr() {
/* 87 */     return this.attr;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getLevelPar() {
/* 93 */     return this.levelPar;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 98 */     String retVal = "petSkill= " + this.petSkill + ", card= " + this.card + ", hurtType= " + this.hurtType + ", attr= " + this.attr + ", levelPar= " + this.levelPar;
/* 99 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\sanguo\webgame\config\bean\PetBean$StarBean.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */