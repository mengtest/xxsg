/*    */ package com.linlongyx.sanguo.webgame.processors.aoi;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.aoi.AvatarDieRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.aoi.AvatarDieResponse;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class AvatarDieProcessor
/*    */   extends ProcessorBase<AvatarDieRequest, AvatarDieResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 16 */     this.response = (ResponseBase)new AvatarDieResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, AvatarDieRequest request) {
/* 21 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\aoi\AvatarDieProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */