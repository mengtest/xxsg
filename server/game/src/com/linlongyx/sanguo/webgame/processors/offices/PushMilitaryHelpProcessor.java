/*    */ package com.linlongyx.sanguo.webgame.processors.offices;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.offices.PushMilitaryHelpRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.offices.PushMilitaryHelpResponse;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PushMilitaryHelpProcessor
/*    */   extends ProcessorBase<PushMilitaryHelpRequest, PushMilitaryHelpResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 16 */     this.response = (ResponseBase)new PushMilitaryHelpResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, PushMilitaryHelpRequest request) {
/* 21 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\offices\PushMilitaryHelpProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */