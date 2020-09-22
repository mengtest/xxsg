/*    */ package com.linlongyx.core.framework.dao.type;
/*    */ 
/*    */ 
/*    */ public abstract class BaseType
/*    */   implements IBaseType
/*    */ {
/*    */   protected transient boolean isRequired;
/*    */   protected transient boolean isInit;
/*    */   
/*    */   BaseType() {
/* 11 */     this(true);
/*    */   }
/*    */   
/*    */   BaseType(boolean isRequired) {
/* 15 */     this.isRequired = isRequired;
/*    */   }
/*    */   
/*    */   protected boolean isRequired() {
/* 19 */     return this.isRequired;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\core\framework\dao\type\BaseType.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */