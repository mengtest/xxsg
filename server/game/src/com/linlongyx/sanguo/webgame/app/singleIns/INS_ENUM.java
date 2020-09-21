/*    */ package com.linlongyx.sanguo.webgame.app.singleIns;
/*    */ 
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ 
/*    */ 
/*    */ 
/*    */ public enum INS_ENUM
/*    */ {
/* 10 */   Copper(1, "铜币副本"),
/* 11 */   Strength(2, "强化石副本"),
/* 12 */   Exp(3, "经验书副本"),
/* 13 */   Break(4, "突破石副本"),
/* 14 */   Strengh(5, "强化副本"),
/* 15 */   Pet(6, "战宠副本"),
/* 16 */   TEST(99, "test");
/*    */   
/*    */   private int type;
/*    */   private String name;
/*    */   private static Map<Integer, INS_ENUM> map;
/*    */   
/*    */   INS_ENUM(int type, String name) {
/* 23 */     this.type = type;
/* 24 */     this.name = name;
/*    */   }
/*    */   static {
/* 27 */     map = new HashMap<>();
/*    */ 
/*    */     
/* 30 */     for (INS_ENUM copy : values())
/* 31 */       map.put(Integer.valueOf(copy.getType()), copy); 
/*    */   }
/*    */   
/*    */   public static INS_ENUM getCopy(int type) {
/* 35 */     return map.get(Integer.valueOf(type));
/*    */   }
/*    */   
/*    */   public int getType() {
/* 39 */     return this.type;
/*    */   }
/*    */   
/*    */   public String getName() {
/* 43 */     return this.name;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\app\singleIns\INS_ENUM.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */