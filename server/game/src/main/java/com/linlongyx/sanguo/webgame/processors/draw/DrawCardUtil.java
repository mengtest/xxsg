/*     */ package com.linlongyx.sanguo.webgame.processors.draw;
/*     */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*     */ import com.linlongyx.sanguo.webgame.app.draw.DrawCardComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.draw.DrawCardEntity;
/*     */ import com.linlongyx.sanguo.webgame.common.util.FinanceUtil;
/*     */ import com.linlongyx.sanguo.webgame.common.util.RandUtil;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.DrawBean;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.DrawMinBean;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.DrawRaffleBean;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.DrawWarehouseBean;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.TimesGoldBean;
/*     */ import com.linlongyx.sanguo.webgame.config.parameter.DrawCardParameter;
/*     */ import com.linlongyx.sanguo.webgame.constant.ParameterConstant;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.draw.DrawCardPushResponse;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.KeyValue;
/*     */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ 
/*     */ public class DrawCardUtil {
/*  24 */   private static List<KeyValue> records = new LinkedList<>();
/*     */ 
/*     */   
/*     */   private static final int MAX_LENGTH = 5;
/*     */ 
/*     */   
/*     */   private static final int MAX_CARD = 6;
/*     */ 
/*     */   
/*     */   public static synchronized void addRecord(int id, String name, int drawId) {
/*  34 */     DrawCardParameter drawCardParameter = (DrawCardParameter)ParameterConstant.getParameter(48);
/*  35 */     KeyValue keyValue = new KeyValue();
/*  36 */     keyValue.key = drawCardParameter.getWarehouseId(id);
/*  37 */     keyValue.value = id;
/*  38 */     keyValue.valueStr = name;
/*  39 */     if (records.size() >= 5) {
/*  40 */       records.remove(0);
/*     */     }
/*  42 */     records.add(keyValue);
/*     */     
/*  44 */     DrawCardPushResponse drawCardPushResponse = new DrawCardPushResponse();
/*  45 */     drawCardPushResponse.drawId = drawId;
/*  46 */     drawCardPushResponse.data = keyValue;
/*  47 */     LookUpService.broadcast((ResponseBase)drawCardPushResponse);
/*     */     
/*  49 */     drawBroadcast(id, name);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static void drawBroadcast(int id, String name) {
/*  58 */     DrawCardParameter drawCardParameter = (DrawCardParameter)ParameterConstant.getParameter(48);
/*  59 */     int warehouseId = drawCardParameter.getWarehouseId(id);
/*  60 */     DrawWarehouseBean drawWarehouseBean = (DrawWarehouseBean)JsonTableService.getJsonData(warehouseId, DrawWarehouseBean.class);
/*  61 */     if (null == drawWarehouseBean) {
/*     */       return;
/*     */     }
/*  64 */     DrawWarehouseBean.IdBean idBean = (DrawWarehouseBean.IdBean)drawWarehouseBean.getId().get(Integer.valueOf(id));
/*  65 */     if (null == idBean) {
/*     */       return;
/*     */     }
/*  68 */     if (idBean.getIsShow() == 0) {
/*     */       return;
/*     */     }
/*     */     
/*  72 */     ArrayList<String> list = new ArrayList<>();
/*  73 */     list.add(name);
/*  74 */     list.add(idBean.getDesc());
/*  75 */     LookUpService.radiate(4801, list);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void sendRewards(int id, IPlayerSession playerSession) {
/*  84 */     DrawCardParameter drawCardParameter = (DrawCardParameter)ParameterConstant.getParameter(48);
/*  85 */     int warehouseId = drawCardParameter.getWarehouseId(id);
/*  86 */     DrawWarehouseBean drawWarehouseBean = (DrawWarehouseBean)JsonTableService.getJsonData(warehouseId, DrawWarehouseBean.class);
/*  87 */     if (null == drawWarehouseBean) {
/*     */       return;
/*     */     }
/*  90 */     DrawWarehouseBean.IdBean idBean = (DrawWarehouseBean.IdBean)drawWarehouseBean.getId().get(Integer.valueOf(id));
/*  91 */     if (null == idBean) {
/*     */       return;
/*     */     }
/*  94 */     FinanceUtil.reward(FinanceUtil.transform(idBean.getRaffleReward()), playerSession.getPlayer(), ResourceEvent.DrawCardReward, true);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static List<KeyValue> getRecords() {
/* 102 */     return new ArrayList<>(records);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean drawCard(IPlayerSession playerSession, int drawId) {
/* 112 */     DrawBean drawBean = (DrawBean)JsonTableService.getJsonData(drawId, DrawBean.class);
/* 113 */     if (null == drawBean) {
/* 114 */       return false;
/*     */     }
/* 116 */     DrawRaffleBean drawRaffleBean = (DrawRaffleBean)JsonTableService.getJsonData(drawBean.getRaffle(), DrawRaffleBean.class);
/* 117 */     if (null == drawRaffleBean) {
/* 118 */       return false;
/*     */     }
/* 120 */     DrawCardParameter drawCardParameter = (DrawCardParameter)ParameterConstant.getParameter(48);
/* 121 */     Map<Integer, DrawRaffleBean.RoundBean> rounds = drawRaffleBean.getRound();
/* 122 */     DrawCardComponent drawCardComponent = (DrawCardComponent)playerSession.getPlayer().createIfNotExist(DrawCardComponent.class);
/* 123 */     DrawCardEntity drawCardEntity = drawCardComponent.getEntity(drawId);
/* 124 */     int round = drawCardEntity.getRound();
/*     */     
/* 126 */     DrawRaffleBean.RoundBean roundBean = rounds.get(Integer.valueOf(round));
/* 127 */     if (null == roundBean) {
/* 128 */       if (round > 0 && round >= rounds.size()) {
/* 129 */         roundBean = rounds.get(Integer.valueOf(rounds.size() - 1));
/*     */       } else {
/* 131 */         return false;
/*     */       } 
/*     */     }
/* 134 */     int goldCardNum = getGoldCardNum(roundBean);
/* 135 */     int normalCard = roundBean.getNormalCard();
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
/* 148 */     int goldCard = roundBean.getRareId();
/*     */     
/* 150 */     Map<Integer, KeyValue> awards = drawCardEntity.getAwards();
/* 151 */     awards.clear();
/*     */     
/* 153 */     Set<Integer> allNumSet = drawCardParameter.getAllNumSet(round, drawId);
/* 154 */     Set<Integer> numSet = new HashSet<>(drawCardEntity.getNumSet());
/* 155 */     JsonTableService.getJsonTable(DrawMinBean.class);
/* 156 */     allNumSet.removeAll(numSet);
/* 157 */     List<Integer> numList = new ArrayList<>();
/* 158 */     if (allNumSet.size() > 0) {
/* 159 */       numList.addAll(allNumSet);
/* 160 */       Collections.sort(numList);
/*     */     } 
/*     */     
/* 163 */     ArrayList<DrawBean.RareItemBean> rareItem = drawBean.getRareItem();
/* 164 */     Map<Integer, Integer> times = drawCardEntity.getTimes();
/*     */ 
/*     */ 
/*     */     
/* 168 */     int count = 0;
/*     */     
/* 170 */     while (awards.size() < 6) {
/*     */       int pos, num, card;
/*     */       
/* 173 */       count++;
/* 174 */       if (count >= 1000) {
/*     */         break;
/*     */       }
/*     */       
/* 178 */       if (awards.size() >= goldCardNum) {
/* 179 */         card = normalCard;
/* 180 */         num = -1;
/*     */       }
/* 182 */       else if (numList.size() > 0) {
/* 183 */         num = ((Integer)numList.remove(0)).intValue();
/* 184 */         DrawMinBean drawMinBean = (DrawMinBean)JsonTableService.getJsonData(drawId, DrawMinBean.class);
/* 185 */         if (null != drawMinBean) {
/* 186 */           int minId = ((DrawMinBean.NumBean)drawMinBean.getNum().get(Integer.valueOf(num))).getMinId();
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
/* 197 */           card = minId;
/*     */         } else {
/* 199 */           card = goldCard;
/* 200 */           num = 0;
/*     */         } 
/*     */       } else {
/* 203 */         card = goldCard;
/* 204 */         num = 0;
/*     */       } 
/*     */       
/* 207 */       int id = getId(card);
/* 208 */       if (-1 == id) {
/*     */         break;
/*     */       }
/* 211 */       int limit = getLimitNum(rareItem, id);
/* 212 */       if (limit > 0) {
/* 213 */         int time = ((Integer)times.getOrDefault(Integer.valueOf(id), Integer.valueOf(0))).intValue();
/* 214 */         if (time >= limit) {
/*     */           continue;
/*     */         }
/* 217 */         time++;
/* 218 */         times.put(Integer.valueOf(id), Integer.valueOf(time + 1));
/*     */       } 
/*     */       do {
/* 221 */         pos = RandUtil.randNum(1, 6);
/* 222 */         if (!awards.containsKey(Integer.valueOf(pos))) {
/*     */           break;
/*     */         }
/* 225 */         ++count;
/* 226 */       } while (count < 1000);
/*     */ 
/*     */ 
/*     */       
/* 230 */       KeyValue keyValue = new KeyValue();
/* 231 */       keyValue.key = id;
/* 232 */       keyValue.value = num;
/* 233 */       awards.put(Integer.valueOf(pos), keyValue);
/*     */     } 
/* 235 */     boolean flag = (awards.size() == 6);
/* 236 */     if (flag) {
/* 237 */       drawCardEntity.setOpenCards(new HashMap<>());
/* 238 */       drawCardComponent.updateOpenCardsDB(drawId);
/* 239 */       drawCardEntity.setRound(round);
/* 240 */       drawCardComponent.updateRoundDB(drawId);
/* 241 */       drawCardEntity.setTimes(times);
/* 242 */       drawCardComponent.updateTimesDB(drawId);
/* 243 */       drawCardEntity.setAwards(awards);
/* 244 */       drawCardComponent.updateAwardsDB(drawId);
/*     */     } 
/* 246 */     return flag;
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
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static int getLimitNum(ArrayList<DrawBean.RareItemBean> rareItem, int id) {
/* 267 */     for (DrawBean.RareItemBean rareItemBean : rareItem) {
/* 268 */       if (rareItemBean.getId() == id) {
/* 269 */         return rareItemBean.getNum();
/*     */       }
/*     */     } 
/* 272 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static int getGoldCardNum(DrawRaffleBean.RoundBean roundBean) {
/* 281 */     ArrayList<DrawRaffleBean.RoundBean.GoldCardBean> goldCardBeans = roundBean.getGoldCard();
/* 282 */     int sum = 0;
/* 283 */     for (DrawRaffleBean.RoundBean.GoldCardBean goldCardBean : goldCardBeans) {
/* 284 */       sum += goldCardBean.getPer();
/*     */     }
/* 286 */     int rand = RandUtil.randNum(sum);
/* 287 */     ArrayList<DrawRaffleBean.RoundBean.GoldCardBean> arrayList = new ArrayList<>(goldCardBeans);
/* 288 */     Collections.shuffle(arrayList);
/* 289 */     for (DrawRaffleBean.RoundBean.GoldCardBean goldCardBean : arrayList) {
/* 290 */       if (rand <= goldCardBean.getPer()) {
/* 291 */         return goldCardBean.getNum();
/*     */       }
/* 293 */       rand -= goldCardBean.getPer();
/*     */     } 
/* 295 */     return -1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static int getId(int warehouseId) {
/* 304 */     DrawWarehouseBean drawWarehouseBean = (DrawWarehouseBean)JsonTableService.getJsonData(warehouseId, DrawWarehouseBean.class);
/* 305 */     if (null == drawWarehouseBean) {
/* 306 */       return -1;
/*     */     }
/* 308 */     DrawCardParameter drawCardParameter = (DrawCardParameter)ParameterConstant.getParameter(48);
/* 309 */     int sum = drawCardParameter.getHouseProp(warehouseId);
/* 310 */     int rand = RandUtil.randNum(sum);
/* 311 */     ArrayList<Map.Entry<Integer, DrawWarehouseBean.IdBean>> arrayList = new ArrayList<>(drawWarehouseBean.getId().entrySet());
/* 312 */     Collections.shuffle(arrayList);
/* 313 */     for (Map.Entry<Integer, DrawWarehouseBean.IdBean> entry : arrayList) {
/* 314 */       DrawWarehouseBean.IdBean idBean = entry.getValue();
/* 315 */       if (rand <= idBean.getRafflePer()) {
/* 316 */         return ((Integer)entry.getKey()).intValue();
/*     */       }
/* 318 */       rand -= idBean.getRafflePer();
/*     */     } 
/* 320 */     return -1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int getIdByDay(List<Integer> list, int day) {
/* 330 */     Collections.sort(list);
/* 331 */     int selectDay = ((Integer)list.get(0)).intValue();
/* 332 */     for (Integer i : list) {
/* 333 */       if (day >= i.intValue()) {
/* 334 */         selectDay = i.intValue();
/*     */       }
/*     */     } 
/* 337 */     return selectDay;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean isNormalCard(KeyValue keyValue) {
/* 346 */     return (-1L == keyValue.value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean isRareCard(KeyValue keyValue) {
/* 355 */     return (0L == keyValue.value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean isNumCard(KeyValue keyValue) {
/* 364 */     return (keyValue.value > 0L);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static TimesGoldBean getRefreshCost(int num) {
/* 374 */     TimesGoldBean timesGoldBean = (TimesGoldBean)JsonTableService.getJsonData(num, TimesGoldBean.class);
/* 375 */     Map<Integer, Object> map = JsonTableService.getJsonTable(TimesGoldBean.class);
/* 376 */     if (null == timesGoldBean) {
/* 377 */       timesGoldBean = (TimesGoldBean)JsonTableService.getJsonData(map.size(), TimesGoldBean.class);
/*     */     }
/* 379 */     return timesGoldBean;
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\draw\DrawCardUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */