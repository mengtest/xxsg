/*     */ package com.linlongyx.sanguo.webgame.processors.welfare;
/*     */ 
/*     */ import com.google.gson.reflect.TypeToken;
/*     */ import com.linlongyx.core.framework.context.AppContext;
/*     */ import com.linlongyx.core.framework.dao.mysql.MysqlDAO;
/*     */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*     */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*     */ import com.linlongyx.core.utils.GsonUtil;
/*     */ import com.linlongyx.core.utils.TimeUtil;
/*     */ import com.linlongyx.sanguo.webgame.app.player.PlayerComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.welfare.WelfareComponent;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.DailyShopBean;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.ReturnBean;
/*     */ import com.linlongyx.sanguo.webgame.config.parameter.LoginParameter;
/*     */ import com.linlongyx.sanguo.webgame.config.parameter.WelfareParameter;
/*     */ import com.linlongyx.sanguo.webgame.constant.MContext;
/*     */ import com.linlongyx.sanguo.webgame.constant.ParameterConstant;
/*     */ import com.linlongyx.sanguo.webgame.log.LogUtil;
/*     */ import com.linlongyx.sanguo.webgame.net.http.ask.CheckCodeEvent;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.Reward;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.welfare.PushDailyShopInfoResponse;
/*     */ import com.linlongyx.sanguo.webgame.service.ConstantService;
/*     */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.concurrent.atomic.AtomicInteger;
/*     */ import org.springframework.jdbc.core.JdbcTemplate;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class WelfareUtil
/*     */ {
/*     */   public static int getfundLevel(int chargeId, int wlevel) {
/*  38 */     Map<Integer, Object> map = JsonTableService.getJsonTable(DailyShopBean.class);
/*     */     
/*  40 */     ArrayList<Integer> arrayList = new ArrayList<>();
/*  41 */     for (Iterator<Integer> iterator1 = map.keySet().iterator(); iterator1.hasNext(); ) { int id = ((Integer)iterator1.next()).intValue();
/*  42 */       DailyShopBean dailyShopBean = (DailyShopBean)map.get(Integer.valueOf(id));
/*  43 */       if (dailyShopBean.getChargeID().get(Integer.valueOf(chargeId)) == null) {
/*     */         continue;
/*     */       }
/*  46 */       int wordLevel = ((DailyShopBean.ChargeIDBean)dailyShopBean.getChargeID().get(Integer.valueOf(chargeId))).getWorldLevel();
/*  47 */       arrayList.add(Integer.valueOf(wordLevel)); }
/*     */     
/*  49 */     int world = ((Integer)arrayList.get(0)).intValue();
/*  50 */     arrayList.sort(Integer::compareTo);
/*  51 */     for (Iterator<Integer> iterator2 = arrayList.iterator(); iterator2.hasNext(); ) { int level = ((Integer)iterator2.next()).intValue();
/*  52 */       if (wlevel <= level) {
/*  53 */         world = level;
/*     */         break;
/*     */       }  }
/*     */     
/*  57 */     return world;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void noticeDailyShopInfo(IPlayerSession playerSession, WelfareComponent welfareComponent) {
/*  67 */     PushDailyShopInfoResponse response = new PushDailyShopInfoResponse();
/*  68 */     for (Iterator<Integer> iterator = welfareComponent.getDailyBuyReward().keySet().iterator(); iterator.hasNext(); ) { int key = ((Integer)iterator.next()).intValue();
/*  69 */       response.dailyOrderId.add(Integer.valueOf(key)); }
/*     */     
/*  71 */     playerSession.sendMessage((ResponseBase)response);
/*     */   }
/*     */ 
/*     */   
/*  75 */   public static int levelSaveSize = 40;
/*     */   
/*  77 */   private static int dailyWorldLevel = 0;
/*     */   
/*  79 */   public static AtomicInteger weekWorldLevel = new AtomicInteger();
/*     */   
/*  81 */   public static AtomicInteger growfundNum = new AtomicInteger();
/*     */   
/*  83 */   public static AtomicInteger groupCharegeNum = new AtomicInteger();
/*     */   
/*     */   public static synchronized int getDailyWorldLevel() {
/*  86 */     return dailyWorldLevel;
/*     */   }
/*     */   
/*     */   public static synchronized void setDailyWorldLevel(int dailyWorldLevel) {
/*  90 */     WelfareUtil.dailyWorldLevel = dailyWorldLevel;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void initGrowFundSql() {
/*  97 */     MysqlDAO mysql = (MysqlDAO)AppContext.getDAO();
/*  98 */     JdbcTemplate template = mysql.getTemplate();
/*  99 */     String selectSql = "SELECT w.buyFund FROM tb_welfare w  where w.buyFund= 1";
/*     */     
/* 101 */     List<Map<String, Object>> info = template.queryForList(selectSql);
/* 102 */     growfundNum.set(info.size());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void initGroupFirstChargeSql() {
/* 109 */     MysqlDAO mysql = (MysqlDAO)AppContext.getDAO();
/* 110 */     JdbcTemplate template = mysql.getTemplate();
/* 111 */     String selectSql = "SELECT e.newFirstCharge FROM tb_extend e  where e.newFirstCharge != '[]'";
/*     */     
/* 113 */     List<Map<String, Object>> info = template.queryForList(selectSql);
/* 114 */     LoginParameter loginParameter = (LoginParameter)ParameterConstant.getParameter(0);
/* 115 */     groupCharegeNum.set(info.size() + loginParameter.getGroupChargeInit());
/*     */   }
/*     */   
/*     */   public static void initRanksMySql() {
/* 119 */     MysqlDAO mysql = (MysqlDAO)AppContext.getDAO();
/* 120 */     JdbcTemplate template = mysql.getTemplate();
/* 121 */     String selectSql = "SELECT distinct p.playerId,p.level,p.totalValue FROM tb_player p  ORDER BY p.level DESC, p.totalValue DESC, p.playerId DESC LIMIT " + levelSaveSize;
/*     */     
/* 123 */     List<Map<String, Object>> info = template.queryForList(selectSql);
/* 124 */     if (info.size() == 0) {
/*     */       return;
/*     */     }
/* 127 */     int levelSize = 40;
/* 128 */     int rank = 1;
/* 129 */     int totalLevel = 0;
/* 130 */     for (Map<String, Object> map : info) {
/* 131 */       int level = ((Integer)map.get("level")).intValue();
/* 132 */       if (rank <= levelSize) {
/* 133 */         totalLevel += level;
/*     */       }
/*     */     } 
/* 136 */     setDailyWorldLevel(totalLevel / levelSize);
/*     */   }
/*     */ 
/*     */   
/*     */   public static void updateWorldLevel(String key) {
/* 141 */     initRanksMySql();
/* 142 */     ConstantService.update(key, getDailyWorldLevel() + "");
/* 143 */     if (TimeUtil.getWeek() == 1) {
/* 144 */       ConstantService.update("weekWorldLevel", getDailyWorldLevel() + "");
/* 145 */       weekWorldLevel.set(getDailyWorldLevel());
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int getGiftTime(int openDay) {
/* 156 */     int endTime = 0;
/* 157 */     if (!MContext.isHeFu()) {
/* 158 */       endTime = TimeUtil.getZeroTimeStamp(MContext.getOpenTimeInt()) + openDay * 86400;
/*     */     }
/* 160 */     return endTime;
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
/*     */   public static short checkCode(Long userId, Long playerId, String code, ArrayList<Reward> arrayList) {
/* 172 */     CheckCodeEvent checkCodeEvent = new CheckCodeEvent();
/* 173 */     Map<String, String> requestMap = new HashMap<>();
/* 174 */     requestMap.put("userId", String.valueOf(userId));
/* 175 */     requestMap.put("playerId", String.valueOf(playerId));
/* 176 */     requestMap.put("code", code);
/* 177 */     Map<String, Object> responseMap = checkCodeEvent.request(requestMap);
/* 178 */     String errCode = String.valueOf(responseMap.get("code"));
/* 179 */     short retCode = Short.parseShort(errCode);
/* 180 */     LogUtil.errorLog(new Object[] { "checkCode", playerId, responseMap.toString() });
/* 181 */     if (retCode == 10001) {
/* 182 */       String items = String.valueOf(responseMap.get("items"));
/* 183 */       if (null != items) {
/* 184 */         arrayList.addAll((Collection<? extends Reward>)GsonUtil.fromJson(items, (new TypeToken<List<Reward>>() {  }
/* 185 */               ).getType()));
/*     */       }
/* 187 */       return 0;
/*     */     } 
/* 189 */     return retCode;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static int checkBackGift(IPlayerSession playerSession) {
/* 195 */     PlayerComponent playerComponent = (PlayerComponent)playerSession.getPlayer().createIfNotExist(PlayerComponent.class);
/* 196 */     WelfareComponent welfareComponent = (WelfareComponent)playerSession.getPlayer().createIfNotExist(WelfareComponent.class);
/*     */     
/* 198 */     WelfareParameter welfareParameter = (WelfareParameter)ParameterConstant.getParameter(19);
/* 199 */     int day = TimeUtil.getDayDiffToOpen(playerComponent.getLoginOutTime(), TimeUtil.currentTime());
/* 200 */     if (day <= welfareParameter.getOfflineDay()) {
/* 201 */       if (welfareComponent.getBackGiftId() != 0) {
/* 202 */         return welfareComponent.getBackGiftId();
/*     */       }
/* 204 */       return 0;
/*     */     } 
/* 206 */     Map<Integer, Object> map = JsonTableService.getJsonTable(ReturnBean.class);
/* 207 */     int level = 99999;
/* 208 */     int id = 0;
/* 209 */     for (Object o : map.values()) {
/* 210 */       ReturnBean returnBean = (ReturnBean)o;
/* 211 */       if (returnBean.getLevel() >= playerComponent.getLevel() && level > returnBean.getLevel()) {
/* 212 */         level = returnBean.getLevel();
/* 213 */         id = returnBean.getId();
/*     */       } 
/*     */     } 
/* 216 */     if (level == 99999) {
/* 217 */       return 0;
/*     */     }
/* 219 */     welfareComponent.setBackGiftId(id);
/* 220 */     return id;
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\welfare\WelfareUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */