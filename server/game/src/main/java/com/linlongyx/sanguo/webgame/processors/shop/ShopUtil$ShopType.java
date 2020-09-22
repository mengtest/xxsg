/*    */ package com.linlongyx.sanguo.webgame.processors.shop;
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
/*    */ public enum ShopType
/*    */ {
/* 44 */   ItemShop(1, "道具商店"),
/* 45 */   PartnerShop(2, "武将商店"),
/* 46 */   EquipShop(3, "装备商店"),
/* 47 */   ArenaShop(4, "竞技商店"),
/* 48 */   ArenaRewardShop(5, "竞技奖励商店"),
/* 49 */   FriendShop(6, "好友商店"),
/* 50 */   UnparalleledShop(7, "无双商店"),
/* 51 */   UnparalleledRewardShop(8, "无双奖励商店"),
/* 52 */   GroupShop(9, "军团商店"),
/* 53 */   GroupRewardShop(10, "军团奖励商店"),
/* 54 */   SoulShop(11, "将魂商店"),
/* 55 */   BossShop(12, "boss奖励商店"),
/* 56 */   TurnplateShop(13, "天金转盘商店"),
/* 57 */   RedEquipShop(14, "红金装备商店"),
/* 58 */   GuessShop(15, "竞猜商店"),
/* 59 */   InviteShop(16, "求助商店"),
/* 60 */   SoulsShop(17, "英魂商店"),
/* 61 */   ExaminationShop(18, "科举商店"),
/*    */   
/* 63 */   SecretShop(96, "秘境商店"),
/* 64 */   BloodCrystalsShop(97, "血晶商店"),
/* 65 */   CrystalSoulShop(98, "晶魂商店"),
/* 66 */   QQVIPShop(99, "玩吧特价礼包商店"),
/* 67 */   MysteryShop(100, "神秘商店");
/*    */   
/*    */   private int type;
/*    */   private String name;
/*    */   
/*    */   ShopType(int type, String name) {
/* 73 */     setType(type);
/* 74 */     setName(name);
/*    */   }
/*    */   
/*    */   public int getType() {
/* 78 */     return this.type;
/*    */   }
/*    */   
/*    */   public void setType(int type) {
/* 82 */     this.type = type;
/*    */   }
/*    */   
/*    */   public String getName() {
/* 86 */     return this.name;
/*    */   }
/*    */   
/*    */   public void setName(String name) {
/* 90 */     this.name = name;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\shop\ShopUtil$ShopType.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */