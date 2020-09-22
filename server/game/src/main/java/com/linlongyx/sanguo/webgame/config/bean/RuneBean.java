/*    */ package com.linlongyx.sanguo.webgame.config.bean;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ public class RuneBean
/*    */ {
/*    */   private int itemId;
/*    */   
/*    */   public int getItemId() {
/* 10 */     return this.itemId;
/*    */   }
/*    */   private int quality; private ArrayList<AttrBean> strengthAttr; private int costCoin;
/*    */   private int suitId;
/*    */   
/*    */   public int getQuality() {
/* 16 */     return this.quality;
/*    */   }
/*    */   private int type;
/*    */   private int mlevel;
/*    */   
/*    */   public ArrayList<AttrBean> getStrengthAttr() {
/* 22 */     return this.strengthAttr;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getCostCoin() {
/* 28 */     return this.costCoin;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getSuitId() {
/* 34 */     return this.suitId;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getType() {
/* 40 */     return this.type;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getMlevel() {
/* 46 */     return this.mlevel;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 51 */     String retVal = "itemId= " + this.itemId + ", quality= " + this.quality + ", strengthAttr= " + this.strengthAttr + ", costCoin= " + this.costCoin + ", suitId= " + this.suitId + ", type= " + this.type + ", mlevel= " + this.mlevel;
/* 52 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\config\bean\RuneBean.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */