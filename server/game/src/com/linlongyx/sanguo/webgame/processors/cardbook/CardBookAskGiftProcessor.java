/*    */ package com.linlongyx.sanguo.webgame.processors.cardbook;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.core.utils.TimeUtil;
/*    */ import com.linlongyx.sanguo.webgame.app.cardbook.CardBookComponent;
/*    */ import com.linlongyx.sanguo.webgame.app.group.GroupMemberComponent;
/*    */ import com.linlongyx.sanguo.webgame.config.bean.CardsDonateBean;
/*    */ import com.linlongyx.sanguo.webgame.config.bean.ItemBean;
/*    */ import com.linlongyx.sanguo.webgame.config.parameter.CardBookParameter;
/*    */ import com.linlongyx.sanguo.webgame.constant.FunctionOpenConstant;
/*    */ import com.linlongyx.sanguo.webgame.constant.ParameterConstant;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.cardbook.CardBookAskGiftRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.cardbook.CardBookAskGiftResponse;
/*    */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CardBookAskGiftProcessor
/*    */   extends ProcessorBase<CardBookAskGiftRequest, CardBookAskGiftResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 26 */     this.response = (ResponseBase)new CardBookAskGiftResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, CardBookAskGiftRequest request) {
/* 31 */     if (!FunctionOpenConstant.isFunctionOpen(playerSession, 50))
/* 32 */       return 10061; 
/* 33 */     CardBookParameter cardBookParameter = (CardBookParameter)ParameterConstant.getParameter(50);
/* 34 */     CardBookComponent cardBookComponent = (CardBookComponent)playerSession.getPlayer().createIfNotExist(CardBookComponent.class);
/*    */ 
/*    */     
/* 37 */     if (cardBookComponent.getNextAskTime() != 0 && cardBookComponent.getNextAskTime() > TimeUtil.currentTime()) {
/* 38 */       return 15009;
/*    */     }
/* 40 */     int type = 0;
/* 41 */     if (cardBookParameter.getColorList().indexOf(Integer.valueOf(request.itemId)) < 0) {
/* 42 */       if (cardBookComponent.getBottomCardAsk() >= cardBookParameter.getBomCardAskLimit()) {
/* 43 */         return 10089;
/*    */       }
/* 45 */       type = 1;
/*    */     }
/* 47 */     else if (cardBookComponent.getColorDanAsk() >= cardBookParameter.getColorDanAsLimit()) {
/* 48 */       return 10089;
/*    */     } 
/*    */     
/* 51 */     ItemBean itemBean = (ItemBean)JsonTableService.getJsonData(request.itemId, ItemBean.class);
/* 52 */     CardsDonateBean cardsDonateBean = (CardsDonateBean)JsonTableService.getJsonData(request.itemId, CardsDonateBean.class);
/* 53 */     if (null == itemBean || null == cardsDonateBean) {
/* 54 */       return 10050;
/*    */     }
/* 56 */     if (request.chatType == 0) {
/* 57 */       cardBookComponent.setInfoId(cardBookComponent.getInfoId() + 1L);
/* 58 */       CardBookUtil.cardBookAsk(playerSession, request, type, itemBean, cardBookComponent.getInfoId());
/* 59 */     } else if (request.chatType == 1) {
/* 60 */       GroupMemberComponent groupMemberComponent = (GroupMemberComponent)playerSession.getPlayer().createIfNotExist(GroupMemberComponent.class);
/* 61 */       if (0L == groupMemberComponent.getId()) {
/* 62 */         return 11101;
/*    */       }
/* 64 */       cardBookComponent.setInfoId(cardBookComponent.getInfoId() + 1L);
/* 65 */       CardBookUtil.cardBookAsk(playerSession, request, type, itemBean, cardBookComponent.getInfoId());
/*    */     } else {
/* 67 */       return 10404;
/*    */     } 
/* 69 */     cardBookComponent.setNextAskTime(TimeUtil.currentTime() + cardBookParameter.getAskCD());
/* 70 */     cardBookComponent.getItemAsk().put(Long.valueOf(cardBookComponent.getInfoId()), Integer.valueOf(request.itemId));
/* 71 */     cardBookComponent.setItemAsk(cardBookComponent.getItemAsk());
/* 72 */     cardBookComponent.getEndTime().put(Long.valueOf(cardBookComponent.getInfoId()), Integer.valueOf(TimeUtil.currentTime() + cardBookParameter.getRobatorResponse()));
/* 73 */     cardBookComponent.setEndTime(cardBookComponent.getEndTime());
/* 74 */     ((CardBookAskGiftResponse)this.response).chatType = request.chatType;
/* 75 */     ((CardBookAskGiftResponse)this.response).itemId = request.itemId;
/* 76 */     ((CardBookAskGiftResponse)this.response).nexAskTime = cardBookComponent.getNextAskTime();
/* 77 */     ((CardBookAskGiftResponse)this.response).bottomCard = cardBookComponent.getBottomCardAsk();
/* 78 */     ((CardBookAskGiftResponse)this.response).colorDan = cardBookComponent.getColorDanAsk();
/* 79 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\cardbook\CardBookAskGiftProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */