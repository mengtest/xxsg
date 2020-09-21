/*    */ package com.linlongyx.sanguo.webgame.processors.group;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.app.group.GroupEntity;
/*    */ import com.linlongyx.sanguo.webgame.app.group.GroupMemberComponent;
/*    */ import com.linlongyx.sanguo.webgame.app.group.GroupMemberEntity;
/*    */ import com.linlongyx.sanguo.webgame.app.offices.MilitaryOfficeComponent;
/*    */ import com.linlongyx.sanguo.webgame.constant.FunctionOpenConstant;
/*    */ import com.linlongyx.sanguo.webgame.constant.MContext;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.group.GroupInfoRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.group.GroupInfoResponse;
/*    */ import com.linlongyx.sanguo.webgame.service.GroupService;
/*    */ import com.linlongyx.sanguo.webgame.service.LookUpService;
/*    */ 
/*    */ public class GroupInfoProcessor
/*    */   extends ProcessorBase<GroupInfoRequest, GroupInfoResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 22 */     this.response = (ResponseBase)new GroupInfoResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, GroupInfoRequest request) {
/* 27 */     if (!FunctionOpenConstant.isFunctionOpen(playerSession, 11)) {
/* 28 */       return 10061;
/*    */     }
/* 30 */     GroupMemberComponent groupMemberComponent = (GroupMemberComponent)playerSession.getPlayer().createIfNotExist(GroupMemberComponent.class);
/* 31 */     if (0L == groupMemberComponent.getId()) {
/* 32 */       return 0;
/*    */     }
/* 34 */     GroupService groupService = (GroupService)MContext.getBean("groupService");
/* 35 */     GroupEntity groupEntity = groupService.getGroupEntity(groupMemberComponent.getId());
/* 36 */     if (null == groupEntity) {
/* 37 */       return 0;
/*    */     }
/* 39 */     GroupMemberEntity groupMemberEntity = (GroupMemberEntity)groupMemberComponent.getEntity();
/*    */     
/* 41 */     if (groupMemberEntity.getPosition() == 1 && !groupEntity.getVices().isEmpty()) {
/* 42 */       boolean result = groupEntity.getVices().removeIf(playerId -> {
/*    */             GroupMemberComponent tobmComponent = (GroupMemberComponent)LookUpService.getComponent(playerId.longValue(), GroupMemberComponent.class);
/* 44 */             return (null == tobmComponent || tobmComponent.getPosition() != 2);
/*    */           });
/*    */ 
/*    */       
/* 48 */       if (result)
/* 49 */         groupService.updateVices(groupMemberEntity.getId()); 
/*    */     } 
/* 51 */     ((GroupInfoResponse)this.response).position = groupMemberComponent.getPosition();
/* 52 */     ((GroupInfoResponse)this.response).data = GroupUtil.getGroupData(groupEntity);
/* 53 */     ((GroupInfoResponse)this.response).totalOffer = groupMemberComponent.getTotalOffer();
/* 54 */     MilitaryOfficeComponent component = (MilitaryOfficeComponent)playerSession.getPlayer().createIfNotExist(MilitaryOfficeComponent.class);
/* 55 */     ((GroupInfoResponse)this.response).helpTimes = component.getOfficeHelpTimes();
/* 56 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\group\GroupInfoProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */