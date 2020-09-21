/*    */ package com.linlongyx.sanguo.webgame.processors.offices;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.core.utils.TimeUtil;
/*    */ import com.linlongyx.sanguo.webgame.app.group.GroupEntity;
/*    */ import com.linlongyx.sanguo.webgame.app.group.GroupMemberComponent;
/*    */ import com.linlongyx.sanguo.webgame.app.offices.MilitaryOfficeComponent;
/*    */ import com.linlongyx.sanguo.webgame.app.player.PlayerComponent;
/*    */ import com.linlongyx.sanguo.webgame.config.bean.MilitaryBean;
/*    */ import com.linlongyx.sanguo.webgame.constant.FunctionOpenConstant;
/*    */ import com.linlongyx.sanguo.webgame.constant.MContext;
/*    */ import com.linlongyx.sanguo.webgame.constant.RedNoticeConstant;
/*    */ import com.linlongyx.sanguo.webgame.processors.login.VipUtil;
/*    */ import com.linlongyx.sanguo.webgame.processors.player.PlayerUtil;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.offices.MilitaryAskForHelpRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.offices.MilitaryAskForHelpResponse;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.KeyValue;
/*    */ import com.linlongyx.sanguo.webgame.service.GroupService;
/*    */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*    */ 
/*    */ public class MilitaryAskForHelpProcessor
/*    */   extends ProcessorBase<MilitaryAskForHelpRequest, MilitaryAskForHelpResponse> {
/*    */   protected void initResponse() {
/* 27 */     this.response = (ResponseBase)new MilitaryAskForHelpResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, MilitaryAskForHelpRequest request) {
/* 32 */     if (!FunctionOpenConstant.isFunctionOpen(playerSession, 80))
/* 33 */       return 10061; 
/* 34 */     PlayerComponent playerComponent = (PlayerComponent)playerSession.getPlayer().createIfNotExist(PlayerComponent.class);
/* 35 */     MilitaryOfficeComponent militaryOfficeComponent = (MilitaryOfficeComponent)playerSession.getPlayer().createIfNotExist(MilitaryOfficeComponent.class);
/* 36 */     if (militaryOfficeComponent.getCurrJobId() == 0 || request.officeId != militaryOfficeComponent.getCurrJobId()) {
/* 37 */       return 22801;
/*    */     }
/* 39 */     GroupMemberComponent groupMemberComponent = (GroupMemberComponent)playerSession.getPlayer().createIfNotExist(GroupMemberComponent.class);
/* 40 */     if (0L == groupMemberComponent.getId()) {
/* 41 */       return 11101;
/*    */     }
/* 43 */     GroupService groupService = (GroupService)MContext.getBean("groupService");
/* 44 */     GroupEntity groupEntity = groupService.getGroupEntity(groupMemberComponent.getId());
/* 45 */     if (null == groupEntity) {
/* 46 */       return 11101;
/*    */     }
/* 48 */     if (militaryOfficeComponent.getStatus() == 1) {
/* 49 */       return 22804;
/*    */     }
/* 51 */     MilitaryBean militaryBean = (MilitaryBean)JsonTableService.getJsonData(request.officeId, MilitaryBean.class);
/* 52 */     if (null == militaryBean) {
/* 53 */       return 10006;
/*    */     }
/* 55 */     int freeTime = VipUtil.getNum(playerComponent.getVip(), 19);
/* 56 */     if (TimeUtil.currentTime() > militaryOfficeComponent.getEndTime() - freeTime) {
/* 57 */       return 22803;
/*    */     }
/* 59 */     militaryOfficeComponent.setStatus(1);
/*    */     
/* 61 */     int level = ((Integer)militaryOfficeComponent.getOfficeList().getOrDefault(Integer.valueOf(request.officeId), Integer.valueOf(0))).intValue();
/* 62 */     KeyValue keyValue = new KeyValue();
/* 63 */     keyValue.key = request.officeId;
/* 64 */     keyValue.value = level;
/* 65 */     keyValue.valueStr = "0";
/* 66 */     groupService.addOffices(playerSession, groupEntity, playerSession.getPlayer().getPlayerId(), keyValue, militaryBean.getName());
/* 67 */     PlayerUtil.sendRedNotice(Long.valueOf(playerSession.getPlayer().getPlayerId()), RedNoticeConstant.MilitaryAskHelp.getSys(), 0);
/* 68 */     ((MilitaryAskForHelpResponse)this.response).status = militaryOfficeComponent.getStatus();
/* 69 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\offices\MilitaryAskForHelpProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */