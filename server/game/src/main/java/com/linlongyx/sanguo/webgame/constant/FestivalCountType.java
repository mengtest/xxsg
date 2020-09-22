/*    */ package com.linlongyx.sanguo.webgame.constant;
/*    */ 
/*    */ 
/*    */ 
/*    */ public enum FestivalCountType
/*    */ {
/*  7 */   less(0, "单次小于或者等于"),
/*  8 */   singleDone(1, "单次达到大于或等于"),
/*  9 */   singleEqual(2, "单次达到等于"),
/* 10 */   cumDone(3, "累计");
/*    */   
/*    */   private int type;
/*    */   
/*    */   private String name;
/*    */   
/*    */   FestivalCountType(int type, String name) {
/* 17 */     setType(type);
/* 18 */     setName(name);
/*    */   }
/*    */   public int getType() {
/* 21 */     return this.type;
/*    */   } public void setType(int type) {
/* 23 */     this.type = type;
/*    */   } public String getName() {
/* 25 */     return this.name;
/*    */   } public void setName(String name) {
/* 27 */     this.name = name;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\constant\FestivalCountType.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */