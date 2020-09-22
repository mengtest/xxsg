/*    */ package com.linlongyx.sanguo.webgame.config.bean;
/*    */ 
/*    */ public class LanguageBean
/*    */ {
/*    */   private int id;
/*    */   
/*    */   public int getId() {
/*  8 */     return this.id;
/*    */   }
/*    */   private String text;
/*    */   private String serverParameter;
/*    */   
/*    */   public String getText() {
/* 14 */     return this.text;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String getServerParameter() {
/* 20 */     return this.serverParameter;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 25 */     String retVal = "id= " + this.id + ", text= " + this.text + ", serverParameter= " + this.serverParameter;
/* 26 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\config\bean\LanguageBean.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */