/*    */ package com.linlongyx.sanguo.webgame.processors.login;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.login.OfflineNoticeRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.login.OfflineNoticeResponse;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class OfflineNoticeProcessor
/*    */   extends ProcessorBase<OfflineNoticeRequest, OfflineNoticeResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 16 */     this.response = (ResponseBase)new OfflineNoticeResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, OfflineNoticeRequest request) {
/* 21 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\login\OfflineNoticeProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */