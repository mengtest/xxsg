/*    */ package com.linlongyx.sanguo.webgame.processors.cardbook;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.core.utils.TimeUtil;
/*    */ import com.linlongyx.sanguo.webgame.app.bag.BagComponent;
/*    */ import com.linlongyx.sanguo.webgame.app.cardbook.CardBookComponent;
/*    */ import com.linlongyx.sanguo.webgame.app.task.TaskComponent;
/*    */ import com.linlongyx.sanguo.webgame.common.util.FinanceUtil;
/*    */ import com.linlongyx.sanguo.webgame.config.bean.CardsBean;
/*    */ import com.linlongyx.sanguo.webgame.constant.FunctionOpenConstant;
/*    */ import com.linlongyx.sanguo.webgame.constant.ResourceEvent;
/*    */ import com.linlongyx.sanguo.webgame.constant.TaskType;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.cardbook.CardBookColoringRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.cardbook.CardBookColoringResponse;
/*    */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*    */ import com.linlongyx.sanguo.webgame.util.CostUtil;
/*    */ 
/*    */ public class CardBookColoringProcessor
/*    */   extends ProcessorBase<CardBookColoringRequest, CardBookColoringResponse> {
/*    */   protected void initResponse() {
/* 24 */     this.response = (ResponseBase)new CardBookColoringResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, CardBookColoringRequest request) {
/* 29 */     if (!FunctionOpenConstant.isFunctionOpen(playerSession, 50))
/* 30 */       return 10061; 
/* 31 */     CardBookComponent cardBookComponent = (CardBookComponent)playerSession.getPlayer().createIfNotExist(CardBookComponent.class);
/* 32 */     CardsBean cardsBean = (CardsBean)JsonTableService.getJsonData(request.cardId, CardsBean.class);
/* 33 */     if (null == cardsBean) {
/* 34 */       return 10006;
/*    */     }
/* 36 */     if (cardBookComponent.getLastCardColor().get(Integer.valueOf(request.cardId)) != null && ((Integer)cardBookComponent.getLastCardColor().get(Integer.valueOf(request.cardId))).intValue() >= cardsBean.getMax()) {
/* 37 */       return 12704;
/*    */     }
/* 39 */     BagComponent bagComponent = (BagComponent)playerSession.getPlayer().createIfNotExist(BagComponent.class);
/* 40 */     if (!bagComponent.check(cardsBean.getCard(), cardsBean.getCardNumber())) {
/* 41 */       return 10050;
/*    */     }
/* 43 */     short res = CostUtil.checkRewards(FinanceUtil.transform(cardsBean.getColor()), playerSession, bagComponent);
/* 44 */     if (res != 0) {
/* 45 */       return res;
/*    */     }
/* 47 */     bagComponent.deleteItem(cardsBean.getCard(), cardsBean.getCardNumber(), ResourceEvent.CardColor, false);
/* 48 */     CostUtil.costs(FinanceUtil.transform(cardsBean.getColor()), playerSession, bagComponent, ResourceEvent.CardColor);
/*    */     
/* 50 */     cardBookComponent.getCardColor().put(Integer.valueOf(request.cardId), Integer.valueOf(((Integer)cardBookComponent.getCardColor().getOrDefault(Integer.valueOf(request.cardId), Integer.valueOf(0))).intValue() + 1));
/* 51 */     cardBookComponent.setCardColor(cardBookComponent.getCardColor());
/*    */     
/* 53 */     cardBookComponent.getLastCardColor().put(Integer.valueOf(request.cardId), Integer.valueOf(((Integer)cardBookComponent.getLastCardColor().getOrDefault(Integer.valueOf(request.cardId), Integer.valueOf(0))).intValue() + 1));
/* 54 */     cardBookComponent.setLastCardColor(cardBookComponent.getLastCardColor());
/*    */     
/* 56 */     cardBookComponent.setColorTime(TimeUtil.currentTime());
/* 57 */     cardBookComponent.setLastToColor(cardBookComponent.getLastToColor() + 1);
/* 58 */     cardBookComponent.setRefToColor(cardBookComponent.getRefToColor() + 1);
/* 59 */     if (((Integer)cardBookComponent.getLastCardColor().get(Integer.valueOf(request.cardId))).intValue() == 1) {
/* 60 */       ((CardBookColoringResponse)this.response).list = FinanceUtil.rewardGet(FinanceUtil.transform(cardsBean.getFirstReward()), playerSession.getPlayer(), ResourceEvent.CardColorReward, true);
/*    */     } else {
/* 62 */       ((CardBookColoringResponse)this.response).list = FinanceUtil.rewardGet(FinanceUtil.transform(cardsBean.getRepeatReward()), playerSession.getPlayer(), ResourceEvent.CardColorReward, true);
/*    */     } 
/* 64 */     TaskComponent taskComponent = (TaskComponent)playerSession.getPlayer().getComponent(TaskComponent.class);
/* 65 */     if (null != taskComponent) {
/* 66 */       taskComponent.refreshSchedule(TaskType.CardBookColor, 0, 1L);
/*    */     }
/* 68 */     ((CardBookColoringResponse)this.response).cardId = request.cardId;
/* 69 */     ((CardBookColoringResponse)this.response).totalRefColor = cardBookComponent.getRefToColor();
/* 70 */     ((CardBookColoringResponse)this.response).totalLastColor = cardBookComponent.getLastToColor();
/* 71 */     ((CardBookColoringResponse)this.response).refColor = ((Integer)cardBookComponent.getCardColor().get(Integer.valueOf(request.cardId))).intValue();
/* 72 */     ((CardBookColoringResponse)this.response).lastColor = ((Integer)cardBookComponent.getLastCardColor().get(Integer.valueOf(request.cardId))).intValue();
/* 73 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\cardbook\CardBookColoringProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */