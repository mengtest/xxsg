/*    */ package com.linlongyx.sanguo.webgame.processors.bosshome;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.bosshome.WorldPushRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.bosshome.WorldPushResponse;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class WorldPushProcessor
/*    */   extends ProcessorBase<WorldPushRequest, WorldPushResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 16 */     this.response = (ResponseBase)new WorldPushResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, WorldPushRequest request) {
/* 21 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\bosshome\WorldPushProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */