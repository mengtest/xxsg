/*    */ package com.linlongyx.sanguo.webgame.config.bean;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ public class FashionBean {
/*    */   private int fashionId;
/*    */   private String fashionNameM;
/*    */   
/*    */   public int getFashionId() {
/* 10 */     return this.fashionId;
/*    */   }
/*    */   
/*    */   private String fashionNameW;
/*    */   
/*    */   public String getFashionNameM() {
/* 16 */     return this.fashionNameM;
/*    */   }
/*    */   
/*    */   private ArrayList<RewardBean> item;
/*    */   
/*    */   public String getFashionNameW() {
/* 22 */     return this.fashionNameW;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ArrayList<RewardBean> getItem() {
/* 28 */     return this.item;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 33 */     String retVal = "fashionId= " + this.fashionId + ", fashionNameM= " + this.fashionNameM + ", fashionNameW= " + this.fashionNameW + ", item= " + this.item;
/* 34 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\config\bean\FashionBean.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */