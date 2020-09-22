/*    */ package com.linlongyx.sanguo.webgame.constant;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public enum EquipPart
/*    */ {
/*  9 */   HELMET(1, "武器"),
/* 10 */   WEAPON(2, "头盔"),
/* 11 */   BELT(3, "铠甲"),
/* 12 */   CLOTH(4, "鞋子"),
/* 13 */   RING(5, "戒指"),
/* 14 */   WRISTER(6, "兽印");
/*    */   
/*    */   private int part;
/*    */   
/*    */   private String name;
/*    */   
/*    */   EquipPart(int part, String name) {
/* 21 */     setPart(part);
/* 22 */     setName(name);
/*    */   }
/*    */   public int getPart() {
/* 25 */     return this.part;
/*    */   } public void setPart(int part) {
/* 27 */     this.part = part;
/*    */   } public String getName() {
/* 29 */     return this.name;
/*    */   } public void setName(String name) {
/* 31 */     this.name = name;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\constant\EquipPart.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */