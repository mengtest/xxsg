/*    */ package com.linlongyx.core.framework.dao.mysql;
/*    */ 
/*    */ import com.linlongyx.core.framework.dao.IDAO;
/*    */ import org.mybatis.spring.SqlSessionTemplate;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class MybatisDAO
/*    */   implements IDAO
/*    */ {
/*    */   private final IDAO.TYPE type;
/*    */   private final SqlSessionTemplate template;
/*    */   
/*    */   public MybatisDAO(SqlSessionTemplate template) {
/* 16 */     this.type = IDAO.TYPE.MYBATIS;
/* 17 */     this.template = template;
/*    */   }
/*    */ 
/*    */   
/*    */   public IDAO.TYPE getType() {
/* 22 */     return this.type;
/*    */   }
/*    */   
/*    */   public SqlSessionTemplate getTemplate() {
/* 26 */     return this.template;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\core\framework\dao\mysql\MybatisDAO.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */