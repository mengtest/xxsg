/*    */ package com.linlongyx.core.framework.dao.type;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class LongType
/*    */   extends BaseType
/*    */ {
/*    */   private Long value;
/*    */   
/*    */   public LongType() {
/* 11 */     super(true);
/* 12 */     this.value = Long.valueOf(0L);
/*    */   }
/*    */   
/*    */   public LongType(long value) {
/* 16 */     super(true);
/* 17 */     this.value = Long.valueOf(value);
/*    */   }
/*    */   
/*    */   public LongType(boolean isRequired) {
/* 21 */     super(isRequired);
/*    */   }
/*    */   
/*    */   public LongType(long value, boolean isRequired) {
/* 25 */     super(isRequired);
/* 26 */     this.value = Long.valueOf(value);
/*    */   }
/*    */ 
/*    */   
/*    */   public Class<?> getType() {
/* 31 */     return Integer.class;
/*    */   }
/*    */ 
/*    */   
/*    */   public String serial() {
/* 36 */     return this.value.toString();
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(String str) {
/* 41 */     this.value = Long.valueOf(Long.parseLong(str));
/*    */   }
/*    */   
/*    */   public Long getValue() {
/* 45 */     return this.value;
/*    */   }
/*    */   
/*    */   public void setValue(Long value) {
/* 49 */     this.value = value;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\core\framework\dao\type\LongType.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */