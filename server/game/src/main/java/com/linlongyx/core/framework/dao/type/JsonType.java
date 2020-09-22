/*    */ package com.linlongyx.core.framework.dao.type;
/*    */ 
/*    */ import com.linlongyx.core.utils.GsonUtil;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class JsonType
/*    */   extends BaseType
/*    */ {
/*    */   public Class<?> getType() {
/* 14 */     return getClass();
/*    */   }
/*    */ 
/*    */   
/*    */   public String serial() {
/* 19 */     return GsonUtil.toJson(this);
/*    */   }
/*    */   
/*    */   public void unserial(String str) {}
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\core\framework\dao\type\JsonType.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */