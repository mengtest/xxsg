/*    */ package com.linlongyx.sanguo.webgame.constant;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public enum ItemUseType
/*    */ {
/* 10 */   PARTNER_EXP_TYPE(3, "战士经验"),
/* 11 */   MILIARY_OFFICER(4, "武将碎片");
/*    */   
/*    */   private int type;
/*    */   private String dec;
/*    */   
/*    */   ItemUseType(int type, String dec) {
/* 17 */     this.type = type;
/* 18 */     this.dec = dec;
/*    */   }
/*    */   
/*    */   public int getType() {
/* 22 */     return this.type;
/*    */   }
/*    */   
/*    */   public void setType(int type) {
/* 26 */     this.type = type;
/*    */   }
/*    */   
/*    */   public String getDec() {
/* 30 */     return this.dec;
/*    */   }
/*    */   
/*    */   public void setDec(String dec) {
/* 34 */     this.dec = dec;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\constant\ItemUseType.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */