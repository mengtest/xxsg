/*    */ package com.linlongyx.sanguo.webgame.processors.crossTeam;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.crossTeam.CrossTeamPlayerDataNoticeRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.crossTeam.CrossTeamPlayerDataNoticeResponse;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CrossTeamPlayerDataNoticeProcessor
/*    */   extends ProcessorBase<CrossTeamPlayerDataNoticeRequest, CrossTeamPlayerDataNoticeResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 16 */     this.response = (ResponseBase)new CrossTeamPlayerDataNoticeResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, CrossTeamPlayerDataNoticeRequest request) {
/* 21 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\sanguo\webgame\processors\crossTeam\CrossTeamPlayerDataNoticeProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */