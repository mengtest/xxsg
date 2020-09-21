/*    */ package com.linlongyx.sanguo.webgame.constant;
/*    */ 
/*    */ 
/*    */ 
/*    */ public enum TowerCountType
/*    */ {
/*  7 */   allSurvival(1, "全部武将存活"),
/*  8 */   surp60(2, "剩余总血量60%"),
/*  9 */   deathLessTwo(3, "阵亡人数不超过2个"),
/* 10 */   allWin(4, "没有条件");
/*    */   
/*    */   private int type;
/*    */   
/*    */   private String name;
/*    */   
/*    */   TowerCountType(int type, String name) {
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


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\constant\TowerCountType.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */