/*    */ package com.linlongyx.sanguo.webgame.processors.common;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.common.AdditionUpdateRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.common.AdditionUpdateResponse;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class AdditionUpdateProcessor
/*    */   extends ProcessorBase<AdditionUpdateRequest, AdditionUpdateResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 16 */     this.response = (ResponseBase)new AdditionUpdateResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, AdditionUpdateRequest request) {
/* 21 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\common\AdditionUpdateProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */