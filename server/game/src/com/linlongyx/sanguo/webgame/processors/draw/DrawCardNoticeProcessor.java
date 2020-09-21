/*    */ package com.linlongyx.sanguo.webgame.processors.draw;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.config.parameter.DrawCardParameter;
/*    */ import com.linlongyx.sanguo.webgame.constant.ParameterConstant;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.draw.DrawCardNoticeRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.draw.DrawCardNoticeResponse;
/*    */ import java.util.List;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class DrawCardNoticeProcessor
/*    */   extends ProcessorBase<DrawCardNoticeRequest, DrawCardNoticeResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 20 */     this.response = (ResponseBase)new DrawCardNoticeResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, DrawCardNoticeRequest request) {
/* 25 */     DrawCardParameter drawCardParameter = (DrawCardParameter)ParameterConstant.getParameter(48);
/* 26 */     List<Integer> list = drawCardParameter.getActId(true);
/* 27 */     ((DrawCardNoticeResponse)this.response).list.addAll(list);
/* 28 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\draw\DrawCardNoticeProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */