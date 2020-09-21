/*    */ package com.linlongyx.sanguo.webgame.proto.binary.group;
/*    */ 
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.proto.ProtocolUtil;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.GroupData;
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
/*    */ public class GroupInfoResponse
/*    */   extends ResponseBase
/*    */ {
/*    */   public int position;
/* 20 */   public GroupData data = new GroupData();
/*    */   public long totalOffer;
/*    */   public int helpTimes;
/*    */   
/*    */   public GroupInfoResponse() {
/* 25 */     this.eventResponseId = 21101;
/* 26 */     this.codeType = 2;
/*    */   }
/*    */   
/*    */   public GroupInfoResponse(short retCode) {
/* 30 */     this.eventResponseId = 21101;
/* 31 */     this.codeType = 2;
/* 32 */     this.retCode = retCode;
/*    */   }
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 37 */     ProtocolUtil.writeInt(out, this.position);
/* 38 */     this.data.serial(out);
/* 39 */     ProtocolUtil.writeLong(out, this.totalOffer);
/* 40 */     ProtocolUtil.writeInt(out, this.helpTimes);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 45 */     this.position = ProtocolUtil.readUTFBinInt(in);
/* 46 */     this.data = new GroupData();
/* 47 */     this.data.unserial(in);
/* 48 */     this.totalOffer = ProtocolUtil.readUTFBinLong(in);
/* 49 */     this.helpTimes = ProtocolUtil.readUTFBinInt(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public GroupInfoResponse clone() throws CloneNotSupportedException {
/* 54 */     GroupInfoResponse clone = (GroupInfoResponse)super.clone();
/* 55 */     clone.data = this.data.clone();
/* 56 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 61 */     this.position = 0;
/* 62 */     this.data.reset();
/* 63 */     this.totalOffer = 0L;
/* 64 */     this.helpTimes = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 69 */     String retVal = "{\"position\":" + this.position + ",\"data\":" + this.data.toString() + ",\"totalOffer\":" + this.totalOffer + ",\"helpTimes\":" + this.helpTimes + "}";
/* 70 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\group\GroupInfoResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */