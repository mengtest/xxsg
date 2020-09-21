/*    */ package com.linlongyx.sanguo.webgame.config.bean;
/*    */ 
/*    */ public class FortuneTimeBean
/*    */ {
/*    */   private int id;
/*    */   
/*    */   public int getId() {
/*  8 */     return this.id;
/*    */   }
/*    */   private int serverType; private int day;
/*    */   private String beginTime;
/*    */   
/*    */   public int getServerType() {
/* 14 */     return this.serverType;
/*    */   }
/*    */   private String endTime;
/*    */   private int type;
/*    */   
/*    */   public int getDay() {
/* 20 */     return this.day;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String getBeginTime() {
/* 26 */     return this.beginTime;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String getEndTime() {
/* 32 */     return this.endTime;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getType() {
/* 38 */     return this.type;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 43 */     String retVal = "id= " + this.id + ", serverType= " + this.serverType + ", day= " + this.day + ", beginTime= " + this.beginTime + ", endTime= " + this.endTime + ", type= " + this.type;
/* 44 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\config\bean\FortuneTimeBean.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */