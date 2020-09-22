/*    */ package com.linlongyx.sanguo.webgame.config.bean;
/*    */ 
/*    */ public class QuestionnaireBean
/*    */ {
/*    */   private int id;
/*    */   
/*    */   public int getId() {
/*  8 */     return this.id;
/*    */   }
/*    */   private int serverType; private String beginTime;
/*    */   private String endTime;
/*    */   
/*    */   public int getServerType() {
/* 14 */     return this.serverType;
/*    */   }
/*    */   private int day;
/*    */   private int type;
/*    */   
/*    */   public String getBeginTime() {
/* 20 */     return this.beginTime;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String getEndTime() {
/* 26 */     return this.endTime;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getDay() {
/* 32 */     return this.day;
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
/* 43 */     String retVal = "id= " + this.id + ", serverType= " + this.serverType + ", beginTime= " + this.beginTime + ", endTime= " + this.endTime + ", day= " + this.day + ", type= " + this.type;
/* 44 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\config\bean\QuestionnaireBean.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */