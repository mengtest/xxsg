/*    */ package com.linlongyx.sanguo.webgame.constant;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public enum CurrencyType
/*    */ {
/* 14 */   EMPTY(0, "空类型"),
/* 15 */   COIN(1, "银币"),
/* 16 */   CCY(2, "元宝"),
/* 17 */   CCY_B(3, "绑定元宝"),
/* 18 */   EXP(4, "经验"),
/* 19 */   Arena(5, "竞技点"),
/* 20 */   Friend(6, "友情点"),
/* 21 */   Prestige(7, "威名"),
/* 22 */   CrystalStone(8, "晶石"),
/* 23 */   Contribute(9, "个人贡献"),
/* 24 */   Soul(10, "将魂"),
/* 25 */   TotalCharge(11, "累计充值"),
/* 26 */   RecruitToken(12, "招贤令"),
/* 27 */   GuessCoin(13, "竞猜币"),
/* 28 */   InviteCoin(14, "邀请币");
/*    */   
/*    */   private int type;
/*    */   
/*    */   private String name;
/*    */   
/*    */   private boolean isPlayerType;
/*    */   
/*    */   CurrencyType(int type, String name) {
/* 37 */     this.type = type;
/* 38 */     this.name = name;
/* 39 */     this.isPlayerType = true;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int getType() {
/* 46 */     return this.type;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String getName() {
/* 53 */     return this.name;
/*    */   }
/*    */   
/*    */   public boolean isPlayerType() {
/* 57 */     return this.isPlayerType;
/*    */   }
/*    */   
/*    */   public static CurrencyType getCurrencyType(int type) {
/* 61 */     switch (type) { case 1:
/* 62 */         return COIN;
/* 63 */       case 2: return CCY;
/* 64 */       case 3: return CCY_B;
/* 65 */       case 4: return EXP;
/* 66 */       case 5: return Arena;
/* 67 */       case 6: return Friend;
/* 68 */       case 7: return Prestige;
/* 69 */       case 8: return CrystalStone;
/* 70 */       case 9: return Contribute;
/* 71 */       case 10: return Soul;
/* 72 */       case 11: return TotalCharge;
/* 73 */       case 12: return RecruitToken;
/* 74 */       case 13: return GuessCoin;
/* 75 */       case 14: return InviteCoin; }
/* 76 */      return EMPTY;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\sanguo\webgame\constant\CurrencyType.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */