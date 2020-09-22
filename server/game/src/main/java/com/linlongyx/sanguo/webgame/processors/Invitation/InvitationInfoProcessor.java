/*    */ package com.linlongyx.sanguo.webgame.processors.Invitation;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.app.invitation.InvitationComponent;
/*    */ import com.linlongyx.sanguo.webgame.config.parameter.InvitationParameter;
/*    */ import com.linlongyx.sanguo.webgame.constant.ParameterConstant;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.Invitation.InvitationInfoRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.Invitation.InvitationInfoResponse;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.WxPlayerInfo;
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class InvitationInfoProcessor
/*    */   extends ProcessorBase<InvitationInfoRequest, InvitationInfoResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 22 */     this.response = (ResponseBase)new InvitationInfoResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, InvitationInfoRequest request) {
/* 27 */     InvitationComponent invitationComponent = (InvitationComponent)playerSession.getPlayer().createIfNotExist(InvitationComponent.class);
/* 28 */     InvitationParameter invitationParameter = (InvitationParameter)ParameterConstant.getParameter(36);
/* 29 */     ((InvitationInfoResponse)this.response).recruitInvitation = invitationComponent.isRecruitInvitation() ? 1 : 0;
/* 30 */     int num = 0;
/* 31 */     for (WxPlayerInfo wxPlayerInfo : invitationComponent.getWxInfo()) {
/* 32 */       if (wxPlayerInfo.level >= invitationParameter.getLevelCount() && !invitationComponent.getNumMap().containsKey(Long.valueOf(wxPlayerInfo.playerId))) {
/* 33 */         ((InvitationInfoResponse)this.response).wxLevelInfo.add(wxPlayerInfo);
/*    */       }
/* 35 */       if (wxPlayerInfo.level >= invitationParameter.getInviteLevelNum()) {
/* 36 */         num++;
/*    */       }
/* 38 */       if (wxPlayerInfo.totalCharge > 0L) {
/* 39 */         ((InvitationInfoResponse)this.response).wxCharge.add(wxPlayerInfo);
/*    */       }
/*    */     } 
/* 42 */     ((InvitationInfoResponse)this.response).num = num;
/* 43 */     ((InvitationInfoResponse)this.response).normalRewards.addAll(invitationComponent.getNormalRewards());
/* 44 */     ((InvitationInfoResponse)this.response).invitationNum = invitationComponent.getInvitationNum();
/* 45 */     ((InvitationInfoResponse)this.response).invitationTotal = invitationComponent.getInvitationTotal();
/* 46 */     ((InvitationInfoResponse)this.response).numRewards = new ArrayList(invitationComponent.getLevelNumMap().keySet());
/* 47 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\Invitation\InvitationInfoProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */