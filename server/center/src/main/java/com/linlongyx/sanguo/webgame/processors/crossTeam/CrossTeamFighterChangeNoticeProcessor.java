/*    */ package com.linlongyx.sanguo.webgame.processors.crossTeam;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.crossTeam.CrossTeamFighterChangeNoticeRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.crossTeam.CrossTeamFighterChangeNoticeResponse;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CrossTeamFighterChangeNoticeProcessor
/*    */   extends ProcessorBase<CrossTeamFighterChangeNoticeRequest, CrossTeamFighterChangeNoticeResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 16 */     this.response = (ResponseBase)new CrossTeamFighterChangeNoticeResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, CrossTeamFighterChangeNoticeRequest request) {
/* 21 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\sanguo\webgame\processors\crossTeam\CrossTeamFighterChangeNoticeProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */