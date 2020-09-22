/*    */ package com.linlongyx.sanguo.webgame.processors.cat;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.config.parameter.FortuneCatParameter;
/*    */ import com.linlongyx.sanguo.webgame.constant.ParameterConstant;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.cat.FortuneCatNoticeRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.cat.FortuneCatNoticeResponse;
/*    */ import java.util.List;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class FortuneCatNoticeProcessor
/*    */   extends ProcessorBase<FortuneCatNoticeRequest, FortuneCatNoticeResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 20 */     this.response = (ResponseBase)new FortuneCatNoticeResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, FortuneCatNoticeRequest request) {
/* 25 */     FortuneCatParameter fortuneCatParameter = (FortuneCatParameter)ParameterConstant.getParameter(80);
/* 26 */     List<Integer> list = fortuneCatParameter.getActId(true);
/* 27 */     ((FortuneCatNoticeResponse)this.response).list.addAll(list);
/* 28 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\cat\FortuneCatNoticeProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */