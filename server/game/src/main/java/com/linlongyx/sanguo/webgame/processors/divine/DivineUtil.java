/*     */ package com.linlongyx.sanguo.webgame.processors.divine;
/*     */ 
/*     */ import com.linlongyx.core.framework.context.AppContext;
/*     */ import com.linlongyx.core.framework.dao.mysql.MysqlDAO;
/*     */ import com.linlongyx.core.utils.GsonUtil;
/*     */ import com.linlongyx.core.utils.TimeUtil;
/*     */ import com.linlongyx.sanguo.webgame.app.divine.DivineComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.divine.DivineEntity;
/*     */ import com.linlongyx.sanguo.webgame.common.util.FinanceUtil;
/*     */ import com.linlongyx.sanguo.webgame.common.util.RandUtil;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.DivineBean;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.DivineNumBean;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.DivineRewardBean;
/*     */ import com.linlongyx.sanguo.webgame.config.parameter.DivineParameter;
/*     */ import com.linlongyx.sanguo.webgame.constant.LanguageConstant;
/*     */ import com.linlongyx.sanguo.webgame.constant.ParameterConstant;
/*     */ import com.linlongyx.sanguo.webgame.constant.RedNoticeConstant;
/*     */ import com.linlongyx.sanguo.webgame.log.LogUtil;
/*     */ import com.linlongyx.sanguo.webgame.processors.limit.LimitUtil;
/*     */ import com.linlongyx.sanguo.webgame.processors.mail.MailUtil;
/*     */ import com.linlongyx.sanguo.webgame.processors.player.PlayerUtil;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.DivineRankData;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.IntIntValue;
/*     */ import com.linlongyx.sanguo.webgame.service.ConstantService;
/*     */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*     */ import com.linlongyx.sanguo.webgame.service.LookUpService;
/*     */ import java.sql.ResultSet;
/*     */ import java.sql.SQLException;
/*     */ import java.text.DecimalFormat;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.TreeMap;
/*     */ import java.util.concurrent.ConcurrentHashMap;
/*     */ import java.util.stream.Collectors;
/*     */ import org.springframework.jdbc.core.JdbcTemplate;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class DivineUtil
/*     */ {
/*  42 */   private static DecimalFormat decimalFormat = new DecimalFormat("00000");
/*     */   
/*  44 */   private static String divineRankSql = "SELECT d.maxDivineNum, d.playerId, p.playerName FROM tb_divine d INNER JOIN tb_player p ON d.playerId=p.playerId AND d.actId=? AND d.maxDivineNum>0";
/*     */   
/*  46 */   private static Map<Integer, Map<Long, DivineRankData>> rankDataMap = new ConcurrentHashMap<>();
/*     */   
/*     */   public static String fmtDivineNum(int divineNum) {
/*  49 */     return decimalFormat.format(divineNum);
/*     */   }
/*     */   
/*     */   public static void loadRecords(int actId) {
/*  53 */     MysqlDAO mysql = (MysqlDAO)AppContext.getDAO();
/*  54 */     JdbcTemplate template = mysql.getTemplate();
/*  55 */     List<DivineRankData> recordDataList = template.query(divineRankSql, new Object[] { Integer.valueOf(actId) }, (resultSet, i) -> {
/*     */           DivineRankData divineRankData = new DivineRankData();
/*     */           divineRankData.playerId = resultSet.getLong("playerId");
/*     */           divineRankData.divineNum = fmtDivineNum(resultSet.getInt("maxDivineNum"));
/*     */           divineRankData.name = resultSet.getString("playerName");
/*     */           return divineRankData;
/*     */         });
/*  62 */     rankDataMap.putIfAbsent(Integer.valueOf(actId), new ConcurrentHashMap<>());
/*  63 */     for (DivineRankData data : recordDataList) {
/*  64 */       ((Map<Long, DivineRankData>)rankDataMap.get(Integer.valueOf(actId))).put(Long.valueOf(data.playerId), data);
/*     */     }
/*     */   }
/*     */   
/*     */   public static synchronized DivineRankData updateMaxDivineNum(int actId, long playerId, String playerName, int maxDivineNum) {
/*  69 */     rankDataMap.putIfAbsent(Integer.valueOf(actId), new ConcurrentHashMap<>());
/*  70 */     String divineNum = fmtDivineNum(maxDivineNum);
/*  71 */     if (((Map)rankDataMap.get(Integer.valueOf(actId))).containsKey(Long.valueOf(playerId))) {
/*  72 */       DivineRankData divineRankData = (DivineRankData)((Map)rankDataMap.get(Integer.valueOf(actId))).get(Long.valueOf(playerId));
/*  73 */       if (Integer.parseInt(divineRankData.divineNum) < maxDivineNum) {
/*  74 */         divineRankData.divineNum = divineNum;
/*  75 */         divineRankData.time = TimeUtil.currentTime();
/*  76 */         ((Map<Long, DivineRankData>)rankDataMap.get(Integer.valueOf(actId))).put(Long.valueOf(playerId), divineRankData);
/*     */       } 
/*  78 */       return divineRankData;
/*     */     } 
/*  80 */     DivineRankData data = new DivineRankData();
/*  81 */     data.playerId = playerId;
/*  82 */     data.divineNum = divineNum;
/*  83 */     data.name = playerName;
/*  84 */     data.time = TimeUtil.currentTime();
/*  85 */     ((Map<Long, DivineRankData>)rankDataMap.get(Integer.valueOf(actId))).put(Long.valueOf(playerId), data);
/*  86 */     return data;
/*     */   }
/*     */ 
/*     */   
/*     */   public static synchronized List<DivineRankData> getRankList(int actId, int limit) {
/*  91 */     if (!rankDataMap.containsKey(Integer.valueOf(actId))) {
/*  92 */       rankDataMap.putIfAbsent(Integer.valueOf(actId), new ConcurrentHashMap<>());
/*     */     }
/*  94 */     if (limit <= 0) {
/*  95 */       return (List<DivineRankData>)((Map)rankDataMap.get(Integer.valueOf(actId))).values().stream().filter(r -> (Integer.parseInt(r.divineNum) > 0)).sorted((o1, o2) -> o2.divineNum.equals(o1.divineNum) ? Integer.compare(o1.time, o2.time) : Integer.compare(Integer.parseInt(o2.divineNum), Integer.parseInt(o1.divineNum)))
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 101 */         .collect(Collectors.toList());
/*     */     }
/* 103 */     return (List<DivineRankData>)((Map)rankDataMap.get(Integer.valueOf(actId))).values().stream().filter(r -> (Integer.parseInt(r.divineNum) > 0)).sorted((o1, o2) -> o2.divineNum.equals(o1.divineNum) ? Integer.compare(o1.time, o2.time) : Integer.compare(Integer.parseInt(o2.divineNum), Integer.parseInt(o1.divineNum)))
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 109 */       .limit(limit).collect(Collectors.toList());
/*     */   }
/*     */ 
/*     */   
/*     */   private static int genSpecialDivineNum() {
/* 114 */     return RandUtil.randNum(1, 99999);
/*     */   }
/*     */   
/*     */   public static int getCurDivineActId() {
/* 118 */     for (Iterator<Integer> iterator = JsonTableService.getJsonTableKey(DivineBean.class).iterator(); iterator.hasNext(); ) { int id = ((Integer)iterator.next()).intValue();
/* 119 */       DivineBean bean = (DivineBean)JsonTableService.getJsonData(id, DivineBean.class);
/* 120 */       if (LimitUtil.isValid(bean.getServerType(), bean.getDay()) && LimitUtil.isActOpen(bean.getServerType(), bean
/* 121 */           .getBeginTime(), bean.getEndTime())) {
/* 122 */         if (!ConstantService.divineMap.containsKey(Integer.valueOf(bean.getId()))) {
/* 123 */           IntIntValue kv = new IntIntValue();
/* 124 */           kv.key = genSpecialDivineNum();
/* 125 */           kv.value = 0;
/* 126 */           ConstantService.divineMap.put(Integer.valueOf(bean.getId()), kv);
/* 127 */           ConstantService.updateValue(ConstantService.DIVINE, GsonUtil.toJson(ConstantService.divineMap));
/*     */         } 
/* 129 */         return bean.getId();
/*     */       }  }
/*     */     
/* 132 */     return -1;
/*     */   }
/*     */   
/*     */   public static void updatePlayerName(long playerId, String newPlayerName) {
/* 136 */     for (Map<Long, DivineRankData> map : rankDataMap.values()) {
/* 137 */       if (map.containsKey(Long.valueOf(playerId)) && !((DivineRankData)map.get(Long.valueOf(playerId))).name.equals(newPlayerName)) {
/* 138 */         ((DivineRankData)map.get(Long.valueOf(playerId))).name = newPlayerName;
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static synchronized void balanceDivineRank() {
/* 147 */     boolean affect = false;
/* 148 */     DivineParameter divineParameter = (DivineParameter)ParameterConstant.getParameter(57);
/* 149 */     String title = LanguageConstant.getLanguage(5701);
/* 150 */     for (Iterator<Integer> iterator = JsonTableService.getJsonTableKey(DivineBean.class).iterator(); iterator.hasNext(); ) { int id = ((Integer)iterator.next()).intValue();
/* 151 */       DivineBean bean = (DivineBean)JsonTableService.getJsonData(id, DivineBean.class);
/* 152 */       if (LimitUtil.isValid(bean.getServerType(), bean.getDay()) && !LimitUtil.isActOpen(bean.getServerType(), bean
/* 153 */           .getBeginTime(), bean.getEndTime())) {
/* 154 */         if (ConstantService.divineMap.containsKey(Integer.valueOf(id)) && ((IntIntValue)ConstantService.divineMap.get(Integer.valueOf(id))).value == 0) {
/* 155 */           ((IntIntValue)ConstantService.divineMap.get(Integer.valueOf(id))).value = 1;
/* 156 */           affect = true;
/*     */           
/* 158 */           LogUtil.errorLog(new Object[] { "divine balance begin", Integer.valueOf(id) });
/* 159 */           List<DivineRankData> list = getRankList(id, divineParameter.getRankSize());
/* 160 */           for (int i = 0; i < list.size(); i++) {
/* 161 */             DivineRankData rankData = list.get(i);
/* 162 */             rankData.rank = i + 1;
/* 163 */             int targetId = 0;
/* 164 */             for (Map.Entry<Integer, Integer> kv : (Iterable<Map.Entry<Integer, Integer>>)((Map)divineParameter.getRankMap().get(Integer.valueOf(id))).entrySet()) {
/* 165 */               targetId = ((Integer)kv.getValue()).intValue();
/* 166 */               if (rankData.rank <= ((Integer)kv.getKey()).intValue()) {
/*     */                 break;
/*     */               }
/*     */             } 
/* 170 */             DivineRewardBean divineRewardBean = (DivineRewardBean)JsonTableService.getJsonData(targetId, DivineRewardBean.class);
/* 171 */             LogUtil.errorLog(new Object[] { "divine balance", Long.valueOf(rankData.playerId), Integer.valueOf(rankData.rank), Integer.valueOf(targetId) });
/* 172 */             if (divineRewardBean != null) {
/* 173 */               MailUtil.sendSysMail(rankData.playerId, FinanceUtil.transform(divineRewardBean.getReward()), title, LanguageConstant.getAndReplaceLanguage(5702, new String[] { rankData.rank + "" }));
/*     */             }
/*     */           } 
/* 176 */           LogUtil.errorLog(new Object[] { "divine balance end", Integer.valueOf(id) });
/*     */         } 
/*     */       } }
/*     */     
/* 180 */     if (affect) {
/* 181 */       ConstantService.updateValue(ConstantService.DIVINE, GsonUtil.toJson(ConstantService.divineMap));
/*     */     }
/*     */   }
/*     */   
/*     */   public static int[] drawTenTime(char[] luckNum) {
/* 186 */     int[] result = new int[10];
/* 187 */     for (int i = 0; i < result.length; i++) {
/* 188 */       result[i] = drawOneTime(luckNum);
/*     */     }
/* 190 */     return result;
/*     */   }
/*     */   
/*     */   public static int drawOneTime(char[] luckNum) {
/* 194 */     int target = 0, tmp = 0;
/* 195 */     for (int i = 0; i < luckNum.length; i++) {
/* 196 */       int posNum = luckNum[i] - 48;
/* 197 */       DivineNumBean bean = (DivineNumBean)JsonTableService.getJsonData(i + 1, DivineNumBean.class);
/* 198 */       Map<Integer, DivineNumBean.NumBean> map = bean.getNum();
/* 199 */       Map<Integer, Integer> proMap = new TreeMap<>();
/* 200 */       int total = 0;
/* 201 */       for (Map.Entry<Integer, DivineNumBean.NumBean> kv : map.entrySet()) {
/* 202 */         if (((Integer)kv.getKey()).intValue() == posNum) {
/* 203 */           total += ((DivineNumBean.NumBean)kv.getValue()).getLuckPro();
/*     */         } else {
/* 205 */           total += ((DivineNumBean.NumBean)kv.getValue()).getPro();
/*     */         } 
/* 207 */         proMap.put(Integer.valueOf(total), kv.getKey());
/*     */       } 
/* 209 */       int randNum = RandUtil.randNum(total);
/* 210 */       for (Map.Entry<Integer, Integer> kv : proMap.entrySet()) {
/* 211 */         if (randNum <= ((Integer)kv.getKey()).intValue()) {
/* 212 */           tmp = ((Integer)kv.getValue()).intValue();
/*     */           break;
/*     */         } 
/*     */       } 
/* 216 */       target = target * 10 + tmp;
/*     */     } 
/* 218 */     return target;
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
/*     */ 
/*     */   
/*     */   public static int sameDigitCount(char[] src, char[] target) {
/* 235 */     if (src == null || target == null) {
/* 236 */       return 0;
/*     */     }
/* 238 */     int sameCount = 0, length = Math.min(src.length, target.length);
/* 239 */     for (int i = 0; i < length; i++) {
/* 240 */       if (src[i] == target[i]) {
/* 241 */         sameCount++;
/*     */       }
/*     */     } 
/* 244 */     return sameCount;
/*     */   }
/*     */   
/*     */   public static void addTimes(long playerId, int num) {
/* 248 */     int curActId = getCurDivineActId();
/* 249 */     if (curActId == -1) {
/*     */       return;
/*     */     }
/* 252 */     DivineComponent divineComponent = (DivineComponent)LookUpService.getComponent(playerId, DivineComponent.class);
/* 253 */     if (divineComponent == null) {
/*     */       return;
/*     */     }
/* 256 */     DivineParameter divineParameter = (DivineParameter)ParameterConstant.getParameter(57);
/*     */     
/* 258 */     DivineEntity divineEntity = divineComponent.getEntity(curActId);
/* 259 */     int total = divineEntity.getYuanbao() + num;
/* 260 */     if (total < divineParameter.getPerTimes()) {
/* 261 */       divineEntity.setYuanbao(total);
/*     */     } else {
/* 263 */       int times = total / divineParameter.getPerTimes();
/* 264 */       divineEntity.setYuanbao(total % divineParameter.getPerTimes());
/* 265 */       divineEntity.setTimes(divineEntity.getTimes() + times);
/* 266 */       PlayerUtil.sendRedNotice(Long.valueOf(playerId), RedNoticeConstant.Divine.getSys(), 0);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\divine\DivineUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */