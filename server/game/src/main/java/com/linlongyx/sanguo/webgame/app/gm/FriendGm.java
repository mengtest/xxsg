/*    */ package com.linlongyx.sanguo.webgame.app.gm;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.sanguo.webgame.processors.friend.FriendApplyProcessor;
/*    */ import com.linlongyx.sanguo.webgame.processors.friend.FriendLoginNoticeProcessor;
/*    */ import com.linlongyx.sanguo.webgame.processors.friend.FriendOpenProcessor;
/*    */ import com.linlongyx.sanguo.webgame.processors.friend.FriendSerachProcessor;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.friend.FriendApplyRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.friend.FriendDealApplyRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.friend.FriendReadChatRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.friend.FriendSendChatRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.friend.FriendSerachRequest;
/*    */ 
/*    */ public class FriendGm implements IGm {
/*    */   public void gm(IPlayerSession playerSession, String[] strings) {
/* 16 */     if (strings[2].equals("open")) {
/* 17 */       (new FriendOpenProcessor()).handle(playerSession, (RequestBase)new FriendOpenRequest());
/* 18 */     } else if (strings[2].equals("login")) {
/* 19 */       (new FriendLoginNoticeProcessor()).handle(playerSession, (RequestBase)new FriendLoginNoticeRequest());
/* 20 */     } else if (strings[2].equals("read")) {
/* 21 */       FriendReadChatRequest request = new FriendReadChatRequest();
/* 22 */       request.needChat = Byte.parseByte(strings[3]);
/* 23 */       request.playerId = Long.parseLong(strings[4]);
/* 24 */       (new FriendReadChatProcessor()).handle(playerSession, (RequestBase)request);
/* 25 */     } else if (strings[2].equals("send")) {
/* 26 */       FriendSendChatRequest request = new FriendSendChatRequest();
/* 27 */       request.playerId = Long.parseLong(strings[3]);
/* 28 */       request.context = strings[4];
/* 29 */       (new FriendSendChatProcessor()).handle(playerSession, (RequestBase)request);
/* 30 */     } else if (strings[2].equals("apply")) {
/* 31 */       FriendApplyRequest request = new FriendApplyRequest();
/* 32 */       request.playerIds.add(Long.valueOf(Long.parseLong(strings[3])));
/* 33 */       (new FriendApplyProcessor()).handle(playerSession, (RequestBase)request);
/* 34 */     } else if (strings[2].equals("dealApply")) {
/* 35 */       FriendDealApplyRequest request = new FriendDealApplyRequest();
/* 36 */       request.playerIds.add(Long.valueOf(Long.parseLong(strings[3])));
/* 37 */       request.agree = Byte.parseByte(strings[4]);
/* 38 */       (new FriendDealApplyProcessor()).handle(playerSession, (RequestBase)request);
/* 39 */     } else if (strings[2].equals("serach")) {
/* 40 */       FriendSerachRequest request = new FriendSerachRequest();
/* 41 */       request.name = strings[3];
/* 42 */       (new FriendSerachProcessor()).handle(playerSession, (RequestBase)request);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\app\gm\FriendGm.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */