/*    */ package com.linlongyx.sanguo.webgame.config.bean;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.Map;
/*    */ 
/*    */ public class TreasureSuitBean {
/*    */   private int treasureLevel;
/*    */   private Map<Integer, TreasurePartBean> treasurePart;
/*    */   
/*    */   public int getTreasureLevel() {
/* 11 */     return this.treasureLevel;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public Map<Integer, TreasurePartBean> getTreasurePart() {
/* 17 */     return this.treasurePart;
/*    */   }
/*    */   
/*    */   public class TreasurePartBean
/*    */   {
/*    */     private int treasureId;
/*    */     private ArrayList<AttrBean> strengthAttr;
/*    */     
/*    */     public int getTreasureId() {
/* 26 */       return this.treasureId;
/*    */     }
/*    */     private int costCoin; private ArrayList<AttrBean> purifyAttr;
/*    */     private int costExp;
/*    */     
/*    */     public ArrayList<AttrBean> getStrengthAttr() {
/* 32 */       return this.strengthAttr;
/*    */     }
/*    */ 
/*    */ 
/*    */     
/*    */     public int getCostCoin() {
/* 38 */       return this.costCoin;
/*    */     }
/*    */ 
/*    */ 
/*    */     
/*    */     public ArrayList<AttrBean> getPurifyAttr() {
/* 44 */       return this.purifyAttr;
/*    */     }
/*    */ 
/*    */ 
/*    */     
/*    */     public int getCostExp() {
/* 50 */       return this.costExp;
/*    */     }
/*    */ 
/*    */     
/*    */     public String toString() {
/* 55 */       String retVal = "treasureId= " + this.treasureId + ", strengthAttr= " + this.strengthAttr + ", costCoin= " + this.costCoin + ", purifyAttr= " + this.purifyAttr + ", costExp= " + this.costExp;
/* 56 */       return retVal;
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 62 */     String retVal = "treasureLevel= " + this.treasureLevel + ", treasurePart= " + this.treasurePart;
/* 63 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\config\bean\TreasureSuitBean.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */