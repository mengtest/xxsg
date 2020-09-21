/*    */ package com.linlongyx.sanguo.webgame.processors.group;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.core.utils.ChatUtil;
/*    */ import com.linlongyx.sanguo.webgame.app.group.GroupEntity;
/*    */ import com.linlongyx.sanguo.webgame.app.group.GroupMemberComponent;
/*    */ import com.linlongyx.sanguo.webgame.config.parameter.GroupParameter;
/*    */ import com.linlongyx.sanguo.webgame.constant.MContext;
/*    */ import com.linlongyx.sanguo.webgame.constant.ParameterConstant;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.group.GroupNoticeChangeRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.group.GroupNoticeChangeResponse;
/*    */ import com.linlongyx.sanguo.webgame.service.GroupService;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class GroupNoticeChangeProcessor
/*    */   extends ProcessorBase<GroupNoticeChangeRequest, GroupNoticeChangeResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 23 */     this.response = (ResponseBase)new GroupNoticeChangeResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, GroupNoticeChangeRequest request) {
/* 28 */     GroupMemberComponent groupMemberComponent = (GroupMemberComponent)playerSession.getPlayer().createIfNotExist(GroupMemberComponent.class);
/* 29 */     if (0L == groupMemberComponent.getId()) {
/* 30 */       return 11101;
/*    */     }
/* 32 */     GroupService groupService = (GroupService)MContext.getBean("groupService");
/* 33 */     GroupEntity groupEntity = groupService.getGroupEntity(groupMemberComponent.getId());
/* 34 */     if (null == groupEntity) {
/* 35 */       return 11101;
/*    */     }
/* 37 */     String reg = "[^(a-zA-Z0-9\\u4e00-\\u9fa5\\u00C0-\\u1EF9\\u0020)]";
/* 38 */     String notice = request.notice.trim().replaceAll(reg, "");
/* 39 */     if (!ChatUtil.isLegalName(notice)) {
/* 40 */       return 11109;
/*    */     }
/* 42 */     GroupParameter groupParameter = (GroupParameter)ParameterConstant.getParameter(11);
/* 43 */     if (notice.length() > groupParameter.getNoticeLength()) {
/* 44 */       return 11122;
/*    */     }
/* 46 */     if (groupEntity.getLevel() < groupParameter.getChangeNotice()) {
/* 47 */       return 11134;
/*    */     }
/* 49 */     if (groupMemberComponent.getPosition() != 1) {
/* 50 */       return 11110;
/*    */     }
/* 52 */     groupEntity.setNotice(notice);
/* 53 */     groupService.updateNotice(groupEntity.getId());
/* 54 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\group\GroupNoticeChangeProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */