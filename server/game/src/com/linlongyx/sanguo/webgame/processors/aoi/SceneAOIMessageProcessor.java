/*    */ package com.linlongyx.sanguo.webgame.processors.aoi;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.aoi.SceneAOIMessageRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.aoi.SceneAOIMessageResponse;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SceneAOIMessageProcessor
/*    */   extends ProcessorBase<SceneAOIMessageRequest, SceneAOIMessageResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 16 */     this.response = (ResponseBase)new SceneAOIMessageResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, SceneAOIMessageRequest request) {
/* 21 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\aoi\SceneAOIMessageProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */