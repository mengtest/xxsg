/*    */ package com.linlongyx.sanguo.webgame.constant;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public enum MilitaryInsType
/*    */ {
/*  8 */   ThreadIns(1, "主线副本"),
/*  9 */   ArenaIns(2, "竞技场"),
/* 10 */   TeamIns(3, "组队副本"),
/* 11 */   DestinyIns(4, "天命战场"),
/* 12 */   SingleBoss(5, "个人boss"),
/* 13 */   RankBoss(6, "排行boss"),
/* 14 */   WorldBoss(7, "世界boss"),
/* 15 */   GroupBoss(8, "军团boss"),
/* 16 */   Rabit(9, "快速作战"),
/* 17 */   TowerIns(10, "千层塔"),
/* 18 */   GoldIns(11, "日常金币副本"),
/* 19 */   RefineStoneIns(12, "日常精炼石副本"),
/* 20 */   ExpBookIns(13, "日常经验书副本"),
/* 21 */   BreakStoneIns(14, "日常突破石副本"),
/* 22 */   TetureIns(15, "日常宝物强化石副本"),
/* 23 */   PetFoodIns(16, "日常战宠口粮副本"),
/* 24 */   GeneralIns(17, "名将副本"),
/* 25 */   UnparaledIns(18, "三国无双副本"),
/* 26 */   SLG(19, "SLG"),
/* 27 */   MilitaryHelp(20, "军务帮助");
/*    */   
/*    */   private int type;
/*    */   
/*    */   private String name;
/*    */   
/*    */   MilitaryInsType(int type, String name) {
/* 34 */     setType(type);
/* 35 */     setName(name);
/*    */   }
/*    */   
/*    */   public int getType() {
/* 39 */     return this.type;
/*    */   }
/*    */   
/*    */   public void setType(int type) {
/* 43 */     this.type = type;
/*    */   }
/*    */   
/*    */   public String getName() {
/* 47 */     return this.name;
/*    */   }
/*    */   
/*    */   public void setName(String name) {
/* 51 */     this.name = name;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\constant\MilitaryInsType.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */