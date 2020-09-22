/*    */ package com.linlongyx.sanguo.webgame.proto.binary.friend;
/*    */ 
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.proto.ProtocolUtil;
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
/*    */ public class FriendInfoNoticeResponse
/*    */   extends ResponseBase
/*    */ {
/*    */   public byte type;
/* 20 */   public FriendInfo friendInfo = new FriendInfo();
/*    */   
/*    */   public FriendInfoNoticeResponse() {
/* 23 */     this.eventResponseId = 23414;
/* 24 */     this.codeType = 2;
/*    */   }
/*    */   
/*    */   public FriendInfoNoticeResponse(short retCode) {
/* 28 */     this.eventResponseId = 23414;
/* 29 */     this.codeType = 2;
/* 30 */     this.retCode = retCode;
/*    */   }
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 35 */     ProtocolUtil.writeByte(out, this.type);
/* 36 */     this.friendInfo.serial(out);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 41 */     this.type = ProtocolUtil.readUTFBinByte(in);
/* 42 */     this.friendInfo = new FriendInfo();
/* 43 */     this.friendInfo.unserial(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public FriendInfoNoticeResponse clone() throws CloneNotSupportedException {
/* 48 */     FriendInfoNoticeResponse clone = (FriendInfoNoticeResponse)super.clone();
/* 49 */     clone.friendInfo = this.friendInfo.clone();
/* 50 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 55 */     this.type = 0;
/* 56 */     this.friendInfo.reset();
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 61 */     String retVal = "{\"type\":" + this.type + ",\"friendInfo\":" + this.friendInfo.toString() + "}";
/* 62 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\friend\FriendInfoNoticeResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */