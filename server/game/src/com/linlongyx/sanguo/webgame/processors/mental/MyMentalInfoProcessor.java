/*    */ package com.linlongyx.sanguo.webgame.processors.mental;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.app.mental.MentalComponent;
/*    */ import com.linlongyx.sanguo.webgame.constant.FunctionOpenConstant;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.mental.MyMentalInfoRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.mental.MyMentalInfoResponse;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class MyMentalInfoProcessor
/*    */   extends ProcessorBase<MyMentalInfoRequest, MyMentalInfoResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 19 */     this.response = (ResponseBase)new MyMentalInfoResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, MyMentalInfoRequest request) {
/* 24 */     if (!FunctionOpenConstant.isFunctionOpen(playerSession, 58))
/* 25 */       return 10061; 
/* 26 */     MentalComponent mentalComponent = (MentalComponent)playerSession.getPlayer().createIfNotExist(MentalComponent.class);
/*    */     
/* 28 */     ((MyMentalInfoResponse)this.response).retCode = mentalComponent.getMyMentalInfo((MyMentalInfoResponse)this.response);
/* 29 */     if (((MyMentalInfoResponse)this.response).retCode != 0) {
/* 30 */       return ((MyMentalInfoResponse)this.response).retCode;
/*    */     }
/* 32 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\mental\MyMentalInfoProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */