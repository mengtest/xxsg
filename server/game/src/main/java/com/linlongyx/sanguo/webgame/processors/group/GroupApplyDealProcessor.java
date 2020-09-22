/*     */ package com.linlongyx.sanguo.webgame.processors.group;
/*     */ 
/*     */ import com.linlongyx.core.framework.base.ProcessorBase;
/*     */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*     */ import com.linlongyx.core.framework.protocol.RequestBase;
/*     */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*     */ import com.linlongyx.core.utils.LogUtils;
/*     */ import com.linlongyx.sanguo.webgame.app.group.GroupEntity;
/*     */ import com.linlongyx.sanguo.webgame.app.group.GroupMemberComponent;
/*     */ import com.linlongyx.sanguo.webgame.constant.MContext;
/*     */ import com.linlongyx.sanguo.webgame.constant.RedNoticeConstant;
/*     */ import com.linlongyx.sanguo.webgame.processors.player.PlayerUtil;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.group.GroupApplyDealRequest;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.group.GroupApplyDealResponse;
/*     */ import com.linlongyx.sanguo.webgame.service.GroupService;
/*     */ import com.linlongyx.sanguo.webgame.service.LookUpService;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class GroupApplyDealProcessor
/*     */   extends ProcessorBase<GroupApplyDealRequest, GroupApplyDealResponse>
/*     */ {
/*     */   protected void initResponse() {
/*  27 */     this.response = (ResponseBase)new GroupApplyDealResponse();
/*     */   }
/*     */ 
/*     */   
/*     */   protected short handleRequest(IPlayerSession playerSession, GroupApplyDealRequest request) {
/*  32 */     GroupMemberComponent groupMemberComponent = (GroupMemberComponent)playerSession.getPlayer().createIfNotExist(GroupMemberComponent.class);
/*  33 */     if (0L == groupMemberComponent.getId()) {
/*  34 */       return 11101;
/*     */     }
/*  36 */     GroupService groupService = (GroupService)MContext.getBean("groupService");
/*  37 */     GroupEntity groupEntity = groupService.getGroupEntity(groupMemberComponent.getId());
/*  38 */     if (null == groupEntity) {
/*  39 */       return 11101;
/*     */     }
/*  41 */     if (groupMemberComponent.getPosition() != 1 && groupMemberComponent.getPosition() != 2) {
/*  42 */       return 11110;
/*     */     }
/*  44 */     List<Long> applyList = groupEntity.getApplyList();
/*  45 */     if (applyList.isEmpty()) {
/*  46 */       return 11111;
/*     */     }
/*  48 */     int size = GroupUtil.getPersonNum(groupEntity.getLevel());
/*  49 */     if (groupEntity.getMemberList().size() >= size) {
/*  50 */       return 11103;
/*     */     }
/*  52 */     int type = request.type;
/*     */     
/*  54 */     List<Long> playerIds = new ArrayList<>();
/*  55 */     if (1 == type) {
/*  56 */       long playerId = request.playerId;
/*  57 */       if (!applyList.contains(Long.valueOf(playerId))) {
/*  58 */         return 11112;
/*     */       }
/*  60 */       playerIds.add(Long.valueOf(playerId));
/*     */     } else {
/*  62 */       playerIds.addAll(applyList);
/*     */     } 
/*  64 */     int deal = request.deal;
/*  65 */     if (1 == deal) {
/*  66 */       for (Long playerId : playerIds) {
/*  67 */         if (groupEntity.getMemberList().size() >= size) {
/*     */           break;
/*     */         }
/*  70 */         GroupMemberComponent memberComponent = (GroupMemberComponent)LookUpService.getComponent(playerId.longValue(), GroupMemberComponent.class);
/*  71 */         if (null != memberComponent) {
/*  72 */           if (memberComponent.getId() == 0L) {
/*  73 */             memberComponent.setId(groupEntity.getId());
/*  74 */             memberComponent.setPosition(3);
/*  75 */             memberComponent.setTotalOffer(0L);
/*  76 */             GroupUtil.clearApply(memberComponent);
/*  77 */             memberComponent.saveToDB();
/*     */           } 
/*  79 */           groupService.removeApply(groupEntity, playerId.longValue());
/*  80 */           groupService.addMember(groupEntity, playerId.longValue());
/*     */ 
/*     */           
/*  83 */           PlayerUtil.sendRedNotice(playerId, RedNoticeConstant.GroupDeal);
/*  84 */           LogUtils.errorLog(new Object[] { "GroupDeal", Long.valueOf(groupEntity.getId()), Long.valueOf(playerSession.getPlayer().getPlayerId()), playerId });
/*     */         } 
/*     */       } 
/*  87 */       ((GroupApplyDealResponse)this.response).num = groupEntity.getMemberList().size();
/*     */     } else {
/*  89 */       for (Long playerId : playerIds) {
/*  90 */         GroupMemberComponent memberComponent = (GroupMemberComponent)LookUpService.getComponent(playerId.longValue(), GroupMemberComponent.class);
/*  91 */         if (null != memberComponent && 
/*  92 */           memberComponent.getId() == 0L) {
/*  93 */           memberComponent.getApplySet().remove(Long.valueOf(groupEntity.getId()));
/*  94 */           memberComponent.saveToDB();
/*     */         } 
/*     */         
/*  97 */         groupService.removeApply(groupEntity, playerId.longValue());
/*  98 */         LogUtils.errorLog(new Object[] { "GroupRefuse", Long.valueOf(groupEntity.getId()), Long.valueOf(playerSession.getPlayer().getPlayerId()), playerId });
/*     */       } 
/*     */     } 
/* 101 */     return 0;
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\group\GroupApplyDealProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */