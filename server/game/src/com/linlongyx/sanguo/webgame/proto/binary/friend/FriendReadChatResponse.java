/*    */ package com.linlongyx.sanguo.webgame.proto.binary.friend;
/*    */ 
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.FriendChatInfo;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.FriendInfo;
/*    */ import io.netty.buffer.ByteBuf;
/*    */ import org.msgpack.annotation.Message;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Message
/*    */ public class FriendReadChatResponse
/*    */   extends ResponseBase
/*    */ {
/* 19 */   public FriendInfo friendInfo = new FriendInfo();
/* 20 */   public FriendChatInfo friendChatInfo = new FriendChatInfo();
/*    */   
/*    */   public FriendReadChatResponse() {
/* 23 */     this.eventResponseId = 23403;
/* 24 */     this.codeType = 2;
/*    */   }
/*    */   
/*    */   public FriendReadChatResponse(short retCode) {
/* 28 */     this.eventResponseId = 23403;
/* 29 */     this.codeType = 2;
/* 30 */     this.retCode = retCode;
/*    */   }
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 35 */     this.friendInfo.serial(out);
/* 36 */     this.friendChatInfo.serial(out);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 41 */     this.friendInfo = new FriendInfo();
/* 42 */     this.friendInfo.unserial(in);
/* 43 */     this.friendChatInfo = new FriendChatInfo();
/* 44 */     this.friendChatInfo.unserial(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public FriendReadChatResponse clone() throws CloneNotSupportedException {
/* 49 */     FriendReadChatResponse clone = (FriendReadChatResponse)super.clone();
/* 50 */     clone.friendInfo = this.friendInfo.clone();
/* 51 */     clone.friendChatInfo = this.friendChatInfo.clone();
/* 52 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 57 */     this.friendInfo.reset();
/* 58 */     this.friendChatInfo.reset();
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 63 */     String retVal = "{\"friendInfo\":" + this.friendInfo.toString() + ",\"friendChatInfo\":" + this.friendChatInfo.toString() + "}";
/* 64 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\friend\FriendReadChatResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */