/*    */ package com.linlongyx.sanguo.webgame.config.bean;
/*    */ 
/*    */ public class PlayerBean {
/*    */   private int sex;
/*    */   private String name;
/*    */   
/*    */   public int getSex() {
/*  8 */     return this.sex;
/*    */   }
/*    */   
/*    */   private String rid;
/*    */   
/*    */   public String getName() {
/* 14 */     return this.name;
/*    */   }
/*    */   
/*    */   private int size;
/*    */   
/*    */   public String getRid() {
/* 20 */     return this.rid;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getSize() {
/* 26 */     return this.size;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 31 */     String retVal = "sex= " + this.sex + ", name= " + this.name + ", rid= " + this.rid + ", size= " + this.size;
/* 32 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\config\bean\PlayerBean.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */