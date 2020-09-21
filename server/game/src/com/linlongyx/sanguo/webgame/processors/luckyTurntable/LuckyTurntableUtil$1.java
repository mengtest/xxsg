/*    */ package com.linlongyx.sanguo.webgame.processors.luckyTurntable;
/*    */ 
/*    */ import java.sql.ResultSet;
/*    */ import java.sql.SQLException;
/*    */ import org.springframework.jdbc.core.RowMapper;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ final class null
/*    */   implements RowMapper<LuckyTurntableUtil.LuckyTurntableData>
/*    */ {
/*    */   public LuckyTurntableUtil.LuckyTurntableData mapRow(ResultSet resultSet, int i) throws SQLException {
/* 76 */     LuckyTurntableUtil.LuckyTurntableData data = new LuckyTurntableUtil.LuckyTurntableData();
/* 77 */     data.actId = resultSet.getInt("actId");
/* 78 */     data.playerId = resultSet.getLong("playerId");
/* 79 */     data.totalPoint = resultSet.getInt("totalPoint");
/* 80 */     data.playerName = resultSet.getString("playerName");
/* 81 */     data.lasttime = resultSet.getLong("lasttime");
/* 82 */     return data;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\luckyTurntable\LuckyTurntableUtil$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */