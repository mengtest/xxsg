/*    */ package com.linlongyx.sanguo.webgame.processors.aoi;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.aoi.UpdateSceneDynDataRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.aoi.UpdateSceneDynDataResponse;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class UpdateSceneDynDataProcessor
/*    */   extends ProcessorBase<UpdateSceneDynDataRequest, UpdateSceneDynDataResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 16 */     this.response = (ResponseBase)new UpdateSceneDynDataResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, UpdateSceneDynDataRequest request) {
/* 21 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\aoi\UpdateSceneDynDataProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */