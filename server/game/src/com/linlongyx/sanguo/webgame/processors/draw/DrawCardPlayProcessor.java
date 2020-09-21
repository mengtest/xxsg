/*     */ package com.linlongyx.sanguo.webgame.processors.draw;
/*     */ 
/*     */ import com.linlongyx.core.framework.base.ProcessorBase;
/*     */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*     */ import com.linlongyx.core.framework.protocol.RequestBase;
/*     */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*     */ import com.linlongyx.core.utils.TimeUtil;
/*     */ import com.linlongyx.sanguo.webgame.app.draw.DrawCardComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.draw.DrawCardEntity;
/*     */ import com.linlongyx.sanguo.webgame.common.util.FinanceUtil;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.DrawBean;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.DrawRaffleBean;
/*     */ import com.linlongyx.sanguo.webgame.config.parameter.DrawCardParameter;
/*     */ import com.linlongyx.sanguo.webgame.constant.CurrencyType;
/*     */ import com.linlongyx.sanguo.webgame.constant.ParameterConstant;
/*     */ import com.linlongyx.sanguo.webgame.constant.ResourceEvent;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.draw.DrawCardPlayRequest;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.draw.DrawCardPlayResponse;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.DrawCardData;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.KeyValue;
/*     */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class DrawCardPlayProcessor
/*     */   extends ProcessorBase<DrawCardPlayRequest, DrawCardPlayResponse>
/*     */ {
/*     */   protected void initResponse() {
/*  32 */     this.response = (ResponseBase)new DrawCardPlayResponse();
/*     */   }
/*     */ 
/*     */   
/*     */   protected short handleRequest(IPlayerSession playerSession, DrawCardPlayRequest request) {
/*  37 */     int drawId = request.drawId;
/*  38 */     int type = request.type;
/*  39 */     int pos = request.pos;
/*  40 */     DrawCardParameter drawCardParameter = (DrawCardParameter)ParameterConstant.getParameter(48);
/*  41 */     if (!drawCardParameter.isActOpen(drawId)) {
/*  42 */       return 11801;
/*     */     }
/*  44 */     DrawBean drawBean = (DrawBean)JsonTableService.getJsonData(drawId, DrawBean.class);
/*  45 */     if (null == drawBean) {
/*  46 */       return 11802;
/*     */     }
/*  48 */     DrawRaffleBean drawRaffleBean = (DrawRaffleBean)JsonTableService.getJsonData(drawBean.getRaffle(), DrawRaffleBean.class);
/*  49 */     if (null == drawRaffleBean) {
/*  50 */       return 11802;
/*     */     }
/*  52 */     Map<Integer, DrawRaffleBean.RoundBean> rounds = drawRaffleBean.getRound();
/*  53 */     DrawCardComponent drawCardComponent = (DrawCardComponent)playerSession.getPlayer().createIfNotExist(DrawCardComponent.class);
/*  54 */     DrawCardEntity drawCardEntity = drawCardComponent.getEntity(drawId);
/*  55 */     int round = drawCardEntity.getRound();
/*  56 */     DrawRaffleBean.RoundBean roundBean = rounds.get(Integer.valueOf(round));
/*  57 */     if (null == roundBean) {
/*  58 */       if (round > 0 && round >= rounds.size()) {
/*  59 */         roundBean = rounds.get(Integer.valueOf(rounds.size() - 1));
/*     */       } else {
/*  61 */         return 11802;
/*     */       } 
/*     */     }
/*  64 */     Map<Integer, Integer> openCards = drawCardEntity.getOpenCards();
/*  65 */     if (openCards.size() == 6) {
/*  66 */       return 11805;
/*     */     }
/*  68 */     Map<Integer, KeyValue> awards = drawCardEntity.getAwards();
/*  69 */     int freeTime = drawCardEntity.getFreeTime();
/*  70 */     int totalPlay = drawCardEntity.getTotalPlay();
/*     */     
/*  72 */     Set<Integer> numSet = drawCardEntity.getNumSet();
/*     */     
/*  74 */     if (1 == type) {
/*  75 */       if (openCards.containsKey(Integer.valueOf(pos))) {
/*  76 */         return 11806;
/*     */       }
/*  78 */       if (freeTime < TimeUtil.currentTime()) {
/*  79 */         drawCardEntity.setFreeTime(TimeUtil.currentTime() + drawCardParameter.getFreeTime() * 60 * 60);
/*     */       } else {
/*  81 */         int cost = drawCardParameter.getOpenCost(1);
/*  82 */         if (!FinanceUtil.checkCurrency(playerSession.getPlayer(), CurrencyType.CCY, cost)) {
/*  83 */           return 10052;
/*     */         }
/*  85 */         FinanceUtil.decCurrency(playerSession.getPlayer(), CurrencyType.CCY, cost, ResourceEvent.DrawCardCost);
/*     */       } 
/*  87 */       KeyValue keyValue = awards.get(Integer.valueOf(pos));
/*  88 */       int id = (int)keyValue.key;
/*  89 */       boolean isRecord = !DrawCardUtil.isNormalCard(keyValue);
/*  90 */       if (DrawCardUtil.isNumCard(keyValue)) {
/*  91 */         numSet.add(Integer.valueOf((int)keyValue.value));
/*  92 */         drawCardEntity.setNumSet(numSet);
/*  93 */         drawCardComponent.updateNumSetDB(drawId);
/*     */       } 
/*  95 */       openCards.put(Integer.valueOf(pos), Integer.valueOf(id));
/*  96 */       drawCardEntity.setOpenCards(openCards);
/*  97 */       drawCardComponent.updateOpenCardsDB(drawId);
/*  98 */       drawCardEntity.setTotalPlay(totalPlay + 1);
/*  99 */       drawCardEntity.setRound(drawCardEntity.getRound() + 1);
/* 100 */       drawCardComponent.updateTotalPlayDB(drawId);
/*     */       
/* 102 */       DrawCardData drawCardData = new DrawCardData();
/* 103 */       drawCardData.pos = pos;
/* 104 */       drawCardData.warehouseId = drawCardParameter.getWarehouseId(id);
/* 105 */       drawCardData.id = id;
/* 106 */       ((DrawCardPlayResponse)this.response).addList.add(drawCardData);
/* 107 */       DrawCardUtil.sendRewards(id, playerSession);
/* 108 */       if (isRecord) {
/* 109 */         DrawCardUtil.addRecord(id, playerSession.getPlayer().getPlayerName(), drawId);
/*     */       }
/* 111 */     } else if (2 == type) {
/* 112 */       int cost = drawCardParameter.getOpenCost(6 - openCards.size());
/* 113 */       if (!FinanceUtil.checkCurrency(playerSession.getPlayer(), CurrencyType.CCY, cost)) {
/* 114 */         return 10052;
/*     */       }
/* 116 */       FinanceUtil.decCurrency(playerSession.getPlayer(), CurrencyType.CCY, cost, ResourceEvent.DrawCardCost);
/*     */       
/* 118 */       for (int i = 1; i <= 6; i++) {
/* 119 */         if (!openCards.containsKey(Integer.valueOf(i))) {
/* 120 */           KeyValue keyValue = awards.get(Integer.valueOf(i));
/* 121 */           int id = (int)keyValue.key;
/* 122 */           boolean isRecord = !DrawCardUtil.isNormalCard(keyValue);
/* 123 */           if (DrawCardUtil.isNumCard(keyValue)) {
/* 124 */             numSet.add(Integer.valueOf((int)keyValue.value));
/* 125 */             drawCardEntity.setNumSet(numSet);
/* 126 */             drawCardComponent.updateNumSetDB(drawId);
/*     */           } 
/* 128 */           openCards.put(Integer.valueOf(i), Integer.valueOf(id));
/* 129 */           DrawCardData drawCardData = new DrawCardData();
/* 130 */           drawCardData.pos = i;
/* 131 */           drawCardData.warehouseId = drawCardParameter.getWarehouseId(id);
/* 132 */           drawCardData.id = id;
/* 133 */           ((DrawCardPlayResponse)this.response).addList.add(drawCardData);
/* 134 */           totalPlay++;
/* 135 */           drawCardEntity.setRound(drawCardEntity.getRound() + 1);
/* 136 */           DrawCardUtil.sendRewards(id, playerSession);
/* 137 */           if (isRecord) {
/* 138 */             DrawCardUtil.addRecord(id, playerSession.getPlayer().getPlayerName(), drawId);
/*     */           }
/*     */         } 
/*     */       } 
/* 142 */       drawCardEntity.setOpenCards(openCards);
/* 143 */       drawCardComponent.updateOpenCardsDB(drawId);
/* 144 */       drawCardEntity.setTotalPlay(totalPlay);
/* 145 */       drawCardComponent.updateTotalPlayDB(drawId);
/*     */     } 
/* 147 */     ((DrawCardPlayResponse)this.response).nextFreeTime = drawCardEntity.getFreeTime();
/* 148 */     ((DrawCardPlayResponse)this.response).drawId = drawId;
/* 149 */     return 0;
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\draw\DrawCardPlayProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */