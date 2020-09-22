/*    */ package com.linlongyx.sanguo.webgame.processors.group;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.app.group.GroupEntity;
/*    */ import com.linlongyx.sanguo.webgame.app.group.GroupMemberComponent;
/*    */ import com.linlongyx.sanguo.webgame.constant.MContext;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.group.GroupMemberListRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.group.GroupMemberListResponse;
/*    */ import com.linlongyx.sanguo.webgame.service.GroupService;
/*    */ import com.linlongyx.sanguo.webgame.service.LookUpService;
/*    */ import java.util.ArrayList;
/*    */ import java.util.HashSet;
/*    */ import java.util.Iterator;
/*    */ import java.util.Set;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class GroupMemberListProcessor
/*    */   extends ProcessorBase<GroupMemberListRequest, GroupMemberListResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 25 */     this.response = (ResponseBase)new GroupMemberListResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, GroupMemberListRequest request) {
/* 30 */     GroupMemberComponent groupMemberComponent = (GroupMemberComponent)playerSession.getPlayer().createIfNotExist(GroupMemberComponent.class);
/* 31 */     if (0L == groupMemberComponent.getId()) {
/* 32 */       return 11101;
/*    */     }
/* 34 */     GroupService groupService = (GroupService)MContext.getBean("groupService");
/* 35 */     GroupEntity groupEntity = groupService.getGroupEntity(groupMemberComponent.getId());
/* 36 */     if (null == groupEntity) {
/* 37 */       return 11101;
/*    */     }
/* 39 */     Set<Long> memberList = groupEntity.getMemberList();
/* 40 */     Set<Long> playerIds = GroupUtil.getMemberList(((GroupMemberListResponse)this.response).datas, new ArrayList<>(memberList));
/* 41 */     Set<Long> removeId = new HashSet<>();
/* 42 */     if (MContext.isHeFu() && !playerIds.isEmpty()) {
/* 43 */       Iterator<Long> iterator; for (iterator = memberList.iterator(); iterator.hasNext(); ) { long p = ((Long)iterator.next()).longValue();
/* 44 */         if (!playerIds.contains(Long.valueOf(p))) {
/* 45 */           removeId.add(Long.valueOf(p));
/*    */         } }
/*    */       
/* 48 */       for (iterator = removeId.iterator(); iterator.hasNext(); ) { long player = ((Long)iterator.next()).longValue();
/* 49 */         GroupMemberComponent groupMemberComponent1 = (GroupMemberComponent)LookUpService.getComponent(player, GroupMemberComponent.class);
/* 50 */         if (null == groupMemberComponent1) {
/* 51 */           groupService.removeMember(groupEntity, player);
/*    */         } }
/*    */     
/*    */     } 
/* 55 */     ((GroupMemberListResponse)this.response).datas.sort(new GroupMemberComparator());
/* 56 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\group\GroupMemberListProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */