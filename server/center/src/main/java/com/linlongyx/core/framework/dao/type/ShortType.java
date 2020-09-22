/*    */ package com.linlongyx.core.framework.dao.type;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ShortType
/*    */   extends BaseType
/*    */ {
/*    */   Short value;
/*    */   
/*    */   public Class<?> getType() {
/* 12 */     return Short.class;
/*    */   }
/*    */ 
/*    */   
/*    */   public String serial() {
/* 17 */     return this.value.toString();
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(String str) {
/* 22 */     this.value = Short.valueOf(Short.parseShort(str));
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\core\framework\dao\type\ShortType.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */