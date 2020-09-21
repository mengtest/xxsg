/*     */ package com.linlongyx.sanguo.webgame.processors.luckyTurntable;
/*     */ 
/*     */ import com.linlongyx.core.framework.context.AppContext;
/*     */ import com.linlongyx.core.framework.dao.mysql.MysqlDAO;
/*     */ import com.linlongyx.sanguo.webgame.common.util.FinanceUtil;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.LuckyRankBean;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.LuckylistBean;
/*     */ import com.linlongyx.sanguo.webgame.config.parameter.LuckyTurntableParameter;
/*     */ import com.linlongyx.sanguo.webgame.constant.LanguageConstant;
/*     */ import com.linlongyx.sanguo.webgame.constant.ParameterConstant;
/*     */ import com.linlongyx.sanguo.webgame.processors.limit.LimitUtil;
/*     */ import com.linlongyx.sanguo.webgame.processors.mail.MailUtil;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.IntIntValue;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.LuckyTurntableRecord;
/*     */ import com.linlongyx.sanguo.webgame.service.ConstantService;
/*     */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*     */ import java.sql.ResultSet;
/*     */ import java.sql.SQLException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.LinkedList;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.concurrent.ConcurrentHashMap;
/*     */ import java.util.concurrent.locks.Lock;
/*     */ import java.util.concurrent.locks.ReentrantLock;
/*     */ import java.util.stream.Collectors;
/*     */ import org.springframework.jdbc.core.JdbcTemplate;
/*     */ import org.springframework.jdbc.core.RowMapper;
/*     */ 
/*     */ public class LuckyTurntableUtil
/*     */ {
/*     */   private static final int RECORD_SIZE = 20;
/*  35 */   private static LinkedList<LuckyTurntableRecord> recordList = new LinkedList<>();
/*  36 */   private static Map<Integer, Map<Long, LuckyTurntableData>> luckyRankMap = new HashMap<>();
/*  37 */   private static Map<Integer, List<IntIntValue>> rankRangeMap = new HashMap<>();
/*     */   
/*  39 */   private static String lucky_turntable_select_sql = "SELECT a.`actId`,a.`playerId`,a.`totalPoint`,a.`lasttime`,b.`playerName` FROM tb_luckyTurntable a INNER JOIN tb_player b ON a.`playerId`=b.`playerId` AND a.`totalPoint`>? AND `actId`=? ORDER BY a.`totalPoint` DESC LIMIT ?";
/*  40 */   private static Lock lock = new ReentrantLock();
/*     */   
/*     */   public static void addRecord(LuckyTurntableRecord record) {
/*     */     try {
/*  44 */       lock.lock();
/*  45 */       recordList.addLast(record);
/*  46 */       int extra = recordList.size() - 20;
/*  47 */       while (extra > 0) {
/*  48 */         recordList.removeFirst();
/*  49 */         extra--;
/*     */       } 
/*     */     } finally {
/*  52 */       lock.unlock();
/*     */     } 
/*     */   }
/*     */   
/*     */   public static ArrayList<LuckyTurntableRecord> getRecordList() {
/*     */     try {
/*  58 */       lock.lock();
/*  59 */       return new ArrayList<>(recordList);
/*     */     } finally {
/*  61 */       lock.unlock();
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void initFromDb() {
/*  66 */     LuckyTurntableParameter luckyTurntableParameter = (LuckyTurntableParameter)ParameterConstant.getParameter(35);
/*  67 */     MysqlDAO mysql = (MysqlDAO)AppContext.getDAO();
/*  68 */     JdbcTemplate template = mysql.getTemplate();
/*  69 */     for (Integer id : JsonTableService.getJsonTableKey(LuckylistBean.class)) {
/*  70 */       LuckylistBean luckylistBean = (LuckylistBean)JsonTableService.getJsonData(id.intValue(), LuckylistBean.class);
/*  71 */       if (LimitUtil.isValid(luckylistBean.getServerTypeb(), luckylistBean.getDay()) && LimitUtil.isActOpen(luckylistBean.getServerTypeb(), luckylistBean.getBeginTimeb(), luckylistBean.getEndTimeb())) {
/*     */         
/*  73 */         List<LuckyTurntableData> rankDataList = template.query(lucky_turntable_select_sql, new Object[] { Integer.valueOf(luckylistBean.getEntryConditions()), id, Integer.valueOf(luckyTurntableParameter.getRankSize()) }, new RowMapper<LuckyTurntableData>()
/*     */             {
/*     */               public LuckyTurntableUtil.LuckyTurntableData mapRow(ResultSet resultSet, int i) throws SQLException {
/*  76 */                 LuckyTurntableUtil.LuckyTurntableData data = new LuckyTurntableUtil.LuckyTurntableData();
/*  77 */                 data.actId = resultSet.getInt("actId");
/*  78 */                 data.playerId = resultSet.getLong("playerId");
/*  79 */                 data.totalPoint = resultSet.getInt("totalPoint");
/*  80 */                 data.playerName = resultSet.getString("playerName");
/*  81 */                 data.lasttime = resultSet.getLong("lasttime");
/*  82 */                 return data;
/*     */               }
/*     */             });
/*  85 */         if (rankDataList != null) {
/*  86 */           Map<Long, LuckyTurntableData> map = new ConcurrentHashMap<>();
/*  87 */           for (LuckyTurntableData data : rankDataList) {
/*  88 */             map.put(Long.valueOf(data.playerId), data);
/*     */           }
/*  90 */           luckyRankMap.put(id, map);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public static List<LuckyTurntableData> getRankList(int actId) {
/*     */     try {
/*  98 */       lock.lock();
/*  99 */       LuckyTurntableParameter luckyTurntableParameter = (LuckyTurntableParameter)ParameterConstant.getParameter(35);
/* 100 */       luckyRankMap.putIfAbsent(Integer.valueOf(actId), new ConcurrentHashMap<>());
/* 101 */       LuckylistBean luckylistBean = (LuckylistBean)JsonTableService.getJsonData(actId, LuckylistBean.class);
/* 102 */       return (List)((Map)luckyRankMap.get(Integer.valueOf(actId))).values().stream().filter(o -> (o.totalPoint >= luckylistBean.getEntryConditions())).sorted((o1, o2) -> (o1.totalPoint == o2.totalPoint) ? Long.compare(o1.lasttime, o2.lasttime) : Long.compare(o2.totalPoint, o1.totalPoint))
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 108 */         .limit(luckyTurntableParameter.getRankSize()).collect(Collectors.toList());
/*     */     } finally {
/* 110 */       lock.unlock();
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void updatePlayerPoint(int actId, long playerId, String playerName, int totalPoint) {
/*     */     try {
/* 116 */       lock.lock();
/* 117 */       luckyRankMap.putIfAbsent(Integer.valueOf(actId), new ConcurrentHashMap<>());
/* 118 */       if (!((Map)luckyRankMap.get(Integer.valueOf(actId))).containsKey(Long.valueOf(playerId))) {
/* 119 */         LuckyTurntableData data = new LuckyTurntableData();
/* 120 */         data.playerId = playerId;
/* 121 */         data.playerName = playerName;
/* 122 */         data.totalPoint = totalPoint;
/* 123 */         data.lasttime = System.currentTimeMillis();
/* 124 */         data.actId = actId;
/* 125 */         ((Map<Long, LuckyTurntableData>)luckyRankMap.get(Integer.valueOf(actId))).put(Long.valueOf(playerId), data);
/*     */       } else {
/* 127 */         ((LuckyTurntableData)((Map)luckyRankMap.get(Integer.valueOf(actId))).get(Long.valueOf(playerId))).totalPoint = totalPoint;
/*     */       } 
/*     */     } finally {
/* 130 */       lock.unlock();
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void balanceRank() {
/*     */     try {
/* 136 */       lock.lock();
/* 137 */       Map<Integer, Object> luckyListBeanMap = JsonTableService.getJsonTable(LuckylistBean.class);
/* 138 */       for (Map.Entry<Integer, Object> kv : luckyListBeanMap.entrySet()) {
/* 139 */         LuckylistBean bean = (LuckylistBean)kv.getValue();
/* 140 */         String title = LanguageConstant.getAndReplaceLanguage(5101, new String[] { bean.getName() });
/* 141 */         if (!LimitUtil.isActOpen(bean.getServerTypeb(), bean
/* 142 */             .getBeginTimeb(), bean.getEndTimeb())) {
/* 143 */           if (ConstantService.turntableStateMap.containsKey(Integer.valueOf(bean.getActId())) && ((Byte)ConstantService.turntableStateMap.get(Integer.valueOf(bean.getActId()))).byteValue() == 1) {
/* 144 */             if (!rankRangeMap.containsKey(Integer.valueOf(bean.getActId())) || ((List)rankRangeMap.get(Integer.valueOf(bean.getActId()))).isEmpty()) {
/* 145 */               List<IntIntValue> rangeList = new ArrayList<>();
/* 146 */               for (Iterator<Integer> iterator = JsonTableService.getJsonTableKey(LuckyRankBean.class).iterator(); iterator.hasNext(); ) { int id = ((Integer)iterator.next()).intValue();
/* 147 */                 LuckyRankBean luckyRankBean = (LuckyRankBean)JsonTableService.getJsonData(id, LuckyRankBean.class);
/* 148 */                 if (luckyRankBean.getActId() == bean.getActId()) {
/* 149 */                   IntIntValue intIntValue = new IntIntValue();
/* 150 */                   intIntValue.key = luckyRankBean.getId();
/* 151 */                   intIntValue.value = luckyRankBean.getTarget();
/* 152 */                   rangeList.add(intIntValue);
/*     */                 }  }
/*     */               
/* 155 */               rankRangeMap.put(Integer.valueOf(bean.getActId()), rangeList);
/*     */             } 
/* 157 */             ConstantService.turntableStateMap.put(Integer.valueOf(bean.getActId()), Byte.valueOf((byte)0));
/* 158 */             List<LuckyTurntableData> rankList = getRankList(bean.getActId());
/* 159 */             int rank = 1;
/* 160 */             for (LuckyTurntableData data : rankList) {
/* 161 */               for (IntIntValue range : rankRangeMap.get(Integer.valueOf(bean.getActId()))) {
/* 162 */                 if (rank <= range.value) {
/* 163 */                   LuckyRankBean luckyRankBean = (LuckyRankBean)JsonTableService.getJsonData(range.key, LuckyRankBean.class);
/* 164 */                   String content = LanguageConstant.getAndReplaceLanguage(5102, new String[] { bean.getName(), String.valueOf(rank) });
/* 165 */                   MailUtil.sendSysMail(data.playerId, FinanceUtil.transform(luckyRankBean.getReward()), title, content);
/* 166 */                   rank++;
/*     */                 } 
/*     */               } 
/*     */             } 
/*     */           } 
/*     */         }
/*     */       } 
/*     */     } finally {
/*     */       
/* 175 */       lock.unlock();
/*     */     } 
/*     */   }
/*     */   
/*     */   public static class LuckyTurntableData {
/*     */     public long playerId;
/*     */     public String playerName;
/*     */     public int actId;
/*     */     public int totalPoint;
/*     */     public long lasttime;
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\luckyTurntable\LuckyTurntableUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */