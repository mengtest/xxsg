/*    */ package com.linlongyx.sanguo.webgame.processors.team;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.common.team.TeamUtil;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.team.SendFighterRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.team.SendFighterResponse;
/*    */ 
/*    */ 
/*    */ public class SendFighterProcessor
/*    */   extends ProcessorBase<SendFighterRequest, SendFighterResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 16 */     this.response = (ResponseBase)new SendFighterResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, SendFighterRequest request) {
/* 21 */     return TeamUtil.changeFighters(playerSession.getPlayer().getPlayerId(), request.teamId, request.fighterList);
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\team\SendFighterProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */