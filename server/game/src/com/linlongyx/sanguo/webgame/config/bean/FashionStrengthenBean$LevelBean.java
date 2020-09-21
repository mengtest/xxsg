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
/*    */ public class LevelBean
/*    */ {
/*    */   private ArrayList<RewardBean> consumeItem;
/*    */   private ArrayList<AttrBean> attr;
/*    */   
/*    */   public ArrayList<RewardBean> getConsumeItem() {
/* 26 */     return this.consumeItem;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ArrayList<AttrBean> getAttr() {
/* 32 */     return this.attr;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 37 */     String retVal = "consumeItem= " + this.consumeItem + ", attr= " + this.attr;
/* 38 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\config\bean\FashionStrengthenBean$LevelBean.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */