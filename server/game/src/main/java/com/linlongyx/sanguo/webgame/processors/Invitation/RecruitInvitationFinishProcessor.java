/*    */ package com.linlongyx.sanguo.webgame.processors.Invitation;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.core.utils.TimeUtil;
/*    */ import com.linlongyx.sanguo.webgame.app.invitation.InvitationComponent;
/*    */ import com.linlongyx.sanguo.webgame.app.recruit.RecruitComponent;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.Invitation.RecruitInvitationFinishRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.Invitation.RecruitInvitationFinishResponse;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class RecruitInvitationFinishProcessor
/*    */   extends ProcessorBase<RecruitInvitationFinishRequest, RecruitInvitationFinishResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 19 */     this.response = (ResponseBase)new RecruitInvitationFinishResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, RecruitInvitationFinishRequest request) {
/* 24 */     InvitationComponent invitationComponent = (InvitationComponent)playerSession.getPlayer().createIfNotExist(InvitationComponent.class);
/* 25 */     if (invitationComponent.isRecruitInvitation()) {
/* 26 */       return 15010;
/*    */     }
/* 28 */     RecruitComponent recruitComponent = (RecruitComponent)playerSession.getPlayer().createIfNotExist(RecruitComponent.class);
/* 29 */     if (recruitComponent.getNextFreeTime() < TimeUtil.currentTime()) {
/* 30 */       return 15011;
/*    */     }
/* 32 */     invitationComponent.setRecruitInvitation(true);
/* 33 */     recruitComponent.setNextFreeTime(TimeUtil.currentTime());
/* 34 */     ((RecruitInvitationFinishResponse)this.response).recruitInvitation = invitationComponent.isRecruitInvitation() ? 1 : 0;
/* 35 */     ((RecruitInvitationFinishResponse)this.response).nextFreeTime = recruitComponent.getNextFreeTime();
/* 36 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\Invitation\RecruitInvitationFinishProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */