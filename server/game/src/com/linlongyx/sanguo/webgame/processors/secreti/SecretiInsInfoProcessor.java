/*    */ package com.linlongyx.sanguo.webgame.processors.secreti;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.app.secreti.SecretiComponent;
/*    */ import com.linlongyx.sanguo.webgame.constant.FunctionOpenConstant;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.secreti.SecretiInsInfoRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.secreti.SecretiInsInfoResponse;
/*    */ import java.util.Collection;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SecretiInsInfoProcessor
/*    */   extends ProcessorBase<SecretiInsInfoRequest, SecretiInsInfoResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 19 */     this.response = (ResponseBase)new SecretiInsInfoResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, SecretiInsInfoRequest request) {
/* 24 */     if (!FunctionOpenConstant.isFunctionOpen(playerSession, 44))
/* 25 */       return 10061; 
/* 26 */     SecretiComponent secretiComponent = (SecretiComponent)playerSession.getPlayer().createIfNotExist(SecretiComponent.class);
/* 27 */     if (secretiComponent.getInsMap().containsKey(Integer.valueOf(request.insId))) {
/* 28 */       ((SecretiInsInfoResponse)this.response).subInsList.addAll((Collection)secretiComponent.getInsMap().get(Integer.valueOf(request.insId)));
/*    */     }
/* 30 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\secreti\SecretiInsInfoProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */