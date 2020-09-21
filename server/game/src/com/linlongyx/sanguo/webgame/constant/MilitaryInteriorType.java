/*    */ package com.linlongyx.sanguo.webgame.constant;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public enum MilitaryInteriorType
/*    */ {
/*  8 */   None(0, "空", 0, 0),
/*  9 */   Gold(1001, "金币", 1, 1),
/* 10 */   Exp(1002, "经验值", 1, 4),
/* 11 */   ArenaPoint(1003, "竞技点", 1, 5),
/* 12 */   Prestige(1004, "威名", 1, 7),
/* 13 */   Soul(1005, "将魂", 1, 10),
/* 14 */   AddiTionEnd(6, "加成结束", 0, 0),
/* 15 */   FirstExpBook(1006, "初级经验书", 0, 30000100),
/* 16 */   SecondExpBook(1007, "中级经验书", 0, 30000101),
/* 17 */   ThirdExpBook(1008, "高级经验书", 0, 30000102),
/* 18 */   RefineStone(1009, "精炼石", 0, 30000201),
/* 19 */   BreakStone(1010, "突破石", 0, 30000301),
/* 20 */   BreakCream(1011, "突破精华", 0, 30000302),
/* 21 */   TetureStenghStone(1012, "宝物强化石", 0, 30000801),
/* 22 */   FirstPetFood(1013, "初级战宠粮食", 0, 30000401),
/* 23 */   SecondPetFood(1014, "中级战宠粮食", 0, 30000402),
/* 24 */   ThirdPetFood(1015, "高级战宠粮食", 0, 30000403),
/* 25 */   DestinyStone(1016, "天命石", 0, 30000020),
/* 26 */   TimeAdd(1017, "军务府被帮助时间增加", 0, 0),
/* 27 */   TimeRefuse(1018, "名将副本次数恢复时间减少", 0, 0),
/* 28 */   TimeAddPercentage(1019, "军务府被帮助时间增加百分比", 1, 0);
/*    */   
/*    */   private int type;
/*    */   
/*    */   private String name;
/*    */   private int addType;
/*    */   private int itemId;
/*    */   
/*    */   MilitaryInteriorType(int type, String name, int addType, int itemId) {
/* 37 */     setType(type);
/* 38 */     setName(name);
/* 39 */     setAddType(addType);
/* 40 */     setItemId(itemId);
/*    */   }
/*    */   public int getType() {
/* 43 */     return this.type;
/*    */   } public void setType(int type) {
/* 45 */     this.type = type;
/*    */   } public String getName() {
/* 47 */     return this.name;
/*    */   } public void setName(String name) {
/* 49 */     this.name = name;
/*    */   }
/*    */   public int getAddType() {
/* 52 */     return this.addType;
/*    */   }
/*    */   
/*    */   public void setAddType(int addType) {
/* 56 */     this.addType = addType;
/*    */   }
/*    */   
/*    */   public int getItemId() {
/* 60 */     return this.itemId;
/*    */   }
/*    */   
/*    */   public void setItemId(int itemId) {
/* 64 */     this.itemId = itemId;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\constant\MilitaryInteriorType.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */