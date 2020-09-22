/*    */ package com.linlongyx.sanguo.webgame.processors.consume;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.app.consume.ConsumeRebateComponent;
/*    */ import com.linlongyx.sanguo.webgame.app.consume.ConsumeRebateEntity;
/*    */ import com.linlongyx.sanguo.webgame.config.parameter.ConsumeRebateParameter;
/*    */ import com.linlongyx.sanguo.webgame.constant.FunctionOpenConstant;
/*    */ import com.linlongyx.sanguo.webgame.constant.ParameterConstant;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.consume.ConsumeRebateInfoRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.consume.ConsumeRebateInfoResponse;
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ConsumeRebateInfoProcessor
/*    */   extends ProcessorBase<ConsumeRebateInfoRequest, ConsumeRebateInfoResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 24 */     this.response = (ResponseBase)new ConsumeRebateInfoResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, ConsumeRebateInfoRequest request) {
/* 29 */     if (!FunctionOpenConstant.isFunctionOpen(playerSession, 52))
/* 30 */       return 10061; 
/* 31 */     ConsumeRebateComponent consumeRebateComponent = (ConsumeRebateComponent)playerSession.getPlayer().createIfNotExist(ConsumeRebateComponent.class);
/* 32 */     ConsumeRebateParameter consumeRebateParameter = (ConsumeRebateParameter)ParameterConstant.getParameter(52);
/* 33 */     if (!consumeRebateParameter.isActOpen(request.actId)) {
/* 34 */       return 12702;
/*    */     }
/* 36 */     ConsumeRebateEntity consumeRebateEntity = consumeRebateComponent.getEntity(request.actId);
/* 37 */     ((ConsumeRebateInfoResponse)this.response).actId = request.actId;
/* 38 */     ((ConsumeRebateInfoResponse)this.response).takeConsume = consumeRebateEntity.getTakeConsume();
/* 39 */     ((ConsumeRebateInfoResponse)this.response).postureConsume = consumeRebateEntity.getPostureConsume();
/* 40 */     ((ConsumeRebateInfoResponse)this.response).takeReward = new ArrayList(consumeRebateEntity.getTakeReward().keySet());
/* 41 */     ((ConsumeRebateInfoResponse)this.response).postureReward = new ArrayList(consumeRebateEntity.getPostureReward().keySet());
/* 42 */     ((ConsumeRebateInfoResponse)this.response).zhenFaConsume = consumeRebateEntity.getZhenFaConsume();
/* 43 */     ((ConsumeRebateInfoResponse)this.response).zhenFaReward = new ArrayList(consumeRebateEntity.getZhenFaReward().keySet());
/* 44 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\consume\ConsumeRebateInfoProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */