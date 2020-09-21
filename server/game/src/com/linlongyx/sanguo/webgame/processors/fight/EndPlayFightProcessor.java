/*    */ package com.linlongyx.sanguo.webgame.processors.fight;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.fight.EndPlayFightRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.fight.EndPlayFightResponse;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EndPlayFightProcessor
/*    */   extends ProcessorBase<EndPlayFightRequest, EndPlayFightResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 16 */     this.response = (ResponseBase)new EndPlayFightResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, EndPlayFightRequest request) {
/* 21 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\fight\EndPlayFightProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */