/*    */ package com.linlongyx.sanguo.webgame.processors.mental;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.app.mental.MentalComponent;
/*    */ import com.linlongyx.sanguo.webgame.constant.FunctionOpenConstant;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.mental.MentalLotteryInfoRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.mental.MentalLotteryInfoResponse;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class MentalLotteryInfoProcessor
/*    */   extends ProcessorBase<MentalLotteryInfoRequest, MentalLotteryInfoResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 19 */     this.response = (ResponseBase)new MentalLotteryInfoResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, MentalLotteryInfoRequest request) {
/* 24 */     if (!FunctionOpenConstant.isFunctionOpen(playerSession, 58)) {
/* 25 */       return 10061;
/*    */     }
/* 27 */     MentalComponent mentalComponent = (MentalComponent)playerSession.getPlayer().createIfNotExist(MentalComponent.class);
/*    */     
/* 29 */     ((MentalLotteryInfoResponse)this.response).retCode = mentalComponent.getLotteryInfo((MentalLotteryInfoResponse)this.response);
/* 30 */     if (((MentalLotteryInfoResponse)this.response).retCode != 0) {
/* 31 */       return ((MentalLotteryInfoResponse)this.response).retCode;
/*    */     }
/* 33 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\mental\MentalLotteryInfoProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */