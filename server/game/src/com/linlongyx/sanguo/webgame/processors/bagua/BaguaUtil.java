/*     */ package com.linlongyx.sanguo.webgame.processors.bagua;
/*     */ 
/*     */ import com.google.gson.reflect.TypeToken;
/*     */ import com.linlongyx.core.framework.context.AppContext;
/*     */ import com.linlongyx.core.framework.dao.mysql.MysqlDAO;
/*     */ import com.linlongyx.core.utils.GsonUtil;
/*     */ import com.linlongyx.sanguo.webgame.log.LogUtil;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.BaguaRecord;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.LongStringValue;
/*     */ import java.lang.reflect.Type;
/*     */ import java.sql.PreparedStatement;
/*     */ import java.sql.ResultSet;
/*     */ import java.sql.SQLException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.Collections;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.TreeMap;
/*     */ import org.springframework.jdbc.core.BatchPreparedStatementSetter;
/*     */ import org.springframework.jdbc.core.JdbcTemplate;
/*     */ 
/*     */ public class BaguaUtil
/*     */ {
/*  25 */   private static Map<Integer, Map<Integer, BaguaRecord>> recordMap = new TreeMap<>();
/*     */   
/*  27 */   private static List<BaguaRecordTable> addRecords = new ArrayList<>();
/*     */   
/*  29 */   private static String bagua_record_create_sql = "CREATE TABLE IF NOT EXISTS `tb_baguaRecord`(`chapterId`INT(11) NOT NULL DEFAULT '0',`insId` INT(11) NOT NULL DEFAULT '0',`createtime` INT(11) NOT NULL DEFAULT '0',`playerList` TEXT NOT NULL,PRIMARY KEY (`chapterId`,`insId`)) ENGINE=InnoDB DEFAULT CHARSET=utf8;";
/*  30 */   private static String bagua_record_insert_sql = "INSERT IGNORE INTO `tb_baguaRecord`(`chapterId`, `insId`, `createtime`, `playerList`) VALUES (?,?,?,?)";
/*  31 */   private static String bagua_record_select_sql = "SELECT * FROM tb_baguaRecord ORDER BY `chapterId`,`insId` ASC";
/*     */ 
/*     */   
/*     */   public static void saveRecords() {
/*  35 */     if (addRecords.size() > 0) {
/*  36 */       MysqlDAO mysql = (MysqlDAO)AppContext.getDAO();
/*  37 */       JdbcTemplate template = mysql.getTemplate();
/*  38 */       int[] result = template.batchUpdate(bagua_record_insert_sql, new BatchPreparedStatementSetter()
/*     */           {
/*     */             public void setValues(PreparedStatement preparedStatement, int i) throws SQLException {
/*  41 */               preparedStatement.setInt(1, (BaguaUtil.addRecords.get(i)).chapterId);
/*  42 */               preparedStatement.setInt(2, (BaguaUtil.addRecords.get(i)).insId);
/*  43 */               preparedStatement.setInt(3, (BaguaUtil.addRecords.get(i)).createtime);
/*  44 */               preparedStatement.setString(4, GsonUtil.toJson((BaguaUtil.addRecords.get(i)).playerList));
/*     */             }
/*     */ 
/*     */             
/*     */             public int getBatchSize() {
/*  49 */               return BaguaUtil.addRecords.size();
/*     */             }
/*     */           });
/*  52 */       LogUtil.debugLog(new Object[] { "bagua save record", Arrays.toString(result) });
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static void loadRecords() {
/*  59 */     MysqlDAO mysql = (MysqlDAO)AppContext.getDAO();
/*  60 */     JdbcTemplate template = mysql.getTemplate();
/*  61 */     template.update(bagua_record_create_sql);
/*  62 */     Type playerListType = (new TypeToken<ArrayList<LongStringValue>>() {  }).getType();
/*  63 */     List<BaguaRecordTable> recordDataList = template.query(bagua_record_select_sql, (resultSet, i) -> {
/*     */           BaguaRecordTable baguaRecordData = new BaguaRecordTable();
/*     */           baguaRecordData.chapterId = resultSet.getInt("chapterId");
/*     */           baguaRecordData.insId = resultSet.getInt("insId");
/*     */           baguaRecordData.createtime = resultSet.getInt("createtime");
/*     */           baguaRecordData.playerList = (ArrayList<LongStringValue>)GsonUtil.fromJson(resultSet.getString("playerList"), playerListType);
/*     */           return baguaRecordData;
/*     */         });
/*  71 */     for (BaguaRecordTable data : recordDataList) {
/*  72 */       recordMap.putIfAbsent(Integer.valueOf(data.chapterId), new TreeMap<>());
/*  73 */       ((Map<Integer, BaguaRecord>)recordMap.get(Integer.valueOf(data.chapterId))).put(Integer.valueOf(data.insId), transform(data));
/*     */     } 
/*     */   }
/*     */   
/*     */   public static Map<Integer, Map<Integer, BaguaRecord>> getRecordMap() {
/*  78 */     return Collections.unmodifiableMap(recordMap);
/*     */   }
/*     */   
/*     */   public static void addRecord(BaguaRecordTable baguaRecordData) {
/*  82 */     recordMap.putIfAbsent(Integer.valueOf(baguaRecordData.chapterId), new TreeMap<>());
/*  83 */     if (!((Map)recordMap.get(Integer.valueOf(baguaRecordData.chapterId))).containsKey(Integer.valueOf(baguaRecordData.insId))) {
/*  84 */       ((Map<Integer, BaguaRecord>)recordMap.get(Integer.valueOf(baguaRecordData.chapterId))).put(Integer.valueOf(baguaRecordData.insId), transform(baguaRecordData));
/*  85 */       addRecords.add(baguaRecordData);
/*     */     } 
/*     */   }
/*     */   
/*     */   public static class BaguaRecordTable
/*     */   {
/*     */     public int chapterId;
/*     */     public int insId;
/*     */     public int createtime;
/*  94 */     public ArrayList<LongStringValue> playerList = new ArrayList<>();
/*     */   }
/*     */   
/*     */   public static BaguaRecord transform(BaguaRecordTable baguaRecordData) {
/*  98 */     BaguaRecord baguaRecord = new BaguaRecord();
/*  99 */     baguaRecord.insId = baguaRecordData.insId;
/* 100 */     baguaRecord.playerList = baguaRecordData.playerList;
/* 101 */     return baguaRecord;
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\bagua\BaguaUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */