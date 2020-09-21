/*     */ package com.linlongyx.sanguo.webgame.processors.cat;
/*     */ 
/*     */ import com.linlongyx.core.framework.base.ProcessorBase;
/*     */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*     */ import com.linlongyx.core.framework.protocol.RequestBase;
/*     */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*     */ import com.linlongyx.sanguo.webgame.app.cat.FortuneCatComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.cat.FortuneCatEntity;
/*     */ import com.linlongyx.sanguo.webgame.app.player.PlayerComponent;
/*     */ import com.linlongyx.sanguo.webgame.common.util.FinanceUtil;
/*     */ import com.linlongyx.sanguo.webgame.common.util.RandUtil;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.FortuneCatBean;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.FortuneChargeBean;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.FortuneTimeBean;
/*     */ import com.linlongyx.sanguo.webgame.config.parameter.FortuneCatParameter;
/*     */ import com.linlongyx.sanguo.webgame.constant.CurrencyType;
/*     */ import com.linlongyx.sanguo.webgame.constant.ParameterConstant;
/*     */ import com.linlongyx.sanguo.webgame.constant.ResourceEvent;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.cat.FortuneCatPlayRequest;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.cat.FortuneCatPlayResponse;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.Reward;
/*     */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Collections;
/*     */ import java.util.Iterator;
/*     */ import java.util.TreeSet;
/*     */ 
/*     */ public class FortuneCatPlayProcessor extends ProcessorBase<FortuneCatPlayRequest, FortuneCatPlayResponse> {
/*     */   protected void initResponse() {
/*  31 */     this.response = (ResponseBase)new FortuneCatPlayResponse();
/*     */   }
/*     */ 
/*     */   
/*     */   protected short handleRequest(IPlayerSession playerSession, FortuneCatPlayRequest request) {
/*  36 */     int id = request.id;
/*  37 */     FortuneTimeBean fortuneTimeBean = (FortuneTimeBean)JsonTableService.getJsonData(id, FortuneTimeBean.class);
/*  38 */     if (null == fortuneTimeBean) {
/*  39 */       return 18001;
/*     */     }
/*  41 */     FortuneCatParameter fortuneCatParameter = (FortuneCatParameter)ParameterConstant.getParameter(80);
/*  42 */     if (!fortuneCatParameter.isActOpen(id)) {
/*  43 */       return 18002;
/*     */     }
/*  45 */     FortuneCatComponent fortuneCatComponent = (FortuneCatComponent)playerSession.getPlayer().createIfNotExist(FortuneCatComponent.class);
/*  46 */     PlayerComponent playerComponent = (PlayerComponent)playerSession.getPlayer().createIfNotExist(PlayerComponent.class);
/*  47 */     FortuneCatEntity fortuneCatEntity = fortuneCatComponent.getEntity(id);
/*  48 */     int count = fortuneCatEntity.getCount();
/*  49 */     int maxCount = 0;
/*  50 */     if (fortuneTimeBean.getType() == 1) {
/*  51 */       maxCount = fortuneCatParameter.getCount(playerComponent.getVip());
/*  52 */     } else if (fortuneTimeBean.getType() == 2) {
/*  53 */       TreeSet<Integer> treeSet = JsonTableService.getJsonTableKey(FortuneChargeBean.class);
/*  54 */       Collections.sort(new ArrayList<>((Collection)treeSet));
/*  55 */       for (Iterator<Integer> iterator = treeSet.iterator(); iterator.hasNext(); ) { int treeId = ((Integer)iterator.next()).intValue();
/*  56 */         long currCharge = fortuneCatEntity.getActCharge();
/*  57 */         FortuneChargeBean fortuneChargeBean = (FortuneChargeBean)JsonTableService.getJsonData(treeId, FortuneChargeBean.class);
/*  58 */         if (currCharge >= fortuneChargeBean.getCharge()) {
/*  59 */           maxCount = fortuneChargeBean.getNum();
/*     */         } }
/*     */       
/*  62 */       maxCount += fortuneCatParameter.getDefaultNum();
/*     */     } 
/*     */     
/*  65 */     int leftCount = maxCount - count;
/*  66 */     if (leftCount <= 0) {
/*  67 */       return 18003;
/*     */     }
/*  69 */     FortuneCatBean.TimesBean timesBean = FortuneCatUtil.getFortuneCatBean(count, fortuneTimeBean.getType());
/*  70 */     if (null == timesBean) {
/*  71 */       return 18001;
/*     */     }
/*  73 */     if (!FinanceUtil.checkCurrency(playerSession.getPlayer(), CurrencyType.CCY, timesBean.getCost())) {
/*  74 */       return 10052;
/*     */     }
/*  76 */     if (fortuneTimeBean.getType() == 1) {
/*  77 */       FinanceUtil.decCurrency(playerSession.getPlayer(), CurrencyType.CCY, timesBean.getCost(), ResourceEvent.FortuneCatCost, true);
/*  78 */     } else if (fortuneTimeBean.getType() == 2) {
/*  79 */       FinanceUtil.decCurrency(playerSession.getPlayer(), CurrencyType.CCY, timesBean.getCost(), ResourceEvent.FortuneCatCost, true);
/*     */     } 
/*  81 */     count++;
/*  82 */     fortuneCatEntity.setCount(count);
/*  83 */     fortuneCatComponent.updateCountDB(id);
/*  84 */     if (fortuneCatEntity.getMinCCY() == 0 || fortuneCatEntity.getMaxCCY() == 0) {
/*  85 */       FortuneCatUtil.getFortuneCatCCY(timesBean, fortuneCatEntity);
/*     */     }
/*  87 */     int num = RandUtil.randNum(fortuneCatEntity.getMinCCY(), fortuneCatEntity.getMaxCCY());
/*  88 */     Reward reward = new Reward();
/*  89 */     reward.type = 1;
/*  90 */     reward.id = CurrencyType.CCY.getType();
/*  91 */     reward.num = num;
/*  92 */     ArrayList<Reward> rewards = new ArrayList<>();
/*  93 */     rewards.add(reward);
/*  94 */     FinanceUtil.reward(rewards, playerSession.getPlayer(), ResourceEvent.FortuneCatReward, false);
/*  95 */     fortuneCatComponent.setRewards(rewards);
/*     */     
/*  97 */     ((FortuneCatPlayResponse)this.response).id = id;
/*  98 */     ((FortuneCatPlayResponse)this.response).leftCount = maxCount - count;
/*  99 */     timesBean = FortuneCatUtil.getFortuneCatBean(count, fortuneTimeBean.getType());
/* 100 */     if (null != timesBean) {
/* 101 */       ((FortuneCatPlayResponse)this.response).cost = timesBean.getCost();
/* 102 */       FortuneCatUtil.getFortuneCatCCY(timesBean, fortuneCatEntity);
/* 103 */       ((FortuneCatPlayResponse)this.response).maxValue = timesBean.getThirdL();
/* 104 */       ((FortuneCatPlayResponse)this.response).minValue = timesBean.getFirstR();
/*     */     } 
/* 106 */     ((FortuneCatPlayResponse)this.response).num = num;
/* 107 */     FortuneCatUtil.addRewardList(num, playerSession.getPlayer().getPlayerName(), fortuneTimeBean.getType());
/* 108 */     return 0;
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\cat\FortuneCatPlayProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */