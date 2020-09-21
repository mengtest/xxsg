/*     */ package com.linlongyx.sanguo.webgame.processors.talisman;
/*     */ 
/*     */ import com.linlongyx.core.framework.base.ProcessorBase;
/*     */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*     */ import com.linlongyx.core.framework.protocol.RequestBase;
/*     */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*     */ import com.linlongyx.sanguo.webgame.app.bag.BagComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.rune.RuneComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.talisman.TalismanComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.talisman.TalismanEntity;
/*     */ import com.linlongyx.sanguo.webgame.app.task.TaskComponent;
/*     */ import com.linlongyx.sanguo.webgame.common.util.FinanceUtil;
/*     */ import com.linlongyx.sanguo.webgame.common.util.RandUtil;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.RewardBean;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.TalismanBoxBean;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.TalismanBoxlibBean;
/*     */ import com.linlongyx.sanguo.webgame.constant.CurrencyType;
/*     */ import com.linlongyx.sanguo.webgame.constant.FunctionOpenConstant;
/*     */ import com.linlongyx.sanguo.webgame.constant.ResourceEvent;
/*     */ import com.linlongyx.sanguo.webgame.constant.TaskType;
/*     */ import com.linlongyx.sanguo.webgame.processors.player.PlayerUtil;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.IntIntValue;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.Reward;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.talisman.TalismanDrawRequest;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.talisman.TalismanDrawResponse;
/*     */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ 
/*     */ public class TalismanDrawProcessor extends ProcessorBase<TalismanDrawRequest, TalismanDrawResponse> {
/*     */   protected void initResponse() {
/*  32 */     this.response = (ResponseBase)new TalismanDrawResponse();
/*     */   }
/*     */   
/*     */   protected short handleRequest(IPlayerSession playerSession, TalismanDrawRequest request) {
/*     */     int drawTimes;
/*  37 */     if (!FunctionOpenConstant.isFunctionOpen(playerSession, 29)) {
/*  38 */       return 10061;
/*     */     }
/*  40 */     TalismanBoxBean talismanBoxBean = (TalismanBoxBean)JsonTableService.getJsonData(request.actId, TalismanBoxBean.class);
/*  41 */     if (talismanBoxBean == null) {
/*  42 */       return 11808;
/*     */     }
/*  44 */     BagComponent bagComponent = (BagComponent)playerSession.getPlayer().createIfNotExist(BagComponent.class);
/*  45 */     TalismanComponent talismanComponent = (TalismanComponent)playerSession.getPlayer().createIfNotExist(TalismanComponent.class);
/*  46 */     TalismanEntity talismanEntity = (TalismanEntity)talismanComponent.getEntity(request.actId + "");
/*     */     
/*  48 */     if (request.type == 1) {
/*  49 */       drawTimes = 1;
/*  50 */       if (talismanEntity.getFreeTimes() > 0) {
/*  51 */         talismanEntity.setFreeTimes(talismanEntity.getFreeTimes() - 1);
/*  52 */       } else if (bagComponent.check(talismanBoxBean.getCostItem(), 1L)) {
/*  53 */         bagComponent.deleteItem(talismanBoxBean.getCostItem(), 1, ResourceEvent.TalismanDraw);
/*     */       } else {
/*  55 */         if (!FinanceUtil.checkCurrency(playerSession.getPlayer(), CurrencyType.CCY, talismanBoxBean.getCost())) {
/*  56 */           return 10052;
/*     */         }
/*  58 */         FinanceUtil.decCurrency(playerSession.getPlayer(), CurrencyType.CCY, talismanBoxBean.getCost(), ResourceEvent.TalismanDraw);
/*     */       } 
/*     */       
/*  61 */       FinanceUtil.reward(FinanceUtil.transform(talismanBoxBean.getCurrency()), playerSession.getPlayer(), ResourceEvent.TalismanDraw, true);
/*  62 */     } else if (request.type == 2) {
/*  63 */       drawTimes = 10;
/*  64 */       if (!FinanceUtil.checkCurrency(playerSession.getPlayer(), CurrencyType.CCY, (talismanBoxBean.getCost() * drawTimes))) {
/*  65 */         return 10052;
/*     */       }
/*  67 */       FinanceUtil.decCurrency(playerSession.getPlayer(), CurrencyType.CCY, (talismanBoxBean.getCost() * drawTimes), ResourceEvent.TalismanDraw);
/*     */       
/*  69 */       FinanceUtil.reward(FinanceUtil.transform(talismanBoxBean.getTenCurrency()), playerSession.getPlayer(), ResourceEvent.TalismanDraw, true);
/*     */     } else {
/*  71 */       return 11808;
/*     */     } 
/*  73 */     ((TalismanDrawResponse)this.response).itemList = drawManyTimes(talismanEntity, talismanBoxBean, drawTimes);
/*  74 */     TalismanBoxlibBean basicLib = (TalismanBoxlibBean)JsonTableService.getJsonData(talismanBoxBean.getBasicLib(), TalismanBoxlibBean.class);
/*  75 */     TalismanBoxlibBean trumpLib = (TalismanBoxlibBean)JsonTableService.getJsonData(talismanBoxBean.getTrumpLib(), TalismanBoxlibBean.class);
/*     */     
/*  77 */     ArrayList<Reward> rewardList = new ArrayList<>();
/*  78 */     boolean forceRefresh = false;
/*  79 */     for (IntIntValue kv : ((TalismanDrawResponse)this.response).itemList) {
/*  80 */       if (kv.value == talismanBoxBean.getTrumpLib()) {
/*  81 */         rewardList.addAll(FinanceUtil.transform(((TalismanBoxlibBean.IdBean)trumpLib.getId().get(Integer.valueOf(kv.key))).getRaffleReward()));
/*  82 */         if (((TalismanBoxlibBean.IdBean)trumpLib.getId().get(Integer.valueOf(kv.key))).getIsShow() == 1) {
/*  83 */           for (RewardBean rewardBean : ((TalismanBoxlibBean.IdBean)trumpLib.getId().get(Integer.valueOf(kv.key))).getRaffleReward()) {
/*  84 */             PlayerUtil.sendNotice(2901, playerSession.getPlayer(), rewardBean.getId(), rewardBean.getNum() + "");
/*     */           }
/*     */         }
/*  87 */         if (((TalismanBoxlibBean.IdBean)trumpLib.getId().get(Integer.valueOf(kv.key))).getIsFresh() == 1)
/*  88 */           forceRefresh = true;  continue;
/*     */       } 
/*  90 */       if (kv.value == talismanBoxBean.getBasicLib()) {
/*  91 */         rewardList.addAll(FinanceUtil.transform(((TalismanBoxlibBean.IdBean)basicLib.getId().get(Integer.valueOf(kv.key))).getRaffleReward()));
/*  92 */         if (((TalismanBoxlibBean.IdBean)basicLib.getId().get(Integer.valueOf(kv.key))).getIsShow() == 1) {
/*  93 */           for (RewardBean rewardBean : ((TalismanBoxlibBean.IdBean)basicLib.getId().get(Integer.valueOf(kv.key))).getRaffleReward()) {
/*  94 */             PlayerUtil.sendNotice(2901, playerSession.getPlayer(), rewardBean.getId(), rewardBean.getNum() + "");
/*     */           }
/*     */         }
/*     */       } 
/*     */     } 
/*  99 */     if (forceRefresh) {
/* 100 */       ArrayList<IntIntValue> refreshList = TalismanUtil.refreshItems(request.actId);
/* 101 */       talismanEntity.setItems(refreshList);
/* 102 */       ((TalismanDrawResponse)this.response).list = refreshList;
/*     */     } 
/* 104 */     if (rewardList.size() > 0) {
/* 105 */       FinanceUtil.reward(rewardList, playerSession.getPlayer(), ResourceEvent.TalismanDraw, false);
/* 106 */       talismanComponent.getRewardMap().put(Integer.valueOf(TalismanComponent.TYPE_TALISMAN_KEY), rewardList);
/*     */     } 
/* 108 */     TaskComponent taskComponent = (TaskComponent)playerSession.getPlayer().createIfNotExist(TaskComponent.class);
/* 109 */     RuneComponent runeComponent = (RuneComponent)playerSession.getPlayer().createIfNotExist(RuneComponent.class);
/* 110 */     runeComponent.setTalismanTurn(runeComponent.getTalismanTurn() + drawTimes);
/* 111 */     if (null != taskComponent) {
/* 112 */       taskComponent.refreshSchedule(TaskType.TalismanTurn, 0, drawTimes);
/* 113 */       taskComponent.refreshSchedule(TaskType.TotalTalismanTurn, 0, 0L);
/*     */     } 
/* 115 */     ((TalismanDrawResponse)this.response).times = talismanBoxBean.getHitNum() + talismanEntity.getHitTimes() - talismanEntity.getTotalTimes();
/* 116 */     ((TalismanDrawResponse)this.response).freeTimes = (byte)talismanEntity.getFreeTimes();
/* 117 */     return 0;
/*     */   }
/*     */   
/*     */   private ArrayList<IntIntValue> drawManyTimes(TalismanEntity talismanEntity, TalismanBoxBean talismanBoxBean, int times) {
/* 121 */     TalismanBoxlibBean basicLib = (TalismanBoxlibBean)JsonTableService.getJsonData(talismanBoxBean.getBasicLib(), TalismanBoxlibBean.class);
/* 122 */     TalismanBoxlibBean trumpLib = (TalismanBoxlibBean)JsonTableService.getJsonData(talismanBoxBean.getTrumpLib(), TalismanBoxlibBean.class);
/* 123 */     int probTotal = 0;
/* 124 */     ArrayList<IntIntValue> resultList = new ArrayList<>();
/* 125 */     ArrayList<Integer> probList = new ArrayList<>();
/* 126 */     ArrayList<Integer> trumpList = new ArrayList<>();
/* 127 */     for (IntIntValue kv : talismanEntity.getItems()) {
/* 128 */       if (kv.value == talismanBoxBean.getBasicLib()) {
/* 129 */         probTotal += ((TalismanBoxlibBean.IdBean)basicLib.getId().get(Integer.valueOf(kv.key))).getPro();
/*     */       } else {
/* 131 */         probTotal += ((TalismanBoxlibBean.IdBean)trumpLib.getId().get(Integer.valueOf(kv.key))).getPro();
/* 132 */         trumpList.add(Integer.valueOf(kv.key));
/*     */       } 
/* 134 */       probList.add(Integer.valueOf(probTotal));
/*     */     } 
/* 136 */     for (int i = 0; i < times; i++) {
/* 137 */       talismanEntity.setTotalTimes(talismanEntity.getTotalTimes() + 1);
/* 138 */       if (talismanEntity.getTotalTimes() - talismanEntity.getHitTimes() == talismanBoxBean.getHitNum()) {
/* 139 */         talismanEntity.setHitTimes(talismanEntity.getTotalTimes());
/* 140 */         IntIntValue intIntValue = new IntIntValue();
/* 141 */         if (trumpList.size() == 1) {
/* 142 */           intIntValue.key = ((Integer)trumpList.get(0)).intValue();
/*     */         } else {
/* 144 */           int index = RandUtil.randNum(trumpList.size() - 1);
/* 145 */           intIntValue.key = ((Integer)trumpList.get(index)).intValue();
/*     */         } 
/* 147 */         intIntValue.value = talismanBoxBean.getTrumpLib();
/* 148 */         resultList.add(intIntValue);
/*     */       } else {
/* 150 */         int randNum = RandUtil.randNum(probTotal);
/* 151 */         int targetIndex = -1;
/* 152 */         for (Iterator<Integer> iterator = probList.iterator(); iterator.hasNext(); ) { int prob = ((Integer)iterator.next()).intValue();
/* 153 */           targetIndex++;
/* 154 */           if (randNum <= prob) {
/*     */             break;
/*     */           } }
/*     */         
/* 158 */         IntIntValue target = talismanEntity.getItems().get(targetIndex);
/* 159 */         if (target.value == talismanBoxBean.getTrumpLib()) {
/* 160 */           talismanEntity.setHitTimes(talismanEntity.getTotalTimes());
/*     */         }
/* 162 */         resultList.add(target);
/*     */       } 
/*     */     } 
/* 165 */     return resultList;
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\talisman\TalismanDrawProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */