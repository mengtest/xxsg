/*    */ package com.linlongyx.sanguo.webgame.config.bean;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ public class SecretiBean {
/*    */   private int id;
/*    */   private ArrayList<Integer> sercetiIns;
/*    */   
/*    */   public int getId() {
/* 10 */     return this.id;
/*    */   }
/*    */   
/*    */   private int passNum;
/*    */   
/*    */   public ArrayList<Integer> getSercetiIns() {
/* 16 */     return this.sercetiIns;
/*    */   }
/*    */   
/*    */   private int lastId;
/*    */   
/*    */   public int getPassNum() {
/* 22 */     return this.passNum;
/*    */   }
/*    */   
/*    */   private int shop;
/*    */   
/*    */   public int getLastId() {
/* 28 */     return this.lastId;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getShop() {
/* 34 */     return this.shop;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 39 */     String retVal = "id= " + this.id + ", sercetiIns= " + this.sercetiIns + ", passNum= " + this.passNum + ", lastId= " + this.lastId + ", shop= " + this.shop;
/* 40 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\config\bean\SecretiBean.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */