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
/*    */ public class GroupInsInfoResponse
/*    */   extends ResponseBase
/*    */ {
/*    */   public int insId;
/*    */   public int times;
/*    */   public long hp;
/*    */   public long maxHp;
/*    */   public byte reset;
/*    */   public byte isLeader;
/*    */   
/*    */   public GroupInsInfoResponse() {
/* 26 */     this.eventResponseId = 21119;
/* 27 */     this.codeType = 2;
/*    */   }
/*    */   
/*    */   public GroupInsInfoResponse(short retCode) {
/* 31 */     this.eventResponseId = 21119;
/* 32 */     this.codeType = 2;
/* 33 */     this.retCode = retCode;
/*    */   }
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 38 */     ProtocolUtil.writeInt(out, this.insId);
/* 39 */     ProtocolUtil.writeInt(out, this.times);
/* 40 */     ProtocolUtil.writeLong(out, this.hp);
/* 41 */     ProtocolUtil.writeLong(out, this.maxHp);
/* 42 */     ProtocolUtil.writeByte(out, this.reset);
/* 43 */     ProtocolUtil.writeByte(out, this.isLeader);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 48 */     this.insId = ProtocolUtil.readUTFBinInt(in);
/* 49 */     this.times = ProtocolUtil.readUTFBinInt(in);
/* 50 */     this.hp = ProtocolUtil.readUTFBinLong(in);
/* 51 */     this.maxHp = ProtocolUtil.readUTFBinLong(in);
/* 52 */     this.reset = ProtocolUtil.readUTFBinByte(in);
/* 53 */     this.isLeader = ProtocolUtil.readUTFBinByte(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public GroupInsInfoResponse clone() throws CloneNotSupportedException {
/* 58 */     GroupInsInfoResponse clone = (GroupInsInfoResponse)super.clone();
/* 59 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 64 */     this.insId = 0;
/* 65 */     this.times = 0;
/* 66 */     this.hp = 0L;
/* 67 */     this.maxHp = 0L;
/* 68 */     this.reset = 0;
/* 69 */     this.isLeader = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 74 */     String retVal = "{\"insId\":" + this.insId + ",\"times\":" + this.times + ",\"hp\":" + this.hp + ",\"maxHp\":" + this.maxHp + ",\"reset\":" + this.reset + ",\"isLeader\":" + this.isLeader + "}";
/* 75 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\group\GroupInsInfoResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */