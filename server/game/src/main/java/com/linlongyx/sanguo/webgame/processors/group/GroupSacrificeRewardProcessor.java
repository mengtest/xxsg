/*    */ package com.linlongyx.sanguo.webgame.processors.group;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.core.utils.LogUtils;
/*    */ import com.linlongyx.sanguo.webgame.app.group.GroupEntity;
/*    */ import com.linlongyx.sanguo.webgame.app.group.GroupMemberComponent;
/*    */ import com.linlongyx.sanguo.webgame.common.util.FinanceUtil;
/*    */ import com.linlongyx.sanguo.webgame.config.bean.BlocProcessBean;
/*    */ import com.linlongyx.sanguo.webgame.constant.MContext;
/*    */ import com.linlongyx.sanguo.webgame.constant.ResourceEvent;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.group.GroupSacrificeRewardRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.group.GroupSacrificeRewardResponse;
/*    */ import com.linlongyx.sanguo.webgame.service.GroupService;
/*    */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*    */ import java.util.Set;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class GroupSacrificeRewardProcessor
/*    */   extends ProcessorBase<GroupSacrificeRewardRequest, GroupSacrificeRewardResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 27 */     this.response = (ResponseBase)new GroupSacrificeRewardResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, GroupSacrificeRewardRequest request) {
/* 32 */     GroupMemberComponent groupMemberComponent = (GroupMemberComponent)playerSession.getPlayer().createIfNotExist(GroupMemberComponent.class);
/* 33 */     if (0L == groupMemberComponent.getId()) {
/* 34 */       return 11101;
/*    */     }
/* 36 */     GroupService groupService = (GroupService)MContext.getBean("groupService");
/* 37 */     GroupEntity groupEntity = groupService.getGroupEntity(groupMemberComponent.getId());
/* 38 */     if (null == groupEntity) {
/* 39 */       return 11101;
/*    */     }
/* 41 */     int id = request.id;
/* 42 */     BlocProcessBean blocProcessBean = (BlocProcessBean)JsonTableService.getJsonData(id, BlocProcessBean.class);
/* 43 */     if (null == blocProcessBean) {
/* 44 */       return 11123;
/*    */     }
/* 46 */     Set<Integer> sacrificeBox = groupMemberComponent.getSacrificeBox();
/* 47 */     if (sacrificeBox.contains(Integer.valueOf(id))) {
/* 48 */       return 10091;
/*    */     }
/* 50 */     int point = groupEntity.getSacrificePoint();
/* 51 */     if (point < blocProcessBean.getPoint()) {
/* 52 */       return 11124;
/*    */     }
/* 54 */     sacrificeBox.add(Integer.valueOf(id));
/* 55 */     groupMemberComponent.setSacrificeBox(sacrificeBox);
/*    */     
/* 57 */     FinanceUtil.reward(FinanceUtil.transform(blocProcessBean.getReward()), playerSession.getPlayer(), ResourceEvent.GroupSacrificeReward, true);
/*    */     
/* 59 */     ((GroupSacrificeRewardResponse)this.response).id = id;
/*    */     
/* 61 */     LogUtils.errorLog(new Object[] { "GroupSacrificeReward", Long.valueOf(playerSession.getPlayer().getPlayerId()), ((GroupSacrificeRewardResponse)this.response).toString() });
/* 62 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\group\GroupSacrificeRewardProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */