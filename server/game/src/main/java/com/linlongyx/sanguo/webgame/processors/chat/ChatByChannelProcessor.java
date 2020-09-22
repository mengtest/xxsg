/*     */ package com.linlongyx.sanguo.webgame.processors.chat;
/*     */ 
/*     */ import com.linlongyx.core.framework.base.ProcessorBase;
/*     */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*     */ import com.linlongyx.core.framework.protocol.RequestBase;
/*     */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*     */ import com.linlongyx.core.utils.ChatUtil;
/*     */ import com.linlongyx.core.utils.TimeUtil;
/*     */ import com.linlongyx.sanguo.webgame.app.gm.GmUtil;
/*     */ import com.linlongyx.sanguo.webgame.app.group.GroupEntity;
/*     */ import com.linlongyx.sanguo.webgame.app.group.GroupMemberComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.group.GroupMemberEntity;
/*     */ import com.linlongyx.sanguo.webgame.app.player.PlayerComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.task.TaskComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.user.UserComponent;
/*     */ import com.linlongyx.sanguo.webgame.constant.MContext;
/*     */ import com.linlongyx.sanguo.webgame.constant.TaskType;
/*     */ import com.linlongyx.sanguo.webgame.processors.player.PlayerUtil;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.chat.ChatByChannelRequest;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.chat.ChatByChannelResponse;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.ChatInfo;
/*     */ import com.linlongyx.sanguo.webgame.service.GroupService;
/*     */ import com.linlongyx.sanguo.webgame.service.LookUpService;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ChatByChannelProcessor
/*     */   extends ProcessorBase<ChatByChannelRequest, ChatByChannelResponse>
/*     */ {
/*     */   protected void initResponse() {
/*  32 */     this.response = (ResponseBase)new ChatByChannelResponse();
/*     */   } protected short handleRequest(IPlayerSession playerSession, ChatByChannelRequest request) {
/*     */     TaskComponent taskComponent;
/*     */     GroupMemberComponent groupMemberComponent;
/*     */     GroupMemberEntity memberEntity;
/*  37 */     PlayerComponent playerComponent = (PlayerComponent)playerSession.getPlayer().createIfNotExist(PlayerComponent.class);
/*  38 */     UserComponent userComponent = (UserComponent)playerSession.getPlayer().createIfNotExist(UserComponent.class);
/*  39 */     if (request.context.startsWith("/gm")) {
/*     */       try {
/*  41 */         if (playerComponent.isPrivilege(4)) {
/*  42 */           GmUtil.gm(playerSession, request.context);
/*     */         }
/*     */         
/*  45 */         ChatInfo chatInfo1 = new ChatInfo();
/*  46 */         chatInfo1.type = request.type;
/*  47 */         chatInfo1.sendPlayerId = playerSession.getPlayer().getPlayerId();
/*  48 */         chatInfo1.sendPlayerName = playerSession.getPlayer().getPlayerName();
/*  49 */         chatInfo1.vip = playerComponent.getVip();
/*  50 */         chatInfo1.titleId = playerComponent.getWearTitle();
/*  51 */         chatInfo1.sex = playerComponent.getSex();
/*  52 */         chatInfo1.head = PlayerUtil.getHeadUrl(playerSession.getPlayer().getPlayerId());
/*  53 */         chatInfo1.context = request.context;
/*  54 */         chatInfo1.time = TimeUtil.currentTime();
/*  55 */         chatInfo1.quality = playerComponent.getQuality();
/*  56 */         ((ChatByChannelResponse)this.response).list.add(chatInfo1);
/*  57 */       } catch (Exception e) {
/*  58 */         e.printStackTrace();
/*     */       } 
/*  60 */       ((ChatByChannelResponse)this.response).setRetCode((short)0);
/*  61 */       playerSession.sendMessage(this.response);
/*  62 */       return 0;
/*     */     } 
/*     */     
/*  65 */     if (playerComponent.isPrivilege(1)) {
/*  66 */       return 10021;
/*     */     }
/*  68 */     ChatInfo chatInfo = new ChatInfo();
/*  69 */     chatInfo.type = request.type;
/*  70 */     chatInfo.sendPlayerId = playerSession.getPlayer().getPlayerId();
/*  71 */     chatInfo.sendPlayerName = playerSession.getPlayer().getPlayerName();
/*  72 */     chatInfo.vip = playerComponent.getVip();
/*  73 */     chatInfo.titleId = playerComponent.getWearTitle();
/*  74 */     chatInfo.sex = playerComponent.getSex();
/*  75 */     chatInfo.head = PlayerUtil.getHeadUrl(playerSession.getPlayer().getPlayerId());
/*  76 */     chatInfo.context = ChatUtil.replaceSensitiveWord(request.context, "*");
/*  77 */     chatInfo.time = TimeUtil.currentTime();
/*  78 */     chatInfo.quality = playerComponent.getQuality();
/*  79 */     ((ChatByChannelResponse)this.response).list.add(chatInfo);
/*  80 */     switch (request.type) {
/*     */       case 0:
/*  82 */         LookUpService.broadcast(this.response);
/*  83 */         taskComponent = (TaskComponent)playerSession.getPlayer().createIfNotExist(TaskComponent.class);
/*  84 */         if (taskComponent != null) {
/*  85 */           taskComponent.refreshSchedule(TaskType.ChatWorld, 0, 1L);
/*     */         }
/*  87 */         ChatUtils.addChatList(chatInfo);
/*  88 */         ChatUtils.sendPlayerChatLog(0, playerComponent.getPlayerId(), playerComponent.getPlayerName(), request.context, playerComponent
/*  89 */             .getChannel(), userComponent.getUid(), playerSession.getClientIp());
/*     */         break;
/*     */       case 1:
/*  92 */         groupMemberComponent = (GroupMemberComponent)playerSession.getPlayer().createIfNotExist(GroupMemberComponent.class);
/*  93 */         memberEntity = (GroupMemberEntity)groupMemberComponent.getEntity();
/*  94 */         if (memberEntity != null && memberEntity.getId() != 0L) {
/*  95 */           GroupService blocBaseService = (GroupService)MContext.getBean("groupService");
/*  96 */           GroupEntity groupEntity = blocBaseService.getGroupEntity(memberEntity.getId());
/*  97 */           LookUpService.campBoradcast(this.response, groupEntity);
/*     */         } 
/*  99 */         ChatUtils.sendPlayerChatLog(1, playerComponent.getPlayerId(), playerComponent.getPlayerName(), request.context, playerComponent
/* 100 */             .getChannel(), userComponent.getUid(), playerSession.getClientIp());
/*     */         break;
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 109 */     ((ChatByChannelResponse)this.response).reset();
/* 110 */     return 0;
/*     */   }
/*     */   
/*     */   protected void process(IPlayerSession playerSession, ChatByChannelRequest request) throws Exception {
/* 114 */     short retCode = handleRequest(playerSession, request);
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\chat\ChatByChannelProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */