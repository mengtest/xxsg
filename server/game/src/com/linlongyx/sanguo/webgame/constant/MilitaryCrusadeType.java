/*    */ package com.linlongyx.sanguo.webgame.constant;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public enum MilitaryCrusadeType
/*    */ {
/*  8 */   ActionForceAdd(2001, "行动力每300秒回复量增加", 0),
/*  9 */   ActionForceRefuse(2002, "行动力消耗降低", 0),
/* 10 */   ActionForceRefuseTow(2003, "行动力消耗降低（百分比）", 1),
/* 11 */   ActionForceLimitAdd(2004, "行动力上限增加", 0),
/* 12 */   Soul(2005, "行军速度", 1);
/*    */   
/*    */   private int type;
/*    */   
/*    */   private String name;
/*    */   
/*    */   private int addType;
/*    */   
/*    */   MilitaryCrusadeType(int type, String name, int addType) {
/* 21 */     setType(type);
/* 22 */     setName(name);
/* 23 */     setAddType(addType);
/*    */   }
/*    */   public int getType() {
/* 26 */     return this.type;
/*    */   } public void setType(int type) {
/* 28 */     this.type = type;
/*    */   } public String getName() {
/* 30 */     return this.name;
/*    */   } public void setName(String name) {
/* 32 */     this.name = name;
/*    */   }
/*    */   public int getAddType() {
/* 35 */     return this.addType;
/*    */   }
/*    */   
/*    */   public void setAddType(int addType) {
/* 39 */     this.addType = addType;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\constant\MilitaryCrusadeType.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */