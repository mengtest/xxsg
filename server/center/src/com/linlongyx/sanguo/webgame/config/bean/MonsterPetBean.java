/*    */ package com.linlongyx.sanguo.webgame.config.bean;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ public class MonsterPetBean {
/*    */   private int id;
/*    */   private String name;
/*    */   
/*    */   public int getId() {
/* 10 */     return this.id;
/*    */   }
/*    */   private String rid; private int quality; private int star;
/*    */   private int petSkill;
/*    */   
/*    */   public String getName() {
/* 16 */     return this.name;
/*    */   }
/*    */   private int hurtType;
/*    */   private ArrayList<AttrBean> attr;
/*    */   
/*    */   public String getRid() {
/* 22 */     return this.rid;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getQuality() {
/* 28 */     return this.quality;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getStar() {
/* 34 */     return this.star;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getPetSkill() {
/* 40 */     return this.petSkill;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getHurtType() {
/* 46 */     return this.hurtType;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ArrayList<AttrBean> getAttr() {
/* 52 */     return this.attr;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 57 */     String retVal = "id= " + this.id + ", name= " + this.name + ", rid= " + this.rid + ", quality= " + this.quality + ", star= " + this.star + ", petSkill= " + this.petSkill + ", hurtType= " + this.hurtType + ", attr= " + this.attr;
/* 58 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\sanguo\webgame\config\bean\MonsterPetBean.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */