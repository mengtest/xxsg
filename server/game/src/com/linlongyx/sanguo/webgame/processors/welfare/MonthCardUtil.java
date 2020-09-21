/*     */ package com.linlongyx.sanguo.webgame.processors.welfare;
/*     */ 
/*     */ import com.linlongyx.core.framework.logic.IPlayer;
/*     */ import com.linlongyx.core.framework.protocol.RequestBase;
/*     */ import com.linlongyx.core.utils.LogUtils;
/*     */ import com.linlongyx.core.utils.TimeUtil;
/*     */ import com.linlongyx.sanguo.webgame.app.player.PlayerComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.task.TaskComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.welfare.WelfareComponent;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.ChargeBean;
/*     */ import com.linlongyx.sanguo.webgame.config.parameter.WelfareParameter;
/*     */ import com.linlongyx.sanguo.webgame.constant.ParameterConstant;
/*     */ import com.linlongyx.sanguo.webgame.constant.TaskType;
/*     */ import com.linlongyx.sanguo.webgame.log.LogUtil;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.IntIntValue;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.welfare.MonthCardInfoRequest;
/*     */ import com.linlongyx.sanguo.webgame.service.LookUpService;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.Map;
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
/*     */ public class MonthCardUtil
/*     */ {
/*     */   public static final int TYPE_NORMAL = 1;
/*     */   public static final int TYPE_SPECIAL = 2;
/*     */   public static final int NORMAL_CARD_DAY = 30;
/*     */   public static final int TYPE_WEEK = 5;
/*     */   public static final int WEEK_CARD_DAY = 7;
/*     */   
/*     */   public static void handleBuy(long playerId, ChargeBean chargeBean) {
/*     */     try {
/*  42 */       monthCardBuy(playerId, chargeBean);
/*  43 */     } catch (Exception e) {
/*  44 */       LogUtils.errorLog(new Object[] { "MonthCardUtil handleBuy", Arrays.toString((Object[])e.getStackTrace()) });
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static void monthCardBuy(long playerId, ChargeBean chargeBean) {
/*     */     int languageId;
/*  54 */     LogUtil.errorLog(new Object[] { "monthCardBuy start", Long.valueOf(playerId), Integer.valueOf(chargeBean.getId()) });
/*  55 */     if (chargeBean.getType() != 1 && chargeBean.getType() != 2 && chargeBean.getType() != 5) {
/*     */       return;
/*     */     }
/*  58 */     WelfareComponent welfareComponent = (WelfareComponent)LookUpService.getComponent(playerId, WelfareComponent.class);
/*  59 */     if (null == welfareComponent) {
/*     */       return;
/*     */     }
/*  62 */     Map<Integer, Integer> monthEndTime = welfareComponent.getMonthEndTime();
/*     */     
/*  64 */     if (chargeBean.getType() == 1) {
/*  65 */       int endTime = ((Integer)monthEndTime.getOrDefault(Integer.valueOf(1), Integer.valueOf(0))).intValue();
/*  66 */       boolean isValue = isCardValue(1, endTime);
/*  67 */       if (!isValue) {
/*  68 */         endTime = TimeUtil.timingTime(23, 59) + 59;
/*  69 */         endTime += 2505600;
/*     */       } else {
/*  71 */         endTime += 2592000;
/*     */       } 
/*  73 */       monthEndTime.put(Integer.valueOf(1), Integer.valueOf(endTime));
/*  74 */       welfareComponent.setMonthEndTime(monthEndTime);
/*  75 */       welfareComponent.saveToDB();
/*  76 */       languageId = 1901;
/*  77 */       LogUtil.errorLog(new Object[] { "monthCardBuy normal", Long.valueOf(playerId), Boolean.valueOf(isValue) });
/*  78 */     } else if (chargeBean.getType() == 2) {
/*  79 */       int endTime = ((Integer)monthEndTime.getOrDefault(Integer.valueOf(2), Integer.valueOf(0))).intValue();
/*  80 */       boolean isValue = isCardValue(2, endTime);
/*  81 */       if (!isValue) {
/*  82 */         monthEndTime.put(Integer.valueOf(2), Integer.valueOf(TimeUtil.currentTime()));
/*  83 */         welfareComponent.setMonthEndTime(monthEndTime);
/*  84 */         welfareComponent.saveToDB();
/*     */       } 
/*  86 */       languageId = 1902;
/*  87 */       LogUtil.errorLog(new Object[] { "monthCardBuy special", Long.valueOf(playerId), Boolean.valueOf(isValue) });
/*     */     } else {
/*  89 */       int endTime = ((Integer)monthEndTime.getOrDefault(Integer.valueOf(5), Integer.valueOf(0))).intValue();
/*  90 */       boolean isValue = isCardValue(5, endTime);
/*  91 */       if (!isValue) {
/*  92 */         endTime = TimeUtil.timingTime(23, 59) + 59;
/*  93 */         endTime += 518400;
/*     */       } else {
/*  95 */         endTime += 604800;
/*     */       } 
/*  97 */       monthEndTime.put(Integer.valueOf(5), Integer.valueOf(endTime));
/*  98 */       welfareComponent.setMonthEndTime(monthEndTime);
/*  99 */       welfareComponent.saveToDB();
/* 100 */       languageId = 1903;
/* 101 */       LogUtil.errorLog(new Object[] { "monthCardBuy week", Long.valueOf(playerId), Boolean.valueOf(isValue) });
/*     */     } 
/*     */     
/* 104 */     ArrayList<String> list = new ArrayList<>();
/* 105 */     PlayerComponent playerComponent = (PlayerComponent)LookUpService.getComponent(playerId, PlayerComponent.class);
/* 106 */     String name = (null != playerComponent) ? playerComponent.getPlayerName() : String.valueOf(playerId);
/* 107 */     list.add(name);
/* 108 */     LookUpService.radiate(languageId, list);
/* 109 */     IPlayer iPlayer = LookUpService.getByPlayerId(playerId);
/* 110 */     if (null != iPlayer && null != iPlayer.getSession()) {
/* 111 */       welfareComponent.updateCardRate();
/*     */       
/* 113 */       (new MonthCardInfoProcessor()).handle(iPlayer.getSession(), (RequestBase)new MonthCardInfoRequest());
/*     */     } 
/* 115 */     TaskComponent taskComponent = (TaskComponent)LookUpService.getComponent(playerId, TaskComponent.class);
/* 116 */     if (null != taskComponent) {
/* 117 */       taskComponent.refreshSchedule(TaskType.MonthYearWeek, 0, 1L);
/*     */     }
/* 119 */     LogUtil.errorLog(new Object[] { "monthCardBuy end", Long.valueOf(playerId) });
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static IntIntValue getNormalKeyValue(int normalEndTime, int normalGetTime) {
/* 129 */     IntIntValue intIntValue = new IntIntValue();
/* 130 */     intIntValue.key = 1;
/* 131 */     intIntValue.value = getNormalCardState(normalEndTime, normalGetTime);
/* 132 */     return intIntValue;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static IntIntValue getWeekKeyValue(int weekEndTime, int weekGetTime) {
/* 142 */     IntIntValue intIntValue = new IntIntValue();
/* 143 */     intIntValue.key = 5;
/* 144 */     intIntValue.value = getWeekCardState(weekEndTime, weekGetTime);
/* 145 */     return intIntValue;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static IntIntValue getSpecialKeyValue(int specialEndTime, int specialGetTime) {
/* 155 */     IntIntValue intIntValue = new IntIntValue();
/* 156 */     intIntValue.key = 2;
/* 157 */     intIntValue.value = getSpecialCardState(specialEndTime, specialGetTime);
/* 158 */     return intIntValue;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int getNormalCardState(int normalEndTime, int normalGetTime) {
/* 168 */     if (0 == normalEndTime)
/* 169 */       return 0; 
/* 170 */     if (!isCardValue(1, normalEndTime))
/* 171 */       return 0; 
/* 172 */     if (0 == normalGetTime)
/* 173 */       return 1; 
/* 174 */     if (TimeUtil.inSameDay(normalGetTime)) {
/* 175 */       return 2;
/*     */     }
/* 177 */     return 1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int getWeekCardState(int weekEndTime, int weekGetTime) {
/* 188 */     if (0 == weekEndTime)
/* 189 */       return 0; 
/* 190 */     if (!isCardValue(5, weekEndTime))
/* 191 */       return 0; 
/* 192 */     if (0 == weekGetTime)
/* 193 */       return 1; 
/* 194 */     if (TimeUtil.inSameDay(weekGetTime)) {
/* 195 */       return 2;
/*     */     }
/* 197 */     return 1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean isCardValue(int type, int endTime) {
/* 208 */     if (type == 1) {
/* 209 */       if (0 == endTime) {
/* 210 */         return false;
/*     */       }
/* 212 */       if (TimeUtil.currentTime() < endTime) {
/* 213 */         return true;
/*     */       }
/* 215 */     } else if (type == 2) {
/* 216 */       if (0 != endTime) {
/* 217 */         return true;
/*     */       }
/* 219 */     } else if (type == 5) {
/* 220 */       if (0 == endTime) {
/* 221 */         return false;
/*     */       }
/* 223 */       if (TimeUtil.currentTime() < endTime) {
/* 224 */         return true;
/*     */       }
/*     */     } 
/* 227 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int getNormalCardDay(int normalEndTime) {
/* 236 */     int time = TimeUtil.currentTime();
/* 237 */     if (0 == normalEndTime || time > normalEndTime) {
/* 238 */       return 0;
/*     */     }
/* 240 */     int day = TimeUtil.getDayDiffToOpen(time, normalEndTime);
/* 241 */     return day;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int getSpecialCardState(int specialEndTime, int specialGetTime) {
/* 251 */     if (0 == specialEndTime)
/* 252 */       return 0; 
/* 253 */     if (0 == specialGetTime)
/* 254 */       return 1; 
/* 255 */     if (TimeUtil.inSameDay(specialGetTime)) {
/* 256 */       return 2;
/*     */     }
/* 258 */     return 1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static double getRate(int normalEndTime, int specialEndTime) {
/* 268 */     WelfareParameter welfareParameter = (WelfareParameter)ParameterConstant.getParameter(19);
/* 269 */     int rate = 0;
/* 270 */     if (isCardValue(1, normalEndTime)) {
/* 271 */       rate += welfareParameter.getNormalRate();
/*     */     }
/* 273 */     if (isCardValue(2, specialEndTime)) {
/* 274 */       rate += welfareParameter.getSpecialRate();
/*     */     }
/* 276 */     return rate / 10000.0D;
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\welfare\MonthCardUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */