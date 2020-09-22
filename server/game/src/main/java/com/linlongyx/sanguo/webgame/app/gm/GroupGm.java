/*    */ package com.linlongyx.sanguo.webgame.app.gm;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.sanguo.webgame.app.group.GroupMemberComponent;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.group.GroupApplyDealRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.group.GroupCreateRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.group.GroupListRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.group.GroupMemberManageRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.group.GroupNoticeChangeRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.group.GroupSacrificeActionRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.group.GroupSacrificeRewardRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.group.GroupSkillLevelUpRequest;
/*    */ import com.linlongyx.sanguo.webgame.service.GroupService;
/*    */ 
/*    */ public class GroupGm implements IGm {
/*    */   public void gm(IPlayerSession playerSession, String[] strings) {
/* 17 */     GroupMemberComponent groupMemberComponent = (GroupMemberComponent)playerSession.getPlayer().createIfNotExist(GroupMemberComponent.class);
/* 18 */     GroupService groupService = (GroupService)MContext.getBean("groupService");
/* 19 */     GroupEntity groupEntity = groupService.getGroupEntity(groupMemberComponent.getId());
/* 20 */     if (strings[2].equals("addExp")) {
/* 21 */       if (null == groupEntity) {
/*    */         return;
/*    */       }
/* 24 */       long exp = Long.valueOf(strings[3]).longValue();
/* 25 */       groupService.addExp(groupEntity, exp);
/*    */     } 
/*    */     
/* 28 */     if (strings[2].equals("t1")) {
/* 29 */       (new GroupInfoProcessor()).handle(playerSession, (RequestBase)new GroupInfoRequest());
/* 30 */     } else if (strings[2].equals("t2")) {
/* 31 */       GroupListRequest request = new GroupListRequest();
/* 32 */       request.page = Integer.valueOf(strings[3]).intValue();
/* 33 */       (new GroupListProcessor()).handle(playerSession, (RequestBase)request);
/* 34 */     } else if (strings[2].equals("t3")) {
/* 35 */       GroupApplyRequest request = new GroupApplyRequest();
/* 36 */       request.id = Long.valueOf(strings[3]).longValue();
/* 37 */       (new GroupApplyProcessor()).handle(playerSession, (RequestBase)request);
/* 38 */     } else if (strings[2].equals("t4")) {
/* 39 */       GroupCancelApplyRequest request = new GroupCancelApplyRequest();
/* 40 */       request.id = Long.valueOf(strings[3]).longValue();
/* 41 */       (new GroupCancelApplyProcessor()).handle(playerSession, (RequestBase)request);
/* 42 */     } else if (strings[2].equals("t5")) {
/* 43 */       GroupCreateRequest request = new GroupCreateRequest();
/* 44 */       request.name = strings[3];
/* 45 */       (new GroupCreateProcessor()).handle(playerSession, (RequestBase)request);
/* 46 */     } else if (strings[2].equals("t6")) {
/* 47 */       GroupNoticeChangeRequest request = new GroupNoticeChangeRequest();
/* 48 */       request.notice = strings[3];
/* 49 */       (new GroupNoticeChangeProcessor()).handle(playerSession, (RequestBase)request);
/* 50 */     } else if (strings[2].equals("t7")) {
/* 51 */       (new GroupMemberListProcessor()).handle(playerSession, (RequestBase)new GroupMemberListRequest());
/* 52 */     } else if (strings[2].equals("t8")) {
/* 53 */       (new GroupApplyListProcessor()).handle(playerSession, (RequestBase)new GroupApplyListRequest());
/* 54 */     } else if (strings[2].equals("t9")) {
/* 55 */       GroupApplyDealRequest request = new GroupApplyDealRequest();
/* 56 */       request.type = Integer.valueOf(strings[3]).intValue();
/* 57 */       request.playerId = Long.valueOf(strings[4]).longValue();
/* 58 */       request.deal = Integer.valueOf(strings[5]).intValue();
/* 59 */       (new GroupApplyDealProcessor()).handle(playerSession, (RequestBase)request);
/* 60 */     } else if (strings[2].equals("t10")) {
/* 61 */       GroupMemberManageRequest request = new GroupMemberManageRequest();
/* 62 */       request.type = Integer.valueOf(strings[3]).intValue();
/* 63 */       request.playerId = Long.valueOf(strings[4]).longValue();
/* 64 */       (new GroupMemberManageProcessor()).handle(playerSession, (RequestBase)request);
/* 65 */     } else if (strings[2].equals("t11")) {
/* 66 */       (new GroupSacrificeInfoProcessor()).handle(playerSession, (RequestBase)new GroupSacrificeInfoRequest());
/* 67 */     } else if (strings[2].equals("t12")) {
/* 68 */       GroupSacrificeActionRequest request = new GroupSacrificeActionRequest();
/* 69 */       request.type = Integer.valueOf(strings[3]).intValue();
/* 70 */       (new GroupSacrificeActionProcessor()).handle(playerSession, (RequestBase)request);
/* 71 */     } else if (strings[2].equals("t13")) {
/* 72 */       GroupSacrificeRewardRequest request = new GroupSacrificeRewardRequest();
/* 73 */       request.id = Integer.valueOf(strings[3]).intValue();
/* 74 */       (new GroupSacrificeRewardProcessor()).handle(playerSession, (RequestBase)request);
/* 75 */     } else if (strings[2].equals("t14")) {
/* 76 */       (new GroupSkillInfoProcessor()).handle(playerSession, (RequestBase)new GroupSkillInfoRequest());
/* 77 */     } else if (strings[2].equals("t15")) {
/* 78 */       GroupSkillLevelUpRequest request = new GroupSkillLevelUpRequest();
/* 79 */       request.index = Integer.valueOf(strings[3]).intValue();
/* 80 */       (new GroupSkillLevelUpProcessor()).handle(playerSession, (RequestBase)request);
/* 81 */     } else if (strings[2].equals("recruit")) {
/* 82 */       GroupRecruitRequest request = new GroupRecruitRequest();
/* 83 */       (new GroupRecruitProcessor()).handle(playerSession, (RequestBase)request);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\app\gm\GroupGm.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */