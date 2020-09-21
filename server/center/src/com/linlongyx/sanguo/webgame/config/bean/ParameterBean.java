/*    */ package com.linlongyx.sanguo.webgame.config.bean;
/*    */ 
/*    */ import java.util.Map;
/*    */ 
/*    */ public class ParameterBean {
/*    */   private int type;
/*    */   private Map<Integer, SecBean> sec;
/*    */   
/*    */   public int getType() {
/* 10 */     return this.type;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public Map<Integer, SecBean> getSec() {
/* 16 */     return this.sec;
/*    */   }
/*    */   
/*    */   public class SecBean
/*    */   {
/*    */     private String value;
/*    */     private int status;
/*    */     
/*    */     public String getValue() {
/* 25 */       return this.value;
/*    */     }
/*    */ 
/*    */ 
/*    */     
/*    */     public int getStatus() {
/* 31 */       return this.status;
/*    */     }
/*    */ 
/*    */     
/*    */     public String toString() {
/* 36 */       String retVal = "value= " + this.value + ", status= " + this.status;
/* 37 */       return retVal;
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 43 */     String retVal = "type= " + this.type + ", sec= " + this.sec;
/* 44 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\sanguo\webgame\config\bean\ParameterBean.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */