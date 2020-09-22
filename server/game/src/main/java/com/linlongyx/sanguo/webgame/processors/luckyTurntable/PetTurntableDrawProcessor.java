/*     */ package com.linlongyx.sanguo.webgame.processors.luckyTurntable;
/*     */ 
/*     */ import com.linlongyx.core.framework.base.ProcessorBase;
/*     */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*     */ import com.linlongyx.core.framework.protocol.RequestBase;
/*     */ import com.linlongyx.core.framework.protocol.ResponseBase;
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
/*     */ import com.linlongyx.sanguo.webgame.constant.ParameterConstant;
/*     */ import com.linlongyx.sanguo.webgame.constant.ResourceEvent;
/*     */ import com.linlongyx.sanguo.webgame.processors.limit.LimitUtil;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.luckyTurntable.PetTurntableDrawRequest;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.luckyTurntable.PetTurntableDrawResponse;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.Reward;
/*     */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Set;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PetTurntableDrawProcessor
/*     */   extends ProcessorBase<PetTurntableDrawRequest, PetTurntableDrawResponse>
/*     */ {
/*     */   protected void initResponse() {
/*  37 */     this.response = (ResponseBase)new PetTurntableDrawResponse();
/*     */   }
/*     */   
/*     */   protected short handleRequest(IPlayerSession playerSession, PetTurntableDrawRequest request) {
/*     */     int drawTimes, totalDrawTimes, yuanbao, luckyPointHigher, toolNum;
/*  42 */     if (!FunctionOpenConstant.isFunctionOpen(playerSession, 43)) {
/*  43 */       return 10061;
/*     */     }
/*  45 */     int type = request.type;
/*  46 */     LuckylistBean luckylistBean = (LuckylistBean)JsonTableService.getJsonData(request.actId, LuckylistBean.class);
/*  47 */     if (luckylistBean == null) {
/*  48 */       return 13502;
/*     */     }
/*  50 */     if (!LimitUtil.isValid(luckylistBean.getServerTypeb(), luckylistBean.getDay()) || !LimitUtil.isActOpen(luckylistBean.getServerTypeb(), luckylistBean
/*  51 */         .getBeginTimeb(), luckylistBean.getEndTimeb())) {
/*  52 */       return 13501;
/*     */     }
/*  54 */     LuckyTurntableComponent luckyTurntableComponent = (LuckyTurntableComponent)playerSession.getPlayer().createIfNotExist(LuckyTurntableComponent.class);
/*  55 */     BagComponent bagComponent = (BagComponent)playerSession.getPlayer().createIfNotExist(BagComponent.class);
/*  56 */     LuckyTurntableEntity luckyTurntableEntity = luckyTurntableComponent.getEntity(request.actId);
/*  57 */     if (luckyTurntableEntity == null) {
/*  58 */       return 13502;
/*     */     }
/*  60 */     if (type != 1 && type != 2) {
/*  61 */       return 13502;
/*     */     }
/*  63 */     LuckyTurntableParameter luckyTurntableParameter = (LuckyTurntableParameter)ParameterConstant.getParameter(35);
/*  64 */     ArrayList<Reward> rewardList = new ArrayList<>();
/*  65 */     ArrayList<Integer> turntableIdList = new ArrayList<>();
/*  66 */     RewardBean rewardBean = luckylistBean.getItemReward().get(0);
/*  67 */     int luckyPointLower = luckyTurntableEntity.getLuckyPoint(), maxPoint = 0;
/*  68 */     int totalPoint = luckyTurntableEntity.getTotalPoint();
/*  69 */     if (type == 1) {
/*  70 */       totalDrawTimes = drawTimes = 1;
/*  71 */       yuanbao = luckylistBean.getMoney();
/*  72 */       toolNum = (int)rewardBean.getNum();
/*  73 */       luckyPointHigher = luckyTurntableParameter.getOneDrawLuckyPoint() + luckyPointLower;
/*  74 */       totalPoint += luckyTurntableParameter.getOneDrawLuckyPoint();
/*     */     } else {
/*  76 */       totalDrawTimes = drawTimes = 10;
/*  77 */       yuanbao = luckylistBean.getMoney() * drawTimes;
/*  78 */       toolNum = (int)(rewardBean.getNum() * drawTimes);
/*  79 */       luckyPointHigher = luckyTurntableParameter.getTenDrawLuckyPoint() + luckyPointLower;
/*  80 */       totalPoint += luckyTurntableParameter.getTenDrawLuckyPoint();
/*     */     } 
/*  82 */     if (bagComponent.check(rewardBean.getId(), toolNum)) {
/*  83 */       bagComponent.deleteItem(rewardBean.getId(), toolNum, ResourceEvent.PetTurntable);
/*     */     } else {
/*  85 */       if (!FinanceUtil.checkCurrency(playerSession.getPlayer(), CurrencyType.CCY, yuanbao)) {
/*  86 */         return 10052;
/*     */       }
/*  88 */       FinanceUtil.decCurrency(playerSession.getPlayer(), CurrencyType.CCY, yuanbao, ResourceEvent.PetTurntable);
/*     */     } 
/*     */     
/*  91 */     Set<Integer> pointSet = JsonTableService.getJsonTableKey(LuckyValueBean.class);
/*  92 */     for (Integer point : pointSet) {
/*  93 */       if (luckyPointLower < point.intValue() && point.intValue() <= luckyPointHigher) {
/*  94 */         LuckyValueBean luckyValueBean = (LuckyValueBean)JsonTableService.getJsonData(point.intValue(), LuckyValueBean.class);
/*  95 */         LuckyTurntableBean luckyTurntableBean = (LuckyTurntableBean)JsonTableService.getJsonData(luckyValueBean.getPetReward(), LuckyTurntableBean.class);
/*  96 */         drawTimes--;
/*  97 */         rewardList.addAll(FinanceUtil.transform(luckyTurntableBean.getReward()));
/*  98 */         turntableIdList.add(Integer.valueOf(luckyValueBean.getPetReward()));
/*     */       } 
/* 100 */       maxPoint = point.intValue();
/*     */     } 
/*     */     
/* 103 */     boolean isAheadTenDraw = (request.type == 2 && luckyTurntableEntity.getTenDrawCount() <= luckyTurntableParameter.getPetAheadTimes());
/* 104 */     int totalProb = isAheadTenDraw ? ((Integer)luckyTurntableParameter.getAheadTotalProb().get(Integer.valueOf(request.actId))).intValue() : ((Integer)luckyTurntableParameter.getTotalProb().get(Integer.valueOf(request.actId))).intValue();
/* 105 */     while (drawTimes > 0) {
/* 106 */       int lower = 0;
/* 107 */       int ranNum = RandUtil.randNum(totalProb - 1);
/* 108 */       for (Integer id : luckylistBean.getActReward()) {
/* 109 */         LuckyTurntableBean bean = (LuckyTurntableBean)JsonTableService.getJsonData(id.intValue(), LuckyTurntableBean.class);
/* 110 */         int tmp = isAheadTenDraw ? bean.getProbability_ten() : bean.getProbability();
/* 111 */         int higher = lower + tmp;
/*     */ 
/*     */         
/* 114 */         if (lower <= ranNum && ranNum < higher) {
/* 115 */           rewardList.addAll(FinanceUtil.transform(bean.getReward()));
/* 116 */           turntableIdList.add(Integer.valueOf(bean.getId()));
/*     */           break;
/*     */         } 
/* 119 */         lower = higher;
/*     */       } 
/* 121 */       drawTimes--;
/*     */     } 
/* 123 */     luckyTurntableEntity.setLuckyPoint((luckyPointHigher >= maxPoint) ? (luckyPointHigher - maxPoint) : luckyPointHigher);
/* 124 */     luckyTurntableEntity.setTotalPoint(totalPoint);
/* 125 */     luckyTurntableEntity.setLasttime(System.currentTimeMillis());
/* 126 */     luckyTurntableEntity.setDrawTimes(luckyTurntableEntity.getDrawTimes() + totalDrawTimes);
/* 127 */     LuckyTurntableUtil.updatePlayerPoint(request.actId, playerSession.getPlayer().getPlayerId(), playerSession.getPlayer().getPlayerName(), totalPoint);
/* 128 */     if (request.type == 2) {
/* 129 */       luckyTurntableEntity.setTenDrawCount(luckyTurntableEntity.getTenDrawCount() + 1);
/*     */     }
/* 131 */     luckyTurntableComponent.getRewardMap().put(Integer.valueOf(LuckyTurntableComponent.TYPE_PET_TURNTABLE_KEY), rewardList);
/* 132 */     if (rewardList.size() > 0) {
/* 133 */       FinanceUtil.reward(rewardList, playerSession.getPlayer(), ResourceEvent.PetTurntable, false);
/*     */     }
/* 135 */     ((PetTurntableDrawResponse)this.response).rewardList = turntableIdList;
/* 136 */     ((PetTurntableDrawResponse)this.response).point = luckyTurntableEntity.getLuckyPoint();
/* 137 */     return 0;
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\luckyTurntable\PetTurntableDrawProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */