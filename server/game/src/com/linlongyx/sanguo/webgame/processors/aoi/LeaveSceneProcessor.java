/*    */ package com.linlongyx.sanguo.webgame.processors.aoi;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.aoi.LeaveSceneRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.aoi.LeaveSceneResponse;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class LeaveSceneProcessor
/*    */   extends ProcessorBase<LeaveSceneRequest, LeaveSceneResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 16 */     this.response = (ResponseBase)new LeaveSceneResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, LeaveSceneRequest request) {
/* 21 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\aoi\LeaveSceneProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */