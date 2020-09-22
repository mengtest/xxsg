/*    */ package com.linlongyx.sanguo.webgame.processors.group;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.app.group.GroupEntity;
/*    */ import com.linlongyx.sanguo.webgame.app.group.GroupMemberComponent;
/*    */ import com.linlongyx.sanguo.webgame.constant.MContext;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.group.GroupApplyListRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.group.GroupApplyListResponse;
/*    */ import com.linlongyx.sanguo.webgame.service.GroupService;
/*    */ import java.util.List;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class GroupApplyListProcessor
/*    */   extends ProcessorBase<GroupApplyListRequest, GroupApplyListResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 22 */     this.response = (ResponseBase)new GroupApplyListResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, GroupApplyListRequest request) {
/* 27 */     GroupMemberComponent groupMemberComponent = (GroupMemberComponent)playerSession.getPlayer().createIfNotExist(GroupMemberComponent.class);
/* 28 */     if (0L == groupMemberComponent.getId()) {
/* 29 */       return 11101;
/*    */     }
/* 31 */     GroupService groupService = (GroupService)MContext.getBean("groupService");
/* 32 */     GroupEntity groupEntity = groupService.getGroupEntity(groupMemberComponent.getId());
/* 33 */     if (null == groupEntity) {
/* 34 */       return 11101;
/*    */     }
/* 36 */     List<Long> applyList = groupEntity.getApplyList();
/* 37 */     GroupUtil.getMemberList(((GroupApplyListResponse)this.response).datas, applyList);
/* 38 */     ((GroupApplyListResponse)this.response).auto = groupEntity.getAutoAdd();
/* 39 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\group\GroupApplyListProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */