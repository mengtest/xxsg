/*    */ package com.linlongyx.sanguo.webgame.processors.cross;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.cross.CrossBattleResourceNoticeRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.cross.CrossBattleResourceNoticeResponse;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CrossBattleResourceNoticeProcessor
/*    */   extends ProcessorBase<CrossBattleResourceNoticeRequest, CrossBattleResourceNoticeResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 16 */     this.response = (ResponseBase)new CrossBattleResourceNoticeResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, CrossBattleResourceNoticeRequest request) {
/* 21 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\sanguo\webgame\processors\cross\CrossBattleResourceNoticeProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */