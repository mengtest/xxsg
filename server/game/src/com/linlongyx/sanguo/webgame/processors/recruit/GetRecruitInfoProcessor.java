/*    */ package com.linlongyx.sanguo.webgame.processors.recruit;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.app.invitation.InvitationComponent;
/*    */ import com.linlongyx.sanguo.webgame.app.recruit.RecruitComponent;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.recruit.GetRecruitInfoRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.recruit.GetRecruitInfoResponse;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class GetRecruitInfoProcessor
/*    */   extends ProcessorBase<GetRecruitInfoRequest, GetRecruitInfoResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 18 */     this.response = (ResponseBase)new GetRecruitInfoResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, GetRecruitInfoRequest request) {
/* 23 */     RecruitComponent recruitComponent = (RecruitComponent)playerSession.getPlayer().createIfNotExist(RecruitComponent.class);
/* 24 */     ((GetRecruitInfoResponse)this.response).freeCount = recruitComponent.getCurrFreeCount();
/* 25 */     ((GetRecruitInfoResponse)this.response).nextFreeTimes = recruitComponent.getNextFreeTime();
/* 26 */     ((GetRecruitInfoResponse)this.response).times = recruitComponent.getCcyRecruitTimes();
/* 27 */     InvitationComponent invitationComponent = (InvitationComponent)playerSession.getPlayer().createIfNotExist(InvitationComponent.class);
/* 28 */     ((GetRecruitInfoResponse)this.response).recruitInvitation = (invitationComponent.isRecruitInvitation() == true) ? 1 : 0;
/* 29 */     ((GetRecruitInfoResponse)this.response).tenCCYRecruit = recruitComponent.getTenCCYRecruit();
/* 30 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\recruit\GetRecruitInfoProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */