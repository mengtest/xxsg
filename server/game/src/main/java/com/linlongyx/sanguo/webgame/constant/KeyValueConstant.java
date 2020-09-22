/*    */ package com.linlongyx.sanguo.webgame.constant;
/*    */ 
/*    */ 
/*    */ 
/*    */ public enum KeyValueConstant
/*    */ {
/*  7 */   NULL(0, "NULL"),
/*  8 */   CCY(1, "元宝"),
/*  9 */   CCY_B(2, "绑定元宝"),
/* 10 */   GCY(3, "金币"),
/* 11 */   EXP(4, "经验"),
/* 12 */   Arena(5, "竞技点"),
/* 13 */   Friend(6, "友情点"),
/* 14 */   Prestige(7, "威名"),
/* 15 */   RecruitToken(8, "招贤令"),
/* 16 */   Contribute(9, "个人贡献"),
/* 17 */   Soul(10, "将魂"),
/* 18 */   TotalCharge(11, "累计充值"),
/*    */   
/* 20 */   TurnplatePoint(50, "天金转盘积分"),
/* 21 */   CHARGE(51, "充值"),
/* 22 */   Chapter(52, "已通关的章节副本id"),
/* 23 */   DISCONNECTION(53, "发现刷协议主动断开连接"),
/* 24 */   QUILITY(54, "主角品质"),
/* 25 */   GROUPCHARGE(55, "首充团购人数"),
/* 26 */   GROWTHFUND(56, "成长基金团购人数"),
/* 27 */   WORLD_LEVEL(60, "世界等级"),
/* 28 */   BAG_SIZE_CHANGE(61, "背包容量"),
/* 29 */   RABIT_COMBAT(62, "快速作战次数上限"),
/* 30 */   MYSTERY_SHOP(63, "神秘商店免费刷新次数上限"),
/* 31 */   CRYSTAL_SHOP(64, "晶魂商店免费刷新次数上限"),
/* 32 */   DELETEEQUIP(65, "删除装备"),
/* 33 */   GOLDENDTIME(66, "金将直购结束时间"),
/* 34 */   BACKGIFT(67, "回归礼包"),
/* 35 */   FUNCTION_OPEN(100, "功能开启"),
/* 36 */   RESET_DATE(101, "重置数据"),
/* 37 */   PLAYER_LEVEL(111, "玩家等级"),
/* 38 */   TASK_SCHEDULE(112, "主线任务进度"),
/*    */   
/* 40 */   PLAYER_FIGHT_VALUE(205, "主角战力"),
/* 41 */   TOTAL_FIGHT_VALUE(206, "总战力");
/*    */   
/*    */   private int key;
/*    */   
/*    */   private String name;
/*    */   
/*    */   KeyValueConstant(int key, String name) {
/* 48 */     this.key = key;
/* 49 */     this.name = name;
/*    */   }
/*    */   
/*    */   public int getKey() {
/* 53 */     return this.key;
/*    */   }
/*    */   
/*    */   public String getName() {
/* 57 */     return this.name;
/*    */   }
/*    */   
/*    */   public static KeyValueConstant get(int id) {
/* 61 */     for (KeyValueConstant keyValueConstant : values()) {
/* 62 */       if (keyValueConstant.getKey() == id) {
/* 63 */         return keyValueConstant;
/*    */       }
/*    */     } 
/* 66 */     return NULL;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\constant\KeyValueConstant.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */