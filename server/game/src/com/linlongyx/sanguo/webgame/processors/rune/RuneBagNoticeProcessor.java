/*    */ package com.linlongyx.sanguo.webgame.processors.rune;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.rune.RuneBagNoticeRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.rune.RuneBagNoticeResponse;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class RuneBagNoticeProcessor
/*    */   extends ProcessorBase<RuneBagNoticeRequest, RuneBagNoticeResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 16 */     this.response = (ResponseBase)new RuneBagNoticeResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, RuneBagNoticeRequest request) {
/* 21 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\rune\RuneBagNoticeProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */