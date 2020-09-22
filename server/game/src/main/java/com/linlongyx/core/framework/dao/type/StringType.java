/*    */ package com.linlongyx.core.framework.dao.type;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class StringType
/*    */   extends BaseType
/*    */ {
/*    */   private String value;
/*    */   
/*    */   public StringType() {
/* 11 */     super(true);
/*    */   }
/*    */   
/*    */   public StringType(String value) {
/* 15 */     super(true);
/* 16 */     this.value = value;
/*    */   }
/*    */   
/*    */   public StringType(boolean isRequired) {
/* 20 */     super(isRequired);
/*    */   }
/*    */   
/*    */   public StringType(String value, boolean isRequired) {
/* 24 */     super(isRequired);
/* 25 */     this.value = value;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public Class<?> getType() {
/* 31 */     return String.class;
/*    */   }
/*    */ 
/*    */   
/*    */   public String serial() {
/* 36 */     return this.value;
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(String str) {
/* 41 */     this.value = str;
/*    */   }
/*    */   
/*    */   public String getValue() {
/* 45 */     return this.value;
/*    */   }
/*    */   
/*    */   public void setValue(String value) {
/* 49 */     this.value = value;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\core\framework\dao\type\StringType.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */