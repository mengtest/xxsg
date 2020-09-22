/*    */ package com.linlongyx.sanguo.webgame.processors.turnplate;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.turnplate.TurnplateNoticeRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.turnplate.TurnplateNoticeResponse;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class TurnplateNoticeProcessor
/*    */   extends ProcessorBase<TurnplateNoticeRequest, TurnplateNoticeResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 16 */     this.response = (ResponseBase)new TurnplateNoticeResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, TurnplateNoticeRequest request) {
/* 21 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\turnplate\TurnplateNoticeProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */