/*    */ package com.linlongyx.sanguo.webgame.processors.chat;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.dao.entity.IEntity;
/*    */ import com.linlongyx.core.framework.logic.IPlayer;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.core.utils.ChatUtil;
/*    */ import com.linlongyx.core.utils.TimeUtil;
/*    */ import com.linlongyx.sanguo.webgame.app.chat.ChatComponent;
/*    */ import com.linlongyx.sanguo.webgame.app.chat.ChatEntity;
/*    */ import com.linlongyx.sanguo.webgame.app.player.PlayerComponent;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.chat.ChatRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.chat.ChatResponse;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.ChatPersonalInfo;
/*    */ import com.linlongyx.sanguo.webgame.service.LookUpService;
/*    */ 
/*    */ 
/*    */ public class ChatProcessor
/*    */   extends ProcessorBase<ChatRequest, ChatResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 24 */     this.response = (ResponseBase)new ChatResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, ChatRequest request) {
/* 29 */     ((ChatResponse)this.response).sendPlayerId = playerSession.getPlayer().getPlayerId();
/* 30 */     ((ChatResponse)this.response).context = ChatUtil.replaceSensitiveWord(request.context, "*");
/* 31 */     if (request.targetPlayerId > 0L) {
/* 32 */       IPlayer targetIPlayer = LookUpService.getByPlayerId(request.targetPlayerId);
/* 33 */       if (targetIPlayer != null && targetIPlayer.getSession().isLogin()) {
/* 34 */         targetIPlayer.getSession().sendMessage(this.response);
/*    */       }
/*    */       
/* 37 */       ChatPersonalInfo chatPersonalInfo = new ChatPersonalInfo();
/* 38 */       chatPersonalInfo.sendPlayerId = ((ChatResponse)this.response).sendPlayerId;
/* 39 */       chatPersonalInfo.time = TimeUtil.currentTime();
/* 40 */       chatPersonalInfo.context = ((ChatResponse)this.response).context;
/* 41 */       ChatComponent chatComponent = (ChatComponent)playerSession.getPlayer().createIfNotExist(ChatComponent.class);
/* 42 */       if (!chatComponent.getEntityMap().keySet().contains(String.valueOf(request.targetPlayerId))) {
/* 43 */         ChatEntity chatEntity = (ChatEntity)chatComponent.createEntity(Long.valueOf(request.targetPlayerId));
/* 44 */         PlayerComponent playerComponent = LookUpService.getPlayerComponent(request.targetPlayerId);
/* 45 */         chatEntity.setToName((null != playerComponent) ? playerComponent.getPlayerName() : ("" + request.targetPlayerId));
/* 46 */         chatComponent.addEntity((IEntity)chatEntity);
/*    */       } 
/* 48 */       chatComponent.getEntity(request.targetPlayerId).getPrivateList().add(chatPersonalInfo);
/* 49 */       chatComponent.saveToDB();
/*    */       
/* 51 */       int delSize = chatComponent.getEntity(request.targetPlayerId).getPrivateList().size() - 50;
/* 52 */       if (delSize > 0) {
/* 53 */         for (int i = 0; i < delSize; i++) {
/* 54 */           chatComponent.getEntity(request.targetPlayerId).getPrivateList().remove(0);
/*    */         }
/*    */       }
/*    */       
/* 58 */       ChatComponent chatComponent2 = (ChatComponent)LookUpService.getComponent(request.targetPlayerId, ChatComponent.class);
/* 59 */       if (null != chatComponent2) {
/* 60 */         if (!chatComponent2.getEntityMap().keySet().contains(String.valueOf(playerSession.getPlayer().getPlayerId()))) {
/* 61 */           ChatEntity chatEntity = (ChatEntity)chatComponent2.createEntity(Long.valueOf(playerSession.getPlayer().getPlayerId()));
/* 62 */           PlayerComponent playerComponent = (PlayerComponent)playerSession.getPlayer().createIfNotExist(PlayerComponent.class);
/* 63 */           chatEntity.setToName(playerComponent.getPlayerName());
/* 64 */           chatComponent2.addEntity((IEntity)chatEntity);
/*    */         } 
/* 66 */         chatComponent2.getEntity(playerSession.getPlayer().getPlayerId()).getPrivateList().add(chatPersonalInfo);
/* 67 */         delSize = chatComponent2.getEntity(playerSession.getPlayer().getPlayerId()).getPrivateList().size() - 50;
/* 68 */         if (delSize > 0) {
/* 69 */           for (int i = 0; i < delSize; i++) {
/* 70 */             chatComponent2.getEntity(playerSession.getPlayer().getPlayerId()).getPrivateList().remove(0);
/*    */           }
/*    */         }
/* 73 */         chatComponent2.maybeSaveToDB();
/*    */       } 
/*    */     } 
/* 76 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\chat\ChatProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */