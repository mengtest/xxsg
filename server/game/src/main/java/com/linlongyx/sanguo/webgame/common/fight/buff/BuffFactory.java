/*    */ package com.linlongyx.sanguo.webgame.common.fight.buff;
/*    */ 
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BuffFactory
/*    */ {
/* 11 */   private static final Map<Integer, BuffType> mapBuff = new HashMap<>();
/*    */   
/*    */   static {
/* 14 */     for (BuffType type : BuffType.values()) {
/* 15 */       mapBuff.put(Integer.valueOf(type.getType()), type);
/*    */     }
/*    */   }
/*    */   
/*    */   public static AbstractBuff build(int buffType) {
/* 20 */     BuffType buffType1 = mapBuff.get(Integer.valueOf(buffType));
/* 21 */     if (null == buffType1)
/* 22 */       return null; 
/* 23 */     return buffType1.build();
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\common\fight\buff\BuffFactory.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */