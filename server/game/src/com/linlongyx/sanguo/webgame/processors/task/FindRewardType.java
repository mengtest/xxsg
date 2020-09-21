/*    */ package com.linlongyx.sanguo.webgame.processors.task;
/*    */ 
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public enum FindRewardType
/*    */ {
/* 11 */   NORMAL_1(1, "日常副本-金币", 1, 1, 1301),
/* 12 */   NORMAL_2(2, "日常副本-精炼石", 1, 2, 1302),
/* 13 */   NORMAL_3(3, "日常副本-经验书", 1, 3, 1303),
/* 14 */   NORMAL_4(4, "日常副本-突破石", 1, 4, 1304),
/* 15 */   NORMAL_5(5, "日常副本-宝物强化石", 1, 5, 1305),
/* 16 */   NORMAL_6(6, "日常副本-宠物进阶", 1, 6, 1306),
/* 17 */   TEAM(7, "组队副本", 1, 7, 37),
/* 18 */   BAGUA(8, "八卦阵", 1, 8, 41),
/* 19 */   UNPARM(9, "三国无双", 1, 9, 18),
/* 20 */   DENSTINY(10, "天命战场", 1, 10, 74),
/* 21 */   ARENA(11, "竞技场", 1, 11, 17),
/* 22 */   CROSS(12, "跨服段位赛", 1, 12, 28),
/* 23 */   PERSONAL_BOSS(13, "个人Boss", 1, 13, 300); private int type;
/*    */   private String name;
/*    */   private int mold;
/*    */   private int extend;
/*    */   private int functionId;
/*    */   private static Map<Integer, FindRewardType> map;
/*    */   
/*    */   static {
/* 31 */     map = new HashMap<>();
/*    */     
/* 33 */     for (FindRewardType findRewardType : values()) {
/* 34 */       map.put(Integer.valueOf(findRewardType.getType()), findRewardType);
/*    */     }
/*    */   }
/*    */   
/*    */   public static FindRewardType getFindRewardType(int type) {
/* 39 */     return map.get(Integer.valueOf(type));
/*    */   }
/*    */ 
/*    */   
/*    */   FindRewardType(int type, String name, int mold, int extend, int functionId) {
/* 44 */     this.type = type;
/* 45 */     this.name = name;
/* 46 */     this.mold = mold;
/* 47 */     this.extend = extend;
/* 48 */     this.functionId = functionId;
/*    */   }
/*    */   public int getType() {
/* 51 */     return this.type;
/*    */   } public String getName() {
/* 53 */     return this.name;
/*    */   }
/*    */   public int getMold() {
/* 56 */     return this.mold;
/*    */   }
/*    */   
/*    */   public int getExtend() {
/* 60 */     return this.extend;
/*    */   }
/*    */   
/*    */   public int getFunctionId() {
/* 64 */     return this.functionId;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\task\FindRewardType.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */