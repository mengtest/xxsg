/*     */ package com.linlongyx.sanguo.webgame.processors.turnplate;
/*     */ 
/*     */ import com.linlongyx.core.framework.base.ProcessorBase;
/*     */ import com.linlongyx.core.framework.logic.IPlayer;
/*     */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*     */ import com.linlongyx.core.framework.protocol.RequestBase;
/*     */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*     */ import com.linlongyx.sanguo.webgame.app.turnplate.TurnplateComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.turnplate.TurnplateEntity;
/*     */ import com.linlongyx.sanguo.webgame.common.util.FinanceUtil;
/*     */ import com.linlongyx.sanguo.webgame.common.util.RandUtil;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.RewardBean;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.TurnplateBean;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.TurnplateRaffleBean;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.TurnplateWarehouseBean;
/*     */ import com.linlongyx.sanguo.webgame.config.parameter.TurnplateParameter;
/*     */ import com.linlongyx.sanguo.webgame.constant.CurrencyType;
/*     */ import com.linlongyx.sanguo.webgame.constant.FunctionOpenConstant;
/*     */ import com.linlongyx.sanguo.webgame.constant.KeyValueConstant;
/*     */ import com.linlongyx.sanguo.webgame.constant.ParameterConstant;
/*     */ import com.linlongyx.sanguo.webgame.constant.ResourceEvent;
/*     */ import com.linlongyx.sanguo.webgame.log.LogUtil;
/*     */ import com.linlongyx.sanguo.webgame.processors.player.PlayerUtil;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.Reward;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.TurnplateRecord;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.turnplate.TurnplateDrawRequest;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.turnplate.TurnplateDrawResponse;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.turnplate.TurnplateNoticeResponse;
/*     */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class TurnplateDrawProcessor extends ProcessorBase<TurnplateDrawRequest, TurnplateDrawResponse> {
/*     */   protected void initResponse() {
/*  36 */     this.response = (ResponseBase)new TurnplateDrawResponse();
/*     */   }
/*     */   
/*     */   protected short handleRequest(IPlayerSession playerSession, TurnplateDrawRequest request) {
/*     */     int gold;
/*  41 */     if (!FunctionOpenConstant.isFunctionOpen(playerSession, 24)) {
/*  42 */       return 10061;
/*     */     }
/*  44 */     TurnplateComponent turnplateComponent = (TurnplateComponent)playerSession.getPlayer().createIfNotExist(TurnplateComponent.class);
/*  45 */     if (turnplateComponent.getCurActId() == 0) {
/*  46 */       return 12402;
/*     */     }
/*  48 */     TurnplateParameter turnplateParameter = (TurnplateParameter)ParameterConstant.getParameter(24);
/*  49 */     TurnplateEntity turnplateEntity = turnplateComponent.getEntity(turnplateComponent.getCurActId());
/*  50 */     TurnplateBean turnplateBean = (TurnplateBean)JsonTableService.getJsonData(turnplateComponent.getCurActId(), TurnplateBean.class);
/*     */     
/*  52 */     ArrayList<Reward> rewardList = new ArrayList<>();
/*  53 */     ArrayList<TurnplateRecord> turnplateRecords = new ArrayList<>();
/*  54 */     ArrayList<Integer> sortList = new ArrayList<>();
/*     */     
/*  56 */     if (request.type == 1) {
/*  57 */       int drawCount = 1;
/*  58 */       gold = (turnplateParameter.getGold2Point()).key;
/*  59 */       int point = (turnplateParameter.getGold2Point()).value;
/*  60 */       if (turnplateEntity.getDrawCount() > 0) {
/*  61 */         if (!FinanceUtil.checkCurrency(playerSession.getPlayer(), CurrencyType.CCY, gold)) {
/*  62 */           return 10052;
/*     */         }
/*  64 */         FinanceUtil.decCurrency(playerSession.getPlayer(), CurrencyType.CCY, gold, ResourceEvent.Turnplate);
/*     */       } 
/*  66 */       turnplateEntity.setPoint(turnplateEntity.getPoint() + point);
/*  67 */       turnplateEntity.setDrawCount(turnplateEntity.getDrawCount() + drawCount);
/*     */       
/*  69 */       PlayerUtil.updateKeyValueInfo(playerSession, KeyValueConstant.TurnplatePoint.getKey(), turnplateEntity.getPoint());
/*     */       
/*  71 */       TurnplateRaffleBean turnplateRaffleBean = (TurnplateRaffleBean)JsonTableService.getJsonData(3, TurnplateRaffleBean.class);
/*  72 */       TurnplateRaffleBean.TimesBean timesBean = (TurnplateRaffleBean.TimesBean)turnplateRaffleBean.getTimes().get(Integer.valueOf(1));
/*  73 */       drawOneTime(turnplateBean.getRaffle(), playerSession.getPlayer(), turnplateEntity, timesBean, rewardList, sortList, turnplateRecords);
/*  74 */     } else if (request.type == 2) {
/*  75 */       int drawCount = 10;
/*  76 */       gold = turnplateParameter.getDiscount();
/*  77 */       int point = gold / (turnplateParameter.getGold2Point()).key * (turnplateParameter.getGold2Point()).value;
/*     */       
/*  79 */       if (!FinanceUtil.checkCurrency(playerSession.getPlayer(), CurrencyType.CCY, gold)) {
/*  80 */         return 10052;
/*     */       }
/*  82 */       FinanceUtil.decCurrency(playerSession.getPlayer(), CurrencyType.CCY, gold, ResourceEvent.Turnplate);
/*  83 */       int oldTenDrawCount = turnplateEntity.getTenDrawCount();
/*  84 */       turnplateEntity.setPoint(turnplateEntity.getPoint() + point);
/*  85 */       turnplateEntity.setDrawCount(turnplateEntity.getDrawCount() + drawCount);
/*  86 */       turnplateEntity.setTenDrawCount(turnplateEntity.getTenDrawCount() + 1);
/*     */       
/*  88 */       PlayerUtil.updateKeyValueInfo(playerSession, KeyValueConstant.TurnplatePoint.getKey(), turnplateEntity.getPoint());
/*     */       
/*  90 */       if (oldTenDrawCount >= turnplateParameter.getMaxTenDraw()) {
/*  91 */         TurnplateRaffleBean turnplateRaffleBean = (TurnplateRaffleBean)JsonTableService.getJsonData(2, TurnplateRaffleBean.class);
/*  92 */         TurnplateRaffleBean.TimesBean timesBean = (TurnplateRaffleBean.TimesBean)turnplateRaffleBean.getTimes().get(Integer.valueOf(1));
/*     */         
/*  94 */         drawManyTimes(drawCount, turnplateBean.getRaffle(), playerSession.getPlayer(), turnplateEntity, timesBean, rewardList, sortList, turnplateRecords);
/*     */       } else {
/*  96 */         boolean mustRare = false;
/*  97 */         for (TurnplateBean.RareTimesBean rareTimesBean : turnplateBean.getRareTimes()) {
/*  98 */           if (rareTimesBean.getMin() <= turnplateEntity.getTenDrawCount() && turnplateEntity.getTenDrawCount() <= rareTimesBean.getMax()) {
/*  99 */             if (!turnplateEntity.getRareRecord().containsKey(Integer.valueOf(rareTimesBean.getMin()))) {
/* 100 */               turnplateEntity.getRareRecord().put(Integer.valueOf(rareTimesBean.getMin()), Integer.valueOf(RandUtil.randNum(rareTimesBean.getMin(), rareTimesBean.getMax())));
/*     */             }
/* 102 */             if (((Integer)turnplateEntity.getRareRecord().get(Integer.valueOf(rareTimesBean.getMin()))).intValue() == turnplateEntity.getTenDrawCount()) {
/* 103 */               mustRare = true;
/*     */             }
/*     */             break;
/*     */           } 
/*     */         } 
/* 108 */         if (mustRare) {
/* 109 */           TurnplateRaffleBean turnplateRaffleBean = (TurnplateRaffleBean)JsonTableService.getJsonData(1, TurnplateRaffleBean.class);
/* 110 */           TurnplateRaffleBean.TimesBean timesBean = (TurnplateRaffleBean.TimesBean)turnplateRaffleBean.getTimes().get(Integer.valueOf(turnplateEntity.getTenDrawCount()));
/*     */ 
/*     */           
/* 113 */           int tick = 0;
/* 114 */           boolean isLimit = drawHouse(playerSession.getPlayer(), turnplateEntity, rewardList, sortList, turnplateRecords, timesBean.getRareId());
/* 115 */           int times = isLimit ? 1 : 0;
/* 116 */           int maxTimes = (drawCount - times) * 20;
/* 117 */           while (times < drawCount && tick < maxTimes) {
/* 118 */             tick++;
/* 119 */             isLimit = drawHouse(playerSession.getPlayer(), turnplateEntity, rewardList, sortList, turnplateRecords, turnplateBean.getRaffle());
/* 120 */             if (isLimit) {
/* 121 */               times++;
/*     */             }
/*     */           } 
/*     */         } else {
/* 125 */           TurnplateRaffleBean turnplateRaffleBean = (TurnplateRaffleBean)JsonTableService.getJsonData(4, TurnplateRaffleBean.class);
/* 126 */           TurnplateRaffleBean.TimesBean timesBean = (TurnplateRaffleBean.TimesBean)turnplateRaffleBean.getTimes().get(Integer.valueOf(1));
/* 127 */           drawManyTimes(drawCount, turnplateBean.getRaffle(), playerSession.getPlayer(), turnplateEntity, timesBean, rewardList, sortList, turnplateRecords);
/*     */         } 
/*     */       } 
/*     */     } else {
/* 131 */       return 12401;
/*     */     } 
/*     */     
/* 134 */     int goldToPool = gold * turnplateParameter.getGoldToPool() / 100;
/* 135 */     TurnplateUtil.addGoldPool(goldToPool);
/*     */     
/* 137 */     if (rewardList.size() > 0) {
/* 138 */       FinanceUtil.reward(rewardList, playerSession.getPlayer(), ResourceEvent.Turnplate, false);
/* 139 */       turnplateComponent.setRewards(rewardList);
/* 140 */       LogUtil.debugLog(new Object[] { Long.valueOf(playerSession.getPlayer().getPlayerId()), "turnplate draw result : ", Arrays.toString(rewardList.toArray()) });
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 146 */     if (turnplateRecords.size() > 0) {
/* 147 */       int goldGetTotal = 0;
/* 148 */       for (TurnplateRecord turnplateRecord : turnplateRecords) {
/* 149 */         if (turnplateRecord.itemId == -1) {
/* 150 */           goldGetTotal += turnplateRecord.num;
/* 151 */           PlayerUtil.sendNotice(24, playerSession.getPlayer(), turnplateRecord.num, null);
/*     */         } 
/*     */       } 
/* 154 */       if (goldGetTotal > 0) {
/* 155 */         FinanceUtil.addCurrency(playerSession.getPlayer(), CurrencyType.CCY, goldGetTotal, ResourceEvent.Turnplate);
/* 156 */         TurnplateUtil.addGoldPool(-goldGetTotal);
/*     */       } 
/* 158 */       TurnplateNoticeResponse turnplateNoticeResponse = new TurnplateNoticeResponse();
/* 159 */       turnplateNoticeResponse.recordList = turnplateRecords;
/* 160 */       TurnplateUtil.sendTurnplateNotice((ResponseBase)turnplateNoticeResponse);
/*     */     } 
/* 162 */     ((TurnplateDrawResponse)this.response).rewardList = sortList;
/* 163 */     ((TurnplateDrawResponse)this.response).point = turnplateEntity.getPoint();
/* 164 */     ((TurnplateDrawResponse)this.response).goldPool = TurnplateUtil.getGoldPool();
/*     */     
/* 166 */     return 0;
/*     */   }
/*     */   
/*     */   private void drawOneTime(int wareHouseId, IPlayer player, TurnplateEntity turnplateEntity, TurnplateRaffleBean.TimesBean timesBean, ArrayList<Reward> rewardList, ArrayList<Integer> sortList, ArrayList<TurnplateRecord> turnplateRecords) {
/* 170 */     int houseId = (RandUtil.randNum(100) <= timesBean.getRarePer()) ? timesBean.getRareId() : wareHouseId;
/*     */ 
/*     */     
/* 173 */     int count = 0;
/* 174 */     boolean isLimit = drawHouse(player, turnplateEntity, rewardList, sortList, turnplateRecords, houseId);
/* 175 */     if (!isLimit) {
/*     */       do {
/* 177 */         count++;
/* 178 */         isLimit = drawHouse(player, turnplateEntity, rewardList, sortList, turnplateRecords, wareHouseId);
/* 179 */       } while (!isLimit && count < 20);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private void drawManyTimes(int times, int wareHouseId, IPlayer player, TurnplateEntity turnplateEntity, TurnplateRaffleBean.TimesBean timesBean, ArrayList<Reward> rewardList, ArrayList<Integer> sortList, ArrayList<TurnplateRecord> turnplateRecords) {
/* 185 */     int count = 0, tick = 0;
/* 186 */     while (count < times && tick < 20 * times) {
/* 187 */       tick++;
/* 188 */       int houseId = (RandUtil.randNum(100) <= timesBean.getRarePer()) ? timesBean.getRareId() : wareHouseId;
/* 189 */       boolean isLimit = drawHouse(player, turnplateEntity, rewardList, sortList, turnplateRecords, houseId);
/* 190 */       if (isLimit) {
/* 191 */         count++;
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   private boolean drawHouse(IPlayer player, TurnplateEntity turnplateEntity, ArrayList<Reward> rewardList, ArrayList<Integer> sortList, ArrayList<TurnplateRecord> turnplateRecords, int houseId) {
/* 197 */     TurnplateWarehouseBean turnplateWarehouseBean = (TurnplateWarehouseBean)JsonTableService.getJsonData(houseId, TurnplateWarehouseBean.class);
/* 198 */     TurnplateParameter turnplateParameter = (TurnplateParameter)ParameterConstant.getParameter(24);
/* 199 */     TurnplateWarehouseBean.IdBean target = null;
/* 200 */     int randNum = RandUtil.randNum();
/* 201 */     int tmp = 0, targetId = 0;
/* 202 */     for (Map.Entry<Integer, TurnplateWarehouseBean.IdBean> idBean : (Iterable<Map.Entry<Integer, TurnplateWarehouseBean.IdBean>>)turnplateWarehouseBean.getId().entrySet()) {
/* 203 */       tmp += ((TurnplateWarehouseBean.IdBean)idBean.getValue()).getRafflePer();
/* 204 */       if (randNum <= tmp) {
/* 205 */         target = idBean.getValue();
/* 206 */         targetId = ((Integer)idBean.getKey()).intValue();
/*     */         break;
/*     */       } 
/*     */     } 
/* 210 */     if (target != null) {
/* 211 */       if (target.getIsRare() == 1) {
/* 212 */         if (turnplateEntity.getRareCount() >= turnplateParameter.getMaxRareDraw()) {
/* 213 */           return false;
/*     */         }
/* 215 */         TurnplateBean turnplateBean = (TurnplateBean)JsonTableService.getJsonData(turnplateEntity.getActId(), TurnplateBean.class);
/* 216 */         if (turnplateBean.getRareItem().containsKey(Integer.valueOf(targetId))) {
/* 217 */           if (!turnplateEntity.getItemDrawCount().containsKey(Integer.valueOf(targetId))) {
/* 218 */             turnplateEntity.getItemDrawCount().put(Integer.valueOf(targetId), Integer.valueOf(0));
/*     */           }
/* 220 */           int itemCount = ((Integer)turnplateEntity.getItemDrawCount().get(Integer.valueOf(targetId))).intValue();
/* 221 */           int num = ((TurnplateBean.RareItemBean)turnplateBean.getRareItem().get(Integer.valueOf(targetId))).getNum();
/* 222 */           if (num != -1 && itemCount >= num) {
/* 223 */             return false;
/*     */           }
/* 225 */           turnplateEntity.getItemDrawCount().put(Integer.valueOf(targetId), Integer.valueOf(itemCount + 1));
/* 226 */           turnplateEntity.setRareCount(turnplateEntity.getRareCount() + 1);
/*     */         } 
/*     */       } 
/*     */       
/* 230 */       sortList.add(Integer.valueOf(target.getSort()));
/* 231 */       if (target.getRaffleReward().size() > 0) {
/* 232 */         rewardList.addAll(FinanceUtil.transform(target.getRaffleReward()));
/* 233 */         if (target.getIsShow() == 1) {
/* 234 */           for (RewardBean rewardBean : target.getRaffleReward()) {
/* 235 */             TurnplateRecord record = new TurnplateRecord();
/* 236 */             record.playerName = player.getPlayerName();
/* 237 */             record.itemId = rewardBean.getId();
/* 238 */             record.num = (int)rewardBean.getNum();
/* 239 */             turnplateRecords.add(record);
/* 240 */             TurnplateUtil.addRecord(record);
/*     */           } 
/*     */         }
/*     */       } else {
/* 244 */         int curGold = TurnplateUtil.getGoldPool();
/* 245 */         int goldGet = curGold * target.getRewardPro() * (100 - turnplateParameter.getTax()) / 10000;
/*     */         
/* 247 */         if (target.getIsShow() == 1) {
/* 248 */           TurnplateRecord record = new TurnplateRecord();
/* 249 */           record.playerName = player.getPlayerName();
/* 250 */           record.itemId = -1;
/* 251 */           record.num = goldGet;
/* 252 */           turnplateRecords.add(record);
/* 253 */           TurnplateUtil.addRecord(record);
/*     */         } 
/*     */       } 
/*     */     } else {
/* 257 */       LogUtil.errorLog(new Object[] { "turnplate target is null" });
/*     */     } 
/* 259 */     return true;
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\turnplate\TurnplateDrawProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */