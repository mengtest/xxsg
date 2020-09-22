/*    */ package com.linlongyx.sanguo.webgame.processors.limit;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.limit.LimitActRedRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.limit.LimitActRedResponse;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class LimitActRedProcessor
/*    */   extends ProcessorBase<LimitActRedRequest, LimitActRedResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 16 */     this.response = (ResponseBase)new LimitActRedResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, LimitActRedRequest request) {
/* 21 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\limit\LimitActRedProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */