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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class DruidDAO
/*    */   implements IDAO
/*    */ {
/*    */   private final JdbcTemplate template;
/*    */   
/*    */   public IDAO.TYPE getType() {
/* 23 */     return IDAO.TYPE.DRUID;
/*    */   }
/*    */ 
/*    */   
/*    */   public DruidDAO(JdbcTemplate template) {
/* 28 */     this.template = template;
/*    */   }
/*    */ 
/*    */   
/*    */   public Object getTemplate() {
/* 33 */     return this.template;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\core\framework\dao\mysql\DruidDAO.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */