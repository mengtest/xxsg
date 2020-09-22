/*     */ package com.linlongyx.sanguo.webgame.config.bean;
/*     */ import java.util.ArrayList;
/*     */ 
/*     */ public class EquipSuitBean {
/*     */   private int equipLevel;
/*     */   private ArrayList<SuitABean> suitA;
/*     */   private ArrayList<SuitBBean> suitB;
/*     */   private ArrayList<SuitCBean> suitC;
/*     */   
/*     */   public int getEquipLevel() {
/*  11 */     return this.equipLevel;
/*     */   }
/*     */   
/*     */   private Map<Integer, EquipPartBean> equipPart;
/*     */   
/*     */   public ArrayList<SuitABean> getSuitA() {
/*  17 */     return this.suitA;
/*     */   }
/*     */   
/*     */   public class SuitABean { private int id;
/*     */     private int num;
/*     */     
/*     */     public int getId() {
/*  24 */       return this.id;
/*     */     }
/*     */ 
/*     */     
/*     */     public int getNum() {
/*  29 */       return this.num;
/*     */     }
/*     */ 
/*     */     
/*     */     public String toString() {
/*  34 */       String retVal = "id= " + this.id + ", num= " + this.num;
/*  35 */       return retVal;
/*     */     } }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ArrayList<SuitBBean> getSuitB() {
/*  42 */     return this.suitB;
/*     */   }
/*     */   
/*     */   public class SuitBBean { private int id;
/*     */     private int num;
/*     */     
/*     */     public int getId() {
/*  49 */       return this.id;
/*     */     }
/*     */ 
/*     */     
/*     */     public int getNum() {
/*  54 */       return this.num;
/*     */     }
/*     */ 
/*     */     
/*     */     public String toString() {
/*  59 */       String retVal = "id= " + this.id + ", num= " + this.num;
/*  60 */       return retVal;
/*     */     } }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ArrayList<SuitCBean> getSuitC() {
/*  67 */     return this.suitC;
/*     */   }
/*     */   
/*     */   public class SuitCBean { private int id;
/*     */     private int num;
/*     */     
/*     */     public int getId() {
/*  74 */       return this.id;
/*     */     }
/*     */ 
/*     */     
/*     */     public int getNum() {
/*  79 */       return this.num;
/*     */     }
/*     */ 
/*     */     
/*     */     public String toString() {
/*  84 */       String retVal = "id= " + this.id + ", num= " + this.num;
/*  85 */       return retVal;
/*     */     } }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Map<Integer, EquipPartBean> getEquipPart() {
/*  92 */     return this.equipPart;
/*     */   }
/*     */   public class EquipPartBean { private int equipId;
/*     */     private ArrayList<AttrBean> strengthAttr;
/*     */     private int costCoin;
/*     */     private ArrayList<AttrBean> purifyAttr;
/*     */     private int costExp;
/*     */     
/*     */     public int getEquipId() {
/* 101 */       return this.equipId;
/*     */     }
/*     */     private ArrayList<AttrBean> zhuhunAttr; private int costZhstone;
/*     */     private ArrayList<AttrBean> artificeAttr;
/*     */     
/*     */     public ArrayList<AttrBean> getStrengthAttr() {
/* 107 */       return this.strengthAttr;
/*     */     }
/*     */     private int artificeStone; private ArrayList<AttrBean> artifiAdd;
/*     */     private ArrayList<AttrBean> attStar;
/*     */     
/*     */     public int getCostCoin() {
/* 113 */       return this.costCoin;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public ArrayList<AttrBean> getPurifyAttr() {
/* 119 */       return this.purifyAttr;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public int getCostExp() {
/* 125 */       return this.costExp;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public ArrayList<AttrBean> getZhuhunAttr() {
/* 131 */       return this.zhuhunAttr;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public int getCostZhstone() {
/* 137 */       return this.costZhstone;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public ArrayList<AttrBean> getArtificeAttr() {
/* 143 */       return this.artificeAttr;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public int getArtificeStone() {
/* 149 */       return this.artificeStone;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public ArrayList<AttrBean> getArtifiAdd() {
/* 155 */       return this.artifiAdd;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public ArrayList<AttrBean> getAttStar() {
/* 161 */       return this.attStar;
/*     */     }
/*     */ 
/*     */     
/*     */     public String toString() {
/* 166 */       String retVal = "equipId= " + this.equipId + ", strengthAttr= " + this.strengthAttr + ", costCoin= " + this.costCoin + ", purifyAttr= " + this.purifyAttr + ", costExp= " + this.costExp + ", zhuhunAttr= " + this.zhuhunAttr + ", costZhstone= " + this.costZhstone + ", artificeAttr= " + this.artificeAttr + ", artificeStone= " + this.artificeStone + ", artifiAdd= " + this.artifiAdd + ", attStar= " + this.attStar;
/* 167 */       return retVal;
/*     */     } }
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 173 */     String retVal = "equipLevel= " + this.equipLevel + ", suitA= " + this.suitA + ", suitB= " + this.suitB + ", suitC= " + this.suitC + ", equipPart= " + this.equipPart;
/* 174 */     return retVal;
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\config\bean\EquipSuitBean.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */