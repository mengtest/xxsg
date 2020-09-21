/*    */ package com.linlongyx.sanguo.webgame.config.bean;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ public class EffectBean {
/*    */   private int id;
/*    */   private int side;
/*    */   
/*    */   public int getId() {
/* 10 */     return this.id;
/*    */   }
/*    */   private int target; private int range; private int demageType;
/*    */   private ArrayList<Integer> power;
/*    */   
/*    */   public int getSide() {
/* 16 */     return this.side;
/*    */   }
/*    */   private ArrayList<Integer> affectType; private ArrayList<AffectParaBean> affectPara;
/*    */   private int dodge;
/*    */   
/*    */   public int getTarget() {
/* 22 */     return this.target;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getRange() {
/* 28 */     return this.range;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getDemageType() {
/* 34 */     return this.demageType;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ArrayList<Integer> getPower() {
/* 40 */     return this.power;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ArrayList<Integer> getAffectType() {
/* 46 */     return this.affectType;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ArrayList<AffectParaBean> getAffectPara() {
/* 52 */     return this.affectPara;
/*    */   }
/*    */   public class AffectParaBean { private int per;
/*    */     private int value;
/*    */     private int level;
/*    */     
/*    */     public int getPer() {
/* 59 */       return this.per;
/*    */     }
/*    */ 
/*    */     
/*    */     public int getValue() {
/* 64 */       return this.value;
/*    */     }
/*    */ 
/*    */     
/*    */     public int getLevel() {
/* 69 */       return this.level;
/*    */     }
/*    */ 
/*    */     
/*    */     public String toString() {
/* 74 */       String retVal = "per= " + this.per + ", value= " + this.value + ", level= " + this.level;
/* 75 */       return retVal;
/*    */     } }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int getDodge() {
/* 82 */     return this.dodge;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 87 */     String retVal = "id= " + this.id + ", side= " + this.side + ", target= " + this.target + ", range= " + this.range + ", demageType= " + this.demageType + ", power= " + this.power + ", affectType= " + this.affectType + ", affectPara= " + this.affectPara + ", dodge= " + this.dodge;
/* 88 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\config\bean\EffectBean.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */