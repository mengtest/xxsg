/*    */ package com.linlongyx.sanguo.webgame.processors.friend;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.friend.FriendFavorValueNoticeRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.friend.FriendFavorValueNoticeResponse;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class FriendFavorValueNoticeProcessor
/*    */   extends ProcessorBase<FriendFavorValueNoticeRequest, FriendFavorValueNoticeResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 16 */     this.response = (ResponseBase)new FriendFavorValueNoticeResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, FriendFavorValueNoticeRequest request) {
/* 21 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\friend\FriendFavorValueNoticeProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */