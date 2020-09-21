/*    */ package com.linlongyx.core.framework.dao.type;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BooleanType
/*    */   extends BaseType
/*    */ {
/*    */   private Boolean value;
/*    */   
/*    */   public Class<?> getType() {
/* 15 */     return Boolean.class;
/*    */   }
/*    */ 
/*    */   
/*    */   public String serial() {
/* 20 */     return this.value.toString();
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(String str) {
/* 25 */     this.value = Boolean.valueOf(Boolean.parseBoolean(str));
/*    */   }
/*    */   
/*    */   public Boolean getValue() {
/* 29 */     return this.value;
/*    */   }
/*    */   
/*    */   public void setValue(boolean value) {
/* 33 */     this.value = Boolean.valueOf(value);
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\core\framework\dao\type\BooleanType.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */