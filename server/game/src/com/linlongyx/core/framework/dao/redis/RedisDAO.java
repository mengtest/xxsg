/*    */ package com.linlongyx.core.framework.dao.redis;
/*    */ 
/*    */ import com.linlongyx.core.framework.dao.IDAO;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class RedisDAO
/*    */   implements IDAO
/*    */ {
/*    */   private final IDAO.TYPE type;
/*    */   private final RedisClientTemplate template;
/*    */   
/*    */   public RedisDAO(RedisClientTemplate template) {
/* 14 */     this.type = IDAO.TYPE.REDIS;
/* 15 */     this.template = template;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public IDAO.TYPE getType() {
/* 21 */     return this.type;
/*    */   }
/*    */   
/*    */   public RedisClientTemplate getTemplate() {
/* 25 */     return this.template;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\core\framework\dao\redis\RedisDAO.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */