/*     */ package com.linlongyx.sanguo.webgame.processors.charge;
/*     */ 
/*     */ import com.linlongyx.core.framework.logic.IPlayer;
/*     */ import com.linlongyx.core.utils.TimeUtil;
/*     */ import com.linlongyx.sanguo.webgame.app.welfare.WelfareComponent;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.ChargeBean;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.EverydayChargeBean;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.OneyuanBean;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.OneyuanRewardBean;
/*     */ import com.linlongyx.sanguo.webgame.config.parameter.FundParameter;
/*     */ import com.linlongyx.sanguo.webgame.constant.MContext;
/*     */ import com.linlongyx.sanguo.webgame.constant.ParameterConstant;
/*     */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*     */ import com.linlongyx.sanguo.webgame.service.LookUpService;
/*     */ import com.linlongyx.sanguo.webgame.service.StatisticsService;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ChargeUtil
/*     */ {
/*     */   private static boolean isDebug = false;
/*  28 */   private static int tempTime = 0;
/*     */   
/*     */   public static void handleRecharge(IPlayer iPlayer, ChargeBean chargeBean) {
/*  31 */     FundParameter fundParameter = (FundParameter)ParameterConstant.getParameter(25);
/*  32 */     int curDay = TimeUtil.getDayDisTime(getOpenTime());
/*  33 */     WelfareComponent welfareComponent = (WelfareComponent)LookUpService.getComponent(iPlayer.getPlayerId(), WelfareComponent.class);
/*  34 */     if (null == welfareComponent) {
/*     */       return;
/*     */     }
/*     */     
/*  38 */     if (chargeBean.getType() == 4 && 
/*  39 */       welfareComponent.getOneState() == 0) {
/*  40 */       int now = TimeUtil.currentTime();
/*  41 */       int id = getOneyuan(now, fundParameter);
/*  42 */       OneyuanBean oneyuanBean = (OneyuanBean)JsonTableService.getJsonData(id, OneyuanBean.class);
/*  43 */       if (null != oneyuanBean) {
/*  44 */         for (int i = 0; i < oneyuanBean.getChargeId().size(); i++) {
/*  45 */           int chargeId = ((Integer)oneyuanBean.getChargeId().get(i)).intValue();
/*  46 */           if (chargeBean.getId() == chargeId) {
/*  47 */             if (oneyuanBean.getRewardId().size() > i) {
/*  48 */               int rewardId = ((Integer)oneyuanBean.getRewardId().get(i)).intValue();
/*  49 */               OneyuanRewardBean oneyuanRewardBean = (OneyuanRewardBean)JsonTableService.getJsonData(rewardId, OneyuanRewardBean.class);
/*  50 */               if (null != oneyuanRewardBean) {
/*  51 */                 welfareComponent.setOneState(1);
/*  52 */                 welfareComponent.setId(id);
/*     */                 
/*  54 */                 ArrayList<String> list = new ArrayList<>();
/*  55 */                 list.add(iPlayer.getPlayerName());
/*  56 */                 list.add(oneyuanRewardBean.getName());
/*  57 */                 LookUpService.radiate(oneyuanRewardBean.getLanguge(), list);
/*     */               } 
/*     */             } 
/*     */             break;
/*     */           } 
/*     */         } 
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static int getOneyuanDay(int now, FundParameter fundParameter) {
/*  69 */     return (now - fundParameter.getOneyuanStartTime()) / 86400 + 1;
/*     */   }
/*     */   
/*     */   public static int getOneyuan(int now, FundParameter fundParameter) {
/*  73 */     int id = 0;
/*     */     
/*  75 */     if (MContext.isHeFu()) {
/*  76 */       return id;
/*     */     }
/*  78 */     if (MContext.getOpenTimeInt() > fundParameter.getOneyuanStartTime()) {
/*  79 */       if (StatisticsService.getDays() <= fundParameter.getOneyuanDays())
/*  80 */         id = StatisticsService.getDays(); 
/*     */     } else {
/*  82 */       int day = getOneyuanDay(now, fundParameter);
/*  83 */       if (day <= fundParameter.getOneyuanDays())
/*  84 */         id = day; 
/*     */     } 
/*  86 */     return id;
/*     */   }
/*     */   
/*     */   public static void setIsDebug(boolean isDebug) {
/*  90 */     isDebug = isDebug;
/*  91 */     tempTime = isDebug ? TimeUtil.currentTime() : 0;
/*     */   }
/*     */   
/*     */   public static void setTempTime() {
/*  95 */     if (0 != tempTime) {
/*  96 */       tempTime -= 86400;
/*     */     }
/*     */   }
/*     */   
/*     */   public static int getOpenTime() {
/* 101 */     if (isDebug) {
/* 102 */       return tempTime;
/*     */     }
/* 104 */     return MContext.getOpenTimeInt();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int updateEveryDayChargeLevel(int playerLevel) {
/* 115 */     ArrayList<Integer> list = new ArrayList<>();
/* 116 */     Map<Integer, Object> map = JsonTableService.getJsonTable(EverydayChargeBean.class);
/* 117 */     for (Object object : map.values()) {
/* 118 */       EverydayChargeBean everydayChargeBean = (EverydayChargeBean)object;
/* 119 */       if (list.indexOf(Integer.valueOf(everydayChargeBean.getPlayerLevel())) < 0) {
/* 120 */         list.add(Integer.valueOf(everydayChargeBean.getPlayerLevel()));
/*     */       }
/*     */     } 
/* 123 */     int currLevel = ((Integer)list.get(0)).intValue();
/* 124 */     Collections.sort(list);
/* 125 */     for (Iterator<Integer> iterator = list.iterator(); iterator.hasNext(); ) { int level = ((Integer)iterator.next()).intValue();
/* 126 */       if (playerLevel >= level) {
/* 127 */         currLevel = level;
/*     */       } }
/*     */     
/* 130 */     return currLevel;
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\charge\ChargeUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */