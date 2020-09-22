/*    */ package com.linlongyx.sanguo.webgame.constant;
/*    */ 
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public enum RankActType
/*    */ {
/* 11 */   Recruit2(8, "武将招募榜", true, 1),
/* 12 */   WarPet2(9, "战宠榜", true, 1),
/* 13 */   Equip2(10, "装备榜", true, 1),
/* 14 */   Arena(11, "竞技榜", false, 3),
/* 15 */   Cost2(12, "消费榜", true, 1),
/* 16 */   Mounts2(13, "坐骑榜", true, 1),
/* 17 */   PlayerFight2(14, "战力榜", true, 2),
/* 18 */   Recruit(15, "武将招募榜", true, 1),
/* 19 */   WarPet(16, "战宠榜", true, 1),
/* 20 */   Equip(17, "装备榜", true, 1),
/* 21 */   Mounts(18, "坐骑榜", true, 1),
/* 22 */   Cost(19, "消费榜", true, 1),
/* 23 */   ZheFa(20, "阵法榜", true, 1),
/* 24 */   Charge(21, "充值榜", true, 1);
/*    */   private int type;
/*    */   private String name;
/*    */   private boolean isDesc;
/*    */   private int order;
/*    */   private static Map<Integer, RankActType> map;
/*    */   
/*    */   static {
/* 32 */     map = new HashMap<>();
/*    */ 
/*    */     
/* 35 */     for (RankActType rankActType : values()) {
/* 36 */       map.put(Integer.valueOf(rankActType.getType()), rankActType);
/*    */     }
/*    */   }
/*    */   
/*    */   public static RankActType getRankActType(int type) {
/* 41 */     return map.get(Integer.valueOf(type));
/*    */   }
/*    */   
/*    */   RankActType(int type, String name, boolean isDesc, int order) {
/* 45 */     this.type = type;
/* 46 */     this.name = name;
/* 47 */     this.isDesc = isDesc;
/* 48 */     this.order = order;
/*    */   }
/*    */   public int getType() {
/* 51 */     return this.type;
/*    */   } public String getName() {
/* 53 */     return this.name;
/*    */   }
/*    */   public boolean isDesc() {
/* 56 */     return this.isDesc;
/*    */   }
/*    */   
/*    */   public int getOrder() {
/* 60 */     return this.order;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\constant\RankActType.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */