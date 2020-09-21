/*    */ package com.linlongyx.sanguo.webgame.constant;
/*    */ 
/*    */ 
/*    */ 
/*    */ public enum RedNoticeConstant
/*    */ {
/*  7 */   GroupDeal(11, 1, "军团申请通过"),
/*  8 */   RechargeAct(12, 0, "限时累充活动红点"),
/*  9 */   Divine(13, 0, "占卜红点"),
/* 10 */   DestinyTimes(74, 0, "天命战场次数"),
/* 11 */   DestinyReward(74, 1, "天命战场挑战"),
/* 12 */   RankAct(20, 0, "开服竞赛红点"),
/* 13 */   CrossRankAct(21, 0, "跨服竞赛红点"),
/* 14 */   RecordStar(30, 0, "三国志红点"),
/* 15 */   WarPet(40, 0, "战宠"),
/* 16 */   Mounts(50, 0, "坐骑"),
/* 17 */   FirstCharge(60, 0, "首充"),
/* 18 */   ChargeRebate(70, 0, "充值抽元宝"),
/* 19 */   KungFu(80, 0, "招式"),
/* 20 */   Stage(90, 0, "将台"),
/* 21 */   RecordStarSpec(100, 0, "三国志特效"),
/* 22 */   MilitaryAskHelp(110, 0, "军务府协助"),
/* 23 */   WarZhenfa(120, 0, "阵法"),
/* 24 */   Souls(130, 0, "英魂"),
/* 25 */   TaskPreView(140, 0, "预览功能任务完成红点"),
/* 26 */   Zodiac(150, 0, "星宫任务完成红点");
/*    */   
/*    */   private int sys;
/*    */   
/*    */   private int index;
/*    */   private String name;
/*    */   
/*    */   RedNoticeConstant(int sys, int index, String name) {
/* 34 */     this.sys = sys;
/* 35 */     this.index = index;
/* 36 */     this.name = name;
/*    */   }
/*    */   
/*    */   public int getSys() {
/* 40 */     return this.sys;
/*    */   }
/*    */   
/*    */   public int getIndex() {
/* 44 */     return this.index;
/*    */   }
/*    */   
/*    */   public String getName() {
/* 48 */     return this.name;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\constant\RedNoticeConstant.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */