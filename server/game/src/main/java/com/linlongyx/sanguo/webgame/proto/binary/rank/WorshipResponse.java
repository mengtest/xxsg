/*    */ package com.linlongyx.sanguo.webgame.proto.binary.rank;
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
/*    */ public class WorshipResponse
/*    */   extends ResponseBase
/*    */ {
/*    */   public long playerId;
/*    */   public int worships;
/*    */   public int type;
/*    */   
/*    */   public WorshipResponse() {
/* 23 */     this.eventResponseId = 27002;
/* 24 */     this.codeType = 2;
/*    */   }
/*    */   
/*    */   public WorshipResponse(short retCode) {
/* 28 */     this.eventResponseId = 27002;
/* 29 */     this.codeType = 2;
/* 30 */     this.retCode = retCode;
/*    */   }
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 35 */     ProtocolUtil.writeLong(out, this.playerId);
/* 36 */     ProtocolUtil.writeInt(out, this.worships);
/* 37 */     ProtocolUtil.writeInt(out, this.type);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 42 */     this.playerId = ProtocolUtil.readUTFBinLong(in);
/* 43 */     this.worships = ProtocolUtil.readUTFBinInt(in);
/* 44 */     this.type = ProtocolUtil.readUTFBinInt(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public WorshipResponse clone() throws CloneNotSupportedException {
/* 49 */     WorshipResponse clone = (WorshipResponse)super.clone();
/* 50 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 55 */     this.playerId = 0L;
/* 56 */     this.worships = 0;
/* 57 */     this.type = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 62 */     String retVal = "{\"playerId\":" + this.playerId + ",\"worships\":" + this.worships + ",\"type\":" + this.type + "}";
/* 63 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\rank\WorshipResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */