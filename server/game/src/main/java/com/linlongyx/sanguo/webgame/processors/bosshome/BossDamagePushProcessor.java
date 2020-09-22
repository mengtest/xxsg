/*    */ package com.linlongyx.sanguo.webgame.processors.bosshome;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.bosshome.BossDamagePushRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.bosshome.BossDamagePushResponse;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BossDamagePushProcessor
/*    */   extends ProcessorBase<BossDamagePushRequest, BossDamagePushResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 16 */     this.response = (ResponseBase)new BossDamagePushResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, BossDamagePushRequest request) {
/* 21 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\bosshome\BossDamagePushProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */