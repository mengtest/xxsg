/*    */ package com.linlongyx.sanguo.webgame.config.bean;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ public class ZhenfaRestraintBean
/*    */ {
/*    */   private int id;
/*    */   
/*    */   public int getId() {
/* 10 */     return this.id;
/*    */   }
/*    */   private String name;
/*    */   private ArrayList<BuffBean> buff;
/*    */   
/*    */   public String getName() {
/* 16 */     return this.name;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ArrayList<BuffBean> getBuff() {
/* 22 */     return this.buff;
/*    */   }
/*    */   
/*    */   public class BuffBean { private int zhenfa;
/*    */     private int id;
/*    */     
/*    */     public int getZhenfa() {
/* 29 */       return this.zhenfa;
/*    */     }
/*    */ 
/*    */ 
/*    */     
/*    */     public int getId() {
/* 35 */       return this.id;
/*    */     }
/*    */ 
/*    */     
/*    */     public String toString() {
/* 40 */       String retVal = "zhenfa= " + this.zhenfa + ", id= " + this.id;
/* 41 */       return retVal;
/*    */     } }
/*    */ 
/*    */ 
/*    */   
/*    */   public String toString() {
/* 47 */     String retVal = "id= " + this.id + ", name= " + this.name + ", buff= " + this.buff;
/* 48 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\config\bean\ZhenfaRestraintBean.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */