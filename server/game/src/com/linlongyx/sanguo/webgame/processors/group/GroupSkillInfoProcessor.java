/*    */ package com.linlongyx.sanguo.webgame.processors.group;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.app.group.GroupEntity;
/*    */ import com.linlongyx.sanguo.webgame.app.group.GroupMemberComponent;
/*    */ import com.linlongyx.sanguo.webgame.config.parameter.GroupParameter;
/*    */ import com.linlongyx.sanguo.webgame.constant.MContext;
/*    */ import com.linlongyx.sanguo.webgame.constant.ParameterConstant;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.group.GroupSkillInfoRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.group.GroupSkillInfoResponse;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.IntIntValue;
/*    */ import com.linlongyx.sanguo.webgame.service.GroupService;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class GroupSkillInfoProcessor
/*    */   extends ProcessorBase<GroupSkillInfoRequest, GroupSkillInfoResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 26 */     this.response = (ResponseBase)new GroupSkillInfoResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, GroupSkillInfoRequest request) {
/* 31 */     GroupMemberComponent groupMemberComponent = (GroupMemberComponent)playerSession.getPlayer().createIfNotExist(GroupMemberComponent.class);
/* 32 */     if (0L == groupMemberComponent.getId()) {
/* 33 */       return 11101;
/*    */     }
/* 35 */     GroupService groupService = (GroupService)MContext.getBean("groupService");
/* 36 */     GroupEntity groupEntity = groupService.getGroupEntity(groupMemberComponent.getId());
/* 37 */     if (null == groupEntity) {
/* 38 */       return 11101;
/*    */     }
/* 40 */     GroupParameter groupParameter = (GroupParameter)ParameterConstant.getParameter(11);
/* 41 */     List<Integer> skillIndex = groupParameter.getSkillIndex();
/* 42 */     Map<Integer, Integer> skills = groupMemberComponent.getSkills();
/*    */     
/* 44 */     for (Integer index : skillIndex) {
/* 45 */       IntIntValue intIntValue = new IntIntValue();
/* 46 */       intIntValue.key = index.intValue();
/* 47 */       intIntValue.value = ((Integer)skills.getOrDefault(index, Integer.valueOf(0))).intValue();
/* 48 */       ((GroupSkillInfoResponse)this.response).skills.add(intIntValue);
/*    */     } 
/* 50 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\group\GroupSkillInfoProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */