/*    */ package com.linlongyx.sanguo.webgame.proto.binary.group;
/*    */ 
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.proto.ProtocolUtil;
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
/*    */ public class GroupMemberManageResponse
/*    */   extends ResponseBase
/*    */ {
/*    */   public int type;
/*    */   public long playerId;
/*    */   
/*    */   public GroupMemberManageResponse() {
/* 22 */     this.eventResponseId = 21110;
/* 23 */     this.codeType = 2;
/*    */   }
/*    */   
/*    */   public GroupMemberManageResponse(short retCode) {
/* 27 */     this.eventResponseId = 21110;
/* 28 */     this.codeType = 2;
/* 29 */     this.retCode = retCode;
/*    */   }
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 34 */     ProtocolUtil.writeInt(out, this.type);
/* 35 */     ProtocolUtil.writeLong(out, this.playerId);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 40 */     this.type = ProtocolUtil.readUTFBinInt(in);
/* 41 */     this.playerId = ProtocolUtil.readUTFBinLong(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public GroupMemberManageResponse clone() throws CloneNotSupportedException {
/* 46 */     GroupMemberManageResponse clone = (GroupMemberManageResponse)super.clone();
/* 47 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 52 */     this.type = 0;
/* 53 */     this.playerId = 0L;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 58 */     String retVal = "{\"type\":" + this.type + ",\"playerId\":" + this.playerId + "}";
/* 59 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\group\GroupMemberManageResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */