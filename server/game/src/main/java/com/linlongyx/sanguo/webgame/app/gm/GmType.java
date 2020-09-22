/*    */ package com.linlongyx.sanguo.webgame.app.gm;
/*    */ 
/*    */ 
/*    */ 
/*    */ public enum GmType
/*    */ {
/*  7 */   Item("item", new ItemGm()),
/*  8 */   Currency("currency", new CurrencyGm()),
/*  9 */   Partner("partner", new PartnerGm()),
/* 10 */   Task("task", new TaskGm()),
/* 11 */   Recruit("recruit", new RecruitGm()),
/* 12 */   Equip("equip", new EquipGm()),
/* 13 */   Shop("shop", new ShopGm()),
/* 14 */   Player("player", new PlayerGm()),
/* 15 */   SingleIns("ins", new SingleInsGm()),
/* 16 */   Boss("boss", new BossGm()),
/* 17 */   Handbook("handbook", new HandbookGm()),
/* 18 */   ResetGm("reset", new ResetGm()),
/* 19 */   ArenaGm("arena", new ArenaGm()),
/* 20 */   Friend("friend", new FriendGm()),
/* 21 */   Mail("mail", new MailGm()),
/* 22 */   Unpara("unpara", new UnparalleledInsGm()),
/* 23 */   RecodeStar("recode", new RecordStarGm()),
/* 24 */   WarPet("pet", new WarPetGm()),
/* 25 */   WarZhenfa("zhenfa", new WarZhenfaGm()),
/* 26 */   GroupGm("group", new GroupGm()),
/* 27 */   SysGm("sys", new SysGm()),
/* 28 */   Ranking("ranking", new RankingGm()),
/* 29 */   General("gen", new GeneralGm()),
/* 30 */   Activity("activity", new ActivityGm()),
/* 31 */   Team("team", new TeamGm()),
/* 32 */   Turnplate("turnplate", new TurnplateGm()),
/* 33 */   Cross("cross", new CrossGm()),
/* 34 */   Military("military", new MilitaryGm()),
/* 35 */   Invite("invite", new InviteGm()),
/* 36 */   LuckyMoney("LuckyMoney", new LuckyMoneyGm()),
/* 37 */   NumTestGm("num", new NumTestGm());
/*    */   
/*    */   private String type;
/*    */   private IGm gm;
/*    */   
/*    */   GmType(String type, IGm gm) {
/* 43 */     this.type = type;
/* 44 */     this.gm = gm;
/*    */   }
/*    */   
/*    */   public String getType() {
/* 48 */     return this.type;
/*    */   }
/*    */   
/*    */   public void setType(String type) {
/* 52 */     this.type = type;
/*    */   }
/*    */   
/*    */   public IGm getGm() {
/* 56 */     return this.gm;
/*    */   }
/*    */   
/*    */   public void setGm(IGm gm) {
/* 60 */     this.gm = gm;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\app\gm\GmType.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */