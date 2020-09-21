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
/*     */ public class EquipPartBean
/*     */ {
/*     */   private int equipId;
/*     */   private ArrayList<AttrBean> strengthAttr;
/*     */   private int costCoin;
/*     */   private ArrayList<AttrBean> purifyAttr;
/*     */   private int costExp;
/*     */   private ArrayList<AttrBean> zhuhunAttr;
/*     */   private int costZhstone;
/*     */   private ArrayList<AttrBean> artificeAttr;
/*     */   private int artificeStone;
/*     */   private ArrayList<AttrBean> artifiAdd;
/*     */   private ArrayList<AttrBean> attStar;
/*     */   
/*     */   public int getEquipId() {
/* 101 */     return this.equipId;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ArrayList<AttrBean> getStrengthAttr() {
/* 107 */     return this.strengthAttr;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getCostCoin() {
/* 113 */     return this.costCoin;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ArrayList<AttrBean> getPurifyAttr() {
/* 119 */     return this.purifyAttr;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getCostExp() {
/* 125 */     return this.costExp;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ArrayList<AttrBean> getZhuhunAttr() {
/* 131 */     return this.zhuhunAttr;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getCostZhstone() {
/* 137 */     return this.costZhstone;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ArrayList<AttrBean> getArtificeAttr() {
/* 143 */     return this.artificeAttr;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getArtificeStone() {
/* 149 */     return this.artificeStone;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ArrayList<AttrBean> getArtifiAdd() {
/* 155 */     return this.artifiAdd;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ArrayList<AttrBean> getAttStar() {
/* 161 */     return this.attStar;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 166 */     String retVal = "equipId= " + this.equipId + ", strengthAttr= " + this.strengthAttr + ", costCoin= " + this.costCoin + ", purifyAttr= " + this.purifyAttr + ", costExp= " + this.costExp + ", zhuhunAttr= " + this.zhuhunAttr + ", costZhstone= " + this.costZhstone + ", artificeAttr= " + this.artificeAttr + ", artificeStone= " + this.artificeStone + ", artifiAdd= " + this.artifiAdd + ", attStar= " + this.attStar;
/* 167 */     return retVal;
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\config\bean\EquipSuitBean$EquipPartBean.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */