/*     */ package com.linlongyx.sanguo.webgame.processors.bosshome;
/*     */ 
/*     */ import com.google.gson.reflect.TypeToken;
/*     */ import com.linlongyx.core.utils.GsonUtil;
/*     */ import java.sql.ResultSet;
/*     */ import java.sql.SQLException;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import org.springframework.jdbc.core.RowMapper;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ final class null
/*     */   implements RowMapper<WorldBossUtil.WorldBossData>
/*     */ {
/*     */   public WorldBossUtil.WorldBossData mapRow(ResultSet resultSet, int i) throws SQLException {
/* 202 */     WorldBossUtil.WorldBossData worldBossData = new WorldBossUtil.WorldBossData();
/* 203 */     worldBossData.timeKey = resultSet.getInt("timeKey");
/* 204 */     worldBossData.insId = resultSet.getInt("insId");
/* 205 */     String curHp = resultSet.getString("curHp");
/* 206 */     worldBossData.curHp = (Map<Byte, Long>)GsonUtil.fromJson(curHp.isEmpty() ? "{}" : curHp, (new TypeToken<HashMap<Byte, Long>>() {  }
/* 207 */         ).getType());
/* 208 */     worldBossData.maxHp = resultSet.getLong("maxHp");
/* 209 */     worldBossData.status = resultSet.getByte("status");
/* 210 */     worldBossData.killNum = resultSet.getInt("killNum");
/* 211 */     worldBossData.nextTime = resultSet.getInt("nextTime");
/* 212 */     worldBossData.openTime = resultSet.getInt("openTime");
/* 213 */     return worldBossData;
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\bosshome\WorldBossUtil$2.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */