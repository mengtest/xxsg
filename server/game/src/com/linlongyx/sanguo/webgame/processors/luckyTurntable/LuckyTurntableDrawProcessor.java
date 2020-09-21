/*     */ package com.linlongyx.sanguo.webgame.processors.luckyTurntable;
/*     */ import com.linlongyx.core.framework.base.ProcessorBase;
/*     */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*     */ import com.linlongyx.core.framework.protocol.RequestBase;
/*     */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*     */ import com.linlongyx.core.utils.TimeUtil;
/*     */ import com.linlongyx.sanguo.webgame.app.bag.BagComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.luckyTurntable.LuckyTurntableComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.luckyTurntable.LuckyTurntableEntity;
/*     */ import com.linlongyx.sanguo.webgame.common.util.FinanceUtil;
/*     */ import com.linlongyx.sanguo.webgame.common.util.RandUtil;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.LuckyTurntableBean;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.LuckyValueBean;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.LuckylistBean;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.RewardBean;
/*     */ import com.linlongyx.sanguo.webgame.config.parameter.LuckyTurntableParameter;
/*     */ import com.linlongyx.sanguo.webgame.constant.CurrencyType;
/*     */ import com.linlongyx.sanguo.webgame.constant.FunctionOpenConstant;
/*     */ import com.linlongyx.sanguo.webgame.constant.MContext;
/*     */ import com.linlongyx.sanguo.webgame.constant.ParameterConstant;
/*     */ import com.linlongyx.sanguo.webgame.constant.ResourceEvent;
/*     */ import com.linlongyx.sanguo.webgame.processors.limit.LimitUtil;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.luckyTurntable.LuckyTurntableDrawRequest;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.luckyTurntable.LuckyTurntableDrawResponse;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.luckyTurntable.LuckyTurntableNoticeResponse;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.LuckyTurntableRecord;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.Reward;
/*     */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Set;
/*     */ 
/*     */ public class LuckyTurntableDrawProcessor extends ProcessorBase<LuckyTurntableDrawRequest, LuckyTurntableDrawResponse> {
/*     */   protected void initResponse() {
/*  34 */     this.response = (ResponseBase)new LuckyTurntableDrawResponse();
/*     */   }
/*     */   
/*     */   protected short handleRequest(IPlayerSession playerSession, LuckyTurntableDrawRequest request) {
/*     */     int drawTimes, totalDrawTimes, yuanbao, luckyPointHigher, coins, toolNum;
/*  39 */     if (!FunctionOpenConstant.isFunctionOpen(playerSession, 43)) {
/*  40 */       return 10061;
/*     */     }
/*  42 */     int type = request.type;
/*  43 */     LuckylistBean luckylistBean = (LuckylistBean)JsonTableService.getJsonData(request.actId, LuckylistBean.class);
/*  44 */     if (luckylistBean == null) {
/*  45 */       return 13502;
/*     */     }
/*     */     
/*  48 */     if (!LimitUtil.isValid(luckylistBean.getServerTypeb(), luckylistBean.getDay()) || !LimitUtil.isActOpen(luckylistBean.getServerTypeb(), luckylistBean
/*  49 */         .getBeginTimeb(), luckylistBean.getEndTimeb())) {
/*  50 */       return 13501;
/*     */     }
/*  52 */     LuckyTurntableComponent luckyTurntableComponent = (LuckyTurntableComponent)playerSession.getPlayer().createIfNotExist(LuckyTurntableComponent.class);
/*  53 */     BagComponent bagComponent = (BagComponent)playerSession.getPlayer().createIfNotExist(BagComponent.class);
/*  54 */     LuckyTurntableEntity luckyTurntableEntity = luckyTurntableComponent.getEntity(request.actId);
/*  55 */     if (luckyTurntableEntity == null) {
/*  56 */       return 13502;
/*     */     }
/*  58 */     if (type != 1 && type != 2) {
/*  59 */       return 13502;
/*     */     }
/*  61 */     boolean inDoubleRateTime = false;
/*  62 */     if (luckylistBean.getServerType() == 0) {
/*  63 */       int disOpenDays = TimeUtil.getDayDiffToOpen(MContext.getOpenTime());
/*  64 */       inDoubleRateTime = (disOpenDays >= Integer.parseInt(luckylistBean.getBeginTime()) && disOpenDays < Integer.parseInt(luckylistBean.getEndTime()));
/*  65 */     } else if (luckylistBean.getServerType() == 1) {
/*  66 */       int curTime = TimeUtil.currentTime();
/*  67 */       inDoubleRateTime = (TimeUtil.getTimeFromDate(luckylistBean.getBeginTime()) <= curTime && curTime <= TimeUtil.getTimeFromDate(luckylistBean.getEndTime()));
/*     */     } 
/*     */     
/*  70 */     LuckyTurntableParameter luckyTurntableParameter = (LuckyTurntableParameter)ParameterConstant.getParameter(35);
/*  71 */     ArrayList<Reward> rewardList = new ArrayList<>();
/*  72 */     ArrayList<Integer> turntableIdList = new ArrayList<>();
/*  73 */     RewardBean costItem = luckylistBean.getItemReward().get(0);
/*  74 */     int luckyPointLower = luckyTurntableEntity.getLuckyPoint(), maxPoint = 0;
/*  75 */     int totalPoint = luckyTurntableEntity.getTotalPoint();
/*  76 */     if (type == 1) {
/*  77 */       totalDrawTimes = drawTimes = 1;
/*  78 */       yuanbao = luckylistBean.getMoney();
/*  79 */       toolNum = (int)costItem.getNum();
/*  80 */       luckyPointHigher = luckyTurntableParameter.getOneDrawLuckyPoint() + luckyPointLower;
/*  81 */       coins = luckyTurntableParameter.getOneDrawCoins();
/*  82 */       totalPoint += luckyTurntableParameter.getOneDrawLuckyPoint();
/*     */     } else {
/*  84 */       totalDrawTimes = drawTimes = 10;
/*  85 */       yuanbao = luckylistBean.getMoney() * drawTimes;
/*  86 */       toolNum = (int)(costItem.getNum() * drawTimes);
/*  87 */       luckyPointHigher = luckyTurntableParameter.getTenDrawLuckyPoint() + luckyPointLower;
/*  88 */       coins = luckyTurntableParameter.getTenDrawCoins();
/*  89 */       totalPoint += luckyTurntableParameter.getTenDrawLuckyPoint();
/*  90 */       if (luckyTurntableEntity.getIsFirstDraw() == 0) {
/*  91 */         if (luckyTurntableEntity.getFreeTimes() < drawTimes && 
/*  92 */           !bagComponent.check(costItem.getId(), toolNum) && !FinanceUtil.checkCurrency(playerSession.getPlayer(), CurrencyType.CCY, yuanbao)) {
/*  93 */           return 10052;
/*     */         }
/*     */         
/*  96 */         LuckyTurntableBean luckyTurntableBean = (LuckyTurntableBean)JsonTableService.getJsonData(luckyTurntableParameter.getFirstDrawReward(), LuckyTurntableBean.class);
/*  97 */         rewardList.addAll(FinanceUtil.transform(luckyTurntableBean.getReward()));
/*  98 */         turntableIdList.add(Integer.valueOf(luckyTurntableParameter.getFirstDrawReward()));
/*  99 */         luckyTurntableEntity.setIsFirstDraw((byte)1);
/* 100 */         drawTimes--;
/*     */       } 
/*     */     } 
/* 103 */     if (luckyTurntableEntity.getFreeTimes() < totalDrawTimes) {
/* 104 */       if (bagComponent.check(costItem.getId(), toolNum)) {
/* 105 */         bagComponent.deleteItem(costItem.getId(), toolNum, ResourceEvent.LuckyTurntable);
/*     */       } else {
/* 107 */         if (!FinanceUtil.checkCurrency(playerSession.getPlayer(), CurrencyType.CCY, yuanbao)) {
/* 108 */           return 10052;
/*     */         }
/* 110 */         FinanceUtil.decCurrency(playerSession.getPlayer(), CurrencyType.CCY, yuanbao, ResourceEvent.LuckyTurntable);
/*     */       } 
/*     */     }
/*     */     
/* 114 */     FinanceUtil.addCurrency(playerSession.getPlayer(), CurrencyType.COIN, coins, ResourceEvent.LuckyTurntable);
/* 115 */     Set<Integer> pointSet = JsonTableService.getJsonTableKey(LuckyValueBean.class);
/* 116 */     for (Integer point : pointSet) {
/* 117 */       if (luckyPointLower < point.intValue() && point.intValue() <= luckyPointHigher) {
/* 118 */         LuckyValueBean luckyValueBean = (LuckyValueBean)JsonTableService.getJsonData(point.intValue(), LuckyValueBean.class);
/* 119 */         LuckyTurntableBean luckyTurntableBean = (LuckyTurntableBean)JsonTableService.getJsonData(luckyValueBean.getShowReward(), LuckyTurntableBean.class);
/* 120 */         drawTimes--;
/* 121 */         rewardList.addAll(FinanceUtil.transform(luckyTurntableBean.getReward()));
/* 122 */         turntableIdList.add(Integer.valueOf(luckyValueBean.getShowReward()));
/*     */       } 
/* 124 */       maxPoint = point.intValue();
/*     */     } 
/*     */     
/* 127 */     int curDrawTimes = luckyTurntableEntity.getDrawTimes();
/* 128 */     while (drawTimes > 0) {
/* 129 */       int totalProb, lower = 0;
/* 130 */       curDrawTimes++;
/* 131 */       if (curDrawTimes <= luckyTurntableParameter.getAheadTimes()) {
/* 132 */         totalProb = inDoubleRateTime ? ((Integer)luckyTurntableParameter.getAheadDoubleTotalProb().get(Integer.valueOf(request.actId))).intValue() : ((Integer)luckyTurntableParameter.getAheadTotalProb().get(Integer.valueOf(request.actId))).intValue();
/*     */       } else {
/* 134 */         totalProb = inDoubleRateTime ? ((Integer)luckyTurntableParameter.getDoubleTotalProb().get(Integer.valueOf(request.actId))).intValue() : ((Integer)luckyTurntableParameter.getTotalProb().get(Integer.valueOf(request.actId))).intValue();
/*     */       } 
/* 136 */       int ranNum = RandUtil.randNum(totalProb - 1);
/* 137 */       for (Integer id : luckylistBean.getActReward()) {
/* 138 */         LuckyTurntableBean bean = (LuckyTurntableBean)JsonTableService.getJsonData(id.intValue(), LuckyTurntableBean.class);
/*     */         
/* 140 */         int tmp = (curDrawTimes <= luckyTurntableParameter.getAheadTimes()) ? (inDoubleRateTime ? bean.getDoubleProbability_ten() : bean.getProbability_ten()) : (inDoubleRateTime ? bean.getDoubleProbability() : bean.getProbability());
/* 141 */         int higher = lower + tmp;
/*     */ 
/*     */         
/* 144 */         if (lower <= ranNum && ranNum < higher) {
/* 145 */           rewardList.addAll(FinanceUtil.transform(bean.getReward()));
/* 146 */           turntableIdList.add(Integer.valueOf(bean.getId()));
/*     */           break;
/*     */         } 
/* 149 */         lower = higher;
/*     */       } 
/* 151 */       drawTimes--;
/*     */     } 
/* 153 */     luckyTurntableEntity.setFreeTimes((luckyTurntableEntity.getFreeTimes() < totalDrawTimes) ? luckyTurntableEntity.getFreeTimes() : (luckyTurntableEntity.getFreeTimes() - totalDrawTimes));
/* 154 */     luckyTurntableEntity.setLuckyPoint((luckyPointHigher >= maxPoint) ? (luckyPointHigher - maxPoint) : luckyPointHigher);
/* 155 */     luckyTurntableEntity.setTotalPoint(totalPoint);
/* 156 */     luckyTurntableEntity.setLasttime(System.currentTimeMillis());
/* 157 */     luckyTurntableEntity.setDrawTimes(luckyTurntableEntity.getDrawTimes() + totalDrawTimes);
/* 158 */     LuckyTurntableUtil.updatePlayerPoint(request.actId, playerSession.getPlayer().getPlayerId(), playerSession.getPlayer().getPlayerName(), totalPoint);
/* 159 */     if (request.type == 2) {
/* 160 */       luckyTurntableEntity.setTenDrawCount(luckyTurntableEntity.getTenDrawCount() + 1);
/*     */     }
/* 162 */     luckyTurntableComponent.getRewardMap().put(Integer.valueOf(LuckyTurntableComponent.TYPE_LUCKY_TURNTABLE_KEY), rewardList);
/* 163 */     if (rewardList.size() > 0) {
/* 164 */       FinanceUtil.reward(rewardList, playerSession.getPlayer(), ResourceEvent.LuckyTurntable, false);
/*     */     }
/* 166 */     Collections.shuffle(turntableIdList);
/* 167 */     for (Integer id : turntableIdList) {
/* 168 */       LuckyTurntableBean bean = (LuckyTurntableBean)JsonTableService.getJsonData(id.intValue(), LuckyTurntableBean.class);
/* 169 */       if (bean.getNotice() == 1) {
/* 170 */         for (RewardBean rewardBean : bean.getReward()) {
/* 171 */           LuckyTurntableNoticeResponse resp = new LuckyTurntableNoticeResponse();
/* 172 */           LuckyTurntableRecord record = new LuckyTurntableRecord();
/* 173 */           record.playerName = playerSession.getPlayer().getPlayerName();
/* 174 */           record.itemId = rewardBean.getId();
/* 175 */           record.num = (int)rewardBean.getNum();
/* 176 */           resp.record = record;
/* 177 */           LuckyTurntableUtil.addRecord(record);
/* 178 */           LookUpService.broadcast((ResponseBase)resp);
/*     */         } 
/*     */       }
/*     */     } 
/* 182 */     ((LuckyTurntableDrawResponse)this.response).luckPoint = luckyTurntableEntity.getLuckyPoint();
/* 183 */     ((LuckyTurntableDrawResponse)this.response).freeTimes = luckyTurntableEntity.getFreeTimes();
/* 184 */     ((LuckyTurntableDrawResponse)this.response).rewardList = turntableIdList;
/* 185 */     return 0;
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\luckyTurntable\LuckyTurntableDrawProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */