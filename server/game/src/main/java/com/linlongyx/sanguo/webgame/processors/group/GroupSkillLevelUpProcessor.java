/*    */ package com.linlongyx.sanguo.webgame.processors.group;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.core.utils.LogUtils;
/*    */ import com.linlongyx.sanguo.webgame.app.group.GroupEntity;
/*    */ import com.linlongyx.sanguo.webgame.app.group.GroupMemberComponent;
/*    */ import com.linlongyx.sanguo.webgame.common.fight.attribute.AttrUpdateUtil;
/*    */ import com.linlongyx.sanguo.webgame.config.bean.BlocSkillBean;
/*    */ import com.linlongyx.sanguo.webgame.config.parameter.GroupParameter;
/*    */ import com.linlongyx.sanguo.webgame.constant.MContext;
/*    */ import com.linlongyx.sanguo.webgame.constant.ParameterConstant;
/*    */ import com.linlongyx.sanguo.webgame.constant.ResourceEvent;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.group.GroupSkillLevelUpRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.group.GroupSkillLevelUpResponse;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.Reward;
/*    */ import com.linlongyx.sanguo.webgame.service.GroupService;
/*    */ import com.linlongyx.sanguo.webgame.util.CostUtil;
/*    */ import java.util.Map;
/*    */ 
/*    */ public class GroupSkillLevelUpProcessor
/*    */   extends ProcessorBase<GroupSkillLevelUpRequest, GroupSkillLevelUpResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 27 */     this.response = (ResponseBase)new GroupSkillLevelUpResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, GroupSkillLevelUpRequest request) {
/* 32 */     GroupMemberComponent groupMemberComponent = (GroupMemberComponent)playerSession.getPlayer().createIfNotExist(GroupMemberComponent.class);
/* 33 */     if (0L == groupMemberComponent.getId()) {
/* 34 */       return 11101;
/*    */     }
/* 36 */     GroupService groupService = (GroupService)MContext.getBean("groupService");
/* 37 */     GroupEntity groupEntity = groupService.getGroupEntity(groupMemberComponent.getId());
/* 38 */     if (null == groupEntity) {
/* 39 */       return 11101;
/*    */     }
/* 41 */     int index = request.index;
/*    */     
/* 43 */     Map<Integer, Integer> skills = groupMemberComponent.getSkills();
/* 44 */     int oldLevel = ((Integer)skills.getOrDefault(Integer.valueOf(request.index), Integer.valueOf(0))).intValue();
/* 45 */     BlocSkillBean oldSkillBean = GroupUtil.getBlocSkillBean(index, oldLevel);
/* 46 */     if (null == oldSkillBean) {
/* 47 */       return 11125;
/*    */     }
/*    */     
/* 50 */     if (groupEntity.getLevel() < oldSkillBean.getBlocLevel()) {
/* 51 */       return 11126;
/*    */     }
/*    */     
/* 54 */     int newLevel = oldLevel + 1;
/* 55 */     BlocSkillBean newSkillBean = GroupUtil.getBlocSkillBean(index, newLevel);
/* 56 */     if (null == newSkillBean) {
/* 57 */       return 11127;
/*    */     }
/*    */ 
/*    */     
/* 61 */     GroupParameter groupParameter = (GroupParameter)ParameterConstant.getParameter(11);
/* 62 */     Reward cost = new Reward();
/* 63 */     cost.type = 1;
/* 64 */     cost.id = groupParameter.getSkillCurrency();
/* 65 */     cost.num = oldSkillBean.getCost();
/* 66 */     short code = CostUtil.handleCostReward(cost, playerSession, ResourceEvent.GroupSkillLevelUp);
/* 67 */     if (0 != code) {
/* 68 */       return code;
/*    */     }
/*    */     
/* 71 */     skills.put(Integer.valueOf(index), Integer.valueOf(newLevel));
/* 72 */     groupMemberComponent.setSkills(skills);
/*    */     
/* 74 */     AttrUpdateUtil.refreshGroup(playerSession);
/*    */     
/* 76 */     ((GroupSkillLevelUpResponse)this.response).index = index;
/* 77 */     ((GroupSkillLevelUpResponse)this.response).level = newLevel;
/*    */     
/* 79 */     LogUtils.errorLog(new Object[] { "GroupSkillLevelUp", Long.valueOf(playerSession.getPlayer().getPlayerId()), ((GroupSkillLevelUpResponse)this.response).toString() });
/* 80 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\group\GroupSkillLevelUpProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */