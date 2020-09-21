/*    */ package com.linlongyx.sanguo.webgame.config.bean;
/*    */ 
/*    */ import java.util.Map;
/*    */ 
/*    */ public class ParameterBean {
/*    */   private int type;
/*    */   
/*    */   public int getType() {
/*  9 */     return this.type;
/*    */   }
/*    */   
/*    */   private Map<Integer, SecBean> sec;
/*    */   
/*    */   public Map<Integer, SecBean> getSec() {
/* 15 */     return this.sec;
/*    */   }
/*    */   
/*    */   public class SecBean
/*    */   {
/*    */     private String value;
/*    */     private int status;
/*    */     
/*    */     public String getValue() {
/* 24 */       return this.value;
/*    */     }
/*    */ 
/*    */ 
/*    */     
/*    */     public int getStatus() {
/* 30 */       return this.status;
/*    */     }
/*    */ 
/*    */     
/*    */     public String toString() {
/* 35 */       String retVal = "value= " + this.value + ", status= " + this.status;
/* 36 */       return retVal;
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 42 */     String retVal = "type= " + this.type + ", sec= " + this.sec;
/* 43 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\config\bean\ParameterBean.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */