/*    */ package com.linlongyx.sanguo.webgame.processors.cross;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.cross.CrossOfflineNoticeRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.cross.CrossOfflineNoticeResponse;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CrossOfflineNoticeProcessor
/*    */   extends ProcessorBase<CrossOfflineNoticeRequest, CrossOfflineNoticeResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 16 */     this.response = (ResponseBase)new CrossOfflineNoticeResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, CrossOfflineNoticeRequest request) {
/* 21 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\sanguo\webgame\processors\cross\CrossOfflineNoticeProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */