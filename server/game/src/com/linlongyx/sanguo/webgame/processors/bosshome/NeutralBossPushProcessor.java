/*    */ package com.linlongyx.sanguo.webgame.processors.bosshome;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.bosshome.NeutralBossPushRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.bosshome.NeutralBossPushResponse;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class NeutralBossPushProcessor
/*    */   extends ProcessorBase<NeutralBossPushRequest, NeutralBossPushResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 16 */     this.response = (ResponseBase)new NeutralBossPushResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, NeutralBossPushRequest request) {
/* 21 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\bosshome\NeutralBossPushProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */