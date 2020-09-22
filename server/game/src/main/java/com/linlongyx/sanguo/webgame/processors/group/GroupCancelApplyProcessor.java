/*    */ package com.linlongyx.sanguo.webgame.processors.group;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.app.group.GroupEntity;
/*    */ import com.linlongyx.sanguo.webgame.app.group.GroupMemberComponent;
/*    */ import com.linlongyx.sanguo.webgame.constant.MContext;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.group.GroupCancelApplyRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.group.GroupCancelApplyResponse;
/*    */ import com.linlongyx.sanguo.webgame.service.GroupService;
/*    */ import java.util.Set;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class GroupCancelApplyProcessor
/*    */   extends ProcessorBase<GroupCancelApplyRequest, GroupCancelApplyResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 22 */     this.response = (ResponseBase)new GroupCancelApplyResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, GroupCancelApplyRequest request) {
/* 27 */     long id = request.id;
/* 28 */     GroupMemberComponent groupMemberComponent = (GroupMemberComponent)playerSession.getPlayer().createIfNotExist(GroupMemberComponent.class);
/* 29 */     Set<Long> applySet = groupMemberComponent.getApplySet();
/* 30 */     applySet.remove(Long.valueOf(id));
/* 31 */     groupMemberComponent.setApplySet(applySet);
/*    */     
/* 33 */     GroupService groupService = (GroupService)MContext.getBean("groupService");
/* 34 */     GroupEntity groupEntity = groupService.getGroupEntity(id);
/* 35 */     if (null != groupEntity) {
/* 36 */       groupService.removeApply(groupEntity, playerSession.getPlayer().getPlayerId());
/*    */     }
/* 38 */     groupMemberComponent.saveToDB();
/* 39 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\group\GroupCancelApplyProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */