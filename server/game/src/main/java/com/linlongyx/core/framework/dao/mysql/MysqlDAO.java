/*    */ package com.linlongyx.core.framework.dao.mysql;
/*    */ 
/*    */ import com.linlongyx.core.framework.dao.IDAO;
/*    */ import org.springframework.jdbc.core.JdbcTemplate;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class MysqlDAO
/*    */   implements IDAO
/*    */ {
/*    */   private final IDAO.TYPE type;
/*    */   private final JdbcTemplate template;
/*    */   
/*    */   public MysqlDAO(JdbcTemplate template) {
/* 19 */     this.type = IDAO.TYPE.MYSQL;
/* 20 */     this.template = template;
/*    */   }
/*    */ 
/*    */   
/*    */   public IDAO.TYPE getType() {
/* 25 */     return this.type;
/*    */   }
/*    */   
/*    */   public JdbcTemplate getTemplate() {
/* 29 */     return this.template;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\core\framework\dao\mysql\MysqlDAO.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */