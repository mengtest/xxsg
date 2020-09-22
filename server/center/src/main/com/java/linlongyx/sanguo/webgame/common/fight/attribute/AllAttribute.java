/*    */ package linlongyx.sanguo.webgame.common.fight.attribute;
/*    */ 
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class AllAttribute
/*    */ {
/* 10 */   private static final Map<Integer, AttributeType> ALL_ATTRIBUTE = new HashMap<>();
/*    */   
/*    */   static {
/* 13 */     for (AttributeType attributeType : AttributeType.values()) {
/* 14 */       ALL_ATTRIBUTE.put(Integer.valueOf(attributeType.getType()), attributeType);
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static boolean isBase(int type) {
/* 25 */     return (type > 0 && type < AttributeType.BASE_ATTR_END.getType());
/*    */   }
/*    */   
/*    */   public static AttributeType getType(int type) {
/* 29 */     return ALL_ATTRIBUTE.get(Integer.valueOf(type));
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\sanguo\webgame\common\fight\attribute\AllAttribute.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */