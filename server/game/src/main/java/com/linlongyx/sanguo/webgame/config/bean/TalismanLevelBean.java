/*    */ package com.linlongyx.sanguo.webgame.config.bean;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.Map;
/*    */ 
/*    */ public class TalismanLevelBean {
/*    */   private int talismanId;
/*    */   private Map<Integer, LvBean> lv;
/*    */   
/*    */   public int getTalismanId() {
/* 11 */     return this.talismanId;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public Map<Integer, LvBean> getLv() {
/* 17 */     return this.lv;
/*    */   }
/*    */   
/*    */   public class LvBean
/*    */   {
/*    */     private ArrayList<RewardBean> item;
/*    */     private ArrayList<AttrBean> attr;
/*    */     
/*    */     public ArrayList<RewardBean> getItem() {
/* 26 */       return this.item;
/*    */     }
/*    */     private ArrayList<AttrBean> talentsAttr; private ArrayList<TalentsBean> talents;
/*    */     private ArrayList<RewardBean> furnace;
/*    */     
/*    */     public ArrayList<AttrBean> getAttr() {
/* 32 */       return this.attr;
/*    */     }
/*    */ 
/*    */ 
/*    */     
/*    */     public ArrayList<AttrBean> getTalentsAttr() {
/* 38 */       return this.talentsAttr;
/*    */     }
/*    */ 
/*    */ 
/*    */     
/*    */     public ArrayList<TalentsBean> getTalents() {
/* 44 */       return this.talents;
/*    */     }
/*    */     
/*    */     public class TalentsBean { private int old;
/*    */       private int fresh;
/*    */       
/*    */       public int getOld() {
/* 51 */         return this.old;
/*    */       }
/*    */ 
/*    */       
/*    */       public int getFresh() {
/* 56 */         return this.fresh;
/*    */       }
/*    */ 
/*    */       
/*    */       public String toString() {
/* 61 */         String retVal = "old= " + this.old + ", fresh= " + this.fresh;
/* 62 */         return retVal;
/*    */       } }
/*    */ 
/*    */ 
/*    */ 
/*    */     
/*    */     public ArrayList<RewardBean> getFurnace() {
/* 69 */       return this.furnace;
/*    */     }
/*    */ 
/*    */     
/*    */     public String toString() {
/* 74 */       String retVal = "item= " + this.item + ", attr= " + this.attr + ", talentsAttr= " + this.talentsAttr + ", talents= " + this.talents + ", furnace= " + this.furnace;
/* 75 */       return retVal;
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 81 */     String retVal = "talismanId= " + this.talismanId + ", lv= " + this.lv;
/* 82 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\config\bean\TalismanLevelBean.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */