/*    */ package com.linlongyx.sanguo.webgame.processors.secreti;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.app.secreti.SecretiComponent;
/*    */ import com.linlongyx.sanguo.webgame.constant.FunctionOpenConstant;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.secreti.SecretiRewardInfoRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.secreti.SecretiRewardInfoResponse;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.IntIntValue;
/*    */ import java.util.Map;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SecretiRewardInfoProcessor
/*    */   extends ProcessorBase<SecretiRewardInfoRequest, SecretiRewardInfoResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 22 */     this.response = (ResponseBase)new SecretiRewardInfoResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, SecretiRewardInfoRequest request) {
/* 27 */     if (!FunctionOpenConstant.isFunctionOpen(playerSession, 44))
/* 28 */       return 10061; 
/* 29 */     SecretiComponent secretiComponent = (SecretiComponent)playerSession.getPlayer().createIfNotExist(SecretiComponent.class);
/* 30 */     for (Map.Entry<Integer, Integer> kv : (Iterable<Map.Entry<Integer, Integer>>)secretiComponent.getRewards().entrySet()) {
/* 31 */       IntIntValue intIntValue = new IntIntValue();
/* 32 */       intIntValue.key = ((Integer)kv.getKey()).intValue();
/* 33 */       intIntValue.value = ((Integer)kv.getValue()).intValue();
/* 34 */       ((SecretiRewardInfoResponse)this.response).rewardList.add(intIntValue);
/*    */     } 
/* 36 */     ((SecretiRewardInfoResponse)this.response).total = secretiComponent.getTotal();
/* 37 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\secreti\SecretiRewardInfoProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */