/*    */ package com.linlongyx.sanguo.webgame.processors.cardbook;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.app.cardbook.CardBookComponent;
/*    */ import com.linlongyx.sanguo.webgame.constant.FunctionOpenConstant;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.cardbook.CardBookInfoRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.cardbook.CardBookInfoResponse;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.KeyValue;
/*    */ import java.util.ArrayList;
/*    */ import java.util.Iterator;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CardBookInfoProcessor
/*    */   extends ProcessorBase<CardBookInfoRequest, CardBookInfoResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 22 */     this.response = (ResponseBase)new CardBookInfoResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, CardBookInfoRequest request) {
/* 27 */     if (!FunctionOpenConstant.isFunctionOpen(playerSession, 50))
/* 28 */       return 10061; 
/* 29 */     CardBookComponent cardBookComponent = (CardBookComponent)playerSession.getPlayer().createIfNotExist(CardBookComponent.class);
/* 30 */     ((CardBookInfoResponse)this.response).bottomCard = cardBookComponent.getBottomCardAsk();
/* 31 */     ((CardBookInfoResponse)this.response).colorDan = cardBookComponent.getColorDanAsk();
/* 32 */     ((CardBookInfoResponse)this.response).giveTimes = cardBookComponent.getGiveTimes();
/* 33 */     ((CardBookInfoResponse)this.response).lastToColor = cardBookComponent.getLastToColor();
/* 34 */     ((CardBookInfoResponse)this.response).refToColor = cardBookComponent.getRefToColor();
/* 35 */     ((CardBookInfoResponse)this.response).nextAskTime = cardBookComponent.getNextAskTime(); Iterator<Integer> iterator;
/* 36 */     for (iterator = cardBookComponent.getCardColor().keySet().iterator(); iterator.hasNext(); ) { int id = ((Integer)iterator.next()).intValue();
/* 37 */       KeyValue keyValue = new KeyValue();
/* 38 */       keyValue.key = id;
/* 39 */       keyValue.value = ((Integer)cardBookComponent.getCardColor().get(Integer.valueOf(id))).intValue();
/* 40 */       keyValue.valueStr = (new StringBuilder()).append(cardBookComponent.getLastCardColor().getOrDefault(Integer.valueOf(id), Integer.valueOf(0))).append("").toString();
/* 41 */       ((CardBookInfoResponse)this.response).coloringInfo.add(keyValue); }
/*    */     
/* 43 */     for (iterator = cardBookComponent.getCardPair().keySet().iterator(); iterator.hasNext(); ) { int id = ((Integer)iterator.next()).intValue();
/* 44 */       KeyValue keyValue = new KeyValue();
/* 45 */       keyValue.key = id;
/* 46 */       keyValue.value = ((Integer)cardBookComponent.getCardPair().get(Integer.valueOf(id))).intValue();
/* 47 */       ((CardBookInfoResponse)this.response).cardPair.add(keyValue); }
/*    */     
/* 49 */     ((CardBookInfoResponse)this.response).process = new ArrayList(cardBookComponent.getProcessMap().keySet());
/* 50 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\cardbook\CardBookInfoProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */