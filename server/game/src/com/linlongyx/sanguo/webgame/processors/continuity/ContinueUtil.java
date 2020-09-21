/*     */ package com.linlongyx.sanguo.webgame.processors.continuity;
/*     */ 
/*     */ import com.linlongyx.core.utils.LogUtils;
/*     */ import com.linlongyx.core.utils.TimeUtil;
/*     */ import com.linlongyx.sanguo.webgame.app.continuity.ContinuityComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.continuity.ContinuityEntity;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.ContinFillingBean;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.ContinFillinglistBean;
/*     */ import com.linlongyx.sanguo.webgame.config.parameter.ContinuityParameter;
/*     */ import com.linlongyx.sanguo.webgame.constant.ParameterConstant;
/*     */ import com.linlongyx.sanguo.webgame.processors.player.PlayerUtil;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.FestivalTime;
/*     */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*     */ import com.linlongyx.sanguo.webgame.service.LookUpService;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.Map;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ContinueUtil
/*     */ {
/*  25 */   private static ContinuityParameter continuityParameter = (ContinuityParameter)ParameterConstant.getParameter(21);
/*     */ 
/*     */   
/*     */   public static final int TOTAL_KEY = -1;
/*     */ 
/*     */ 
/*     */   
/*     */   public static void refresh(long playerId, long value) {
/*  33 */     if (value <= 0L) {
/*     */       return;
/*     */     }
/*     */     try {
/*  37 */       addCharge(playerId, value);
/*  38 */     } catch (Exception e) {
/*  39 */       LogUtils.errorLog(new Object[] { "ContinueUtil", Arrays.toString((Object[])e.getStackTrace()) });
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static void addCharge(long playerId, long value) {
/*  49 */     LogUtils.errorLog(new Object[] { "ContinueUtil addCharge start", Long.valueOf(playerId), Long.valueOf(value) });
/*  50 */     ArrayList<Integer> list = continuityParameter.getActId(true);
/*  51 */     if (null == list || list.isEmpty()) {
/*     */       return;
/*     */     }
/*  54 */     ContinuityComponent continuityComponent = (ContinuityComponent)LookUpService.getComponent(playerId, ContinuityComponent.class);
/*  55 */     if (null == continuityComponent) {
/*     */       return;
/*     */     }
/*     */     
/*  59 */     for (Integer id : list) {
/*  60 */       ContinFillingBean continFillingBean = (ContinFillingBean)JsonTableService.getJsonData(id.intValue(), ContinFillingBean.class);
/*  61 */       if (null == continFillingBean) {
/*  62 */         LogUtils.errorLog(new Object[] { "ContinueUtil addCharge continFillingBean", Long.valueOf(playerId), Long.valueOf(value) });
/*     */         continue;
/*     */       } 
/*  65 */       FestivalTime festivalTime = continuityParameter.getFestivalTime(id.intValue());
/*  66 */       if (null == festivalTime) {
/*  67 */         LogUtils.errorLog(new Object[] { "ContinueUtil addCharge festivalTime", Long.valueOf(playerId), Long.valueOf(value) });
/*     */         continue;
/*     */       } 
/*  70 */       int curDay = TimeUtil.getDayDisTime(festivalTime.startTime);
/*  71 */       ContinuityEntity continuityEntity = continuityComponent.getContinuityEntity(id.intValue());
/*  72 */       Map<Integer, Long> dateCharges = continuityEntity.getDateCharges();
/*  73 */       Map<Integer, Integer> states = continuityEntity.getStates();
/*  74 */       ArrayList<Integer> rankList = continFillingBean.getRankList();
/*  75 */       int type = PlayerUtil.getPlatformType();
/*     */       
/*  77 */       for (Integer tid : rankList) {
/*  78 */         ContinFillinglistBean continFillinglistBean = (ContinFillinglistBean)JsonTableService.getJsonData(tid.intValue(), ContinFillinglistBean.class);
/*  79 */         if (null == continFillinglistBean || judgeEntryDone(states, continFillinglistBean) || curDay != continFillinglistBean.getDay()) {
/*     */           continue;
/*     */         }
/*  82 */         if (type != continFillinglistBean.getConnectortype()) {
/*     */           continue;
/*     */         }
/*  85 */         long oldValue = ((Long)dateCharges.getOrDefault(Integer.valueOf(curDay), Long.valueOf(0L))).longValue();
/*  86 */         oldValue += value;
/*     */         
/*  88 */         if (oldValue >= continFillinglistBean.getRmb()) {
/*  89 */           oldValue = continFillinglistBean.getRmb();
/*  90 */           states.put(Integer.valueOf(continFillinglistBean.getId()), Integer.valueOf(1));
/*     */           
/*  92 */           if (!judgeTotalDone(states) && 
/*  93 */             getTotalNum(states) >= continFillingBean.getCommit()) {
/*  94 */             states.put(Integer.valueOf(-1), Integer.valueOf(1));
/*     */           }
/*     */ 
/*     */           
/*  98 */           continuityEntity.setStates(states);
/*  99 */           continuityComponent.updateStatesDB(id.intValue());
/*     */         } 
/* 101 */         dateCharges.put(Integer.valueOf(curDay), Long.valueOf(oldValue));
/* 102 */         continuityEntity.setDateCharges(dateCharges);
/* 103 */         continuityComponent.updateDateChargesDB(id.intValue());
/*     */       } 
/*     */     } 
/* 106 */     continuityComponent.maybeSaveToDB();
/* 107 */     LogUtils.errorLog(new Object[] { "ContinueUtil addCharge end", Long.valueOf(playerId), Long.valueOf(value) });
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean judgeEntryDone(Map<Integer, Integer> states, ContinFillinglistBean continFillinglistBean) {
/* 117 */     int state = ((Integer)states.getOrDefault(Integer.valueOf(continFillinglistBean.getId()), Integer.valueOf(0))).intValue();
/* 118 */     return (0 != state);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean judgeTotalDone(Map<Integer, Integer> states) {
/* 127 */     int state = ((Integer)states.getOrDefault(Integer.valueOf(-1), Integer.valueOf(0))).intValue();
/* 128 */     return (0 != state);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int getTotalNum(Map<Integer, Integer> states) {
/* 137 */     int num = 0;
/* 138 */     for (Map.Entry<Integer, Integer> entry : states.entrySet()) {
/* 139 */       if (((Integer)entry.getKey()).intValue() != -1 && (((Integer)entry.getValue()).intValue() == 1 || ((Integer)entry.getValue()).intValue() == 2)) {
/* 140 */         num++;
/*     */       }
/*     */     } 
/* 143 */     return num;
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\continuity\ContinueUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */