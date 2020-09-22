/*    */ package com.linlongyx.core.framework.dao.type;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ByteType
/*    */   extends BaseType
/*    */ {
/*    */   Byte value;
/*    */   
/*    */   public Class<?> getType() {
/* 15 */     return Byte.class;
/*    */   }
/*    */ 
/*    */   
/*    */   public String serial() {
/* 20 */     return this.value.toString();
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(String str) {
/* 25 */     this.value = Byte.valueOf(Byte.parseByte(str));
/*    */   }
/*    */   
/*    */   public Byte getValue() {
/* 29 */     return this.value;
/*    */   }
/*    */   
/*    */   public void setValue(Byte value) {
/* 33 */     this.value = value;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\core\framework\dao\type\ByteType.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */