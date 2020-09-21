/*     */ package com.linlongyx.sanguo.webgame.processors.rebate;
/*     */ 
/*     */ import com.linlongyx.core.framework.logic.IPlayer;
/*     */ import com.linlongyx.core.utils.TimeUtil;
/*     */ import com.linlongyx.sanguo.webgame.app.rebate.RechargeRebateComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.rebate.RechargeRebateEntity;
/*     */ import com.linlongyx.sanguo.webgame.common.util.FinanceUtil;
/*     */ import com.linlongyx.sanguo.webgame.common.util.RandUtil;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.RechargeTurntablerewardBean;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.RewardBean;
/*     */ import com.linlongyx.sanguo.webgame.config.parameter.ChargeRebateParameter;
/*     */ import com.linlongyx.sanguo.webgame.constant.ParameterConstant;
/*     */ import com.linlongyx.sanguo.webgame.constant.RedNoticeConstant;
/*     */ import com.linlongyx.sanguo.webgame.constant.TaskType;
/*     */ import com.linlongyx.sanguo.webgame.processors.player.PlayerUtil;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.KeyValue;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.Reward;
/*     */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*     */ import com.linlongyx.sanguo.webgame.service.LookUpService;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.Iterator;
/*     */ import java.util.LinkedList;
/*     */ import java.util.List;
/*     */ import java.util.concurrent.locks.Lock;
/*     */ import java.util.concurrent.locks.ReentrantLock;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ChargeRebateUtil
/*     */ {
/*  34 */   private static LinkedList<KeyValue> recordList = new LinkedList<>();
/*     */   
/*  36 */   private static int MAX_RECORD_SIZE = 0;
/*  37 */   private static final Lock lock = new ReentrantLock();
/*     */ 
/*     */   
/*     */   private static final int TYPE_INTERFACE_NOTICE = 1;
/*     */ 
/*     */   
/*     */   private static final int TYPE_INTERFACE_WORLD_NOTICE = 2;
/*     */ 
/*     */ 
/*     */   
/*     */   public static void addRecord(KeyValue record) {
/*  48 */     if (MAX_RECORD_SIZE == 0) {
/*  49 */       ChargeRebateParameter chargeRebateParameter = (ChargeRebateParameter)ParameterConstant.getParameter(49);
/*  50 */       MAX_RECORD_SIZE = chargeRebateParameter.getServiceRecord();
/*     */     } 
/*     */     try {
/*  53 */       lock.lock();
/*  54 */       recordList.addLast(record);
/*  55 */       int extra = recordList.size() - MAX_RECORD_SIZE;
/*  56 */       while (extra > 0) {
/*  57 */         recordList.removeFirst();
/*  58 */         extra--;
/*     */       } 
/*     */     } finally {
/*  61 */       lock.unlock();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static ArrayList<KeyValue> getRecordList() {
/*     */     try {
/*  72 */       lock.lock();
/*  73 */       return new ArrayList<>(recordList);
/*     */     } finally {
/*  75 */       lock.unlock();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void turnTime(IPlayer player, ChargeRebateParameter chargeRebateParameter, RechargeRebateEntity rechargeRebateEntity, ArrayList<KeyValue> timeArray, ArrayList<Reward> rewardList, ArrayList<KeyValue> keyValues, RechargeRebateComponent rechargeRebateComponent, int turnTimes, int raffle) {
/*  87 */     for (int i = 0; i < turnTimes; i++) {
/*  88 */       rechargeRebateEntity.setTimes(rechargeRebateEntity.getTimes() + 1);
/*  89 */       int second = Integer.parseInt(((KeyValue)timeArray.get(0)).valueStr);
/*     */       
/*  91 */       for (KeyValue keyValue1 : timeArray) {
/*  92 */         if (keyValue1.key <= rechargeRebateEntity.getTimes() && rechargeRebateEntity.getTimes() <= keyValue1.value) {
/*  93 */           second = Integer.parseInt(keyValue1.valueStr);
/*     */           break;
/*     */         } 
/*     */       } 
/*  97 */       int randNum = RandUtil.randNum(0, 1);
/*     */       
/*  99 */       int weight1 = ((Integer)chargeRebateParameter.getRaffleWeightMap().getOrDefault(Integer.valueOf(raffle), Integer.valueOf(0))).intValue();
/* 100 */       int weight2 = ((Integer)chargeRebateParameter.getRaffleWeightMap().getOrDefault(Integer.valueOf(second), Integer.valueOf(0))).intValue();
/* 101 */       int totalWeight = weight1 + weight2;
/*     */       
/* 103 */       int rand = RandUtil.randNum(totalWeight);
/* 104 */       RechargeTurntablerewardBean rechargeTurntablerewardBean = (RechargeTurntablerewardBean)JsonTableService.getJsonData(raffle, RechargeTurntablerewardBean.class);
/* 105 */       RechargeTurntablerewardBean rechargeTurntablerewardBean2 = (RechargeTurntablerewardBean)JsonTableService.getJsonData(second, RechargeTurntablerewardBean.class);
/* 106 */       ArrayList<Integer> arrayList = new ArrayList<>(rechargeTurntablerewardBean.getRewardId().keySet());
/* 107 */       Collections.shuffle(arrayList);
/* 108 */       int weight = 0;
/* 109 */       RechargeTurntablerewardBean.RewardIdBean rewardIdBean = null;
/* 110 */       int reward = 0;
/* 111 */       int key = 0; Iterator<Integer> iterator;
/* 112 */       for (iterator = arrayList.iterator(); iterator.hasNext(); ) { int rewardId = ((Integer)iterator.next()).intValue();
/* 113 */         rewardIdBean = (RechargeTurntablerewardBean.RewardIdBean)rechargeTurntablerewardBean.getRewardId().get(Integer.valueOf(rewardId));
/* 114 */         weight += rewardIdBean.getProbability();
/* 115 */         if (weight >= rand) {
/* 116 */           reward = rewardId;
/* 117 */           key = raffle;
/*     */           break;
/*     */         }  }
/*     */       
/* 121 */       if (reward == 0) {
/* 122 */         for (iterator = rechargeTurntablerewardBean2.getRewardId().keySet().iterator(); iterator.hasNext(); ) { int rewardId = ((Integer)iterator.next()).intValue();
/* 123 */           rewardIdBean = (RechargeTurntablerewardBean.RewardIdBean)rechargeTurntablerewardBean2.getRewardId().get(Integer.valueOf(rewardId));
/* 124 */           weight += rewardIdBean.getProbability();
/* 125 */           if (weight >= rand) {
/* 126 */             reward = rewardId;
/* 127 */             key = second;
/*     */             break;
/*     */           }  }
/*     */       
/*     */       }
/* 132 */       rewardList.addAll(FinanceUtil.transform(rewardIdBean.getReward()));
/* 133 */       KeyValue keyValue = new KeyValue();
/* 134 */       keyValue.key = key;
/* 135 */       keyValue.value = reward;
/* 136 */       keyValues.add(keyValue);
/*     */       
/* 138 */       if (rewardIdBean.getNotice() == 1) {
/* 139 */         for (RewardBean rewardBean : rewardIdBean.getReward()) {
/* 140 */           KeyValue record = new KeyValue();
/* 141 */           record.valueStr = player.getPlayerName();
/* 142 */           record.key = rewardBean.getId();
/* 143 */           record.value = rewardBean.getNum();
/* 144 */           addRecord(record);
/*     */         } 
/* 146 */       } else if (rewardIdBean.getNotice() == 2) {
/* 147 */         for (RewardBean rewardBean : rewardIdBean.getReward()) {
/* 148 */           KeyValue record = new KeyValue();
/* 149 */           record.valueStr = player.getPlayerName();
/* 150 */           record.key = rewardBean.getId();
/* 151 */           record.value = rewardBean.getNum();
/* 152 */           addRecord(record);
/* 153 */           PlayerUtil.sendNotice(10, player, (int)rewardBean.getNum(), null);
/*     */         } 
/*     */       } 
/* 156 */       for (RewardBean rewardBean : rewardIdBean.getReward()) {
/*     */         
/* 158 */         KeyValue record2 = new KeyValue();
/* 159 */         record2.valueStr = rechargeRebateEntity.getTimes() + "," + TimeUtil.currentTime();
/* 160 */         record2.key = rewardBean.getId();
/* 161 */         record2.value = rewardBean.getNum();
/* 162 */         rechargeRebateComponent.addRecords(record2);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void addChargeValue(int type, long value, long playerId) {
/* 171 */     if (type == TaskType.Charge.getType()) {
/* 172 */       RechargeRebateComponent rechargeRebateComponent = (RechargeRebateComponent)LookUpService.getComponent(playerId, RechargeRebateComponent.class);
/* 173 */       ChargeRebateParameter chargeRebateParameter = (ChargeRebateParameter)ParameterConstant.getParameter(49);
/* 174 */       List<Integer> list = chargeRebateParameter.getActId(true);
/* 175 */       if (list.isEmpty()) {
/*     */         return;
/*     */       }
/* 178 */       for (Iterator<Integer> iterator = list.iterator(); iterator.hasNext(); ) { int id = ((Integer)iterator.next()).intValue();
/* 179 */         RechargeRebateEntity rechargeRebateEntity = rechargeRebateComponent.getEntity(id);
/* 180 */         rechargeRebateEntity.setCharge(rechargeRebateEntity.getCharge() + value);
/* 181 */         rechargeRebateEntity.setRefChare(rechargeRebateEntity.getRefChare() + value);
/* 182 */         rechargeRebateComponent.updateRefChareDB(rechargeRebateEntity.getId());
/* 183 */         rechargeRebateComponent.updateChargeDB(rechargeRebateEntity.getId());
/* 184 */         if (rechargeRebateEntity.getRefChare() >= chargeRebateParameter.getOneCost())
/* 185 */           PlayerUtil.sendRedNotice(Long.valueOf(playerId), RedNoticeConstant.ChargeRebate.getSys(), id);  }
/*     */     
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\rebate\ChargeRebateUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */