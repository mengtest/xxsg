/*     */ package com.linlongyx.sanguo.webgame.processors.group;
/*     */ 
/*     */ import com.linlongyx.core.framework.base.ProcessorBase;
/*     */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*     */ import com.linlongyx.core.framework.protocol.RequestBase;
/*     */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*     */ import com.linlongyx.core.utils.LogUtils;
/*     */ import com.linlongyx.sanguo.webgame.app.group.GroupEntity;
/*     */ import com.linlongyx.sanguo.webgame.app.group.GroupMemberComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.player.PlayerComponent;
/*     */ import com.linlongyx.sanguo.webgame.config.parameter.GroupParameter;
/*     */ import com.linlongyx.sanguo.webgame.constant.LanguageConstant;
/*     */ import com.linlongyx.sanguo.webgame.constant.MContext;
/*     */ import com.linlongyx.sanguo.webgame.constant.ParameterConstant;
/*     */ import com.linlongyx.sanguo.webgame.processors.mail.MailUtil;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.group.GroupMemberManageRequest;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.group.GroupMemberManageResponse;
/*     */ import com.linlongyx.sanguo.webgame.service.GroupService;
/*     */ import com.linlongyx.sanguo.webgame.service.LookUpService;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class GroupMemberManageProcessor
/*     */   extends ProcessorBase<GroupMemberManageRequest, GroupMemberManageResponse>
/*     */ {
/*     */   protected void initResponse() {
/*  30 */     this.response = (ResponseBase)new GroupMemberManageResponse();
/*     */   }
/*     */ 
/*     */   
/*     */   protected short handleRequest(IPlayerSession playerSession, GroupMemberManageRequest request) {
/*  35 */     GroupMemberComponent groupMemberComponent = (GroupMemberComponent)playerSession.getPlayer().createIfNotExist(GroupMemberComponent.class);
/*  36 */     if (0L == groupMemberComponent.getId()) {
/*  37 */       return 11101;
/*     */     }
/*  39 */     GroupService groupService = (GroupService)MContext.getBean("groupService");
/*  40 */     GroupEntity groupEntity = groupService.getGroupEntity(groupMemberComponent.getId());
/*  41 */     if (null == groupEntity) {
/*  42 */       return 11101;
/*     */     }
/*  44 */     int type = request.type;
/*  45 */     long playerId = request.playerId;
/*  46 */     if (1 == type) {
/*  47 */       if (groupMemberComponent.getPosition() != 1) {
/*  48 */         return 11110;
/*     */       }
/*  50 */       if (!groupEntity.getMemberList().contains(Long.valueOf(playerId))) {
/*  51 */         return 11114;
/*     */       }
/*  53 */       GroupMemberComponent memberComponent = (GroupMemberComponent)LookUpService.getComponent(playerId, GroupMemberComponent.class);
/*  54 */       if (null != memberComponent) {
/*  55 */         groupMemberComponent.setPosition(3);
/*  56 */         memberComponent.setPosition(1);
/*  57 */         boolean isVice = groupEntity.getVices().contains(Long.valueOf(playerId));
/*  58 */         if (isVice) {
/*  59 */           groupService.removeVice(groupEntity, playerId);
/*     */         }
/*  61 */         String leaderName = "";
/*  62 */         PlayerComponent playerComponent = (PlayerComponent)LookUpService.getComponent(playerId, PlayerComponent.class);
/*  63 */         if (null != playerComponent) {
/*  64 */           leaderName = playerComponent.getPlayerName();
/*     */         }
/*  66 */         groupService.changeLeader(groupEntity, playerId, leaderName);
/*  67 */         memberComponent.saveToDB();
/*  68 */         groupMemberComponent.saveToDB();
/*     */ 
/*     */         
/*  71 */         String title = LanguageConstant.getLanguage(1103);
/*  72 */         String content = LanguageConstant.getAndReplaceLanguage(1104, new String[] { leaderName });
/*  73 */         for (Long id : groupEntity.getMemberList()) {
/*  74 */           MailUtil.sendSysMail(id.longValue(), new ArrayList(), title, content);
/*     */         }
/*  76 */         LogUtils.errorLog(new Object[] { "GroupLeaderTransfer", Long.valueOf(groupEntity.getId()), Long.valueOf(playerSession.getPlayer().getPlayerId()), Long.valueOf(playerId) });
/*     */       } 
/*  78 */     } else if (2 == type) {
/*  79 */       if (groupMemberComponent.getPosition() != 1) {
/*  80 */         return 11110;
/*     */       }
/*  82 */       if (!groupEntity.getMemberList().contains(Long.valueOf(playerId))) {
/*  83 */         return 11114;
/*     */       }
/*  85 */       if (groupEntity.getVices().contains(Long.valueOf(playerId))) {
/*  86 */         return 11117;
/*     */       }
/*  88 */       if (groupEntity.getLeader() == playerId) {
/*  89 */         return 11118;
/*     */       }
/*  91 */       GroupParameter groupParameter = (GroupParameter)ParameterConstant.getParameter(11);
/*  92 */       if (groupEntity.getVices().size() >= groupParameter.getViceSize()) {
/*  93 */         return 11119;
/*     */       }
/*  95 */       GroupMemberComponent memberComponent = (GroupMemberComponent)LookUpService.getComponent(playerId, GroupMemberComponent.class);
/*  96 */       if (null != memberComponent) {
/*  97 */         memberComponent.setPosition(2);
/*  98 */         memberComponent.saveToDB();
/*  99 */         groupService.addVice(groupEntity, playerId);
/*     */       } 
/* 101 */       LogUtils.errorLog(new Object[] { "GroupViceGive", Long.valueOf(groupEntity.getId()), Long.valueOf(playerSession.getPlayer().getPlayerId()), Long.valueOf(playerId) });
/* 102 */     } else if (3 == type) {
/* 103 */       if (groupMemberComponent.getPosition() != 1) {
/* 104 */         return 11110;
/*     */       }
/* 106 */       if (!groupEntity.getMemberList().contains(Long.valueOf(playerId))) {
/* 107 */         return 11114;
/*     */       }
/* 109 */       GroupMemberComponent memberComponent = (GroupMemberComponent)LookUpService.getComponent(playerId, GroupMemberComponent.class);
/* 110 */       if (null != memberComponent) {
/* 111 */         memberComponent.setPosition(3);
/* 112 */         memberComponent.saveToDB();
/* 113 */         groupService.removeVice(groupEntity, playerId);
/*     */       } 
/* 115 */       LogUtils.errorLog(new Object[] { "GroupViceOut", Long.valueOf(groupEntity.getId()), Long.valueOf(playerId) });
/* 116 */     } else if (4 == type) {
/* 117 */       if (playerSession.getPlayer().getPlayerId() == playerId) {
/* 118 */         playerId = playerSession.getPlayer().getPlayerId();
/* 119 */         if (groupMemberComponent.getPosition() == 1) {
/* 120 */           if (groupEntity.getMemberList().size() > 1) {
/* 121 */             return 11113;
/*     */           }
/* 123 */         } else if (groupMemberComponent.getPosition() == 2) {
/* 124 */           groupService.removeVice(groupEntity, playerId);
/*     */         } 
/*     */         
/* 127 */         groupService.removeMember(groupEntity, playerId);
/* 128 */         groupMemberComponent.setTotalOffer(0L);
/* 129 */         groupMemberComponent.setId(0L);
/* 130 */         groupMemberComponent.setPosition(0);
/* 131 */         groupMemberComponent.setPlayerRewards(new HashMap<>());
/* 132 */         groupMemberComponent.saveToDB();
/* 133 */         LogUtils.errorLog(new Object[] { "GroupLeave", Long.valueOf(groupEntity.getId()), Long.valueOf(playerId) });
/*     */       } 
/* 135 */       if (groupEntity.getMemberList().size() == 0) {
/* 136 */         groupService.deleteGroupEntity(groupEntity);
/*     */       }
/* 138 */     } else if (type == 5) {
/* 139 */       if (groupMemberComponent.getPosition() != 1 && groupMemberComponent.getPosition() != 2) {
/* 140 */         return 11110;
/*     */       }
/* 142 */       boolean isVice = groupEntity.getVices().contains(Long.valueOf(playerId));
/* 143 */       if (groupMemberComponent.getPosition() == 2 && isVice) {
/* 144 */         return 11110;
/*     */       }
/* 146 */       if (!groupEntity.getMemberList().contains(Long.valueOf(playerId))) {
/* 147 */         return 11114;
/*     */       }
/* 149 */       if (playerSession.getPlayer().getPlayerId() == playerId) {
/* 150 */         return 11115;
/*     */       }
/* 152 */       if (groupEntity.getLeader() == playerId) {
/* 153 */         return 11116;
/*     */       }
/* 155 */       GroupMemberComponent memberComponent = (GroupMemberComponent)LookUpService.getComponent(playerId, GroupMemberComponent.class);
/* 156 */       if (null != memberComponent) {
/* 157 */         memberComponent.setId(0L);
/* 158 */         memberComponent.setPosition(0);
/* 159 */         memberComponent.setTotalOffer(0L);
/* 160 */         memberComponent.setPlayerRewards(new HashMap<>());
/* 161 */         memberComponent.saveToDB();
/*     */         
/* 163 */         if (isVice) {
/* 164 */           groupService.removeVice(groupEntity, playerId);
/*     */         }
/* 166 */         groupService.removeMember(groupEntity, playerId);
/*     */       } 
/* 168 */       String title = LanguageConstant.getLanguage(1105);
/* 169 */       String content = LanguageConstant.getAndReplaceLanguage(1106, new String[] { groupEntity.getGroupName() });
/* 170 */       MailUtil.sendSysMail(playerId, new ArrayList(), title, content);
/* 171 */       LogUtils.errorLog(new Object[] { "GroupGun", Long.valueOf(groupEntity.getId()), Long.valueOf(playerId) });
/* 172 */       if (groupEntity.getMemberList().size() == 0) {
/* 173 */         groupService.deleteGroupEntity(groupEntity);
/*     */       }
/*     */     } 
/*     */     
/* 177 */     ((GroupMemberManageResponse)this.response).type = type;
/* 178 */     ((GroupMemberManageResponse)this.response).playerId = playerId;
/* 179 */     return 0;
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\group\GroupMemberManageProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */