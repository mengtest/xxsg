/*    */ package com.linlongyx.sanguo.webgame.config.bean;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.Map;
/*    */ 
/*    */ public class RecruitLibBean {
/*    */   private int libId;
/*    */   private Map<Integer, IdBean> id;
/*    */   
/*    */   public int getLibId() {
/* 11 */     return this.libId;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public Map<Integer, IdBean> getId() {
/* 17 */     return this.id;
/*    */   }
/*    */   
/*    */   public class IdBean {
/*    */     private ArrayList<RewardBean> goods;
/*    */     private int prob;
/*    */     private int isShow;
/*    */     
/*    */     public ArrayList<RewardBean> getGoods() {
/* 26 */       return this.goods;
/*    */     }
/*    */ 
/*    */ 
/*    */     
/*    */     public int getProb() {
/* 32 */       return this.prob;
/*    */     }
/*    */ 
/*    */ 
/*    */     
/*    */     public int getIsShow() {
/* 38 */       return this.isShow;
/*    */     }
/*    */ 
/*    */     
/*    */     public String toString() {
/* 43 */       String retVal = "goods= " + this.goods + ", prob= " + this.prob + ", isShow= " + this.isShow;
/* 44 */       return retVal;
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 50 */     String retVal = "libId= " + this.libId + ", id= " + this.id;
/* 51 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\config\bean\RecruitLibBean.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */