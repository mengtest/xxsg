/*    */ package com.linlongyx.sanguo.webgame.processors.crossTeam;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.crossTeam.CrossSendFighterRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.crossTeam.CrossSendFighterResponse;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CrossSendFighterProcessor
/*    */   extends ProcessorBase<CrossSendFighterRequest, CrossSendFighterResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 16 */     this.response = (ResponseBase)new CrossSendFighterResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, CrossSendFighterRequest request) {
/* 21 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\sanguo\webgame\processors\crossTeam\CrossSendFighterProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */