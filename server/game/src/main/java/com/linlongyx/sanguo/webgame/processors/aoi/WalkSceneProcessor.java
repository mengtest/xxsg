/*    */ package com.linlongyx.sanguo.webgame.processors.aoi;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.aoi.WalkSceneRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.aoi.WalkSceneResponse;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class WalkSceneProcessor
/*    */   extends ProcessorBase<WalkSceneRequest, WalkSceneResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 16 */     this.response = (ResponseBase)new WalkSceneResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, WalkSceneRequest request) {
/* 21 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\aoi\WalkSceneProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */