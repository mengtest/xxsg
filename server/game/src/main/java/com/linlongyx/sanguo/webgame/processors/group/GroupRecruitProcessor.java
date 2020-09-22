/*    */ package com.linlongyx.sanguo.webgame.processors.group;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.core.utils.TimeUtil;
/*    */ import com.linlongyx.sanguo.webgame.app.group.GroupEntity;
/*    */ import com.linlongyx.sanguo.webgame.app.group.GroupMemberComponent;
/*    */ import com.linlongyx.sanguo.webgame.app.player.PlayerComponent;
/*    */ import com.linlongyx.sanguo.webgame.config.parameter.GroupParameter;
/*    */ import com.linlongyx.sanguo.webgame.constant.LanguageConstant;
/*    */ import com.linlongyx.sanguo.webgame.constant.MContext;
/*    */ import com.linlongyx.sanguo.webgame.constant.ParameterConstant;
/*    */ import com.linlongyx.sanguo.webgame.processors.player.PlayerUtil;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.chat.ChatByChannelResponse;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.group.GroupRecruitRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.group.GroupRecruitResponse;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.ChatInfo;
/*    */ import com.linlongyx.sanguo.webgame.service.GroupService;
/*    */ import com.linlongyx.sanguo.webgame.service.LookUpService;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class GroupRecruitProcessor
/*    */   extends ProcessorBase<GroupRecruitRequest, GroupRecruitResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 29 */     this.response = (ResponseBase)new GroupRecruitResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, GroupRecruitRequest request) {
/* 34 */     GroupMemberComponent groupMemberComponent = (GroupMemberComponent)playerSession.getPlayer().createIfNotExist(GroupMemberComponent.class);
/* 35 */     if (0L == groupMemberComponent.getId()) {
/* 36 */       return 11101;
/*    */     }
/* 38 */     GroupService groupService = (GroupService)MContext.getBean("groupService");
/* 39 */     GroupEntity groupEntity = groupService.getGroupEntity(groupMemberComponent.getId());
/*    */     
/* 41 */     if (groupMemberComponent.getPosition() != 1) {
/* 42 */       return 11110;
/*    */     }
/* 44 */     if (TimeUtil.currentTime() < groupEntity.getNextRecruitTime()) {
/* 45 */       return 15009;
/*    */     }
/* 47 */     PlayerComponent playerComponent = (PlayerComponent)playerSession.getPlayer().createIfNotExist(PlayerComponent.class);
/* 48 */     String context = LanguageConstant.getAndReplaceLanguage(904, new String[] { groupEntity.getGroupName(), String.valueOf(groupEntity.getId()) });
/* 49 */     ChatByChannelResponse chatByChannelResponse = new ChatByChannelResponse();
/* 50 */     ChatInfo chatInfo = new ChatInfo();
/* 51 */     chatInfo.type = 0;
/* 52 */     chatInfo.sendPlayerId = playerSession.getPlayer().getPlayerId();
/* 53 */     chatInfo.sendPlayerName = playerSession.getPlayer().getPlayerName();
/* 54 */     chatInfo.vip = playerComponent.getVip();
/* 55 */     chatInfo.titleId = playerComponent.getWearTitle();
/* 56 */     chatInfo.sex = playerComponent.getSex();
/* 57 */     chatInfo.head = PlayerUtil.getHeadUrl(playerSession.getPlayer().getPlayerId());
/* 58 */     chatInfo.context = context;
/* 59 */     chatInfo.time = TimeUtil.currentTime();
/* 60 */     chatInfo.quality = playerComponent.getQuality();
/* 61 */     chatByChannelResponse.list.add(chatInfo);
/* 62 */     LookUpService.broadcast((ResponseBase)chatByChannelResponse);
/* 63 */     GroupParameter groupParameter = (GroupParameter)ParameterConstant.getParameter(11);
/* 64 */     groupEntity.setNextRecruitTime(TimeUtil.currentTime() + groupParameter.getGroupRecruitCD());
/* 65 */     ((GroupRecruitResponse)this.response).nextSendTime = groupEntity.getNextRecruitTime();
/* 66 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\group\GroupRecruitProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */