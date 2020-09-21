/*    */ package com.linlongyx.sanguo.webgame.processors.Invitation;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.core.utils.TimeUtil;
/*    */ import com.linlongyx.sanguo.webgame.app.invitation.InvitationComponent;
/*    */ import com.linlongyx.sanguo.webgame.config.parameter.InvitationParameter;
/*    */ import com.linlongyx.sanguo.webgame.constant.ParameterConstant;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.Invitation.NormalInvitationFinishRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.Invitation.NormalInvitationFinishResponse;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class NormalInvitationFinishProcessor
/*    */   extends ProcessorBase<NormalInvitationFinishRequest, NormalInvitationFinishResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 20 */     this.response = (ResponseBase)new NormalInvitationFinishResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, NormalInvitationFinishRequest request) {
/* 25 */     InvitationComponent invitationComponent = (InvitationComponent)playerSession.getPlayer().createIfNotExist(InvitationComponent.class);
/* 26 */     InvitationParameter invitationParameter = (InvitationParameter)ParameterConstant.getParameter(36);
/* 27 */     if (invitationComponent.getNextTime() > TimeUtil.currentTime()) {
/* 28 */       return 15009;
/*    */     }
/* 30 */     if (invitationComponent.getInvitationNum() >= invitationParameter.getDayLimit()) {
/* 31 */       return 15010;
/*    */     }
/* 33 */     if (invitationComponent.getInvitationTotal() >= invitationParameter.getTotalLimit()) {
/* 34 */       return 15010;
/*    */     }
/* 36 */     invitationComponent.setNextTime(TimeUtil.currentTime() + invitationParameter.getInviteCD());
/* 37 */     invitationComponent.setInvitationNum(invitationComponent.getInvitationNum() + 1);
/* 38 */     invitationComponent.setInvitationTotal(invitationComponent.getInvitationTotal() + 1);
/* 39 */     ((NormalInvitationFinishResponse)this.response).cdTime = invitationComponent.getNextTime();
/* 40 */     ((NormalInvitationFinishResponse)this.response).invitationNum = invitationComponent.getInvitationNum();
/* 41 */     ((NormalInvitationFinishResponse)this.response).invitationTotal = invitationComponent.getInvitationTotal();
/* 42 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\Invitation\NormalInvitationFinishProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */