/*    */ package com.linlongyx.sanguo.webgame.processors.bag;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.bag.BagDropRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.bag.BagDropResponse;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BagDropProcessor
/*    */   extends ProcessorBase<BagDropRequest, BagDropResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 16 */     this.response = (ResponseBase)new BagDropResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, BagDropRequest request) {
/* 21 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\bag\BagDropProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */