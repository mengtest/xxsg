/*    */ package com.linlongyx.sanguo.webgame.processors.aoi;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.aoi.RemoveAvatarRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.aoi.RemoveAvatarResponse;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class RemoveAvatarProcessor
/*    */   extends ProcessorBase<RemoveAvatarRequest, RemoveAvatarResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 16 */     this.response = (ResponseBase)new RemoveAvatarResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, RemoveAvatarRequest request) {
/* 21 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\aoi\RemoveAvatarProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */