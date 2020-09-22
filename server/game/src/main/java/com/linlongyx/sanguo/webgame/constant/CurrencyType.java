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
/* 25 */   TotalCharge(11, "vip经验"),
/* 26 */   RecruitToken(12, "招贤令"),
/* 27 */   GuessCoin(13, "竞猜币"),
/* 28 */   InviteCoin(14, "邀请币"),
/* 29 */   SpiritCoin(15, "精魂"),
/* 30 */   PlatformCharge(16, "渠道充值返还"),
/* 31 */   BloodCrystal(17, "血晶"),
/* 32 */   SecritCrystal(18, "秘境水晶");
/*    */   
/*    */   private int type;
/*    */   
/*    */   private String name;
/*    */   
/*    */   private boolean isPlayerType;
/*    */   
/*    */   CurrencyType(int type, String name) {
/* 41 */     this.type = type;
/* 42 */     this.name = name;
/* 43 */     this.isPlayerType = true;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int getType() {
/* 50 */     return this.type;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String getName() {
/* 57 */     return this.name;
/*    */   }
/*    */   
/*    */   public boolean isPlayerType() {
/* 61 */     return this.isPlayerType;
/*    */   }
/*    */   
/*    */   public static CurrencyType getCurrencyType(int type) {
/* 65 */     switch (type) { case 1:
/* 66 */         return COIN;
/* 67 */       case 2: return CCY;
/* 68 */       case 3: return CCY_B;
/* 69 */       case 4: return EXP;
/* 70 */       case 5: return Arena;
/* 71 */       case 6: return Friend;
/* 72 */       case 7: return Prestige;
/* 73 */       case 8: return CrystalStone;
/* 74 */       case 9: return Contribute;
/* 75 */       case 10: return Soul;
/* 76 */       case 11: return TotalCharge;
/* 77 */       case 12: return RecruitToken;
/* 78 */       case 13: return GuessCoin;
/* 79 */       case 14: return InviteCoin;
/* 80 */       case 15: return SpiritCoin;
/* 81 */       case 16: return PlatformCharge;
/* 82 */       case 17: return BloodCrystal;
/* 83 */       case 18: return SecritCrystal; }
/* 84 */      return EMPTY;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\constant\CurrencyType.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */