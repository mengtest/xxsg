/*    */ package com.linlongyx.sanguo.webgame.constant;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public enum LimitCountType
/*    */ {
/*  8 */   singleDone(0, "大于或等于"),
/*  9 */   singleEqual(1, "小于等于"),
/* 10 */   cumDone(2, "等于"),
/* 11 */   cumMoreDone(3, "可以领取多少等于");
/*    */   
/*    */   private int type;
/*    */   
/*    */   private String name;
/*    */   
/*    */   LimitCountType(int type, String name) {
/* 18 */     setType(type);
/* 19 */     setName(name);
/*    */   }
/*    */   public int getType() {
/* 22 */     return this.type;
/*    */   } public void setType(int type) {
/* 24 */     this.type = type;
/*    */   } public String getName() {
/* 26 */     return this.name;
/*    */   } public void setName(String name) {
/* 28 */     this.name = name;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\constant\LimitCountType.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */