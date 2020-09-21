/*    */ package com.linlongyx.sanguo.webgame.proto.binary.destiny;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Message
/*    */ public class DestinyMatchInfoResponse
/*    */   extends ResponseBase
/*    */ {
/*    */   public byte type;
/*    */   public int rank;
/*    */   public int destinyPoint;
/*    */   public int time;
/*    */   public int zoneId;
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 31 */     ProtocolUtil.writeByte(out, this.type);
/* 32 */     ProtocolUtil.writeInt(out, this.rank);
/* 33 */     ProtocolUtil.writeInt(out, this.destinyPoint);
/* 34 */     ProtocolUtil.writeInt(out, this.time);
/* 35 */     ProtocolUtil.writeInt(out, this.zoneId);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 40 */     this.type = ProtocolUtil.readUTFBinByte(in);
/* 41 */     this.rank = ProtocolUtil.readUTFBinInt(in);
/* 42 */     this.destinyPoint = ProtocolUtil.readUTFBinInt(in);
/* 43 */     this.time = ProtocolUtil.readUTFBinInt(in);
/* 44 */     this.zoneId = ProtocolUtil.readUTFBinInt(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public DestinyMatchInfoResponse clone() throws CloneNotSupportedException {
/* 49 */     DestinyMatchInfoResponse clone = (DestinyMatchInfoResponse)super.clone();
/* 50 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 55 */     this.type = 0;
/* 56 */     this.rank = 0;
/* 57 */     this.destinyPoint = 0;
/* 58 */     this.time = 0;
/* 59 */     this.zoneId = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 64 */     String retVal = "{\"type\":" + this.type + ",\"rank\":" + this.rank + ",\"destinyPoint\":" + this.destinyPoint + ",\"time\":" + this.time + ",\"zoneId\":" + this.zoneId + "}";
/* 65 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\destiny\DestinyMatchInfoResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */