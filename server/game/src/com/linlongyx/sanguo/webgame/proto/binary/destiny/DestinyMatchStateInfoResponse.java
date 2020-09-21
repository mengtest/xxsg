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
/*    */ public class DestinyMatchStateInfoResponse
/*    */   extends ResponseBase
/*    */ {
/*    */   public int state;
/*    */   public byte canBet;
/*    */   public byte hasMatch;
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 29 */     ProtocolUtil.writeInt(out, this.state);
/* 30 */     ProtocolUtil.writeByte(out, this.canBet);
/* 31 */     ProtocolUtil.writeByte(out, this.hasMatch);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 36 */     this.state = ProtocolUtil.readUTFBinInt(in);
/* 37 */     this.canBet = ProtocolUtil.readUTFBinByte(in);
/* 38 */     this.hasMatch = ProtocolUtil.readUTFBinByte(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public DestinyMatchStateInfoResponse clone() throws CloneNotSupportedException {
/* 43 */     DestinyMatchStateInfoResponse clone = (DestinyMatchStateInfoResponse)super.clone();
/* 44 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 49 */     this.state = 0;
/* 50 */     this.canBet = 0;
/* 51 */     this.hasMatch = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 56 */     String retVal = "{\"state\":" + this.state + ",\"canBet\":" + this.canBet + ",\"hasMatch\":" + this.hasMatch + "}";
/* 57 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\destiny\DestinyMatchStateInfoResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */