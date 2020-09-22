/*     */ package com.linlongyx.sanguo.webgame.processors.cardbook;
/*     */ 
/*     */ import com.linlongyx.core.framework.base.ProcessorBase;
/*     */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*     */ import com.linlongyx.core.framework.protocol.RequestBase;
/*     */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*     */ import com.linlongyx.sanguo.webgame.app.cardbook.CardBookComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.player.PlayerComponent;
/*     */ import com.linlongyx.sanguo.webgame.common.fight.attribute.AttrUpdateUtil;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.CardsPairBean;
/*     */ import com.linlongyx.sanguo.webgame.config.parameter.CardBookParameter;
/*     */ import com.linlongyx.sanguo.webgame.constant.FunctionOpenConstant;
/*     */ import com.linlongyx.sanguo.webgame.constant.ParameterConstant;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.cardbook.CardBookPairUpgradeRequest;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.cardbook.CardBookPairUpgradeResponse;
/*     */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*     */ import java.util.Iterator;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CardBookPairUpgradeProcessor
/*     */   extends ProcessorBase<CardBookPairUpgradeRequest, CardBookPairUpgradeResponse>
/*     */ {
/*     */   protected void initResponse() {
/*  25 */     this.response = (ResponseBase)new CardBookPairUpgradeResponse();
/*     */   }
/*     */ 
/*     */   
/*     */   protected short handleRequest(IPlayerSession playerSession, CardBookPairUpgradeRequest request) {
/*  30 */     if (!FunctionOpenConstant.isFunctionOpen(playerSession, 50))
/*  31 */       return 10061; 
/*  32 */     CardBookComponent cardBookComponent = (CardBookComponent)playerSession.getPlayer().createIfNotExist(CardBookComponent.class);
/*  33 */     CardsPairBean cardsPairBean = (CardsPairBean)JsonTableService.getJsonData(request.pairId, CardsPairBean.class);
/*  34 */     if (null == cardsPairBean) {
/*  35 */       return 10006;
/*     */     }
/*  37 */     PlayerComponent playerComponent = (PlayerComponent)playerSession.getPlayer().createIfNotExist(PlayerComponent.class);
/*  38 */     if (playerComponent.getLevel() < cardsPairBean.getLevel()) {
/*  39 */       return 10084;
/*     */     }
/*  41 */     if (request.type != 1 && request.type != 0) {
/*  42 */       return 11808;
/*     */     }
/*  44 */     if (request.type == 0) {
/*  45 */       if (cardBookComponent.getCardPair().containsKey(Integer.valueOf(request.pairId)))
/*  46 */         return 15005; 
/*     */       Iterator<Integer> iterator;
/*  48 */       for (iterator = cardsPairBean.getRequire().iterator(); iterator.hasNext(); ) { int cardId = ((Integer)iterator.next()).intValue();
/*  49 */         if (!cardBookComponent.getCardColor().containsKey(Integer.valueOf(cardId))) {
/*  50 */           return 16007;
/*     */         }
/*  52 */         if (((Integer)cardBookComponent.getCardColor().getOrDefault(Integer.valueOf(cardId), (V)Integer.valueOf(0))).intValue() < 1) {
/*  53 */           return 16007;
/*     */         } }
/*     */ 
/*     */       
/*  57 */       for (iterator = cardsPairBean.getRequire().iterator(); iterator.hasNext(); ) { int cardId = ((Integer)iterator.next()).intValue();
/*  58 */         int num = ((Integer)cardBookComponent.getCardColor().getOrDefault(Integer.valueOf(cardId), Integer.valueOf(0))).intValue() - 1;
/*  59 */         cardBookComponent.getCardColor().put(Integer.valueOf(cardId), Integer.valueOf(num));
/*  60 */         cardBookComponent.setCardColor(cardBookComponent.getCardColor()); }
/*     */       
/*  62 */       cardBookComponent.getCardPair().put(Integer.valueOf(request.pairId), Integer.valueOf(0));
/*  63 */       cardBookComponent.setCardPair(cardBookComponent.getCardPair());
/*  64 */       cardBookComponent.setRefToColor(cardBookComponent.getRefToColor() - cardsPairBean.getRequire().size());
/*     */     } else {
/*  66 */       CardBookParameter cardBookParameter = (CardBookParameter)ParameterConstant.getParameter(50);
/*  67 */       if (!cardBookComponent.getCardPair().containsKey(Integer.valueOf(request.pairId))) {
/*  68 */         return 15005;
/*     */       }
/*  70 */       if (((Integer)cardBookComponent.getCardPair().get(Integer.valueOf(request.pairId))).intValue() >= cardBookParameter.getMaxStar())
/*  71 */         return 10011; 
/*     */       Iterator<Integer> iterator;
/*  73 */       for (iterator = cardsPairBean.getRequire().iterator(); iterator.hasNext(); ) { int cardId = ((Integer)iterator.next()).intValue();
/*  74 */         if (!cardBookComponent.getCardColor().containsKey(Integer.valueOf(cardId))) {
/*  75 */           return 16007;
/*     */         }
/*  77 */         if (((Integer)cardBookComponent.getCardColor().getOrDefault(Integer.valueOf(cardId), (V)Integer.valueOf(0))).intValue() < 1) {
/*  78 */           return 16007;
/*     */         } }
/*     */       
/*  81 */       for (iterator = cardsPairBean.getRequire().iterator(); iterator.hasNext(); ) { int cardId = ((Integer)iterator.next()).intValue();
/*  82 */         int num = ((Integer)cardBookComponent.getCardColor().getOrDefault(Integer.valueOf(cardId), Integer.valueOf(0))).intValue() - 1;
/*  83 */         cardBookComponent.getCardColor().put(Integer.valueOf(cardId), Integer.valueOf(num));
/*  84 */         cardBookComponent.setCardColor(cardBookComponent.getCardColor()); }
/*     */       
/*  86 */       cardBookComponent.getCardPair().put(Integer.valueOf(request.pairId), Integer.valueOf(((Integer)cardBookComponent.getCardPair().getOrDefault(Integer.valueOf(request.pairId), Integer.valueOf(0))).intValue() + 1));
/*  87 */       cardBookComponent.setCardPair(cardBookComponent.getCardPair());
/*  88 */       cardBookComponent.setRefToColor(cardBookComponent.getRefToColor() - cardsPairBean.getRequire().size());
/*     */     } 
/*  90 */     ((CardBookPairUpgradeResponse)this.response).type = request.type;
/*  91 */     ((CardBookPairUpgradeResponse)this.response).level = ((Integer)cardBookComponent.getCardPair().get(Integer.valueOf(request.pairId))).intValue();
/*  92 */     ((CardBookPairUpgradeResponse)this.response).pairId = request.pairId;
/*  93 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public void process(IPlayerSession playerSession, CardBookPairUpgradeRequest request) throws Exception {
/*  98 */     short retCode = handleRequest(playerSession, request);
/*  99 */     ((CardBookPairUpgradeResponse)this.response).setRetCode(retCode);
/* 100 */     playerSession.sendMessage(this.response);
/* 101 */     if (0 != retCode)
/* 102 */       return;  AttrUpdateUtil.refreshCardBook(playerSession);
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\cardbook\CardBookPairUpgradeProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */