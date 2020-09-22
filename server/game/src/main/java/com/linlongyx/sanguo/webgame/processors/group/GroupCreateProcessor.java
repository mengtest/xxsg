/*    */ package com.linlongyx.sanguo.webgame.processors.group;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.core.utils.ChatUtil;
/*    */ import com.linlongyx.core.utils.LogUtils;
/*    */ import com.linlongyx.sanguo.webgame.app.group.GroupEntity;
/*    */ import com.linlongyx.sanguo.webgame.app.group.GroupMemberComponent;
/*    */ import com.linlongyx.sanguo.webgame.app.player.PlayerComponent;
/*    */ import com.linlongyx.sanguo.webgame.common.util.FinanceUtil;
/*    */ import com.linlongyx.sanguo.webgame.config.parameter.GroupParameter;
/*    */ import com.linlongyx.sanguo.webgame.constant.CurrencyType;
/*    */ import com.linlongyx.sanguo.webgame.constant.FunctionOpenConstant;
/*    */ import com.linlongyx.sanguo.webgame.constant.MContext;
/*    */ import com.linlongyx.sanguo.webgame.constant.ParameterConstant;
/*    */ import com.linlongyx.sanguo.webgame.constant.ResourceEvent;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.group.GroupCreateRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.group.GroupCreateResponse;
/*    */ import com.linlongyx.sanguo.webgame.service.GroupService;
/*    */ 
/*    */ public class GroupCreateProcessor extends ProcessorBase<GroupCreateRequest, GroupCreateResponse> {
/*    */   protected void initResponse() {
/* 25 */     this.response = (ResponseBase)new GroupCreateResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, GroupCreateRequest request) {
/* 30 */     if (!FunctionOpenConstant.isFunctionOpen(playerSession, 11)) {
/* 31 */       return 10061;
/*    */     }
/* 33 */     GroupService groupService = (GroupService)MContext.getBean("groupService");
/* 34 */     GroupMemberComponent groupMemberComponent = (GroupMemberComponent)playerSession.getPlayer().createIfNotExist(GroupMemberComponent.class);
/* 35 */     if (groupMemberComponent.getId() != 0L) {
/* 36 */       GroupEntity groupEntity2 = groupService.getGroupEntity(groupMemberComponent.getId());
/* 37 */       if (null == groupEntity2) {
/* 38 */         groupMemberComponent.setId(0L);
/*    */       }
/*    */     } 
/* 41 */     if (groupMemberComponent.getId() != 0L) {
/* 42 */       return 11106;
/*    */     }
/* 44 */     String reg = "[^(a-zA-Z0-9\\u4e00-\\u9fa5\\u00C0-\\u1EF9\\u0020)]";
/* 45 */     String name = request.name.replaceAll(reg, "");
/*    */     
/* 47 */     if (!ChatUtil.isLegalName(name)) {
/* 48 */       return 11107;
/*    */     }
/* 50 */     if (groupService.checkName(name)) {
/* 51 */       return 11108;
/*    */     }
/* 53 */     PlayerComponent playerComponent = (PlayerComponent)playerSession.getPlayer().createIfNotExist(PlayerComponent.class);
/* 54 */     GroupParameter groupParameter = (GroupParameter)ParameterConstant.getParameter(11);
/* 55 */     int ccy = groupParameter.getCreateCost(playerComponent.getVip());
/*    */ 
/*    */ 
/*    */     
/* 59 */     if (!FinanceUtil.checkCurrency(playerSession.getPlayer(), CurrencyType.CCY, ccy)) {
/* 60 */       return 10052;
/*    */     }
/* 62 */     FinanceUtil.decCurrency(playerSession.getPlayer(), CurrencyType.CCY, ccy, ResourceEvent.GroupCreate);
/*    */     
/* 64 */     long playerId = playerSession.getPlayer().getPlayerId();
/* 65 */     GroupEntity groupEntity = groupService.createGroup();
/* 66 */     groupEntity.setGroupName(name);
/* 67 */     groupEntity.setLeader(playerId);
/* 68 */     groupEntity.setLeaderName(playerSession.getPlayer().getPlayerName());
/* 69 */     groupEntity.setLevel(1);
/* 70 */     groupEntity.getMemberList().add(Long.valueOf(playerId));
/* 71 */     groupEntity.setNotice(groupParameter.getDefaultNotice());
/* 72 */     groupService.addGroupEntity(groupEntity);
/*    */     
/* 74 */     ((GroupCreateResponse)this.response).data = GroupUtil.getGroupData(groupEntity);
/*    */ 
/*    */     
/* 77 */     GroupUtil.clearApply(groupMemberComponent);
/*    */     
/* 79 */     groupMemberComponent.setId(groupEntity.getId());
/* 80 */     groupMemberComponent.setPosition(1);
/* 81 */     groupMemberComponent.saveToDB();
/*    */     
/* 83 */     LogUtils.errorLog(new Object[] { "GroupCreate", Long.valueOf(playerId), groupEntity.toString() });
/*    */     
/* 85 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\group\GroupCreateProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */