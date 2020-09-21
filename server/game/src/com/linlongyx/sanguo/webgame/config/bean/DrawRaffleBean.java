/*    */ package com.linlongyx.sanguo.webgame.config.bean;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.Map;
/*    */ 
/*    */ public class DrawRaffleBean {
/*    */   private int id;
/*    */   private Map<Integer, RoundBean> round;
/*    */   
/*    */   public int getId() {
/* 11 */     return this.id;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public Map<Integer, RoundBean> getRound() {
/* 17 */     return this.round;
/*    */   }
/*    */   
/*    */   public class RoundBean {
/*    */     private int normalCard;
/*    */     private ArrayList<GoldCardBean> goldCard;
/*    */     private int rareId;
/*    */     
/*    */     public int getNormalCard() {
/* 26 */       return this.normalCard;
/*    */     }
/*    */ 
/*    */ 
/*    */     
/*    */     public ArrayList<GoldCardBean> getGoldCard() {
/* 32 */       return this.goldCard;
/*    */     }
/*    */     
/*    */     public class GoldCardBean {
/*    */       private int num;
/*    */       
/*    */       public int getNum() {
/* 39 */         return this.num;
/*    */       }
/*    */       private int per;
/*    */       
/*    */       public int getPer() {
/* 44 */         return this.per;
/*    */       }
/*    */ 
/*    */       
/*    */       public String toString() {
/* 49 */         String retVal = "num= " + this.num + ", per= " + this.per;
/* 50 */         return retVal;
/*    */       }
/*    */     }
/*    */ 
/*    */ 
/*    */     
/*    */     public int getRareId() {
/* 57 */       return this.rareId;
/*    */     }
/*    */ 
/*    */     
/*    */     public String toString() {
/* 62 */       String retVal = "normalCard= " + this.normalCard + ", goldCard= " + this.goldCard + ", rareId= " + this.rareId;
/* 63 */       return retVal;
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 69 */     String retVal = "id= " + this.id + ", round= " + this.round;
/* 70 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\config\bean\DrawRaffleBean.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */