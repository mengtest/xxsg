/*    */ package com.linlongyx.sanguo.webgame.processors.cardbook;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.core.utils.TimeUtil;
/*    */ import com.linlongyx.sanguo.webgame.app.cardbook.CardBookComponent;
/*    */ import com.linlongyx.sanguo.webgame.common.util.FinanceUtil;
/*    */ import com.linlongyx.sanguo.webgame.config.bean.CardsProcessBean;
/*    */ import com.linlongyx.sanguo.webgame.constant.FunctionOpenConstant;
/*    */ import com.linlongyx.sanguo.webgame.constant.ResourceEvent;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.cardbook.CardBookProcessRewardRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.cardbook.CardBookProcessRewardResponse;
/*    */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CardBookProcessRewardProcessor
/*    */   extends ProcessorBase<CardBookProcessRewardRequest, CardBookProcessRewardResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 24 */     this.response = (ResponseBase)new CardBookProcessRewardResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, CardBookProcessRewardRequest request) {
/* 29 */     if (!FunctionOpenConstant.isFunctionOpen(playerSession, 50))
/* 30 */       return 10061; 
/* 31 */     CardsProcessBean cardsProcessBean = (CardsProcessBean)JsonTableService.getJsonData(request.processId, CardsProcessBean.class);
/* 32 */     if (null == cardsProcessBean) {
/* 33 */       return 10006;
/*    */     }
/* 35 */     CardBookComponent cardBookComponent = (CardBookComponent)playerSession.getPlayer().createIfNotExist(CardBookComponent.class);
/* 36 */     if (cardBookComponent.getLastToColor() < cardsProcessBean.getRequire()) {
/* 37 */       return 15018;
/*    */     }
/* 39 */     if (cardBookComponent.getProcessMap().containsKey(Integer.valueOf(request.processId))) {
/* 40 */       return 15002;
/*    */     }
/* 42 */     cardBookComponent.getProcessMap().put(Integer.valueOf(request.processId), Integer.valueOf(TimeUtil.currentTime()));
/* 43 */     cardBookComponent.setProcessMap(cardBookComponent.getProcessMap());
/* 44 */     FinanceUtil.reward(FinanceUtil.transform(cardsProcessBean.getReward()), playerSession.getPlayer(), ResourceEvent.CardColorProcess, true);
/* 45 */     ((CardBookProcessRewardResponse)this.response).processId = request.processId;
/* 46 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\cardbook\CardBookProcessRewardProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */