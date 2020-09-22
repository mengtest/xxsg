/*     */ package com.linlongyx.sanguo.webgame.processors.cardbook;
/*     */ 
/*     */ import com.linlongyx.core.framework.base.ProcessorBase;
/*     */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*     */ import com.linlongyx.core.framework.protocol.RequestBase;
/*     */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*     */ import com.linlongyx.sanguo.webgame.app.bag.BagComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.cardbook.CardBookComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.player.PlayerComponent;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.CardsDonateBean;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.ItemBean;
/*     */ import com.linlongyx.sanguo.webgame.config.parameter.CardBookParameter;
/*     */ import com.linlongyx.sanguo.webgame.constant.FunctionOpenConstant;
/*     */ import com.linlongyx.sanguo.webgame.constant.LanguageConstant;
/*     */ import com.linlongyx.sanguo.webgame.constant.ParameterConstant;
/*     */ import com.linlongyx.sanguo.webgame.constant.ResourceEvent;
/*     */ import com.linlongyx.sanguo.webgame.processors.mail.MailUtil;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.cardbook.CardBookGiveRequest;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.cardbook.CardBookGiveResponse;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.Reward;
/*     */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*     */ import com.linlongyx.sanguo.webgame.service.LookUpService;
/*     */ import com.linlongyx.sanguo.webgame.util.CostUtil;
/*     */ import java.util.ArrayList;
/*     */ 
/*     */ public class CardBookGiveProcessor
/*     */   extends ProcessorBase<CardBookGiveRequest, CardBookGiveResponse> {
/*     */   protected void initResponse() {
/*  29 */     this.response = (ResponseBase)new CardBookGiveResponse();
/*     */   }
/*     */   
/*     */   protected short handleRequest(IPlayerSession playerSession, CardBookGiveRequest request) {
/*     */     CardBookComponent targetCardBookComponent;
/*  34 */     if (!FunctionOpenConstant.isFunctionOpen(playerSession, 50))
/*  35 */       return 10061; 
/*  36 */     PlayerComponent targetPlayerComponent = (PlayerComponent)LookUpService.getComponent(request.playerId, PlayerComponent.class);
/*     */     
/*  38 */     if (request.playerId == playerSession.getPlayer().getPlayerId()) {
/*  39 */       return 11811;
/*     */     }
/*  41 */     if (LookUpService.getByPlayerId(request.playerId) != null) {
/*  42 */       targetCardBookComponent = (CardBookComponent)LookUpService.getComponent(request.playerId, CardBookComponent.class);
/*     */     } else {
/*  44 */       targetCardBookComponent = CardBookUtil.getCardBookComponent(request.playerId);
/*     */     } 
/*  46 */     BagComponent bagComponent = (BagComponent)playerSession.getPlayer().createIfNotExist(BagComponent.class);
/*  47 */     CardBookParameter cardBookParameter = (CardBookParameter)ParameterConstant.getParameter(50);
/*  48 */     CardBookComponent myCardBookComponent = (CardBookComponent)playerSession.getPlayer().createIfNotExist(CardBookComponent.class);
/*  49 */     if (null == targetCardBookComponent || null == targetPlayerComponent) {
/*  50 */       return 11808;
/*     */     }
/*  52 */     if (myCardBookComponent.getGiveTimes() >= cardBookParameter.getGiveLimit()) {
/*  53 */       return 10089;
/*     */     }
/*     */ 
/*     */     
/*  57 */     ArrayList<Reward> rewards = new ArrayList<>();
/*     */ 
/*     */     
/*  60 */     int itemId = ((Integer)targetCardBookComponent.getItemAsk().get(Long.valueOf(request.giftId))).intValue();
/*     */     
/*  62 */     ItemBean itemBean = (ItemBean)JsonTableService.getJsonData(itemId, ItemBean.class);
/*  63 */     if (null == itemBean) {
/*  64 */       return 10701;
/*     */     }
/*  66 */     CardsDonateBean cardsDonateBean = (CardsDonateBean)JsonTableService.getJsonData(itemId, CardsDonateBean.class);
/*  67 */     if (null == cardsDonateBean) {
/*  68 */       return 10701;
/*     */     }
/*  70 */     Reward reward = new Reward();
/*  71 */     reward.type = 2;
/*  72 */     reward.id = itemId;
/*  73 */     reward.num = 1L;
/*  74 */     rewards.add(reward);
/*     */     
/*  76 */     if (CostUtil.check(reward, playerSession, bagComponent) != 0) {
/*  77 */       return 10050;
/*     */     }
/*  79 */     if (!targetCardBookComponent.getItemAsk().containsKey(Long.valueOf(request.giftId))) {
/*  80 */       return 11809;
/*     */     }
/*  82 */     if (!targetCardBookComponent.getEndTime().containsKey(Long.valueOf(request.giftId))) {
/*  83 */       return 11809;
/*     */     }
/*     */     
/*  86 */     if (targetCardBookComponent.getGetGive().containsKey(Long.valueOf(request.giftId))) {
/*  87 */       return 11810;
/*     */     }
/*     */     
/*  90 */     synchronized (targetCardBookComponent) {
/*  91 */       targetCardBookComponent.getGetGive().put(Long.valueOf(request.giftId), Long.valueOf(playerSession.getPlayer().getPlayerId()));
/*  92 */       targetCardBookComponent.setGetGive(targetCardBookComponent.getGetGive());
/*  93 */       if (cardBookParameter.getColorList().indexOf(Integer.valueOf(itemId)) >= 0) {
/*  94 */         if (targetCardBookComponent.getColorDanAsk() >= cardBookParameter.getColorDanAsLimit()) {
/*  95 */           return 10089;
/*     */         }
/*  97 */         targetCardBookComponent.setColorDanAsk(targetCardBookComponent.getColorDanAsk() + 1);
/*     */       } else {
/*  99 */         if (targetCardBookComponent.getBottomCardAsk() >= cardBookParameter.getBomCardAskLimit()) {
/* 100 */           return 10089;
/*     */         }
/* 102 */         targetCardBookComponent.setBottomCardAsk(targetCardBookComponent.getBottomCardAsk() + 1);
/*     */       } 
/* 104 */       CardBookUtil.saveToDB(targetCardBookComponent);
/*     */     } 
/* 106 */     myCardBookComponent.setGiveTimes(myCardBookComponent.getGiveTimes() + 1);
/*     */     
/* 108 */     CostUtil.cost(reward, playerSession, bagComponent, ResourceEvent.GiveAskCost);
/*     */     
/* 110 */     String title = LanguageConstant.getAndReplaceLanguage(5003, new String[0]);
/* 111 */     String content = LanguageConstant.getAndReplaceLanguage(5004, new String[] { targetPlayerComponent.getPlayerName() });
/* 112 */     MailUtil.sendSysRewardBeanMail(playerSession.getPlayer().getPlayerId(), cardsDonateBean.getDonateReward(), title, content);
/*     */     
/* 114 */     String title2 = LanguageConstant.getAndReplaceLanguage(5001, new String[0]);
/* 115 */     String content2 = LanguageConstant.getAndReplaceLanguage(5002, new String[] { playerSession.getPlayer().getPlayerName(), itemBean.getName() });
/* 116 */     MailUtil.sendSysMail(request.playerId, rewards, title2, content2);
/*     */     
/* 118 */     targetPlayerComponent.maybeSaveToDB();
/* 119 */     ((CardBookGiveResponse)this.response).giftId = request.giftId;
/* 120 */     ((CardBookGiveResponse)this.response).playerId = request.playerId;
/* 121 */     return 0;
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\cardbook\CardBookGiveProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */