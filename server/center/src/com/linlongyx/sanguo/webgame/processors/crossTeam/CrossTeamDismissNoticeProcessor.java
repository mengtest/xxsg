/*    */ package com.linlongyx.sanguo.webgame.processors.crossTeam;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.crossTeam.CrossTeamDismissNoticeRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.crossTeam.CrossTeamDismissNoticeResponse;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CrossTeamDismissNoticeProcessor
/*    */   extends ProcessorBase<CrossTeamDismissNoticeRequest, CrossTeamDismissNoticeResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 16 */     this.response = (ResponseBase)new CrossTeamDismissNoticeResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, CrossTeamDismissNoticeRequest request) {
/* 21 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\sanguo\webgame\processors\crossTeam\CrossTeamDismissNoticeProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */