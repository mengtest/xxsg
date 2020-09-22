/*    */ package com.linlongyx.sanguo.webgame.processors.group;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.core.utils.LogUtils;
/*    */ import com.linlongyx.sanguo.webgame.app.group.GroupEntity;
/*    */ import com.linlongyx.sanguo.webgame.app.group.GroupMemberComponent;
/*    */ import com.linlongyx.sanguo.webgame.app.task.TaskComponent;
/*    */ import com.linlongyx.sanguo.webgame.common.util.FinanceUtil;
/*    */ import com.linlongyx.sanguo.webgame.config.parameter.GroupParameter;
/*    */ import com.linlongyx.sanguo.webgame.constant.MContext;
/*    */ import com.linlongyx.sanguo.webgame.constant.ParameterConstant;
/*    */ import com.linlongyx.sanguo.webgame.constant.ResourceEvent;
/*    */ import com.linlongyx.sanguo.webgame.constant.TaskType;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.group.GroupSacrificeActionRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.group.GroupSacrificeActionResponse;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.Reward;
/*    */ import com.linlongyx.sanguo.webgame.service.GroupService;
/*    */ import com.linlongyx.sanguo.webgame.util.CostUtil;
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ public class GroupSacrificeActionProcessor
/*    */   extends ProcessorBase<GroupSacrificeActionRequest, GroupSacrificeActionResponse> {
/*    */   protected void initResponse() {
/* 27 */     this.response = (ResponseBase)new GroupSacrificeActionResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, GroupSacrificeActionRequest request) {
/* 32 */     GroupMemberComponent groupMemberComponent = (GroupMemberComponent)playerSession.getPlayer().createIfNotExist(GroupMemberComponent.class);
/* 33 */     if (0L == groupMemberComponent.getId()) {
/* 34 */       return 11101;
/*    */     }
/* 36 */     GroupService groupService = (GroupService)MContext.getBean("groupService");
/* 37 */     GroupEntity groupEntity = groupService.getGroupEntity(groupMemberComponent.getId());
/* 38 */     if (null == groupEntity) {
/* 39 */       return 11101;
/*    */     }
/* 41 */     if (groupMemberComponent.getSacrificeType() != 0) {
/* 42 */       return 11120;
/*    */     }
/* 44 */     GroupParameter groupParameter = (GroupParameter)ParameterConstant.getParameter(11);
/* 45 */     int type = request.type;
/*    */     
/* 47 */     if (1 != type && 2 != type && 3 != type) {
/* 48 */       return 11121;
/*    */     }
/* 50 */     int index = type - 1;
/* 51 */     Reward cost = groupParameter.getSacrificeCost(index);
/* 52 */     short code = CostUtil.handleCostReward(cost, playerSession, ResourceEvent.GroupSacrificeAction);
/* 53 */     if (0 != code) {
/* 54 */       return code;
/*    */     }
/* 56 */     groupMemberComponent.setSacrificeType(type);
/* 57 */     groupMemberComponent.saveToDB();
/*    */     
/* 59 */     Reward reward = groupParameter.getSacrificeReward(index);
/* 60 */     ArrayList<Reward> rewards = new ArrayList<>();
/* 61 */     rewards.add(reward);
/* 62 */     FinanceUtil.reward(rewards, playerSession.getPlayer(), ResourceEvent.GroupSacrificeAction, true);
/*    */     
/* 64 */     int exp = groupParameter.getSacrificeExp(index);
/* 65 */     int point = groupParameter.getSacrificePoint(index);
/* 66 */     groupService.addExp(groupEntity, exp);
/* 67 */     groupService.addPoint(groupEntity, point);
/*    */     
/* 69 */     ((GroupSacrificeActionResponse)this.response).process = groupEntity.getSacrificePoint();
/* 70 */     ((GroupSacrificeActionResponse)this.response).num = groupEntity.getSacrificeNum();
/* 71 */     ((GroupSacrificeActionResponse)this.response).type = type;
/*    */     
/* 73 */     TaskComponent taskComponent = (TaskComponent)playerSession.getPlayer().createIfNotExist(TaskComponent.class);
/* 74 */     if (null != taskComponent) {
/* 75 */       taskComponent.refreshSchedule(TaskType.GroupWorship, 0, 1L);
/*    */     }
/* 77 */     LogUtils.errorLog(new Object[] { "GroupSacrificeAction", Long.valueOf(playerSession.getPlayer().getPlayerId()), ((GroupSacrificeActionResponse)this.response).toString() });
/* 78 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\group\GroupSacrificeActionProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */