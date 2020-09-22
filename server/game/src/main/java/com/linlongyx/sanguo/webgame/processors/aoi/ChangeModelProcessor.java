/*    */ package com.linlongyx.sanguo.webgame.processors.aoi;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.aoi.ChangeModelRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.aoi.ChangeModelResponse;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ChangeModelProcessor
/*    */   extends ProcessorBase<ChangeModelRequest, ChangeModelResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 16 */     this.response = (ResponseBase)new ChangeModelResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, ChangeModelRequest request) {
/* 21 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\aoi\ChangeModelProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */