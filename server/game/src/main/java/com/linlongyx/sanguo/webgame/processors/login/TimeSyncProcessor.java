/*    */ package com.linlongyx.sanguo.webgame.processors.login;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.core.utils.TimeUtil;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.login.TimeSyncRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.login.TimeSyncResponse;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class TimeSyncProcessor
/*    */   extends ProcessorBase<TimeSyncRequest, TimeSyncResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 17 */     this.response = (ResponseBase)new TimeSyncResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, TimeSyncRequest request) {
/* 22 */     ((TimeSyncResponse)this.response).timestamp = TimeUtil.currentTime();
/* 23 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\login\TimeSyncProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */