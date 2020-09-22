/*    */ package com.linlongyx.core.framework.dao.type;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class DoubleType
/*    */   extends BaseType
/*    */ {
/*    */   Double value;
/*    */   
/*    */   public Class<?> getType() {
/* 16 */     return Double.class;
/*    */   }
/*    */ 
/*    */   
/*    */   public String serial() {
/* 21 */     return this.value.toString();
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(String str) {
/* 26 */     this.value = Double.valueOf(Double.parseDouble(str));
/*    */   }
/*    */   
/*    */   public Double getValue() {
/* 30 */     return this.value;
/*    */   }
/*    */   
/*    */   public void setValue(Double value) {
/* 34 */     this.value = value;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\core\framework\dao\type\DoubleType.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */