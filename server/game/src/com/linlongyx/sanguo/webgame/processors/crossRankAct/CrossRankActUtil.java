/*     */ package com.linlongyx.sanguo.webgame.processors.crossRankAct;
/*     */ import com.linlongyx.core.utils.LogUtils;
/*     */ import com.linlongyx.core.utils.TimeUtil;
/*     */ import com.linlongyx.sanguo.webgame.app.crossRankAct.CrossRankActComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.crossRankAct.CrossRankActEntity;
/*     */ import com.linlongyx.sanguo.webgame.app.player.PlayerComponent;
/*     */ import com.linlongyx.sanguo.webgame.common.util.FinanceUtil;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.RankingKuafuBean;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.RankingKuafulistBean;
/*     */ import com.linlongyx.sanguo.webgame.config.parameter.CrossRankParameter;
/*     */ import com.linlongyx.sanguo.webgame.constant.CrossRankActType;
/*     */ import com.linlongyx.sanguo.webgame.constant.FestivalCountType;
/*     */ import com.linlongyx.sanguo.webgame.constant.LanguageConstant;
/*     */ import com.linlongyx.sanguo.webgame.constant.MContext;
/*     */ import com.linlongyx.sanguo.webgame.constant.ParameterConstant;
/*     */ import com.linlongyx.sanguo.webgame.constant.RedNoticeConstant;
/*     */ import com.linlongyx.sanguo.webgame.processors.cross.CrossUtil;
/*     */ import com.linlongyx.sanguo.webgame.processors.mail.MailUtil;
/*     */ import com.linlongyx.sanguo.webgame.processors.player.PlayerUtil;
/*     */ import com.linlongyx.sanguo.webgame.processors.player.VersionUtil;
/*     */ import com.linlongyx.sanguo.webgame.processors.rankAct.RankAct;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.RankingData;
/*     */ import com.linlongyx.sanguo.webgame.service.ConstantService;
/*     */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*     */ import com.linlongyx.sanguo.webgame.service.LookUpService;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import java.util.TreeMap;
/*     */ import java.util.concurrent.ConcurrentHashMap;
/*     */ 
/*     */ public class CrossRankActUtil {
/*  35 */   private static Map<Integer, ArrayList<RankingData>> rankMap = new ConcurrentHashMap<>();
/*     */   
/*     */   private static boolean canOpen(RankingKuafuBean bean) {
/*  38 */     int openDays = TimeUtil.getDayDiffToOpen(MContext.getOpenTime());
/*  39 */     int curTime = TimeUtil.currentTime();
/*  40 */     int startTime = TimeUtil.getTimeFromDate(bean.getBeginTime());
/*  41 */     int endTime = TimeUtil.getTimeFromDate(bean.getEndTime());
/*  42 */     return (startTime <= curTime && curTime <= endTime && ((Integer)bean.getServerList().get(0)).intValue() <= openDays && openDays <= ((Integer)bean.getServerList().get(1)).intValue());
/*     */   }
/*     */   
/*     */   public static boolean isActClose(RankingKuafuBean bean) {
/*  46 */     int curTime = TimeUtil.currentTime();
/*  47 */     int endTime = TimeUtil.getTimeFromDate(bean.getEndTime());
/*  48 */     return (curTime > endTime);
/*     */   }
/*     */   
/*     */   public static boolean isActOpen(RankingKuafuBean bean) {
/*  52 */     Map<Integer, Byte> map = (Map<Integer, Byte>)ConstantService.crossRankMap.get(Integer.valueOf(bean.getSeriesId()));
/*  53 */     return (map != null && map.containsKey(Integer.valueOf(bean.getId())) && ((Byte)map.get(Integer.valueOf(bean.getId()))).intValue() == RankAct.RankActState.Opening.getState());
/*     */   }
/*     */   
/*     */   public static Map<Integer, Byte> getCrossRankActList() {
/*  57 */     Map<Integer, Byte> resultMap = new TreeMap<>();
/*  58 */     int curTime = TimeUtil.currentTime();
/*     */     Iterator<Integer> iterator;
/*  60 */     for (iterator = JsonTableService.getJsonTableKey(RankingKuafuBean.class).iterator(); iterator.hasNext(); ) { int id = ((Integer)iterator.next()).intValue();
/*  61 */       RankingKuafuBean bean = (RankingKuafuBean)JsonTableService.getJsonData(id, RankingKuafuBean.class);
/*  62 */       ConstantService.crossRankMap.putIfAbsent(Integer.valueOf(bean.getSeriesId()), new TreeMap<>());
/*  63 */       if (((Map)ConstantService.crossRankMap.get(Integer.valueOf(bean.getSeriesId()))).isEmpty()) {
/*  64 */         if (canOpen(bean)) {
/*  65 */           for (Iterator<Integer> iterator1 = JsonTableService.getJsonTableKey(RankingKuafuBean.class).iterator(); iterator1.hasNext(); ) { int targetId = ((Integer)iterator1.next()).intValue();
/*  66 */             RankingKuafuBean targetBean = (RankingKuafuBean)JsonTableService.getJsonData(targetId, RankingKuafuBean.class);
/*  67 */             if (targetBean.getSeriesId() == bean.getSeriesId() && targetBean.getShowType() == bean.getShowType()) {
/*  68 */               ((Map<Integer, Byte>)ConstantService.crossRankMap.get(Integer.valueOf(bean.getSeriesId()))).put(Integer.valueOf(targetBean.getId()), Byte.valueOf((byte)RankAct.RankActState.NotOpen.getState()));
/*     */             } }
/*     */           
/*  71 */           ((Map<Integer, Byte>)ConstantService.crossRankMap.get(Integer.valueOf(bean.getSeriesId()))).put(Integer.valueOf(bean.getId()), Byte.valueOf((byte)RankAct.RankActState.Opening.getState()));
/*  72 */           CrossRankActType crossRankActType = CrossRankActType.getRankActType(bean.getType());
/*  73 */           CrossUtil.addRankServer("CrossRankActUtil::getCrossRankActList", bean.getId(), bean.getType(), bean.getPlayerNum(), crossRankActType.isDesc(), bean.getEntryConditions());
/*     */           
/*  75 */           resultMap.putAll((Map<? extends Integer, ? extends Byte>)ConstantService.crossRankMap.get(Integer.valueOf(bean.getSeriesId())));
/*     */           break;
/*     */         } 
/*     */         continue;
/*     */       } 
/*  80 */       if (((Map)ConstantService.crossRankMap.get(Integer.valueOf(bean.getSeriesId()))).containsKey(Integer.valueOf(bean.getId()))) {
/*  81 */         int startTime = TimeUtil.getTimeFromDate(bean.getBeginTime());
/*  82 */         int endTime = TimeUtil.getTimeFromDate(bean.getEndTime());
/*  83 */         if (startTime <= curTime && curTime <= endTime) {
/*  84 */           if (((Byte)((Map)ConstantService.crossRankMap.get(Integer.valueOf(bean.getSeriesId()))).get(Integer.valueOf(bean.getId()))).intValue() != RankAct.RankActState.Opening.getState()) {
/*  85 */             CrossRankActType crossRankActType = CrossRankActType.getRankActType(bean.getType());
/*  86 */             CrossUtil.addRankServer("CrossRankActUtil::getCrossRankActList", bean.getId(), bean.getType(), bean.getPlayerNum(), crossRankActType.isDesc(), bean.getEntryConditions());
/*  87 */             ((Map<Integer, Byte>)ConstantService.crossRankMap.get(Integer.valueOf(bean.getSeriesId()))).put(Integer.valueOf(bean.getId()), Byte.valueOf((byte)RankAct.RankActState.Opening.getState()));
/*     */           } 
/*  89 */           resultMap.putAll((Map<? extends Integer, ? extends Byte>)ConstantService.crossRankMap.get(Integer.valueOf(bean.getSeriesId())));
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
/*     */           break;
/*     */         } 
/*     */       }  }
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
/* 114 */     if (resultMap.isEmpty()) {
/* 115 */       for (Map.Entry<Integer, Map<Integer, Byte>> act : (Iterable<Map.Entry<Integer, Map<Integer, Byte>>>)ConstantService.crossRankMap.entrySet()) {
/* 116 */         if (resultMap.isEmpty()) {
/* 117 */           for (Map.Entry<Integer, Byte> kv : (Iterable<Map.Entry<Integer, Byte>>)((Map)act.getValue()).entrySet()) {
/*     */             
/* 119 */             if (((Byte)kv.getValue()).intValue() == RankAct.RankActState.Opening.getState()) {
/* 120 */               resultMap.putAll(act.getValue());
/*     */               
/*     */               break;
/*     */             } 
/* 124 */             RankingKuafuBean bean = (RankingKuafuBean)JsonTableService.getJsonData(((Integer)kv.getKey()).intValue(), RankingKuafuBean.class);
/* 125 */             if (curTime <= TimeUtil.getTimeFromDate(bean.getCloseTime())) {
/* 126 */               resultMap.putAll(act.getValue());
/*     */             }
/*     */           } 
/*     */         }
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/* 134 */     return resultMap;
/*     */   }
/*     */   
/*     */   public static void refreshRankMap(int actId, ArrayList<RankingData> rankingData) {
/* 138 */     rankMap.put(Integer.valueOf(actId), rankingData);
/*     */   }
/*     */   
/*     */   public static ArrayList<RankingData> getRankDataList(int id) {
/* 142 */     return rankMap.getOrDefault(Integer.valueOf(id), new ArrayList<>());
/*     */   }
/*     */   
/*     */   public static void refreshRankValue(int type, long newValue, long playerId) {
/* 146 */     CrossRankParameter crossRankParameter = (CrossRankParameter)ParameterConstant.getParameter(59);
/* 147 */     Set<Integer> crossRankActIdList = crossRankParameter.getCrossActIdsByType(type);
/* 148 */     if (crossRankActIdList.isEmpty()) {
/*     */       return;
/*     */     }
/* 151 */     LogUtils.errorLog(new Object[] { "CrossRankActUtil addCrossRankValue", Long.valueOf(playerId), Integer.valueOf(type), Long.valueOf(newValue) });
/* 152 */     CrossRankActComponent crossRankActComponent = (CrossRankActComponent)LookUpService.getComponent(playerId, CrossRankActComponent.class);
/* 153 */     if (crossRankActComponent == null)
/* 154 */       return;  for (Iterator<Integer> iterator = crossRankActIdList.iterator(); iterator.hasNext(); ) { int id = ((Integer)iterator.next()).intValue();
/* 155 */       RankingKuafuBean bean = (RankingKuafuBean)JsonTableService.getJsonData(id, RankingKuafuBean.class);
/* 156 */       CrossRankActEntity entity = crossRankActComponent.getEntity(id);
/* 157 */       if (entity == null)
/*     */         continue; 
/* 159 */       if (bean.getOpenType() == 1 && !isActOpen(bean))
/*     */         continue; 
/* 161 */       int countType = bean.getCountType();
/* 162 */       long oldValue = entity.getValue();
/* 163 */       long value = entity.getValue();
/* 164 */       if (entity.isOpen() && isActOpen(bean)) {
/* 165 */         if (countType == FestivalCountType.cumDone.getType()) {
/* 166 */           value += newValue;
/*     */         } else {
/* 168 */           if (value == newValue)
/* 169 */             continue;  if (countType == FestivalCountType.singleDone.getType()) {
/* 170 */             if (newValue > value) value = newValue; 
/*     */           } else {
/* 172 */             value = newValue;
/*     */           } 
/*     */         } 
/* 175 */         if (oldValue != value) {
/* 176 */           Map<Integer, Integer> states = entity.getStates();
/* 177 */           for (Iterator<Integer> iterator1 = bean.getPersonalReward().iterator(); iterator1.hasNext(); ) { int itemId = ((Integer)iterator1.next()).intValue();
/* 178 */             RankingKuafulistBean kuafulistBean = (RankingKuafulistBean)JsonTableService.getJsonData(itemId, RankingKuafulistBean.class);
/* 179 */             if (kuafulistBean != null) {
/* 180 */               int status = ((Integer)states.getOrDefault(Integer.valueOf(itemId), Integer.valueOf(0))).intValue();
/* 181 */               if (status == 1 || status == 2) {
/*     */                 continue;
/*     */               }
/* 184 */               if (countType == FestivalCountType.less.getType()) {
/* 185 */                 if (value <= Integer.parseInt(kuafulistBean.getTarget())) {
/* 186 */                   states.put(Integer.valueOf(itemId), Integer.valueOf(1));
/* 187 */                   entity.setStates(states);
/* 188 */                   PlayerUtil.sendRedNotice(Long.valueOf(playerId), RedNoticeConstant.CrossRankAct.getSys(), id);
/*     */                 }  continue;
/*     */               } 
/* 191 */               if (value >= Integer.parseInt(kuafulistBean.getTarget())) {
/* 192 */                 states.put(Integer.valueOf(itemId), Integer.valueOf(1));
/* 193 */                 entity.setStates(states);
/* 194 */                 PlayerUtil.sendRedNotice(Long.valueOf(playerId), RedNoticeConstant.CrossRankAct.getSys(), id);
/*     */               } 
/*     */             }  }
/*     */ 
/*     */           
/* 199 */           entity.setValue(value);
/* 200 */           crossRankActComponent.updateValueDB(entity.getId());
/* 201 */           crossRankActComponent.updateStatesDB(entity.getId());
/* 202 */           PlayerComponent playerComponent = (PlayerComponent)LookUpService.getComponent(playerId, PlayerComponent.class);
/* 203 */           if (playerComponent != null) {
/* 204 */             RankingData data = RankingUtil.transform(playerId, VersionUtil.getCrossPlayerName(playerId, playerComponent.getPlayerName()), playerComponent.getTotalValue(), playerComponent.getLevel(), playerComponent
/* 205 */                 .getHead(), playerComponent.getVip(), playerComponent.getSex(), playerComponent.getWearTitle(), playerComponent.getWearFashion(), playerComponent.getQuality(), value, 0);
/*     */             
/* 207 */             CrossRankActType crossRankActType = CrossRankActType.getRankActType(type);
/* 208 */             CrossUtil.uploadPlayerRankData("CrossRankActUtil::refreshRankValue", id, type, bean.getPlayerNum(), crossRankActType.isDesc(), bean.getEntryConditions(), data);
/*     */           } 
/*     */           
/* 211 */           LogUtils.errorLog(new Object[] { "CrossRankActUtil value change", Long.valueOf(playerId), Integer.valueOf(type), Long.valueOf(oldValue), Long.valueOf(newValue), Long.valueOf(value) });
/*     */         } 
/*     */       }  }
/*     */     
/* 215 */     crossRankActComponent.saveToDB();
/*     */   }
/*     */ 
/*     */   
/*     */   public static void balanceExpireCrossRankAct(long playerId) {
/* 220 */     Map<Integer, Byte> rankActMap = getCrossRankActList();
/* 221 */     CrossRankActComponent component = (CrossRankActComponent)LookUpService.getComponent(playerId, CrossRankActComponent.class);
/* 222 */     if (component != null) {
/* 223 */       for (Map.Entry<Integer, Byte> kv : rankActMap.entrySet()) {
/* 224 */         if (((Byte)kv.getValue()).intValue() == RankAct.RankActState.Expire.getState()) {
/* 225 */           component.balancePersonalReward(((Integer)kv.getKey()).intValue());
/*     */         }
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static synchronized void balanceCrossRankAct() {
/* 235 */     Map<Integer, Byte> rankActMap = getCrossRankActList();
/* 236 */     if (rankActMap.isEmpty())
/* 237 */       return;  for (Map.Entry<Integer, Byte> kv : rankActMap.entrySet()) {
/* 238 */       RankingKuafuBean bean = (RankingKuafuBean)JsonTableService.getJsonData(((Integer)kv.getKey()).intValue(), RankingKuafuBean.class);
/* 239 */       if (((Byte)kv.getValue()).intValue() == RankAct.RankActState.Opening.getState() && isActClose(bean)) {
/* 240 */         int targetId = -1;
/* 241 */         for (Iterator<Integer> iterator = ConstantService.crossRankMap.keySet().iterator(); iterator.hasNext(); ) { int serialId = ((Integer)iterator.next()).intValue();
/* 242 */           if (((Map)ConstantService.crossRankMap.get(Integer.valueOf(serialId))).containsKey(Integer.valueOf(bean.getId()))) {
/* 243 */             targetId = serialId; break;
/*     */           }  }
/*     */         
/* 246 */         if (targetId == -1)
/* 247 */           continue;  ((Map<Integer, Byte>)ConstantService.crossRankMap.get(Integer.valueOf(targetId))).put(Integer.valueOf(bean.getId()), Byte.valueOf((byte)RankAct.RankActState.Expire.getState()));
/* 248 */         ArrayList<RankingData> rankList = CrossUtil.getRankList("", ((Integer)kv.getKey()).intValue(), true);
/* 249 */         for (RankingData data : rankList) {
/* 250 */           if (data.rank > 0 && LookUpService.getPlayerComponent(data.playerId) != null) {
/* 251 */             int priceId = 0;
/* 252 */             CrossRankParameter crossRankParameter = (CrossRankParameter)ParameterConstant.getParameter(59);
/* 253 */             for (Integer itemId : bean.getRankReward()) {
/* 254 */               List<Integer> range = crossRankParameter.getRange(itemId.intValue());
/* 255 */               if (range != null && range.size() >= 2 && ((Integer)range.get(0)).intValue() <= data.rank && data.rank <= ((Integer)range.get(1)).intValue()) {
/* 256 */                 priceId = itemId.intValue();
/*     */                 break;
/*     */               } 
/*     */             } 
/* 260 */             RankingKuafulistBean rankingKuafulistBean = (RankingKuafulistBean)JsonTableService.getJsonData(priceId, RankingKuafulistBean.class);
/* 261 */             if (null != rankingKuafulistBean) {
/* 262 */               String title = LanguageConstant.getAndReplaceLanguage(5901, new String[] { bean.getTagName() });
/* 263 */               String content = LanguageConstant.getAndReplaceLanguage(5902, new String[] { bean.getTagName(), String.valueOf(data.rank) });
/* 264 */               MailUtil.sendSysMail(data.playerId, FinanceUtil.rewardGet(FinanceUtil.transform(rankingKuafulistBean.getReward())), title, content.replace("{rank}", String.valueOf(data.rank)));
/*     */             } 
/*     */           } 
/*     */         } 
/*     */ 
/*     */         
/* 270 */         for (Iterator<Long> iterator1 = LookUpService.getOnlinePlayer().iterator(); iterator1.hasNext(); ) { long playerId = ((Long)iterator1.next()).longValue();
/* 271 */           CrossRankActComponent component = (CrossRankActComponent)LookUpService.getComponent(playerId, CrossRankActComponent.class);
/* 272 */           if (component != null)
/* 273 */             component.balancePersonalReward(((Integer)kv.getKey()).intValue());  }
/*     */       
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\crossRankAct\CrossRankActUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */