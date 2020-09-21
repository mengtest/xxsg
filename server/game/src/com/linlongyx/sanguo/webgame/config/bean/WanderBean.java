/*    */ package com.linlongyx.sanguo.webgame.config.bean;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ public class WanderBean {
/*    */   private int id;
/*    */   private int vip;
/*    */   
/*    */   public int getId() {
/* 10 */     return this.id;
/*    */   }
/*    */   
/*    */   private int level;
/*    */   
/*    */   public int getVip() {
/* 16 */     return this.vip;
/*    */   }
/*    */   
/*    */   private ArrayList<Integer> entry;
/*    */   
/*    */   public int getLevel() {
/* 22 */     return this.level;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ArrayList<Integer> getEntry() {
/* 28 */     return this.entry;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 33 */     String retVal = "id= " + this.id + ", vip= " + this.vip + ", level= " + this.level + ", entry= " + this.entry;
/* 34 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\config\bean\WanderBean.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */