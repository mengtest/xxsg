/*    */ package com.linlongyx.sanguo.webgame.processors.group;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayer;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.core.utils.ChatUtil;
/*    */ import com.linlongyx.core.utils.LogUtils;
/*    */ import com.linlongyx.sanguo.webgame.app.bag.BagComponent;
/*    */ import com.linlongyx.sanguo.webgame.app.group.GroupEntity;
/*    */ import com.linlongyx.sanguo.webgame.app.group.GroupMemberComponent;
/*    */ import com.linlongyx.sanguo.webgame.config.parameter.GroupParameter;
/*    */ import com.linlongyx.sanguo.webgame.constant.FunctionOpenConstant;
/*    */ import com.linlongyx.sanguo.webgame.constant.MContext;
/*    */ import com.linlongyx.sanguo.webgame.constant.ParameterConstant;
/*    */ import com.linlongyx.sanguo.webgame.constant.ResourceEvent;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.group.GroupChangeNameRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.group.GroupChangeNameResponse;
/*    */ import com.linlongyx.sanguo.webgame.service.GroupService;
/*    */ 
/*    */ public class GroupChangeNameProcessor extends ProcessorBase<GroupChangeNameRequest, GroupChangeNameResponse> {
/*    */   protected void initResponse() {
/* 24 */     this.response = (ResponseBase)new GroupChangeNameResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, GroupChangeNameRequest request) {
/* 29 */     if (!FunctionOpenConstant.isFunctionOpen(playerSession, 11)) {
/* 30 */       return 10061;
/*    */     }
/* 32 */     IPlayer iPlayer = playerSession.getPlayer();
/*    */     
/* 34 */     GroupParameter groupParameter = (GroupParameter)ParameterConstant.getParameter(11);
/*    */     
/* 36 */     BagComponent bagComponent = (BagComponent)iPlayer.createIfNotExist(BagComponent.class);
/* 37 */     if (!bagComponent.check(groupParameter.getChangeNameItemId(), 1L)) {
/* 38 */       return 10050;
/*    */     }
/* 40 */     GroupMemberComponent groupMemberComponent = (GroupMemberComponent)playerSession.getPlayer().createIfNotExist(GroupMemberComponent.class);
/* 41 */     if (0L == groupMemberComponent.getId()) {
/* 42 */       return 11101;
/*    */     }
/* 44 */     GroupService groupService = (GroupService)MContext.getBean("groupService");
/* 45 */     GroupEntity groupEntity = groupService.getGroupEntity(groupMemberComponent.getId());
/* 46 */     if (null == groupEntity) {
/* 47 */       return 11101;
/*    */     }
/*    */ 
/*    */     
/* 51 */     String reg = "[^(a-zA-Z0-9\\u4e00-\\u9fa5\\u00C0-\\u1EF9\\u0020)]";
/* 52 */     String groupName = request.groupName.replaceAll(reg, "");
/*    */ 
/*    */     
/* 55 */     if (!ChatUtil.isLegalName(groupName)) {
/* 56 */       return 11107;
/*    */     }
/*    */     
/* 59 */     if (groupService.checkName(groupName)) {
/* 60 */       return 11108;
/*    */     }
/*    */ 
/*    */     
/* 64 */     if (groupEntity.getLeader() != iPlayer.getPlayerId()) {
/* 65 */       return 11110;
/*    */     }
/* 67 */     bagComponent.deleteItem(groupParameter.getChangeNameItemId(), 1, ResourceEvent.ChangeGroupName, true);
/*    */     
/* 69 */     String oldName = groupEntity.getGroupName();
/* 70 */     groupEntity.setGroupName(groupName);
/* 71 */     groupService.updateGroupName(groupEntity.getId());
/* 72 */     LogUtils.errorLog(new Object[] { "ChangeGroupName:", Integer.valueOf(groupParameter.getChangeNameItemId()), " blocId", Long.valueOf(groupEntity.getId()), "old", oldName, "new", groupName });
/*    */     
/* 74 */     ((GroupChangeNameResponse)this.response).groupName = groupName;
/* 75 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\group\GroupChangeNameProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */