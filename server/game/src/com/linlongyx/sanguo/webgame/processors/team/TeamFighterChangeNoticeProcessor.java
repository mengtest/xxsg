/*    */ package com.linlongyx.sanguo.webgame.processors.team;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.team.TeamFighterChangeNoticeRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.team.TeamFighterChangeNoticeResponse;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class TeamFighterChangeNoticeProcessor
/*    */   extends ProcessorBase<TeamFighterChangeNoticeRequest, TeamFighterChangeNoticeResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 16 */     this.response = (ResponseBase)new TeamFighterChangeNoticeResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, TeamFighterChangeNoticeRequest request) {
/* 21 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\team\TeamFighterChangeNoticeProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */