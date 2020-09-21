/*    */ package com.linlongyx.sanguo.webgame.processors.cross;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.cross.CrossLogoutRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.cross.CrossLogoutResponse;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CrossLogoutProcessor
/*    */   extends ProcessorBase<CrossLogoutRequest, CrossLogoutResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 16 */     this.response = (ResponseBase)new CrossLogoutResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, CrossLogoutRequest request) {
/* 21 */     playerSession.getPlayer().logout();
/* 22 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\sanguo\webgame\processors\cross\CrossLogoutProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */