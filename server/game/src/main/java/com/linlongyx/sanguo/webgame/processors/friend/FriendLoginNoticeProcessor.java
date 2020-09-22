/*    */ package com.linlongyx.sanguo.webgame.processors.friend;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.app.friend.FriendComponent;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.friend.FriendLoginNoticeRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.friend.FriendLoginNoticeResponse;
/*    */ import java.util.Collection;
/*    */ import java.util.Map;
/*    */ import java.util.concurrent.LinkedBlockingQueue;
/*    */ import java.util.stream.Collectors;
/*    */ 
/*    */ public class FriendLoginNoticeProcessor
/*    */   extends ProcessorBase<FriendLoginNoticeRequest, FriendLoginNoticeResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 19 */     this.response = (ResponseBase)new FriendLoginNoticeResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, FriendLoginNoticeRequest request) {
/* 24 */     FriendComponent friendComponent = (FriendComponent)playerSession.getPlayer().createIfNotExist(FriendComponent.class);
/* 25 */     ((FriendLoginNoticeResponse)this.response).playerIds.addAll((Collection)friendComponent.getChatMap().entrySet().stream()
/* 26 */         .filter(entry -> (((LinkedBlockingQueue)entry.getValue()).size() > 0))
/* 27 */         .map(entry -> (Long)entry.getKey())
/* 28 */         .collect(Collectors.toList()));
/* 29 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\friend\FriendLoginNoticeProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */