/*    */ package com.linlongyx.core.framework.dao.type;
/*    */ 
/*    */ 
/*    */ public class IntegerType
/*    */   extends BaseType
/*    */ {
/*    */   private Integer value;
/*    */   
/*    */   public IntegerType() {
/* 10 */     super(true);
/* 11 */     this.value = Integer.valueOf(0);
/*    */   }
/*    */   
/*    */   public IntegerType(int value) {
/* 15 */     super(true);
/* 16 */     this.value = Integer.valueOf(value);
/*    */   }
/*    */   
/*    */   public IntegerType(boolean isRequired) {
/* 20 */     super(isRequired);
/*    */   }
/*    */   
/*    */   public IntegerType(int value, boolean isRequired) {
/* 24 */     super(isRequired);
/* 25 */     this.value = Integer.valueOf(value);
/*    */   }
/*    */ 
/*    */   
/*    */   public Class<?> getType() {
/* 30 */     return Integer.class;
/*    */   }
/*    */ 
/*    */   
/*    */   public String serial() {
/* 35 */     return this.value.toString();
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(String str) {
/* 40 */     this.value = Integer.valueOf(Integer.parseInt(str));
/*    */   }
/*    */   
/*    */   public Integer getValue() {
/* 44 */     return this.value;
/*    */   }
/*    */   
/*    */   public void setValue(Integer value) {
/* 48 */     this.value = value;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\core\framework\dao\type\IntegerType.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */