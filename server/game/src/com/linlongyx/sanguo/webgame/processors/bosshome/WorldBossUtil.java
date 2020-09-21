/*     */ package com.linlongyx.sanguo.webgame.processors.bosshome;
/*     */ 
/*     */ import com.google.gson.reflect.TypeToken;
/*     */ import com.linlongyx.core.framework.context.AppContext;
/*     */ import com.linlongyx.core.framework.dao.mysql.MysqlDAO;
/*     */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*     */ import com.linlongyx.core.utils.GsonUtil;
/*     */ import com.linlongyx.core.utils.TimeUtil;
/*     */ import com.linlongyx.sanguo.webgame.common.fight.side.WorldBossFightSide;
/*     */ import com.linlongyx.sanguo.webgame.constant.TabNotice;
/*     */ import com.linlongyx.sanguo.webgame.log.LogUtil;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.common.TabNoticeResponse;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.BossDamageData;
/*     */ import com.linlongyx.sanguo.webgame.service.LookUpService;
/*     */ import java.sql.PreparedStatement;
/*     */ import java.sql.ResultSet;
/*     */ import java.sql.SQLException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import org.springframework.jdbc.core.BatchPreparedStatementSetter;
/*     */ import org.springframework.jdbc.core.JdbcTemplate;
/*     */ import org.springframework.jdbc.core.RowMapper;
/*     */ 
/*     */ 
/*     */ public class WorldBossUtil
/*     */ {
/*  30 */   private static String world_boss_create_sql = "CREATE TABLE IF NOT EXISTS `tb_worldBoss`(`timeKey` INT(11) NOT NULL DEFAULT '0', `insId` INT(11) NOT NULL DEFAULT '0', `curHp` TEXT DEFAULT NULL, `maxHp` BIGINT(20) NOT NULL DEFAULT '0',`status` TINYINT(4) NOT NULL DEFAULT '1', `killNum`INT(11) NOT NULL DEFAULT '0', `nextTime` INT(11) NOT NULL DEFAULT '0', `openTime` INT(11) NOT NULL DEFAULT '0', PRIMARY KEY (`timeKey`)) ENGINE=INNODB DEFAULT CHARSET=utf8;";
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
/*  41 */   private static String world_boss_insert_sql = "REPLACE INTO `tb_worldBoss`(`timeKey`, `insId`, `curHp`, `maxHp`, `status`, `killNum`, `nextTime`, `openTime`) VALUES (?,?,?,?,?,?,?,?)";
/*  42 */   private static String world_boss_select_sql = "SELECT * FROM tb_worldBoss WHERE `timeKey`=?";
/*     */   
/*  44 */   private static String world_boss_rank_create_sql = "CREATE TABLE IF NOT EXISTS `tb_worldBossRank` (`playerId` BIGINT(20) NOT NULL DEFAULT '0',`playerName` VARCHAR(20) DEFAULT NULL,`damage` BIGINT(20) NOT NULL DEFAULT '0',`rank` INT(11) NOT NULL DEFAULT '0',PRIMARY KEY(`playerId`)) ENGINE=INNODB DEFAULT CHARSET=utf8;";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  52 */   private static String world_boss_rank_truncate_sql = "TRUNCATE TABLE `tb_worldBossRank`";
/*  53 */   private static String world_boss_rank_insert_sql = "REPLACE INTO `tb_worldBossRank`(`playerId`, `playerName`, `damage`, `rank`) VALUES (?,?,?,?)"; private static WorldBossFightSide worldBossFightSide;
/*  54 */   private static String world_boss_rank_select_sql = "SELECT * FROM tb_worldBossRank ORDER BY `rank` ASC";
/*     */   
/*     */   public enum WORLD_BOSS_STATUS {
/*  57 */     CLOSE(1),
/*  58 */     OPENING(2),
/*  59 */     WAITING(3),
/*  60 */     END(4);
/*     */     
/*     */     byte type;
/*     */     
/*     */     WORLD_BOSS_STATUS(int type) {
/*  65 */       this.type = (byte)type;
/*     */     }
/*     */     
/*     */     public byte getType() {
/*  69 */       return this.type;
/*     */     }
/*     */   }
/*     */   
/*     */   public static class WorldBossData
/*     */   {
/*     */     public int timeKey;
/*     */     public int insId;
/*     */     public Map<Byte, Long> curHp;
/*     */     public long maxHp;
/*     */     public byte status;
/*     */     public int killNum;
/*     */     public int nextTime;
/*     */     public int openTime;
/*     */     
/*     */     public String toString() {
/*  85 */       return "WorldBossData{timeKey=" + this.timeKey + ", insId=" + this.insId + ", curHp=" + this.curHp + ", maxHp=" + this.maxHp + ", status=" + this.status + ", killNum=" + this.killNum + ", nextTime=" + this.nextTime + ", openTime=" + this.openTime + '}';
/*     */     }
/*     */   }
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
/*     */   private static void broadcastTabNotice(TabNotice tabNotice) {
/* 101 */     TabNoticeResponse resp = new TabNoticeResponse();
/* 102 */     resp.sys = tabNotice.getSys();
/* 103 */     resp.index = tabNotice.getIndex();
/* 104 */     LookUpService.broadcast((ResponseBase)resp);
/*     */   }
/*     */   
/*     */   public static void openBoss() {
/* 108 */     clearRankData();
/* 109 */     worldBossFightSide = new WorldBossFightSide();
/* 110 */     worldBossFightSide.open();
/* 111 */     broadcastTabNotice(TabNotice.worldBoss);
/*     */   }
/*     */   
/*     */   public static void closeBoss() {
/* 115 */     if (worldBossFightSide != null) {
/* 116 */       worldBossFightSide.close();
/* 117 */       save();
/*     */     } 
/*     */   }
/*     */   
/*     */   public static boolean isClose() {
/* 122 */     if (worldBossFightSide == null)
/* 123 */       return true; 
/* 124 */     return (worldBossFightSide.getStatus() == WORLD_BOSS_STATUS.CLOSE.getType());
/*     */   }
/*     */   
/*     */   public static void executeOnStartUp() {
/*     */     try {
/* 129 */       int curDay = TimeUtil.getCurrentYearMonthDay();
/* 130 */       WorldBossData worldBossData = loadBossDataFromDb(curDay);
/* 131 */       if (worldBossData != null && 
/* 132 */         worldBossFightSide == null) {
/* 133 */         List<BossDamageData> bossDamageDataList = loadBossDamageData();
/* 134 */         worldBossFightSide = new WorldBossFightSide();
/* 135 */         worldBossFightSide.initWorldBossFromDb(worldBossData, bossDamageDataList);
/*     */       }
/*     */     
/* 138 */     } catch (Exception e) {
/* 139 */       e.printStackTrace();
/* 140 */       LogUtil.errorLog(new Object[] { "load world boss data error:", Arrays.toString((Object[])e.getStackTrace()) });
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void executeOnShutdown() {
/* 145 */     if (worldBossFightSide != null) {
/* 146 */       save();
/*     */     }
/*     */   }
/*     */   
/*     */   public static void save() {
/*     */     try {
/* 152 */       saveBossData();
/* 153 */       saveBossDamageData();
/* 154 */     } catch (Exception e) {
/* 155 */       e.printStackTrace();
/* 156 */       LogUtil.errorLog(new Object[] { "save world boss data error:", Arrays.toString((Object[])e.getStackTrace()) });
/*     */     } 
/*     */   }
/*     */   
/*     */   private static void saveBossData() {
/* 161 */     if (worldBossFightSide != null) {
/* 162 */       MysqlDAO mysql = (MysqlDAO)AppContext.getDAO();
/* 163 */       JdbcTemplate template = mysql.getTemplate();
/* 164 */       template.update(world_boss_create_sql);
/* 165 */       template.update(world_boss_insert_sql, new Object[] { Integer.valueOf(worldBossFightSide.getTimeKey()), Integer.valueOf(worldBossFightSide.getInsId()), GsonUtil.toJson(worldBossFightSide.getCurHp()), 
/* 166 */             Long.valueOf(worldBossFightSide.getMaxHp()), Byte.valueOf(worldBossFightSide.getStatus()), Integer.valueOf(worldBossFightSide.getKillNum()), Integer.valueOf(worldBossFightSide.getNextTime()), Integer.valueOf(worldBossFightSide.getOpenTime()) });
/*     */     } 
/*     */   }
/*     */   
/*     */   private static void saveBossDamageData() {
/* 171 */     if (worldBossFightSide != null) {
/* 172 */       MysqlDAO mysql = (MysqlDAO)AppContext.getDAO();
/* 173 */       JdbcTemplate template = mysql.getTemplate();
/* 174 */       template.update(world_boss_rank_create_sql);
/* 175 */       if (!worldBossFightSide.getDamageMap().isEmpty()) {
/* 176 */         final List<BossDamageData> bossDamageDataList = new ArrayList<>(worldBossFightSide.getDamageMap().values());
/* 177 */         int[] arrayOfInt = template.batchUpdate(world_boss_rank_insert_sql, new BatchPreparedStatementSetter()
/*     */             {
/*     */               public void setValues(PreparedStatement preparedStatement, int i) throws SQLException {
/* 180 */                 preparedStatement.setLong(1, ((BossDamageData)bossDamageDataList.get(i)).fromId);
/* 181 */                 preparedStatement.setString(2, ((BossDamageData)bossDamageDataList.get(i)).name);
/* 182 */                 preparedStatement.setLong(3, ((BossDamageData)bossDamageDataList.get(i)).damage);
/* 183 */                 preparedStatement.setInt(4, ((BossDamageData)bossDamageDataList.get(i)).rank);
/*     */               }
/*     */ 
/*     */               
/*     */               public int getBatchSize() {
/* 188 */                 return bossDamageDataList.size();
/*     */               }
/*     */             });
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private static WorldBossData loadBossDataFromDb(int timeKey) {
/* 196 */     MysqlDAO mysql = (MysqlDAO)AppContext.getDAO();
/* 197 */     JdbcTemplate template = mysql.getTemplate();
/* 198 */     template.update(world_boss_create_sql);
/* 199 */     List<WorldBossData> worldBossDataList = template.query(world_boss_select_sql, new Object[] { Integer.valueOf(timeKey) }, new RowMapper<WorldBossData>()
/*     */         {
/*     */           public WorldBossUtil.WorldBossData mapRow(ResultSet resultSet, int i) throws SQLException {
/* 202 */             WorldBossUtil.WorldBossData worldBossData = new WorldBossUtil.WorldBossData();
/* 203 */             worldBossData.timeKey = resultSet.getInt("timeKey");
/* 204 */             worldBossData.insId = resultSet.getInt("insId");
/* 205 */             String curHp = resultSet.getString("curHp");
/* 206 */             worldBossData.curHp = (Map<Byte, Long>)GsonUtil.fromJson(curHp.isEmpty() ? "{}" : curHp, (new TypeToken<HashMap<Byte, Long>>() {  }
/* 207 */                 ).getType());
/* 208 */             worldBossData.maxHp = resultSet.getLong("maxHp");
/* 209 */             worldBossData.status = resultSet.getByte("status");
/* 210 */             worldBossData.killNum = resultSet.getInt("killNum");
/* 211 */             worldBossData.nextTime = resultSet.getInt("nextTime");
/* 212 */             worldBossData.openTime = resultSet.getInt("openTime");
/* 213 */             return worldBossData;
/*     */           }
/*     */         });
/* 216 */     return (worldBossDataList == null || worldBossDataList.isEmpty()) ? null : worldBossDataList.get(0);
/*     */   }
/*     */   
/*     */   private static List<BossDamageData> loadBossDamageData() {
/* 220 */     MysqlDAO mysql = (MysqlDAO)AppContext.getDAO();
/* 221 */     JdbcTemplate template = mysql.getTemplate();
/* 222 */     template.update(world_boss_rank_create_sql);
/* 223 */     List<BossDamageData> bossDamageDataList = template.query(world_boss_rank_select_sql, new RowMapper<BossDamageData>()
/*     */         {
/*     */           public BossDamageData mapRow(ResultSet resultSet, int i) throws SQLException {
/* 226 */             BossDamageData bossDamageData = new BossDamageData();
/* 227 */             bossDamageData.fromId = resultSet.getLong("playerId");
/* 228 */             bossDamageData.name = resultSet.getString("playerName");
/* 229 */             bossDamageData.damage = resultSet.getLong("damage");
/* 230 */             bossDamageData.rank = resultSet.getInt("rank");
/* 231 */             return bossDamageData;
/*     */           }
/*     */         });
/* 234 */     return (bossDamageDataList == null) ? new ArrayList<>() : bossDamageDataList;
/*     */   }
/*     */   
/*     */   private static void clearRankData() {
/* 238 */     MysqlDAO mysql = (MysqlDAO)AppContext.getDAO();
/* 239 */     JdbcTemplate template = mysql.getTemplate();
/* 240 */     template.update(world_boss_rank_create_sql);
/* 241 */     template.update(world_boss_rank_truncate_sql);
/*     */   }
/*     */   
/*     */   public static WorldBossFightSide getWorldBossFightSide() {
/* 245 */     return worldBossFightSide;
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\bosshome\WorldBossUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */