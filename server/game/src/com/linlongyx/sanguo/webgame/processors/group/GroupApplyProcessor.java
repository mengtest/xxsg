/*    */ package com.linlongyx.sanguo.webgame.processors.group;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.app.group.GroupEntity;
/*    */ import com.linlongyx.sanguo.webgame.app.group.GroupMemberComponent;
/*    */ import com.linlongyx.sanguo.webgame.config.parameter.GroupParameter;
/*    */ import com.linlongyx.sanguo.webgame.constant.FunctionOpenConstant;
/*    */ import com.linlongyx.sanguo.webgame.constant.MContext;
/*    */ import com.linlongyx.sanguo.webgame.constant.ParameterConstant;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.group.GroupApplyRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.group.GroupApplyResponse;
/*    */ import com.linlongyx.sanguo.webgame.service.GroupService;
/*    */ import java.util.List;
/*    */ import java.util.Set;
/*    */ 
/*    */ public class GroupApplyProcessor
/*    */   extends ProcessorBase<GroupApplyRequest, GroupApplyResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 23 */     this.response = (ResponseBase)new GroupApplyResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, GroupApplyRequest request) {
/* 28 */     if (!FunctionOpenConstant.isFunctionOpen(playerSession, 11)) {
/* 29 */       return 10061;
/*    */     }
/* 31 */     long id = request.id;
/* 32 */     GroupService groupService = (GroupService)MContext.getBean("groupService");
/* 33 */     long playerId = playerSession.getPlayer().getPlayerId();
/* 34 */     GroupMemberComponent groupMemberComponent = (GroupMemberComponent)playerSession.getPlayer().createIfNotExist(GroupMemberComponent.class);
/* 35 */     GroupEntity groupEntity2 = groupService.getGroupEntity(id);
/* 36 */     if (id != 0L && null == groupEntity2) {
/* 37 */       groupMemberComponent.setId(0L);
/*    */     }
/* 39 */     if (0L != groupMemberComponent.getId()) {
/* 40 */       return 11106;
/*    */     }
/* 42 */     GroupParameter groupParameter = (GroupParameter)ParameterConstant.getParameter(11);
/* 43 */     Set<Long> applySet = groupMemberComponent.getApplySet();
/* 44 */     if (applySet.size() >= groupParameter.getMaxApplyNum()) {
/* 45 */       return 11105;
/*    */     }
/* 47 */     if (0L == id) {
/* 48 */       List<Long> showList = groupMemberComponent.getShowList();
/* 49 */       for (Long groupId : showList) {
/* 50 */         GroupEntity groupEntity = groupService.getGroupEntity(groupId.longValue());
/* 51 */         if (null == groupEntity) {
/*    */           continue;
/*    */         }
/* 54 */         if (groupEntity.getApplyList().contains(groupId)) {
/*    */           continue;
/*    */         }
/* 57 */         short code = groupService.addApply(groupEntity, playerId, groupService, playerSession);
/* 58 */         if (0 != code) {
/*    */           continue;
/*    */         }
/* 61 */         applySet.add(groupId);
/* 62 */         ((GroupApplyResponse)this.response).ids.add(groupId);
/*    */       } 
/* 64 */       groupMemberComponent.setApplySet(applySet);
/*    */     } else {
/* 66 */       GroupEntity groupEntity = groupService.getGroupEntity(id);
/* 67 */       if (null == groupEntity) {
/* 68 */         return 11104;
/*    */       }
/* 70 */       if (groupEntity.getApplyList().contains(Long.valueOf(id))) {
/* 71 */         return 11102;
/*    */       }
/* 73 */       short code = groupService.addApply(groupEntity, playerId, groupService, playerSession);
/* 74 */       if (0 != code) {
/* 75 */         return code;
/*    */       }
/* 77 */       applySet.add(Long.valueOf(id));
/* 78 */       ((GroupApplyResponse)this.response).ids.add(Long.valueOf(id));
/* 79 */       groupMemberComponent.setApplySet(applySet);
/*    */     } 
/* 81 */     groupMemberComponent.saveToDB();
/* 82 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\group\GroupApplyProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */