/*    */ package com.linlongyx.sanguo.webgame.config.bean;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ public class ModelParaBean
/*    */ {
/*    */   private int id;
/*    */   
/*    */   public int getId() {
/* 10 */     return this.id;
/*    */   }
/*    */   private String rid;
/*    */   private ArrayList<ActTimeBean> actTime;
/*    */   
/*    */   public String getRid() {
/* 16 */     return this.rid;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ArrayList<ActTimeBean> getActTime() {
/* 22 */     return this.actTime;
/*    */   }
/*    */   
/*    */   public class ActTimeBean { private int actType;
/*    */     private int time;
/*    */     
/*    */     public int getActType() {
/* 29 */       return this.actType;
/*    */     }
/*    */ 
/*    */     
/*    */     public int getTime() {
/* 34 */       return this.time;
/*    */     }
/*    */ 
/*    */     
/*    */     public String toString() {
/* 39 */       String retVal = "actType= " + this.actType + ", time= " + this.time;
/* 40 */       return retVal;
/*    */     } }
/*    */ 
/*    */ 
/*    */   
/*    */   public String toString() {
/* 46 */     String retVal = "id= " + this.id + ", rid= " + this.rid + ", actTime= " + this.actTime;
/* 47 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\config\bean\ModelParaBean.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */