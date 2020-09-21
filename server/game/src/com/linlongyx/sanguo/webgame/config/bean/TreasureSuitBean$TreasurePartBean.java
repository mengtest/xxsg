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
/*    */ public class TreasurePartBean
/*    */ {
/*    */   private int treasureId;
/*    */   private ArrayList<AttrBean> strengthAttr;
/*    */   private int costCoin;
/*    */   private ArrayList<AttrBean> purifyAttr;
/*    */   private int costExp;
/*    */   
/*    */   public int getTreasureId() {
/* 26 */     return this.treasureId;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ArrayList<AttrBean> getStrengthAttr() {
/* 32 */     return this.strengthAttr;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getCostCoin() {
/* 38 */     return this.costCoin;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ArrayList<AttrBean> getPurifyAttr() {
/* 44 */     return this.purifyAttr;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getCostExp() {
/* 50 */     return this.costExp;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 55 */     String retVal = "treasureId= " + this.treasureId + ", strengthAttr= " + this.strengthAttr + ", costCoin= " + this.costCoin + ", purifyAttr= " + this.purifyAttr + ", costExp= " + this.costExp;
/* 56 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\config\bean\TreasureSuitBean$TreasurePartBean.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */