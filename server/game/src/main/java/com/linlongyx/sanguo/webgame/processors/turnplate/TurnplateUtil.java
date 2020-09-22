/*     */ package com.linlongyx.sanguo.webgame.processors.turnplate;
/*     */ 
/*     */ import com.linlongyx.core.framework.logic.IPlayer;
/*     */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*     */ import com.linlongyx.core.utils.GsonUtil;
/*     */ import com.linlongyx.core.utils.TimeUtil;
/*     */ import com.linlongyx.sanguo.webgame.config.parameter.TurnplateParameter;
/*     */ import com.linlongyx.sanguo.webgame.constant.ParameterConstant;
/*     */ import com.linlongyx.sanguo.webgame.log.LogUtil;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.IntIntValue;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.TurnplateRecord;
/*     */ import com.linlongyx.sanguo.webgame.service.ConstantService;
/*     */ import com.linlongyx.sanguo.webgame.service.LookUpService;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.HashSet;
/*     */ import java.util.LinkedList;
/*     */ import java.util.Set;
/*     */ import java.util.concurrent.atomic.AtomicInteger;
/*     */ import java.util.concurrent.locks.Lock;
/*     */ import java.util.concurrent.locks.ReentrantLock;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TurnplateUtil
/*     */ {
/*     */   public static final int TYPE_TEM_DRAW = 1;
/*     */   public static final int TYPE_MATCH_MAX_TEM_DRAW = 2;
/*     */   public static final int TYPE_SINGLE_DRAW = 3;
/*     */   public static final int TYPE_NOT_IN_TEM_DRAW_RANGE = 4;
/*     */   public static final int DRAW_COUNT_LIMIT = 20;
/*  34 */   private static final AtomicInteger goldPool = new AtomicInteger();
/*  35 */   private static volatile int lastModifytime = 0;
/*  36 */   private static int saveTime = 0;
/*     */   
/*  38 */   private static int MAX_RECORD_SIZE = 0;
/*  39 */   private static LinkedList<TurnplateRecord> recordList = new LinkedList<>();
/*  40 */   private static final Lock lock = new ReentrantLock();
/*     */   
/*  42 */   private static Set<Long> PLAYER_OPEN_TURNPLATE_SET = Collections.synchronizedSet(new HashSet<>());
/*     */   
/*     */   public static void addRecord(TurnplateRecord record) {
/*  45 */     if (MAX_RECORD_SIZE == 0) {
/*  46 */       TurnplateParameter turnplateParameter = (TurnplateParameter)ParameterConstant.getParameter(24);
/*  47 */       MAX_RECORD_SIZE = turnplateParameter.getMaxRecord();
/*     */     } 
/*     */     try {
/*  50 */       lock.lock();
/*  51 */       recordList.addLast(record);
/*  52 */       int extra = recordList.size() - MAX_RECORD_SIZE;
/*  53 */       while (extra > 0) {
/*  54 */         recordList.removeFirst();
/*  55 */         extra--;
/*     */       } 
/*     */     } finally {
/*  58 */       lock.unlock();
/*     */     } 
/*     */   }
/*     */   
/*     */   public static ArrayList<TurnplateRecord> getRecordList() {
/*     */     try {
/*  64 */       lock.lock();
/*  65 */       return new ArrayList<>(recordList);
/*     */     } finally {
/*  67 */       lock.unlock();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static void initGoldPool(int value) {
/*  73 */     goldPool.set(value);
/*  74 */     saveTime = lastModifytime = TimeUtil.currentTime();
/*     */   }
/*     */   
/*     */   public static void resetGoldPool() {
/*  78 */     TurnplateParameter turnplateParameter = (TurnplateParameter)ParameterConstant.getParameter(24);
/*  79 */     LogUtil.debugLog(new Object[] { "before reset gold pool value: " + goldPool.get() });
/*  80 */     goldPool.set(turnplateParameter.getInitGold());
/*  81 */     saveTime = lastModifytime = TimeUtil.currentTime();
/*  82 */     saveToDB();
/*     */   }
/*     */   
/*     */   public static void addGoldPool(int addValue) {
/*     */     int curValue;
/*     */     do {
/*  88 */       curValue = goldPool.get();
/*  89 */     } while (!goldPool.compareAndSet(curValue, curValue + addValue));
/*     */     
/*  91 */     boolean needSave = (lastModifytime - saveTime >= 300);
/*  92 */     TurnplateParameter turnplateParameter = (TurnplateParameter)ParameterConstant.getParameter(24);
/*  93 */     if (goldPool.get() < turnplateParameter.getInitGold()) {
/*  94 */       goldPool.set(turnplateParameter.getInitGold());
/*  95 */       needSave = true;
/*     */     } 
/*  97 */     lastModifytime = TimeUtil.currentTime();
/*  98 */     if (needSave) {
/*  99 */       saveToDB();
/* 100 */       saveTime = lastModifytime;
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void saveToDB() {
/* 105 */     IntIntValue intIntValue = new IntIntValue();
/* 106 */     intIntValue.key = goldPool.get();
/* 107 */     intIntValue.value = lastModifytime;
/* 108 */     ConstantService.updateValue("turnplate", GsonUtil.toJson(intIntValue));
/*     */   }
/*     */   
/*     */   public static int getLastModifytime() {
/* 112 */     return lastModifytime;
/*     */   }
/*     */   
/*     */   public static int getGoldPool() {
/* 116 */     int value = goldPool.get();
/* 117 */     if (value == 0) {
/* 118 */       int curTime = TimeUtil.currentTime();
/* 119 */       TurnplateParameter turnplateParameter = (TurnplateParameter)ParameterConstant.getParameter(24);
/* 120 */       initGoldPool(turnplateParameter.getInitGold());
/* 121 */       saveToDB();
/* 122 */       value = turnplateParameter.getInitGold();
/* 123 */       lastModifytime = curTime;
/* 124 */       saveTime = curTime;
/*     */     } 
/* 126 */     return value;
/*     */   }
/*     */   
/*     */   public static void addPlayer(long playerId) {
/* 130 */     PLAYER_OPEN_TURNPLATE_SET.add(Long.valueOf(playerId));
/*     */   }
/*     */   
/*     */   public static void sendTurnplateNotice(ResponseBase response) {
/* 134 */     Set<Long> removeSet = new HashSet<>();
/* 135 */     PLAYER_OPEN_TURNPLATE_SET.forEach(playerId -> {
/*     */           IPlayer player = LookUpService.getByPlayerId(playerId.longValue());
/*     */           if (player == null) {
/*     */             removeSet.add(playerId);
/*     */           } else {
/*     */             player.getSession().sendMessage(response);
/*     */           } 
/*     */         });
/* 143 */     if (!removeSet.isEmpty())
/* 144 */       PLAYER_OPEN_TURNPLATE_SET.removeAll(removeSet); 
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\turnplate\TurnplateUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */