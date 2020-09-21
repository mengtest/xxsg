/*    */ package com.linlongyx.sanguo.webgame.processors.secreti;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.app.secreti.SecretiComponent;
/*    */ import com.linlongyx.sanguo.webgame.constant.FunctionOpenConstant;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.secreti.SecretiInfoRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.secreti.SecretiInfoResponse;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SecretiInfoProcessor
/*    */   extends ProcessorBase<SecretiInfoRequest, SecretiInfoResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 19 */     this.response = (ResponseBase)new SecretiInfoResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, SecretiInfoRequest request) {
/* 24 */     if (!FunctionOpenConstant.isFunctionOpen(playerSession, 44))
/* 25 */       return 10061; 
/* 26 */     SecretiComponent secretiComponent = (SecretiComponent)playerSession.getPlayer().createIfNotExist(SecretiComponent.class);
/* 27 */     ((SecretiInfoResponse)this.response).insList.addAll(secretiComponent.getInsMap().keySet());
/* 28 */     ((SecretiInfoResponse)this.response).rewardTimes = secretiComponent.getRewardTimes();
/* 29 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\secreti\SecretiInfoProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */