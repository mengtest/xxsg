/*    */ package com.linlongyx.sanguo.webgame.proto.binary.team;
/*    */ 
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
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
/*    */ 
/*    */ @Message
/*    */ public class TeamPlayerReadyRequest
/*    */   extends RequestBase
/*    */ {
/*    */   public long teamId;
/*    */   public byte isReady;
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 29 */     ProtocolUtil.writeLong(out, this.teamId);
/* 30 */     ProtocolUtil.writeByte(out, this.isReady);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 35 */     this.teamId = ProtocolUtil.readUTFBinLong(in);
/* 36 */     this.isReady = ProtocolUtil.readUTFBinByte(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public TeamPlayerReadyRequest clone() throws CloneNotSupportedException {
/* 41 */     TeamPlayerReadyRequest clone = (TeamPlayerReadyRequest)super.clone();
/* 42 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 47 */     this.teamId = 0L;
/* 48 */     this.isReady = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 53 */     String retVal = "{\"teamId\":" + this.teamId + ",\"isReady\":" + this.isReady + "}";
/* 54 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\team\TeamPlayerReadyRequest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */