/*    */ package com.linlongyx.sanguo.webgame.processors.group;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.app.group.GroupMemberComponent;
/*    */ import com.linlongyx.sanguo.webgame.app.task.TaskComponent;
/*    */ import com.linlongyx.sanguo.webgame.config.parameter.GroupParameter;
/*    */ import com.linlongyx.sanguo.webgame.constant.FunctionOpenConstant;
/*    */ import com.linlongyx.sanguo.webgame.constant.MContext;
/*    */ import com.linlongyx.sanguo.webgame.constant.ParameterConstant;
/*    */ import com.linlongyx.sanguo.webgame.constant.TaskType;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.group.GroupListRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.group.GroupListResponse;
/*    */ import com.linlongyx.sanguo.webgame.service.GroupService;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ 
/*    */ public class GroupListProcessor
/*    */   extends ProcessorBase<GroupListRequest, GroupListResponse> {
/*    */   protected void initResponse() {
/* 23 */     this.response = (ResponseBase)new GroupListResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, GroupListRequest request) {
/* 28 */     if (!FunctionOpenConstant.isFunctionOpen(playerSession, 11)) {
/* 29 */       return 10061;
/*    */     }
/* 31 */     int page = request.page;
/* 32 */     if (page < 0) {
/* 33 */       page = 0;
/*    */     }
/* 35 */     GroupMemberComponent groupMemberComponent = (GroupMemberComponent)playerSession.getPlayer().createIfNotExist(GroupMemberComponent.class);
/* 36 */     GroupParameter groupParameter = (GroupParameter)ParameterConstant.getParameter(11);
/* 37 */     int start = page * groupParameter.getShowSize();
/* 38 */     int end = start + groupParameter.getShowSize();
/* 39 */     GroupService groupService = (GroupService)MContext.getBean("groupService");
/* 40 */     List<Long> showList = new ArrayList<>();
/* 41 */     int size = groupService.getList(((GroupListResponse)this.response).datas, showList, playerSession.getPlayer().getPlayerId(), start, end);
/* 42 */     groupMemberComponent.setShowList(showList);
/* 43 */     ((GroupListResponse)this.response).page = page;
/* 44 */     ((GroupListResponse)this.response).total = (int)Math.ceil(size / groupParameter.getShowSize());
/*    */     
/* 46 */     if (!groupMemberComponent.isListOpen()) {
/* 47 */       groupMemberComponent.setListOpen(true);
/*    */     }
/*    */     
/* 50 */     TaskComponent taskComponent = (TaskComponent)playerSession.getPlayer().createIfNotExist(TaskComponent.class);
/* 51 */     taskComponent.refreshSchedule(TaskType.OpenGroupList, 0, 0L);
/* 52 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\group\GroupListProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */