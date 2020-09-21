/*    */ package com.linlongyx.sanguo.webgame.processors.bagua;
/*    */ 
/*    */ import com.linlongyx.core.utils.GsonUtil;
/*    */ import java.sql.PreparedStatement;
/*    */ import java.sql.SQLException;
/*    */ import org.springframework.jdbc.core.BatchPreparedStatementSetter;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ final class null
/*    */   implements BatchPreparedStatementSetter
/*    */ {
/*    */   public void setValues(PreparedStatement preparedStatement, int i) throws SQLException {
/* 41 */     preparedStatement.setInt(1, ((BaguaUtil.BaguaRecordTable)BaguaUtil.access$000().get(i)).chapterId);
/* 42 */     preparedStatement.setInt(2, ((BaguaUtil.BaguaRecordTable)BaguaUtil.access$000().get(i)).insId);
/* 43 */     preparedStatement.setInt(3, ((BaguaUtil.BaguaRecordTable)BaguaUtil.access$000().get(i)).createtime);
/* 44 */     preparedStatement.setString(4, GsonUtil.toJson(((BaguaUtil.BaguaRecordTable)BaguaUtil.access$000().get(i)).playerList));
/*    */   }
/*    */ 
/*    */   
/*    */   public int getBatchSize() {
/* 49 */     return BaguaUtil.access$000().size();
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\bagua\BaguaUtil$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */